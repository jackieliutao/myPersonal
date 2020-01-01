package com.dongpo.controller;
import com.dongpo.domain.Department;
import com.dongpo.domain.Position;
import com.dongpo.service.DepartmentService;
import com.dongpo.vo.PageResultVo;
import com.dongpo.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/departmentMenu")
    public String departmentMenu(){
        return "department";
    }

    @RequestMapping("/listDepartment")
    @ResponseBody
    public PageResultVo pageList(Integer page,Integer limit,String key){
        PageResultVo pageResultVo = departmentService.pageList(page,limit,key);
        return pageResultVo;
    }

    @RequestMapping("/addDepartment")
    @ResponseBody
    public Result addDepartment(Department department){
        System.out.println("接受的参数------"+department);
        try {
            departmentService.add(department);
            return new Result(true,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"添加失败");
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Integer depId){
        try {
            departmentService.delete(depId);
            return new Result(true,"操作成功");
        }catch (Exception e){
            return new Result(false,"删除失败");
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(Department department){
        try {
            departmentService.update(department);
            return new Result(true,"更新成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"更新失败");
        }

    }

    @RequestMapping("/deleteAll")
    @ResponseBody
    public Result deleteAll(Integer[] depIds){

        try {
            departmentService.deleteAll(depIds);
            return new Result(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public List<Department> getAll(){
        return departmentService.getAll();
    }

}
