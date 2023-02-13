package logic.DAO;

import logic.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PeopleDAO {
    private List<Person> people;

    PeopleDAO(){
        people = new ArrayList<>();

        people.add(new Person(1,"Bob"));
        people.add(new Person(2,"Tom"));
        people.add(new Person(3,"Rob"));
    }

    public List<Person> getPeople(){
        return people;
    }
    public Person getPersonById(int id){
//стрим поиска человека по ID
        Person person = people.stream().filter(p -> p.getId() == id).findAny().orElse(null);
        return person;
    }

}
