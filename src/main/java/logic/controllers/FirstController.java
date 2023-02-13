package logic.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RequestMapping("/myFirstController") //поменяет url для запросов на контроллер
public class FirstController {
    @GetMapping("/hello")
    public String hello(HttpServletRequest request, Model model) {//передаем запрос 1 способ
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
//назначаем атрибут
        model.addAttribute("message", "name: "+name+", "+"age: "+age);
//не забываем обратиться к атрибуту в представлении
        return "first/helloPage";
    }

    @GetMapping("goodbye")//передаем запрос 2способ
    public String goodBay(@RequestParam(value = "name", required = false) String name,
                          @RequestParam(value = "age", required = false) Integer age) {
        System.out.println(name);
        System.out.println(age);



        return "first/goodbyePage";
    }
}
