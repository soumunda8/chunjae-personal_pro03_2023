<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../include/head.jsp" />
    <title>${title }</title>
</head>
<body>
<jsp:include page="../include/headerSub.jsp" />
<div class="container is-fullhd">
    <h2>${title }</h2>
    <table class="table is-fullwidth">
        <thead>
        <tr>
            <th>NO</th>
            <th>TITLE</th>
            <th>REG DATE</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${noticeList }" var="notice" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td><a href="${path }/member/detail.do?id=${notice.seq }" title="${notice.title }">${notice.title }</a></td>
                <td>
                    <fmt:parseDate value="${notice.regDate }" var="regDate" pattern="yyyy-MM-dd HH:mm:ss" />
                    <fmt:formatDate value="${regDate }" pattern="yyyy-MM-dd" />
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="buttons">
        <a class="button is-primary">공지사항 추가</a>
    </div>
</div>
<jsp:include page="../include/footer.jsp" />
</body>
</html>