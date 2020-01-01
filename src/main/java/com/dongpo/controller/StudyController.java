package com.dongpo.controller;

import com.dongpo.domain.Employee;
import com.dongpo.domain.Study;
import com.dongpo.service.EmployeeService;
import com.dongpo.service.StudyService;
import com.dongpo.vo.PageResultVo;
import com.dongpo.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/study")
public class StudyController {

    @Autowired
    private StudyService studyService;
    @Autowired
    private EmployeeService employeeService;
    @RequestMapping("/studyMenu")
    public String studyMenu(){
        return "study";
    }


    @RequestMapping("/pageList")
    @ResponseBody
    public PageResultVo pageList(Integer page,Integer limit,String key){
        return studyService.pageList(page,limit,key);
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result add(Study study){
        try {
            Integer empId = study.getEmployee().getEmpId();
            Employee one = employeeService.findOne(empId);
            if(one == null){
                return new Result(false,"用户编号错误");
            }
            studyService.add(study);
            return new Result(true,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"添加失败");
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(Study study){
        try {
            Integer empId = study.getEmployee().getEmpId();
            Employee one = employeeService.findOne(empId);
            if(one == null){
                return new Result(false,"用户编号错误");
            }
            studyService.update(study);
            return new Result(true,"更新成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"更新失败");
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Integer sId){
        try {
            studyService.delete(sId);
            return new Result(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }

    @RequestMapping("/deleteAll")
    @ResponseBody
    public Result deleteAll(Integer[] sIds){
        try {
            studyService.deleteAll(sIds);
            return new Result(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }
}
