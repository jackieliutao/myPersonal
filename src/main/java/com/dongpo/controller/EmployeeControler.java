package com.dongpo.controller;
import com.dongpo.domain.Employee;
import com.dongpo.domain.Family;
import com.dongpo.domain.Position;
import com.dongpo.service.EmployeeService;
import com.dongpo.vo.PageResultVo;
import com.dongpo.vo.Result;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequestMapping("/employee")
@Controller
public class EmployeeControler {
    @Autowired
    private EmployeeService employeeService;
    @RequestMapping("/getAll")
    @ResponseBody
    public List<Employee> getAll(){
        return employeeService.getAll();
    }

    @RequestMapping("/employeeMenu")
    public String employeeMenu(){
        return "employee";
    }

    @RequestMapping("/employeeAddMenu")
    public String employeeAddMenu(){
        return "employeeAdd";
    }

    @RequestMapping("/pageList")
    @ResponseBody
    public PageResultVo pageList(Integer page,Integer limit,Integer depId,Integer poId,String key){
        return employeeService.pageList(page,limit,depId,poId,key);
    }

    @RequestMapping("/addEmployeeMenu")
    public String addEmployeeMenu(){
        return "addEmployee";
    }

    @RequestMapping("/uploadImg")
    @ResponseBody
    public Result uploadImg(MultipartFile file, HttpSession session){
       //文件名处理
        try {
            //判断文件类型
            String uuid = UUID.randomUUID().toString();
            String replace = uuid.replace("-", "");
            long time = new Date().getTime();
            int i = file.getOriginalFilename().lastIndexOf(".");
            String substring = file.getOriginalFilename().substring(i);
            String fileName = replace+time+substring;
            String realPath = session.getServletContext().getRealPath(File.separator+"upload");
            File file1 = new File(realPath);
            if(!file1.exists()){
                file1.mkdirs();
            }
            File file2 = new File(realPath + File.separator + fileName);
            if(file2.exists()){
                return new Result(false,"出现错误");
            }
            file.transferTo(file2);
            return new Result(true,fileName);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"上传失败,检查文件大小");
        }

    }

    @RequestMapping("/deleteImg")
    @ResponseBody
    public Result deleteImg(String path,HttpSession session){
        try {
            String realPath = session.getServletContext().getRealPath(File.separator + "upload");
            File file = new File(realPath + File.separator + path);
            if(file.exists()){
                file.delete();
            }
            return new Result(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result add(Employee employee, Family family){
        try {
            employeeService.add(employee,family);
            return new Result(true,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"添加失败");
        }
    }

    @RequestMapping("/deleteAll")
    @ResponseBody
    public Result deleteAll(Integer[] empIds,HttpSession session){
        try {
            employeeService.deleteAll(empIds,session.getServletContext().getRealPath(File.separator+"upload"));
            return new Result(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Integer empId,HttpSession session){
        try {
            employeeService.delete(empId,session.getServletContext().getRealPath(File.separator+"upload"));
            return new Result(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(Employee employee,Family family){
        try {
            //判断是否为空
            employeeService.update(employee,family);
            return new Result(true,"更新成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"更新失败");
        }
    }

    @RequestMapping("/getFamily")
    @ResponseBody
    public Family getFamily(Integer empId){
        return employeeService.getFamily(empId);
    }

}
