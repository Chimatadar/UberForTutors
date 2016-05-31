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

.scrollable-menu {
	height: auto;
	max-height: 200px;
	overflow-x: hidden;
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
	ArrayList<SkillsModel> allskill = (ArrayList<SkillsModel>) request.getAttribute("allSkillsList");

		ArrayList<String> skillsKnown = (ArrayList<String>) request.getAttribute("skillsKnown");
		ArrayList<SkillsLearntWithActivityId> skillsLearnt = (ArrayList<SkillsLearntWithActivityId>) request
				.getAttribute("skillsLearnt");
		ArrayList<Notifications> notifications = (ArrayList<Notifications>) request.getAttribute("notifications");
		//ArrayList<SkillsModel> recommendedSkillModels = (ArrayList<SkillsModel>) request.getAttribute("recommendedSkills");
		String name = (String)session.getAttribute("userName");
	%>
	<%@include file="headerandfooter.jsp"%>

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
							src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/2000px-User_icon_2.svg.png">
<!--             http://lorempixel.com/100/100/people/9/ -->
            
					</div>
					<div class="card-info">
						<span class="card-title"><%=name %></span>
					</div>

				</div>

				<!--/.well -->
			</div>
			<!--/span-->
			<div class="span9">

				<div class="row-fluid">
					<!-- 				ArrayList<String> skillsKnown = (ArrayList<String>) request.getAttribute("skillsKnown"); -->

					<div class="span4 hero-unit" style = "background-color:#17202A;color:white">
						<h3>Skills user knows</h3>
						<%
						if (skillsKnown.size() != 0) {
					%>
						<ul>
							<%
								for (int i = 0; i < skillsKnown.size(); i++) {
							%>
							<li><%=skillsKnown.get(i)%></li>
							<%
								}
							%>
						</ul>
						<%
						}
					%>
						<p></p>

						<div class="subscribe">
							<div class="btn">Add a skil</div>
							<form class="form2" action="" method="post">
								<p>
			                        	<select class="form-group col-sm-12 selectpicker" multiple name="language"">
			                        	<% for(SkillsModel skill: allskill) { %>
										  <option style = "background-color:#de615e ;padding-bottom:10px"><%= skill.SkillName %></option>										  
										  
									      <% } %>
										</select>
									<input class="btn-primary" name="submit" type="submit"
										value="Submit" />
								</p>

							</form>
						</div>
					</div>

					<div class="span4 hero-unit" style = "background-color:#17202A;color:white">

						<h3>Skills user learnt</h3>
						<%
							if (skillsLearnt.size() != 0) {
						%>
						<ul>
							<%
								for (int i = 0; i < skillsLearnt.size(); i++) {
							%>
							<li><%=skillsLearnt.get(i)%>></li>
							<%
								}
							%>
						</ul>
						<%
							}
						%>
						<p></p>
						<p>
							<a class="btn" href="skillsListPage?fromProfilePage=1"> Learn
								a new skill &raquo;</a>
						</p>
					</div>

					<div class="span4 hero-unit" style = "background-color:#17202A;color:white">
						<h3>Skills user taught</h3>
						<%
							if (rankedTutors.size() != 0) {
						%>

						<p></p>
						<ul>
							<%
								for (SkillsTaught user : rankedTutors) {
							%>
							<li><%=user.skill%> -</li>
							<%
								for (int i = 0; i < user.rating; i++) {
							%>
							<i class="fa fa-star"></i>
							<%
								}
							%>
							<%
								}
							%>
						</ul>
						<%
							}
						%>
					</div>
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