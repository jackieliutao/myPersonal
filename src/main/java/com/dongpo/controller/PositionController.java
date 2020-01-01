package com.dongpo.controller;
import com.dongpo.domain.Position;
import com.dongpo.service.PositionService;
import com.dongpo.vo.PageResultVo;
import com.dongpo.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/position")
public class PositionController {
    @Autowired
    private PositionService positionService;
    @RequestMapping("/positionMenu")
    public String positionMenu(){
        return "position";
    }

    @RequestMapping("/pageList")
    @ResponseBody
    public PageResultVo pageList(Integer page,Integer limit,String key){
        return positionService.pageList(page,limit,key);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Integer pid){
        try {
            positionService.delete(pid);
            return new Result(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }

    @RequestMapping("/deleteAll")
    @ResponseBody
    public Result deleteAll(Integer[] pids){
        try {
            positionService.deleteAll(pids);
            return new Result(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }

    //添加
    @RequestMapping("addPosition")
    @ResponseBody
    public Result addPosition(Position position){
        try {
            positionService.addPosition(position);
            return new Result(true,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"添加失败，检查编号，或姓名是否重复");
        }
    }

    //更新
    @RequestMapping("/update")
    @ResponseBody
    public Result update(Position position){
        try {
            positionService.update(position);
            return new Result(true,"更新成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"更新失败");
        }
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public List<Position> getAll(){
        return positionService.getAll();
    }

    @RequestMapping("/getPositionByDepId")
    @ResponseBody
    public List<Position> getPositionByDepId(Integer depId){
        if(depId == null){
            return null;
        }
        return positionService.getPositionByDepId(depId);
    }
}
