layui.use(['layer','element','form','laydate','upload','table','laytpl'],function () {
        var form = layui.form,
            layer = layui.layer,
            $ = layui.jquery,
            element = layui.element ,
            laydate = layui.laydate,
            laytpl = layui.laytpl,
            table = layui.table,
            upload = layui.upload;

    var empTable = table.render({
        elem: '#empTable',
        height : "full-125",
        cellMinWidth : 95,
        url: '/employee/pageList', //数据接口,
        page: true, //开启分页，
        limit:10,
        limits:[5,10,15,20]
        ,cols: [[ //表头
            {type: "checkbox", fixed:"left", width:50}
            ,{field: 'empId', title: '编号', width:100, sort: true, fixed: 'left'}
            ,{field: 'empName', title: '员工姓名', width:110}
            ,{field: 'empSex', title: '性别', width:80}
            ,{field: 'empBirthday', title: '出生日期', width:160}
            ,{field: 'position.poId', title: '职位', width:110,templet:function (data) {
                    if(data.position){
                        return data.position.poName;
                    }else{
                        return "暂未分配";
                    }
                }},
            {field: 'department.depName', title: '所属部门', width:160,templet:function (data) {
                    if(data.department){
                        return data.department.depName;
                    }else{
                        return "暂未分配";
                    }
                }},
            {field: 'professional.proName', title: '所属职称', width:160,templet:function (data) {
                    if(data.professional){
                        if(data.professional){
                            return data.professional.proName;
                        }
                    }else{
                        return "暂未分配";
                    }
                }}
            ,{field: 'empPhone', title: '手机号', width: 150}
            ,{field: 'empQq', title: 'qq', width: 160}
            ,{field: 'empEmail', title: '邮箱', width: 160}
            ,{field: 'empImg', title: '照片', width: 100,height:80,templet:function(d){
                        if(d.empImg){
                            var path = getRootPath()+"/upload/"+d.empImg;
                            return "<img style='height: 80px;' src='"+path+"'>";
                        }else{
                            return "暂未添加";
                        }
                    }}
            ,{title: '操作', width:174, templet:'#handleBar',fixed:"right",align:"center"}
        ]]
    });

    function loadSearchDep(){
        $('#selectDep').empty();
        $('#selectDep').append("<option value=''>请选择部门</option>");
        $.get("/department/getAll",function (res) {
            console.log(res);
            $.each(res,function (index,value) {
                $('#selectDep').append("<option value='"+value.depId+"'>"+value.depName+"</option>");
            });
        })
    }
    function loadSearchPo(depId) {
        $.get("/position/getPositionByDepId?depId="+depId,function (res) {
            console.log(res);
            $.each(res,function (index,value) {
                $('#selectPo').append("<option value='"+value.poId+"'>"+value.poName+"</option>");
            });
        })
    }
    loadSearchDep();

    //加载搜索面板的部门
    //搜索面板
    $('#selectDep').change(function () {
        var depId = $(this).val();
        $('#selectPo').empty();
        $('#selectPo').append("<option value=''>请选择职位</option>");
        form.render();

        //重新加载数据
        table.reload("empTable",{
            page:{
                curr:1//第一页开始
            },
            where:{
                depId:depId,
            }
        });
        form.render();
        loadSearchPo(depId);

    });
    $('#selectPo').change(function (){
        var poId = $(this).val();
        table.reload("empTable",{
            page:{
                curr:1
            },
            where:{
                poId:poId,
            },
        });
    });

    function loadDialog(data){
        //添加员工的dialog
        var dialog = layer.open({
            title:'员工界面',
            area:["1000px","500px"],
            type:1,
            content:$('#addInfo'),
            btn:["保存","重置"],
            yes:function () {
                var url;
                if(data != null && data.empId !== '' && data.empId !==null){
                    url="/employee/update"
                }else{
                    url = "/employee/add";
                }
                var email = $('#email').val();
                if(email == null || email === ''){
                    layer.msg("数据输入不完整");
                    return false;
                }
                if(!checkEmail(email)){
                    layer.msg("邮箱不合法");
                    return false;
                }
                var data1 = form.val('addForm');
                var family = form.val('familyForm');
                for(var key in family){
                    data1[key] = family[key];
                }
                $.post(url,data1,function (res) {
                    layer.msg(res.msg);
                    if(res.success){
                        layer.close(dialog);
                        empTable.reload();
                    }
                })
            },
            btn2:function () {
                $("#addForm")[0].reset();
                $("#familyForm")[0].reset();
                //图片重置
                if(data == null){
                    $('.myImg').css('display','none');
                    var path = $('.myImg>img').attr("src");
                    var number = path.lastIndexOf("/");
                    var s = path.substring(number+1);
                    if(path != null && path !== ""){
                        $.post("/employee/deleteImg?path="+s,function (res) {
                            console.log(res.msg);
                        });
                    }
                    $('#empImg').val('');
                    $('.myImg>img').attr("src","");
                }
                form.render();
                return false;
            },
            success:function () {
                loadDep(null);
                if(data != null && data.empId !== '' && data.empId !==null){

                    var depId;
                    //回显部门
                    if(data.department){
                        depId =data.department.depId;
                    }
                    if(depId != null){
                        loadDep(depId);
                        $('#add-selDep').val(depId);
                        if(data.professional){
                            loadProfessional(depId,data.professional.proId);
                        }else{
                            loadProfessional(depId,null);
                        }
                        if(data.position){
                            loadPosition(depId,data.position.poId);
                        }
                    };
                    //图片回显
                    if(data.empImg != null && data.empImg !==""){
                        //回显
                        $('.myImg').css('display',"block");
                        $('.myImg>img').attr('src',getRootPath()+"/upload/"+data.empImg);
                        $('#empImg').val(data.empImg);
                    }else{
                        //重置
                        $('.myImg').css('display',"none");
                        $('.myImg>img').attr('src',"");
                        $('#empImg').val("");
                    }

                }

            }
        });
    }

    function checkEmail(str){
        var
            re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/
        if(re.test(str)){
            return true;
        }else{
            return false;
        }
    }
    //渲染日期
    laydate.render({
        elem:'#birthday',
    });
    //加载部门
    function loadDep(depId) {
        $('#add-selDep').empty();
        $('#add-selDep').append("<option value=''>请选择部门</option>");
        $.get('/department/getAll',function (res) {
            $.each(res,function (index,value) {
                $('#add-selDep').append("<option value='"+value.depId+"'>"+value.depName+"</option>")
            });
            if(depId != null){
                $('#add-selDep').val(depId);
            }
            form.render();
        });
    };

    //加载职位
    function loadPosition(depId,poId){
        $('#add-selPo').empty();
        $('#add-selPo').append("<option value=''>请选择部门</option>");
        form.render();
        if(depId == '' || depId == null){
            return;
        }
        $.get('/position/getPositionByDepId?depId='+depId,function (res) {
            console.log(res);
            $.each(res,function (index,value) {
                $('#add-selPo').append("<option value='"+value.poId+"'>"+value.poName+"</option>")
            });
            if(poId != null){
                $('#add-selPo').val(poId);
            }
            form.render();
        })
    }
    //加载职称
    function loadProfessional(depId,proId){
        $('#add-selPro').empty();
        $('#add-selPro').append("<option value=''>请选择职称</option>");
        form.render();
        if(depId == '' || depId == null){
            return;
        }
        $.get('/professional/getProfessionalByDepId?depId='+depId,function (res) {
            $.each(res,function (index,value) {
                $('#add-selPro').append("<option value='"+value.proId+"'>"+value.proName+"</option>")
            });
            if(proId != null){
                $('#add-selPro').val(proId);
            }
            form.render();
        })
    }
    //监听部门选择的变化
    //监听部门的变化
    form.on('select(department)', function(data){
        console.log(data);
        //加载职位
        loadPosition(data.value,null);
        //加载职称
        loadProfessional(data.value,null);
    });

    //上传图片
    var uploadInst = upload.render({
        elem: '#uploadImg' //绑定元素
        ,url: '/employee/uploadImg' //上传接口
        ,done: function(res){
            //上传完毕回调
            $('.myImg').css('display','block');
            $('.myImg>img').attr('src',getRootPath()+"/upload/"+res.msg);
            $('#empImg').val(res.msg);
        },
        before:function () {
            //先判断img的输入框是否有值，有就去删除图片
            var imgName = $('#empImg').val();
            if(imgName != null && imgName !==""){
                $.post("/employee/deleteImg?path="+imgName,function (res) {
                    console.log(res.msg);
                });
            }
        }
        ,error: function(){
            //请求异常回调
        }
    });

    //添加按钮的点击
    $('.addEmpBtn').click(function () {
        $("#addForm")[0].reset();
        $("#familyForm")[0].reset();
        $('.myImg').css("display","none");
        $('.myImg>img').attr('src','');
        $('#empImg').val("");

        loadDialog(null);
    });

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('empTable'),
            data = checkStatus.data,
            empIds = [];
        if(data.length > 0) {
            for (var i in data) {
                empIds.push(data[i].empId);
            }
            layer.confirm('确定删除选中的员工？', {icon: 3, title: '提示信息'}, function (index) {
                $.ajax({
                    url:'/employee/deleteAll',
                    type:'post',
                    data:{empIds:empIds},
                    traditional:true,
                    success:function (data) {
                        layer.close(index);
                        layer.msg(data.msg);
                        if(data.success){
                            empTable.reload();
                        }
                    }
                });
            })
        }else{
            layer.msg("请选择需要删除的员工");
        }
    });

    //查询按钮
    $('#searchBtn').click(function () {
        var value = $('#searchVal').val();
        if(value == '' || value == null){
            layer.msg("请输入搜索的内容");
            return;
        }
        //获取下拉框选中的值
        var poId = $('#selectPo').val();
        var depId = $('#selectDep').val();

        table.reload("empTable",{
            page:{
                curr:1
            },
            where:{
                poId:poId,
                depId:depId,
                key:value,
            },
        });

    });

    function getRootPath(){
        var currentPagepath=location.href;
        var pathName = window.document.location.pathname;
        var pos = currentPagepath.indexOf(pathName);
        var localhostPath = currentPagepath.substring(0,pos);
        var projectName = pathName.substring(0,pathName.substr(1).indexOf("/")+1);
        return localhostPath;
    }

    //列表操作
    table.on('tool(empTable)', function(obj){
        console.log(obj);
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit') {
            //编辑
            loadDialog(obj.data);
            //家庭信息回显
            $.get("/employee/getFamily?empId="+obj.data.empId,function (res) {
                if(res){
                    form.val('familyForm',res);
                }else{
                    $("#familyForm")[0].reset();
                }
                form.render();
            });

            //界面回显
            form.val('addForm',obj.data);
        }else if(layEvent === 'del'){
            //删除
            layer.confirm('确定删除？',{icon:3, title:'提示信息'},function(index){
                $.post("/employee/delete?empId="+data.empId,function (res) {
                    layer.msg(res.msg);
                    if(res.success){
                        empTable.reload();
                        layer.close(index);
                    }
                })
            });
        }
    });

});
