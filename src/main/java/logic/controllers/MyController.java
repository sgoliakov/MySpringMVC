package logic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @GetMapping("/hello-mvc")//когда пользователь будет вводить
        // "/hello-world" - то его запрос будет приходить в этот метод
   public String sayHello(){
        //обработка запроса
        //вернем страничку
        return "myHtml";
    }
}
