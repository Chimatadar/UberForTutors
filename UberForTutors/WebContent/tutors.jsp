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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">

</head>
<body style = "background:black;padding-top:0px">
	<%
		ArrayList<TutorDataContract> rankedTutors = (ArrayList<TutorDataContract>) request
				.getAttribute("userSkillRatings");
	ArrayList<TutorDataContract> friendsTutor = (ArrayList<TutorDataContract>) request
			.getAttribute("friendsTutors");
		//ArrayList<SkillsModel> recommendedSkillModels = (ArrayList<SkillsModel>) request.getAttribute("recommendedSkills");
	%>
	<%
		int sid = 0;
		if (request.getAttribute("skillId") != null) {
			String s = request.getAttribute("skillId").toString();
			sid=Integer.parseInt(s);
		}
	%>
	<%@include file="headerandfooter.jsp"%>
<%
		if (rankedTutors == null || rankedTutors.size() == 0) {
	%>
	
	
	<div class = "hero-unit" style = "background:transparent"></div>
	   <div class="hero-unit" style = "background-color:#17202A">
	   <hr>
	   		<div style="color:white;text-align:center">No tutors found</div>
	   <hr>
	   </div>
		<% } %>
	
<div class = "hero-unit span5" style = "background:transparent;width:500px">
	<%
		if (rankedTutors != null) {
	%>
	<%
		if (rankedTutors.size() != 0) {
	%>
	<h2 style = "color:white"> Tutors recommended by us</h2>
	<%
		for (TutorDataContract user : rankedTutors) {
	%>
	
	<div class="hero-unit" style="background-color:rgba(255,255,255,0.1);padding-top: 10px;">
		<div class="">
			<span style = "color:white">Name:  </span>:<a href="ProfileController?userId=<%=user.UserId%>"><%=user.Email%></a>
			<br> <span style = "color:white">Rating -</span>
			
			<span style = "color:yellow"><%
				for (int i = 0; i < user.RatingId; i++) {
			%>
			<i class="fa fa-star"></i>
			<%
				}
			%>
			</span>
		</div>
		<div class="">
			<form method='post' action="ActivityController?userId=<%=user.UserId%>&skillId=<%=user.SkillId%>">

				<textarea style="width: 300px; height: 150px;" type="text" name="message" placeholder="Please enter the message for tutor here"></textarea>
				
					<input class="btn-primary" name="submit" type="submit"
										value=Request />
			</form>
		</div>

	</div>
	<%
		}
	%>

	<%
		}
		}
	%>
	
	
		</div>
	
	
	
	<div class = "hero-unit span5" style = "background:transparent;width:500px">
	<%
		if (friendsTutor != null) {
	%>
	<%
		if (friendsTutor.size() != 0) {
	%>
	<h2 style = "color:white"> Tutors recommended by friends</h2>
	<%
		for (TutorDataContract user : friendsTutor) {
	%>
	
	<div class="hero-unit" style="background-color:rgba(255,255,255,0.1);padding-top: 10px;">
		<div class="">
			<span style = "color:white">Name:  </span>:<a href="ProfileController?userId=<%=user.UserId%>"><%=user.Email%></a>
			<br> <span style = "color:white">Rating -</span>
			
			<span style = "color:yellow"><%
				for (int i = 0; i < user.RatingId; i++) {
			%>
			<i class="fa fa-star"></i>
			<%
				}
			%>
			</span>
		</div>
		<div class="">
			<form method='post' action="ActivityController?userId=<%=user.UserId%>&skillId=<%=user.SkillId%>">

				<textarea style="width: 300px; height: 150px;" type="text" name="message" placeholder="Please enter the message for tutor here"></textarea>
				
					<input class="btn-primary" name="submit" type="submit"
										value=Request />
			</form>
		</div>

	</div>
	<%
		}
	%>

	<%
		}
		}
	%>
	
	
		</div>
</body>
</html>

