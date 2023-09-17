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
    <h1 class="is-size-3 has-text-weight-semibold">투표 관리</h1>
    <form action="${path }/admin/voteList.do" method="get" class="field has-addons has-addons-right">
        <p class="control">
            <span class="select">
                <select id="type" name="type">
                    <option value="title">제목</option>
                </select>
            </span>
        </p>
        <p class="control">
            <input class="input" type="text" id="keyword" name="keyword" placeholder="검색어를 입력하세요" value="${keyword }">
        </p>
        <p class="control">
            <input type="submit" class="button is-mainColor" value="검색" />
        </p>
    </form>
    <table class="table is-fullwidth table is-striped mt-5">
        <colgroup>
            <col style="width:5%;">
            <col style="width:auto;">
            <col style="width:25%;">
            <col style="width:10%;">
            <col style="width:10%;">
        </colgroup>
        <thead>
        <tr>
            <th class="has-text-centered">#</th>
            <th class="has-text-centered">제목</th>
            <th class="has-text-centered">투표일자</th>
            <th class="has-text-centered">총 투표수</th>
            <th class="has-text-centered">진행여부</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="vote" items="${voteList }" varStatus="status">
            <tr>
                <td class="has-text-centered">${status.count }</td>
                <td class="has-text-centered"><a href="${path }/admin/getVote.do?vno=${vote.vno }">${vote.title }</a></td>
                <td class="has-text-centered">${vote.startDate } ~ ${vote.finishDate}</td>
                <td class="has-text-centered"></td>
                <td class="has-text-centered">
                    <c:if test="${vote.stateYn }">진행 중</c:if>
                    <c:if test="${!vote.stateYn }">종료</c:if>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty voteList }">
            <tr>
                <td colspan="5" class="has-text-centered">등록된 투표가 없습니다.</td>
            </tr>
        </c:if>
        </tbody>
    </table>
    <div class="buttons is-right">
        <a href="${path }/admin/voteAdd.do" class="button is-mainColor">등록</a>
    </div>
    <nav class="pagination is-rounded is-centered mb-6" role="navigation" aria-label="pagination">
        <c:if test="${curPage > page.pageCount }">
            <a href="${path }/admin/voteList.do?page=${page.blockStartNum - 1 }<c:if test="${!empty keyword }">&type=${type }&keyword=${keyword }</c:if>" class="pagination-previous">Previous</a>
        </c:if>
        <c:if test="${page.blockLastNum < page.totalPageCount }">
            <a href="${path }/admin/voteList.do?page=${page.blockLastNum + 1 }<c:if test="${!empty keyword }">&type=${type }&keyword=${keyword }</c:if>" class="pagination-next">Next page</a>
        </c:if>

        <ul class="pagination-list">
            <c:forEach var="i" begin="${page.blockStartNum }" end="${page.blockLastNum }">
                <c:choose>
                    <c:when test="${i == curPage }">
                        <li>
                            <a href="${path }/admin/voteList.do?page=${i }<c:if test="${!empty keyword }">&type=${type }&keyword=${keyword }</c:if>" class="pagination-link is-current" aria-label="Page ${i }" aria-current="page">${i }</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <a href="${path }/admin/voteList.do?page=${i }<c:if test="${!empty keyword }">&type=${type }&keyword=${keyword }</c:if>" class="pagination-link" aria-label="Page ${i }" aria-current="page">${i }</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </ul>
    </nav>
</div>
<jsp:include page="../layout/footerAdmin.jsp" />
</body>
</html>