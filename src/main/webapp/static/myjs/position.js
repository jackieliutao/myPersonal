layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;
    //部门
    var tableIns = table.render({
        elem: '#depList',
        url : '/position/pageList',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limit : 10,
        limits : [5,10,15,20],
        id : "depListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {title:'序号',filed:'', fixed:"left", type:'numbers', width:80},
            {field: 'poNum', title: '职位编号', width:200, align:"center"},
            {field: 'poName', title: '职位名称', width:350},
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

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
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
        addDep("添加职位",null);
    });
    //leader下拉列表的加载
    function loadDep(id) {
        $.get("/department/getAll",function (data) {
            if(data){
                $(data).each(function (index, value) {
                    $('#selectDep').append("<option value='"+value.depId+"'>"+value.depName+"</option>");
                });
            }
            if(id!=null && id!==''){
                $('#selectDep').val(id);
            }
            form.render('select');
        });
    }
    function addDep(title,id){
        loadDep(id);
        $('#selectDep').empty();
        $('#selectDep').append("<option value=''></option>");
        form.val('addForm',{
            "poId":'',
            "poNum":"",
            "poName":"",
        });
        addDialog = layui.layer.open({
            title:title,
            type:1,
            content:$('#addDep'),
            area:["500px","400px"],
            btn:['提交','重置'],
            yes:function (index,layer) {
                var data = form.val("addForm");
                var url;
                console.log(data);
                if(data.poId != null && data.poId !==''){
                    url = '/position/update'
                }else{
                    url =  '/position/addPosition'
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
                    "poNum":'',
                    "poName":''
                });
                $('#selectDep').val("");
                form.render('select','addForm');
                return false;
            }
        });


    }

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('depListTable'),
            data = checkStatus.data,
            pids = [];
        if(data.length > 0) {
            for (var i in data) {
                pids.push(data[i].poId);
            }
            layer.confirm('确定删除选中的职位？', {icon: 3, title: '提示信息'}, function (index) {
                $.ajax({
                    url:'/position/deleteAll',
                    type:'post',
                    data:{pids:pids},
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
            if(data.department){
                addDep("编辑职位",data.department.depId);
            }else{
                addDep("编辑职位",null);
            }

            form.val("addForm",{
                "poId":data.poId,
                "poNum":data.poNum,
                "poName":data.poName
            });

            layui.form.render("select");
        } else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此职位？',{icon:3, title:'提示信息'},function(index){
                $.post("/position/delete",{
                    pid : data.poId  //将需要删除的newsId作为参数传入
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