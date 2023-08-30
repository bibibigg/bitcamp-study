<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset='UTF-8'>
    <title>비트캠프</title>
    <link rel="stylesheet" href="/css/stylesform.css">
</head>
<body>
    <div class='main'>
        <h1 class='logo'>게시글</h1>
        <div class='container'>
            <form action='add' method='post' enctype='multipart/form-data'>
                <input type='text' name='title' placeholder='제목' class='add'><br>
                <textarea name='content' placeholder='내용' class='contentadd'></textarea><br>
                파일 <input type='file' name='files' multiple> <br>
                <input type='hidden' name='category' value='${param.category}'>
                <button id='buttonform' class='add'>등록</button>
                <button id='cancelButton' type='button' class='add' onclick="goBack()">취소</button>
            </form>
        </div>
    </div>

    <script>
        function goBack() {
            window.location.href = 'list?category=${param.category}';
        }
    </script>
</body>
</html>