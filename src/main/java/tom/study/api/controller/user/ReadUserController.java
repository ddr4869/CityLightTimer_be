package tom.study.api.controller.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadUserController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
