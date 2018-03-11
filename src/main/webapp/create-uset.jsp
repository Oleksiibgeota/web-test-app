<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
    <head>
        <title>Create User</title>
        <style type="text/css">
            * { margin: 0; padding: 0; }
            #left { position: absolute; left: 11; width: 70%; }
            #right { position: absolute; right: 0; top: 5; width: 30%; }
        </style>
    </head>
<body>
    <div id="left">
        <h1>Create user</h1>
        <h2>Add user form</h2>
        <form action="/mywebapp/create" method="POST">
            name<br>
            <input type="text" name="firstname"><br>
            Last name:<br>
            <input type="text" name="lastname"><br>
            <input type="submit" value="Create">
        </form>
        <h2>Delete user form</h2>
        <form action="/mywebapp/create" method="DELETE">
            name<br>
            <input type="text" name="firstname"><br>
            Last name:<br>
            <input type="text" name="lastname"><br>
            <input type="submit" value="Delete">
        </form>
    </div>
    <div id = "right">
        <a href="/mywebapp/">All Users</a>
        <a href="/mywebapp/create">Create user</a>
        <a href="/mywebapp/get">View user</a>
    </div>
</body>
</html>