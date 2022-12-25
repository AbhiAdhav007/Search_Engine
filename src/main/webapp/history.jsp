<%@page import = "java.util.ArrayList"%>
<%@page import = "com.Ab.HistoryResult"%>
<html>
<head>
<link rel = "stylesheet" type = "text/css" href = "styles.css">
</head>

    <body>
        <form action = "Search">
              <input type = "text" name = "keyword">
              <button type = "Submit">Search</button>
        </form>
           <div class = "resultTable">
       <table border = 2>
              <tr>
                 <td>keyword</td>
                 <td>Link</td>
              </tr>
              <%
                  ArrayList<HistoryResult> results =(ArrayList<HistoryResult>) request.getAttribute("results");
                  for(HistoryResult result : results){
              %>
                   <tr>
                      <td><%out.println(result.getKeyword());%></td>
                      <td><a href = "<%out.println(result.getLink());%>"><%out.println(result.getLink());%></a></td>
                   </tr>
              <%
                   }
              %>
       </table>
       </div>
    </body>
</html>
