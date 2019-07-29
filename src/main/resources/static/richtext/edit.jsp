
<!doctype html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>HTML Editor</title>
<link rel="stylesheet" href="richtext/docs.css">
<link rel="stylesheet" href="richtext/codemirror.css">
<link
	href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<script src="richtext/jscolor.js"></script>
<script src="richtext/tinymce.gzip.js"></script>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script src="richtext/codemirror.js"></script>
<script src="richtext/xml-fold.js"></script>
<script src="richtext/matchtags.js"></script>
<script src="richtext/active-line.js"></script>
<script src="richtext/xml.js"></script>
<script src="richtext/html5-editor.js?x=2019"></script>
<link rel="stylesheet" type="text/css" href="richtext/html5-editor.css?ver=2019" />
<script src="richtext/linketado.js?x=190006"></script>
</head>
<body onload="initoptions(); ">
	<div id="popupUzenetBox">
		<div id="popupUzenet">HTML Editor</div>
		<div
			onclick="document.getElementById('popupUzenetBox').style.display='none';"
			class="okButton">OK</div>
	</div>
	<div id="logContainer"></div>
	<div id="pagewrap">
		<div class="programIsThinking"></div>
		<div id="editorWrapper">
			<div id="szekciok">
				<div class="szekcio1">
					<div id="sourceFieldecske"></div>
					<script>
						var sourceEditorFricc = CodeMirror(document
								.getElementById("sourceFieldecske"), {
							value : "",
							lineNumbers : true,
							styleActiveLine : true,
							mode : "text/html",
							lineWrapping : true,
							matchTags : {
								bothTags : true
							},
						});
						sourceEditorFricc.on("change", function() {
							inputChanged();
						});
					</script>
					<div class="commandBoxes clearfix">
						<div class="multiButton">
							<div title="Adjust cleaning options" class="cleanOptionsToggle">
							</div>
							<div class="convertButton performCleaning">
								<span id="cleanButtonText">Clean</span>
								<div id="onTheConvertButton"></div>
							</div>
						</div>
						<div class="additionalSourceOptions">
							<div>
								<a title="Color Picker" class="colorPickerOpen"> </a>
							</div>
							<div>
								<a title="Lorem-Ipsum generator" class="lipszum"> </a>
							</div>
							<div>
								<a title="Find and Replace tool" class="findundreplace"> </a>
							</div>
							<div>
								<a title="Compress HTML" class="compressHTML"> </a>
							</div>
							<div class="textBiggerSmaller">
								<a title="Smaller Font" class="sourceSmallerFont">-</a> <a
									title="Normal Font"><span>a</span><span>a</span><span>a</span></a>
								<a title="Bigger Font" class="sourceBiggerFont">+</a>
							</div>
						</div>
						<a title="Fresh page">
							<div class="clearButton" onclick="deletePressed();"></div>
						</a> <a title="Undo">
							<div class="undoButton" onclick="undoPressed();"></div>
						</a>
						<div id="inputLength">Source: 0</div>
					</div>
				</div>
				<div class="szekcio2">
					<form method="post">
						<textarea id="elm1" name="elm1"></textarea>
					</form>
				</div>
				<div class="clearboth"></div>
			</div>
		</div>
		<div class="klirfix"></div>
		<div id="lipszuTextarea"></div>
		<div class="overlayContainer">
			<div class="overlayer"></div>
			<div class="settingsList">
				<div id="optionButton0" onclick="clickedOption(0);">Cleaning
					options</div>
				<div id="optionButton1" onclick="clickedOption(1);">Clear
					inline styles</div>
				<div id="optionButton3" onclick="clickedOption(3);">Clear
					classes and IDs</div>
				<div id="optionButton16" onclick="clickedOption(16);">Character
					encoding</div>
				<div id="optionButton13" onclick="clickedOption(13);">Clear
					comments</div>
				<div id="optionButton10" onclick="clickedOption(10);">Clear
					span tags</div>
				<div id="optionButton2" onclick="clickedOption(2);">Clear
					successive &amp;nbsp;s</div>
				<div id="optionButton6" onclick="clickedOption(6);">Clear tags
					with one &amp;nbsp;</div>
				<div id="optionButton5" onclick="clickedOption(5);">Clear
					empty tags</div>
				<div id="optionButton15" onclick="clickedOption(15);">Clear
					tag attributes</div>
				<div id="optionButton12" onclick="clickedOption(12);">Clear
					all tags</div>
				<div id="optionButton11" onclick="clickedOption(11);">Clear
					images</div>
				<div id="optionButton9" onclick="clickedOption(9);">Clear
					links</div>
				<div id="optionButton7" onclick="clickedOption(7);">Clear
					tables</div>
				<div id="optionButton8" onclick="clickedOption(8);">Convert
					tables to &lt;div&gt;s&nbsp;&nbsp;</div>
				<div id="optionButton14" onclick="clickedOption(14);">Organize
					tree-view</div>
				<div id="optionButton4" onclick="clickedOption(4);">Convert
					&lt;b&gt; to &lt;strong&gt;, &lt;i&gt; to &lt;em&gt;</div>
				<p>
					<a class="buttonka settingsDefault">Default</a> <a
						class="buttonka settingsOk">Close</a> <a
						class="buttonka settingsClean performCleaning">Clean</a>
				</p>
			</div>
			<div class="colorPicker">
				<div class="windowHeader">Color Picker</div>
				<div class="windowContent">
					<input
						class="color {pickerOnfocus:false, onImmediateChange:'updateColor();'}"
						id="myColor"
						onmousemove="document.getElementById('myColor').color.showPicker();" />
					<div id="selectedbackgr"></div>
					<div id="colorToUse"></div>
					<div id="savedCodes" class="clearfix"></div>
					<div id="openColorLink" class="buttonkak">
						<a class="buttonka" href="https://rgbcolorcode.com/"
							target="_blank">Color Mixer</a>
					</div>
				</div>
			</div>
			<div class="lipsumText">
				<div class="windowHeader">Gibberish text generator</div>
				<div class="lipsumItself">
					<form name="szettingform">
						Number of paragraphs: <select name="hanyparagrafuslegyen">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="5" selected="selected">5</option>
							<option value="10">10</option>
							<option value="15">15</option>
							<option value="20">20</option>
						</select> <br />Populate: <select name="mitpupulaljon">
							<option value="1" selected="selected">the editor</option>
							<option value="2">below</option>
						</select>
					</form>
					<a class="buttonka" onclick="generateLipsum();">Generate</a>
				</div>
			</div>

			<div class="replaceList">
				<div class="windowHeader">Find and replace in source</div>
				<div id="replace1" class="replaceText">
					<div>
						Find: <input name="replacetext1" id="replacetext1" />
					</div>
					<div>
						Replace with: <input name="replacewith1" id="replacewith1" />
					</div>
					<a onclick="deleteRepField(1);"
						title="Delete this find and replace field"><div
							class="deleteField"></div></a>
				</div>
				<div id="replace2" class="replaceText">
					<div>
						Find: <input name="replacetext2" id="replacetext2" />
					</div>
					<div>
						Replace with: <input name="replacewith2" id="replacewith2" />
					</div>
					<a onclick="deleteRepField(2);"
						title="Delete this find and replace field"><div
							class="deleteField"></div></a>
				</div>
				<div id="replace3" class="replaceText">
					<div>
						Find: <input name="replacetext3" id="replacetext3" />
					</div>
					<div>
						Replace with: <input name="replacewith3" id="replacewith3" />
					</div>
					<a onclick="deleteRepField(3);"
						title="Delete this find and replace field"><div
							class="deleteField"></div></a>
				</div>
				<div id="replace4" class="replaceText">
					<div>
						Find: <input name="replacetext4" id="replacetext4" />
					</div>
					<div>
						Replace with: <input name="replacewith4" id="replacewith4" />
					</div>
					<a onclick="deleteRepField(4);"
						title="Delete this find and replace field"><div
							class="deleteField"></div></a>
				</div>
				<div id="replace5" class="replaceText">
					<div>
						Find: <input name="replacetext5" id="replacetext5" />
					</div>
					<div>
						Replace with: <input name="replacewith5" id="replacewith5" />
					</div>
					<a onclick="deleteRepField(5);"
						title="Delete this find and replace field"><div
							class="deleteField"></div></a>
				</div>
				<div id="replace6" class="replaceText">
					<div>
						Find: <input name="replacetext6" id="replacetext6" />
					</div>
					<div>
						Replace with: <input name="replacewith6" id="replacewith6" />
					</div>
					<a onclick="deleteRepField(6);"
						title="Delete this find and replace field"><div
							class="deleteField"></div></a>
				</div>
				<div id="replace7" class="replaceText">
					<div>
						Find: <input name="replacetext7" id="replacetext7" />
					</div>
					<div>
						Replace with: <input name="replacewith7" id="replacewith7" />
					</div>
					<a onclick="deleteRepField(7);"
						title="Delete this find and replace field"><div
							class="deleteField"></div></a>
				</div>
				<div id="replace8" class="replaceText">
					<div>
						Find: <input name="replacetext8" id="replacetext8" />
					</div>
					<div>
						Replace with: <input name="replacewith8" id="replacewith8" />
					</div>
					<a onclick="deleteRepField(8);"
						title="Delete this find and replace field"><div
							class="deleteField"></div></a>
				</div>
				<div id="replace9" class="replaceText">
					<div>
						Find: <input name="replacetext9" id="replacetext9" />
					</div>
					<div>
						Replace with: <input name="replacewith9" id="replacewith9" />
					</div>
					<a onclick="deleteRepField(9);"
						title="Delete this find and replace field"><div
							class="deleteField"></div></a>
				</div>
				<div id="replace10" class="replaceText">
					<div>
						Find: <input name="replacetext10" id="replacetext10" />
					</div>
					<div>
						Replace with: <input name="replacewith10" id="replacewith10" />
					</div>
					<a onclick="deleteRepField(10);"
						title="Delete this find and replace field"><div
							class="deleteField"></div></a>
				</div>
				<div id="replace11" class="replaceText">
					<div>
						Find: <input name="replacetext11" id="replacetext11" />
					</div>
					<div>
						Replace with: <input name="replacewith11" id="replacewith11" />
					</div>
					<a onclick="deleteRepField(11);"
						title="Delete this find and replace field"><div
							class="deleteField"></div></a>
				</div>
				<div id="replace12" class="replaceText">
					<div>
						Find: <input name="replacetext12" id="replacetext12" />
					</div>
					<div>
						Replace with: <input name="replacewith12" id="replacewith12" />
					</div>
					<a onclick="deleteRepField(12);"
						title="Delete this find and replace field"><div
							class="deleteField"></div></a>
				</div>
				<a onclick="addRepField();" title="Add new field"><div
						class="addNewField"></div></a>
				<div class="clearboth"></div>
				<a title="Find and Replace now" id="findreplacenow" class="buttonka">Replace
					Now</a>
			</div>
		</div>
	</div>
	<script async
		src="https://www.googletagmanager.com/gtag/js?id=UA-69513418-1"></script>
	<script>
		window.dataLayer = window.dataLayer || [];
		function gtag() {
			dataLayer.push(arguments);
		}
		gtag('js', new Date());
		gtag('config', 'UA-69513418-1');
	</script>
</body>
</html>
