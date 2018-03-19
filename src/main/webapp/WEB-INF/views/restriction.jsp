<%@include file="header.jsp"%>
<div class="container-fluid padding-top-20">
	<div class="row">
	
		<div class="col-md-1 col-md-offset-1">
			<img src="<%=request.getContextPath()%>/resources/img/back.jpg" style="width: 100px;height:auto;" onclick="goBack()">
		</div>
	
		<div class="col-md-8 main-panel padding-bottom-20 padding-left-20 padding-right-20">
			<h1>
				<p><spring:message code="restrictions"/></p>
			</h1>
			
			<div style="border-top: thick solid #A4A4A4; border-width: 1px; margin-top: 10px;">
			</div>
			<div class="row padding-20 panel-center">
				<button type="button" class="btn btn-success "
					data-toggle="modal" data-target="#createRestrictionModal"
					id="createRestrictionButton" >
					<i class="fa fa-plus-circle" aria-hidden="true"></i>
					<spring:message code="add"/>
				</button>
			</div>
			<table id="restrictionsTable" class="table table-striped table-hover table-bordered" class="width-100p">
				<thead class="panel-center">
					<tr style="background-color: #BDBDBD;">
						<td><spring:message code="discipline"/></td>
						<td><spring:message code="lecturer"/></td>
						<td><spring:message code="classroom"/></td>
						<td><spring:message code="day"/></td>
						<td><spring:message code="period"/></td>
						<td data-orderable="false"></td>
					</tr>
				</thead>
				<tbody class="panel-center">
					<c:forEach items="${restrictions}" var="restriction">
						<c:if test="${restriction.selection}">
							<tr style="background-color: #90EE90;">
						</c:if>
						<c:if test="${!restriction.selection}">
							<tr style="background-color: #F08080;">
						</c:if>
						
							<c:if test="${empty restriction.discipline}">
    							<td></td>
							</c:if>
							<c:if test="${not empty restriction.discipline}">
    							<td>${restriction.discipline.name}</td>
							</c:if>
							
							<c:if test="${empty restriction.lecturer}">
    							<td></td>
							</c:if>
							<c:if test="${not empty restriction.lecturer}">
    							<td>${restriction.lecturer.name}</td>
							</c:if>
							
							<c:if test="${empty restriction.classroom}">
    							<td></td>
							</c:if>
							<c:if test="${not empty restriction.classroom}">
    							<td>${restriction.classroom.building}-${restriction.classroom.number}</td>
							</c:if>
							
							<c:if test="${empty restriction.daytime}">
    							<td></td>
							</c:if>
							<c:if test="${not empty restriction.daytime}">
    							<td>${restriction.daytime.name}</td>
							</c:if>
							
							<c:if test="${empty restriction.periodtime}">
    							<td></td>
							</c:if>
							<c:if test="${not empty restriction.periodtime}">
    							<td>${restriction.periodtime.number}</td>
							</c:if>
							
							<td>
								<a class="btn btn-xs btn-danger" href="<c:url value="/restriction/delete/${restriction.id}/" />">
									<i class="glyphicon glyphicon-trash" aria-hidden="true"></i> <spring:message code="delete"/>
								</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>

<!-- createRestrictionModal -->
<div class="modal fade" id="createRestrictionModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog modal-sm" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel"><spring:message code="restriction.create"/> </h4>
			</div>
			<form>
				<div class="modal-body">
				
					<div class="form-group">
						<label for="name"><spring:message code="discipline"/>:</label>
						<select id="select-disc" class="form-control" data-size="5">
							<option value=""></option>
							<c:forEach items="${disciplines}" var="discipline">
								<option value="${discipline.id}">${discipline.name}</option>
							</c:forEach>
						</select>
					</div>
					
					<div class="form-group">
						<label for="name"><spring:message code="lecturer"/>:</label>
						<select id="select-lect" class="form-control" data-size="5">
							<option value=""></option>
							<c:forEach items="${lecturers}" var="lecturer">
								<option value="${lecturer.id}">${lecturer.name}</option>
							</c:forEach>
						</select>
					</div>
					
					<div class="form-group">
						<label for="name"><spring:message code="classroom"/>:</label>
						<select id="select-class" class="form-control" data-size="5">
							<option value=""></option>
							<c:forEach items="${classrooms}" var="classroom">
								<option value="${classroom.id}">${classroom.building}-${classroom.number}</option>
							</c:forEach>
						</select>
					</div>
					
					<div class="form-group">
						<label for="name"><spring:message code="day"/>:</label>
						<select id="select-day" class="form-control" data-size="5">
							<option value=""></option>
							<c:forEach items="${daytimes}" var="daytime">
								<option value="${daytime.id}">${daytime.name}</option>
							</c:forEach>
						</select>
					</div>
					
					<div class="form-group">
						<label for="name"><spring:message code="period"/>:</label>
						<select id="select-period" class="form-control" data-size="5">
							<option value=""></option>
							<c:forEach items="${periodtimes}" var="periodtime">
								<option value="${periodtime.id}">${periodtime.number}</option>
							</c:forEach>
						</select>
					</div>
					
					<div class="form-group">
						<div class="row">
							<div class="col-md-6">
								<input type="radio" value="1" name="optradio"> <spring:message code="wanted"/> 
							</div>  
							<div class="col-md-6">
								<input type="radio" value="0" name="optradio" checked="checked"> <spring:message code="unwanted"/>
							</div>
						</div>
					</div>

				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cancel"/></button>
					<button type="button" class="btn btn-success" onclick="createRestriction(${wsId});"><spring:message code="create"/></button>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- loadingModal -->
<div class="modal fade" data-backdrop="static" data-keyboard="false" id="loadingModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
    	<div class="text-center">
			<img src="<%=request.getContextPath()%>/resources/img/loading.gif" width="50px">
    	</div>
  	</div>
</div>

<!-- networkErrorModal -->
<div class="modal fade" data-backdrop="static" data-keyboard="false" id="networkErrorModal" tabindex="-1" role="dialog" >
  	<div class="modal-dialog" role="document">
    	<div class="text-center errorMessageBody">
    		<div class="alert alert-danger">
  				<strong><spring:message code="networkerror"/></strong><spring:message code="networkerror.extra"/>
			</div>
    	</div>
  	</div>
</div>

<script>

$('#restrictionsTable').DataTable(default_options);

function createRestriction(wsId){
	
	var disc_id;
	if (document.getElementById("select-disc").value == ''){
		disc_id = 0;
	}
	else{
		disc_id = document.getElementById("select-disc").value;
	}
	
	var lect_id;
	if (document.getElementById("select-lect").value == ''){
		lect_id = 0;
	}
	else{
		lect_id = document.getElementById("select-lect").value;
	}
	
	var class_id;
	if (document.getElementById("select-class").value == ''){
		class_id = 0;
	}
	else{
		class_id = document.getElementById("select-class").value;
	}
	
	var day_id;
	if (document.getElementById("select-day").value == ''){
		day_id = 0;
	}
	else{
		day_id = document.getElementById("select-day").value;
	}
	
	var period_id;
	if (document.getElementById("select-period").value == ''){
		period_id = 0;
	}
	else{
		period_id = document.getElementById("select-period").value;
	}
	
	var selection = $('input[name=optradio]:checked').val();
	
	$.ajax({
	    url: getContextPath() + "/restriction/create/" + wsId,
	    data: {
	    	disc_id: disc_id, 
	    	lect_id: lect_id,
	    	class_id: class_id,
	    	day_id: day_id,
	    	period_id: period_id,
	    	selection: selection
	    },
	    type: "POST",
	    dataType : "text",
		timeout: 60000
	})
	.done(function(message) {
	    location.reload();
	})
	.fail(function() {
		$('#networkErrorModal').modal('show');
	});
	
}

</script>

<%@include file="footer.jsp"%>
