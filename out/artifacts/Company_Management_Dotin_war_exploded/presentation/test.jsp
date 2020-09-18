<%@ page import="entity.File" %>
<%@ include file="./header.jsp" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>



<%

    File file = (File) request.getAttribute("file");



%>

<textarea>
    <%
    out.print(file.getBlol());



    %>
</textarea>




<%@ include file="./footer.jsp" %>
<script>




</script>