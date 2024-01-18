package tom.study.api.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller // @RestController는 thymeleaf 적용이 안됨
public class ReadUserController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/basic")
    public String basic(Model model) {
        model.addAttribute("data", "test");
        return "basic";
    }
}
