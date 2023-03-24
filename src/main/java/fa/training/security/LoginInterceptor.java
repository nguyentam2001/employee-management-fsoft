package fa.training.security;

import java.util.logging.Logger;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    private static Logger log = Logger.getLogger(LoginInterceptor.class.getName());
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Handling preHandle");
        if(request.getSession().getAttribute("employeeAccount") == null){
                response.sendRedirect(request.getContextPath() + "/login/showLoginPage");
                return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("Handling postHandle");
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("Handling afterCompletion");
        super.afterCompletion(request, response, handler, ex);
    }
}
