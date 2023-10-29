<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
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
<body>
<jsp:include page="../layout/header.jsp" />
<section class="hero is-info">
    <div class="container is-fullhd">
        <div class="hero-body has-text-centered">
            <p class="title">${title }</p>
            <p class="subtitle">${titleSub }</p>
        </div>
    </div>
</section>
<div class="container is-fullhd">
    <section class="section">
        <div class="tabs is-centered">
            <ul>
                <li class="is-active"><a>나의 정보</a></li>
                <li><a href="${path }/vote/getMyList.do">나의 투표 내역</a></li>
                <li><a href="${path }/qna/myQnaList.do">나의 문의 내역</a></li>
            </ul>
        </div>
        <section class="section">
            <h2 class="title has-text-centered">비밀번호 수정</h2>
            <form action="${path }/user/editPwPro.do" method="post" onsubmit="return editCheck(this)">
                <div class="columns is-mobile">
                    <div class="column is-three-fifths is-offset-one-fifth">
                        <table class="table is-fullwidth is-bordered">
                            <tbody>
                            <tr>
                                <th>현재 비밀번호</th>
                                <td>
                                    <input type="password" class="input" name="pw" id="pw" placeholder="현재 비밀번호를 입력하세요." required>
                                    <input type="hidden" name="pwCk" id="pwCk" value="no"/>
                                </td>
                            </tr>
                            <tr>
                                <th>새 비밀번호</th>
                                <td>
                                    <input type="password" class="input" name="pwNew" id="pwNew" placeholder="변경할 비밀번호를 입력하세요." required>
                                </td>
                            </tr>
                            <tr>
                                <th>새 비밀번호 확인</th>
                                <td>
                                    <input type="password" class="input" name="pwNewCk" id="pwNewCk" placeholder="변경할 비밀번호 확인을 입력하세요." required>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="buttons is-centered">
                    <button type="submit" class="button is-mainColor">회원 정보 수정</button>
                </div>
            </form>
        </section>
        <script>

            $(function () {
                $("#pw").focusout(function(){
                    if($("#pw").val() == ""){
                        alert("현재 비밀번호를 입력하지 않으셨습니다.");
                        $("#pw").focus();
                        return false;
                    }

                    var params = {pw : $("#pw").val()};

                    $.ajax({
                        url:"${path }/user/checkPw.do",
                        type:"post",
                        dataType:"json",
                        data:params,
                        success:function(result){
                            var pwChk = result.result;
                            if(pwChk == false){
                                $("#pwCk").val("no");
                            } else if(pwChk == true){
                                $("#pwCk").val("yes");
                            } else if(pwChk == ""){
                                alert("비밀번호가 확인되지 않았습니다. 다시 시도해주시기 바랍니다.");
                            }
                        }
                    });
                });
            });

            function editCheck(f){
                if(f.pwCk.value == "no") {
                    alert("현재 비밀번호가 다릅니다");
                    return false;
                }

                if(f.pwNew.value != f.pwNewCk.value){
                    alert("변경할 비밀번호와 변경할 비밀번호 확인이 서로 다릅니다.");
                    f.pwNew.focus();
                    return false;
                }
            }
        </script>
    </section>
</div>
<jsp:include page="../layout/footer.jsp" />
</body>
</html>