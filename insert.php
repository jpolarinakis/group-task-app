<?php
function insert($project, $taskName, $taskPerson, $taskComment) 
{
$servername = "localhost";
$username = "username";
$password = "password";
$dbname = "myDB";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// prepare and bind
$stmt = $conn->prepare("INSERT INTO $project (taskID, taskName, taskPerson, taskDone, taskComment) VALUES (?, ?, ?, ?, ?)");
$result = mysql_query("SELECT max(id) FROM tablename");

if (!$result) {
    die('Could not query:' . mysql_error());
}

$id = mysql_result($result, 0, 'id');
$stmt->bind_param("issis",$id, $taskName, $taskPerson, 0, $taskComment);

echo "New records created successfully";

$stmt->close();
$conn->close();

}



?>