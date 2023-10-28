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
    <h1 class="is-size-3 has-text-weight-semibold">학습자료실 관리 - 수정하기</h1>
    <form action="${path }/fileBoard/edit.do" method="post" class="mt-5">
        <table class="table is-fullwidth is-bordered">
            <colgroup>
                <col style="width:20%;">
                <col style="width:auto;">
            </colgroup>
            <tbody>
            <tr>
                <th class="has-text-centered">제목</th>
                <td>
                    <input type="hidden" name="no" id="no" value="${fileBoard.seq }" >
                    <input type="text" name="title" id="title" class="input" placeholder="제목 입력" value="${fileBoard.title }" maxlength="98" required>
                </td>
            </tr>
            <tr>
                <th class="has-text-centered">내용</th>
                <td>
                    <textarea name="content" id="content" class="textarea" placeholder="내용 입력" rows="8" cols="100" maxlength="1400" required>${fileBoard.content }</textarea>
                    <script>
                        CKEDITOR.replace('content',	{filebrowserUploadUrl:'${path }/util/imageUpload.do'});
                    </script>
                </td>
            </tr>
            </tbody>
        </table>
        <div class="buttons is-right">
            <button type="submit" class="button is-mainColor">수정</button>
            <a href="${path }/admin/fileBoardList.do" class="button is-success">목록</a>
        </div>
    </form>
</div>
<jsp:include page="../layout/footerAdmin.jsp" />
</body>
</html>