package fa.training.controller;

import fa.training.dto.LoginForm;
import fa.training.entities.Account;
import fa.training.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
    @RequestMapping("/login")
public class LoginController {
    @Autowired
    AccountService accountService;
    @GetMapping("/showLoginPage")
    public String showMyLoginPage(Model model) {
        LoginForm loginForm = new LoginForm();
        model.addAttribute("loginForm", loginForm);
        return "login";
    }

    @PostMapping("/authenticateUser")
    public String authenticateUser(@ModelAttribute("loginForm") LoginForm loginForm, ModelMap model, HttpServletRequest request) {
        Account theAccount=accountService.getAccountByAccount(loginForm.getAccount());
        if(theAccount.getPassword()==null
                ||theAccount.getAccount()==null
                ||!theAccount.getPassword().equals(loginForm.getPassword())
                ||!theAccount.getAccount().equals(loginForm.getAccount())) {
                model.addAttribute("error", "account or password is not valid");
                return "login";

        }else {
//       using to call other controller resquest
            //create session to save account
            HttpSession session= request.getSession(true);
            session.setAttribute("employeeAccount", theAccount.getAccount());
            return "redirect:/employee/employees";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("employeeAccount");
        return "redirect:/login/showLoginPage";
    }
}
