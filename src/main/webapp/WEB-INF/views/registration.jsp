<%@include file="header.jsp"%>
<br/>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-4 col-md-offset-4">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="wrapper">
						<div class="container-fluid">
							<div class="input-group">
								<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
								<input id="username" type="text" class="form-control" name="username" placeholder="Username" required>
							</div><br/>
							<div class="input-group">
								<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span> 
								<input id="password" type="password" class="form-control" name="password" placeholder="Password" required>
							</div><br/>
							<div class="input-group">
								<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span> 
								<input id="passwordC" type="password" class="form-control" name="passwordC" placeholder="Confirm Password" required>
							</div><br/>
							<div class="top5">
								<button type="submit" class="btn btn-success col-md-6 col-md-offset-3" onclick="register();">Register me</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<center>
	<div class="okBox" style="display:none">
		Success! 
	</div><br/>
	<div class="alertBox" style="display:none">
		Try another username. 
	</div>
</center>
<script>
function register() {
	var name = $("#username").val();
	var pass = $("#password").val();
	var passC = $("#passwordC").val();
	
	var user = {
			name: name,
        	password: pass
        };
	
	if(name==""){
		alert("Username field can't be empty!");
	}else if(pass != passC || pass==""){
		alert("Shure that password fields are equal and not empty!");
	}else{
	    $.ajax({
	        cache: false,
	        timeout: 15000,
	    	url: context+"/createUser/",
	        method: 'POST',
	        data: JSON.stringify(user),
	        contentType: "application/json; charset=utf-8",
	        dataType: "json"
	    }).done(function (data) {
	    	if(data==0){
		    	document.getElementsByClassName("alertBox")[0].style.display = "block";
		    	document.getElementsByClassName("alertBox")[0].style.backgroundColor = "#F08080";
		    	document.getElementsByClassName("alertBox")[0].style.width = "30%";
	    	}else{
		    	document.getElementsByClassName("okBox")[0].style.display = "block";
		    	document.getElementsByClassName("okBox")[0].style.backgroundColor = "#00FF00";
		    	document.getElementsByClassName("okBox")[0].style.width = "30%";
		    	setTimeout(redirect, 1000);
		    	function redirect() { document.location.href = context+'/login'; }
	    	}
	    }).fail(function (error) {
	    	document.getElementsByClassName("alertBox")[0].style.display = "block";
	    	document.getElementsByClassName("alertBox")[0].style.backgroundColor = "#F08080";
	    	document.getElementsByClassName("alertBox")[0].style.width = "30%";
	    });
	}
	
}
</script>
<%@include file="footer.jsp"%>