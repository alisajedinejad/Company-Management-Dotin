<%@ page import="entity.Email" %>
<%@ page import="entity.User" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%

    List<Email> emails = (List<Email>) request.getAttribute("emails");
    List<User> senders = (List<User>) request.getAttribute("senders");
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

        <% for (int i = 0; i < emails.size(); i++) {


        %>
        <tr>
            <td><% out.print(senders.get(i).getEmail()); %></td>
            <td><% out.print(userName); %></td>
            <td><% out.print(emails.get(i).getCreatedate()); %></td>
            <td><% out.print(emails.get(i).getContext()); %></td>
            <td><% out.print(emails.get(i).getImportance().getName()); %></td>

            <td><a href="<% out.print(emails.get(i).getAttachments().get(0).getLocation()); %>"><%
                out.print(emails.get(i).getAttachments().get(0).getLocation()); %></a></td>


        </tr>
        <%
            }

        %>


    </table>


</div>
<%@ include file="./footer.jsp" %>