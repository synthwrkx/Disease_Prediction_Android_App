<?php
if(isset($_POST['login']))
  {
	if($_POST['uname']==='admin')
	  {
		if($_POST['pwd']==='admin')
		  {
			header("Location: home.php");
		  }
        else
          {
            echo 'Invalid Username or Password';
          }
	  }
    else
      {
        echo 'Invalid Username or Password';
      } 
  }
?>

<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>Disease Prediction - Admin</title>
  <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.2.0/css/all.css'>
<link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.2.0/css/fontawesome.css'><link rel="stylesheet" href="./style.css">

</head>
<body>
<!-- partial:index.partial.html -->
<div class="container">
	<div class="screen">
		<div class="screen__content">
			<form class="login" method="POST" action="index.php">
				<h1 class="admin">Admin Login</h1>
				<div class="login__field">
					<i class="login__icon fas fa-user"></i>
					<input type="text" class="login__input" placeholder="Username" name="uname">
				</div>
				<div class="login__field">
					<i class="login__icon fas fa-lock"></i>
					<input type="password" class="login__input" placeholder="Password" name="pwd">
				</div>
				<button class="button login__submit" name="login">
					<span class="button__text">Log In</span>
					<i class="button__icon fas fa-chevron-right"></i>
				</button>				
			</form>
		</div>
	</div>
</div>
<!-- partial -->
</body>
</html>