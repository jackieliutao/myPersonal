layui.use(['layer','upload','element','form','laydate'],function () {
    var $ = layui.jquery,
        layer = layui.layer,
        form = layui.form,
        upload = layui.upload,
        laydate = layui.laydate;
    //填充日期
    laydate.render({
        elem: '#birthday', //指定元素
        trigger:'click'
    });



    function getRootPath(){
        var currentPagepath=location.href;
        var pathName = window.document.location.pathname;
        var pos = currentPagepath.indexOf(pathName);
        var localhostPath = currentPagepath.substring(0,pos);
        var projectName = pathName.substring(0,pathName.substr(1).indexOf("/")+1);
        return localhostPath;
    }
    //加载选在部门
    function loadDep() {
        $('#add-selDep').empty();
        $('#add-selDep').append("<option value=''>请选择部门</option>");
        $.get('/department/getAll',function (res) {
            $.each(res,function (index,value) {
                $('#add-selDep').append("<option value='"+value.depId+"'>"+value.depName+"</option>")
            });
            form.render();
        });
    }
    loadDep();
    function loadPosition(depId){
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
            form.render();
        })
    }

    function loadProfessional(depId){
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
            form.render();
        })
    }
    //上传图片操作
    //执行实例
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
            if(imgName != null && imgName !==''){
                $.post("/employee/deleteImg?path="+imgName,function (res) {
                    console.log(res.msg);
                });
            }
        }
        ,error: function(){
            //请求异常回调
        }
    });

    //保存操作-包括添加和修改
    $('#saveEmp').click(function () {
        //获取form表单的值
        var data1 = form.val("addForm");
        $.post('/employee/add',data1,function (res) {
            layer.msg(res.msg);
        })
    });


    //监听部门的变化
    form.on('select(department)', function(data){
        //加载职位
        loadPosition(data.value);
        //加载职称
        loadProfessional(data.value);
    })
});