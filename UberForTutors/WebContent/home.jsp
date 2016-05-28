
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

<body>

<%
		ArrayList<CategoriesModel> categoryModels = (ArrayList<CategoriesModel>) request.getAttribute("categoryList");
		ArrayList<SkillsModel> recommendedSkillModels = (ArrayList<SkillsModel>) request.getAttribute("recommendedSkillIds");
	%>
<div class="navbar navbar-fixed-bottom">
        <div class="navbar-inner">
            <div class="container-fluid">
                <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>

                </a>
                <a class="brand" href="#">Uber For Tutors</a>
                <div class="nav-collapse collapse">
                    
                    <ul class="nav">
                        <li class="active">
                            <a href="#">Home</a>
                        </li>
                        <li>
                            <a href="#about">Privacy Policy</a>
                        </li>
                        <li>
                            <a href="#contact">Log Out</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>



    <div class="navbar navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container-fluid">
                <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>

                </a>
                <a class="brand" href="#">Uber For Tutors</a>
                <div class="nav-collapse collapse">
                    <p class="navbar-text pull-right">Logged in as                 
                     <a href="profile.jsp" class="badge1 navbar-link" data-badge="1">Username</a></p>
                     
                    <ul class="nav">
                        <li class="active">
                            <a href="#">Home</a>
                        </li>
                        <li>
                            <a href="#about">About</a>
                        </li>
                        <li>
                            <a href="#contact">Contact</a>
                        </li>
                        <li>
                        &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</li>
                        <li>
                        <form class="navbar-form" action="HomeController" method="post">
                        <input class="span5" type="text" placeholder="search a skill" name="searchSkill">
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
                <div class="well sidebar-nav" style = "background-color:#17202A;color:white">
                    <ul class="nav nav-list">
                        <li class="nav-header">Recommended Skills</li>
 					<% for(SkillsModel user : recommendedSkillModels) { %>
                        
                        <li class="active">
                            <a href="SkillController?sid=<%= user.SkillId%>"><%= user.SkillName %></a>

                        </li>
                        <% } %>
                    </ul>
                </div>
                <!--/.well -->
            </div>
            <!--/span-->
            <div class="span9">
                <div class="hero-unit" style = "background-color:#17202A;color:white">
                    <h1 >Hello, "Insert user here>!"</h1>

                    <p>Brief intro<p>
                        <a class="btn btn-primary btn-large">Learn more &raquo;</a>
                    </p>
                </div>
<!--                 <div class="row-fluid">
 -->                    
 				<div class="row-fluid">
                    <div class="span4 hero-unit" style = "background-color:#17202A;color:white">
                        <h3><%= categoryModels.get(0).CategoryName %></h3>

                        <p></p>
                        <p>
                            <a class="btn" href="#">View details &raquo;</a>
                        </p>
                    </div>
                    <!--/span-->
                    <div class="span4 hero-unit">
                        <h3><%= categoryModels.get(1).CategoryName %></h3>
                        <p></p>
                        <p>
                            <a class="btn" href="#">View details &raquo;</a>
                        </p>
                    </div>
                    <!--/span-->
                    <div class="span4 hero-unit">
                        <h3><%= categoryModels.get(2).CategoryName %></h3>

                        <p></p>
                        <p>
                            <a class="btn" href="#">View details &raquo;</a>
                        </p>
                    </div>
                    <!--/span-->
                </div>
                <div class="row-fluid">
                    <div class="span4 hero-unit" style = "background-color:#17202A;color:white">
                        <h3><%= categoryModels.get(3).CategoryName %></h3>

                        <p></p>
                        <p>
                            <a class="btn" href="#">View details &raquo;</a>
                        </p>
                    </div>
                    <!--/span-->
                    <div class="span4 hero-unit">
                        <h3><%= categoryModels.get(4).CategoryName %></h3>
                        <p></p>
                        <p>
                            <a class="btn" href="#">View details &raquo;</a>
                        </p>
                    </div>
                    <!--/span-->
                    <div class="span4 hero-unit">
                        <h3><%= categoryModels.get(5).CategoryName %></h3>

                        <p></p>
                        <p>
                            <a class="btn" href="#">View details &raquo;</a>
                        </p>
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