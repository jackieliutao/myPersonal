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
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" media="all"/>
    <style>
        body {
            padding: 10px;
            box-sizing: border-box;
            overflow: hidden;
        }

        .layui-table-cell {
            height: 50px;
            line-height: 50px;
        }

        .layui-table-cell .layui-form-checkbox[lay-skin=primary] {
            top: 15px;
        }

        .selTip {
            color: #666666;
        }

        .mySel {
            height: 38px;
            border: 1px solid #e6e6e6;
            overflow: hidden;
            color: #666;
            width: 150px;
            vertical-align: top;
            margin-right: 10px;
        }
        #birthday{
            border: 1px solid #e6e6e6;
            color: #666666;
            height: 35px;
        }
        #addInfo{
            display: none;
        }
        .work{
            width: 100%;
            height: 100%;

        }
        .work-item{
            padding-left: 35px;
            box-sizing: border-box;
            background-color: #eee;
            overflow: hidden;
            padding-bottom: 10px;
        }
        .work-item input{
            width: 250px;
            height: 30px;
            padding-left: 3px;
            border: 1px solid #cccccc;
        }

        .work-item>div{
            margin-top: 8px;
            width: 350px;
            float: left!important;
            font-weight: 400;
        }
        .work-item>div>span{
            padding-right: 5px;
        }
        .work-item>div>button{
            width: 100px;
            height: 35px;
        }
    </style>
</head>
<body>

<blockquote class="layui-elem-quote quoteBox">
    <form class="layui-form">
        <span class="selTip">选择部门</span>
        <select class="mysel" name="city" id="selectDep" lay-verify="" lay-ignore>
            <option value="">请选择部门</option>
        </select>
        <span class="selTip">选择职位</span>
        <select class="mysel" name="city" id="selectPo" lay-verify="" lay-ignore>
            <option value="">请选择职位</option>
        </select>
        <div class="layui-inline">
            <div class="layui-input-inline">
                <input type="text" class="layui-input searchVal" id="searchVal" placeholder="请输入员工姓名或编号"/>
            </div>
            <a class="layui-btn search_btn" id="searchBtn" data-type="reload">搜索</a>
        </div>
        <div class="layui-inline">
            <a class="layui-btn layui-btn-normal addEmpBtn">添加员工</a>
        </div>
        <div class="layui-inline">
            <a class="layui-btn layui-btn-danger layui-btn-normal delAll_btn">批量删除</a>
        </div>
    </form>
</blockquote>



<table id="empTable" lay-filter="empTable">

</table>
<%--选项卡--%>
<div id="addInfo">
    <div class="layui-tab">
        <ul class="layui-tab-title">
            <li class="layui-this">员工信息</li>
            <li>家庭信息</li>
        </ul>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <%--员工信息--%>
                <div class="empInfo myInfo" id="empInfo">
                    <form class="layui-form" lay-filter="addForm" id="addForm" >
                        <input type="hidden" name="empId">
                        <div class="layui-form-item">
                            <label class="layui-form-label">员工姓名</label>
                            <div class="layui-input-block">
                                <input type="text"  required  lay-verify="required" name="empName" placeholder="请输入姓名" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">出生日期</label>
                            <input type="text" readonly id="birthday" placeholder="请选择出生日期" name="empBirthday">
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">选择部门</label>
                            <div class="layui-input-block">
                                <select name="department.depId" lay-filter="department" id="add-selDep" lay-verify="required">
                                </select>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">选择职位</label>
                            <div class="layui-input-block">
                                <select name="position.poId" lay-filter="position" id="add-selPo" lay-verify="required">
                                </select>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">选择职称</label>
                            <div class="layui-input-block">
                                <select name="professional.proId" id="add-selPro" lay-verify="required">
                                </select>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">性别</label>
                            <div class="layui-input-block">
                                <input type="radio" name="empSex" value="男" title="男" checked>
                                <input type="radio" name="empSex" value="女" title="女">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">手机号</label>
                            <div class="layui-input-block">
                                <input type="text"  required  lay-verify="required" name="empPhone" placeholder="请输入手机号" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">QQ</label>
                            <div class="layui-input-block">
                                <input type="text"   name="empQq" placeholder="请输入qq" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">邮箱</label>
                            <div class="layui-input-block">
                                <input type="text" id="email"  name="empEmail" placeholder="请输入邮箱" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text" style="padding-left: 35px;">
                            <div class="myImg" style="width: 150px;height: 100px;margin-bottom: 10px;display: none">
                                <img src="" style="height: 100px;">
                            </div>
                            <button type="button" class="layui-btn" style="" id="uploadImg">
                                <input type="hidden" name="empImg" id="empImg">
                                <i class="layui-icon" name="file">&#xe67c;</i>上传个人照片
                            </button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="layui-tab-item">
                <%--家庭信息--%>
                <div class="familyInfo myInfo">
                    <form class="layui-form" lay-filter="familyForm" id="familyForm"> <!-- 提示：如果你不想用form，你可以换成div等任何一个普通元素 -->
                        <div class="layui-form-item">
                            <input type="hidden" name="faId">
                            <label class="layui-form-label">监护人姓名</label>
                            <div class="layui-input-block">
                                <input type="text" name="faName" placeholder="请输入" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">地址</label>
                            <div class="layui-input-block">
                                <input type="text" name="faAddress" placeholder="请输入" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">职业</label>
                            <div class="layui-input-block">
                                <input type="text" name="faPosition" placeholder="请输入" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">党派</label>
                            <div class="layui-input-block">
                                <input type="text" name="faGroup" placeholder="请输入" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">所属关系</label>
                            <div class="layui-input-block">
                                <input type="radio" name="faRel" value="父亲" title="父亲">
                                <input type="radio" name="faRel" value="母亲" title="母亲" checked>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">电话</label>
                            <div class="layui-input-block">
                                <input type="text" name="faPhone" placeholder="请输入" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


<!--操作-->
<script type="text/html" id="handleBar">
    <buttton class="layui-btn layui-btn-xs" lay-event="edit">编辑</buttton>
    <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/myjs/employee.js"></script>

</body>
</html>
