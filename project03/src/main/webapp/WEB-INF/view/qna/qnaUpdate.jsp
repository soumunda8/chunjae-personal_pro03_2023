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
    <script type="text/javascript" src="${path }/resources/ckeditor/ckeditor.js"></script>
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
            <form action="${path }/qna/edit.do" method="post" class="mt-5">
                <input type="hidden" name="no" id="no" value="${qna.qno }">
                <div class="columns is-mobile">
                    <div class="column is-three-fifths is-offset-one-fifth">
                        <table class="table is-fullwidth is-bordered">
                            <tbody>
                            <tr>
                                <th>문의 제목</th>
                                <td><input type="text" class="input" name="title" id="title" value="${qna.title }" required></td>
                            </tr>
                            <tr>
                                <th>문의 제목</th>
                                <td>
                                    <textarea name="content" id="content" class="textarea" placeholder="내용 입력" rows="8" cols="100" maxlength="1400" required>${qna.content }</textarea>
                                    <script>
                                        CKEDITOR.replace('content',	{filebrowserUploadUrl:'${path }/util/imageUpload.do'});
                                    </script>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="buttons is-centered">
                    <a href="${path }/admin/getQna.do?no=${qna.qno }" class="button is-mainColor">목록</a>
                    <button type="submit" class="button is-success">수정</button>
                </div>
            </form>
        </section>
    </section>
</div>
<jsp:include page="../layout/footer.jsp" />
</body>
</html>