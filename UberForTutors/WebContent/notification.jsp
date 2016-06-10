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
<body>
	<%
		ArrayList<ReqNotificationDataContract> activityList = (ArrayList<ReqNotificationDataContract>) request
				.getAttribute("activityList");

		//ArrayList<SkillsModel> recommendedSkillModels = (ArrayList<SkillsModel>) request.getAttribute("recommendedSkills");
	%>
	<%@include file="headerandfooter.jsp"%>
<div class = "hero-unit" style = "background:white;padding-bottom:5px">Your Notifications<hr></div>
	<%
		for (ReqNotificationDataContract activity : activityList) {
	%>
	

	<div class="hero-unit" style="">
		<%
			if (activity.IsDeleted == 0 && activity.Status == 0) {
		%>
		<div class = "span5">You received a notification from
		<%=activity.Email%></div>
		<div class = "span5"><form action="AcceptRejectController?ActivityId=<%=activity.activityId%>" method="post">
			<input type='submit' value='Accept' name='update'
				class="btn btn-primary btn-medium "> 
				<input type='submit'
				value='Reject' name='remove' class="btn btn-primary btn-medium ">
				
		</form></div>
		<%
			}
		%>
		
		<%
			if (activity.IsDeleted == 0 && activity.Status == 1) {
		%>
		<div class = "span5">You have already responded to
		<%=activity.userName%>'s
		
		request</div>
		<%
			}
		%>
		
		<%
			if (activity.IsDeleted == 1 && activity.Status == 1) {
		%>
		<div class = "span5">You have been rated for <%=activity.SkillName %> by <%=activity.userName %> 
		<%
			for (int i = 0; i < activity.RatingId; i++) {
		%>
		<i class="fa fa-star"></i>
		<%
			}
		%>
		</div>
		<%
			}
		%>
		<%
			if (activity.IsDeleted == 1 && activity.Status == 2) {
		%>
		<div class = "span5">You have rejected
		<%=activity.userName%>
		's request</div>
		<%
			}
		%>
	</div>
	<% } %>
</body>
</html>