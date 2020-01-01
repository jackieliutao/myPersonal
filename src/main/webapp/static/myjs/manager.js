layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;
    //管理
    var tableIns = table.render({
        elem: '#depList',
        url : '/manage/pageList',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limit : 10,
        limits : [5,10,15,20],
        id : "depListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {title:'序号',filed:'', fixed:"left", type:'numbers', width:80},
            {field: 'username', title: '管理编号', width:300, align:"center"},
            {field: 'status', title: '管理管理', align:'center',templet:function(d){
                    if(d.status){
                        return "<span style='color: #0000FF'>启用</span>";
                    }else{
                        return "<span style='color: red'>暂未启用</span>";
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
        addDep("添加管理");
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

    function addDep(title){
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
                if(data.id != null && data.id !==''){
                    url = '/manage/update'
                }else{
                    url =  '/manage/add'
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

    $('.stopAll_btn').click(function () {
        var checkStatus = table.checkStatus('depListTable'),
            data = checkStatus.data,
            ids = [];
        if(data.length > 0) {
            for(var i = 0;i<data.length;i++){
                ids.push(data[i].id);
            }

            layer.confirm("确定切换状态吗？",{icon:3,title:'提示信息'},function (index) {
                $.ajax({
                    url:'/manage/changeStatus',
                    data:{
                        ids:ids,
                    },
                    traditional:true,
                    success:function (res) {
                        layer.close(index);
                        layer.msg(res.msg);
                        if(res.success){
                            tableIns.reload();
                        }
                    }
                })
            })
        }else{
            layer.msg("请选择数据");
        }
    });

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('depListTable'),
            data = checkStatus.data,
            ids = [];
        if(data.length > 0) {
            for (var i in data) {
                ids.push(data[i].id);
            }
            layer.confirm('确定删除选中的管理？', {icon: 3, title: '提示信息'}, function (index) {
                $.ajax({
                    url:'/manage/deleteAll',
                    type:'post',
                    data:{ids:ids},
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
            layer.msg("请选择需要删除的管理");
        }
    });

    //列表操作
    table.on('tool(depList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit'){ //编辑
            if(data.department){
                addDep("编辑管理");
            }else{
                addDep("编辑管理");
            }

            form.val("addForm",obj.data);

            layui.form.render("select");
        } else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此管理？',{icon:3, title:'提示信息'},function(index){
                $.post("/manage/delete",{
                    id : data.id  //将需要删除的newsId作为参数传入
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