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
        <section class="section">
            <h2 class="title has-text-centered">소통게시판</h2>
            <form action="${path }/freeBoard/list.do" method="get" class="field has-addons has-addons-right">
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
                    <col style="width:15%;">
                    <col style="width:10%;">
                </colgroup>
                <thead>
                <tr>
                    <th class="has-text-centered">#</th>
                    <th class="has-text-centered">제목</th>
                    <th class="has-text-centered">작성자</th>
                    <th class="has-text-centered">작성일자</th>
                    <th class="has-text-centered">조회수</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="freeBoard" items="${freeBoardList }" varStatus="status">
                    <tr>
                        <td class="has-text-centered">${status.count }</td>
                        <td><a href="${path }/freeBoard/detail.do?no=${freeBoard.seq }">${freeBoard.title }</a></td>
                        <td class="has-text-centered">${freeBoard.name }</td>
                        <td class="has-text-centered">
                            <fmt:parseDate value="${freeBoard.regDate }" var="regDate" pattern="yyyy-MM-dd HH:mm:ss" />
                            <fmt:formatDate value="${regDate }" pattern="yyyy-MM-dd" />
                        </td>
                        <td class="has-text-centered">${freeBoard.visited }</td>
                    </tr>
                </c:forEach>
                <c:if test="${empty freeBoardList }">
                    <tr>
                        <td colspan="5" class="has-text-centered">등록된 소통게시판이 없습니다.</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
            <nav class="pagination is-rounded is-centered mb-6" role="navigation" aria-label="pagination">
                <c:if test="${curPage > page.pageCount }">
                    <a href="${path }/freeBoard/list.do?page=${page.blockStartNum - 1 }<c:if test="${!empty keyword }">&type=${type }&keyword=${keyword }</c:if>" class="pagination-previous">Previous</a>
                </c:if>
                <c:if test="${page.blockLastNum < page.totalPageCount }">
                    <a href="${path }/freeBoard/list.do?page=${page.blockLastNum + 1 }<c:if test="${!empty keyword }">&type=${type }&keyword=${keyword }</c:if>" class="pagination-next">Next page</a>
                </c:if>

                <ul class="pagination-list">
                    <c:forEach var="i" begin="${page.blockStartNum }" end="${page.blockLastNum }">
                        <c:choose>
                            <c:when test="${i == curPage }">
                                <li>
                                    <a href="${path }/freeBoard/list.do?page=${i }<c:if test="${!empty keyword }">&type=${type }&keyword=${keyword }</c:if>" class="pagination-link is-current" aria-label="Page ${i }" aria-current="page">${i }</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="${path }/freeBoard/list.do?page=${i }<c:if test="${!empty keyword }">&type=${type }&keyword=${keyword }</c:if>" class="pagination-link" aria-label="Page ${i }" aria-current="page">${i }</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ul>
            </nav>

            <c:if test="${sid ne null}">
            <div class="buttons is-right">
                <a href="${path }/freeBoard/add.do?no=${freeBoard.seq }" class="button is-mainColor">등록</a>
            </div>
            </c:if>
        </section>
    </section>
</div>
<jsp:include page="../layout/footer.jsp" />
</body>
</html>