<html>
<head>
<script type="text/javascript">
function login(){
	var Name=document.getElementById("name").value;
	var Id=document.getElementById("id").value;
		var jsonObject={
			"Name" : Name,
			"Id" : Id
		}
		var json = JSON.stringify(jsonObject);
		alert(json);
		
		if (window.XMLHttpRequest) {
			request = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			request = new ActiveXObject("MicroSoft.XMLHTTP");
		}
		try {
			request.onreadystatechange = retrieveData;
			request.open("POST", 'userprofile.com' , true);
			request.setRequestHeader("Content-type", "application/json");
			request.send(json);
		} catch (e) {
			alert("Unable to connect server");
		}
	}
	function retrieveData() {
		if (request.readyState == 4 && request.status == 200) {
			//var JSONObject = JSON.parse(request.responseText);
			alert("Ya Nice Work");
			}
		}
</script>
</head>
<body>
	<div>
		<form style="height: 132px;">
			<br><table><tr><td>Name</td><td><input type="text" id="name"></td></tr>
			<tr><td>Id</td><td><input type="text" id="id"></td></tr></table>
			<input type="button" value="Login" onclick="login()"><br>
		</form>
	</div>
</body>
</html>
