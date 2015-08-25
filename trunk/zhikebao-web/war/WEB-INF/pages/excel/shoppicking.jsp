<%@ page contentType="application/msexcel" pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath(); 
	response.setHeader("Content-disposition", "attachment; filename=ShopPicking.xls");
%>
<html xmlns:v="urn:schemas-microsoft-com:vml"
	xmlns:o="urn:schemas-microsoft-com:office:office"
	xmlns:x="urn:schemas-microsoft-com:office:excel"
	xmlns="http://www.w3.org/TR/REC-html40">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="ProgId" content="Excel.Sheet">
		<meta name="Generator" content="Microsoft Excel 11">
		<!--[if !mso]>
<style>
v\:* {behavior:url(#default#VML);}
o\:* {behavior:url(#default#VML);}
x\:* {behavior:url(#default#VML);}
.shape {behavior:url(#default#VML);}
</style>
<![endif]-->
		<!--[if gte mso 9]><xml>
 <o:DocumentProperties>
  <o:Author>jun</o:Author>
  <o:LastAuthor>微软用户</o:LastAuthor>
  <o:LastPrinted>2009-08-03T03:38:39Z</o:LastPrinted>
  <o:Created>2009-06-30T02:47:20Z</o:Created>
  <o:LastSaved>2009-09-12T02:46:22Z</o:LastSaved>
  <o:Company>BSN</o:Company>
  <o:Version>11.9999</o:Version>
 </o:DocumentProperties>
</xml><![endif]-->
		<style>
<!--
table {
	mso-displayed-decimal-separator: "\.";
	mso-displayed-thousand-separator: "\,";
}

@page {
	margin: .1in .2in 0in .2in;
	mso-header-margin: .12in;
	mso-footer-margin: .12in;
	mso-page-orientation: landscape;
	mso-horizontal-page-align: center;
}

.font0 {
	color: windowtext;
	font-size: 11.0pt;
	font-weight: 400;
	font-style: normal;
	text-decoration: none;
	font-family: 宋体;
	mso-generic-font-family: auto;
	mso-font-charset: 134;
}

.font8 {
	color: windowtext;
	font-size: 11.0pt;
	font-weight: 400;
	font-style: normal;
	text-decoration: none;
	font-family: 宋体;
	mso-generic-font-family: auto;
	mso-font-charset: 134;
}

.font9 {
	color: red;
	font-size: 11.0pt;
	font-weight: 400;
	font-style: normal;
	text-decoration: none;
	font-family: 宋体;
	mso-generic-font-family: auto;
	mso-font-charset: 134;
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

.style0 {
	mso-number-format: General;
	text-align: general;
	vertical-align: middle;
	white-space: nowrap;
	mso-rotate: 0;
	mso-background-source: auto;
	mso-pattern: auto;
	color: windowtext;
	font-size: 11.0pt;
	font-weight: 400;
	font-style: normal;
	text-decoration: none;
	font-family: 宋体;
	mso-generic-font-family: auto;
	mso-font-charset: 134;
	border: none;
	mso-protection: locked visible;
	mso-style-name: 常规;
	mso-style-id: 0;
}

td {
	mso-style-parent: style0;
	padding: 0px;
	mso-ignore: padding;
	color: windowtext;
	font-size: 11.0pt;
	font-weight: 400;
	font-style: normal;
	text-decoration: none;
	font-family: 宋体;
	mso-generic-font-family: auto;
	mso-font-charset: 134;
	mso-number-format: General;
	text-align: general;
	vertical-align: middle;
	border: none;
	mso-background-source: auto;
	mso-pattern: auto;
	mso-protection: locked visible;
	white-space: nowrap;
	mso-rotate: 0;
}

.xl65 {
	mso-style-parent: style0;
	border-top: none;
	border-right: none;
	border-bottom: .5pt solid windowtext;
	border-left: none;
}

.xl66 {
	mso-style-parent: style0;
	border: .5pt solid windowtext;
}

.xl67 {
	mso-style-parent: style0;
	border-top: .5pt solid windowtext;
	border-right: none;
	border-bottom: none;
	border-left: .5pt solid windowtext;
}

.xl68 {
	mso-style-parent: style0;
	border-top: .5pt solid windowtext;
	border-right: none;
	border-bottom: none;
	border-left: none;
}

.xl69 {
	mso-style-parent: style0;
	border-top: .5pt solid windowtext;
	border-right: .5pt solid windowtext;
	border-bottom: none;
	border-left: none;
}

.xl70 {
	mso-style-parent: style0;
	border-top: none;
	border-right: none;
	border-bottom: none;
	border-left: .5pt solid windowtext;
}

.xl71 {
	mso-style-parent: style0;
	border-top: none;
	border-right: .5pt solid windowtext;
	border-bottom: none;
	border-left: none;
}

.xl72 {
	mso-style-parent: style0;
	border-top: none;
	border-right: none;
	border-bottom: .5pt solid windowtext;
	border-left: .5pt solid windowtext;
}

.xl73 {
	mso-style-parent: style0;
	border-top: none;
	border-right: .5pt solid windowtext;
	border-bottom: .5pt solid windowtext;
	border-left: none;
}

.xl74 {
	mso-style-parent: style0;
	color: red;
	text-align: center;
	border: .5pt solid windowtext;
}

.xl75 {
	mso-style-parent: style0;
	font-weight: 700;
	border-top: none;
	border-right: none;
	border-bottom: none;
	border-left: .5pt solid windowtext;
}

.xl76 {
	mso-style-parent: style0;
	font-weight: 700;
}

.xl77 {
	mso-style-parent: style0;
	font-weight: 700;
	border-top: .5pt solid windowtext;
	border-right: none;
	border-bottom: none;
	border-left: .5pt solid windowtext;
}

.xl78 {
	mso-style-parent: style0;
	font-weight: 700;
	border-top: .5pt solid windowtext;
	border-right: none;
	border-bottom: none;
	border-left: none;
}

.xl79 {
	mso-style-parent: style0;
	background: yellow;
	mso-pattern: auto none;
}

.xl80 {
	mso-style-parent: style0;
	color: red;
	text-align: center;
	border: .5pt solid windowtext;
	background: white;
	mso-pattern: auto none;
}

.xl81 {
	mso-style-parent: style0;
	color: white;
	background: white;
	mso-pattern: auto none;
}

.xl82 {
	mso-style-parent: style0;
	border-top: .5pt solid windowtext;
	border-right: none;
	border-bottom: none;
	border-left: .5pt solid windowtext;
	background: yellow;
	mso-pattern: auto none;
}

.xl83 {
	mso-style-parent: style0;
	border-top: .5pt solid windowtext;
	border-right: none;
	border-bottom: none;
	border-left: none;
	background: yellow;
	mso-pattern: auto none;
}

.xl84 {
	mso-style-parent: style0;
	border-top: .5pt solid windowtext;
	border-right: .5pt solid windowtext;
	border-bottom: none;
	border-left: none;
	background: yellow;
	mso-pattern: auto none;
}

.xl85 {
	mso-style-parent: style0;
	border-top: none;
	border-right: none;
	border-bottom: none;
	border-left: .5pt solid windowtext;
	background: yellow;
	mso-pattern: auto none;
}

.xl86 {
	mso-style-parent: style0;
	border-top: none;
	border-right: .5pt solid windowtext;
	border-bottom: none;
	border-left: none;
	background: yellow;
	mso-pattern: auto none;
}

.xl87 {
	mso-style-parent: style0;
	border-top: none;
	border-right: none;
	border-bottom: .5pt solid windowtext;
	border-left: .5pt solid windowtext;
	background: yellow;
	mso-pattern: auto none;
}

.xl88 {
	mso-style-parent: style0;
	border-top: none;
	border-right: none;
	border-bottom: .5pt solid windowtext;
	border-left: none;
	background: yellow;
	mso-pattern: auto none;
}

.xl89 {
	mso-style-parent: style0;
	border-top: none;
	border-right: .5pt solid windowtext;
	border-bottom: .5pt solid windowtext;
	border-left: none;
	background: yellow;
	mso-pattern: auto none;
}

.xl90 {
	mso-style-parent: style0;
	color: white;
	text-align: center;
	border: .5pt solid windowtext;
	background: red;
	mso-pattern: auto none;
}

.xl91 {
	mso-style-parent: style0;
	color: white;
	border: .5pt solid windowtext;
	background: red;
	mso-pattern: auto none;
}

.xl92 {
	mso-style-parent: style0;
	text-align: center;
	border-top: .5pt solid windowtext;
	border-right: none;
	border-bottom: .5pt solid windowtext;
	border-left: .5pt solid windowtext;
}

.xl93 {
	mso-style-parent: style0;
	text-align: center;
	border-top: .5pt solid windowtext;
	border-right: .5pt solid windowtext;
	border-bottom: .5pt solid windowtext;
	border-left: none;
}

.xl94 {
	mso-style-parent: style0;
	text-align: center;
	border-top: .5pt solid windowtext;
	border-right: none;
	border-bottom: none;
	border-left: .5pt solid windowtext;
}

.xl95 {
	mso-style-parent: style0;
	text-align: center;
	border-top: .5pt solid windowtext;
	border-right: .5pt solid windowtext;
	border-bottom: none;
	border-left: none;
}

.xl96 {
	mso-style-parent: style0;
	text-align: center;
	border-top: none;
	border-right: none;
	border-bottom: none;
	border-left: .5pt solid windowtext;
}

.xl97 {
	mso-style-parent: style0;
	text-align: center;
	border-top: none;
	border-right: .5pt solid windowtext;
	border-bottom: none;
	border-left: none;
}

.xl98 {
	mso-style-parent: style0;
	text-align: center;
	border-top: none;
	border-right: none;
	border-bottom: .5pt solid windowtext;
	border-left: .5pt solid windowtext;
}

.xl99 {
	mso-style-parent: style0;
	text-align: center;
	border-top: none;
	border-right: .5pt solid windowtext;
	border-bottom: .5pt solid windowtext;
	border-left: none;
}

.xl100 {
	mso-style-parent: style0;
	color: white;
	text-align: center;
	border-top: .5pt solid windowtext;
	border-right: none;
	border-bottom: .5pt solid windowtext;
	border-left: .5pt solid windowtext;
	background: red;
	mso-pattern: auto none;
}

.xl101 {
	mso-style-parent: style0;
	color: white;
	text-align: center;
	border-top: .5pt solid windowtext;
	border-right: .5pt solid windowtext;
	border-bottom: .5pt solid windowtext;
	border-left: none;
	background: red;
	mso-pattern: auto none;
}

.xl102 {
	mso-style-parent: style0;
	text-align: center;
	border-top: .5pt solid windowtext;
	border-right: none;
	border-bottom: none;
	border-left: none;
}

.xl103 {
	mso-style-parent: style0;
	text-align: center;
}

.xl104 {
	mso-style-parent: style0;
	text-align: center;
	border-top: none;
	border-right: none;
	border-bottom: .5pt solid windowtext;
	border-left: none;
}

.xl105 {
	mso-style-parent: style0;
	text-align: center;
	border-top: .5pt solid windowtext;
	border-right: .5pt solid windowtext;
	border-bottom: none;
	border-left: .5pt solid windowtext;
}

.xl106 {
	mso-style-parent: style0;
	text-align: center;
	border-top: none;
	border-right: .5pt solid windowtext;
	border-bottom: none;
	border-left: .5pt solid windowtext;
}

.xl107 {
	mso-style-parent: style0;
	text-align: center;
	border-top: none;
	border-right: .5pt solid windowtext;
	border-bottom: .5pt solid windowtext;
	border-left: .5pt solid windowtext;
}

.xl108 {
	mso-style-parent: style0;
	text-align: center;
	border: .5pt solid windowtext;
}

.xl109 {
	mso-style-parent: style0;
	border-top: .5pt solid windowtext;
	border-right: none;
	border-bottom: .5pt solid windowtext;
	border-left: .5pt solid windowtext;
}

.xl110 {
	mso-style-parent: style0;
	border-top: .5pt solid windowtext;
	border-right: none;
	border-bottom: .5pt solid windowtext;
	border-left: none;
}

.xl111 {
	mso-style-parent: style0;
	border-top: .5pt solid windowtext;
	border-right: .5pt solid windowtext;
	border-bottom: .5pt solid windowtext;
	border-left: none;
}

.xl112 {
	mso-style-parent: style0;
	font-size: 18.0pt;
	text-decoration: underline;
	text-underline-style: single;
	text-align: center;
	border-top: .5pt solid windowtext;
	border-right: none;
	border-bottom: none;
	border-left: none;
}

.xl113 {
	mso-style-parent: style0;
	text-align: left;
	border-top: .5pt solid windowtext;
	border-right: none;
	border-bottom: .5pt solid windowtext;
	border-left: .5pt solid windowtext;
}

.xl114 {
	mso-style-parent: style0;
	text-align: left;
	border-top: .5pt solid windowtext;
	border-right: none;
	border-bottom: .5pt solid windowtext;
	border-left: none;
}

.xl115 {
	mso-style-parent: style0;
	text-align: left;
	border-top: .5pt solid windowtext;
	border-right: .5pt solid windowtext;
	border-bottom: .5pt solid windowtext;
	border-left: none;
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
    <x:Name>Sheet1</x:Name>
    <x:WorksheetOptions>
     <x:DefaultRowHeight>285</x:DefaultRowHeight>
     <x:Print>
      <x:ValidPrinterInfo/>
      <x:PaperSizeIndex>126</x:PaperSizeIndex>
      <x:Scale>90</x:Scale>
      <x:HorizontalResolution>600</x:HorizontalResolution>
      <x:VerticalResolution>600</x:VerticalResolution>
     </x:Print>
     <x:Selected/>
     <x:Panes>
      <x:Pane>
       <x:Number>3</x:Number>
       <x:ActiveRow>13</x:ActiveRow>
       <x:ActiveCol>17</x:ActiveCol>
      </x:Pane>
     </x:Panes>
     <x:ProtectContents>False</x:ProtectContents>
     <x:ProtectObjects>False</x:ProtectObjects>
     <x:ProtectScenarios>False</x:ProtectScenarios>
    </x:WorksheetOptions>
   </x:ExcelWorksheet>
   <x:ExcelWorksheet>
    <x:Name>Sheet2</x:Name>
    <x:WorksheetOptions>
     <x:DefaultRowHeight>285</x:DefaultRowHeight>
     <x:ProtectContents>False</x:ProtectContents>
     <x:ProtectObjects>False</x:ProtectObjects>
     <x:ProtectScenarios>False</x:ProtectScenarios>
    </x:WorksheetOptions>
   </x:ExcelWorksheet>
   <x:ExcelWorksheet>
    <x:Name>Sheet3</x:Name>
    <x:WorksheetOptions>
     <x:DefaultRowHeight>285</x:DefaultRowHeight>
     <x:ProtectContents>False</x:ProtectContents>
     <x:ProtectObjects>False</x:ProtectObjects>
     <x:ProtectScenarios>False</x:ProtectScenarios>
    </x:WorksheetOptions>
   </x:ExcelWorksheet>
  </x:ExcelWorksheets>
  <x:WindowHeight>9120</x:WindowHeight>
  <x:WindowWidth>12120</x:WindowWidth>
  <x:WindowTopX>480</x:WindowTopX>
  <x:WindowTopY>120</x:WindowTopY>
  <x:ProtectStructure>False</x:ProtectStructure>
  <x:ProtectWindows>False</x:ProtectWindows>
 </x:ExcelWorkbook>
</xml><![endif]-->
		<!--[if gte mso 9]><xml>
 <o:shapedefaults v:ext="edit" spidmax="2049"/>
</xml><![endif]-->
		<!--[if gte mso 9]><xml>
 <o:shapelayout v:ext="edit">
  <o:idmap v:ext="edit" data="1"/>
 </o:shapelayout></xml><![endif]-->
	</head>

	<body link=blue vlink=purple>
		<table x:str border=0 cellpadding=0 cellspacing=0 width=1039
			style='border-collapse: collapse; table-layout: fixed; width: 783pt'>
			<col width=40
				style='mso-width-source: userset; mso-width-alt: 1280; width: 30pt'>
			<col width=48
				style='mso-width-source: userset; mso-width-alt: 1536; width: 36pt'>
			<col width=44
				style='mso-width-source: userset; mso-width-alt: 1408; width: 33pt'>
			<col width="295"
				style='mso-width-source: userset; mso-width-alt: 9440; width: 221pt'>
			<col width=33 span=7
				style='mso-width-source: userset; mso-width-alt: 1056; width: 25pt'>
			<col width=29 span=6
				style='mso-width-source: userset; mso-width-alt: 928; width: 22pt'>
			<col width=51 span=2
				style='mso-width-source: userset; mso-width-alt: 1632; width: 38pt'>
			<col width=29
				style='mso-width-source: userset; mso-width-alt: 928; width: 22pt'>
			<col width=45
				style='mso-width-source: userset; mso-width-alt: 1440; width: 34pt'>
			<col width=127
				style='mso-width-source: userset; mso-width-alt: 4064; width: 95pt'>
			<col width=81
				style='mso-width-source: userset; mso-width-alt: 2592; width: 61pt'>
			<logic:iterate id="shopPicking" name="list">
			<tr height=30 style='mso-height-source: userset; height: 22.5pt'>
				<td height=30 class=xl67 width=40
					style='height: 22.5pt; width: 30pt'>
				</td>
				<td class=xl68 width=48 style='width: 36pt'>
				</td>
				<td colspan=19 class=xl112 width=743 style='width: 561pt'>
					堡狮龙网购清单
				</td>
				<td width=127 style='width: 95pt' align=left valign=top>
					<!--[if gte vml 1]><v:shapetype
   id="_x0000_t75" coordsize="21600,21600" o:spt="75" o:preferrelative="t"
   path="m@4@5l@4@11@9@11@9@5xe" filled="f" stroked="f">
   <v:stroke joinstyle="miter"/>
   <v:formulas>
    <v:f eqn="if lineDrawn pixelLineWidth 0"/>
    <v:f eqn="sum @0 1 0"/>
    <v:f eqn="sum 0 0 @1"/>
    <v:f eqn="prod @2 1 2"/>
    <v:f eqn="prod @3 21600 pixelWidth"/>
    <v:f eqn="prod @3 21600 pixelHeight"/>
    <v:f eqn="sum @0 0 1"/>
    <v:f eqn="prod @6 1 2"/>
    <v:f eqn="prod @7 21600 pixelWidth"/>
    <v:f eqn="sum @8 21600 0"/>
    <v:f eqn="prod @7 21600 pixelHeight"/>
    <v:f eqn="sum @10 21600 0"/>
   </v:formulas>
   <v:path o:extrusionok="f" gradientshapeok="t" o:connecttype="rect"/>
   <o:lock v:ext="edit" aspectratio="t"/>
  </v:shapetype><v:shape id="Picture_x0020_1" o:spid="_x0000_s1039" type="#_x0000_t75"
   alt="img_bossinin_logo" style='position:absolute;margin-left:20.25pt;
   margin-top:3pt;width:129pt;height:46.5pt;z-index:1;visibility:visible'>
   <v:imagedata src="<%=basePath%>/img/excel/bossinilogo.png"
    o:title="img_bossinin_logo"/>
   <x:ClientData ObjectType="Pict">
    <x:SizeWithCells/>
    <x:CF>Bitmap</x:CF>
   </x:ClientData>
  </v:shape><![endif]-->
					<![if !vml]>
					<span
						style='mso-ignore: vglayout; position: absolute; z-index: 1; margin-left: 27px; margin-top: 4px; width: 172px; height: 62px'><img
							width=172 height=62
							src="<%=basePath%>/img/excel/bossinilogo.jpg"
							alt="img_bossinin_logo" v:shapes="Picture_x0020_1">
					</span>
					<![endif]>
					<span style='mso-ignore: vglayout2'>
						<table cellpadding=0 cellspacing=0>
							<tr>
								<td height=30 class=xl68 width=127
									style='height: 22.5pt; width: 95pt'>
								</td>
							</tr>
						</table> </span>
				</td>
				<td class=xl69 width=81 style='width: 61pt'>
				</td>
			</tr>
			<tr height=15 style='height: 14.25pt'>
				<td height=15 class=xl70 style='height: 14.25pt'>
				</td>
				<td colspan=21 style='mso-ignore: colspan'></td>
				<td class=xl71>
				</td>
			</tr>
			<tr height=15 style='height: 14.25pt'>
				<td height=15 class=xl70 style='height: 14.25pt'>
				</td>
				<td colspan=21 style='mso-ignore: colspan'></td>
				<td class=xl71>
				</td>
			</tr>
			<tr height=20 style='mso-height-source: userset; height: 20.1pt'>
				<td height=20 class=xl66 style='height: 20.1pt'>
					订单
					<span style='display: none'>号:</span>
				</td>
				<td class=xl66 style='border-left: none'>
				</td>
				<td colspan=7 class=xl109
					style='border-right: .5pt solid black; border-left: none'>
					${shopPicking.tid}
				</td>
				<td class=xl66 style='border-left: none'>
					堡狮
					<span style='display: none'>龙库管理号:</span>
				</td>
				<td class=xl66 style='border-left: none'>
				</td>
				<td class=xl66 style='border-left: none'>
				</td>
				<td class=xl66 style='border-left: none'>
				</td>
				<td colspan=6 class=xl109
					style='border-right: .5pt solid black; border-left: none'>
				</td>
				<td class=xl66 style='border-left: none'>
					发
					<span style='display: none'>货日期:</span>
				</td>
				<td class=xl66 style='border-left: none'>
				</td>
				<td colspan=2 class=xl109
					style='border-right: .5pt solid black; border-left: none'>
				</td>
			</tr>
			<tr height=20 style='mso-height-source: userset; height: 20.1pt'>
				<td height=20 class=xl66 style='height: 20.1pt; border-top: none'>
					会员
					<span style='display: none'>名:</span>
				</td>
				<td class=xl66 style='border-top: none; border-left: none'>
				</td>
				<td colspan=7 class=xl109
					style='border-right: .5pt solid black; border-left: none'>
					${shopPicking.buyerNick}
				</td>
				<td class=xl66 style='border-top: none; border-left: none'>
					收
					<span style='mso-spacerun: yes'>&nbsp; </span><span
						style='display: none'><span style='mso-spacerun: yes'>&nbsp;&nbsp;</span>货<span
						style='mso-spacerun: yes'>&nbsp;&nbsp; </span>人:</span>
				</td>
				<td class=xl66 style='border-top: none; border-left: none'>
				</td>
				<td class=xl66 style='border-top: none; border-left: none'>
				</td>
				<td class=xl66 style='border-top: none; border-left: none'>
				</td>
				<td colspan=6 class=xl109
					style='border-right: .5pt solid black; border-left: none'>
					${shopPicking.receiverName}
				</td>
				<td class=xl66 style='border-top: none; border-left: none'>
					快<span style='display: none'>递公司:</span>
				</td>
				<td class=xl66 style='border-top: none; border-left: none'>
				</td>
				<td colspan=2 class=xl109
					style='border-right: .5pt solid black; border-left: none'>
				</td>
			</tr>
			<tr height=20 style='mso-height-source: userset; height: 20.1pt'>
				<td height=20 class=xl66 style='height: 20.1pt; border-top: none'>
					收货
					<span style='display: none'>地址:</span>
				</td>
				<td class=xl66 style='border-top: none; border-left: none'>
				</td>
				<td colspan=17 class=xl109
					style='border-right: .5pt solid black; border-left: none'>
					${shopPicking.receiverAddress}
				</td>
				<td class=xl66 style='border-top: none; border-left: none'>
					发<span style='display: none'>货编码：</span>
				</td>
				<td class=xl66 style='border-top: none; border-left: none'>
				</td>
				<td colspan=2 class=xl109
					style='border-right: .5pt solid black; border-left: none'>
				</td>
			</tr>
			<tr height=20 style='mso-height-source: userset; height: 20.1pt'>
				<td height=20 class=xl66 style='height: 20.1pt; border-top: none'>
					联系
					<span style='display: none'>电话:</span>
				</td>
				<td class=xl66 style='border-top: none; border-left: none'>
				</td>
				<td colspan=7 class=xl109
					style='border-right: .5pt solid black; border-left: none'>
					${shopPicking.receiverPhone}
				</td>
				<td colspan=4 class=xl113
					style='border-right: .5pt solid black; border-left: none'>
					手
					<span style='mso-spacerun: yes'>&nbsp;&nbsp; </span>机:
				</td>
				<td colspan=6 class=xl109
					style='border-right: .5pt solid black; border-left: none'>
					${shopPicking.receiverMobile}
				</td>
				<td class=xl66 style='border-top: none; border-left: none'>
					邮
					<span style='display: none'>政编码：</span>
				</td>
				<td class=xl66 style='border-top: none; border-left: none'>
				</td>
				<td colspan=2 class=xl109
					style='border-right: .5pt solid black; border-left: none'>
					${shopPicking.receiverZip}
				</td>
			</tr>
			<tr height=20 style='mso-height-source: userset; height: 20.1pt'>
				<td colspan=19 height=20 class=xl70 style='height: 20.1pt'>
				</td>
				<td colspan=2 class=xl66>
					总运费
				</td>
				<td class=xl66 style='border-top: none; border-left: none'>
				</td>
				<td class=xl71>
				</td>
			</tr>
			<tr height=15 style='height: 14.25pt'>
				<td colspan=2 rowspan=3 height=57 class=xl94
					style='border-right: .5pt solid black; border-bottom: .5pt solid black; height: 42.75pt'>
					货品款号
				</td>
				<td rowspan=3 class=xl105 style='border-bottom: .5pt solid black'>
					颜色
				</td>
				<td class=xl80 style='border-left: none'>
					男女装
				</td>
				<td class=xl74 style='border-left: none' x:num>
					24
				</td>
				<td class=xl74 style='border-left: none' x:num>
					25
				</td>
				<td class=xl74 style='border-left: none' x:num>
					26
				</td>
				<td class=xl74 style='border-left: none' x:num>
					27
				</td>
				<td class=xl74 style='border-left: none' x:num>
					28
				</td>
				<td class=xl74 style='border-left: none' x:num>
					29
				</td>
				<td class=xl74 style='border-left: none' x:num>
					30
				</td>
				<td class=xl74 style='border-left: none' x:num>
					31
				</td>
				<td class=xl74 style='border-left: none' x:num>
					32
				</td>
				<td class=xl74 style='border-left: none' x:num>
					33
				</td>
				<td class=xl74 style='border-left: none' x:num>
					34
				</td>
				<td class=xl74 style='border-left: none' x:num>
					35
				</td>
				<td class=xl74 style='border-left: none' x:num>
					36
				</td>
				<td rowspan=3 class=xl105 style='border-bottom: .5pt solid black'>
					件数
				</td>
				<td rowspan=3 class=xl105 style='border-bottom: .5pt solid black'>
					单价
				</td>
				<td colspan=3 rowspan=3 class=xl94
					style='border-right: .5pt solid black; border-bottom: .5pt solid black'>
					退货原因
				</td>
				<td rowspan=3 class=xl105 style='border-bottom: .5pt solid black'>
					退货数量
				</td>
			</tr>
			<tr height=15 style='height: 14.25pt'>
				<td height=15 class=xl80
					style='height: 14.25pt; border-top: none; border-left: none'>
					童装
				</td>
				<td class=xl74 style='border-top: none; border-left: none' x:num>
					100
				</td>
				<td class=xl74 style='border-top: none; border-left: none' x:num>
					110
				</td>
				<td class=xl74 style='border-top: none; border-left: none' x:num>
					120
				</td>
				<td class=xl74 style='border-top: none; border-left: none' x:num>
					130
				</td>
				<td class=xl74 style='border-top: none; border-left: none' x:num>
					140
				</td>
				<td class=xl74 style='border-top: none; border-left: none' x:num>
					150
				</td>
				<td class=xl74 style='border-top: none; border-left: none' x:num>
					160
				</td>
				<td class=xl74 style='border-top: none; border-left: none'>
				</td>
				<td class=xl74 style='border-top: none; border-left: none'>
				</td>
				<td class=xl74 style='border-top: none; border-left: none'>
				</td>
				<td class=xl74 style='border-top: none; border-left: none'>
				</td>
				<td class=xl74 style='border-top: none; border-left: none'>
				</td>
				<td class=xl74 style='border-top: none; border-left: none'>
				</td>
			</tr>
			<tr height=15 style='height: 14.25pt'>
				<td height=15 class=xl80
					style='height: 14.25pt; border-top: none; border-left: none'>
					男女装
				</td>
				<td class=xl74 style='border-top: none; border-left: none'>
					XS
				</td>
				<td class=xl74 style='border-top: none; border-left: none'>
				</td>
				<td class=xl74 style='border-top: none; border-left: none'>
					S
				</td>
				<td class=xl74 style='border-top: none; border-left: none'>
				</td>
				<td class=xl74 style='border-top: none; border-left: none'>
					M
				</td>
				<td class=xl74 style='border-top: none; border-left: none'>
				</td>
				<td class=xl74 style='border-top: none; border-left: none'>
					L
				</td>
				<td class=xl74 style='border-top: none; border-left: none'>
				</td>
				<td class=xl74 style='border-top: none; border-left: none'>
					XL
				</td>
				<td class=xl74 style='border-top: none; border-left: none'>
				</td>
				<td class=xl74 style='border-top: none; border-left: none'>
					XXL
				</td>
				<td class=xl74 style='border-top: none; border-left: none'>
				</td>
				<td class=xl74 style='border-top: none; border-left: none'>
					FS
				</td>
			</tr>
			<logic:iterate id="shopPickingItem" name="shopPicking" property="item"> 
			<tr height=18 style='mso-height-source: userset; height: 18.6pt'>
				<td colspan=2 height=18 class=xl92
					style='border-right: .5pt solid black; height: 18.6pt'>
					${shopPickingItem.plu}
				</td>
				<td class=xl66 style='border-top: none; border-left: none'>
					${shopPickingItem.colorCode}
				</td>
				<td class=xl66 style='border-top: none; border-left: none'>
					${shopPickingItem.name}
				</td>
				<td class=xl66 style='border-top: none; border-left: none'>
					<logic:notEqual value="0" name="shopPickingItem"  property="num1">
						${shopPickingItem.num1}
					</logic:notEqual>
				</td>
				<td class=xl66 style='border-top: none; border-left: none'>
					<logic:notEqual value="0" name="shopPickingItem"  property="num2">
						${shopPickingItem.num2}
					</logic:notEqual>
				</td>
				<td class=xl66 style='border-top: none; border-left: none'>
					<logic:notEqual value="0" name="shopPickingItem"  property="num3">
						${shopPickingItem.num3}
					</logic:notEqual>
				</td>
				<td class=xl66 style='border-top: none; border-left: none'>
					<logic:notEqual value="0" name="shopPickingItem"  property="num4">
						${shopPickingItem.num4}
					</logic:notEqual>
				</td>
				<td class=xl66 style='border-top: none; border-left: none'>
					<logic:notEqual value="0" name="shopPickingItem"  property="num5">
						${shopPickingItem.num5}
					</logic:notEqual>
				</td>
				<td class=xl66 style='border-top: none; border-left: none'>
					<logic:notEqual value="0" name="shopPickingItem"  property="num6">
						${shopPickingItem.num6}
					</logic:notEqual>
				</td>
				<td class=xl66 style='border-top: none; border-left: none'>
					<logic:notEqual value="0" name="shopPickingItem"  property="num7">
						${shopPickingItem.num7}
					</logic:notEqual>
				</td>
				<td class=xl66 style='border-top: none; border-left: none'>
					<logic:notEqual value="0" name="shopPickingItem"  property="num8">
						${shopPickingItem.num8}
					</logic:notEqual>
				</td>
				<td class=xl66 style='border-top: none; border-left: none'>
					<logic:notEqual value="0" name="shopPickingItem"  property="num9">
						${shopPickingItem.num9}
					</logic:notEqual>
				</td>
				<td class=xl66 style='border-top: none; border-left: none'>
					<logic:notEqual value="0" name="shopPickingItem"  property="num10">
						${shopPickingItem.num10}
					</logic:notEqual>
				</td>
				<td class=xl66 style='border-top: none; border-left: none'>
					<logic:notEqual value="0" name="shopPickingItem"  property="num11">
						${shopPickingItem.num11}
					</logic:notEqual>
				</td>
				<td class=xl66 style='border-top: none; border-left: none'>
					<logic:notEqual value="0" name="shopPickingItem"  property="num12">
						${shopPickingItem.num12}
					</logic:notEqual>
				</td>
				<td class=xl66 style='border-top: none; border-left: none'>
					<logic:notEqual value="0" name="shopPickingItem"  property="num13">
						${shopPickingItem.num13}
					</logic:notEqual>
				</td>
				<td class=xl66 style='border-top: none; border-left: none'>
					<logic:notEqual value="0" name="shopPickingItem"  property="num">
						${shopPickingItem.num}
					</logic:notEqual>
				</td>
				<td class=xl66 style='border-top: none; border-left: none'>
					${shopPickingItem.price}
				</td>
				<td colspan=3 class=xl108 style='border-left: none'>
				</td>
				<td class=xl66 style='border-top: none; border-left: none'>
				</td>
			</tr>
			</logic:iterate> 
			<tr height=18 style='mso-height-source: userset; height: 18.6pt'>
				<td height=18 class=xl77 colspan=3
					style='height: 18.6pt; mso-ignore: colspan'>
					注意事项：
				</td>
				<td class=xl68 style='border-top: none'>
				</td>
				<td class=xl68 style='border-top: none'>
				</td>
				<td class=xl68 style='border-top: none'>
				</td>
				<td class=xl68 style='border-top: none'>
				</td>
				<td class=xl68 style='border-top: none'>
				</td>
				<td class=xl68 style='border-top: none'>
				</td>
				<td class=xl68 style='border-top: none'>
				</td>
				<td class=xl78 colspan=4 style='mso-ignore: colspan'>
					退货流程：
				</td>
				<td class=xl68 style='border-top: none'>
				</td>
				<td class=xl68 style='border-top: none'>
				</td>
				<td class=xl68 style='border-top: none'>
				</td>
				<td class=xl68 style='border-top: none'>
				</td>
				<td class=xl68 style='border-top: none'>
				</td>
				<td class=xl68 style='border-top: none'>
				</td>
				<td class=xl68 colspan=2 style='mso-ignore: colspan'>
					退款操作步骤说明：
				</td>
				<td class=xl69 style='border-top: none'>
				</td>
			</tr>
			<tr height=15 style='height: 14.25pt'>
				<td height=15 class=xl75 colspan=5
					style='height: 14.25pt; mso-ignore: colspan'>
					以下情况的商品不提供退货服务：
				</td>
				<td colspan=5 style='mso-ignore: colspan'></td>
				<td colspan=9 style='mso-ignore: colspan'>
					关于退货的申请方法,请按淘宝商城的
				</td>
				<td></td>
				<td colspan=3
					style='mso-ignore: colspan; border-right: .5pt solid black'>
					1.联系堡狮龙
					<font class="font9">淘宝旗舰店</font><font class="font8">客服中心,</font>
				</td>
			</tr>
			<tr height=15 style='height: 14.25pt'>
				<td height=15 class=xl70 colspan=4
					style='height: 14.25pt; mso-ignore: colspan'>
					1
					<font class="font8">.</font><font class="font0">已穿着，洗涤的商品；</font>
				</td>
				<td colspan=6 style='mso-ignore: colspan'></td>
				<td colspan=9 style='mso-ignore: colspan'>
					退货流程进行操作.完成退货申请后,请
				</td>
				<td></td>
				<td colspan=2 style='mso-ignore: colspan'>
					告知退货原因.
				</td>
				<td class=xl71>
				</td>
			</tr>
			<tr height=15 style='height: 14.25pt'>
				<td height=15 class=xl70 colspan=4
					style='height: 14.25pt; mso-ignore: colspan'>
					2
					<font class="font8">.</font><font class="font0">直接接触皮肤的内衣类商品</font>
				</td>
				<td colspan=6 style='mso-ignore: colspan'></td>
				<td colspan=9 style='mso-ignore: colspan'>
					依照下列程序处理,否则可能无法办理退货.
				</td>
				<td></td>
				<td colspan=3
					style='mso-ignore: colspan; border-right: .5pt solid black'>
					2.堡狮龙认可后,按左记&quot;退货流程&quot;
				</td>
			</tr>
			<tr height=15 style='height: 14.25pt'>
				<td height=15 class=xl70 colspan=4
					style='height: 14.25pt; mso-ignore: colspan'>
					3
					<font class="font8">.</font><font class="font0">已修改裤脚等加工后的商品；</font>
				</td>
				<td colspan=6 style='mso-ignore: colspan'></td>
				<td colspan=9 style='mso-ignore: colspan'>
					1.依照随货寄出的商品清单,核对并填写退
				</td>
				<td></td>
				<td colspan=2 style='mso-ignore: colspan'>
					办理退货.
				</td>
				<td class=xl71>
				</td>
			</tr>
			<tr height=15 style='height: 14.25pt'>
				<td height=15 class=xl70 colspan=4
					style='height: 14.25pt; mso-ignore: colspan'>
					4
					<font class="font8">.</font><font class="font0">商品吊牌，各种包装有缺损</font>
				</td>
				<td colspan=6 style='mso-ignore: colspan'></td>
				<td colspan=4 style='mso-ignore: colspan'>
					货原因和数量.
				</td>
				<td colspan=6 style='mso-ignore: colspan'></td>
				<td colspan=3
					style='mso-ignore: colspan; border-right: .5pt solid black'>
					3.在淘宝线上申请&quot;支付宝&quot;退款,
				</td>
			</tr>
			<tr height=15 style='height: 14.25pt'>
				<td height=15 class=xl75 colspan=4
					style='height: 14.25pt; mso-ignore: colspan'>
					退货时请注意以下内容：
				</td>
				<td colspan=6 style='mso-ignore: colspan'></td>
				<td colspan=9 style='mso-ignore: colspan'>
					2.将填写好的商品清单(复印有效)与商品
				</td>
				<td></td>
				<td colspan=3
					style='mso-ignore: colspan; border-right: .5pt solid black'>
					并提交退款协议.在填写&quot;退款协议&quot;
				</td>
			</tr>
			<tr height=15 style='height: 14.25pt'>
				<td height=15 class=xl70 colspan=4
					style='height: 14.25pt; mso-ignore: colspan'>
					1
					<font class="font8">.</font><font class="font0">申请退货须在商品清单上正确</font>
				</td>
				<td colspan=6 style='mso-ignore: colspan'></td>
				<td colspan=9 style='mso-ignore: colspan'>
					一同退回.未附有退货商品清单的,将不能
				</td>
				<td></td>
				<td colspan=3
					style='mso-ignore: colspan; border-right: .5pt solid black'>
					时,请选择&quot;我要退货&quot;,并在&quot;退款
				</td>
			</tr>
			<tr height=15 style='height: 14.25pt'>
				<td height=15 class=xl70 colspan=5
					style='height: 14.25pt; mso-ignore: colspan'>
					填写退货原因和数量,并一同寄回。
				</td>
				<td colspan=5 style='mso-ignore: colspan'></td>
				<td colspan=2 style='mso-ignore: colspan'>
					退款.
				</td>
				<td colspan=8 style='mso-ignore: colspan'></td>
				<td colspan=3
					style='mso-ignore: colspan; border-right: .5pt solid black'>
					说明&quot;的留言处填入退货情况(返送
				</td>
			</tr>
			<tr height=15 style='height: 14.25pt'>
				<td height=15 class=xl70 colspan=6
					style='height: 14.25pt; mso-ignore: colspan'>
					2
					<font class="font8">.</font><font class="font0">退货商品须在商品到达后7日之内提出。</font>
				</td>
				<td colspan=4 style='mso-ignore: colspan'></td>
				<td colspan=8 style='mso-ignore: colspan'>
					3.请发货到以下指定送回地点.
				</td>
				<td colspan=2 style='mso-ignore: colspan'></td>
				<td colspan=3
					style='mso-ignore: colspan; border-right: .5pt solid black'>
					快递单号及快递公司).此处信息不
				</td>
			</tr>
			<tr height=15 style='height: 14.25pt'>
				<td height=15 class=xl70 colspan=7
					style='height: 14.25pt; mso-ignore: colspan'>
					3.
					<font class="font0">如我方发现收到是“非堡狮龙正规商品、没有</font>
				</td>
				<td colspan=3 style='mso-ignore: colspan'></td>
				<td colspan=9 style='mso-ignore: colspan'>
					地址:广州市白云区新市棠涌西苑路14号
				</td>
				<td></td>
				<td colspan=3
					style='mso-ignore: colspan; border-right: .5pt solid black'>
					填,堡狮龙无法进行退款操作.
				</td>
			</tr>
			<tr height=15 style='height: 14.25pt'>
				<td height=15 class=xl70 colspan=8
					style='height: 14.25pt; mso-ignore: colspan'>
					购买记录的商品，以及恶意性的大量退货”的情况
				</td>
				<td colspan=3 style='mso-ignore: colspan'></td>
				<td colspan=3 style='mso-ignore: colspan'>
					堡狮龙仓库
				</td>
				<td colspan=6 style='mso-ignore: colspan'></td>
				<td colspan=3
					style='mso-ignore: colspan; border-right: .5pt solid black'>
					4.待退回商品及退款申请被确认后,
				</td>
			</tr>
			<tr height=15 style='height: 14.25pt'>
				<td height=15 class=xl70 colspan=6
					style='height: 14.25pt; mso-ignore: colspan'>
					堡狮龙拒绝退货并以收货方付款形式退回。
				</td>
				<td colspan=14 style='mso-ignore: colspan'></td>
				<td colspan=2 style='mso-ignore: colspan'>
					款项将退回您的帐户.
				</td>
				<td class=xl71>
				</td>
			</tr>
			<tr height=15 style='height: 14.25pt'>
				<td height=15 class=xl70 colspan=7
					style='height: 14.25pt; mso-ignore: colspan'>
					4.原则上，因退货而产生的运费由顾客承担。
				</td>
				<td colspan=3 style='mso-ignore: colspan'></td>
				<td colspan=7 style='mso-ignore: colspan' x:str="电话：（020）86265336  ">
					电话：（020）86265336
					<span style='mso-spacerun: yes'>&nbsp;&nbsp;</span>
				</td>
				<td colspan=5 style='mso-ignore: colspan'></td>
				<td class=xl71>
				</td>
			</tr>
			<tr height=15 style='height: 14.25pt'>
				<td height=15 class=xl75 colspan=3
					style='height: 14.25pt; mso-ignore: colspan'>
					〈温馨提示〉
				</td>
				<td colspan=7 style='mso-ignore: colspan'></td>
				<td colspan=5 style='mso-ignore: colspan'>
					邮政编码: 510410
				</td>
				<td colspan=7 style='mso-ignore: colspan'></td>
				<td class=xl71>
				</td>
			</tr>
			<tr height=15 style='height: 14.25pt'>
				<td height=15 class=xl72 colspan=6
					style='height: 14.25pt; mso-ignore: colspan'>
					商品的长短及腰身尺寸修改由客户自行负责
				</td>
				<td class=xl65>
				</td>
				<td class=xl65>
				</td>
				<td class=xl65>
				</td>
				<td class=xl65>
				</td>
				<td class=xl65>
				</td>
				<td class=xl65>
				</td>
				<td class=xl65>
				</td>
				<td class=xl65>
				</td>
				<td class=xl65>
				</td>
				<td class=xl65>
				</td>
				<td class=xl65>
				</td>
				<td class=xl65>
				</td>
				<td class=xl65>
				</td>
				<td class=xl65>
				</td>
				<td class=xl65>
				</td>
				<td class=xl65>
				</td>
				<td class=xl73>
				</td>
			</tr>
			<tr height=15 style='height: 14.25pt'>
				<td height=15 colspan=23
					style='height: 14.25pt; mso-ignore: colspan'></td>
			</tr>
			<tr height=15 style='mso-height-source: userset; height: 15.75pt'>
				<td height=15 class=xl82 colspan=23
					style='height: 15.75pt; mso-ignore: colspan; border-right: .5pt solid black'>
					备注：建议在外包装纸箱上添加关于快递的备注：请当快递员面检查后签收，如有数量，以及商品问题请拒绝签收！并及时联系我方客服中心，
				</td>
			</tr>
			<tr height=15 style='height: 14.25pt'>
				<td height=15 class=xl85 colspan=5
					style='height: 14.25pt; mso-ignore: colspan'>
					若请他人代收，事先请做好沟通作用，
				</td>
				<td colspan=17 class=xl79 style='mso-ignore: colspan'>
				</td>
				<td class=xl86>
				</td>
			</tr>
			<tr height=20 style='mso-height-source: userset; height: 19.5pt'>
				<td height=20 class=xl87 colspan=13
					style='height: 19.5pt; mso-ignore: colspan'>
					如在签收后发现任何因快递问题造成的损坏，我方将很难向快递公司索赔。
				</td>
				<td class=xl88>
				</td>
				<td class=xl88>
				</td>
				<td class=xl88>
				</td>
				<td class=xl88>
				</td>
				<td class=xl88>
				</td>
				<td class=xl88>
				</td>
				<td class=xl88>
				</td>
				<td class=xl88>
				</td>
				<td class=xl88>
				</td>
				<td class=xl89>
				</td>
			</tr>
			<tr height=15 style='height: 14.25pt'>
				<td height=15 colspan=23 class=xl81
					style='height: 14.25pt; mso-ignore: colspan'>
				</td>
			</tr>
			<tr height=15 style='height: 14.25pt'>
				<td height=15 colspan=23 class=xl81
					style='height: 14.25pt; mso-ignore: colspan'>
				</td>
			</tr>
			<![if supportMisalignedColumns]>
			<tr height=0 style='display: none'>
				<td width=40 style='width: 30pt'></td>
				<td width=48 style='width: 36pt'></td>
				<td width=44 style='width: 33pt'></td>
				<td width="295" style='width: 221pt'></td>
				<td width=33 style='width: 25pt'></td>
				<td width=33 style='width: 25pt'></td>
				<td width=33 style='width: 25pt'></td>
				<td width=33 style='width: 25pt'></td>
				<td width=33 style='width: 25pt'></td>
				<td width=33 style='width: 25pt'></td>
				<td width=33 style='width: 25pt'></td>
				<td width=29 style='width: 22pt'></td>
				<td width=29 style='width: 22pt'></td>
				<td width=29 style='width: 22pt'></td>
				<td width=29 style='width: 22pt'></td>
				<td width=29 style='width: 22pt'></td>
				<td width=29 style='width: 22pt'></td>
				<td width=51 style='width: 38pt'></td>
				<td width=51 style='width: 38pt'></td>
				<td width=29 style='width: 22pt'></td>
				<td width=45 style='width: 34pt'></td>
				<td width=127 style='width: 95pt'></td>
				<td width=81 style='width: 61pt'></td>
			</tr>
			<![endif]>
			</logic:iterate>
		</table>
	</body>

</html>



