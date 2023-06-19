<?php
    $host = "localhost"; // Host name
    $username = "root"; // Mysql username
    $password = ""; // Mysql password
    $db_name = "diseaseprediction"; // Database name

    // Connect to server and select databse.
    $conn = mysqli_connect($host, $username, $password, $db_name);

    // Check connection
    if (!$conn) {
        die("Connection failed: " . mysqli_connect_error());
    }

    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
        // get the value passed from the Android app
        $disease = $_POST['disease'];
    }

    // Retrieve data from table
    $sql = "SELECT * FROM treatment WHERE disease='$disease'";
    $result = mysqli_query($conn, $sql);

    $return_arr = array();

    if($result->num_rows >=0) {
        $return_arr['treatment'] = array();
        while($row = $result->fetch_array()) {
            array_push($return_arr['treatment'], array(
                'disease' => $row['disease'],
                'treat' => $row['treat']
            ));
        }
    }
    echo json_encode($return_arr);
    // Close connection
    mysqli_close($conn);
?>