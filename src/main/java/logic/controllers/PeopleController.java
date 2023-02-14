package logic.controllers;

import jakarta.validation.Valid;
import logic.DAO.PeopleDAO;
import logic.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("people", peopleDAO.getPeople());
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

    //метод для возвращения хтмл формы для создания нового человека
    @GetMapping("/new")
    public String newPerson(Model model) {
//когда мы используем thymleaf мы должны передать тот объект для которого нужна форма
        model.addAttribute(new Person());
        System.out.println("tyt");
        return "/peopleControllerView/new";
    }

    //метод для добавления человека в БД
    @PostMapping()//ничего не указываем(и так по POST запросу "/people" мы попадем в этот метод)
//@ModelAttribute("person") Person person - само создаст newPerson,
// назначит сетеры и засунет в модель атрибут в конце
//Аннотация @Valid - будет проверять подходят ли значения
//если Valid не проходит, то появляется ошибка которая должна помещаться в BindingResult
    public String addNewPerson(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult) {
//можем проверить есть ли ошибки в BindingResult
        if (bindingResult.hasErrors()) {
            //то сразу возвращаем форму создания нового человека
            return "/peopleControllerView/new";
        }
//добавим персона в БД
        peopleDAO.save(person);
//как сделать редирект
        return "redirect:/people";//перейдет на страницу со всеми людьми showAllPeople
    }

    //метод для возвращения хтмл формы для update нового человека
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleDAO.getPersonById(id));
        return "/peopleControllerView/edit_person";
    }

    //метод для обновления данных человека
    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult,
                               @PathVariable("id") int id) {
        //можем проверить есть ли ошибки в BindingResult
        if (bindingResult.hasErrors()) {
            //то сразу возвращаем форму редактирования человека
            return "//peopleControllerView/edit_person";
        }
        peopleDAO.update(person, id);
        return "redirect:/people";
    }

    //метод для удаления человека
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleDAO.delete(id);

        return "redirect:/people";
    }
}
