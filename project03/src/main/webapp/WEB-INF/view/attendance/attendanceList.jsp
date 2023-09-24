<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../layout/head.jsp" />
    <title>티스푼::출석체크</title>
    <link rel="stylesheet" href="${path }/resources/css/sub.css">
</head>
<body>
<jsp:include page="../layout/header.jsp" />
<section class="hero is-info">
    <div class="container is-fullhd">
        <div class="hero-body has-text-centered">
            <p class="title">출석체크</p>
            <p class="subtitle">매일 매일 확인해보세요.</p>
        </div>
    </div>
</section>
<div class="container is-fullhd">
    <section class="section">
        <div class="tabs is-centered">
            <ul>
                <li><a href="${path }/user/myPage.do">나의 정보</a></li>
                <li><a href="${path }/vote/getMyList.do">나의 투표 내역</a></li>
                <li class="is-active"><a>나의 출석</a></li>
            </ul>
        </div>
        <section class="section">
            <h2 class="title has-text-centered">출석체크</h2>

            <div class="block">
                <h4 class="is-size-5 has-text-weight-bold has-text-centered">${currentYear}년 ${currentMonth}월</h4>
            </div>

            <div class="block">
                <c:choose>
                    <c:when test="${monthFirstDateWeek.equals('MONDAY') }">
                        <c:set var="start" value="0" />
                    </c:when>
                    <c:when test="${monthFirstDateWeek.equals('TUESDAY') }">
                        <c:set var="start" value="1" />
                    </c:when>
                    <c:when test="${monthFirstDateWeek.equals('WEDNESDAY') }">
                        <c:set var="start" value="2" />
                    </c:when>
                    <c:when test="${monthFirstDateWeek.equals('THURSDAY') }">
                        <c:set var="start" value="3" />
                    </c:when>
                    <c:when test="${monthFirstDateWeek.equals('FRIDAY') }">
                        <c:set var="start" value="4" />
                    </c:when>
                    <c:when test="${monthFirstDateWeek.equals('SATURDAY') }">
                        <c:set var="start" value="5" />
                    </c:when>
                    <c:when test="${monthFirstDateWeek.equals('SUNDAY') }">
                        <c:set var="start" value="6" />
                    </c:when>
                </c:choose>
                <div class="columns is-centered has-text-centered is-mobile is-multiline">
                    <c:if test="${start > 0}">
                        <c:forEach var="startI" begin="0" end="${start }">
                            <div class="column m-1 notification is-light is-light" style="height:80px;"></div>
                        </c:forEach>
                    </c:if>
                    <c:forEach var="day" begin="1" end="${dayList }">
                    <c:if test="${(start + day) % 7 == 0}">
                </div>
                <div class="columns is-centered has-text-centered is-mobile is-multiline">
                    </c:if>
                        <c:if test="${!empty attendanceList }">
                            <c:set var="loop_flag" value="false" />
                            <c:if test="${not loop_flag }">
                                <c:forEach var="attend" items="${attendanceList }">
                                    <c:if test="${day == attend.nowDay }">
                                        <c:set var="loop_flag" value="true" />
                                    </c:if>
                                </c:forEach>
                            </c:if>
                            <div class="column m-1 notification is-light<c:if test="${!loop_flag }"> is-info</c:if><c:if test="${loop_flag }"> is-danger</c:if>" style="height:80px;">[${day }]
                                <c:if test="${loop_flag }"><br />출석 완료</c:if>
                            </div>
                        </c:if>
                        <c:if test="${empty attendanceList }">
                            <div class="column m-1 notification is-info is-light" style="height:80px;">[${day }]</div>
                        </c:if>
                    </c:forEach>
                    <c:if test="${(dayList + start + 1) % 7 != 0}">
                        <c:choose>
                            <c:when test="${(start + dayList + 1) % 7 == 1 }">
                                <c:set var="endNum" value="6" />
                            </c:when>
                            <c:when test="${(start + dayList + 1) % 7 == 2 }">
                                <c:set var="endNum" value="5" />
                            </c:when>
                            <c:when test="${(start + dayList + 1) % 7 == 3 }">
                                <c:set var="endNum" value="4" />
                            </c:when>
                            <c:when test="${(start + dayList + 1) % 7 == 4 }">
                                <c:set var="endNum" value="3" />
                            </c:when>
                            <c:when test="${(start + dayList + 1) % 7 == 5 }">
                                <c:set var="endNum" value="2" />
                            </c:when>
                            <c:when test="${(start + dayList + 1) % 7 == 6 }">
                                <c:set var="endNum" value="1" />
                            </c:when>
                        </c:choose>
                        <c:forEach var="endI" begin="1" end="${endNum }">
                            <div class="column m-1 notification is-light is-light" style="height:80px;"></div>
                        </c:forEach>
                    </c:if>
                </div>
            </div>

            <c:if test="${!pass }">
                <div class="buttons is-centered">
                    <a href="${path }/attend/add.do" class="button is-mainColor">출석 체크</a>
                </div>
            </c:if>
        </section>
    </section>
</div>
<jsp:include page="../layout/footer.jsp" />
</body>
</html>