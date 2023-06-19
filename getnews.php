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
    $sql = "SELECT * FROM news WHERE ((SELECT COUNT(*) FROM news) - sno)<20";
    $result = mysqli_query($conn, $sql);

    $return_arr = array();

    if($result->num_rows > 0) {
        $return_arr['disease'] = array();
        while($row = $result->fetch_array()) {
            array_push($return_arr['disease'], array(
                'sno' => $row['sno'],
                'dise_name' => $row['news_name'],
                'dise_desc' => $row['news_desc']
            ));
        }
    }

    echo json_encode($return_arr);

    // Close connection
    mysqli_close($conn);
?>