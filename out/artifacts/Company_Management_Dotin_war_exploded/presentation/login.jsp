<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>


<div id="main">
    <div id="back"></div>

    <img class="loginIMGAnimation" id="loginIMG" src="../assets/images/login.png">

    <form id="loginTable" action="/ctl/checkPassword" method="post">

        <input type="text" class="loginTableItems" id="userName" name="userName" placeholder="نام کاربری">
        <input type="password" class="loginTableItems" id="password" name="password" placeholder="پسوورد">
        <button class="loginTableItems submitAnimation" id="submit" type="submit">ورود</button>


        <div class="loginTableItemsHolder"></div>
        <div class="loginTableItemsHolder"></div>
        <img id="fingerIMGU" class="fingerIMG" src="../assets/images/userName.png">
        <img id="fingerIMGP" class="fingerIMG" src="../assets/images/password.png">


    </form>


</div>


<div></div>
<script>
    $("#fingerIMGP , #fingerIMGU ").fadeIn(2500);
    $(".loginTableItems , .loginTableItemsHolder ").fadeIn(3500);

</script>
<%@ include file="footer.jsp" %>

