<%@ page import="entity.User" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%

    List<User> users2 = (List<User>) request.getAttribute("users2");

%>
<%@ include file="./header.jsp" %>
<div id="main">
    <div id="back"></div>
    <div id="header">
        <img id="profilePic" src="../assets/images/profiles-avatar.png">
        <img id="profilePic2" src="../assets/images/more.png">

    </div>

    <table id="showAllClientTable">

        <th> نام</th>

        <th>ایمیل</th>
        <th>نقش</th>
        <th>مدیر مستقیم</th>

        <% for (int i = 0; i < users2.size(); i++) { %>
        <tr>
            <td><% out.print(users2.get(i).getName()); %></td>
            <td><% out.print(users2.get(i).getEmail()); %></td>
            <td><% out.print(users2.get(i).getRole().getName()); %></td>

            <td><% if (users2.get(i).getManager() == null) {
                out.print("بدون مدیر");
            } else {
                out.print(users2.get(i).getManager().getName());
            } %></td>


        </tr>
        <% } %>


    </table>


</div>
<%@ include file="./footer.jsp" %>