package fa.training.controller;

import fa.training.dto.Pagination;
import fa.training.entities.Account;
import fa.training.entities.Employee;
import fa.training.service.AccountService;
import fa.training.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    AccountService accountService;


    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        // create model attribute to bind form data
        Employee theEmployee = new Employee();

        theModel.addAttribute("employee", theEmployee);

        return "employee-form";
    }
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee, ModelMap model) {
        //check account and email
        List<Boolean> validates= new ArrayList<>();
        Account accountByEmail=accountService.getAccountByEmail(employee.getAccount().getEmail());
        Account accountByAccount=accountService.getAccountByAccount(employee.getAccount().getAccount());
        if(accountByEmail.getEmail()!=null&&accountByEmail.getEmail().equals(employee.getAccount().getEmail())){
            model.addAttribute("isExistEmail", "Email is exist");
            validates.add(false);
        }
        if(accountByAccount.getAccount()!=null&&accountByAccount.getAccount().equals(employee.getAccount().getAccount())){
            model.addAttribute("isExistAccount", "Account is exist");
            validates.add(false);
        }
        if(validates.contains(false)){
            return "employee-form";
        }else{
            employeeService.saveEmployee(employee);
            return "redirect:/employee/employees";
        }
    }

    @PostMapping("/updateEmployee")
    public  String updateEmployee(@ModelAttribute("employee") Employee employee, HttpServletRequest request, ModelMap model) {
        HttpSession session=request.getSession();
        Employee oldEmployee= (Employee)session.getAttribute("oldEmployee");
        //check if old account changed or not and email change or not;
        List<Boolean> validates= new ArrayList<>();
        if(!employee.getAccount().getEmail().equals(oldEmployee.getAccount().getEmail())){
            Account accountByEmail=accountService.getAccountByEmail(employee.getAccount().getEmail());
            if(accountByEmail.getEmail()!=null&&accountByEmail.getEmail().equals(employee.getAccount().getEmail())){
                model.addAttribute("isExistEmail", "Email is exist");
                validates.add(false);
            }
        }
        if(!employee.getAccount().getAccount().equals(oldEmployee.getAccount().getAccount())){
            Account accountByAccount=accountService.getAccountByAccount(employee.getAccount().getAccount());
            if(accountByAccount.getAccount()!=null&&accountByAccount.getAccount().equals(employee.getAccount().getAccount())){
                model.addAttribute("isExistAccount", "Account is exist");
                validates.add(false);
            }
        }
        if(validates.contains(false)){
            return "employee-view";
        }else{
            employee.setEmployeeId(employee.getEmployeeId());
            employeeService.saveEmployee(employee);
            return "redirect:/employee/employees";
        }
    }



    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId ) {
        System.out.println(theId);
        employeeService.deleteEmployeeById(theId);
        return "redirect:/employee/employees";
    }


    @GetMapping("/viewEmployee")
    public String viewEmployee(@RequestParam("employeeId") int theId, Model theModel, HttpServletRequest request) {
        Employee employee = employeeService.getEmployeeById(theId);
        HttpSession session=request.getSession();
        session.setAttribute("oldEmployee", employee);
        theModel.addAttribute("employee", employee);

        return "employee-view";
    }

    @GetMapping("/employees")
    public  String getEmployees(@RequestParam(value = "page", defaultValue = "2") int page,
                                @RequestParam(value = "size", defaultValue = "1") int size,
                                @RequestParam(value = "name", defaultValue = "") String name,
                                @RequestParam(value = "selectOption" ,defaultValue="") String selectOption,
                                Model theModel) {

        Pagination pagination = employeeService.getAllEmployees(page, size,name,selectOption);
        theModel.addAttribute("currentPage",page);
        theModel.addAttribute("totalPage",pagination.getTotalPage());
        theModel.addAttribute("totalRecords",pagination.getTotalRecords());
        theModel.addAttribute("theEmployees", pagination.getData());
       return "employee-list";
    }


}
