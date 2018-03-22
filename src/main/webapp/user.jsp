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
        border-collapse: collapse;
        border: thin solid black;
        table-layout: fixed;
   }
    TD, TH {
            padding: 3px;
            border: 1px solid black;
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
                  <th width ="12%">Id</th>
                  <th width ="23%">First name</th>
                  <th width ="23%">Last name</th>
                  <th width = "42%">car</th>
               </tr>
            </thead>
            <tbody >
                    <tr>
                    <td>${user.id}</td>
                    <td>${user.nameFirst}</td>
                    <td>${user.nameLast}</td>
                    <td>
                        <table  border="0" cellpadding="10">
                            <thead>
                            </thead>
                            <tbody>
                                <c:forEach items="${cars}" var="car">
                                    <tr>
                                        <td>${car.name}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </td>
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