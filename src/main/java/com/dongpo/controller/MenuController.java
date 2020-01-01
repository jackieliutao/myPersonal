package com.dongpo.controller;

import com.dongpo.domain.Menu;
import com.dongpo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @RequestMapping("/main")
    public String main(){
        return "main";
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public List<Menu> getAll(){
        return menuService.findAll();
    }
}
