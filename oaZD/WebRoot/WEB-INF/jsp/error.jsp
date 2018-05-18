<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<html>
<head>
	<title>错误页面</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
    <h1>错误页面</h1> 
	<s:property/><!--  异常被放在了栈顶  所以我们只要输出栈顶元素即可   s:property/> 就是输出栈顶元素 -->
	<s:debug></s:debug>
</body>
</html>
