<%@page import="java.util.ArrayList"%>
<%@page import="Model.*"%>
<%@page import="DataContracts.*"%>
<%@ page import="java.sql.*"%>

<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>Home Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<!-- Le styles -->
<!-- TODO: make sure bootstrap.min.css points to BootTheme generated file
        -->
<link href="bootstrap.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">

<style type="text/css">

/* USER PROFILE PAGE */
.card {
	margin-top: 20px;
	padding: 30px;
	background-color: rgba(214, 224, 226, 0.2);
	-webkit-border-top-left-radius: 5px;
	-moz-border-top-left-radius: 5px;
	border-top-left-radius: 5px;
	-webkit-border-top-right-radius: 5px;
	-moz-border-top-right-radius: 5px;
	border-top-right-radius: 5px;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

.card.hovercard {
	position: relative;
	padding-top: 0;
	overflow: hidden;
	text-align: center;
	background-color: #fff;
	background-color: rgba(255, 255, 255, 1);
}

.card.hovercard .card-background {
	height: 130px;
}

.card-background img {
	-webkit-filter: blur(25px);
	-moz-filter: blur(25px);
	-o-filter: blur(25px);
	-ms-filter: blur(25px);
	filter: blur(25px);
	margin-left: -100px;
	margin-top: -200px;
	min-width: 130%;
}

.card.hovercard .useravatar {
	position: absolute;
	top: 15px;
	left: 0;
	right: 0;
}

.card.hovercard .useravatar img {
	width: 100px;
	height: 100px;
	max-width: 100px;
	max-height: 100px;
	-webkit-border-radius: 50%;
	-moz-border-radius: 50%;
	border-radius: 50%;
	border: 5px solid rgba(255, 255, 255, 0.5);
}

.card.hovercard .card-info {
	position: absolute;
	bottom: 14px;
	left: 0;
	right: 0;
}

.card.hovercard .card-info .card-title {
	padding: 0 5px;
	font-size: 20px;
	line-height: 1;
	color: #262626;
	background-color: rgba(255, 255, 255, 0.1);
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
}

.card.hovercard .card-info {
	overflow: hidden;
	font-size: 12px;
	line-height: 20px;
	color: #737373;
	text-overflow: ellipsis;
}

.card.hovercard .bottom {
	padding: 0 20px;
	margin-bottom: 17px;
}

.btn-pref .btn {
	-webkit-border-radius: 0 !important;
}

body {
	padding-top: 60px;
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}

.badge1 {
	position: relative;
}

.badge1[data-badge]:after {
	content: attr(data-badge);
	position: absolute;
	top: -10px;
	right: -10px;
	font-size: .7em;
	background: green;
	color: white;
	width: 18px;
	height: 18px;
	text-align: center;
	line-height: 18px;
	border-radius: 50%;
	box-shadow: 0 0 1px #333;
}

.form2, .subscribe:hover>div {
	display: none;
}

.subscribe:hover>form {
	display: block;
}
</style>
<!-- TODO: make sure bootstrap-responsive.min.css points to BootTheme
        generated file -->
<link
	href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-responsive.min.css"
	rel="stylesheet">
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
<!-- Fav and touch icons -->
<link rel="shortcut icon" href="../assets/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="../assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="../assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="../assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="../assets/ico/apple-touch-icon-57-precomposed.png">
</head>

<body>
	<%
		ArrayList<SkillsTaught> rankedTutors = (ArrayList<SkillsTaught>) request.getAttribute("skillsTaught");
ArrayList<String> skillsKnown = (ArrayList<String>) request.getAttribute("skillsKnown");
ArrayList<SkillsLearntWithActivityId> skillsLearnt = (ArrayList<SkillsLearntWithActivityId>) request.getAttribute("skillsLearnt");
ArrayList<Notifications> notifications = (ArrayList<Notifications>) request.getAttribute("notifications");
		//ArrayList<SkillsModel> recommendedSkillModels = (ArrayList<SkillsModel>) request.getAttribute("recommendedSkills");
	%>
	<div class="navbar navbar-fixed-bottom">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>

				</a> <a class="brand" href="#">Uber For Tutors</a>
				<div class="nav-collapse collapse">

					<ul class="nav">
						<li class="active"><a href="#">Home</a></li>
						<li><a href="#about">Privacy Policy</a></li>
						<li><a href="#contact">Log Out</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>



	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>

				</a> <a class="brand" href="#">Uber For Tutors</a>
				<div class="nav-collapse collapse">
					<p class="navbar-text pull-right">
						Logged in as <a href="profile.jsp" class="badge1 navbar-link"
							data-badge="1">Username</a>
					</p>

					<ul class="nav">
						<li class="active"><a href="#">Home</a></li>
						<li><a href="#about">About</a></li>
						<li><a href="#contact">Contact</a></li>
						<li>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</li>
						<li>
							<form class="navbar-form" action="" method="post">
								<input class="span5" type="text" placeholder="search a skill">
								<button type="submit" class="btn">Search</button>
							</form>
						</li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3">
				<div class="card hovercard">
					<div class="card-background">
						<img class="card-bkimg" alt=""
							src="http://lorempixel.com/100/100/people/9/">
						<!-- http://lorempixel.com/850/280/people/9/ -->
					</div>
					<div class="useravatar">
						<img alt=""
							src="
<!--             http://lorempixel.com/100/100/people/9/ -->
            ">
					</div>
					<div class="card-info">
						<span class="card-title">Username</span>
					</div>

				</div>

				<!--/.well -->
			</div>
			<!--/span-->
			<div class="span9">
				<div class="hero-unit">
					<form method='post' action=''>
						<div class="hero-unit"
							style="background-color: white; padding: 5px">
							<span style="width: 300px"> requested to learn for the
								skill: </span> <input type='submit' value='Accept' name='update'
								class="btn btn-primary btn-medium "> <input
								type='submit' value='Reject' name='remove'
								class="btn btn-primary btn-medium ">
						</div>
						<div class="hero-unit"
							style="background-color: white; padding: 5px">
							<span style="width: 300px"> requested to learn for the
								skill: </span> <input type='submit' value='Accept' name='update'
								class="btn btn-primary btn-medium "> <input
								type='submit' value='Reject' name='remove'
								class="btn btn-primary btn-medium ">
						</div>
					</form>
				</div>
				<div class="row-fluid">
					<!-- 				ArrayList<String> skillsKnown = (ArrayList<String>) request.getAttribute("skillsKnown"); -->
					<% if(skillsKnown.size()!=0) { %>
					<div class="span4 hero-unit">
						<h2>Skills user knows</h2>
						<ul>
							<% for(int i = 0; i <skillsKnown.size();i++) { %>
							<li><%= skillsKnown.get(i)  %>></li>
							<% } %>
						</ul>
						<p></p>

						<div class="subscribe">
							<div class="btn">Add a skil</div>
							<form class="form2" action="" method="post">
								<p>
									<input type="text" class="email_field" name="skill" size="18"
										placeholder="enter a skill" /> <input class="email_btn"
										name="submit" type="submit" value="Enter" />
								</p>
							</form>
						</div>
					</div>
					<% } %>
					<!--/span-->
					<!-- 					ArrayList<SkillsLearntWithActivityId> skillsLearnt = (ArrayList<SkillsLearntWithActivityId>) request.getAttribute("skillsLearnt");
 -->
					<% if(skillsLearnt.size()!=0) { %>
					<div class="span4 hero-unit">
						<h2>Skills user learnt</h2>
						<ul>
							<% for(int i = 0; i <skillsLearnt.size();i++) { %>
							<li><%= skillsLearnt.get(i)  %>></li>
							<% } %>
						</ul>
						<p></p>

						<p>
							<a class="btn" href="skills.jsp"> Learn a new skill &raquo;</a>
						</p>
					</div>
					<% } %>
					<!--/span-->
					<!-- 							ArrayList<SkillsTaught> rankedTutors = (ArrayList<SkillsTaught>) request.getAttribute("skillsTaught");
 -->
					<% if(rankedTutors.size()!=0) { %>

					<div class="span4 hero-unit">
						<h2>Skills user taught</h2>
						<p></p>
						<ul>
						<% for(SkillsTaught user : rankedTutors) { %>
							<li><%= user.skill %> -</li>
						<% for(int i = 0; i < user.rating; i++) { %>
						<i class="fa fa-star"></i>
						<% } %>
						<%  } %> 
						</ul>
						

					</div>
					<% } %>
					<!--/span-->
				</div>

			</div>
			<!--/span-->
		</div>
		<!--/row-->
		<hr>
		<footer>
			<p>&copy; "Insert Footer Here"</p>
		</footer>
	</div>
	<!--/.fluid-container-->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js">
		
	</script>
	<script
		src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
</body>

</html>