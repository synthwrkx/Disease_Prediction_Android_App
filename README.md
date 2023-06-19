# Disease_Prediction_Android_App
Project on Disease Prediction Using Machine Learning implemented as an Android app.

Install Docker Desktop.
Install XAMPP.

FOLDER LOCATIONS:
  1) The files getdata.php, getnews.php and gettreatment.php goes into the htdocs folder of xampp.
  2) The folder "DiseasePrediction" also goes into the "htdocs" folder of xampp.
  3) The folder "Disease Prediction Codes" can be kept anywhere on your system.

The machine learning algorithms are executed on a Docker container.

TO BUILD AND RUN DOCKER IMAGE:
  1) Go into the foder "Disease Prediction Codes" and launch a terminal or CMD there.
  2) Type (without quotes): "docker build -f Dockerfile.txt -t myapp ."
     and execute the command to build the Docker image.
  3) Type (without quotes): docker run -ti --rm -p 5000:5000 --name python-server myapp
     and execute the command to run the Docker container.
 
 SETUP THE DATABASE:
 1) Database file is present in DiseasePrediction
