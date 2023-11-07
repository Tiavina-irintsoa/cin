<!DOCTYPE html>
<html>
  <head>
    <title>Carte Google Maps</title>
  </head>
  <body>
    <form action="carte_ajout" method="post">
      <p>
        <label for=""> Adresse </label>
        <input type="text" name="adresse" />
      </p>
      <p>
        <label for=""> description </label>
        <input type="text" name="description" />
      </p>
      <p>
        <input type="hidden" name="idcin" value="<%= (String) request.getAttribute("idcin")%>" />
      </p>
      <p>
        <input type="submit" value="Enregistrer" />
      </p>
    </form>
    <div id="map" style="height: 400px; width: 100%"></div>
    <button id="toggleRecording">Activer l'enregistrement</button>
    <button id="saveData" disabled>Enregistrer les données</button>
    <script>
      var map; // Déclarer la carte comme une variable globale
      var isRecording = false;
      var recordedData = [];
      var markers = [];
      var polygon;
      console.log("hoho");
      function initMap() {
        var antananarivo = { lat: -18.87919, lng: 47.507905 };
        map = new google.maps.Map(document.getElementById("map"), {
          zoom: 10,
          center: antananarivo,
        });

        var toggleButton = document.getElementById("toggleRecording");
        toggleButton.addEventListener("click", function () {
          isRecording = !isRecording;
          toggleButton.textContent = isRecording
            ? "Désactiver l'enregistrement"
            : "Activer l'enregistrement";
          document.getElementById("saveData").disabled = !isRecording;
        });

        var saveButton = document.getElementById("saveData");
        saveButton.addEventListener("click", function () {
          if (recordedData.length > 0) {
            console.log(recordedData);
            // Vous pouvez ajouter les données en tant que paramètres de requête à l'URL GET
            var dataToSend = JSON.stringify({
              coordonnee: recordedData,
              id_cin: 1,
            });
            var url = "/presidence/foncier";
            var xhr = new XMLHttpRequest();
            xhr.open("POST", url, true);
            xhr.setRequestHeader("Content-Type", "application/json");
            console.log("tonga teto");
            xhr.onreadystatechange = function () {
              if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                  alert("Données enregistrées avec succès.");
                  recordedData = [];
                  localStorage.removeItem("recordedData");
                  clearMarkers();
                } else {
                  alert("Erreur lors de l'enregistrement des données.");
                }
              }
            };
            xhr.send(dataToSend);
          }
        });

        map.addListener("click", function (event) {
          if (isRecording) {
            var point = event.latLng;
            console.log("point : ");
            console.log(point);
            recordedData.push(point);
            createMarker(point);
            updatePolygon();
            localStorage.setItem("recordedData", JSON.stringify(recordedData));
          }
        });
      }

      function createMarker(location) {
        var marker = new google.maps.Marker({
          position: location,
          map: map,
        });
        markers.push(marker);
      }

      function clearMarkers() {
        for (var i = 0; i < markers.length; i++) {
          markers[i].setMap(null);
        }
        markers = [];
      }

      function updatePolygon() {
        if (polygon) {
          polygon.setMap(null);
        }
        if (recordedData.length > 1) {
          polygon = new google.maps.Polygon({
            paths: recordedData,
            strokeColor: "#FF0000",
            strokeOpacity: 1.0,
            strokeWeight: 2,
            fillColor: "#FF0000",
            fillOpacity: 0.35,
          });
          polygon.setMap(map);
        }
      }
    </script>
    <script
      async
      defer
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCec-7VgSCmboA2sSYm52X2rKS02pp9Mcw&callback=initMap"
    ></script>
  </body>
</html>
