<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String contextPath = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <title>EditorMD</title>
    <link type="text/css" href="static/editormd/lib/codemirror/codemirror.min.css">
    <script type="text/javascript" src="static/jquery-3.1.1.js"></script>
    <script type="text/javascript" src="static/editormd/editormd.js"></script>
    <link rel="stylesheet" href="static/editormd/css/editormd.css">
</head>

<body>
<div id="layout">
    <header>
        <h1>动态创建 Editor.md</h1>
        <p>Dynamic create Editor.md</p>
        <br>
        <div class="btns" style="margin:0;">
            <button id="create-btn">动态创建一个 Editor.md</button>
            <button id="remove-btn">移除 Editor.md</button>
        </div>
    </header>
</div>
<script type="text/javascript">
    var testEditormd;

    $(function () {
        $("#create-btn").click(function () {

            $("#layout").append("<div id=\"test-editormd\"></div>");

            testEditormd = editormd("test-editormd", {
                width: "90%",
                height: 640,
                emoji: true,
                markdown: "### 动态创建 Editor.md\r\n\r\nDynamic create Editor.md",
                path: 'static/editormd/lib/',
                imageUpload : true,
                imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
                imageUploadURL : "imageUpload"
            });

        });

        $("#remove-btn").click(function () {
            testEditormd.editor.remove();
        });
    });
</script>

</body>
</html>