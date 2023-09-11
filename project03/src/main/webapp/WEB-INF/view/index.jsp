<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="./include/head.jsp" />
    <title>${title }</title>
</head>
<body>
<jsp:include page="include/headerMain.jsp" />
    <div class="container is-fullhd">
        <h2>${title }</h2>
        <div class="contents">
            이름 : ${name }
            <hr>
            현재 : ${today }
        </div>
    </div>
</div>
<jsp:include page="./include/footer.jsp" />
</body>
</html>