<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"%>

    <!DOCTYPE html>
    <html>
    <head>
    <meta charset='UTF-8'>
    <title>BITCAMP GYM</title>
    <link rel="stylesheet" href="/css/stylesform.css">
    </head>
    <body>


      <div class="main">
    <h1 class="logo">login</h1>
    <div class="container">
    <form action='login' method="post">
      <input type='tel' name='phone_number' placeholder="PhoneNumber" class="add" value="${cookie.PhoneNumber.value}">
      <input type='password' name='password' placeholder="Password" class="add">
      <button button id="buttonform" class="add">login</button>
      <input type='checkbox' name='savePhoneNumber' ${cookie.PhoneNumber != null ? "checked" : ""}> 핸드폰번호 저장

    </form>
    </div>
    </div>


    </body>
    </html>

