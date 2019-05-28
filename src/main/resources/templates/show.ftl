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
    <h1>공지사항 - 글 상세</h1>
    <br>
    <table class="table">
        <tr>
            <td>제목</td>
            <td id="title" colspan="2"></td>
        </tr>
        <tr>
            <td>
                <label>작성자</label>
                <p id="writer"></p>
            </td>
            <td>
                <label>작성일</label>
                <p id="createdAt"></p>
            </td>
            <td>
                <label>수정일</label>
                <p id="updatedAt"></p>
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <label>내용</label>
                <p id="content"></p>
            </td>
        </tr>
    </table>

    <button type="button" onclick="location.href='/index'" class="btn btn-primary">리스트</button>
    <button type="button" onclick="location.href='/update/${noticeId}'" class="btn btn-warning">수정</button>
    <button type="button" class="btn btn-danger" id="deleteBtn">삭제</button>
</div>

<script>
    $(document).ready(function () {
        $.ajax({
            url: "/api/notices/${noticeId}",
            type: "GET",
            error : function(request) {
                alert(request.responseText);
            }
        }).done(function(result) {
            $('#title').text(result.title);
            $('#writer').text(result.writer);
            $('#createdAt').text(result.createdAt);
            $('#updatedAt').text(result.updatedAt);
            $('#content').text(result.content);
        })
    });

    $('#deleteBtn').click(function() {
        $.ajax({
            url: "/api/notices/${noticeId}",
            type: "DELETE"
        }).done(function() {
            alert(${noticeId} + '번 글이 삭제 되었습니다.');
            location.href='/index';
        })
    });
</script>

</body>
</html>