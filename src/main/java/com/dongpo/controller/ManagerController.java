package com.dongpo.controller;

import com.dongpo.domain.Manager;
import com.dongpo.service.ManagerService;
import com.dongpo.vo.PageResultVo;
import com.dongpo.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller
@RequestMapping("/manage")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Result login(Manager manager, HttpSession session){
        try {
            //判断用户是否存在
            Manager manager1 = managerService.getManagerByName(manager.getUsername());
            if(manager1 == null){
                return new Result(false,"用户不存在");
            }else if(!manager.getPassword().equals(manager1.getPassword())){
                return new Result(false,"密码错误");
            }
            session.setAttribute("username",manager.getUsername());
            return new Result(true,"登录成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"登录出错");
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("username");
        return "redirect:/login.jsp";
    }

    @RequestMapping("/changePwdMenu")
    public String changePwdMenu(){
        return "changePwd";
    }

    @RequestMapping("/changePwd")
    @ResponseBody
    public Result changePwd(String oldPwd,String newPwd,HttpSession session){
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            if(username==null){
                return new Result(false,"暂未登录");
            }
            Manager managerByName = managerService.getManagerByName(username);
            if(! managerByName.getPassword().equals(oldPwd)){
                return new Result(false,"旧密码错误");
            }
            managerByName.setPassword(newPwd);
            managerService.update(managerByName);
            return new Result(true,"修改成功");
        }catch (Exception e){
            return new Result(false,"修改失败");
        }
    }

    @RequestMapping("/manageMenu")
    public String menageMenu(){
        return "manager";
    }

    @RequestMapping("/pageList")
    @ResponseBody
    public PageResultVo pageList(Integer page, Integer limit, String key){
        return managerService.pageList(page,limit,key);
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result add(Manager manager){
        try {
            managerService.add(manager);
            return new Result(true,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"添加失败");
        }
    }

    @RequestMapping("/changeStatus")
    @ResponseBody
    public Result changeStatus(Integer []ids){
        try {
            managerService.changeStatus(ids);
            return new Result(true,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"修改失败");
        }
    }

    @RequestMapping("/deleteAll")
    @ResponseBody
    public Result deleteAll(Integer []ids){
        try {
            managerService.deleteAll(ids);
            return new Result(true,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Integer id){
        try {
            managerService.delete(id);
            return new Result(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }

}
