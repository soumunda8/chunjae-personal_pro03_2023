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
    <script type="text/javascript" src="${path }/resources/ckeditor/ckeditor.js"></script>
</head>
<body>
<jsp:include page="../layout/headerAdmin.jsp" />
<div class="column">
    <h1 class="is-size-3 has-text-weight-semibold">문의하기 관리 - 답변하기</h1>
    <form action="${path }/admin/addAnswer.do" method="post" class="mt-5">
        <input type="hidden" name="no" id="no" value="${qna.qno }">
        <table class="table is-fullwidth is-bordered mt-5">
            <colgroup>
                <col style="width:20%;">
                <col style="width:auto;">
            </colgroup>
            <tbody>
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
        <table class="table is-fullwidth is-bordered mt-5">
            <colgroup>
                <col style="width:20%;">
                <col style="width:auto;">
            </colgroup>
            <tbody>
            <tr>
                <th class="has-text-centered">답변 내용</th>
                <td>
                    <textarea name="answer" id="answer" class="textarea" placeholder="내용 입력" rows="8" cols="100" maxlength="1400" required></textarea>
                    <script>
                        CKEDITOR.replace('answer',	{filebrowserUploadUrl:'${path }/util/imageUpload.do'});
                    </script>
                </td>
            </tr>
            </tbody>
        </table>
        <div class="buttons is-right">
            <button type="submit" class="button is-mainColor">답변하기</button>
            <a href="${path }/admin/noticeList.do" class="button is-success">목록</a>
        </div>
    </form>
</div>
<jsp:include page="../layout/footerAdmin.jsp" />
</body>
</html>