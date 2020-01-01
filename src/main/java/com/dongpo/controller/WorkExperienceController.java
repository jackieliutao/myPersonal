package com.dongpo.controller;
import com.dongpo.domain.Employee;
import com.dongpo.domain.Position;
import com.dongpo.domain.Workexperience;
import com.dongpo.service.EmployeeService;
import com.dongpo.service.PositionService;
import com.dongpo.service.WorkExperienceService;
import com.dongpo.vo.PageResultVo;
import com.dongpo.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/workExperience")
public class WorkExperienceController {
    @Autowired
    private WorkExperienceService workExperienceService;
    @Autowired
    private EmployeeService employeeService;
    @RequestMapping("/workExperienceMenu")
    public String workExperienceMenu(){
        return "workExperience";
    }

    @RequestMapping("/pageList")
    @ResponseBody
    public PageResultVo pageList(Integer page,Integer limit,String key){
        return workExperienceService.pageList(page,limit,key);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Integer wbId){
        try {
            workExperienceService.delete(wbId);
            return new Result(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }

    @RequestMapping("/deleteAll")
    @ResponseBody
    public Result deleteAll(Integer[] wbIds){
        try {
            workExperienceService.deleteAll(wbIds);
            return new Result(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }

    //添加
    @RequestMapping("add")
    @ResponseBody
    public Result add(Workexperience workexperience){
        try {
            Employee one = employeeService.findOne(workexperience.getEmployee().getEmpId());
            if(one == null){
                return new Result(false,"用户编号错误");
            }
            workExperienceService.add(workexperience);
            return new Result(true,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"添加失败，检查编号，或姓名是否重复");
        }
    }

    //更新
    @RequestMapping("/update")
    @ResponseBody
    public Result update(Workexperience workexperience){
        try {
            Employee one = employeeService.findOne(workexperience.getEmployee().getEmpId());
            if(one == null){
                return new Result(false,"用户编号错误");
            }
            workExperienceService.update(workexperience);
            return new Result(true,"更新成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"更新失败");
        }
    }
}
