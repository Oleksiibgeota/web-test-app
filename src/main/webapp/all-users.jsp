<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<title>ALL Users</title>
<style type="text/css">
   * { margin: 0; padding: 0; }
   p { padding: 10px; }
   #left { position: absolute; left: 11; width: 70%; }
   #right { position: absolute; right: 0; top: 5; width: 30%; }
    h2{
    font-size:18px;
    }
   table {
        font-size:25px;
        width: 80%;
        border: thin solid black;
        table-layout: fixed;
   }
   tbody td {
       font-size: 100%;
   }
</style>
</head>
<body>
    <h1>All users</h1>
    <div id="left">
        <table border="0" cellpadding="10">
            <thead>
                <tr align="left">
                  <th>Id</th>
                  <th>First name</th>
                  <th>Last name</th>
               </tr>
            </thead>
            <tbody >
               <c:forEach items="${users}" var="user">
                    <tr>
                    <td>${user.id}</td>
                    <td>${user.nameFirst}</td>
                    <td>${user.nameLast}</td>
                    </tr>
               </c:forEach>
            </tbody>
        </table><br>
    </div>

    <div id = "right">
        <a href="/mywebapp/">All Users</a>
        <a href="/mywebapp/create">Create user</a>
        <a href="/mywebapp/get">View user</a>
    </div>
</body>
</html>