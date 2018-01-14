<%@include file="header.jsp"%>

<div class="container-fluid panel-center">

	<div class="row padding-top-10">
		<div class="col-md-12">
			<div class="panel-center">
				<font size="6" color="white" face="Century Gothic"><spring:message code="worksheets"/></font>
			</div>
		</div>
	</div>
	
	<div class="row padding-top-20">
		<div class="col-md-8 col-md-offset-2">
		 	<div class="main-panel">
		 		<div class="row">
		 			<c:choose>
						<c:when test="${worksheets.size() == 0}">
							<div class="padding-45">
								<font size="4" face="Century Gothic"><spring:message code="worksheet.notfound"/></font>
							</div>
						</c:when>
						<c:otherwise>
							<c:forEach items="${worksheets}" var="worksheet">
			                	<div class="col-md-3 padding-top-20" id="worksheet-${worksheet.id}">
			                		<a href="<c:url value="/worksheet/view/${worksheet.id}/" />">
			  							<img src="<%=request.getContextPath()%>/resources/img/worksheet.png"
											style="width: 80%">
										<p class="padding-top-10">${worksheet.name}</p>
									</a>
									<p>
										<button type="button" class="btn btn-xs btn-danger"
											data-toggle="modal" data-target="#deleteWorksheetModal"
											onclick="deleteInit(${worksheet.id});">
											<i class="fa fa-trash" aria-hidden="true"></i><spring:message code="delete"/>
										</button>
									</p>
			                	</div> 
			               	</c:forEach>
		 				</c:otherwise>
					</c:choose>
	        	</div> 
	        </div> 
	     </div>
	     <div class="col-md-1 padding-top-20">
	     	<a data-toggle="modal" data-target="#createWorksheetModal">
	  			<img src="<%=request.getContextPath()%>/resources/img/add_worksheet.png"
					style="width: 100%">
			</a>
	     </div>        
     </div>   
</div>

<!-- createWorksheetModal -->
<div class="modal fade" id="createWorksheetModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
	    <div class="modal-content">
	    	<div class="modal-header">
	        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        	<h4 class="modal-title" id="myModalLabel"><spring:message code="worksheet.create"/></h4>
	      	</div>
	     	<form>
	        	<div class="modal-body">
					<div class="form-group">
						<label for="name"><spring:message code="worksheet.name"/></label>
				    	<input type="text" maxlength="255" class="form-control" name="name" id="newWorksheetName" placeholder="Enter new worksheet name"/>
						
						<div id="emptyNameError" class="padding-left-10" hidden="hidden">
	          				<font size="2" color="red"><spring:message code="noemptyfield"/></font>
	          			</div>
					</div>
	          	</div>
	          	
	      		<div class="modal-footer">
	       			<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="cancel"/></button>
	        		<button type="button" class="btn btn-success" onclick="createWorksheet();"><spring:message code="create"/></button>
	      		</div>
	    	</form>
		</div>
	</div>
</div>
     
<!-- deleteModal -->
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
					<button type="button" class="btn btn-danger" onclick="deleteWorksheet();"><spring:message code="delete"/></button>
				</div>
			</form>
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

$('#createWorksheetModal').on('hidden.bs.modal', function() {
	document.getElementById('emptyNameError').hidden = "hidden";
})

var idToDelete = 0;

function deleteInit(id) {
	idToDelete = id;
}

function createWorksheet() {
	
	var name = $('#newWorksheetName').val();
	
	if (name == ""){
		document.getElementById('emptyNameError').hidden = "";
	}
	
	else{
		
		$('#createWorksheetModal').modal('hide');

		$.ajax({
			url: getContextPath() + "/worksheet/create/",
	    	data: {
	        	name: name
	    	},
	    	type: "POST",
	    	dataType : "text",
			timeout: 60000
		})
		.done(function(id) {
	    	window.location = "<%=request.getContextPath()%>/worksheet/view/" + id + "/";
		})
		.fail(function() {
			$('#networkErrorModal').modal('show');
		});
	}
	
}

function deleteWorksheet() {
	
	$('#deleteWorksheetModal').modal('hide');
	
	var id = idToDelete;

	$.ajax({
	    url: getContextPath() + "/worksheet/delete/",
	    data: {
	        id: id
	    },
	    type: "POST",
	    dataType : "text",
		timeout: 60000
	})
	.done(function(message) {
		$('#worksheet-'+ id).remove();
		var h = $('#col-worksheets').css('height');
		$('#col-new-worksheet').css('height', h);
	})
	.fail(function( xhr, status, errorThrown ) {
		$('#networkErrorModal').modal('show');
	})
	.always(function( xhr, status ) {
	});
}

</script>
<%@include file="footer.jsp"%>