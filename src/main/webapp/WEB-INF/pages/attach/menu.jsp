<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-inverse" role="navigation">
    <div class="navbar-header">
        <a class="navbar-brand" href="#">BING－学堂</a>
    </div>
    <div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="/web/index">主页</a></li>
            <li><a href="/web/sale">集团销量</a></li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    商品管理 <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="/web/goods/list">商品列表</a></li>
                    <li class="divider">
                    <li><a href="/web/goods/purchase/query">购买记录</a></li>
                    <li class="divider">
                    <li><a href="/web/goods/manage/list">商品信息维护</a></li>
                    <li class="divider">
                    <li><a href="/web/goods/mytab">tab页面测试</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    第一学习阶段 <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="#">redis学习</a></li>
                    <li><a href="#">java线程池</a></li>
                    <li><a href="#">Sprint Aop/aspectj</a></li>
                    <li class="divider">
                    <li><a href="#">nginx学习</a></li>
                    <li><a href="#">nsq</a></li>
                    <li><a href="#">disconf</a></li>
                    <li class="divider">
                    <li><a href="#">maven学习</a></li>
                    <li><a href="#">mongodb</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    第二学习阶段 <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="#">rpc/dubbo</a></li>
                    <li><a href="#">zk</a></li>
                    <li><a href="#">es</a></li>
                    <li class="divider">
                    <li><a href="#">memcache</a></li>
                    <li><a href="#">activemq</a></li>
                    <li><a href="#">twemproxy</a></li>
                    <li class="divider">
                    <li><a href="#">mycat</a></li>
                    <li><a href="file:///Users/xubing/Desktop/test.html">三级菜单</a></li>
                </ul>
            </li>
        </ul>
    </div>
</nav>
