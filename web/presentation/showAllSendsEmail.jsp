<%@ page import="entity.Email" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%

    List<Email> emails = (List<Email>) request.getAttribute("emails");
    String userName = (String) request.getAttribute("userName");
    String password = (String) request.getAttribute("password");


%>
<%@ include file="./header.jsp" %>
<div id="main">
    <div id="back"></div>
    <div id="header">
        <img id="profilePic" src="../assets/images/profiles-avatar.png">
        <img id="profilePic2" src="../assets/images/more.png">

    </div>

    <form action="/ctl/checkPassword">
        <input name="userName" value="<% out.print(userName); %>" hidden>
        <input name="password" value="<% out.print(password); %>" hidden>

        <button class="defaultBTN" style="">بازگشت</button>

    </form>


    <table id="showAllClientTable">

        <th> فرستاده شده توسط</th>

        <th>دریافت کننده ی ایمیل</th>
        <th>زمان فرستادن ایمیل</th>
        <th>متن ایمیل</th>
        <th>درجه اهمیت</th>
        <th>لینک فایل ضمیمه</th>


        <% for (Email email : emails) {

            for (int i = 0; i < email.getRecivers().size(); i++) {

        %>


        <tr>
            <td><% out.print(userName); %></td>
            <td><% out.print(email.getRecivers().get(i).getName()); %></td>
            <td><% out.print(email.getCreatedate()); %></td>
            <td><% out.print(email.getContext()); %></td>
            <td><% out.print(email.getImportance().getName()); %></td>

            <%if (email.getAttachments().size() != 0) { %>
            <td>
                <%  if(!email.getAttachments().get(0).getLocation().equals("بدونه ضمیمه")){   %>
                <a href="<% out.print(email.getAttachments().get(0).getLocation()); %>">
                <% out.print(email.getAttachments().get(0).getLocation()); %></a>
            <% }else{%>

                <p>
                    <% out.print(email.getAttachments().get(0).getLocation()); %></p>

                <% }%>

            </td>
            <% } else { %>
            <td><a href="#" onclick="alert('ضمیمه ای یافت نشد','fail')">
                بدون ضمیمه
            </a></td>
            <% } %>


        </tr>
        <%
                }
            }
        %>

    </table>


</div>
<%@ include file="./footer.jsp" %>