<%@ include file="./header.jsp" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%


    String user = (String) request.getAttribute("userName");
    String pass = (String) request.getAttribute("password");
%>

<form action="/ctl/checkPassword">
    <a style="width: 100%;text-align: center" href="">
        <button type="submit"
                style="line-height:35px;width:50%;left:25%;position:absolute;text-align: center;font-size: 20px;margin-top: 100px;color: black">
            ثبت با موفقیت انجام شد برای ادامه کلیک کنید

        </button>
    </a>

    <input name="userName" value="<% out.print(user); %>" hidden>
    <input name="password" value="<% out.print(pass); %>" hidden>


</form>
<%@ include file="./footer.jsp" %>