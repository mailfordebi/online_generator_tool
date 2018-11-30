
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>SOAPUI Client</title>
<meta name="description" content="SOAPUI Client">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="https://json-schema-validator.herokuapp.com/css/style.css"
	rel="stylesheet" type="text/css">


<link
	href="https://json-schema-validator.herokuapp.com/js/ext/jquery.qtip.min.css"
	rel="stylesheet" type="text/css">
<script
	src="https://json-schema-validator.herokuapp.com/js/ext/jquery-1.9.1.min.js"
	type="text/javascript"></script>
<script
	src="https://json-schema-validator.herokuapp.com/js/ext/jquery.browser.js"
	type="text/javascript"></script>
<script
	src="https://json-schema-validator.herokuapp.com/js/ext/jquery.qtip.min.js"
	type="text/javascript"></script>
<!-- <script type="text/javascript">
	var pageName = "jjschema";
</script> -->
<script src="https://json-schema-validator.herokuapp.com/js/common.js"
	type="text/javascript"></script>

</head>
<body>
	<form id="inputwsdl" action="processwsdl" method="post">
		<div id="top">
			<input type="text" name="wsdlName" size="60" value="WSDl here..">
			<input type="submit" value="Submit">
		</div>
	</form>
	<form id="process" action="callService" method="post">
		<div id="left" class="content">
			<div class="horiz">
				<label for="input">SOAP Request</label>
			</div>
			<textarea name="input" rows="20" cols="20" id="input">${inputReq}</textarea>
			<div class="horiz">
				<input type="submit" value="Submit">
			</div>
		</div>
	</form>

	<div id="right" class="content">
		<div class="horiz">
			<label for="results">SOAP Response</label>
		</div>
		<textarea name="results" rows="20" cols="20" id="results"
			readonly="readonly">${result}</textarea>
	</div>

</body>
</html>
