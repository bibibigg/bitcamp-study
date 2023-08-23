<%@ page
    language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <!DOCTYPE html>
    <html>
    <head>
    <meta charset='UTF-8'>
    <title>BITCAMP GYM</title>
    <link rel="stylesheet" href="../auth/stylesform.css">
    </head>
    <body>
    <div class='main'>
    <h1 class='logo'>게시글</h1>
    <div class='container'>
    <form action='/board/add' method='post' enctype="multipart/form-data">
    <input type='text' name='title' placeholder='제목' class='add'><br>
    <textarea name='content' placeholder='내용' class='contentadd'></textarea><br>
    <input type='hidden' name='category' value='<%= request.getParameter("category")%>'>
    <input type="file" name="files" multiple />
    <button id='buttonform' class='add'>등록</button> <%-- css미적용 --%>
    <button id='cancelButton' type='button' class='add'>취소</button>
    </form>
    </div>
    </div>
    <script>
    document.getElementById('cancelButton').addEventListener('click', function() {"
            + "      window.location.href = '/board/list?category=" + category + "';"
            + "    });
    </script>
    </body>
    </html>