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
    <h1>공지사항 - 글 등록</h1>
    <br>

    <div class="form-group">
        <label for="writer">작성자</label>
        <input style="width: 400px" type="text" class="form-control" id="writer" placeholder="작성자를 입력하세요">
    </div>
    <div class="form-group">
        <label for="title">제목</label>
        <input style="width: 400px" type="text" class="form-control" id="title" placeholder="제목을 입력하세요">
    </div>
    <div class="form-group">
        <label for="content">내용</label>
        <textarea style="width: 400px" cols="40" rows="10" class="form-control" id="content" placeholder="내용을 입력하세요"></textarea>
    </div>

    <button type="button" onclick="location.href='/index'" class="btn btn-primary">리스트</button>
    <button type="button" class="btn btn-success" id="insertBtn">등록</button>
</div>

<script>

    $("#insertBtn").click(function() {
        var writer = $("#writer").val();
        var title = $("#title").val();
        var content = $("#content").val();

        if (writer == "") {
            alert("작성자를 입력해주세요.");
        } else if (title == "") {
            alert("제목을 입력해주세요.");
        } else {
            $.ajax({
                url: "/api/notices",
                type: "POST",
                contentType: 'application/json',
                data: JSON.stringify({ title: title,
                                       content: content,
                                       writer: writer })
            }).done(function () {
                alert('등록되었습니다.');
                location.href='/index';
            })
        }
    });

</script>

</body>
</html>
</head>