<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="./layout/head.jsp" />
    <title>티스푼</title>
    <link rel="stylesheet" href="${path }/resources/css/main.css">
</head>
<body>
<jsp:include page="./layout/header.jsp" />
    <section class="hero is-large is-info">
        <div class="container is-fullhd">
            <div class="hero-body">
                <p class="title">${title }</p>
                <p class="subtitle">${titleSub }</p>
            </div>
        </div>
    </section>
    <div class="container is-fullhd my-5">
        <div class="tile is-ancestor">
            <div class="tile is-vertical is-12">
                <div class="tile">
                    <div class="tile is-parent is-vertical">
                        <article class="tile is-child box">
                            <p class="title">공지사항</p>
                            <c:forEach var="notice" items="${noticeList }" varStatus="status">
                                <p class="subtitle"><a href="${path }/notice/detail.do?no=${notice.seq }">${notice.title }</a></p>
                            </c:forEach>
                        </article>
                    </div>
                    <div class="tile is-parent is-vertical">
                        <article class="tile is-child box">
                            <p class="title">학습자료실</p>
                            <c:forEach var="fileBoard" items="${fileBoardList }" varStatus="status">
                                <p class="subtitle"><a href="${path }/fileBoard/detail.do?no=${fileBoard.seq }">${fileBoard.title }</a></p>
                            </c:forEach>
                        </article>
                    </div>
                    <div class="tile is-parent is-vertical">
                        <article class="tile is-child box">
                            <p class="title">소통게시판</p>
                            <c:forEach var="freeBoard" items="${freeBoardList }" varStatus="status">
                                <p class="subtitle"><a href="${path }/freeBoard/detail.do?no=${freeBoard.seq }">${freeBoard.title }</a></p>
                            </c:forEach>
                        </article>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="./layout/footer.jsp" />
</body>
</html>