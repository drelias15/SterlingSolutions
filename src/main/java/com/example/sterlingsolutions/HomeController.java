package com.example.sterlingsolutions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

//    @Autowired
//    CloudinaryConfig cloudc;

    @RequestMapping("/")
    public String homePage(){
        return "index";
    }

    @RequestMapping("/list")
    public String listCourse(Model model){
        model.addAttribute("employees", employeeRepository.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String courseForm(Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("employee", new Employee());
        return "employeeform";
    }
    @PostMapping("/process")
    public String processForm(@ModelAttribute @Valid Employee employee, @RequestParam long departmentId, BindingResult result)
    {
        if (result.hasErrors()){
            return "employeeform";
        }
        Department dep = departmentRepository.findById(departmentId);
        employee.setDepartment(dep);
        employeeRepository.save(employee);
        return "redirect:/list";
    }
    @RequestMapping("/detail/{id}")
    public String showCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("employee", employeeRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("employee", employeeRepository.findById(id).get());
        return "employeeform";
    }

    @RequestMapping("/delete/{id}")
    public String delCourse(@PathVariable("id") long id){
        employeeRepository.deleteById(id);
        return "redirect:/list";
    }
    @GetMapping("/adddep")
    public String instructorForm(Model model){
        model.addAttribute("department", new Department());
        return "departmentform";
    }
    @PostMapping("/process2")
    public String processForm(@Valid Department department, BindingResult result)
    {
        if (result.hasErrors()){
            return "departmentform";
        }
        departmentRepository.save(department);
        return "redirect:/add";
    }
    @PostMapping("/search")
    public String searchByName(@RequestParam("search") String search, @RequestParam("search2") String search2,@RequestParam long departmentId, Model model){
        model.addAttribute("employees", employeeRepository.findByFirstNameOrLastNameOrDepartment_id(search, search2, departmentId));

        return "list";
    }
//    @PostMapping("/search2")
//    public String searchByDepartment(@ModelAttribute Department department, @RequestParam long departmentId, Model model){
//        model.addAttribute("departments", departmentRepository.findAll());
//        model.addAttribute("departments", departmentRepository.findById(departmentId));
//        return "list";
//    }
}


