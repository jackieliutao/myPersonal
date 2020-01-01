package com.dongpo.controller;

import com.dongpo.domain.Department;
import com.dongpo.domain.Professional;
import com.dongpo.service.ProfessionalService;
import com.dongpo.vo.PageResultVo;
import com.dongpo.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/professional")
public class ProfessionalController {
    @Autowired
    private ProfessionalService professionalService;
    @RequestMapping("/professionalMenu")
    public String professionalMenu(){
        return "professional";
    }
    @RequestMapping("/listProfessional")
    @ResponseBody
    public PageResultVo listProfessional(Integer page,Integer limit,String key){
        return professionalService.list(page,limit,key);
    }

    @RequestMapping("/addProfessional")
    @ResponseBody
    public Result addProfessional(Professional professional){

        try {
            professionalService.add(professional);
            return new Result(true,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"添加失败");
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Integer proId){
        try {
            professionalService.delete(proId);
            return new Result(true,"操作成功");
        }catch (Exception e){
            return new Result(false,"删除失败");
        }
    }

    @RequestMapping("/deleteAll")
    @ResponseBody
    public Result deleteAll(Integer[] proIds){
        try {
            professionalService.deleteAll(proIds);
            return new Result(true,"操作成功");
        }catch (Exception e){
            return new Result(false,"删除失败");
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(Professional professional){
        try {
            professionalService.update(professional);
            return new Result(true,"更新成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"更新失败");
        }

    }

    @RequestMapping("/getProfessionalByDepId")
    @ResponseBody
    public List<Professional> getProfessionalByDepId(Integer depId){
        return professionalService.getProfessionalByDepId(depId);
    }

}
