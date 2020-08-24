<%@ page import="entity.Email" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%

    List<Email> emails = (List<Email>) request.getAttribute("emails");
    String userName = (String) request.getAttribute("userName");

%>
<%@ include file="./header.jsp" %>
<div id="main">
    <div id="back"></div>
    <div id="header">
        <img id="profilePic" src="../assets/images/profiles-avatar.png">
        <img id="profilePic2" src="../assets/images/more.png">

    </div>

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
            <td><% out.print(email.getCreationTIme()); %></td>
            <td><% out.print(email.getContext()); %></td>
            <td><% out.print(email.getImportance().getName()); %></td>

            <td><a href="<% out.print(email.getAttachments().get(0).getLocation()); %>"><%
                out.print(email.getAttachments().get(0).getLocation()); %></a></td>


        </tr>
        <%
                }
            }
        %>


    </table>


</div>
<%@ include file="./footer.jsp" %>