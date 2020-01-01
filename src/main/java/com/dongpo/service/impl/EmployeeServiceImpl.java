package com.dongpo.service.impl;
import com.dongpo.domain.Employee;
import com.dongpo.domain.Family;
import com.dongpo.mapper.EmployeeMapper;
import com.dongpo.mapper.FamilyMapper;
import com.dongpo.service.EmployeeService;
import com.dongpo.vo.PageResultVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private FamilyMapper familyMapper;
    @Override
    public List<Employee> getAll() {
        return employeeMapper.getAll();
    }

    @Override
    public PageResultVo pageList(Integer page, Integer pageSize,Integer depId,Integer poId,String key) {
        PageResultVo pageResultVo = new PageResultVo();
        Page<Object> objects = PageHelper.startPage(page, pageSize);
        List<Employee> employees = employeeMapper.selectAll(depId,poId,key);
        pageResultVo.setCount(objects.getTotal());
        pageResultVo.setData(employees);
        return pageResultVo;
    }

    @Override
    public void add(Employee employee, Family family) throws ParseException {
        employee.setEmpIsblack(false);
        Integer age = this.calculateAge(employee.getEmpBirthday());
        employee.setEmpAge(age);

        employeeMapper.insert(employee);
        family.setEmpId(employee.getEmpId());
        familyMapper.insert(family);
    }

    //删除所有
    @Override
    public void deleteAll(Integer[] empIds,String path) {
        for(int i = 0;i<empIds.length;i++){
            Employee employee = employeeMapper.selectByPrimaryKey(empIds[i]);
            if(employee.getEmpImg() != null){
                deleteImg(path+File.separator+employee.getEmpImg());
            }
            employeeMapper.deleteByPrimaryKey(empIds[i]);
        }
    }

    @Override
    public void delete(Integer empId, String realPath) {
        familyMapper.deleteByEmpId(empId);
        Employee employee = employeeMapper.selectByPrimaryKey(empId);
        deleteImg(realPath+File.separator+employee.getEmpImg());
        employeeMapper.deleteByPrimaryKey(empId);
    }

    @Override
    public void update(Employee employee,Family family) throws ParseException {
        if(employee.getEmpBirthday()!= null){
            Integer age = calculateAge(employee.getEmpBirthday());
            employee.setEmpAge(age);
            familyMapper.deleteByEmpId(employee.getEmpId());
            family.setEmpId(employee.getEmpId());
            familyMapper.insert(family);
        }
        employee.setEmpIsblack(false);
        employeeMapper.updateByPrimaryKey(employee);
    }

    @Override
    public Family getFamily(Integer empId) {

        return familyMapper.getFamilyByEmpId(empId);
    }

    @Override
    public Employee findOne(Integer empId) {
        return employeeMapper.selectByPrimaryKey(empId);
    }

    public Integer calculateAge(String date) throws ParseException {
        if(date == null)
            return 0;

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String newDate1 = simpleDateFormat.format(date1);
        //截取获取年份
        int i = date.indexOf("-");
        String substring = date.substring(0, i);

        int i1 = newDate1.indexOf("-");
        String substring1 = newDate1.substring(0,i1);
        Integer d1 = Integer.parseInt(substring);
        Integer d2 = Integer.parseInt(substring1);
        return d2-d1;
    }

    public void deleteImg(String path){
        File file = new File(path);
        if(file.exists()){
            file.delete();
        }
    }


}
