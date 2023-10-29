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
                <li><a href="${path }/vote/getMyList.do">나의 투표 내역</a></li>
                <li class="is-active"><a>나의 문의 내역</a></li>
            </ul>
        </div>
        <section class="section">
            <h2 class="title has-text-centered">${title }</h2>
            <div class="columns is-mobile">
                <div class="column is-three-fifths is-offset-one-fifth">
                    <table class="table is-fullwidth table is-striped mt-5">
                        <colgroup>
                            <col style="width:5%;">
                            <col style="width:auto;">
                            <col style="width:20%;">
                            <col style="width:20%;">
                        </colgroup>
                        <thead>
                        <tr>
                            <th class="has-text-centered">#</th>
                            <th class="has-text-centered">제목</th>
                            <th class="has-text-centered">작성일자</th>
                            <th class="has-text-centered">답변여부</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="qna" items="${qnaList }" varStatus="status">
                            <tr>
                                <td class="has-text-centered">${status.count }</td>
                                <td><a href="${path }/qna/detail.do?no=${qna.qno }">${qna.title }</a></td>
                                <td class="has-text-centered">
                                    <fmt:parseDate value="${qna.resDate }" var="resDate" pattern="yyyy-MM-dd HH:mm:ss" />
                                    <fmt:formatDate value="${resDate }" pattern="yyyy-MM-dd" />
                                </td>
                                <td class="has-text-centered">
                                    <c:if test="${qna.answerYn }">답변완료</c:if>
                                    <c:if test="${!qna.answerYn }">답변대기</c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty qnaList }">
                            <tr>
                                <td colspan="4" class="has-text-centered">문의한 문의사항이 없습니다.</td>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="buttons is-centered">
                <a href="${path }/qna/add.do" class="button is-mainColor">문의하기</a>
            </div>
        </section>
    </section>
</div>
<jsp:include page="../layout/footer.jsp" />
</body>
</html>