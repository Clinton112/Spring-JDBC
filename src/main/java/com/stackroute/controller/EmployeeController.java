package com.stackroute.controller;

import com.stackroute.beans.Employee;
import com.stackroute.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao dao;

    @RequestMapping(value = "/empform")
    public String showform(Model m){
        m.addAttribute("command", new Employee());
        return "empform";
    }

    @PostMapping(value="/save")
    public String save(@ModelAttribute("emp") Employee employee){
        dao.save(employee);
        return "redirect:/viewemp";//will redirect to viewemp request mapping
    }

    @RequestMapping(value = "/viewemp")
    public String viewemp(Model m){
        List<Employee> list=dao.getEmployees();
        m.addAttribute("list",list);
        return "viewemp";
    }

    @RequestMapping(value="/editemp/{id}")
    public String edit(@PathVariable int id, Model m){
        Employee employee=dao.getEmpById(id);
        m.addAttribute("command",employee);
        return "empeditform";
    }

    @PostMapping(value="/editsave")
    public String editsave(@ModelAttribute("emp") Employee employee){
        dao.update(employee);
        return "redirect:/viewemp";
    }

    @GetMapping(value="/deleteemp/{id}")
    public String delete(@PathVariable int id){
        dao.delete(id);
        return "redirect:/viewemp";
    }
}
