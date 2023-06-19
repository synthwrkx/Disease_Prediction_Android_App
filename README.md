# Disease_Prediction_Android_App
Project on Disease Prediction Using Machine Learning implemented as an Android app.

Install Docker Desktop.
Install XAMPP.

FOLDER LOCATIONS:
  1) The files getdata.php, getnews.php and gettreatment.php goes into the htdocs folder of xampp.
  2) The folder "DiseasePredictionWeb" also goes into the "htdocs" folder of xampp.
  3) The folder "Disease Prediction Codes" can be kept anywhere on your system.
  4) The folder "DiseasePrediction" should be in your "AndroidStudioProjects" folder.

The machine learning algorithms are executed on a Docker container.

TO BUILD AND RUN DOCKER IMAGE:
  1) Go into the foder "Disease Prediction Codes" and launch a terminal or CMD there.
  2) Type (without quotes): "docker build -f Dockerfile.txt -t myapp ."
     and execute the command to build the Docker image.
  3) Type (without quotes): docker run -ti --rm -p 5000:5000 --name python-server myapp
     and execute the command to run the Docker container.
 
SETUP THE DATABASE AND HOST THE ADMIN PAGE:
 1) Run XAMPP Apache server and MySQL.
 2) Import the database file present in "DiseasePrediction" folder to phpmyadmin on XAMPP.
 3) Open your browser and paste this URL (without quotes) "http://localhost/DiseasePredictionWeb/".

RUN THE ANDROID APP:
  1) Install Android Studio.
  2) Open Android Studio and run the app.

  NOTE: The string file in resource folder of Android contain IP address assignments change it if needed. Also, change some IP addresses that are hardcoded within          the Java code if needed.
