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
                    <form action="${path }/user/joinPro.do" method="post" class="box" onsubmit="return joinCheck(this)">
                        <div class="field">
                            <label for="id" class="label">아이디</label>
                            <input type="text" class="input" name="id" id="id" placeholder="아이디를 입력해주세요" pattern="^[a-z0-9]{5,12}" maxlength="12" required>
                            <input type="hidden" name="idCk" id="idCk" value="no"/>
                            <p id="msg" style="padding-left:0.5rem">아이디 중복 체크를 하지 않으셨습니다.</p>
                        </div>
                        <div class="field">
                            <label for="pw" class="label">비밀번호</label>
                            <input type="password" id="pw" name="pw" placeholder="비밀번호를 입력해주세요" class="input mg_5" maxlength="12" pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,12}$" required>
                            <input type="password" id="pwCk" name="pwCk" placeholder="비밀번호를 한번 더 입력해주세요" class="input" maxlength="12" pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,12}$" required>
                        </div>
                        <div class="field">
                            <label for="name" class="label">이름</label>
                            <input type="text" id="name" name="name" placeholder="이름을 입력하세요." class="input" required>
                        </div>
                        <div class="field">
                            <label for="email" class="label">이메일</label>
                            <input type="email" id="email" name="email" placeholder="이메일을 입력하세요." class="input" required>
                        </div>
                        <div class="field">
                            <label for="tel" class="label">전화번호</label>
                            <input type="text" id="tel" name="tel" placeholder="전화번호를 입력해주세요" class="input" required>
                        </div>
                        <div class="field">
                            <label for="addr1" class="label">주소</label>
                            <input type="text" name="addr1" id="addr1" value="" placeholder="기본주소를 입력해주세요" maxlength="150" class="input mg_5">
                            <input type="text" name="addr2" id="addr2" value="" placeholder="상세주소를 입력해주세요" maxlength="150" class="input mg_5">
                            <input type="text" name="postcode" id="postcode" value="" placeholder="우편번호를 입력해주세요" maxlength="150" class="input mg_5">
                            <div class="buttons is-centered">
                                <input type="button" class="button is-success" value="주소 찾기" onclick="findAddr()">
                            </div>
                        </div>
                        <div class="field">
                            <label for="birth" class="label">생년월일</label>
                            <input type="date" name="birth" id="birth" class="input" required>
                        </div>
                        <div class="buttons is-centered">
                            <button type="submit" class="button is-mainColor">회원가입</button>
                        </div>
                    </form>
                    <script>
                        $(function (){
                            $("#id").keyup(function(){
                                $("#idck").val("no");
                                if($(this).val() != ""){
                                    $("#msg").html("<strong>아이디 입력중입니다.</strong>");
                                } else {
                                    $("#msg").html("아이디 중복 체크를 하지 않으셨습니다.");
                                }
                            });

                            $("#id").focusout(function(){
                                if($("#id").val() == ""){
                                    alert("아이디를 입력하지 않으셨습니다.");
                                    $("#id").focus();
                                    return false;
                                }
                                var params = {id : $("#id").val()};

                                $.ajax({
                                    url:"${path }/user/checkId.do",
                                    type:"post",
                                    dataType:"json",
                                    data:params,
                                    success:function(result){
                                        var idChk = result.result;
                                        if(idChk == false){
                                            $("#idCk").val("no");
                                            $("#msg").html("<strong style='color:red'>기존에 사용되고 있는 아이디 입니다. 다시 입력하시기 바랍니다.</strong>");
                                            $("#id").focus();
                                        } else if(idChk == true){	//사용 가능한 아이디
                                            $("#idCk").val("yes");
                                            $("#msg").html("<strong style='color:blue'>사용가능한 아이디 입니다.</strong>");
                                        } else if(idCk == ""){
                                            $("#msg").html("<strong>아이디가 확인되지 않았습니다. 다시 시도해주시기 바랍니다.</strong>");
                                        }
                                    }
                                });
                            });
                        });

                        function joinCheck(f){
                            if(f.pw.value != f.pwCk.value){
                                alert("비밀번호와 비밀번호 확인이 서로 다릅니다.");
                                f.pw.focus();
                                return false;
                            }
                            if(f.idCk.value != "yes"){
                                alert("아이디 중복 체크를 하지 않으셨습니다.");
                                return false;
                            }
                        }

                        function findAddr() {
                            new daum.Postcode({
                                oncomplete: function(data) {
                                    console.log(data);
                                    var roadAddr = data.roadAddress;
                                    var jibunAddr = data.jibunAddress;
                                    document.getElementById("postcode").value = data.zonecode;
                                    if(roadAddr !== '') {
                                        document.getElementById("addr1").value = roadAddr;
                                    } else if(jibunAddr !== ''){
                                        document.getElementById("addr1").value = jibunAddr;
                                    }
                                    document.getElementById("addr2").focus();
                                }
                            }).open();
                        }
                    </script>
                    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>