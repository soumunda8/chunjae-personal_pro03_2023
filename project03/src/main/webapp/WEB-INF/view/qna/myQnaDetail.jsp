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
                    <table class="table is-fullwidth is-bordered">
                        <tbody>
                        <tr>
                            <th>문의 제목</th>
                            <td>${qna.title }</td>
                        </tr>
                        <tr>
                            <th>문의 제목</th>
                            <td>${qna.content }</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <c:if test="${qna.answerYn }">
                <div class="columns is-mobile pt-2">
                    <div class="column is-three-fifths is-offset-one-fifth">
                        <table class="table is-fullwidth is-bordered">
                            <tbody>
                            <tr>
                                <th>문의 답변</th>
                                <td>${qna.answer }</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:if>
            <div class="buttons is-centered">
                <a href="${path }/qna/myQnaList.do" class="button is-mainColor">목록</a>
                <c:if test="${!qna.answerYn }">
                    <a href="${path }/qna/edit.do?no=${qna.qno }" class="button is-success">수정</a>
                    <a href="${path }/qna/delPro.do?no=${qna.qno }" class="button is-mainColor">삭제</a>
                </c:if>
            </div>
        </section>
    </section>
</div>
<jsp:include page="../layout/footer.jsp" />
</body>
</html>