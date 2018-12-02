
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>SOAPUI Client</title>
<meta name="description" content="SOAPUI Client">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/style.css"
	rel="stylesheet" type="text/css">


<link
	href="js/ext/jquery.qtip.min.css"
	rel="stylesheet" type="text/css">
<script
	src="js/ext/jquery-1.9.1.min.js"
	type="text/javascript"></script>
<script
	src="js/ext/jquery.browser.js"
	type="text/javascript"></script>
<script
	src="js/ext/jquery.qtip.min.js"
	type="text/javascript"></script>
<!-- <script type="text/javascript">
	var pageName = "jjschema";
</script> -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <script src="js/common.js"
	type="text/javascript"></script> -->
<script>
	function fillSoapReq(data) {
		document.getElementById("soapReq").value = data;
	}

	function selectEndpoint(value) {
		debugger;
		document.getElementById("endPoint").value = value;
	}

	function showOthers() {
		document.getElementById("showOthers").style.display = 'block';
	}
</script>
</head>
<body>
	<form id="inputwsdl" action="processwsdl" method="post">
		<div id="top">
			<input type="text" name="wsdlName" size="60" value="WSDl here..">
			<input type="submit" value="Validate">
			<br> <br>
				<c:forEach items="${serviceMapDetails}" var="op">
					<input type="radio" name="opName" id="opName"
						onclick="fillSoapReq(this.value);showOthers()" value="${op.value}">${op.key}
				</c:forEach>
				<br>
				<div id="showOthers" style="display: none;">
				<label for="input" id="endPointLabel">End Point	URL:</label> 
				<select id="urls" name="urls" onchange="selectEndpoint(this.value);">
					<c:forEach items="${serviceNames}" var="sn">
						<option value="${sn}">${sn}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</form>
	<form id="process" action="callService" method="post">
		<div id="left" class="content">
			<div class="horiz">
				<label for="input">SOAP Request</label>
			</div>
			<input type="hidden" id="endPoint" name="endPoint"
				value="${serviceNames[0]}">
			<textarea name="soapReq" rows="20" cols="20" id="soapReq">${inputReq}</textarea>
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
