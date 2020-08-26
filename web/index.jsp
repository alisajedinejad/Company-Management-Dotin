<%@ include file="presentation/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>


<div id="main">
    <div id="back"></div>

    <img class="loginIMGAnimation" id="loginIMG" src="../assets/images/login.png">

    <form id="loginTable" action="/ctl/logToSystem" method="post">


        <button class="hover loginView" style="" type="submit">ورود به سیستم مدیریت همکاران</button>

    </form>


    <form id="loginTable2" action="/ctl/createSample" method="post">


        <button class="hover loginView"  style="" type="submit">ساخت تیبل های نمونه</button>

    </form>


</div>


<div></div>
<script>
    $("#fingerIMGP , #fingerIMGU ").fadeIn(2500);
    $(".loginTableItems , .loginTableItemsHolder ").fadeIn(3500);

</script>
<%@ include file="presentation/footer.jsp" %>

