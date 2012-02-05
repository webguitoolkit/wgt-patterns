<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
  <meta http-equiv="content-type" content="text/html; charset=utf-8" />
  <title>Custom Buttons 3.0 (demo)</title>
  <style type="text/css">
    body {
      background:#fff;
      padding:20px;
      color:#000;
      font-family:Arial,sans-serif;
      font-size:13px;
      line-height:1.3;
      }
    h1 {margin:0 0 .25em; font-size:1.8em;}
    h2 {margin:1.5em 0 .25em; font-size:1.2em;}
    code {font:12px Monaco,monospaced; color:#963;}
    
    /* Start custom button CSS here
    ---------------------------------------- */
    .btn {
      display:inline-block;
      background:none;
      margin:0;
      padding:3px 0;
      border-width:0;
      overflow:visible;
      font:100%/1.2 Arial,Sans-serif;
      text-decoration:none;
      color:#333;
      }
    * html button.btn {
      padding-bottom:1px;
      }
    /* Immediately below is a temporary hack to serve the 
       following margin values only to Gecko browsers
       Gecko browsers add an extra 3px of left/right 
       padding to button elements which can't be overriden.
       Thus, we use -3px of left/right margin to overcome this. */
    html:not([lang*=""]) button.btn {
      margin:0 -3px;
      }
    .btn span {
      background:#ddd url(images/bg-button.gif) repeat-x 0 0;
      margin:0;
      padding:3px 0;
      border-left:1px solid #bbb;
      border-right:1px solid #aaa;
      }
    * html .btn span {
      padding-top:0;
      }
    .btn span span {
      position:relative;
      padding:3px .4em;
      border-width:0;
      border-top:1px solid #bbb;
      border-bottom:1px solid #aaa;
      }
    
    /* pill classes only needed if using pill style buttons ( LEFT | CENTER | RIGHT ) */
    button.pill-l span {
      border-right-width:0;
      }
    button.pill-l span span {
      border-right:1px solid #bbb;
      }
    button.pill-c span {
      border-right-style:none;
      border-left-color:#fff;
      }
    button.pill-c span span {
      border-right:1px solid #bbb;
      }
    button.pill-r span {
      border-left-color:#fff;
      }
    
    /* only needed if implementing separate hover/focus/active state for buttons */
    .btn:hover span, .btn:hover span span, .btn:focus span, .btn:focus span span {
      cursor:pointer; /* important for any clickable/pressable element */
      border-color:#9cf !important;
      color:#000;
      }
    .btn:active span {
      background-position:0 -400px;
      outline:none;
      }
    .btn:focus, .btn:active {
      outline:none; /* doesn't seem to be respected for <button>, only <a> */
      }

    /* use if one button should be the 'primary' button */
    .primary {
      font-weight:bold;
      color:#000;
      }
  </style>
</head>

<body>

<h1>Custom Buttons 3.0 (demo)</h1>

<p class="s0"><strong>Updated:</strong> 27 Feb 2008</p>

<p>This doc attempts to show how custom buttoms with a 1px radius could be created without corner images. The gradient requires an image, but is optional. Ideally, the <code>&lt;button&gt;</code> element should be used if possible (instead of <code>&lt;a&gt;</code>) for semantic and accessibility reasons. Note that the hover state will not work on <code>button</code> elements for IE6 and lower without using JavaScript to detect a mouseOver event.</p>

<form action="#">
  <p><a href="#" class="btn"><span><span>button</span></span></a> &nbsp;<code>&lt;a&gt;</code></p>

  <p><button type="button" class="btn"><span><span>button</span></span></button> &nbsp;<code>&lt;button&gt;</code></p>

  <p><a href="#" class="btn"><span><span>longer button text</span></span></a> &nbsp;<code>&lt;a&gt;</code></p>

  <p><button type="button" class="btn"><span><span>longer button text</span></span></button> &nbsp;<code>&lt;button&gt;</code></p>

  <p><button type="button" class="btn"><span><span>button</span></span></button>&nbsp;<button type="button" class="btn"><span><span>button</span></span></button>&nbsp;<button type="button" class="btn"><span><span>button</span></span></button> &nbsp;<code>&lt;button&gt; &lt;button&gt; &lt;button&gt;</code></p>
  
  <p><button type="button" class="btn primary"><span><span>Save</span></span></button>&nbsp;<button type="button" class="btn"><span><span>Cancel</span></span></button>
  &nbsp;<code>&lt;button class="primary"&gt; &lt;button&gt;</code></p>
  
  <p><button type="button" class="btn pill-l"><span><span>Left</span></span></button><button type="button" class="btn pill-c"><span><span>Center</span></span></button><button type="button" class="btn pill-r"><span><span>Right</span></span></button>
  &nbsp;<code>&lt;button class="pill-l"&gt;&lt;button class="pill-c"&gt;&lt;button class="pill-r"&gt;</code></p>

</form>

<h2>Reasons for 2 nested spans:</h2>
<ol>
  <li>The inner <code>span</code> creates the top and bottom borders. Because <code>button</code> and <code>a</code> are inline elements, top/bottom borders and padding do not affect the height of parent elements (such as the outer <code>span</code>). So with the same top/bottom padding of both <code>span</code> elements, the left/right borders of the outer <code>span</code> stop short of the top/bottom borders of the inner <code>span</code>.</li>
  <li>The outer <code>span</code> is necessary to fix bugs in Gecko-based browsers and in IE (separate issues for both). Gecko browsers add an extra 3px of padding inside <code>button</code> elements which can't be eliminated. Without the outer span, top/bottom and left/right borders wouldn't connect. IE just has trouble styling <code>button</code>s and nested elements inside them, so the majority of visible styles are applied to two inner <code>span</code>s.</li>
</ol>


</body>
</html>
