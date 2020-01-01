<%--
  Created by IntelliJ IDEA.
  User: dongpo
  Date: 2019/11/20
  Time: 8:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>员工管理</title>
    <meta name="renderer" content="webkit">
    <meta name="referrer" content="never">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" media="all"/>
    <style>
        html,body{
            padding: 0;
            margin: 0;
            height: 400px;
        }
        .info-side{
            width: 1000px;
            height: 350px;
            box-sizing: border-box;
            position: relative;
        }
        .myContent{
            width: 100%;
            height: 85%;
            overflow: auto;
            padding-bottom: 20px;
        }
        .myContent #addForm{
            box-sizing: border-box;
        }
        .bottom>.myBtn{
            width: 100%;
            padding-left: 800px;
            box-sizing: border-box;
        }
        #birthday{
            border: 1px solid #e6e6e6;
            color: #666666;
            height: 35px;
        }
    </style>
</head>
<body>
<div class="info-side">
    <div class="top">
        <div class="layui-tab layui-tab-brief myTab" lay-filter="docDemoTabBrief">
            <ul class="layui-tab-title">
                <li class="layui-this">员工信息</li>
                <li>家庭关系</li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show myContent">

                </div>
                <div class="layui-tab-item myContent">
                    <%--家庭信息--%>
                    <div class="familyInfo myInfo" id="fmInfo"></div>
                </div>
            </div>
            <%--    按钮组--%>
        </div>
    </div>
    <div class="bottom">
        <div class="myBtn">
            <button class="layui-btn" id="saveEmp">保存</button>
            <button class="layui-btn">重置</button>
        </div>
    </div>
</div>



<script type="text/javascript" src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/myjs/employeeAdd.js"></script>

</body>
</html>
