
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Java to JSON Schema</title>
<meta name="description"
	content="Generate a JSON Schema out of your Java classes">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="js/ext/jquery.qtip.min.css" rel="stylesheet" type="text/css">
<script src="js/ext/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="js/ext/jquery.browser.js" type="text/javascript"></script>
<script src="js/ext/jquery.qtip.min.js" type="text/javascript"></script>
<script type="text/javascript">
	var pageName = "jjschema";
</script>
<script src="js/common.js" type="text/javascript"></script>

</head>
<body>
	<div class="horizMenu">
		<ul>
			<li>Select page:</li>
			<li><a href="index.jsp">Instance validation</a></li>
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

		<p>
			This page allows you to generate a JSON Schema out of a Java source
			code. Paste the source code into the text area, then press the <span
				style="font-family: monospace">Generate schema</span> button. Notes:
		</p>

		<ul>
			<li>it is safe to put static initializers in the code: they will
				not be executed;</li>
			<li>on failure (compilation errors), the compiler messages are
				displayed instead, with line and column information.</li>
		</ul>

		<p>
			Software used: <a href="https://github.com/reinert/JJSchema">JJSchema</a>.
		</p>

	</div>

	<form id="process" method="POST">
		<div id="left" class="content">
			<div class="horiz">
				<label for="input">Java source code:</label> <span
					class="error starthidden" id="input-invalid">Invalid JSON:
					parse error, <a id="input-link" href="#"></a>
				</span>
			</div>
			<textarea name="input" rows="20" cols="20" id="input"></textarea>
			<div class="horiz">
				<input type="submit" value="Generate schema"> <span>(<a
					id="load" href="#">load sample data</a>)
				</span>
			</div>
		</div>
	</form>

	<div id="right" class="content">
		<div class="horiz">
			<label for="results">Generation result:</label> <span
				class="error starthidden" id="processingFailure">failure</span> <span
				class="success starthidden" id="processingSuccess">success</span>
		</div>
		<textarea name="results" rows="20" cols="20" id="results"
			readonly="readonly"></textarea>
	</div>
</body>
</html>