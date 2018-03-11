<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>
        start page
    </title>
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
    </style>
</head>

<body>
<div id="left">
<form action="" method="GET">
    <p>Input user name : <input type="text" name="firstName"></p>
    <input type="submit" value="pass" />
</form>
    </div>
    <div id = "right">
        <a href="/mywebapp/mwa">mwa</a>
        <a href="/mywebapp/test">test</a>
    </div>
</body>
</html>