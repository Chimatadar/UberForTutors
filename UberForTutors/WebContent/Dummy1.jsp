<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="RankingController" >
<div class = "span5"></div>
   <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container-fluid">
                <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>

                </a>
                <a class="brand" > Uber <span style = "color:red">for Tutors</span></a>
              
            </div>
        </div>
    </div>
          
          <div style = "text-align:center" class="navbar navbar-inverse navbar-fixed-bottom">
        <div class="navbar-inner"><a class="brand" href="aboutus.jsp">About us</a>
            <div>
            <ul class="nav">
                
                <li><a href="PrivacyPolicy.jsp">Privacy Policy</a>
                </li>
                </ul>
                </div>
                <div class="pull-right">
                <ul class = "nav">
                <li><a href="#top">Back to Top</a>
                </li>
                
            </ul>
            </div>
        </div>
        </div>
<div class = "span1"></div>

<div style = "background-color:#17202A;color:#70DBDB;align:center" class="hero-unit span4">
                        <br>
                        Email: <input class="pull-right" style = "height:30px;weight:50px" type="text" placeholder="email" name="userName" >
                        <br>
                        <br>
                        Password:<input class="pull-right" style = "height:30px;weight:50px" type="password" placeholder="password" name="password" >
                        <br>
                        <br>
                        
                        <input type="submit" value="Submit" style = "color:black" class="btn">
                        <input type="reset" value="Reset" style = "color:black" class="btn">
</div>
<div class = "span4"></div>
</form>
</body>
</html>