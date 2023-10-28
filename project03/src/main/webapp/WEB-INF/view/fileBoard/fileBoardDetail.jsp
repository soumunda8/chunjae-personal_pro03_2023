<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../layout/head.jsp" />
    <title>티스푼::학습자료실</title>
    <link rel="stylesheet" href="${path }/resources/css/sub.css">
</head>
<body>
<jsp:include page="../layout/header.jsp" />
<section class="hero is-info">
    <div class="container is-fullhd">
        <div class="hero-body has-text-centered">
            <p class="title">학습자료실</p>
            <p class="subtitle">티스푼에 관한 학습자료를 확인해보세요.</p>
        </div>
    </div>
</section>
<div class="container is-fullhd">
    <section class="section">
        <h2 class="title has-text-centered">학습자료실 - 상세보기</h2>
        <div class="columns is-mobile">
            <div class="column is-three-fifths is-offset-one-fifth">
                <table class="table is-fullwidth is-bordered">
                    <colgroup>
                        <col style="width:20%;">
                        <col style="width:auto;">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th class="has-text-centered">조회수</th>
                        <td>${fileBoard.visited }</td>
                    </tr>
                    <tr>
                        <th class="has-text-centered">제목</th>
                        <td>${fileBoard.title }</td>
                    </tr>
                    <tr>
                        <th class="has-text-centered">내용</th>
                        <td>${fileBoard.content }</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="buttons is-right">
            <a href="${path }/fileBoard/list.do" class="button is-mainColor">목록</a>
        </div>
    </section>
</div>
<jsp:include page="../layout/footer.jsp" />
</body>
</html>