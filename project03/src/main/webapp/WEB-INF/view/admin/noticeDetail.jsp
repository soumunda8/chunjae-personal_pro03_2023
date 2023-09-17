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
    <h1 class="is-size-3 has-text-weight-semibold">공지사항 관리 - 상세보기</h1>
    <table class="table is-fullwidth is-bordered mt-5">
        <colgroup>
            <col style="width:20%;">
            <col style="width:auto;">
        </colgroup>
        <tbody>
        <tr>
            <th class="has-text-centered">조회수</th>
            <td>${notice.visited }</td>
        </tr>
        <tr>
            <th class="has-text-centered">제목</th>
            <td>${notice.title }</td>
        </tr>
        <tr>
            <th class="has-text-centered">내용</th>
            <td>${notice.content }</td>
        </tr>
        </tbody>
    </table>
    <div class="buttons is-right">
        <a href="${path }/admin/noticeEdit.do?no=${notice.seq }" class="button is-mainColor">수정</a>
        <a href="${path }/notice/delPro.do?no=${notice.seq }" class="button is-success">삭제</a>
        <a href="${path }/admin/noticeList.do" class="button is-mainColor">목록</a>
    </div>
</div>
<jsp:include page="../layout/footerAdmin.jsp" />
</body>
</html>