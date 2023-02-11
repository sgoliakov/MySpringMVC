package logic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("/myFirstController") //поменяет url для запросов на контроллер
public class FirstController {
    @GetMapping("/hello")
    public String hello() {
        return "first/helloPage";
    }

    @GetMapping("goodbye")
    public String goodBay() {
        return "first/goodbyePage";
    }
}
