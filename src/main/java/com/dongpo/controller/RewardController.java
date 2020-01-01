package com.dongpo.controller;

import com.dongpo.domain.Employee;
import com.dongpo.domain.Reward;
import com.dongpo.domain.Study;
import com.dongpo.service.EmployeeService;
import com.dongpo.service.RewardService;
import com.dongpo.service.StudyService;
import com.dongpo.vo.PageResultVo;
import com.dongpo.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

@Controller
@RequestMapping("/reward")
public class RewardController {

    @Autowired
    private RewardService rewardService;
    @Autowired
    private EmployeeService employeeService;
    @RequestMapping("/rewardMenu")
    public String studyMenu(){
        return "reward";
    }


    @RequestMapping("/pageList")
    @ResponseBody
    public PageResultVo pageList(Integer page,Integer limit,String key){
        return rewardService.pageList(page,limit,key);
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result add(Reward reward){
        try {
            Employee one = employeeService.findOne(reward.getEmployee().getEmpId());
            if(one == null){
                return new Result(false,"员工编号输入错误");
            }
            rewardService.add(reward);
            return new Result(true,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"添加失败");
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(Reward reward){
        try {
            Employee one = employeeService.findOne(reward.getEmployee().getEmpId());
            if(one == null){
                return new Result(false,"员工编号输入错误");
            }
            rewardService.update(reward);
            return new Result(true,"更新成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"更新失败");
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Integer reId){
        try {
            rewardService.delete(reId);
            return new Result(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }

    @RequestMapping("/deleteAll")
    @ResponseBody
    public Result deleteAll(Integer[] reIds){
        try {
            rewardService.deleteAll(reIds);
            return new Result(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }
}
