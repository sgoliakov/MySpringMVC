package logic.DAO;

import logic.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PeopleDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

  public PeopleDAO(){
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT,"Bob",23,"@mailA"));
        people.add(new Person(++PEOPLE_COUNT,"Tom",33,"@mailB"));
        people.add(new Person(++PEOPLE_COUNT,"Rob",43,"@mailC"));
    }

    public List<Person> getPeople(){
        return people;
    }
    public Person getPersonById(int id){
//стрим поиска человека по ID
        Person person = people.stream().filter(p -> p.getId() == id).findAny().orElse(null);
        return person;
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(Person person, int id) {
        Person personById = getPersonById(id);
        personById.setName(person.getName());
        personById.setAge(person.getAge());
        personById.setName(person.getMail());
    }
//метод для удаления с помощью лямбда
    public void delete(int id) {
      //при true будет удален из списка(если id == p.getId())
      people.removeIf(p -> id == p.getId());
    }
}
