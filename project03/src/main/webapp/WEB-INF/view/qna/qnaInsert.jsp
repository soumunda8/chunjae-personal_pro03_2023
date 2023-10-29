<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../layout/head.jsp" />
    <title>티스푼::문의하기</title>
    <link rel="stylesheet" href="${path }/resources/css/sub.css">
    <script type="text/javascript" src="${path }/resources/ckeditor/ckeditor.js"></script>
</head>
<body>
<jsp:include page="../layout/header.jsp" />
<section class="hero is-info">
    <div class="container is-fullhd">
        <div class="hero-body has-text-centered">
            <p class="title">문의하기</p>
            <p class="subtitle">나의 회원정보 및 투표내역을 확인해보세요.</p>
        </div>
    </div>
</section>
<div class="container is-fullhd">
    <section class="section">
        <section class="section">
            <h2 class="title has-text-centered">문의하기</h2>
            <form action="${path }/qna/add.do" method="post" class="mt-5">
                <div class="columns is-mobile">
                    <div class="column is-three-fifths is-offset-one-fifth">
                        <table class="table is-fullwidth is-bordered">
                            <tbody>
                            <tr>
                                <th>문의 제목</th>
                                <td>
                                    <input type="text" class="input" name="title" id="title" required>
                                </td>
                            </tr>
                            <tr>
                                <th>문의 제목</th>
                                <td>
                                    <textarea name="content" id="content" class="textarea" placeholder="내용 입력" rows="8" cols="100" maxlength="1400" required></textarea>
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
                    <button type="submit" class="button is-mainColor">문의하기</button>
                </div>
            </form>
        </section>
    </section>
</div>
<jsp:include page="../layout/footer.jsp" />
</body>
</html>