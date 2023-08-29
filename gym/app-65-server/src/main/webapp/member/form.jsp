<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"%>

    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>BITCAMP GYM</title>
    <link rel="stylesheet" href="/css/stylesform.css">
    </head>
    <body>
    <jsp:include page="../header.jsp"/>
      <div class="main">
      <h1 class="logo">회원 등록</h1>
      <div class="container">
      <form action="/member/add" method="post" enctype="multipart/form-data">

        <input type='text' name='name' placeholder="이름" class="add">
        <input type="number" name="age" placeholder="나이" class="add">

    <input type="tel" name="phone_number" placeholder="핸드폰번호" class="add">

    <input type="password" name="password" placeholder="비밀번호" class="add">


            <select name="per" placeholder="등록개월" class="add">
              <option value="1">1개월</option>
              <option value="3">3개월</option>
              <option value="6">6개월</option>
            </select>

            <tr>
      <th>사진</th> <td><input type='file' name='photo'></td>
    </tr>

        <button button id="buttonform" type="submit"  class="add">등록</button>
        <button button id="cancelButton" type="button"  class="add">취소</button>
      </form>
      </div>
      </div>

      <script>
        document.getElementById("cancelButton").addEventListener("click", function() {
          // 리스트 페이지 URL로 리디렉션
          window.location.href = "/member/list";
        });
      </script>

<jsp:include page="../footer.jsp"/>
    </body>
    </html>