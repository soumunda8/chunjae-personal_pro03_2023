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
                <li><a>나의 투표 내역</a></li>
            </ul>
        </div>
        <section class="section">
            <h2 class="title has-text-centered">회원 정보 수정</h2>
            <form action="${path }/user/editPro.do" method="post">
                <div class="columns is-mobile">
                    <div class="column is-three-fifths is-offset-one-fifth">
                        <table class="table is-fullwidth is-bordered">
                            <tbody>
                            <tr>
                                <th>아이디</th>
                                <td>
                                    ${member.id }
                                    <input type="hidden" class="input" name="id" id="id" value="${member.id }" required>
                                </td>
                            </tr>
                            <tr>
                                <th>비밀번호</th>
                                <td>
                                    <a href="${path }/user/editPw.do?id=${member.id }" class="button is-success">비밀번호 변경</a>
                                </td>
                            </tr>
                            <tr>
                                <th>이름</th>
                                <td>${member.name }</td>
                            </tr>
                            <tr>
                                <th>이메일</th>
                                <td>
                                    <input type="email" class="input" name="email" id="email" value="${member.email }" required>
                                </td>
                            </tr>
                            <tr>
                                <th>전화번호</th>
                                <td>
                                    <input type="text" class="input" name="tel" id="tel" value="${member.tel }" required>
                                </td>
                            </tr>
                            <tr>
                                <th>생일</th>
                                <td>${member.birth }</td>
                            </tr>
                            <tr>
                                <th>주소</th>
                                <td>
                                    <label for="addr1" class="label">주소</label>
                                    <input type="text" name="addr1" id="addr1" value="${member.addr1 }" maxlength="150" class="input mg_5">
                                    <input type="text" name="addr2" id="addr2" value="${member.addr2 }" maxlength="150" class="input mg_5">
                                    <input type="text" name="postcode" id="postcode" value="${member.postcode }" maxlength="150" class="input mg_5">
                                    <input type="button" class="button is-success" value="주소 찾기" onclick="findAddr()">
                                </td>
                            </tr>
                            <tr>
                                <th>포인트</th>
                                <td>${member.pt }</td>
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
    </section>
</div>
<jsp:include page="../layout/footer.jsp" />
</body>
</html>