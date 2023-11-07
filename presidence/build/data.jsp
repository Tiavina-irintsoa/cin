<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="presidence.*" %>
<%@page import="sante.*" %>
<%@page import="foncier.*" %>
<%@page import="banque.*" %>

<% CitoyenData data = (CitoyenData) request.getAttribute("data"); %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="assets/css/data2.css" />
    <title>Informations sur <%=data.getSante().getNom()+" "+data.getSante().getPrenom()%></title>
  </head>
  <body>
    <div id="filter"></div>
    <div id="content">
      <div id="sante">
        <h1>Santé</h1>
        <div id="info-sante">
          <p><span class="bold">Nom:</span><%=data.getSante().getNom()%></p>
          <p><span class="bold">Prénom:</span><%=data.getSante().getPrenom()%></p>
          <p>
            <span class="bold">Date de Naissance:</span><%=data.getSante().getDateNaissance()%>
          </p>
        </div>
        <table border="1">
          <tbody>
            <tr>
              <td>Maladies:</td>
              <td>
                <ul>
                  <% for(Maladie m : data.getSante().getMaladie()){ %>
                    <li><%=m.getMaladie()%></li>
                  <% } %>
                </ul>
              </td>
            </tr>
            <tr>
              <td>Allergies</td>
              <td>
                <ul>
                  <% for(Allergie a : data.getSante().getAllergie()){ %>
                    <li><%=a.getAllergie()%></li>
                  <% } %>
                </ul>
              </td>
            </tr>
            <tr>
              <td>Operations</td>
              <td>
                <ul>
                  <% for(sante.Operation o : data.getSante().getOperation()){ %>
                    <li><%=o.getDateOperation()%>: <%=o.getPartieCorps().getNomPartieCorps()%></li>
                  <% } %>
                </ul>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div id="foncier">
        <h1>Foncier</h1>
        <div>
          Voici la liste complète des proprietes<span class="bold">:</span>
          <a href="carte?idcin=<%=data.getFoncier().getCitoyen().getIdcin()%>">
            Ajouter une propriete via la carte
          </a>
          <a href="formulaire?idcin=<%=data.getFoncier().getCitoyen().getIdcin()%>">
            Ajouter une propriete via le formulaire
          </a>
        </div>
        <table id="table">
          <thead>
            <th>Adresse</th>
            <th>Superficie</th>
            <th>Perimetre</th>
            <th>Description</th>

            <th></th>
          </thead>
          <tbody>
          <% for(Propriete p : data.getFoncier().getProprietes()){ %>
            <tr>
              <td><%=p.getAdresse()%></td>
              <td><%=p.getSuperficie()%></td>
              <td><%=p.getPerimetre()%> m2</td>
              <td><%=p.getDescription()%></td>
              <td><a href="details?idpropriete=<%=p.getIdpropriete()%>">Details</a></td>
            </tr>
          <% } %>
          </tbody>
        </table>
      </div>
      <div id="banque">
        <h1>Banque</h1>
        <p><span class="bold">Nom: </span><%=data.getBanque().getCitoyen().getNom()%></p>
        <p><span class="bold">Prenom: </span><%=data.getBanque().getCitoyen().getNom()%></p>
        <p><span class="bold">Solde en banque: </span><%=data.getBanque().getSolde()%> Ar</p>
        <p>Voici les opérations banquaires de <%=data.getBanque().getCitoyen().getNom()%>:</p>
        <table id="table">
          <thead>
            <th>
              Date
            </th>
            <th>
              Montant en plus (Ariary)
            </th>
            <th>
              Montant en moins (Ariary)
            </th>
          </thead>
          <tbody>
          <% for(banque.Operation o : data.getBanque().getOperations()){ %>
            <tr>
              <td>
                <%=o.getDateOperation()%>
              </td>
              <td style="text-align: right;">
                <%=o.getPlus()%>
              </td>
              <td style="text-align: right;">
                <%=o.getMoins()%>
              </td>
            </tr>
          <% } %>
          </tbody>
        </table>
      </div>

    </div>
  </body>
</html>
