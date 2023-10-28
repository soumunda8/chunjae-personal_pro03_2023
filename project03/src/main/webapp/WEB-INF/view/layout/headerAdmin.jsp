<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath }" />
<nav class="navbar">
    <div class="container">
        <div class="navbar-brand">
            <a class="navbar-item brand-text" href="${path }/"><img src="${path }/resources/image/common/logo.png" width="28" height="28"></a>
            <div class="navbar-burger burger" data-target="navMenu">
                <span></span>
                <span></span>
                <span></span>
            </div>
        </div>
        <div id="navMenu" class="navbar-menu">
            <div class="navbar-end"><a class="navbar-item is-right" href="${path }/user/logout.do">로그아웃</a></div>
        </div>
    </div>
</nav>
<div class="container">
    <div class="columns">
        <div class="column is-3 ">
            <aside class="menu is-hidden-mobile">
                <p class="menu-label">관리</p>
                <ul class="menu-list">
                    <li><a href="${path }/admin/memberList.do" <c:if test="${adminNum == 0 }">class="is-active"</c:if>>회원 관리</a></li>
                    <li><a href="${path }/admin/noticeList.do" <c:if test="${adminNum == 1 }">class="is-active"</c:if>>공지사항 관리</a></li>
                    <li><a href="${path }/admin/fileBoardList.do" <c:if test="${adminNum == 2 }">class="is-active"</c:if>>학습자료실 관리</a></li>
                    <li><a href="#" <c:if test="${adminNum == 3 }">class="is-active"</c:if>>문의하기 관리</a></li>
                    <li><a href="${path }/admin/voteList.do" <c:if test="${adminNum == 4 }">class="is-active"</c:if>>투표 관리</a></li>
                </ul>
            </aside>
        </div>
        <div class="column is-9">