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
    <h1 class="is-size-3 has-text-weight-semibold">문의하기 관리 - 상세보기</h1>
    <table class="table is-fullwidth is-bordered mt-5">
        <colgroup>
            <col style="width:20%;">
            <col style="width:auto;">
        </colgroup>
        <tbody>
        <tr>
            <th class="has-text-centered">답변여부</th>
            <td>
                <c:if test="${qna.answerYn }">답변완료</c:if>
                <c:if test="${!qna.answerYn }">답변대기</c:if>
            </td>
        </tr>
        <tr>
            <th class="has-text-centered">문의 제목</th>
            <td>${qna.title }</td>
        </tr>
        <tr>
            <th class="has-text-centered">문의 내용</th>
            <td>${qna.content }</td>
        </tr>
        </tbody>
    </table>
    <c:if test="${qna.answerYn }">
        <table class="table is-fullwidth is-bordered mt-5">
            <colgroup>
                <col style="width:20%;">
                <col style="width:auto;">
            </colgroup>
            <tbody>
            <tr>
                <th class="has-text-centered">답변 내용</th>
                <td>${qna.answer }</td>
            </tr>
            </tbody>
        </table>
    </c:if>
    <div class="buttons is-right">
        <a href="${path }/admin/qnaList.do" class="button is-mainColor">목록</a>
        <a href="${path }/qna/delPro.do?no=${qna.qno }" class="button is-success">삭제</a>
        <c:if test="${!qna.answerYn }"><a href="${path }/admin/addAnswer.do?no=${qna.qno }" class="button is-mainColor">답변하기</a></c:if>
    </div>
</div>
<jsp:include page="../layout/footerAdmin.jsp" />
</body>
</html>