<%@include file="header.jsp"%>
<br />
<div class="container-fluid">
	<div class="row">
		<div class="col-md-4 col-md-offset-4">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="wrapper">
						<h1>Hello!</h1>
						<h3><a href="<c:url value="/registration"/>">registration</a><br /></h3>
						<h3><a href="<c:url value="/login"/>">login</a><br /></h3>
						<h3><a href="<c:url value="/logout"/>">logout</a><br /></h3>
						<h3><a href="<c:url value="/worksheets"/>">worksheets</a><br /></h3>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@include file="footer.jsp"%>