<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath }" />
<header id="hd" class="header">
    <div class="container is-fullhd">
        <nav class="navbar" role="navigation" aria-label="main navigation">
            <div class="navbar-brand">
                <a class="navbar-item" href="${path }/"><img src="${path }/resources/image/common/logo.png" width="28" height="28"></a>
                <a role="button" class="navbar-burger" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample">
                    <span aria-hidden="true"></span>
                    <span aria-hidden="true"></span>
                    <span aria-hidden="true"></span>
                </a>
            </div>
            <div id="navbarBasicExample" class="navbar-menu">
                <div class="navbar-start">
                    <div class="navbar-item has-dropdown is-hoverable">
                        <a class="navbar-link">이벤트</a>
                        <div class="navbar-dropdown">
                            <a href="${path }/vote/list.do" class="navbar-item">티이슈</a>
                            <a href="#" class="navbar-item">출석체크</a>
                        </div>
                    </div>
                    <div class="navbar-item has-dropdown is-hoverable">
                        <a class="navbar-link">커뮤니티</a>
                        <div class="navbar-dropdown">
                            <a href="${path }/notice/list.do" class="navbar-item">공지사항</a>
                            <a href="#" class="navbar-item">자유게시판</a>
                            <a href="#" class="navbar-item">소통게시판</a>
                            <a href="#" class="navbar-item">학습자료실</a>
                            <hr class="navbar-divider">
                            <a href="#" class="navbar-item">문의하기</a>
                        </div>
                    </div>
                </div>

                <div class="navbar-end">
                    <div class="navbar-item">
                        <div class="buttons">
                            <c:if test="${empty sid}">
                                <a href="${path }/user/joinAgree.do" class="button is-link is-light">회원가입</a>
                                <a href="${path }/user/login.do" class="button is-info is-light">로그인</a>
                            </c:if>
                            <c:if test="${!empty sid}">
                                <a href="${path }/user/logout.do" class="button is-link is-light">로그아웃</a>
                                <c:if test="${sid.equals('admin')}">
                                    <a href="${path }/admin/memberList.do" class="button is-info is-light">관리자페이지</a>
                                </c:if>
                                <c:if test="${!sid.equals('admin')}">
                                    <a href="${path }/user/myPage.do" class="button is-info is-light">마이페이지</a>
                                </c:if>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </nav>
    </div>
</header>
