<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../layout/head.jsp" />
    <title>관리자 페이지</title>
    <link rel="stylesheet" href="${path }/resources/css/subAdmin.css">
</head>
<body>
<jsp:include page="../layout/headerAdmin.jsp" />
<div class="column">
    <h1 class="is-size-3 has-text-weight-semibold">문의하기 관리</h1>
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
                <td><a href="${path }/admin/getQna.do?no=${qna.qno }">${qna.title }</a></td>
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
<jsp:include page="../layout/footerAdmin.jsp" />
</body>
</html>