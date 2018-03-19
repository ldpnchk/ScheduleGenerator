<%@include file="header.jsp"%>
<script>
var allLecturers = new Array();
<c:forEach items="${allLecturers}" var="lecturer">  
	var temp = {	id: '${lecturer.id}',
					name: '${lecturer.name}'
				};
	allLecturers.push(temp);
</c:forEach>
var disc = 0;
</script>
<div class="container-fluid padding-top-20">
	<div class="row">
		<div class="col-md-1 col-md-offset-1">
			<img src="<%=request.getContextPath()%>/resources/img/back.jpg" style="width: 100px;height:auto;" onclick="goBack()">
		</div>
		<div class="col-md-8 main-panel padding-20">
			<div class="row padding-bottom-20">
				<div class="col-md-10">
					<font size="6"><spring:message code="discipline"/></font>
				</div>
				<div class="col-md-2">
					<div style="padding-left: 45px;">
						<a data-toggle="modal" data-target="#addDisciplineModal"><button
								type="button" class="btn btn-success btn-sm"><spring:message code="add"/></button></a>
					</div>
				</div>
			</div>
			<table id="disciplinesTable"
				class="table table-striped table-hover table-bordered"
				class="width-100p">
				<thead class="panel-center">
					<tr style="background-color: #BDBDBD;">
						<td><spring:message code="discipline.name"/></td>
						<td style="width: 10%;" data-orderable="false"><spring:message code="lecturers"/></td>
						<td style="width: 10%;" data-orderable="false"><spring:message code="students.add"/></td>
						<td style="width: 10%;" data-orderable="false"><spring:message code="students.enrolled"/></td>
						<td style="width: 10%;" data-orderable="false"><spring:message code="delete"/></td>
					</tr>
				</thead>
				<tbody class="panel-center">
					<c:forEach items="${disciplines}" var="discipline">
						<tr>
							<td>${discipline.name}</td>
							<td><a class="btn btn-xs btn-primary"
								onclick="showLecturers('${discipline.id}');"> <i
									class="glyphicon glyphicon-eye-open" aria-hidden="true"></i>
									<spring:message code="lecturers"/>
							</a></td>
							<td>
								<button type="button" class="btn btn-xs btn-primary"
									onclick="disc = ${discipline.id} ;" data-toggle="modal"
									data-target="#students">
									<i class="glyphicon glyphicon-eye-open" aria-hidden="true"></i>
									<spring:message code="students.add"/>
								</button>
							</td>
							<td>
								<button type="button" class="btn btn-xs btn-primary"
									onclick="showEnrolledStudents(${discipline.id});">
									<i class="glyphicon glyphicon-eye-open" aria-hidden="true"></i>
									<spring:message code="students.enrolled"/>
								</button>
							</td>
							<td><a class="btn btn-xs btn-danger"
								href="<c:url value="/discipline/delete/${discipline.id}/${wsId}" />">
									<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
									<spring:message code="delete"/>
							</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<!-- addDisciplineModal -->
			<div class="modal fade" id="addDisciplineModal" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel"><spring:message code="discipline.add"/></h4>
						</div>
						<form>
							<div class="modal-body">
								<div class="form-group">
									<label for="name"><spring:message code="discipline.name"/>:</label> <input type="text"
										maxlength="255" class="form-control" name="name"
										id="newDisciplineName" placeholder="Enter new discipline name" />

									<div id="emptyNameError" class="padding-left-10"
										hidden="hidden">
										<font size="2" color="red"><spring:message code="noemptyfield"/></font>
									</div>
								</div>
							</div>

							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal"><spring:message code="cancel"/></button>
								<button type="button" class="btn btn-success"
									onclick="addDiscipline(${wsId});"><spring:message code="create"/></button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- LECTURERS -->
<div class="modal fade" id="lecturers" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel"><spring:message code="lecturers"/>:</h4>
			</div>
			<div class="modal-body">
				<div id="enrolledLecturers"></div>
				<hr />
				<div id="notEnrolledLecturers"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="close"/></button>
			</div>
		</div>
	</div>
</div>

<!-- STUDENTS -->
<div class="modal fade" id="students" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel"><spring:message code="students"/>:</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-4">
						<label for="year"><spring:message code="year"/>:</label> 
						<select class="form-control" id="year">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
						</select> 
					</div>
					<div class="col-md-4">
						<label for="specialty">Select specialty:</label> 
						<select class="form-control" id="specialty">
							<c:forEach items="${specs}" var="spec">
								<option value="${spec.id}">${spec.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-md-4">
						<div class="padding-top-25">
							<button type="button" class="btn btn-primary" onclick="showStudents();"><spring:message code="show"/></button>
						</div>
					</div>
				</div>
				<hr />
				<div class="form-group">
					<div id="studentsDiv"></div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="close"/></button>
			</div>
		</div>
	</div>
</div>
<!-- #enrolledStudents -->
<div class="modal fade" id="enrolledStudents" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel"><spring:message code="students"/>:</h4>
			</div>
			<div class="modal-body">
				<div id="enrolledStudentsDiv"></div>
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
$('#disciplinesTable').DataTable(default_options);

function addDiscipline(wsId){
	
	var name = $('#newDisciplineName').val();

	document.getElementById("newDisciplineName").value = "";
	
		$('#createDisciplineModal').modal('hide');

		$.ajax({
			url: getContextPath() + "/discipline/create/" + wsId,
	    	data: {
	        	name: name
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

function showLecturers(id){

	$.ajax({
		cache: false,
		timeout: 10000, 
		url: getContextPath() + "/discipline/viewL/" + id,
		method: 'POST',       
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	})
	.done(function(tools) {
		$('#enrolledLecturers').empty();
		$('#notEnrolledLecturers').empty();
		var result = '';
		var all = '';
		var enrolledIds = new Array();
		result += '<div class="row">';
		tools.forEach(function(item) {
			enrolledIds.push(item.id);
			result += '<div class="col-md-4">';
			result += '<label class="checkbox-inline">';
			result += '<input type="checkbox" onchange="updateLecturer('+id+', '+item.id+');" id="'+item.id+'" checked>';
			result += item.name;
			result += ' </label>';
			result += '</div>';
		});
		result += '</div>';
		all += '<div class="row">';
		allLecturers.forEach(function(entry) {
			if(!enrolledIds.contains(entry.id)){
				all += '<div class="col-md-4">';
				all += '<label class="checkbox-inline">';
				all += '<input type="checkbox" onchange="updateLecturer('+id+', '+entry.id+');" id="'+entry.id+'">';
				all += entry.name;
				all += ' </label>';
				all += '</div>';
			}
					
		});
		all += '</div>';
		$('#enrolledLecturers').append(result);
		$('#notEnrolledLecturers').append(all);
		$('#lecturers').modal('show');
	})
	.fail(function() {
		$('#networkErrorModal').modal('show');
	});
}
function updateLecturer(disciplineId, lecturerId){
	if (document.getElementById(lecturerId).checked){									//alert("checked");
		$.ajax({
			url: getContextPath() + "/discipline/addLecturer/"+disciplineId+"/"+lecturerId,
	    	type: "GET",
	    	dataType : "text",
			timeout: 15000
		})
		.done(function(id) {
			$('#enrolledLecturers').empty();
			$('#notEnrolledLecturers').empty();
		})
		.fail(function() {
		});
	}else{																				//alert("UNchecked");
		$.ajax({
			url: getContextPath() + "/discipline/removeLecturer/"+disciplineId+"/"+lecturerId,
	    	type: "GET",
	    	dataType : "text",
			timeout: 60000
		})
		.done(function(id) {
			$('#enrolledLecturers').empty();
			$('#notEnrolledLecturers').empty();
		})
		.fail(function() {
			$('#networkErrorModal').modal('show');
		});
	}
}

function showStudents(){
	var course = $('#year').val();
	var specialtyId = $('#specialty').val();
	var disciplineId = disc;
	$.ajax({
		cache: false,
		timeout: 60000,
		url: getContextPath() + "/student/studentsNotInByDisciplineAndCourseAndSpecialty/"+course+"/"+specialtyId+"/"+disciplineId,
		method: 'POST',       
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	})
	.done(function(tools) {
		$('#studentsDiv').empty();
		$('#enrolledStudentsDiv').empty();
		var result = '';
		result += '<div class="row">';
		if (tools.length == 0){
			result += '<div class="panel-center">';
			result += 'No students found';
			result += '</div>';
		}
		tools.forEach(function(item) {
			result += '<div class="col-md-4">';
			result += '<label class="checkbox-inline">';
			result += '<input type="checkbox" onchange="updateStudent('+disc+', '+item.id+');" id="'+item.id+'">';
			result += item.name;
			result += ' </label>';
			result += '</div>';
		});
		result += '</div>';
		$('#studentsDiv').append(result);
	})
	.fail(function() {
		$('#networkErrorModal').modal('show');
	});
}

function showEnrolledStudents(disciplineId){

	$.ajax({
		cache: false,
		timeout: 60000, 
		url: getContextPath() + "/discipline/viewS/" + disciplineId,
		method: 'POST',       
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	})
	.done(function(tools) {
		$('#studentsDiv').empty();
		$('#enrolledStudentsDiv').empty();
		var result = '';
		result += '<div class="row">';
		tools.forEach(function(item) {
			result += '<div class="col-md-4">';
			result += '<label class="checkbox-inline">';
			result += '<input type="checkbox" onchange="updateStudent('+disciplineId+', '+item.id+');" id="'+item.id+'" checked>';
			result += item.name;
			result += ' </label>';
			result += '</div>';
		});
		result += '</div>';
		$('#enrolledStudentsDiv').append(result);
		$('#enrolledStudents').modal('show');
	})
	.fail(function() {
		$('#networkErrorModal').modal('show');
	});
}

function updateStudent (disciplineId, studentId){
	if (document.getElementById(studentId).checked){									//alert("checked");
		$.ajax({
			url: getContextPath() + "/discipline/addStudent/"+disciplineId+"/"+studentId,
	    	type: "GET",
	    	dataType : "text",
			timeout: 60000
		})
		.done(function(id) {
			$('#enrolledLecturers').empty();
			$('#notEnrolledLecturers').empty();
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