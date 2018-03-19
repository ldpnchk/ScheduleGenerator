<%@include file="header.jsp"%>
<script>
var allDisciplines = new Array();
<c:forEach items="${disciplines}" var="discipline">  
	var temp = {	id: '${discipline.id}',
					name: '${discipline.name}'
				};
	allDisciplines.push(temp);
</c:forEach>
</script>
<div class="container-fluid padding-top-20">
<div class="row">
<div class="col-md-1 col-md-offset-1">
	<img src="<%=request.getContextPath()%>/resources/img/back.jpg" style="width: 100px;height:auto;" onclick="goBack()">
</div>
<div class="col-md-8 main-panel padding-20">
	<div class="row padding-bottom-20" >
		<div class="col-md-10">
			<font size="6" ><spring:message code="students"/></font>
		</div>
		<div class="col-md-2">
			<div style="padding-left: 45px;">
				<a data-toggle="modal" data-target="#createStudentModal"><button type="button" class="btn btn-success btn-sm"><spring:message code="add"/></button></a>
			</div>
		</div>
	</div>
	<table id="studentsTable" class="table table-striped table-hover table-bordered" class="width-100p">
		<thead class="panel-center">
			<tr style="background-color: #BDBDBD;;">
				<td><spring:message code="name"/></td>
				<td><spring:message code="year"/></td>
				<td><spring:message code="specialty"/></td>
				<td style="width: 10%;" data-orderable="false"><spring:message code="details"/></td>
				<td style="width: 10%;" data-orderable="false"><spring:message code="delete"/></td>
			</tr>
		</thead>
		<tbody class="panel-center">
			<c:forEach items="${students}" var="student">
				<tr>
					<td>${student.name}</td>
					<td>${student.year}</td>
					<td>${student.specialty.name}</td>
					<td><a class="btn btn-xs btn-primary"
								onclick="showDisciplines('${student.id}');"> <i
									class="glyphicon glyphicon-eye-open" aria-hidden="true"></i>
									<spring:message code="discipline.manage"/></a>
					</td>
					<td>								
						<a class="btn btn-xs btn-danger" href="<c:url value="/student/delete/${student.id}/" />">
							<i class="glyphicon glyphicon-trash" aria-hidden="true"></i> <spring:message code="delete"/>
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- addStudentModal -->
	<div class="modal fade" id="createStudentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
		    <div class="modal-content">
		    	<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title" id="myModalLabel"><spring:message code="add.Group"/></h4>
		      	</div>
		     	<form>
		        	<div class="modal-body">
						<div class="form-group">
							 <div class="form-group">
							  <label for="year"><spring:message code="year"/></label>
							  <select class="form-control" id="year">
							    <option value="1">1</option>
							    <option value="2">2</option>
							    <option value="3">3</option>
							    <option value="4">4</option>
							    <option value="5">5</option>
							    <option value="6">6</option>
							  </select>
							</div>
							<div class="form-group">
							  <label for="subGroup"><spring:message code="subGroup"/></label>
							  <select class="form-control" id="subGroup">
							    <option value="1">1</option>
							    <option value="2">2</option>
							    <option value="3">3</option>
							    <option value="4">4</option>
							    <option value="5">5</option>
							    <option value="6">6</option>
							    <option value="7">7</option>
							    <option value="8">8</option>
							  </select>
							</div>
							
							<label for="specialty"><spring:message code="specialty"/></label>
							<select class="form-control" id="specialty">
								<c:forEach items="${specs}" var="spec">
									<option value="${spec.id}">${spec.name}</option>
								</c:forEach>
							</select>
							
							<div id="emptyNameError" class="padding-left-10" hidden="hidden">
		          				<font size="2" color="red"><spring:message code="noemptyfield"/></font>
		          			</div>
						</div>
		          	</div>
		          	
		      		<div class="modal-footer">
		       			<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cancel"/></button>
		        		<button type="button" class="btn btn-success" onclick="addStudent(${wsId});"><spring:message code="create"/></button>
		      		</div>
		    	</form>
			</div>
		</div>
	</div>
</div>
</div>
</div>

<!-- DISCIPLINES -->
<div class="modal fade" id="disciplinesModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel"><spring:message code="disciplines"/>:</h4>
			</div>
			<div class="modal-body">
				<div id="disciplinesEnrolled"></div>
				<hr/>
				<div id="disciplinesNotEnrolled"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="close"/></button>
			</div>
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
$('#studentsTable').DataTable(default_options);

function addStudent(wsId){

	
	var year = $('#year').val();
	var specialtyId = $('#specialty').val();
	var names = $('#specialty option:selected').html().split(" ");
	var name = "";
	var subGroup = $('#subGroup').val();
	names.forEach(function(item, i, arr) {
		  name = name+item.charAt(0);
	});
	name = name.toUpperCase();
	name = name +"-"+year+" "+subGroup;
	
	
		$('#createStudentModal').modal('hide');

		$.ajax({
			url: getContextPath() + "/student/create/" + wsId,
	    	data: {
	        	name: name,
	        	year: year,
	        	specialtyId: specialtyId
	    	},
	    	type: "POST",
	    	dataType : "text",
			timeout: 60000
		})
		.done(function(id) {
		    location.reload();
		})
		.fail(function() {
			$('#networkErrorModal').modal('show');
		});
	
}

function showDisciplines(studentId){

	$.ajax({
		cache: false,
		timeout: 60000, 
		url: getContextPath() + "/discipline/getAllByStudent/" + studentId,
		method: 'POST',       
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	})
	.done(function(tools) {
		$('#disciplinesEnrolled').empty();
		$('#disciplinesNotEnrolled').empty();
		var result = '';
		var all = '';
		var enrolledIds = new Array();
		result += '<div class="row">';
		tools.forEach(function(item) {
			enrolledIds.push(item.id);
			result += '<div class="col-md-4">';
			result += '<label class="checkbox-inline">';
			result += '<input type="checkbox" onchange="update('+item.id+', '+studentId+');" id="'+item.id+'" checked>';
			result += item.name;
			result += ' </label>';
			result += '</div>';
		});
		result += '</div>';
		all += '<div class="row">';
		allDisciplines.forEach(function(entry) {
			if(!enrolledIds.contains(entry.id)){
				all += '<div class="col-md-4">';
				all += '<label class="checkbox-inline">';
				all += '<input type="checkbox" onchange="update('+entry.id+', '+studentId+');" id="'+entry.id+'">';
				all += entry.name;
				all += ' </label>';
				all += '</div>';
			}
		});
		all += '</div>';
		$('#disciplinesEnrolled').append(result);
		$('#disciplinesNotEnrolled').append(all);
		$('#disciplinesModal').modal('show');
	})
	.fail(function() {
		$('#networkErrorModal').modal('show');
	});
}
function update(disciplineId, studentId){
	if (document.getElementById(disciplineId).checked){									//alert("checked");
		$.ajax({
			url: getContextPath() + "/discipline/addStudent/"+disciplineId+"/"+studentId,
	    	type: "GET",
	    	dataType : "text",
			timeout: 60000
		})
		.done(function(id) {

		})
		.fail(function() {
			$('#networkErrorModal').modal('show');
		});
	}else{																				//alert("UNchecked");
		$.ajax({
			url: getContextPath() + "/discipline/removeStudent/"+disciplineId+"/"+studentId,
	    	type: "GET",
	    	dataType : "text",
			timeout: 60000
		})
		.done(function(id) {
		})
		.fail(function() {
			$('#networkErrorModal').modal('show');
		});
	}
}
</script>
<%@include file="footer.jsp"%>