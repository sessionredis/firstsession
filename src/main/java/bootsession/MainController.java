package bootsession;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 * 控制器 博客出处：http://www.cnblogs.com/GoodHelper/
 *
 */
@Controller
public class MainController {

    @GetMapping("/index")
    public String index() {
    	return "redirect:/index.html";
    }
    @GetMapping("/second")
    public String second() {
    	return "redirect:/second.html";
    }
    @GetMapping("/login")
    public String login() {
        return "redirect:/login.html";
    }

    @PostMapping("/loginPost")
    public  String loginPost(@Valid User user,HttpServletRequest request, HttpServletResponse response) {
    	HttpSession session = request.getSession();
        if("admin".equals(user.getUserName())&&"123456".equals(user.getUserPwd())){
        	// 设置session
            session.setAttribute(WebSecurityConfig.SESSION_KEY, user.getUserName());
            return "redirect:/index";
        }else{
        	return "redirect:/login";
        }
        
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
    	HttpSession session = request.getSession(false);
        // 移除session
        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
        return "redirect:/login";
    }

}