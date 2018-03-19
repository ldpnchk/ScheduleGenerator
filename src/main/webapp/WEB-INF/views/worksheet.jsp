<%@include file="header.jsp"%>
<div class="container-fluid padding-top-20">
	<div class="row">
		<div class="col-md-1 col-md-offset-1">
			<img src="<%=request.getContextPath()%>/resources/img/back.jpg" style="width: 100px;height:auto;" onclick="goBack()">
		</div>

		<div class="col-md-8 main-panel padding-bottom-20 padding-left-20 padding-right-20">
			<h1>
				<p id="worksheetName">${worksheet.name}</p>
			</h1>
			<h6>
				<p id="worksheetName"><spring:message code="lessons"/>: ${result.size()}.</p>
			</h6>
			
			<div class="btn-group btn-group-sm" role="group" aria-label="...">
				<button type="button" class="btn btn-success" onclick="generate();">
					<i class="fa fa-plus-circle" aria-hidden="true"></i> <spring:message code="generate"/>
				</button>
				<button type="button" class="btn btn-primary" data-toggle="modal"
					data-target="#getScheduleModal">
					<i class="fa fa-print" aria-hidden="true"></i> <spring:message code="schedule"/>
				</button>
				<button type="button" class="btn btn-warning" data-toggle="modal"
					data-target="#editWorksheetNameModal">
					<i class="fa fa-pencil" aria-hidden="true"></i> <spring:message code="worksheet.edit"/>
				</button>
				<button type="button" class="btn btn-danger" data-toggle="modal"
					data-target="#deleteWorksheetModal" id="deleteWorksheetButton">
					<i class="fa fa-trash" aria-hidden="true"></i> <spring:message code="worksheet.delete"/>
				</button>
			</div>
			<div style="border-top: thick solid #A4A4A4; border-width: 1px; margin-top: 10px;">
			</div>
			<div class="row padding-20 panel-center">
				<button type="button" class="btn btn-success "
					data-toggle="modal" data-target="#createLessonModal"
					id="createLessonButton" onclick="getData(${worksheet.id});" >
					<i class="fa fa-plus-circle" aria-hidden="true"></i>
					<spring:message code="lesson.create"/>
				</button>
				<button type="button" class="btn btn-success "
					data-toggle="modal" data-target="#addClassroom"
					id="addClassroomButton"  >
					<i class="fa fa-plus-circle" aria-hidden="true"></i>
					<spring:message code="addClassroom"/>
				</button>
				<button type="button" class="btn btn-success "
					data-toggle="modal" data-target="#addSpec"
					id="addSpecButton"  >
					<i class="fa fa-plus-circle" aria-hidden="true"></i>
					<spring:message code="addSpec"/>
				</button>
				
				<a href="<c:url value="/settings/${worksheet.id}"/>" >
					<button type="button" class="btn btn-success ">
						<span class="glyphicon glyphicon-cog"> </span>
						<spring:message code="DisciplinesGroupsLecturers"/>
					</button>
				</a>
				<a href="<c:url value="/restriction/view/${worksheet.id}"/>" >
					<button type="button" class="btn btn-success ">
						<span class="glyphicon glyphicon-alert"> </span>
						<spring:message code="restrictions"/>
					</button>
				</a>
				
			</div>
			<table id="lessonsTable" class="table table-striped table-hover table-bordered" class="width-100p">
				<thead class="panel-center">
					<tr style="background-color: #BDBDBD;;">
						<td><spring:message code="name"/></td>
						<td><spring:message code="discipline"/></td>
						<td><spring:message code="lecturer"/></td>
						<td style="width: 10%;"><spring:message code="student.amount"/></td>
						<td style="width: 10%;"><spring:message code="classroom"/></td>
						<td style="width: 10%;"><spring:message code="day"/></td>
						<td style="width: 10%;"><spring:message code="period"/></td>
						<td style="width: 10%;" data-orderable="false"></td>
					</tr>
				</thead>
				<tbody class="panel-center">
					<c:forEach items="${result}" var="r">
						<tr>
							<td>${r.lesson.name}</td>
							<td>
								<p>${r.lesson.discipline.name}</p>
							</td>
							<td>								
								<p>${r.lesson.lecturer.name}</p>
							</td>
							<td>${r.amount}</td>
							
							<c:if test="${empty r.condition}">
    							<td></td>
								<td></td>
								<td></td>
							</c:if>
							<c:if test="${not empty r.condition}">
    							<td>${r.condition.classroom.building}-${r.condition.classroom.number}</td>
								<td>${r.condition.day.name}</td>
								<td>${r.condition.period.number}</td>
							</c:if>

							<td>
								<a class="btn btn-xs btn-danger" href="<c:url value="/lesson/delete/${worksheet.id}/${r.lesson.id}/" />">
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

<!-- editWorksheetNameModal -->
<div class="modal fade" id="editWorksheetNameModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel"><spring:message code="worksheet.edit"/></h4>
			</div>
			<form>
				<div class="modal-body">

					<div class="form-group">
						<label for="name"><spring:message code="worksheet.name"/></label> 
						<input type="text" class="form-control" maxlength="255" id="newWorksheetName"
							placeholder="Enter new worksheet name" value="${worksheet.name}" required />
					</div>

				</div>
			
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cancel"/></button>
					<button type="button" class="btn btn-warning"
						onclick="updateWorksheetName();"><spring:message code="update"/></button>
				</div>
			</form>

		</div>
	</div>
</div>

<!-- deleteWorksheetModal -->
<div class="modal fade" id="deleteWorksheetModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog modal-sm" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel"><spring:message code="deleting"/></h4>
			</div>
			<form>
				<div class="modal-body">

					<div class="form-group">
						<p><spring:message code="areyousure"/></p>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cancel"/></button>
					<button type="button" class="btn btn-danger"
						onclick="deleteWorksheet();"><spring:message code="delete"/></button>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- createLessonModal -->
<div class="modal fade" id="createLessonModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel"><spring:message code="lesson.create"/></h4>
			</div>
			<form>
				<div class="modal-body">
				
					<div class="row">
						<div class="col-md-4">
							<div class="form-group">
								<label for="name"><spring:message code="lesson.name"/>:</label>
								<input type="text" maxlength="255" class="form-control" id="newLessonName" placeholder="Enter new lesson name">
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label for="name"><spring:message code="discipline"/>:</label>
								<div id="disciplines">

								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label for="name"><spring:message code="lecturer"/>:</label>
								<div id="lecturers">
									<select class="form-control" data-size="5" disabled>
									</select>
								</div>
							</div>
						</div>
					
					</div>
					
					<div class="form-group">
						<label for="name"><spring:message code="roomtype"/>:</label>
						<div id="roomtypes">
						
						</div>
					</div>
					
					<div class="form-group">
						<label for="name"><spring:message code="students"/>:</label>
						<input type="checkbox" id="checkall" disabled/> <spring:message code="checkall"/>
						<div id="mystudents">
						
						</div>
					</div>
					
					<div class="form-group">
						<div id="tools">
						
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cancel"/></button>
					<button type="button" class="btn btn-success" onclick="createLesson();"><spring:message code="create"/></button>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- getSheduleModal -->
<div class="modal fade" id="getScheduleModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog modal-sm" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel"><spring:message code="schedule.get"/></h4>
			</div>
			<form>
				<div class="modal-body">
					<div class="form-group">
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
							
							<label for="specialty"><spring:message code="specialty"/>:</label>
							<select class="form-control" id="specialty">
								<c:forEach items="${specs}" var="spec">
									<option value="${spec.id}">${spec.name}</option>
								</c:forEach>
							</select>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cancel"/></button>
					<a onclick="getSchedule();" id="generatorRequest"><button type="button" class="btn btn-success"><spring:message code="update"/></button></a>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- addClassroom Modal -->
	<div class="modal fade" id="addClassroom" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
		    <div class="modal-content">
		    	<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title" id="myModalLabel"><spring:message code="addClassroom"/></h4>
		      	</div>
		     	<form>
		        	<div class="modal-body">
						<div class="form-group">
							
							<label for="building"><spring:message code="Building"/></label>
					    	<input type="text" maxlength="255" class="form-control" name="building" id="building" placeholder="Enter building number"/>
							
							<label for="newClassroomName"><spring:message code="Room"/></label>
					    	<input type="text" maxlength="255" class="form-control" name="newClassroomName" id="newClassroomName" placeholder="Enter classroom number"/>
							
							<label for="capacity"><spring:message code="Capacity"/></label>
					    	<input type="number" class="form-control" name="capacity" id="capacity" placeholder="Enter capacity"/>
							
							<div class="form-group">
							  <label for="type"><spring:message code="Type"/></label>
							  <select class="form-control" id="type">
							  	<option value="1"><spring:message code="RegularRoom"/></option>
							    <option value="2"><spring:message code="CompRoom"/></option>
							  </select>
							</div>
							
							<div class="form-group">
							  <label for="tool"><spring:message code="Projector"/></label>
							  <select class="form-control" id="tool">
							    <option value="1"><spring:message code="has"/></option>
							    <option value="0"><spring:message code="hasNo"/></option>
							  </select>
							</div>
						</div>
		          	</div>
		          	
		      		<div class="modal-footer">
		       			<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cancel"/></button>
		        		<button type="button" class="btn btn-success" onclick="addClassroom(${worksheet.id});"><spring:message code="create"/></button>
		      		</div>
		    	</form>
			</div>
		</div>
	</div>
	
<!-- add Specialty Modal -->
	<div class="modal fade" id="addSpec" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
		    <div class="modal-content">
		    	<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title" id="myModalLabel"><spring:message code="addSpec"/></h4>
		      	</div>
		     	<form>
		        	<div class="modal-body">
						<div class="form-group">
							
							<label for="nameSpec"><spring:message code="SpecName"/></label>
					    	<input type="text" maxlength="255" class="form-control" name="nameSpec" id="nameSpec" placeholder="Enter specialty name"/>
							
						</div>
		          	</div>
		          	
		      		<div class="modal-footer">
		       			<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cancel"/></button>
		        		<button type="button" class="btn btn-success" onclick="addSpecialty(${worksheet.id});"><spring:message code="create"/></button>
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

var isDataInitialized = false;

$('#lessonsTable').DataTable(default_options);

function updateWorksheetName() {
	
	$('#editWorksheetNameModal').modal('hide');
	
	var id = "${worksheet.id}";
	var name = $('#newWorksheetName').val();

	$.ajax({
	    url: getContextPath() + "/worksheet/update/",
	    data: {
	    	id: id,
	        name: name
	    },
	    type: "POST",
	    dataType : "text",
		timeout: 60000
	})
	.done(function(message) {
		$('#worksheetName').text(name);
	})
	.fail(function() {
		$('#networkErrorModal').modal('show');
	});
}

function deleteWorksheet() {
	
	$('#deleteWorksheetModal').modal('hide');

	var id = "${worksheet.id}";
	
	$.ajax({
	    url: getContextPath()+"/worksheet/delete/",
	    data: {
	        id: id
	    },
	    type: "POST",
	    dataType : "text",
		timeout: 60000
	})
	.done(function(message) {
	    window.location = '<%=request.getContextPath()%>';
	})
	.fail(function() {
		$('#networkErrorModal').modal('show');
	});
}

function getData(wsId){
	if (!isDataInitialized){
		$('#loadingModal').modal('show');
		getDisciplines(wsId);
		getRoomTypes();
		getTools();
		isDataInitialized = true;
		$('#loadingModal').modal('hide');
	}
}

function getDisciplines(wsId){
	
	$.ajax({
		cache: false,
		timeout: 60000, 
		url: getContextPath() + "/discipline/getAll/" + wsId,
		method: 'POST',       
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	})
	.done(function(disciplines) {
		var result = '<select id="select-disp" class="form-control" data-size="5" onchange="getData2();">';
		result += '<option value=""></option>';
		disciplines.forEach(function(item) {
			result += ('<option value="' + item.id + '">' + item.name + '</option>');
		});
		result += '</select>';
		$('#disciplines').append(result);
	})
	.fail(function() {
		$('#networkErrorModal').modal('show');
	});
	
}

function getRoomTypes(){
	
	$.ajax({
		cache: false,
		timeout: 60000, 
		url: getContextPath() + "/roomType/getAll/",
		method: 'POST',       
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	})
	.done(function(roomTypes) {
		var result = '';
		var i = 0
		roomTypes.forEach(function(item) {
			if (i == 0){
				result += ('<label class="radio-inline"><input type="radio" value="' + item.id + '" name="optradio" id="rt-' + item.id + '" checked="checked">' + item.name + '</label>');
				i++;
			}
			else{
				result += ('<label class="radio-inline"><input type="radio" value="' + item.id + '" name="optradio" id="rt-' + item.id + '">' + item.name + '</label>');
			}
		});
		$('#roomtypes').append(result);
	})
	.fail(function() {
		$('#networkErrorModal').modal('show');
	});
}

function getTools(){
	
	$.ajax({
		cache: false,
		timeout: 60000, 
		url: getContextPath() + "/tool/getAll/",
		method: 'POST',       
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	})
	.done(function(tools) {
		var result = '';
		tools.forEach(function(item) {
			result += '<label for="name">';
			result += item.name;
			result += ':';
			result += '</label>';
			result += '</br>';
			result += '<label class="radio-inline">';
			result += '<input type="radio" value="yes" name="opttool" id="toolr-' + item.id + '"> <spring:message code="yes"/>';
			result += '</label>';
			result += '<label class="radio-inline">';
			result += '<input type="radio" name="opttool" id="toolr-' + item.id + '" checked> <spring:message code="dmatter"/>';
			result += '</label>';
			result += '<label class="radio-inline">';
			result += '<input type="radio" value="no" name="opttool" id="toolr-' + item.id + '"> <spring:message code="no"/>';
			result += '</label>';
		});
		$('#tools').append(result);
	})
	.fail(function() {
		$('#networkErrorModal').modal('show');
	});
}


function getData2(){
	if (document.getElementById("select-disp").value != ''){
		$('#loadingModal').modal('show');
		getLecturers();
		getStudents();
		$('#loadingModal').modal('hide');
	}
}

function getLecturers(){
	
	var id = document.getElementById("select-disp").value;
	
	$.ajax({
		cache: false,
		timeout: 60000, 
		url: getContextPath() + "/lecturer/getAllByDiscipline/",
		method: 'POST', 
		data: {
	        id: id
	    },
	    dataType: "json"
	})
	.done(function(lecturers) {
		var result = '<select id="select-lect" class="form-control" data-size="5"">';
		result += '<option value=""></option>';
		lecturers.forEach(function(item) {
			result += ('<option value="' + item.id + '">' + item.name + '</option>');
		});
		result += '</select>';
		$("#lecturers").empty();
		$('#lecturers').append(result);
	})
	.fail(function() {
		$('#networkErrorModal').modal('show');
	});
}

function getStudents(){
	
	var id = document.getElementById("select-disp").value;
	
	$.ajax({
		cache: false,
		timeout: 60000, 
		url: getContextPath() + "/student/getAllByDiscipline/",
		method: 'POST', 
		data: {
	        id: id
	    },
	    dataType: "json"
	})
	.done(function(students) {
		var result = '';
		result += '</hr>';
		result += '<div class="row">';
		students.forEach(function(item) {
			result += '<div class="col-md-4">';
			result += ('<input type="checkbox" id="stud-' + item.id + '"/> ' + item.name);
			result += '</div>';
		});
		result += '</div>';
		document.getElementById('checkall').disabled = false;
		$("#mystudents").empty();
		$('#mystudents').append(result);
	})
	.fail(function() {
		$('#networkErrorModal').modal('show');
	});
	
}

function createLesson(){
	
	$('#loadingModal').modal('show');
	
	students = [];
	students.push(0);
	var elements = $('[id^="stud"]');
	
	$('[id^="stud"]').each(function(item){
		 if(elements[item].checked){
			 students.push(parseInt(elements[item].id.slice(5), 10));
		 }
	});
	
	toolsy = [];
	toolsy.push(0);
	toolsn = [];
	toolsn.push(0);
	
	var eelements = $('[id^="toolr"]');
	
	$('[id^="toolr"]').each(function(item){
		if(eelements[item].checked){
			if (eelements[item].getAttribute('value') == "yes"){
				toolsy.push(parseInt(eelements[item].id.slice(6), 10));
			}
			else if (eelements[item].getAttribute('value') == "no"){
				toolsn.push(parseInt(eelements[item].id.slice(6), 10));
			}
		}
	});
	
	var id = "${worksheet.id}";
	var disp_id = document.getElementById("select-disp").value;
	var lect_id = document.getElementById("select-lect").value;
	var name = $('#newLessonName').val();
	
	var room_type_id = $('input[name=optradio]:checked').val();
	
	$.ajax({
	    url: getContextPath() + "/lesson/create/",
	    data: {
	    	id: id,
	    	name: name,
	    	disp_id: disp_id, 
	    	lect_id: lect_id,
	    	room_type_id: room_type_id,
	    	students: students,
	    	toolsy: toolsy,
	    	toolsn: toolsn
	    },
	    type: "POST",
	    dataType : "text",
		timeout: 60000
	})
	.done(function(message) {
		$('#loadingModal').modal('hide');
	    location.reload();
	})
	.fail(function() {
		$('#loadingModal').modal('hide');
		$('#networkErrorModal').modal('show');
	});
	
}

function generate(){
	
	$('#loadingModal').modal('show');
	
	var id = "${worksheet.id}";
	
	$.ajax({
	    url: getContextPath() + "/generator/generate/",
	    data: {
	        id: id
	    },
	    type: "POST",
	    dataType : "text",
		timeout: 60000
	})
	.done(function(message) {
	    $('#loadingModal').modal('hide');
	    location.reload();
	})
	.fail(function() {
		$('#loadingModal').modal('hide');
		$('#networkErrorModal').modal('show');
	});
	
}

$('#checkall').change(function () {
	if (document.getElementById('checkall').checked){
		var elements = $('[id^="stud"]');
		
		$('[id^="stud"]').each(function(item){
			 elements[item].checked = true;
		});
		
	}
	else{
		var elements = $('[id^="stud"]');
		
		$('[id^="stud"]').each(function(item){
			 elements[item].checked = false;
		});
	}
});

function getSchedule(){

	var year = $('#year').val();
	var specialtyId = $('#specialty').val();
	var idWorksheet = "${worksheet.id}";
	document.getElementById("generatorRequest").setAttribute("href",getContextPath()+"/generator/get/"+year+"/"+specialtyId+"/"+idWorksheet);
	
}

function addSpecialty(wsId){
	var name = $('#nameSpec').val();
	
		$.ajax({
			url: context + "/worksheet/createSpeciality",
	    	data: {
	        	name: name,
	        	wsId: wsId
	    	},
	    	type: "POST",
	    	dataType : "text",
			timeout: 60000
		})
		.done(function(id) {
		    //location.reload();
			$('#addSpec').modal('hide');
		})
		.fail(function() {
			$('#networkErrorModal').modal('show');
		});
}

function addClassroom(wsId){

	var building = $('#building').val();
	var numClassroom = $('#newClassroomName').val();
	var capacity = $('#capacity').val();
	var tool = $('#tool').val();
	var type = $('#type').val();
	
	//alert(building +" "+ numClassroom +" "+ capacity +" "+ tool +" "+ type);
	//document.getElementById("newStudentName").value = "";
	
		$.ajax({
			url: context + "/worksheet/createClassroom",
	    	data: {
	        	building: building+"",
	        	numClassroom: numClassroom+"",
	        	tool: tool,
	        	type: type,
	        	capacity: capacity,
	        	wsId: wsId
	    	},
	    	type: "POST",
	    	dataType : "text",
			timeout: 60000
		})
		.done(function(id) {
		    //location.reload();
			$('#addClassroom').modal('hide');
		})
		.fail(function() {
			$('#networkErrorModal').modal('show');
		});
	
}
</script>

<%@include file="footer.jsp"%>