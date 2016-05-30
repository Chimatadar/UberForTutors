<%@page import="java.util.ArrayList"%>
<%@page import="Model.*"%>

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
ArrayList<SkillsModel> skillsList = (ArrayList<SkillsModel>) request.getAttribute("skillsList");
	
	
//ArrayList<SkillsModel> recommendedSkillModels = (ArrayList<SkillsModel>) request.getAttribute("recommendedSkills");
%>
	<%@include file="headerandfooter.jsp" %>

	
	<%int k =0; 
	for(int i =0; i < Math.ceil(skillsList.size()/3.0);i+=1 ) { %>

	<div class="row-fluid">
	<% for(int j =0; j < 3;j+=1 ) { %>

		<div class="span4 hero-unit">
			<h2><%= skillsList.get(k).SkillName %></h2>

			<p></p>
			<p>
				<a class="btn"
					href="TutorController?sid=<%= skillsList.get(k).SkillId%>">
					Find Tutors &raquo;</a>
			</p>
		</div>
		<% k++;
		if (k>=skillsList.size())
		break;%>
		
		<% } %>

	</div>
	
	<% } %>


</body>
</html>