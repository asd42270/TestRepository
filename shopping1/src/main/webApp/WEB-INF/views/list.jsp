<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script>
        function goList(){
            $.ajax({
                url: "/shopping1/ajaxList.do",
                type: "get",
                dataType: "json", //받고자 하는 데이터 타입
                success: bookList,
                error: function (){alert("오류입니다.");}
            }); //$는 선택을 의미함.(jquery에 있는 함수 중에서 비동기 처리 관련 함수를 사용하겠다.
        }

        function bookList(data) {
            //alert((data)) //Json Array
            //동적인 뷰를 만들어준다.
            var result = "<table class='table table - bordered table - hover'>";
            result += "<thead>";
            result += "<tr>";
            result += "<th>번호</th>";
            result += "<th>제목</th>";
            result += "<th>가격</th>";
            result += "<th>저자</th>";
            result += "<th>페이지</th>";
            result += "</tr>";
            result += "</thead>";
                result += "<tbody>";
            //data -> [{}, {}, {}...]
            $.each(data, function (index, object) {
                result += "<tr>";
                    result += "<td>" + object.num + "<td>";
                    result += "<td>" + object.title + "<td>";
                    result += "<td>" + object.price + "<td>";
                    result += "<td>" + object.name + "<td>";
                    result += "<td>" + object.page + "<td>";
                result += "</tr>";0

            });
                result += "</tbody>";
            result += "</table>";
            $("#list").html(result);
            if ($("#list").css("display") == "none") {
                $("#list").css("display", "block");
            } else {
                $("#list").css("display", "none");
            }
        }

        function goDel(num){
            //삭제 요청, url 돌려서 or
            location.href="/shopping1/delete.do?num="+num;//get 방식 요청
        }
    </script>
</head>
<body>

<div class="container">
    <h2>MVC Framework</h2>
    <div class="card">
        <div class="card-header">
            <c:if test="${empty uservo}">
                <form class="form-inline" action="/shopping1/login.do" method="post">
                    <label for="text">아이디:</label>
                    <input type="text" class="form-control" placeholder="Enter 아이디" id="username" name="username">
                    <label for="pwd">비밀번호:</label>
                    <input type="password" class="form-control" placeholder="Enter password" id="password" name="password">
                    <div class="form-check">
                        <label class="form-check-label">
                            <input class="form-check-input" type="checkbox"> Remember me
                        </label>
                    </div>
                    <button type="submit" class="btn btn-primary">로그인</button>
                </form>
            </c:if>
            <c:if test="${!empty uservo}">
                <div class="row">
                    <div class="col-8 text-right">
                        Welcome, ${uservo.name} 님 환영 합니다.
                    </div>
                    <div class="col-4 text-left">
                        <form class="form-inline" action="/shopping1/logout.do" method="post">
                            <button type="submit" class="btn btn-primary btn-sm">로그아웃</button>
                        </form>
                    </div>
                </div>
            </c:if>
        </div>
        <div class="card-body">
            <table class="table table-bordered table-hover">
                <thead>
                    <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>가격</th>
                    <th>저자</th>
                    <th>페이지</th>
                    <th>삭제</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="book" items="${list}">
                    <tr>
                    <td>${book.num}</td>
                    <td>${book.title}</td>
                    <td>${book.price}</td>
                    <td>${book.name}</td>
                    <td>${book.page}</td>
                    <c:if test="${!empty uservo}">
                        <td><button type="button" class="btn btn-sm btn-danger" onclick="goDel(${book.num})">삭제</button></td>
                    </c:if>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
            <button class="btn btn-secondary btn-sm" onclick="location.href='/shopping1/register.do'">책 등록</button>
            <button class="btn btn-secondary btn-sm" onclick="goList()">북 리스트</button>
        </div>
        <div id="list" class="container" style="display: none">
            여기에 책 리스트 출력
        </div>
        <div class="card-footer">패스트캠퍼스_BE7_이유빈</div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>