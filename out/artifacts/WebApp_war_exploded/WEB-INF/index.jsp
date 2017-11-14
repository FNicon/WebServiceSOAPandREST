<%--
  Created by IntelliJ IDEA.
  User: ireneedriadr
  Date: 11/3/17
  Time: 5:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/component/header.html"%>
<%@ include file="/component/nav.html"%>
<%
    String site = new String("login.jsp");
    response.setStatus(response.SC_MOVED_TEMPORARILY);
    response.setHeader("Location", site);
%>


<%@include file="/component/footer.html" %>
