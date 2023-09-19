<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../layout/head.jsp" />
    <title>티스푼::${title }</title>
    <link rel="stylesheet" href="${path }/resources/css/sub.css">
</head>
<body>
<jsp:include page="../layout/header.jsp" />
<section class="hero is-info">
    <div class="container is-fullhd">
        <div class="hero-body has-text-centered">
            <p class="title">${title }</p>
            <p class="subtitle">${titleSub }</p>
        </div>
    </div>
</section>
<div class="container is-fullhd">
    <section class="section">
        <div class="tabs is-centered">
            <ul>
                <li class="is-active"><a>나의 정보</a></li>
                <li><a href="${path }/vote/getMyList.do">나의 투표 내역</a></li>
                <li><a href="${path }/attend/getMyAttend.do">나의 출석</a></li>
            </ul>
        </div>
        <section class="section">
            <h2 class="title has-text-centered">${title }</h2>
            <div class="columns is-mobile">
                <div class="column is-three-fifths is-offset-one-fifth">
                    <table class="table is-fullwidth is-bordered">
                        <tbody>
                        <tr>
                            <th>아이디</th>
                            <td>${member.id }</td>
                        </tr>
                        <tr>
                            <th>이름</th>
                            <td>${member.name }</td>
                        </tr>
                        <tr>
                            <th>이메일</th>
                            <td>${member.email }</td>
                        </tr>
                        <tr>
                            <th>전화번호</th>
                            <td>${member.tel }</td>
                        </tr>
                        <tr>
                            <th>생일</th>
                            <td>${member.birth }</td>
                        </tr>
                        <tr>
                            <th>주소</th>
                            <td><c:if test="${member.postcode != '' }">[${member.postcode }]</c:if> ${member.addr1 } ${member.addr2}</td>
                        </tr>
                        <tr>
                            <th>포인트</th>
                            <td>${member.pt }</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="buttons is-centered">
                <a href="${path }/user/edit.do" class="button is-mainColor">회원 정보 수정</a>
                <a href="${path }/user/deletePro.do?id=${member.id }" class="button is-success">회원 탈퇴</a>
            </div>
        </section>
    </section>
</div>
<jsp:include page="../layout/footer.jsp" />
</body>
</html>