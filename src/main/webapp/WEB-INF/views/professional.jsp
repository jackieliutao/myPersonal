<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>部门管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/public.css" media="all" />
    <style>
        body .layui-form-selectup dl {
            top: 42px;
            bottom: unset;
        }
    </style>
</head>
<body class="childrenBody">
<form class="layui-form">
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" class="layui-input searchVal" placeholder="请输入职称名称" />
                </div>
                <a class="layui-btn search_btn" data-type="reload">搜索</a>
            </div>
            <div class="layui-inline">
                <a class="layui-btn layui-btn-normal addNews_btn">添加职称</a>
            </div>
            <div class="layui-inline">
                <a class="layui-btn layui-btn-danger layui-btn-normal delAll_btn">批量删除</a>
            </div>
        </form>
    </blockquote>
    <table id="proList" lay-filter="proList">

    </table>

    <!--操作-->
    <script type="text/html" id="newsListBar">
        <buttton class="layui-btn layui-btn-xs" lay-event="edit">编辑</buttton>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>
</form>

<div id="addDep" style="display: none;padding: 20px 30px 10px 10px;">
    <form class="layui-form" id="addForm" lay-filter="addForm" action="/professional/addProfessional" method="post">
        <div class="layui-form-item">
            <input type="hidden" name="proId" id="proId">
            <label class="layui-form-label">职称名称</label>
            <div class="layui-input-block">
                <input type="text" name="proName" required  lay-verify="required" placeholder="请输入职称名称" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">选择部门</label>
            <div class="layui-input-block">
                <select name="department.depId" id="selDep" lay-verify="required">
                </select>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/myjs/professional.js"></script>
</body>
</html>