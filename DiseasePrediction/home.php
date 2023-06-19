<?php
if(isset($_POST['logout']))
{
  header("Location: index.php");
}

if(isset($_POST['news_submit']))
  {
    include 'db_cnct.php';
    $name = $_POST['news_nm'];
    $desc = $_POST['news_de'];

    $sql = "INSERT INTO news(news_name,news_desc) VALUES('$name','$desc')";

    if(!mysqli_query($conn, $sql))
      {
        die("Error inserting data.");
      }
  }

if(isset($_POST['symp_submit']))
  {
    include 'db_cnct.php';
    $name = $_POST['symp_nm'];
    $desc = $_POST['symp_de'];

    $sql = "INSERT INTO symptom(symp_name,symp_desc) VALUES('$name','$desc')";

    if(!mysqli_query($conn, $sql))
      {
        die("Error inserting data.");
      }
  }

if(isset($_POST['treat_submit']))
  {
    include 'db_cnct.php';
    $name = $_POST['treat_nm'];
    $desc = $_POST['treat_de'];

    $sql = "INSERT INTO treatment(disease,treat) VALUES('$name','$desc')";

    if(!mysqli_query($conn, $sql))
      {
        die("Error inserting data.");
      }
  }
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Disease Prediction - Admin</title>

    
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css" type="text/css">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#" style="margin-right: 62em;">Disease Prediction</a>
        <form class="form-inline my-2 my-lg-0" method="POST" action="home.php">
            <button class="btn btn-outline-success my-2 my-sm-0" name="logout" type="submit">Logout</button>
        </form>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
    </nav>

    <h1 class="text-center p-5 text-primary">Disease Prediction Admin</h1>

    <div class="container intro">
        <div class="row intro">
            <div class="col-md-4">
                <form method="POST" action="home.php">
                    <div class="form-group">
                        <label for="exampleInputEmail1">News</label>
                        <input type="text" name="news_nm" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter News">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Enter News Details</label>
                        <textarea type="text" name="news_de" class="form-control" id="exampleInputPassword1" placeholder="News Details" rows="4" cols="50"></textarea>
                    </div>
                    <button type="submit" name="news_submit" class="btn btn-primary">Add News</button>
                </form>
            </div>

            <div class="col-md-4">
                <form method="POST" action="home.php">
                    <div class="form-group">
                        <label for="exampleInputEmail1">Symptom</label>
                        <input type="text" name="symp_nm" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter Symptom">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Enter Symptom Description</label>
                        <textarea type="text" name="symp_de" class="form-control" id="exampleInputPassword1" placeholder="Symptom Details" rows="4" cols="50"></textarea>
                    </div>
                    <button type="submit" name="symp_submit" class="btn btn-primary">Add Symptom</button>
                </form>
            </div>

            <div class="col-md-4">
                <form method="POST" action="home.php">
                    <div class="form-group">
                        <label for="exampleInputEmail1">Treatment</label>
                        <input type="text" name="treat_nm" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter Disease">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Enter Treatment Description</label>
                        <textarea type="text" name="treat_de" class="form-control" id="exampleInputPassword1" placeholder="Treatment Details" rows="4" cols="50"></textarea>
                    </div>
                    <button type="submit" name="treat_submit" class="btn btn-primary">Add Treatment</button>
                </form>
            </div>

        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>