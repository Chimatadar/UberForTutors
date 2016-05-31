
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Sign up</title>

        <!-- CSS -->
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="assets/css/form-elements.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css">
        

        <!-- Favicon and touch icons -->
        <link rel="shortcut icon" href="assets/ico/favicon.png">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">

    </head>

    <body>

        <!-- Top content -->
        <div class="top-content">
        	
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                            <h1><strong>Uber</strong> For Tutors</h1>
                            <div class="description">
                            	<p>
	                            	</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                        	<div class="form-top">
                        		<div class="form-top-left">
                        			<h3>Register to our site</h3>
                            		<p>Enter your following details to sign up:</p>
                        		</div>
                        		<div class="form-top-right">
                        			<i class="fa fa-lock"></i>
                        		</div>
                            </div>
                            <div class="form-bottom">
			                    <form role="form" action="SignupController" method="post" class="login-form">
			                    <div class="form-group">
			                    		<label class="sr-only">Username</label>
			                        	<input type="text" name="userName" placeholder="Username..." class="form-control" >
			                        </div>
			                    	<div class="form-group">
			                    		<label class="sr-only">Email</label>
			                        	<input type="text" name="email" placeholder="Email..." class="form-control" >
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" >Password</label>
			                        	<input type="password" name="password" placeholder="Password..." class="form-control" >
			                        </div>
			                        <div class="form-group" style = "padding-left:0px">
			                        	<select class="form-group col-sm-12 selectpicker" multiple name="language"">
										  <option style = "background-color:#de615e ;padding-bottom:10px">English</option>
										  <option style = "background-color:#de615e;padding-bottom:10px">Gujarati</option>
										  <option style = "background-color:#de615e ;padding-bottom:10px">Hindi</option>
									      <option style = "background-color:#de615e ;padding-bottom:10px">Kannada</option>
									      <option style = "background-color:#de615e ;padding-bottom:10px">Chinese</option>
									      <option style = "background-color:#de615e ;padding-bottom:10px">Spanish</option>
									      
										</select>
										</div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="form-address">Address</label>
			                        	<textarea rows="4" cols="50"  name="address" 
			                        	placeholder="Type yourAddress Here...." class="form-control"></textarea>
			                        </div>
			                        <button type="submit" class="btn">Sign up!</button>
			                    </form>
		                    </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 social-login">
                        	<h3>Returning User? </h3>
                        	<div class="social-login-buttons">
	                        	<a class="btn btn-link-2" href="signUp.jsp">
	                        		<i class="fa fa-user-plus"></i> Sign In Here
	                        	</a>
	                        	
                        	</div>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>


        <!-- Javascript -->
        <script src="assets/js/jquery-1.11.1.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/jquery.backstretch.min.js"></script>
        <script src="assets/js/scripts.js"></script>
        <!-- Latest compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>

<!-- (Optional) Latest compiled and minified JavaScript translation files -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/i18n/defaults-*.min.js"></script>
        
        
        
        <!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->

    </body>

</html>