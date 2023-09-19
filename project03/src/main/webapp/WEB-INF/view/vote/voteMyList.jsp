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
                <li><a href="${path }/user/myPage.do">나의 정보</a></li>
                <li class="is-active"><a>나의 투표 내역</a></li>
                <li><a href="${path }/attend/getMyAttend.do">나의 출석</a></li>
            </ul>
        </div>
        <section class="section">
            <h2 class="title has-text-centered">${title }</h2>
            <div class="columns is-mobile">
                <div class="column is-three-fifths is-offset-one-fifth">
                    <table class="table is-fullwidth is-bordered">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>제목</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="vote" items="${voteList }" varStatus="status">
                            <tr>
                                <td>${status.count }</td>
                                <td><a href="${path }/vote/get.do?vno=${vote.vno }"><c:if test="${!vote.stateYn }">[완료] </c:if>${vote.title }</a></td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty voteList }">
                            <tr>
                                <td colspan="2">진행한 투표가 없습니다.</td>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </section>
    </section>
</div>
<jsp:include page="../layout/footer.jsp" />
</body>
</html>