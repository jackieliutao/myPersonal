layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;
    //新闻列表
    var tableIns = table.render({
        elem: '#depList',
        url : '/department/listDepartment',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limit : 10,
        limits : [5,10,15,20],
        id : "depListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field:'depId',title:"部门编号",width:200},
            {field: 'depName', title: '部门名称', width:560},
            {field:'count',title:"总人数",width:300},
            {title: '操作', width:170, templet:'#newsListBar',fixed:"right",align:"center"}
        ]]
    });

    //是否置顶
    form.on('switch(newsTop)', function(data){
        var index = layer.msg('修改中，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
            layer.close(index);
            if(data.elem.checked){
                layer.msg("置顶成功！");
            }else{
                layer.msg("取消置顶成功！");
            }
        },500);
    });

    $(".search_btn").on("click",function(){
        if($(".searchVal").val() != ''){
            table.reload("depListTable",{
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    key: $(".searchVal").val()  //搜索的关键字
                }
            })
        }else{
            layer.msg("请输入搜索的内容");
        }
    });

    var addDialog = null;
    $(".addNews_btn").click(function(){
        $('#addForm')[0].reset();
        layui.form.render();
        addDep("添加部门",null);
    });
    function addDep(title,id){
        form.val('addForm',{
            "depId":"",
            "depName":"",
        });
        addDialog = layui.layer.open({
            title:title,
            type:1,
            content:$('#addDep'),
            area:["500px","300px"],
            btn:['提交','重置'],
            yes:function (index,layer) {
                var data = form.val("addForm");
                var url;
                if(data.depId != null && data.depId !==''){
                    url = '/department/update'
                }else{
                    url =  '/department/addDepartment'
                }
                $.post(url,data,function (res) {
                    layui.layer.msg(res.msg);
                    if(res.success){
                        tableIns.reload();
                        layui.layer.close(addDialog);
                    }
                });
            },
            btn2:function (index,layer) {
                form.val('addForm',{
                   "depName":'',
                });
                return false;
            }
        });


    }
    //监听添加部门的提交
    form.on('submit(addForm)', function(data){
        var url = '';
        if(data.field.depId == null || data.field.depId===''){
            url = "/department/addDepartment";
        }else{
            url="/department/update";
        }
        $.post(url,data.field,function (response) {
            if(response.success){
                layui.layer.close(addDialog);
                layer.msg(response.msg);
                tableIns.reload();
            }else{
                layer.msg(response.msg);
            }
        });
        return false;
    });

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('depListTable'),
            data = checkStatus.data,
            depIds = [];
        if(data.length > 0) {
            for (var i in data) {
                depIds.push(data[i].depId);
            }
            layer.confirm('确定删除选中的部门？', {icon: 3, title: '提示信息'}, function (index) {
                $.ajax({
                    url:'/department/deleteAll',
                    type:'post',
                    data:{depIds:depIds},
                    traditional:true,
                    success:function (data) {
                        layer.close(index);
                        layer.msg(data.msg);
                        if(data.success){
                            tableIns.reload();
                        }
                    }
                });
            })
        }else{
            layer.msg("请选择需要删除的部门");
        }
    });

    //列表操作
    table.on('tool(depList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit'){ //编辑
            if(data.leader){
                addDep("编辑部门",data.leader.empId);
            }else{
                addDep("编辑部门",null);
            }

            form.val("addForm",{
                "depId":data.depId,
                "depName":data.depName,
            });
            //layuiSelected("selectEmp","2");
        } else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此部门？',{icon:3, title:'提示信息'},function(index){
                $.post("/department/delete",{
                    depId : data.depId  //将需要删除的newsId作为参数传入
                },function(data){
                    if(data.success){
                        layer.msg(data.msg);
                        tableIns.reload();
                        layer.close(index);
                    }else{
                        layer.msg(data.msg);
                    }
                })
            });
        }
    });

});