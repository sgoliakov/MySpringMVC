package logic.controllers;

import logic.DAO.PeopleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/people")//пусть все адреса в нашем контроллере начинаются с "/people"
public class PeopleController {
//даем DAO контроллеру
    private final PeopleDAO peopleDAO;

    @Autowired
    public PeopleController(PeopleDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }

    @GetMapping()//если оставить скобки пустыми, то будет переходить сразу по
    //  - то его запрос будет приходить в этот метод по юрл - "/people"
    public String showAllPeople(Model model) {
        //назначаем список в атрибут
        model.addAttribute("people",peopleDAO.getPeople());
        //вернем страничку, которая будет отображать список из людей
        return "/peopleControllerView/show_people";
    }

    //создадим метод чтоб возвращал человека по id
    @GetMapping("/{id}")//"/{id}" - это значит что можно к "/people" в uRL сразу подставить
    // число, которое будет id человека, с помощью аннотации (@PathVariable("id")
    public String showByID(@PathVariable("id") int id, Model model) {
        //достаем человека
        model.addAttribute("person", peopleDAO.getPersonById(id));
        //вернем страничку, которая будет отображать человека
        return "/peopleControllerView/show_person";
    }
}
