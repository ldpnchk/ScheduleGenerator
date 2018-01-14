<%@include file="header.jsp"%>
	<div class="row padding-top-10">
		<div class="col-md-1 col-md-offset-1">
	    	<a href="<c:url value="/" />">
	  			<img src="<%=request.getContextPath()%>/resources/img/back.jpg"
					style="width: 100%">
			</a>
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
						<a href="<c:url value="/disciplines"/>" > <button type="button" class="btn btn-primary btn-lg"><spring:message code="disciplines"/></button> </a>
						<a href="<c:url value="/students"/>" > <button type="button" class="btn btn-primary btn-lg"><spring:message code="students"/></button> </a>
						<a href="<c:url value="/lecturers"/>" > <button type="button" class="btn btn-primary btn-lg"><spring:message code="lecturers"/></button> </a>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-2">
		</div>
	</div>
<%@include file="footer.jsp"%>