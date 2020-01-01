layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;
    //职称列表
    var tableIns = table.render({
        elem: '#proList',
        url : '/professional/listProfessional',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limit : 10,
        limits : [5,10,15,20],
        id : "proListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'proId', title: '职称编号', width:200, align:"center"},
            {field: 'proName', title: '职称名称', width:350},
            {field: 'department', title: '所属部门', align:'center',templet:function(d){
                    if(d.department){
                        return d.department.depName;
                    }else{
                        return "暂未添加";
                    }
                }},
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
            table.reload("proListTable",{
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
        addDep("添加职称",null);
    });
    //leader下拉列表的加载
    function loadLeader(id) {
        $.get("/department/getAll",function (data) {
            if(data){
                $(data).each(function (index, value) {
                    $('#selDep').append("<option value='"+value.depId+"'>"+value.depName+"</option>");
                });
            }
            if(id!=null && id!==''){
                $('#selDep').val(id);
            }
            form.render('select');
        });
    }
    function addDep(title,id){
        loadLeader(id);
        $('#selDep').empty();
        $('#selDep').append("<option value=''></option>");
        form.val('addForm',{
            "proId":"",
            "proName":"",
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
                if(data.proId != null && data.proId !==''){
                    url = '/professional/update'
                }else{
                    url =  '/professional/addProfessional'
                }
                if($('#selDep').val() == null || $('#selDep').val() == ''){
                    layui.layer.msg("请选择部门");
                    return;
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
                   "proName":'',
                });
                $('#selDep').val("");
                form.render('select','addForm');
                return false;
            }
        });


    }
    //监听添加部门的提交
    form.on('submit(addForm)', function(data){
        var url = '';
        if(data.field.proId == null || data.field.proId===''){
            url = "/professional/addProfessional";
        }else{
            url="/professional/update";
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
        var checkStatus = table.checkStatus('proListTable'),
            data = checkStatus.data,
            proIds = [];
        if(data.length > 0) {
            for (var i in data) {
                proIds.push(data[i].proId);
            }
            layer.confirm('确定删除选中的职称？', {icon: 3, title: '提示信息'}, function (index) {
                $.ajax({
                    url:'/professional/deleteAll',
                    type:'post',
                    data:{proIds:proIds},
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
            layer.msg("请选择需要删除的职称");
        }
    });

    //列表操作
    table.on('tool(proList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit'){ //编辑
            if(data.department){
                addDep("编辑职称",data.department.depId);
            }else{
                addDep("编辑职称",null);
            }
            form.val("addForm",{
                "proId":data.proId,
                "proName":data.proName,
            });

            //layuiSelected("selectEmp","2");
            layui.form.render("select");
        } else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除？',{icon:3, title:'提示信息'},function(index){
                $.post("/professional/delete",{
                    proId : data.proId  //将需要删除的newsId作为参数传入
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