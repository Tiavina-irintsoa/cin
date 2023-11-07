<!DOCTYPE html>
<html>
  <head>
    <title>Formulaire avec Ajout Dynamique</title>
  </head>
  <body>
    <form id="monFormulaire" action="/presidence/formulaireSubmit" method='POST'>
      <label for="adresse">Adresse:</label>
      <input type="text" name="adresse" id="adresse" required /><br />

      <label for="description">Description:</label>
      <textarea name="description" id="description" required></textarea><br />

      <input type="hidden" name="idcin" id="idcin" value="<%=(String) request.getAttribute("idcin")%>" />

      <label for="latitude">Latitude:</label>
      <input type="text" name="latitude" id="latitude" required /><br />

      <label for="longitude">Longitude:</label>
      <input type="text" name="longitude" id="longitude" required /><br />

      <button type="button" id="ajouterChamp">Ajouter</button>
      <p>
        <input type="submit" value="valider" />
      </p>
    </form>

    <script>
      document
        .getElementById("ajouterChamp")
        .addEventListener("click", function () {
          var formulaire = document.getElementById("monFormulaire");

          var newLatitudeInput = document.createElement("input");
          newLatitudeInput.type = "text";
          newLatitudeInput.name = "latitude";
          newLatitudeInput.placeholder = "Nouvelle Latitude";
          newLatitudeInput.required = true;

          var newLongitudeInput = document.createElement("input");
          newLongitudeInput.type = "text";
          newLongitudeInput.name = "longitude";
          newLongitudeInput.placeholder = "Nouvelle Longitude";
          newLongitudeInput.required = true;

          formulaire.appendChild(document.createElement("br"));
          formulaire.appendChild(
            document.createTextNode("Nouvelle Latitude: ")
          );
          formulaire.appendChild(newLatitudeInput);
          formulaire.appendChild(document.createElement("br"));
          formulaire.appendChild(
            document.createTextNode("Nouvelle Longitude: ")
          );
          formulaire.appendChild(newLongitudeInput);
        });
    </script>
  </body>
</html>

<%-- ajoute les nouveaux champs devant le bouton valider et ajouter avec des names
différents mais qui seront ensuit traiter dans un servlet java pour etre en
liste de type Coordonnee{ longitude , latitude } --%>
