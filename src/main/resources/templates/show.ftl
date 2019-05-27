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
    <table class="table">
        <tr>
            <td>제목</td><td id="title"></td>
        </tr>
<#--        <tr>-->
<#--            <td>작성자 : ${notice.user.userId}</td>-->
<#--            <td>작성일 : ${notice.createdAt}</td>-->
<#--            <td>최종수정일 : ${notice.updatedAt}</td>-->
<#--        </tr>-->
<#--        <tr>-->
<#--            <td>${notice.content}</td>-->
<#--        </tr>-->
    </table>

    <button type="button" onclick="location.href='/index'" class="btn btn-primary">리스트</button>
    <button type="button" onclick="location.href='/update/${notice.id}'" class="btn btn-warning">수정</button>
    <button type="button" class="btn btn-danger" id="deleteBtn">삭제</button>
</div>

<script>
    $(document).ready(function () {
        $.ajax({
            url: "/api/notices/${notice.id}",
            type: "GET"
        }).done(function(result) {
            console.log(result);
        })
    });
</script>

</body>
</html>