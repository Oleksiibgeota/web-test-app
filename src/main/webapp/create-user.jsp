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
        <form action="/mywebapp/createUser" method="POST">
            name<br>
            <input type="text" name="firstname"><br>
            Last name:<br>
            <input type="text" name="lastname"><br>
            <input type="submit" name = "action" value="Create">
            <input type="submit" name = "action" value="Delete">
        </form>
        <form action="/mywebapp/createCar" method="POST">
                    id user<br>
                    <input type="text" name="idUser"><br>
                    car name<br>
                    <input type="text" name="carName"><br>
                    <input type="submit" name = "actionForCar" value="Create">
                    <input type="submit" name = "actionForCar" value="Delete">
        </form>
    </div>
    <div id = "right">
        <a href="/mywebapp/">All Users</a>
        <a href="/mywebapp/createUser">Create user</a>
        <a href="/mywebapp/createCar">Create Car</a>
        <a href="/mywebapp/get">View user</a>
    </div>
</body>
</html>