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
    <h1 class="is-size-3 has-text-weight-semibold">회원 관리</h1>
    <form action="${path }/admin/memberList.do" method="get" class="field has-addons has-addons-right">
        <p class="control">
            <span class="select">
                <select id="type" name="type">
                    <option value="id">아이디</option>
                    <option value="name">이름</option>
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
            <col style="width:6%;">
            <col style="width:27%;">
            <col style="width:27%;">
            <col style="width:20%;">
            <col style="width:20%;">
        </colgroup>
        <thead>
        <tr>
            <th class="has-text-centered">#</th>
            <th class="has-text-centered">아이디</th>
            <th class="has-text-centered">이름</th>
            <th class="has-text-centered">탈퇴여부</th>
            <th class="has-text-centered">비고</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="member" items="${memberList }" varStatus="status">
            <tr>
                <td class="has-text-centered">${status.count }</td>
                <td class="has-text-centered">${member.id }</td>
                <td class="has-text-centered">${member.name}</td>
                <td class="has-text-centered">
                    <c:if test="${member.useYn == true}">사용중</c:if>
                    <c:if test="${member.useYn == false}">사용중지</c:if>
                </td>
                <td>
                    <c:if test="${member.useYn == true}">
                        <div class="buttons are-small is-centered">
                            <a href="${path }/user/deletePro.do?id=${member.id }" class="button">회원탈퇴</a>
                        </div>
                    </c:if>
                    <c:if test="${member.useYn == false}">탈퇴</c:if>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty memberList }">
            <tr>
                <td colspan="5" class="has-text-centered">등록된 회원이 없습니다.</td>
            </tr>
        </c:if>
        </tbody>
    </table>
    <nav class="pagination is-rounded is-centered mb-6" role="navigation" aria-label="pagination">
        <c:if test="${curPage > page.pageCount }">
            <a href="${path }/admin/memberList.do?page=${page.blockStartNum - 1 }<c:if test="${!empty keyword }">&type=${type }&keyword=${keyword }</c:if>" class="pagination-previous">Previous</a>
        </c:if>
        <c:if test="${page.blockLastNum < page.totalPageCount }">
            <a href="${path }/admin/memberList.do?page=${page.blockLastNum + 1 }<c:if test="${!empty keyword }">&type=${type }&keyword=${keyword }</c:if>" class="pagination-next">Next page</a>
        </c:if>

        <ul class="pagination-list">
            <c:forEach var="i" begin="${page.blockStartNum }" end="${page.blockLastNum }">
                <c:choose>
                    <c:when test="${i == curPage }">
                        <li>
                            <a href="${path }/admin/memberList.do?page=${i }<c:if test="${!empty keyword }">&type=${type }&keyword=${keyword }</c:if>" class="pagination-link is-current" aria-label="Page ${i }" aria-current="page">${i }</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <a href="${path }/admin/memberList.do?page=${i }<c:if test="${!empty keyword }">&type=${type }&keyword=${keyword }</c:if>" class="pagination-link" aria-label="Page ${i }" aria-current="page">${i }</a>
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