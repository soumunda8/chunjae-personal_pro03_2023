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
        <h2 class="title has-text-centered">소통게시판 - 상세보기</h2>
        <div class="columns is-mobile">
            <div class="column is-three-fifths is-offset-one-fifth">
                <table class="table is-fullwidth is-bordered">
                    <colgroup>
                        <col style="width:20%;">
                        <col style="width:auto;">
                    </colgroup>
                    <tbody>
                    <tr>
                        <th class="has-text-centered">조회수</th>
                        <td>${freeBoard.visited }</td>
                    </tr>
                    <tr>
                        <th class="has-text-centered">제목</th>
                        <td>${freeBoard.title }</td>
                    </tr>
                    <tr>
                        <th class="has-text-centered">작성자</th>
                        <td>${freeBoard.name }</td>
                    </tr>
                    <tr>
                        <th class="has-text-centered">내용</th>
                        <td>${freeBoard.content }</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="columns is-mobile">
            <div class="column is-three-fifths is-offset-one-fifth comment_area only_comment">
                <c:if test="${not empty sid }">
                    <div class="mb-3">
                        <input type="hidden" name="par" id="par" value="${freeBoard.seq }"/>
                        <div class="comment_input" style="width:100%;">
                            <textarea class="form-control" rows="2" name="content" id="content" placeholder="댓글을 입력하세요." required style="display:inline-block;width:80%;vertical-align:top;min-height:40px;"></textarea>
                            <button class="button is-mainColor has-text-centered" type="button" onclick="commentAdd()" style="display:inline-block;width:calc(20% - 5px);vertical-align:top;height:40px;">등록하기</button>
                        </div>
                    </div>
                </c:if>

                <c:if test="${not empty commentList }">
                    <ul id="commentListArea" class="mb-0">
                        <c:forEach var="comment" items="${commentList }" varStatus="status">
                            <li id="comm${status.count }" class="mb-3">
                                <div class="comment_top" style="position:relative;">
                                    <p class="comment_writer mb-0">${status.count}. ${comment.name }<span class="comment_date ml-2">${comment.resDate }</span></p>
                                    <c:if test="${!empty sid && (sid.equals('admin') || sid.equals(comment.author))}">
                                        <button class="button is-mainColor deleteBtn" onclick="commentDelete(${comment.cno }, ${status.count })" style="position:absolute;top:-3px;right:0;height:30px;">삭제</button>
                                        <%--<button class="button p-0 modifyBtn" onclick="commentModify(${comment.cno }, ${status.count })">수정</button>--%>
                                    </c:if>
                                </div>
                                <div class="comment_con">
                                        ${comment.content }
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </c:if>
                <c:if test="${empty commentList }">
                    <div class="has-text-centered pb-3 no_comment">등록된 댓글이 없습니다.</div>
                </c:if>
            </div>
        </div>

        <c:if test="${not empty sid }">
        <script>
            function commentAdd() {
                let par = parseInt($("#par").val());
                let content = $("#content").val();
                let num = $("#commentListArea > li").length + 1;

                let params = {"par" : par, "content" : content};
                $.ajax({
                    url:"${path }/freeBoard/commentAdd.do",
                    type:"post",
                    data:params,
                    success : function(result) {
                        $("#content").val("");
                        let htmlStr = "<li id='comm" + num + "' class='mb-3'>";
                        htmlStr += "<div class='comment_top' style='position:relative;'>";
                        htmlStr += "<p class='comment_writer mb-0'>" + num + ". " + result.name + "<span class='comment_date ml-2'>" + result.resDate + "</span></p>";
                        htmlStr += "<button class='button is-mainColor deleteBtn' onclick='commentDelete(" + result.cno + ")' style='position:absolute;top:-3px;right:0;height:30px;'>삭제</button>";
                        /*htmlStr += "<button class='button p-0 modifyBtn' onclick='commentModify(" + result.cno + ")'>수정</button>";*/
                        htmlStr += "</div>";
                        htmlStr += "<div class='comment_con'>";
                        htmlStr += result.content;
                        htmlStr += "</div>";
                        htmlStr += "</li>";
                        if(num > 1) {
                            $("#commentListArea").append(htmlStr);
                        } else {
                            $(".no_comment").remove();
                            let newHtmlStr = "<ul id='commentListArea' class='mb-0'>";
                            newHtmlStr += htmlStr;
                            newHtmlStr += "</ul>";
                            $(".only_comment").append(newHtmlStr);
                        }
                    },
                });
            }

            function commentDelete(cno, cnt){
                let params = {"cno" : parseInt(cno)};
                let num = $("#commentListArea > li").length;
                $.ajax({
                    url:"${path }/freeBoard/commentRemove.do",
                    type:"post",
                    data:params,
                    success : function(result) {
                        if(result) {
                            $("#comm" + cnt).remove();
                            if(num == 1) {
                                $("#commentListArea").remove();
                                $(".only_comment").append("<div class='has-text-centered pb-3 no_comment'>등록된 댓글이 없습니다.</div>");
                            }
                        }
                    },
                });
            }

            /*function commentModify(cno, cnt){}*/
        </script>
        </c:if>

        <div class="buttons is-right">
            <a href="${path }/freeBoard/list.do" class="button is-mainColor">목록</a>
            <c:if test="${sid ne null && (sid eq freeBoard.author || sie eq 'admin')}">
                <a href="${path }/freeBoard/edit.do?no=${freeBoard.seq }" class="button is-success">수정</a>
                <a href="${path }/freeBoard/delPro.do?no=${freeBoard.seq }" class="button is-mainColor">삭제</a>
            </c:if>
        </div>
    </section>
</div>
<jsp:include page="../layout/footer.jsp" />
</body>
</html>