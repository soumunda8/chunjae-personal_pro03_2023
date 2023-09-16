<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
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
<body class="user">
<section class="hero is-fullheight">
    <div class="hero-body">
        <div class="container">
            <div class="columns is-centered">
                <div class="column is-5-tablet is-4-desktop is-6-widescreen">
                    <div class="buttons is-centered">
                        <a href="${path }/"><img src="${path }/resources/image/common/logo.png" width="100" height="100"></a>
                    </div>
                    <form action="${path }/user/loginPro.do" method="post" class="box">
                        <div class="field">
                            <label for="id" class="label">아이디</label>
                            <input type="text" class="input" name="id" id="id" placeholder="아이디를 입력해주세요" maxlength="12" required>
                        </div>
                        <div class="field">
                            <label for="pw" class="label">비밀번호</label>
                            <input type="password" id="pw" name="pw" placeholder="비밀번호를 입력해주세요" class="input" required>
                        </div>
                        <div class="buttons is-centered">
                            <p id="msg" class="mg_5 has-text-danger">${msg }</p>
                        </div>
                        <div class="buttons is-centered">
                            <button type="submit" class="button is-mainColor">로그인</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>