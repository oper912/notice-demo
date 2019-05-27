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

    <style>
        .addDiv {
            float: left;
            width: 50%;
        }

        #searchDiv {
            float: left;
            width: 50%;
            text-align: right;
        }

        #searchType {
            height: 27px;
        }

        .topBtn {
            background-color: #568e94;
            border-color: #568e94;
        }
    </style>
</head>

<body>
<div class="container">
    <h1>공지사항</h1>
    <div class="addDiv">
        <button type="button" onclick="location.href='/insert'" class="btn btn-primary topBtn">글 등록</button>
        <button class="btn btn-primary topBtn" onclick="location.href='/index'">전체목록</button>
    </div>
    <div id="searchDiv">
        <select id="searchType">
            <option>선택없음</option>
            <option>제목</option>
            <option>작성자</option>
        </select>
        <input type="text" id="searchWord"/>
        <button class="searchBtn topBtn btn btn-primary">검색</button>
    </div>
    <div>
        <table class="table table-striped">
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>수정일</th>
            </tr>
            <#list noticeCriteria.notices as notice>
                <tr>

                    <td>${notice.id}</td>
                    <td><a href="./show/${notice.id}"> ${notice.title}</a></td>
                    <td>${notice.user.userId}</td>
<#--                    <td>${notice.createdAt?string["dd.MM.yyyy, HH:mm"]}</td>-->
                    <td>${notice.createdAt}</td>
                    <td>${notice.updatedAt}</td>
                </tr>
            </#list>
        </table>
    </div>

    <nav style="text-align: center;">
        <ul class="pagination myPage">
        </ul>
    </nav>
</div>

<script>
    $(document).ready(function () {
        paging();
    });

    function paging() {
        var str = "";

        if (${noticeCriteria.startPage} > ${noticeCriteria.pageSize}) {
            str += "<li class='page-item' id='prev'><a class='page-link' href='/index?" +
                "page=" + (${noticeCriteria.startPage} - 1) + "&searchType=${searchType}&searchWord=${searchWord}'>Prev</a></li>";
        }

        for (var i = ${noticeCriteria.startPage}; i <= ${noticeCriteria.endPage}; i++) {
            str += "<li class='page-item'><a class='page-link" + i + "' " +
                "href='/index?page=" + i + "&searchType=${searchType}&searchWord=${searchWord}'>" + i + "</a></li>";
        }

        if (${noticeCriteria.pagingCount} > ${noticeCriteria.endPage}) {
            str += "<li class='page-item' id='next'><a class='page-link' href='/index?" +
                "page=" + (${noticeCriteria.endPage} + 1) + "&searchType=${searchType}&searchWord=${searchWord}'>Next</a></li>";
        }

        $(".myPage").html(str);
    }

    $(".searchBtn").click(function() {
        var searchWord = $("#searchWord").val();
        var index = $("#searchType option").index($("#searchType option:selected"));

        switch (index) {
            case 0:
                alert("검색 타입을 정해주세요");
                break;
            case 1:
                if (searchWord == "") alert("검색어를 입력해주세요");
                else location.href = '/index?page=1&searchType=title&searchWord=' + searchWord;
                break;
            case 2:
                if (searchWord == "") alert("검색어를 입력해주세요");
                else location.href = '/index?page=1&searchType=title&searchWord=' + searchWord;
                break;
        }
    });
</script>
</body>
</html>