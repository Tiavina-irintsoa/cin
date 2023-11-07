<%@page import="devise.*"%>
<%@page import="java.util.*"%>

<% List<Devise> liste = (List<Devise>) request.getAttribute("liste");%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Transferer de l'argent</title>
    <link rel="stylesheet" href="assets/css/transfert.css" />
  </head>
  <body>
    <div id="filter"></div>
    <div id="content">
      <div id="left">
        <img src="assets/images/11515152.webp" alt="" />
      </div>
      <div id="right">
        <h2>Tranfert d'argent</h2>
        <form action="banque" method="post">
          <div class="input-group">
            <label for="">Envoyeur:</label>
            <input type="text" name="sender" />
          </div>
          <div class="input-group">
            <label for="">Receveur:</label>
            <input type="text" name="receiver" />
          </div>
          <div class="input-group">
            <label for="">Montant:</label>
            <input type="text" name="money" />
          </div>
          <div class="input-group">
            <label for="">Date:</label>
            <input type="date" name="date" style="width: 69%;height: 4vh;border-radius: 5px;border: none;background-color: #b4b2b2;padding: 5px;margin-top: 1vh;" />
          </div>
          <div class="input-group">
            <label for="">Devise:</label>
            <select name="devise" id="">
              <% for(Devise d:liste){ %>
                <option value="<%= d.getIdDevise()%>"><%= d.getNomDevise()%></option>
              <%}%>
            </select>
          </div>
          <input type="submit" value="Entrer" />
        </form>
      </div>
    </div>
  </body>
</html>
