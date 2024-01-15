package tom.study.api.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller // @RestController는 thymeleaf 적용이 안됨
public class ReadUserController {
//    @RequestMapping("/login")
//    public String login() {
//        return "login";
//    }

    @RequestMapping("/basic")
    public String basic(Model model) {
        model.addAttribute("data", "test");
        return "basic";
    }
}
