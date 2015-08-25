<%@ page contentType="application/msexcel" pageEncoding="UTF-8"%>
<%
	response.setHeader("Content-disposition", "attachment; filename=expPrinterDate.xls");
%>
<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="ProgId" content="Excel.Sheet">
		<meta name="Generator" content="Microsoft Excel 11">
		<!--[if gte mso 9]><xml>
 <o:DocumentProperties>
  <o:LastAuthor>微软用户</o:LastAuthor>
  <o:LastSaved>2009-09-09T08:29:15Z</o:LastSaved>
  <o:Version>11.9999</o:Version>
 </o:DocumentProperties>
 <o:OfficeDocumentSettings>
  <o:Colors>
   <o:Color>
    <o:Index>1</o:Index>
    <o:RGB>#DED7B5</o:RGB>
   </o:Color>
   <o:Color>
    <o:Index>2</o:Index>
    <o:RGB>#000000</o:RGB>
   </o:Color>
   <o:Color>
    <o:Index>3</o:Index>
    <o:RGB>#F4F4F4</o:RGB>
   </o:Color>
   <o:Color>
    <o:Index>4</o:Index>
    <o:RGB>#000000</o:RGB>
   </o:Color>
   <o:Color>
    <o:Index>5</o:Index>
    <o:RGB>#FFFFFF</o:RGB>
   </o:Color>
   <o:Color>
    <o:Index>6</o:Index>
    <o:RGB>#000000</o:RGB>
   </o:Color>
   <o:Color>
    <o:Index>7</o:Index>
    <o:RGB>#FFFFFF</o:RGB>
   </o:Color>
   <o:Color>
    <o:Index>8</o:Index>
    <o:RGB>#000000</o:RGB>
   </o:Color>
   <o:Color>
    <o:Index>9</o:Index>
    <o:RGB>#F4F4F4</o:RGB>
   </o:Color>
   <o:Color>
    <o:Index>10</o:Index>
    <o:RGB>#000000</o:RGB>
   </o:Color>
   <o:Color>
    <o:Index>11</o:Index>
    <o:RGB>#E3E3E3</o:RGB>
   </o:Color>
   <o:Color>
    <o:Index>12</o:Index>
    <o:RGB>#000000</o:RGB>
   </o:Color>
  </o:Colors>
 </o:OfficeDocumentSettings>
</xml><![endif]-->
		<style>
<!--
table {
	mso-displayed-decimal-separator: "\.";
	mso-displayed-thousand-separator: "\,";
}

@page {
	margin: 1.0in .75in 1.0in .75in;
	mso-header-margin: .5in;
	mso-footer-margin: .5in;
}

tr {
	mso-height-source: auto;
	mso-ruby-visibility: none;
}

col {
	mso-width-source: auto;
	mso-ruby-visibility: none;
}

br {
	mso-data-placement: same-cell;
}

td {
	mso-style-parent: style0;
	padding-top: 1px;
	padding-right: 1px;
	padding-left: 1px;
	mso-ignore: padding;
	color: windowtext;
	font-size: 10.0pt;
	font-weight: 400;
	font-style: normal;
	text-decoration: none;
	font-family: Arial, sans-serif;
	mso-font-charset: 0;
	mso-number-format: General;
	text-align: general;
	vertical-align: bottom;
	border: none;
	mso-background-source: auto;
	mso-pattern: auto;
	mso-protection: locked visible;
	white-space: nowrap;
	mso-rotate: 0;
}

.xl18 {
	mso-style-parent: style0;
	color: black;
	font-size: 9.0pt;
	font-family: 宋体;
	mso-generic-font-family: auto;
	mso-font-charset: 134;
	text-align: center;
	border: .5pt solid black;
	background: #DED7B5;
	mso-pattern: auto none;
}

.xl19 {
	mso-style-parent: style0;
	color: black;
	font-size: 9.0pt;
	text-align: left;
	vertical-align: bottom;
	border-top: .5pt solid black;
	border-right: .5pt solid black;
	border-bottom: .5pt solid black;
	border-left: none;
	background: #FFFFFF;
}

.xl20 {
	mso-style-parent: style0;
	color: black;
	font-size: 9.0pt;
	font-family: 宋体;
	mso-generic-font-family: auto;
	mso-font-charset: 134;
	text-align: left;
	border: .5pt solid black;
	background: white;
	mso-pattern: auto none;
	white-space: normal;
}

ruby {
	ruby-align: left;
}

rt {
	color: windowtext;
	font-size: 9.0pt;
	font-weight: 400;
	font-style: normal;
	text-decoration: none;
	font-family: 宋体;
	mso-generic-font-family: auto;
	mso-font-charset: 134;
	mso-char-type: none;
	display: none;
}
-->
</style>
		<!--[if gte mso 9]><xml>
 <x:ExcelWorkbook>
  <x:ExcelWorksheets>
   <x:ExcelWorksheet>
    <x:Name>dddddd</x:Name>
    <x:WorksheetOptions>
     <x:Print>
      <x:ValidPrinterInfo/>
      <x:PaperSizeIndex>9</x:PaperSizeIndex>
      <x:HorizontalResolution>180</x:HorizontalResolution>
      <x:VerticalResolution>180</x:VerticalResolution>
     </x:Print>
     <x:Selected/>
     <x:Panes>
      <x:Pane>
       <x:Number>3</x:Number>
       <x:ActiveRow>14</x:ActiveRow>
       <x:ActiveCol>4</x:ActiveCol>
      </x:Pane>
     </x:Panes>
     <x:ProtectContents>False</x:ProtectContents>
     <x:ProtectObjects>False</x:ProtectObjects>
     <x:ProtectScenarios>False</x:ProtectScenarios>
    </x:WorksheetOptions>
   </x:ExcelWorksheet>
  </x:ExcelWorksheets>
  <x:WindowHeight>8865</x:WindowHeight>
  <x:WindowWidth>14955</x:WindowWidth>
  <x:WindowTopX>120</x:WindowTopX>
  <x:WindowTopY>45</x:WindowTopY>
  <x:ProtectStructure>False</x:ProtectStructure>
  <x:ProtectWindows>False</x:ProtectWindows>
 </x:ExcelWorkbook>
</xml><![endif]-->
	</head>

	<body link="black" vlink="purple">
		<table x:str border=0 cellpadding=0 cellspacing=0 width=1006 style='border-collapse: collapse; table-layout: fixed; width: 755pt'>
			<col width=119
				style='mso-width-source: userset; mso-width-alt: 4352; width: 89pt'>
			<col width=189
				style='mso-width-source: userset; mso-width-alt: 6912; width: 142pt'>
			<col width=163
				style='mso-width-source: userset; mso-width-alt: 5961; width: 122pt'>
			<col width=94
				style='mso-width-source: userset; mso-width-alt: 3437; width: 71pt'>
			<col width=441
				style='mso-width-source: userset; mso-width-alt: 16128; width: 331pt'>
			<tr height=17 style='height: 12.75pt'>
				<td height="17" class="xl18" width="119"
					style='height: 12.75pt; width: 89pt'>
					姓名
				</td>
				<td class="xl18" width="189" style='border-left: none; width: 142pt'>
					电话
				</td>
				<td class="xl18" width="163" style='border-left: none; width: 122pt'>
					手机
				</td>
				<td class="xl18" width="94" style='border-left: none; width: 71pt'>
					邮编
				</td>
				<td class="xl18" width="441" style='border-left: none; width: 331pt'>
					地址
				</td>
			</tr>
			<c:forEach items="${list}" var="expPrinter">
				<tr height="17"  style='height: 12.75pt'>
					<td height="17" class="xl19" style='height: 12.75pt'>
						${expPrinter.receiverName }
					</td>
					<td class="xl19" style='border-left: none;'>
						${expPrinter.receiverPhone }
					</td>
					<td class="xl19" style='border-left: none'>
						${expPrinter.receiverMobile }
					</td>
					<td class="xl19" style='border-left: none'>
						${expPrinter.receiverZip }
					</td>
					<td class="xl19" style='border-left: none'>
						${expPrinter.receiverAddress }
					</td>
				</tr>
			</c:forEach>
			<![if supportMisalignedColumns]>
			<tr height=0 style='display: none'>
				<td width=119 style='width: 89pt'></td>
				<td width=189 style='width: 142pt'></td>
				<td width=163 style='width: 122pt'></td>
				<td width=94 style='width: 71pt'></td>
				<td width=441 style='width: 331pt'></td>
			</tr>
			<![endif]>
		</table>
	</body>
</html>



