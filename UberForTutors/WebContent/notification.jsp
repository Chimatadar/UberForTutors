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
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
	padding-left: 20px;
	padding-right: 20px;
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
ArrayList<ReqNotificationDataContract> activityList = (ArrayList<ReqNotificationDataContract>) request.getAttribute("activityList");
	
	
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
							<form class="navbar-form" action="skillListPage" method="post">
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
		<% for( ReqNotificationDataContract activity : activityList ) { %>
	
	<div class="row-fluid">
<% if(activity.IsDeleted==0 && activity.Status==0) { %>
			You received a notification from <h2><%= activity.FromUser %></h2> 
			<form action = "" method="post">
			<input type='submit' value='Accept' name='update' class="btn btn-primary btn-medium "> 
			<input type='submit' value='Reject' name='remove' class="btn btn-primary btn-medium ">
			</form>
<% } %>
<br>
<% if(activity.IsDeleted==0 && activity.Status==1) { %>
You have already responded to <h2><%= activity.FromUser %>'s</h2> request
<% } %>
<br>
<% if(activity.IsDeleted==1 && activity.Status==1) { %>
You have been rated - <%
					for (int i = 0; i < activity.RatingId; i++) {
				%>
				<i class="fa fa-star"></i>
				<%
					}
				%>
<% } %>
<br>
<% if(activity.IsDeleted==1 && activity.Status==2) { %>
You have rejected <h2><%= activity.FromUser %></h2> 's request
<% } %>
<br>
	</div>
			<% } %>
	
	



</body>
</html>