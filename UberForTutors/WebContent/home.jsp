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
        }
        .sidebar-nav {
            padding: 9px 0;
        }
        .badge1 {
		   position:relative;
		}
		.badge1[data-badge]:after {
		   content:attr(data-badge);
		   position:absolute;
		   top:-10px;
		   right:-10px;
		   font-size:.7em;
		   background:green;
		   color:white;
		   width:18px;height:18px;
		   text-align:center;
		   line-height:18px;
		   border-radius:50%;
		   box-shadow:0 0 1px #333;
		}
    </style>
    <!-- TODO: make sure bootstrap-responsive.min.css points to BootTheme
        generated file -->
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-responsive.min.css" rel="stylesheet">
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
    <!-- Fav and touch icons -->
    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
</head>

<body style = "background-color: rgba(0, 0, 0, 1);">

<%
		ArrayList<CategoriesModel> categoryModels = (ArrayList<CategoriesModel>) request.getAttribute("categoryList");
		ArrayList<SkillsModel> recommendedSkillModels = (ArrayList<SkillsModel>) request.getAttribute("recommendedSkills");
	String name1 = (String)session.getAttribute("userName");
	String[] name = name1.split("@");
	%>
<%@include file="headerandfooter.jsp" %>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span3">
                <div class="well sidebar-nav" style = "border:0px;background-color:#17202A;color:white">
                    <ul class="nav nav-list">
                        <li class="nav-header">Recommended Skills</li>
 					<% if(recommendedSkillModels!=null){ 
 					for(SkillsModel user : recommendedSkillModels) { %>
                        
                        <li class="active">
                            <a href="TutorController?sid=<%= user.SkillId%>"><%= user.SkillName %></a>

                        </li>
                        <br>
                        <% } }%>
                    </ul>
                </div>
                <!--/.well -->
            </div>
            <!--/span-->
            <div class="span9">
                <div class="hero-unit" style = "background-color:#17202A;color:white">
                    <h1 >Hello, <%=  name[0]%></h1>

                    <p>Brief intro<p>
                        <a class="btn btn-primary btn-large">Learn more &raquo;</a>
                    </p>
                </div>
<!--                 <div class="row-fluid">
 -->                    
 				<div class="row-fluid">
                    <div class="span4 hero-unit" style = "background-color:#17202A;color:white">
                    <div class = "hero-unit" style = 
                    "background-image:url(http://www.mccormick.northwestern.edu/eecs/computer-science/images/featured-image/detecting-malicious-ads.jpg)"></div>
                        <div  style = "padding:0px;background-color:#17202A;color:white" class = "hero-unit"><h4><%= categoryModels.get(0).CategoryName %></h4>

                        <p></p>
                        <p>
                            <a class="btn" href="skillsListPage?categoryId=<%= categoryModels.get(0).CategoryId %>">View details &raquo;</a>
                        </p>
                        </div>
                    </div>
                    <!--/span-->
                    <div class="span4 hero-unit" style = "background-color:#17202A;color:white">
                    <div class = "hero-unit" style = 
                    "background-image:url(http://chs.conroeisd.net/25C872A0-00870B2F.0/arts%20and%20humanities.jpg)"></div>
                        <div  style = "padding:0px;background-color:#17202A;color:white" class = "hero-unit"><h4><%= categoryModels.get(1).CategoryName %></h4>

                        <p></p>
                        <p>
                            <a class="btn" href="skillsListPage?categoryId=<%= categoryModels.get(1).CategoryId %>">View details &raquo;</a>
                        </p>
                        </div>
                    </div>
                    <!--/span-->
                    <div class="span4 hero-unit" style = "background-color:#17202A;color:white">
                    <div class = "hero-unit" style = 
                    "background-image:url(http://chs.conroeisd.net/25C872A0-00870B2F.0/arts%20and%20humanities.jpg)"></div>
                        <div  style = "padding:0px;background-color:#17202A;color:white" class = "hero-unit"><h4><%= categoryModels.get(2).CategoryName %></h4>

                        <p></p>
                        <p>
                            <a class="btn" href="skillsListPage?categoryId=<%= categoryModels.get(2).CategoryId %>">View details &raquo;</a>
                        </p>
                        </div>
                    </div>
                    <!--/span-->
                </div>
                <div class="row-fluid">
                    <div class="span4 hero-unit" style = "background-color:#17202A;color:white">
                    <div class = "hero-unit" style = 
                    "background-image:url(http://chs.conroeisd.net/25C872A0-00870B2F.0/arts%20and%20humanities.jpg)"></div>
                        <div  style = "padding:0px;background-color:#17202A;color:white" class = "hero-unit"><h4><%= categoryModels.get(3).CategoryName %></h4>

                        <p></p>
                        <p>
                            <a class="btn" href="skillsListPage?categoryId=<%= categoryModels.get(3).CategoryId %>">View details &raquo;</a>
                        </p>
                        </div>
                    </div>
                    <!--/span-->
                    <div class="span4 hero-unit" style = "background-color:#17202A;color:white">
                    <div class = "hero-unit" style = 
                    "background-image:url(http://chs.conroeisd.net/25C872A0-00870B2F.0/arts%20and%20humanities.jpg)"></div>
                        <div  style = "padding:0px;background-color:#17202A;color:white" class = "hero-unit"><h4><%= categoryModels.get(4).CategoryName %></h4>

                        <p></p>
                        <p>
                            <a class="btn" href="skillsListPage?categoryId=<%= categoryModels.get(4).CategoryId %>">View details &raquo;</a>
                        </p>
                        </div>
                    </div>
                    <!--/span-->
                    <div class="span4 hero-unit" style = "background-color:#17202A;color:white">
                    <div class = "hero-unit" style = 
                    "background-image:url(http://chs.conroeisd.net/25C872A0-00870B2F.0/arts%20and%20humanities.jpg)"></div>
                        <div  style = "padding:0px;background-color:#17202A;color:white" class = "hero-unit"><h4><%= categoryModels.get(5).CategoryName %></h4>

                        <p></p>
                        <p>
                            <a class="btn" href="skillsListPage?categoryId=<%= categoryModels.get(5).CategoryId %>">View details &raquo;</a>
                        </p>
                        </div>
                    </div>
                    <!--/span-->
                </div>
                <!--/row-->
                    
                    
                    <!--/span-->
                    
                    <!--/span-->

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
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js">
    </script>
    <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
</body>


</html>
