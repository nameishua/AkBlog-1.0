<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<script src="<%=contextPath%>/static/jquery-3.1.1.js"></script>
<script src="<%=contextPath%>/static/bootstrap/js/bootstrap.js"></script>
<link href="<%=contextPath%>/static/bootstrap/css/bootstrap.css" rel="stylesheet"/>
<link href="<%=contextPath%>/static/bootstrap/css/bootstrap-theme.css" rel="stylesheet"/>
<div class="row">
    <nav class="navbar navbar-inverse">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<%=contextPath%>/">AK的小屋</a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav" id="nv1">
                    <li class="active" id="firstPage"><a href="<%=contextPath%>/">首页</a></li>
                    <li id="webPage"><a href="<%=contextPath%>/column/jottings/jottings">随笔</a></li>
                    <li id="androidPage"><a href="<%=contextPath%>/column/excerpt /excerpt">摘录</a></li>

                </ul>
                <form class="navbar-form navbar-right">
                    <<div class="input-group">
                        <%--<input type="text" class="form-control" placeholder="搜索">--%>
                        <input type="button" style="background-color:#313131;color:#9D9D9D;border:none;" value="嘿,不要找了,后台入口只有站长知道^-^"></li>
                        <input type="button" style="background-color:#0F192A;color:#9D9D9D;border:none;" id="play"value="Music-Play/Stop"></li>
                        <input type="button" style="background-color:#0F192A;color:#9D9D9D;border:none;" id="up"value="Volume-Up"></li>
                        <input type="button" style="background-color:#0F192A;color:#9D9D9D;border:none;" id="down"value="Volume-Down"></li>
                        <%--<span class="input-group-btn">
                        <button class="btn btn-default" type="button">Go!</button>
                    </span>--%>
                    </div>
                </form>
            </div>
        </div>
    </nav>
</div>
<script>
    var media = $('#bgAudio')[0];

    var audioTimer = null;

    $('#up').bind('click', function() {
        up();
    });
    $('#down').bind('click', function() {
        down();
    });

    //up
    function up() {
        var volume = media.volume + 0.1;
        media.volume =volume;
        if(volume >=1 ) {
            volume = 1;
            media.volume = volume;
        }
    }

    //down
    function down() {
        var volume = media.volume - 0.1;
        media.volume = volume;
        if(volume <=0 ) {
            volume = 0;
            media.volume = volume;
        }
    }

    //绑定播放暂停控制
    $('#play').bind('click', function() {
        playAudio();
    });

    //播放暂停切换
    function playAudio() {
        if(media.paused) {
            play();
        } else {
            pause();
        }
    }

    //播放
    function play() {
        media.play();
        //$('#play').html('Pause');
    }

    //暂停
    function pause() {
        media.pause();
        //$('#play').html('Play');
    }
</script>

<script>
    var href = location.href;
    var id = href.substring(href.lastIndexOf("/") + 1, href.length);
    if (id=="") {
        id = "firstPage";
    }
    var ids = ["firstPage", "jottings", "jottings"];
    for (var i = 0; i < ids.length; i++) {
        if (id == ids[i]) {
            $("#" + id).attr("class", "active");
        } else {
            $("#" + ids[i]).removeAttr("class");
        }
    }
</script>