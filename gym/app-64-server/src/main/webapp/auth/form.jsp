<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"%>

    <!DOCTYPE html>
    <html>
    <head>
    <meta charset='UTF-8'>
    <title>BITCAMP GYM</title>
    <link rel="stylesheet" href="stylesform.css">
    </head>
    <body>


      <div class="main">
    <h1 class="logo">login</h1>
    <div class="container">
    <form action='/auth/login.jsp' method="post">
      <input type='tel' name='phone_number' placeholder="PhoneNumber" class="add">
      <input type='password' name='password' placeholder="Password" class="add">
      <button id='buttonform' class="add">login</button>

    </form>
    </div>
    </div>


    </body>
    </html>

