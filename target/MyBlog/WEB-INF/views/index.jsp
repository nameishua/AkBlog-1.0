<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String contextPath = request.getContextPath();
%>
<html>
<head>
    <title>AK的个人学习心得</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<%=contextPath%>/static/mycss.css" rel="stylesheet"/>
    <audio src="<%=contextPath%>/static/music/music.mp3" id="bgAudio" hidden="true" autoplay="true" loop="true"></audio>

</head>
<body>
<script>
    document.getElementById("bgAudio").volume = 0.1;
</script>
<script type="text/javascript" color="0,0,255" opacity='0.7' zIndex="-2" count="99" src="//cdn.bootcss.com/canvas-nest.js/1.0.1/canvas-nest.min.js"></script>
<jsp:include page="comm/top.jsp"/>
<div id="row1" class="row">
<%--内容--%>
</div>

<div id="row2" class="row">
    <div class="container">
        <div class="jumbotron">
            <div align="center">
                <a href="javascript:void(0)" id="uppage" style="font-size:15px;background-color:#f0e7e7;">上一页</a>
                <span id="span1"></span>
                <input type="hidden" value="" id="thecurrentPage" name="thecurrentPage">
                <input type="hidden" value="" id="userid" name="userId">
                <a href="javascript:void(0)" id="downpage" style="font-size:15px;background-color:#f0e7e7;">下一页 </a>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    $(function(){
       /*上一页*/
        $("#uppage").click(function(){
            var Page = $("#thecurrentPage").val();
            var currentPage = parseInt(Page);
            /*console.log(currentPage);*/

            if (currentPage < 1) {
                showMain(1);
            }else {
                showMain(currentPage - 1);
            }
        });
        /*下一页*/
        $("#downpage").click(function(){
            var Page = $("#thecurrentPage").val();
            var pages = $("#pages").val();
            var currentPage = parseInt(Page);
            var thepages = parseInt(pages);
           /* console.log(currentPage);
            console.log(thepages);*/
            if (!(currentPage < thepages)) {
                showMain(thepages);
            }else {
                showMain(currentPage + 1);
            }

        });
    })
    /*显示*/
    function showMain(currentPage){

        console.log("currentPage:"+currentPage);
        var item;
        var item2;
        $.ajax({
            url:"/page",
            type:"get",
            data:"currentPage=" + currentPage +"&categoryId=" +1,
            dataType:"json",
            success:function(data) {
                //console.log(data);
//style="width:100%; height:50%; background:url(<%=contextPath%>/static/img/backgroundimg.png);"
                $("#row1").empty();
                $("#thecurrentPage").empty();
                $("#span1").empty();
                $.each(data.list, function (i, Object) {
                    item= '<div id="container" class="container">'
                        +'<div class="jumbotron">'
                        +'<h3>'+Object.title+'</h3>'
                        +'<span class="summary">'+Object.summary+'</span><br><br>'
                        +'<p><a class="btn btn-primary btn-lg" href="/detail/'+Object.id+'/firstPage"'
                        +'role="button">阅读全文</a></p>'
                        +'</div>'
                        +'</div>'

                    $("#row1").append(item);

                })
                item2 = "<input type='hidden' value='"+data.currentPage+"' id='thecurrentPage' name='thecurrentPage'>"
                    +"<span id='currentP'>第"+data.currentPage+"页</span>"
                    +"<input type='hidden' value='"+data.pages+"' id='pages' name='pages'>"

                $("#span1").append(item2);
            }
        });
    }
</script>
<script>
    $(function(){
        showMain(1);
    });
</script>
<jsp:include page="comm/foot.jsp"/>
</body>
</html>
