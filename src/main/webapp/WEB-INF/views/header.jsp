<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="context" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>

<script type="text/javascript"> 
var context = '${pageContext.request.contextPath}';
function goBack() {
    window.history.back();
}
</script>
<script>
Array.prototype.contains = function ( needle ) {
	   for (i in this) {
	       if (this[i] == needle) return true;
	   }
	   return false;
}

var default_options = {
		"sScrollY": 400,
		"sScrollX": "100%",
		"sScrollXInner": "100%",
		"bJQueryUI": true,
		"sPaginationType": "full_numbers",
							"oLanguage": {
								"sLengthMenu": "\u0412\u0456\u0434\u043E\u0431\u0440\u0430\u0436\u0435\u043D\u043E _MENU_ \u0437\u0430\u043F\u0438\u0441\u0456\u0432",
								"sSearch": "\u041F\u043E\u0448\u0443\u043A:",
								"sZeroRecords": "\u041D\u0456\u0447\u043E\u0433\u043E \u043D\u0435 \u0437\u043D\u0430\u0439\u0434\u0435\u043D\u043E.",
								"sInfo": "\u041F\u043E\u043A\u0430\u0437\u0430\u043D\u043E \u0437 _START_ \u043F\u043E _END_ \u0437 _TOTAL_ \u0437\u0430\u043F\u0438\u0441\u0456\u0432",
								"sInfoEmpty": "\u041F\u043E\u043A\u0430\u0437\u0430\u043D\u043E \u0437 0 \u043F\u043E 0 \u0437 0 \u0437\u0430\u043F\u0438\u0441\u0456\u0432",
								"sInfoFiltered": "(\u0412\u0456\u0434\u0444\u0456\u043B\u044C\u0442\u0440\u043E\u0432\u0430\u043D\u043E \u0437 _MAX_ \u0432\u0441\u0456\u0437 \u0437\u0430\u043F\u0438\u0441\u0456\u0432)",
								"oPaginate": {
								"sFirst": "\u041F\u0435\u0440\u0448\u0430",
								"sLast":"\u041E\u0441\u0442.",
								"sNext":"\u041D\u0430\u0441\u0442.",
								"sPrevious":"\u041F\u043E\u043F\u0435\u0440.",				
								} 
							},
		"bProcessing": true 
				
};
</script>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Schedule Generator</title>

		<!-- Shortcut -->
		<link href="<%=request.getContextPath()%>/resources/img/shortcut.ico" rel="shortcut icon">

		<!-- jQuery -->
		<script src="<%=request.getContextPath()%>/resources/js/jquery-2.2.4.js"></script>
		
		<!-- jQuery DataTable -->
		<script src="<%=request.getContextPath()%>/resources/js/jquery.dataTables.min.js"></script>
	
		<!-- Bootstrap Core JavaScript -->
		<script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
		
		<!-- Bootstrap DataTable JavaScript -->
		<script src="<%=request.getContextPath()%>/resources/js/dataTables.bootstrap.min.js"></script>
	
		<!-- Bootstrap Core CSS -->
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
		
		<!-- Bootstrap DataTable CSS -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/dataTables.bootstrap.min.css">

		<!-- Icons -->
		<script src="https://use.fontawesome.com/7b15c48d6d.js"></script>
	
		<!-- CSS -->
		<link href="<%=request.getContextPath()%>/resources/css/styles.css" type="text/css" rel="stylesheet">
	
		<script src="<%=request.getContextPath()%>/resources/js/main.js"></script>
		
		<script type="text/javascript">function getContextPath() { return "<%=request.getContextPath()%>";}</script>

	</head>
	
	<body class="background">
	<div class="container-fluid padding-top-10">
		<div class="row padding-top-10" style="border-bottom:2px solid #FFFFFF;">
			<div class="col-md-2">
				<a href="<c:url value="/"/>" ><span class="glyphicon glyphicon-home"> </span></a>
			</div>
			<div class="col-md-8">
				<div class="panel-center">
					<font size="24" color="white" face="Century Gothic"><spring:message code="system.name"/></font>
				</div>
			</div>
			<div class="col-md-2 padding-left-100">
				
			</div>
		</div>
	</div>