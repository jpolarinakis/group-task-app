--This is a list of SQL commands that we wish to execute
--This list may be incomplete and I'm not sure if they work
/*
The first command is to check if the user is logged in. This will require us to have the username of whoever is logging in

CREATE FUNCTION CalcIncome ( starting_value INT )
RETURNS INT

BEGIN

   DECLARE income INT;

   SET income = 0;

   label1: WHILE income <= 3000 DO
     SET income = income + starting_value;
   END WHILE label1;

   RETURN income;

END; //

DELIMITER ;

Example function that I'm going to use as a basis for writing my code!
*/

CREATE FUNCTION FindUser ( name CHAR, pw CHAR)
RETURN TINYINT

BEGIN
SELECT * FROM users
WHERE username = name AND password = pw 
-- I'm not sure how to do this, so I'll look later
/*
This fuction inserts a new user into the users table
*/
CREATE FUNCTION insertUser (un CHAR, pw CHAR, em CHAR)
	INSERT INTO users
			(usernames, email, password)
			VALUES
			(un, em, pw);
--------------- Maybe way #2---------------------------
CREATE FUNCTION insertUser (un char, pw char, em char)
	@vun = un;
	@vpw = pw;
	@em  = em;
	INSERT INTO users (usernames, email, password)
						VALUES
						(@vun, @vpw,@em);
----------------------------------------------------------
/*
basically the rest of the inserts will work the same way, but with different table names, and table IDs
The most important part is to test all of this. 
*/

/*
Create a table
*/

$name = mysql_real_escape_string($_POST['name']);
mysql_query("CREATE TABLE `".$name."` ( task CHAR, assigned CHAR, done TINYINT, notes CHAR, deadline CHAR)");

--I think this uses php instead of MySQL---------------------------------------------------
