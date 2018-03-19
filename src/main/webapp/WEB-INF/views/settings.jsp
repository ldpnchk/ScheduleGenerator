<%@include file="header.jsp"%>
	<div class="row padding-top-10">
		<div class="col-md-1 col-md-offset-1">
			<img src="<%=request.getContextPath()%>/resources/img/back.jpg" style="width: 100px;height:auto;" onclick="goBack()">
		</div>
	    
		<div class="col-md-8">
			<div class="row">
				<div class="col-md-12">
					<div class="panel-center">
						<font size="6" color="white" face="Century Gothic"><spring:message code="settings"/>:</font>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 padding-top-20">
					<div class="panel-center">
						<a href="<c:url value="/disciplines/${wsId}"/>" > <button type="button" class="btn btn-primary btn-lg"><spring:message code="disciplines"/></button> </a>
						<a href="<c:url value="/students/${wsId}"/>" > <button type="button" class="btn btn-primary btn-lg"><spring:message code="students"/></button> </a>
						<a href="<c:url value="/lecturers/${wsId}"/>" > <button type="button" class="btn btn-primary btn-lg"><spring:message code="lecturers"/></button> </a>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-2">
		</div>
	</div>
<%@include file="footer.jsp"%>