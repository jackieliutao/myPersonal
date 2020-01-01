<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>学习管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/public.css" media="all" />
</head>
<body class="childrenBody">
<form class="layui-form">
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" class="layui-input searchVal" placeholder="输入员工名.." />
                </div>
                <a class="layui-btn search_btn" data-type="reload">搜索</a>
            </div>
            <div class="layui-inline">
                <a class="layui-btn layui-btn-normal addNews_btn">关联学习</a>
            </div>
            <div class="layui-inline">
                <a class="layui-btn layui-btn-danger layui-btn-normal delAll_btn">批量删除</a>
            </div>
        </form>
    </blockquote>
    <table id="stuList" lay-filter="stuList">

    </table>

    <!--操作-->
    <script type="text/html" id="newsListBar">
        <buttton class="layui-btn layui-btn-xs" lay-event="edit">编辑</buttton>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>
</form>

<div id="addStudy" style="display: none;padding: 20px 30px 10px 10px;">
    <form class="layui-form" id="addForm" lay-filter="addForm" action="/department/addDepartment" method="post">
        <div class="layui-form-item">
            <input type="hidden" name="sId" id="sid">
            <label class="layui-form-label">员工编号</label>
            <div class="layui-input-block">
                <input type="text" id="empId" name="employee.empId" required  lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">学习内容</label>
            <div class="layui-input-block">
                <input type="text" name="sContent" required  lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">学习地点</label>
            <div class="layui-input-block">
                <input type="text" name="sAddress" required  lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
    </form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/myjs/study.js"></script>
</body>
</html>