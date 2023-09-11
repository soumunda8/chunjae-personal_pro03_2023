<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath }" />
<header id="hd" class="header">
    <div class="container is-fullhd">
        <nav class="navbar" role="navigation" aria-label="main navigation">
            <div class="navbar-brand">
                <a class="navbar-item" href="https://bulma.io">
                    <img src="https://bulma.io/images/bulma-logo.png" width="112" height="28">
                </a>

                <a role="button" class="navbar-burger" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample">
                    <span aria-hidden="true"></span>
                    <span aria-hidden="true"></span>
                    <span aria-hidden="true"></span>
                </a>
            </div>

            <div id="navbarBasicExample" class="navbar-menu">
                <div class="navbar-start">
                    <a class="navbar-item">
                        Home
                    </a>

                    <a class="navbar-item">
                        Documentation
                    </a>

                    <div class="navbar-item has-dropdown is-hoverable">
                        <a class="navbar-link">
                            커뮤니티
                        </a>

                        <div class="navbar-dropdown">
                            <a href="${path }/notice/list.do" class="navbar-item">
                                게시판
                            </a>
                            <a class="navbar-item">
                                Contact
                            </a>
                            <hr class="navbar-divider">
                            <a class="navbar-item">
                                Report an issue
                            </a>
                        </div>
                    </div>
                </div>

                <div class="navbar-end">
                    <div class="navbar-item">
                        <div class="buttons">
                            <c:if test="${empty sid}">
                                <a href="${path }/member/term.do" class="button is-primary">
                                    <strong>Sign up</strong>
                                </a>
                                <a href="${path }/member/login.do" class="button is-light">
                                    Log in
                                </a>
                            </c:if>
                            <c:if test="${!empty sid}">
                                <a href="${path }/member/mypage.do" class="button is-primary">
                                    mypage
                                </a>
                                <a href="${path }/member/logout.do" class="button is-light">
                                    <strong>Log out</strong>
                                </a>
                                <c:if test="${sid.equals('admin')}">
                                    <a href="${path }/member/list.do" class="button is-light">
                                        MemberList
                                    </a>
                                </c:if>
                            </c:if>
                            <a href="${path }/member/list.do" class="button is-light">
                                MemberList
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </nav>
    </div>
</header>
<section class="hero is-info">
    <div class="container is-fullhd">
        <div class="hero-body">
            <p class="title">
                Info hero
            </p>
            <p class="subtitle">
                Info subtitle
            </p>
        </div>
    </div>
</section>
