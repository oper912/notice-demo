<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>NOTICE</title>

    <script
            src="https://code.jquery.com/jquery-2.2.4.min.js"
            integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</head>

<body>
<div class="container">
    <h1>공지사항</h1>

    <input type="hidden" id="userId" name="userId" value="${user.id}">
    <div class="form-group">
        <label for="title">제목</label>
        <input type="text" class="form-control" id="title" name="title"
               placeholder="제목을 입력하세요" value="${notice.title}">
    </div>
    <div class="form-group">
        <label for="content">내용</label>
        <input type="text" class="form-control" id="content" name="content"
               placeholder="내용을 입력하세요" value="${notice.content}">
    </div>
    <button type="button" onclick="location.href='/index'" class="btn btn-primary">리스트</button>
    <button type="button" class="btn btn-success" id="updateBtn">수정</button>

</div>

<script>
    $("#updateBtn").click(function() {
        // 전처리
        $.ajax({
            url: "/api/notices/${notice.id}",
            type: "PUT",
            data: JSON.stringify({ title: $("#title").val(), content: $("#content").val(), userId: ${user.id} }),
            contentType: 'application/json'
        }).done(function(result) {
        })
    });
</script>

</body>
</html>