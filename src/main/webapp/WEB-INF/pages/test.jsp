
<!DOCTYPE html>
<html lang="en">
<head>
<title>JSON to JAVA Converter: Convert JSON to JAVA format</title>
<meta http-equiv="content-language" content="en-US">
<meta http-equiv="X-UA-Compatible" content="IE=9,chrome=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta charset="utf-8" />
<meta name="description"
	content="Best Online Liquibase to DataBase Script Converter: Convert Liquibase XML to DataBase Script format" />
<meta name="author" content="Debi Prasad Pradhan" />
<meta name="keywords"
	content="liquibase xml to database script, Liquibase XML to DataBase Script" />
<meta property="og:image"
	content="https://codebeautify.org/img/cb/logo.png" />
<meta property="og:url"
	content="https://codebeautify.org/json-to-java-converter" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=10">
<!-- <link href="https://plus.google.com/111146553686540738855"
	rel="publisher" /> -->
<link
	href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href="/img/cb.png" rel="icon" />
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css" />
<link href="css/formatter.css" rel="stylesheet" />
<!-- <script src="//ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"
	type="c797d4ba6821fe42fe5509c8-text/javascript"></script> -->
<input type="hidden" id="viewName" value="json-to-java-converter">
<input type="hidden" id="isLogin" value="">
<input type="hidden" id="isFavTool" value="not-fav" />
</head>
<body id="cbBody">
	<script type="c797d4ba6821fe42fe5509c8-text/javascript">

$(document).ready(function(){
	    $("body").addClass("intro");
	});

</script>
  <!-- Google ads
  <script type="c797d4ba6821fe42fe5509c8-text/javascript">
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-51765955-1', 'codebeautify.org');
  ga('send', 'pageview');

</script> -->
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<div class='mainheader'>
					<div class='logo'>
						<a href="/" class="brand">
							<div class="cblogoimage" title="Code Beautify"></div>
						</a>
					</div>
					<div class='headerMenu'>
						<div class="nav-collapse collapse">
							<ul class="nav">
								<li class="dropdown pull-right margin-last-li"><a
									href="https://jsonformatter.org" target="_blank"
									title="JSON Formatter">&nbsp;JSON Formatter</a></li>
								<li class="dropdown pull-right"><a
									href="/ipcheck-weatherinfo-latestnews">&nbsp;My Ip</a></li>
								<li class="dropdown pull-right"><a href="/search">Search</a>
								</li>
								<li class="dropdown pull-right"><a href="/recentLinksPage">Recent
										Links</a></li>
								<li class="dropdown pull-right"><a href="/code"
									class="dropdown-toggle" data-toggle="dropdown">Sample</a></li>
								<li class="dropdown pull-right" id="moreMenu"><a href='#'
									id='more'>More <b class="caret"></b></a></li>
								<li>
								<li id="loggedin" class="dropdown" onclick="toggleOpenClass()"
									style="display: none"><a id="username" class="dropbtn"
									data-toggle="dropdown"> <b id="usernamelable"
										class="dropbtn"></b> <b class="caret"></b>
								</a>
									<div id="myDropdown" class="dropdown-content">
										<a class="leftText" style="float: none"
											href="https://codebeautify.org/userSaveLinkPage">Links</a> <a
											class="leftText"
											href="https://codebeautify.org/userSaveOldLinkPage">Archived</a>
										<a class="leftText" href="/profile">Profile</a> <a
											class="leftText" href="/myFavouriteTools">Favourites</a> <a
											class="leftText" href="/login/logout">Logout</a>
									</div></li>
								</li>
								<li id="notloggedin"><a href="/login" rel="nofollow">Sign
										in</a></li>
							</ul>
							<div id="copy-note-msg" class="hide">Copied to Clipboard.</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="some_other_box"></div>
	<div class="containe-fluid headerEditorContainer">
		<div class="row-fluid">
			<div class="span12 subTitleDiv">
				<div class="span6">
					<h1 id="subTitle" title="JSON to JAVA Converter">JSON to JAVA
						Converter</h1>
				</div>
				<div class="span6" id="permalinkDiv">
					<div class='permalinkButtonDiv'>
						<input type="button" value="Save & Share"
							class="btn permalinkButton btn-inverse "
							onclick="openSavedialog(true);" id='plinkBtn' />
					</div>
					<div class='permalinkDiv'>
						<label id="permalink" class='permaLinkLabel'> </label>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="fbpost" class='hide'>
		<div id="shareDiv"></div>
	</div>
	<div id="innerBody" class="innerBody setBgImage">
		<div class="containe-fluid viewerInnerBodyDiv">
			<div class="row-fluid back">
				<div class="span12 viewerInnerBodyDiv1">
					<div class="span5 mainLeftInputDiv" id="mainLeftDiv">
						<div class="span12 mainLeftInputDiv1">
							Input <a id="fs" class="fullScreen" href="#"
								onclick="fullScreenLeft();">Full Screen</a> <a id="clearImg"
								class='clearImage' href="#">Clear</a>
						</div>
						<div class="span12 viewerInnerBodyDiv1">
							<div id="editor" class='viewerEditor'></div>
						</div>
					</div>
					<div class="span2 center buttonSection">
						<input type="button" title="Load From Url" value="Load Url"
							onclick="loadFromURL('json');" class="btn"><br> <input
							type="button" value="open" id="me"
							onclick="openFile('me','json')" class="btn" title="OpenFile"><br>
						<input type="button" value="JSON TO JAVA"
							title="Convert JSON TO JAVA" onclick="convertJsonToJava();"
							class="btn"><br> <input type="button"
							value="Download" onclick="createFile('java');" class="btn"
							title="Download Result">
					</div>
					<div class="span5 mainLeftInputDiv" id="mainRightDiv">
						<div class="span12 mainLeftInputDiv1">
							<span id="outputMsg">Result</span> <a id="fs1" class='fullScreen'
								href="#" onclick="fullScreenRight();">Full Screen</a>
						</div>
						<div class="span12 mainRightInputDiv2">
							<div id="result" class='viewerEditor'></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid infoSection">
		<div class="row-fluid footerpart">
			<div class="span12">
				<div class="span6 infoDiv">
					<h3>What can you do with JSON TO JAVA CONVERTER ?</h3>
					<ul class='infoFeatureDiv'>
						<li>This tool will help you to convert your JSON String/Data
							to JAVA Class Object</li>
						<li>To Save and Share this code, use Save and Share button</li>
					</ul>
				</div>
				<div class="span6 infoTools" id="relatedTools">
					<h3>Related Tools</h3>
					<ul>
						<li><a href=" /xml-to-java-converter">XML to JAVA
								Converter </a></li>
						<li><a href=" /yaml-to-json-xml-csv">YAML to JSON-XML-CSV
								Converter </a></li>
						<li><a href=" /xml-to-yaml">XML to YAML Converter </a></li>
						<li><a href=" /json-to-yaml">JSON to YAML Converter </a></li>
						<li><a href=" /xmltojson">XML to JSON Converter </a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<script src="js/common_load_with_home.js"
		type="c797d4ba6821fe42fe5509c8-text/javascript"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/ace/1.3.3/ace.js"
		type="c797d4ba6821fe42fe5509c8-text/javascript"></script>
	<script src="js/json2convert.js"
		type="c797d4ba6821fe42fe5509c8-text/javascript"></script>
	<script src="js/vkbeautify.js"
		type="c797d4ba6821fe42fe5509c8-text/javascript"></script>
	<script src="js/convertToJava.js"
		type="c797d4ba6821fe42fe5509c8-text/javascript"></script>
	<script
		src="//cdnjs.cloudflare.com/ajax/libs/js-beautify/1.7.5/beautify.min.js"
		type="c797d4ba6821fe42fe5509c8-text/javascript"></script>
	<script type="c797d4ba6821fe42fe5509c8-text/javascript">

    $(document).ready(function() {
        //called after everything loaded
        setDefaultData();
        conditionalCode();
        $("#me").click();
    });

</script>
	<input id="fContent" type="hidden" value="" />
	<input id="inputvalue" type="hidden" value="" />
	<input id="fUrl" type="hidden" value="" />
	<input id="fTitle" type="hidden"
		value="JSON to JAVA Converter: Convert JSON to JAVA format" />
	<input id="fValue" type="hidden" value="" />
	<input id="loginWith" type="hidden" value="" />
	<div id="openError" class='hide'></div>
	<div id="loadUrlPathDiv" class='hide'>
		<input type="text" id="path" class='urlPath'
			placeholder="Paste your url">
	</div>
	<div id="savedialog" class="hide">
		<input type="hidden" id="edit_link_id" value="" />
		<table>
			<tr>
				<td>Title</td>
				<td><input type="text" maxlength="60" id="save_link_title"
					value="" /></td>
			</tr>
			<tr>
				<td>Description</td>
				<td><textarea id="save_link_description" placeholder="optional"></textarea></td>
			</tr>
			<tr>
				<td>Tags</td>
				<td><textarea id="save_link_tags" placeholder="optional"></textarea></td>
			</tr>
		</table>
	</div>
	<div id="gdriveDialog" class='hide'>
		<div id="savetodrive-div"></div>
	</div>
	<div style="display: none" id="exportToExcelDiv"></div>
	<script
		src="https://ajax.cloudflare.com/cdn-cgi/scripts/2448a7bd/cloudflare-static/rocket-loader.min.js"
		data-cf-nonce="c797d4ba6821fe42fe5509c8-" defer=""></script>
</body>
</html>