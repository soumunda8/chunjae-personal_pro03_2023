<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../layout/head.jsp" />
    <title>티스푼::출석체크</title>
    <link rel="stylesheet" href="${path }/resources/css/sub.css">
</head>
<body>
<jsp:include page="../layout/header.jsp" />
<section class="hero is-info">
    <div class="container is-fullhd">
        <div class="hero-body has-text-centered">
            <p class="title">출석체크</p>
            <p class="subtitle">매일 매일 확인해보세요.</p>
        </div>
    </div>
</section>
<div class="container is-fullhd">
    <section class="section">
        <div class="tabs is-centered">
            <ul>
                <li><a href="${path }/user/myPage.do">나의 정보</a></li>
                <li><a href="${path }/vote/getMyList.do">나의 투표 내역</a></li>
                <li class="is-active"><a>나의 출석</a></li>
            </ul>
        </div>
        <section class="section">
            <h2 class="title has-text-centered">출석체크</h2>
            <c:forEach var="day" items="${day }" varStatus="status">
                <div style="border:1px solid pink;box-sizing:border-box;display:inline-block;">
                    [${status.count }]
                </div>
            </c:forEach>
        </section>
    </section>
</div>
<jsp:include page="../layout/footer.jsp" />
</body>
</html>