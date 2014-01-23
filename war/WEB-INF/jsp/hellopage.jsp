<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- The HTML 4.01 Transitional DOCTYPE declaration-->
<!-- above set at the top of the file will set     -->
<!-- the browser's rendering engine into           -->
<!-- "Quirks Mode". Replacing this declaration     -->
<!-- with a "Standards Mode" doctype is supported, -->
<!-- but may lead to some differences in layout.   -->

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>Hello App Engine</title>
<script type="text/javascript">
var request;
	function call() {
	var Name=document.getElementById("name").value;
	var Age=document.getElementById("age").value;
	var Id=document.getElementById("id").value;
		var jsonObject ={
			"Name" : Name,
			"Age" : Age,
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
			request.open("POST", 'register.com' , true);
			request.setRequestHeader("Content-type", "application/json");
			request.send(json);
		} catch (e) {
			alert("Unable to connect server");
		}
	}
	function retrieveData() {
		if (request.readyState == 4 && request.status == 200) {
			var JSONObject = JSON.parse(request.responseText);
			alert("good");
			}
		}
</script>
</head>

<body>
	<h1>Hello App Engine!</h1>

	<table>
		<tr>
			<td colspan="2" style="font-weight: bold;"><div
					style="height: 104px; width: 592px; background-color: Aqua">
					<form
						style="background-color: Aqua; vertical-align: middle; letter-spacing: normal; text-align: right; word-spacing: normal; width: 413px">
						name: <input type="text" id="name"><br>age:<input
							type="text" id="age"><br>Id:<input type="text"
							id="id"><br>
						<input type="button" value="Register" name="Register" onclick="call()"><br>
					</form>
				</div></td>
		</tr>
	</table>

</body>
</html>
