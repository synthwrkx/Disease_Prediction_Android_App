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

    // Retrieve data from table
    $sql = "SELECT * FROM symptom";
    $result = mysqli_query($conn, $sql);

    $return_arr = array();

    if($result->num_rows > 0) {
        $return_arr['symptom'] = array();
        while($row = $result->fetch_array()) {
            array_push($return_arr['symptom'], array(
                'sno' => $row['sno'],
                'symp_name' => $row['symp_name']
            ));
        }
    }

    echo json_encode($return_arr);

    // Close connection
    mysqli_close($conn);
?>