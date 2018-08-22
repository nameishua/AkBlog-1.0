<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html>
<head>
    <title>详情</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <audio src="<%=contextPath%>/static/music/music4.mp3" id="bgAudio" hidden="true" autoplay="true" loop="true"></audio>
</head>
<body>
<script>
    document.getElementById("bgAudio").volume = 0.1;
</script>
<script type="text/javascript" color="0,0,255" opacity='0.7' zIndex="-2" count="99" src="//cdn.bootcss.com/canvas-nest.js/1.0.1/canvas-nest.min.js"></script>
<jsp:include page="comm/top.jsp"/>
<div class="container">
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">${article.title}</h3>
        </div>
        <div class="panel-body">

            <span>${article.content}</span>
        </div>
    </div>
</div>
<jsp:include page="comm/foot.jsp"/>
<%--<script>
    $('.img').css({'width':'100%','height':'auto','visibility':'inherit'});
</script>--%>
</body>
</html>