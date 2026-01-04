<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@page isErrorPage="true" %>
<%
	response.setStatus(200);
%>
<%
	response.setContentType("application/json");
	JSONObject obj = new JSONObject();
	//obj.put("rstCd", "002");
	//obj.put("msg", "404 error");
	response.getWriter().write(obj.toString());
%>