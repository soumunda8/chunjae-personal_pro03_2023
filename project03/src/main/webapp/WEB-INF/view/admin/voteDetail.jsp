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
    <h1 class="is-size-3 has-text-weight-semibold">투표 관리 - 상세보기</h1>
    <table class="table is-fullwidth is-bordered mt-5">
        <colgroup>
            <col style="width:20%;">
            <col style="width:auto;">
        </colgroup>
        <tbody>
        <tr>
            <th class="has-text-centered">제목</th>
            <td>${vote.title }</td>
        </tr>
        <tr>
            <th class="has-text-centered">내용</th>
            <td>${vote.content }</td>
        </tr>
        <tr>
            <th class="has-text-centered">시작일</th>
            <td>${vote.startDate }</td>
        </tr>
        <tr>
            <th class="has-text-centered">종료일</th>
            <td>${vote.finishDate }</td>
        </tr>
        <tr>
            <th class="has-text-centered">완료시 등록될 포인트</th>
            <td>${vote.addPt }</td>
        </tr>
        </tbody>
    </table>

    <c:if test="${!vote.useYn }">
        <div class="block answer_area">
            <h4 class="is-size-5 has-text-weight-bold p-2">투표 질문지 내역</h4>
            <hr class="m-0" />
            <ul class="is-full mt-5">
                <c:forEach var="voteAnswer" items="${voteAnswerList }" varStatus="status">
                    <form action="${path }/vote/editAnswer.do" method="post" class="is-full">
                        <li class="columns is-mobile">
                            <div class="column is-two-thirds">
                                <p class="text${status.count }">[${status.count }] ${voteAnswer.title }</p>
                                <input type="hidden" name="title" id="voteTitle${status.count }" value="${voteAnswer.title }" />
                                <input type="hidden" name="lno" id="lno" value="${voteAnswer.lno }">
                                <input type="hidden" name="vno" value="${vote.vno }">
                            </div>
                            <div class="column">
                                <div class="buttons is-right">
                                    <button type="button" id="editBtn" class="button is-small is-success is-outlined" onclick="editClick(${status.count })">수정하기</button>
                                    <button type="submit" id="editSubmit" class="button is-small is-success is-outlined">수정하기</button>
                                    <a href="${path }/vote/delAnswerPro.do?vno=${vote.vno }&lno=${voteAnswer.lno }" class="button is-small is-danger is-outlined">삭제하기</a>
                                </div>
                            </div>
                        </li>
                    </form>
                </c:forEach>
                <c:if test="${empty voteAnswerList }">
                    등록된 투표질문지가 없습니다.
                </c:if>
            </ul>
            <script>
                function editClick(cnt) {
                    $("#editSubmit").show();
                    $("#voteTitle" + cnt).prop("type", "text");
                    $("#editBtn").hide();
                    $(".text" + cnt).hide();
                }
            </script>
        </div>

        <form action="${path }/vote/addAnswer.do" method="post" class="mt-5">
            <div class="columns is-mobile my-5">
                <div class="column is-four-fifths">
                    <input type="hidden" value="${vote.vno}" name="vno" id="vno" />
                    <input type="text" name="title" id="title" class="input" placeholder="투표 질문지 내용 입력" maxlength="98" required>
                </div>
                <div class="column">
                    <div class="buttons is-right">
                        <button type="submit" class="button is-mainColor">등록하기</button>
                    </div>
                </div>
            </div>
        </form>
    </c:if>
    <c:if test="${vote.useYn }">
        <div class="columns is-multiline is-mobile is-four-fifths is-offset-1 mt-5">
            <c:forEach items="${voteCountList }" var="voteAnswer" varStatus="status">
                <div class="column is-half">
                    <div class="vote_li box<c:if test="${cnt != 0 && sid.equals('admin') && getMaxLno.lno == voteAnswer.lno }"> check</c:if>">
                        <p class="is-size-5 has-text-weight-semibold">${voteAnswer.title }</p>
                        <c:if test="${cnt !=0 }">
                            <c:set var="lnoTotal" value="${(voteAnswer.cnt / cnt) * 100 }" />
                            <p><span>투표수 : ${voteAnswer.cnt }</span> | <span>투표율 : <fmt:formatNumber value="${lnoTotal }" type="pattern" pattern="0.00" /> %</span></p>
                        </c:if>
                        <c:if test="${cnt == 0 }">
                            <p><span>투표수 : 0</span> | <span>투표율 : 0 %</span></p>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>

    <div class="buttons is-right">
        <a href="${path }/admin/voteList.do" class="button is-mainColor">목록</a>
        <c:if test="${!vote.stateYn }">
            <a href="${path }/vote/delPro.do?vno=${vote.vno }" class="button is-success">삭제</a>
        </c:if>
        <c:if test="${vote.stateYn }">
            <a href="${path }/vote/delPro.do?vno=${vote.vno }" class="button is-success">삭제</a>
            <c:if test="${vote.useYn }">
                <a href="${path }/admin/voteEdit.do?vno=${vote.vno }" class="button is-mainColor">수정</a>
            </c:if>
            <c:if test="${!vote.useYn }">
                <button type="button" class="button is-mainColor" onclick="useVote('${path }', '${vote.vno }')">등록</button>
            </c:if>
            <c:if test="${cnt != 0 }">
                <a href="${path }/vote/voteAnswerEdit.do?vno=${vote.vno }" class="button is-success">투표 종료</a>
            </c:if>
        </c:if>
    </div>
    <script>
        function useVote(url, vno) {
            var notice = confirm("이 투표를 등록하시면 투표 질문지 등록 및 수정이 불가합니다. 등록을 진행하시겠습니까?");
            if(notice) {
                location.href = url + "/vote/editUse.do?vno=" + vno;
            }
        }
    </script>
</div>
<jsp:include page="../layout/footerAdmin.jsp" />
</body>
</html>