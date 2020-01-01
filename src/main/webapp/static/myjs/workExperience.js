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
        url : '/workExperience/pageList',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limit : 10,
        limits : [5,10,15,20],
        id : "depListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {title:'序号',filed:'', fixed:"left", type:'numbers', width:80},
            {field: 'employee', title: '员工姓名', align:'center',templet:function(d){
                    if(d.employee){
                        return d.employee.empName;
                    }else{
                        return "暂未添加";
                    }
                }},
            {field: 'wbCompany', title: '在职公司', width:200, align:"center"},
            {field: 'wbPosition', title: '所属职位', width:150},
            {field: 'wbStart', title: '开始时间', width:150},
            {field: 'wbEnd', title: '结束时间', width:150},
            {title: '操作', width:170, templet:'#newsListBar',fixed:"right",align:"center"}
        ]]
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
        $('#wbId').val('');
        addWork("添加工作经历");
    });
    //leader下拉列表的加载
    function addWork(title){
        addDialog = layui.layer.open({
            title:title,
            type:1,
            content:$('#addWork'),
            area:["500px","400px"],
            btn:['提交','重置'],
            yes:function (index,layer) {
                var data = form.val("addForm");
                var url;
                if(data.wbId != null && data.wbId !==''){
                    url = '/workExperience/update'
                }else{
                    url =  '/workExperience/add'
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

    //开始时间

    //结束时间
    laydate.render({
        elem:"#wbStart",
        trigger:"click"
    });
    laydate.render({
        elem:"#wbEnd",
        trigger:"click"
    });

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('depListTable'),
            data = checkStatus.data,
            wbIds = [];
        if(data.length > 0) {
            for (var i in data) {
                wbIds.push(data[i].wbId);
            }
            layer.confirm('确定删除选中的工作经历？', {icon: 3, title: '提示信息'}, function (index) {
                $.ajax({
                    url:'/workExperience/deleteAll',
                    type:'post',
                    data:{wbIds:wbIds},
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
            $('#empId').val(obj.data.employee.empId);
            addWork("编辑工作经历");
            form.val("addForm",obj.data);
            layui.form.render();
        } else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此工作经历？',{icon:3, title:'提示信息'},function(index){
                $.post("/workExperience/delete",{
                    wbId : data.wbId  //将需要删除的newsId作为参数传入
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