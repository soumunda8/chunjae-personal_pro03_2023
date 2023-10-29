<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../layout/head.jsp" />
    <title>티스푼::소통게시판</title>
    <link rel="stylesheet" href="${path }/resources/css/sub.css">
    <script type="text/javascript" src="${path }/resources/ckeditor/ckeditor.js"></script>
</head>
<body>
<jsp:include page="../layout/header.jsp" />
<section class="hero is-info">
    <div class="container is-fullhd">
        <div class="hero-body has-text-centered">
            <p class="title">소통게시판</p>
            <p class="subtitle">티스푼에 관해 서로 많은 정보를 나누세요.</p>
        </div>
    </div>
</section>
<div class="container is-fullhd">
    <section class="section">
        <h2 class="title has-text-centered">소통게시판 - 수정하기</h2>
        <form action="${path }/freeBoard/edit.do" method="post" class="mt-5">
            <input type="hidden" name="no" id="no" value="${freeBoard.seq }" >
            <div class="columns is-mobile">
                <div class="column is-three-fifths is-offset-one-fifth">
                    <table class="table is-fullwidth is-bordered">
                        <colgroup>
                            <col style="width:20%;">
                            <col style="width:auto;">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th class="has-text-centered">제목</th>
                            <td>
                                <input type="text" name="title" id="title" class="input" placeholder="제목 입력" value="${freeBoard.title }" maxlength="98" required>
                            </td>
                        </tr>
                        <tr>
                            <th class="has-text-centered">내용</th>
                            <td>
                                <textarea name="content" id="content" class="textarea" placeholder="내용 입력" rows="8" cols="100" maxlength="1400" required>${freeBoard.content }</textarea>
                                <script>
                                    CKEDITOR.replace('content',	{filebrowserUploadUrl:'${path }/util/imageUpload.do'});
                                </script>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="buttons is-right">
                <button type="submit" class="button is-mainColor">수정</button>
                <a href="${path }/freeBoard/list.do" class="button is-success">목록</a>
            </div>
        </form>
    </section>
</div>
<jsp:include page="../layout/footer.jsp" />
</body>
</html>