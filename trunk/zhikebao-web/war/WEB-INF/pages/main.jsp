<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>知客宝商城应用系统</title>
<link rel="stylesheet" type="text/css" href="/css/gxt-all.css" />
<link rel="stylesheet" type="text/css" href="/themes/default/css/resources.css" />
<link rel="stylesheet" type="text/css" href="/themes/default/css/main.css" />
<script type="text/javascript" src="/js/ext/ext-lang-zh_CN.js"></script>
<style>
* {
  margin: 0px;
  padding: 0px;
}

#loading {
  position: absolute;
  left: 45%;
  top: 40%;
  margin-left: -45px;
  padding: 2px;
  z-index: 20001;
  height: auto;
  border: 1px solid #ccc;
}

#loading a {
  color: #225588;
}

#loading .loading-indicator {
  background: white;
  color: #444;
  font: bold 13px tahoma, arial, helvetica;
  padding: 10px;
  margin: 0;
  height: auto;
}

#loading .loading-indicator img {
  margin-right:8px;
  float:left;
  vertical-align:top;
}

#loading-msg {
  font: normal 10px arial, tahoma, sans-serif;
}
</style>
</head>
<body style="overflow: hidden">
<div id="loading">
    <div class="loading-indicator"><img src="/images/default/shared/large-loading.gif" width="32" height="32" style="margin-right:8px;float:left;vertical-align:top;"/>知客宝商城应用系统<a href="http://www.zhikebao.com"></a><br /><span id="loading-msg">加载中...</span></div>
</div>
<script language="javascript" src="/zhikebao/zhikebao.nocache.js"></script>
<iframe src="javascript:''" id="__gwt_historyFrame" style="width:0;height:0;border:0"></iframe>
</body>
</html>
