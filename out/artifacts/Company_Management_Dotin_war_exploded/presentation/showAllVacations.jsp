<%@ page import="entity.DayOffRequest" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%

    List<DayOffRequest> dayOffRequests = (List<DayOffRequest>) request.getAttribute("dayOffRequests");
    String userName = (String) request.getAttribute("userName");
    String password = (String) request.getAttribute("password");
%>
<%@ include file="./header.jsp" %>

<div id="main">
    <div id="back"></div>
    <div id="header">

    </div>
    <form action="/ctl/checkPassword">
        <input name="userName" value="<% out.print(userName); %>" hidden>
        <input name="password" value="<% out.print(password); %>" hidden>
        <button class="defaultBTN" style="">بازگشت</button>

    </form>

    <table id="showAllVacationsTable">
        <th>تاریخ شروع مرخصی</th>
        <%--<th>ساعت شروع مرخصی</th>--%>
        <th>تاریخ پایان مرخصی</th>
        <%--<th>ساعت پایان مرخصی</th>--%>
        <th>وضعیت</th>
        <% for (int i = 0; i < dayOffRequests.size(); i++) { %>
        <tr>
            <td><% out.print(dayOffRequests.get(i).getStart()); %></td>
            <td><% out.print(dayOffRequests.get(i).getEnd()); %></td>
            <td><% out.print(dayOffRequests.get(i).getStatus().getName()); %></td>

        </tr>

        <% } %>
    </table>


</div>
<%@ include file="./footer.jsp" %>
