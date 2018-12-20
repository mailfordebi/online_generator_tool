
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Liquibase xml to Database script</title>
<meta name="description"
	content="Generate Database script out of your Liquibase xml">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="js/ext/jquery.qtip.min.css" rel="stylesheet" type="text/css">
<script src="js/ext/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="js/ext/jquery.browser.js" type="text/javascript"></script>
<script src="js/ext/jquery.qtip.min.js" type="text/javascript"></script>
<script type="text/javascript">
	var pageName = "script";
</script>
<script src="js/common.js" type="text/javascript"></script>
</head>

<body>
	<div class="horizMenu">
		<ul>
			<li ><a href="index.jsp">Instance validation</a></li>
			<li><a href="syntax.jsp">Schema syntax validation</a></li>
			<li><a href="jjschema.jsp">Java to JSON Schema</a></li>
			<li><a href="schema2pojo.jsp">JSON Schema to Java</a></li>
			<li><a href="jsonpatch.jsp">JSON Patch</a></li>
			<li><a href="avro.jsp">Avro to JSON Schema</a></li>
			<li><a href="about.jsp">About this site</a></li>
		</ul>
	</div>

	<div id="top">
		<div class="noscript">
			<p>
				<span style="font-weight: bold">This site requires Javascript
					to run correctly</span>
			</p>
		</div>

	</div>

	<form id="process1" method="POST" action="/format">
		<div id="left" class="content">
			<div class="horiz">
				<label for="input" class="label info">JSON:</label>
			</div>
			<textarea name="input" rows="20" cols="20" id="input">${inputReq}</textarea>
			<div class="horiz">
				<div class="right">
					<input type="submit" value="Format JSON" style="margin-right: 30px">
				</div>
			</div>
		</div>
	</form>

	<div id="right" class="content">
		<div class="horiz">
			<label class="label success">Formatted JSON:</label> <span
				class="error starthidden" id="processingFailure">failure</span> <span
				class="success starthidden" id="processingSuccess">success</span>
		</div>
		<textarea name="results" rows="20" cols="20" id="results"
			readonly="readonly">${result}</textarea>
	</div>
</body>


</html>