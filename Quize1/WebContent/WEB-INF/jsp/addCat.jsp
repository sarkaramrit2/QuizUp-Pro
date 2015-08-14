
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>BLOCKS - Bootstrap Dashboard Theme</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Carlos Alvarez - Alvarez.is">

<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- Le styles -->

<link href="login.css" rel="stylesheet">

<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css" />

<style type="text/css">
body {
	padding-top: 30px;
}

pbfooter {
	position: relative;
}
</style>

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- Google Fonts call. Font Used Open Sans & Raleway -->
<link href="http://fonts.googleapis.com/css?family=Raleway:400,300"
	rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Open+Sans"
	rel="stylesheet" type="text/css">
<link href="main.css" rel="stylesheet">
<!-- Jquery Validate Script -->
<script type="text/javascript" src="jquery.validate.js"></script>

<!-- Jquery Validate Script - Validation Fields -->
<script type="text/javascript">
		
		$.validator.setDefaults({
			submitHandler: function() { window.open ('dashboard.html','_self',false) }
		});
		
		$().ready(function() {
			// validate the comment form when it is submitted
			$("#commentForm").validate();
		
			// validate signup form on keyup and submit
			$("#signupForm").validate({
				rules: {
					firstname: "required",
					lastname: "required",
					username: {
						required: true,
						minlength: 1
					},
					password: {
						required: true,
						minlength: 1
					},
					confirm_password: {
						required: true,
						minlength: 2,
						equalTo: "#password"
					},
					email: {
						required: true,
						email: true
					},
					topic: {
						required: "#newsletter:checked",
						minlength: 2
					},
					agree: "required"
				},
				messages: {
					firstname: "Please enter your firstname",
					lastname: "Please enter your lastname",
					username: {
						required: "Please enter a username",
						minlength: "Your username must consist of at least 1 character"
					},
					password: {
						required: "Please provide a password",
						minlength: "Your password must be at least 1 character long"
					},
					confirm_password: {
						required: "Please provide a password",
						minlength: "Your password must be at least 5 characters long",
						equalTo: "Please enter the same password as above"
					},
					email: "Please enter a valid email address",
					agree: "Please accept our policy"
				}
			});
		
			// propose username by combining first- and lastname
			$("#username").focus(function() {
				var firstname = $("#firstname").val();
				var lastname = $("#lastname").val();
				if(firstname && lastname && !this.value) {
					this.value = firstname + "." + lastname;
				}
			});
		
			//code to hide topic selection, disable for demo
			var newsletter = $("#newsletter");
			// newsletter topics are optional, hide at first
			var inital = newsletter.is(":checked");
			var topics = $("#newsletter_topics")[inital ? "removeClass" : "addClass"]("gray");
			var topicInputs = topics.find("input").attr("disabled", !inital);
			// show when newsletter is checked
			newsletter.click(function() {
				topics[this.checked ? "removeClass" : "addClass"]("gray");
				topicInputs.attr("disabled", !this.checked);
			});
		});
		</script>

</head>

<style>
.pbfooter {
	position: relative;
}

.navbar-header {
	height: 60px;
}

.container1{
	margin-top: 100px;
	margin-right: 50px;
}
</style>

<body bgcolor="#00FFFF">

	<!-- NAVIGATION MENU -->

	<div class="navbar-nav navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header"  style="width: 150px">
				
			<a class="navbar-brand" href="Admin_Console.html" style="padding:0;padding-top:15px;width:200px;">
				<span style="inline"><img src="logo30.png"alt=""/> QuizUp Pro</span></a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="home.htm"><i
							class="icon-home icon-white"></i> Home</a></li>
					<li><a href="addQues.htm"><i
							class="icon-th icon-white"></i>Add a Question</a></li>
					<li><a href="addCat.htm"><i class="icon-lock icon-white"></i>Add a
							Category</a></li>
					<li><a href="modifyQues.htm"><i class="icon-user icon-white"></i>Modify
							a Question</a></li>
					<li><a href="logout.htm"><i class="icon-user icon-white"></i>Logout</a></li>
				
				

				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>

<div class="container"  align="center"  >
	<div class="panel panel-default" style="margin-top: 100px;width: 800px; ">
      <div class="panel-heading">Adding a new Category</div>
    
<form class="form-horizontal" role="form" style="margin-top: 30px;" method="post" action="Cat.htm">

	
 
	<div class="form-group">
    <label class="control-label col-sm-2" for="pwd">Category Name:</label>
    <div class="col-sm-10" style="padding-right:30px;"> 
      <input type="text" class="form-control" id="cat" placeholder="Your New Category" name="category_name">
    </div>
  </div>
  
  <div class="form-group">
    <label class="control-label col-sm-2" for="pwd">Category Desc:</label>
    <div class="col-sm-10" style="padding-right:30px;"> 
      <input type="text" class="form-control" id="cat" placeholder="Your New Category" name="category_desc">
    </div>
  </div>
  

  <div class="form-group"> 
    <div class="col-sm-offset-2 col-sm-10" align="left">
      <button type="submit" class="btn btn-default">Add Category</button>
    </div>
  </div>
  
  
  
</form>
</div>
</div>





	<script type="text/javascript" src="bootstrap.js"></script>


</body>
</html>