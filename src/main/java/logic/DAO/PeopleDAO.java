package logic.DAO;

import logic.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PeopleDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PeopleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getPeople() {//вместо new PersonMapper() можно использовать new BeanPropertyRowMapper<>(Person.class)
        List<Person> list = jdbcTemplate.query("select * from person", new PersonMapper());
        return list;
    }

    public Person getPersonById(int id) {
//стрим поиска человека по ID
//        Person person = people.stream().filter(p -> p.getId() == id).findAny().orElse(null);
        return jdbcTemplate.query("select * from person where id=?", new Object[]{id}, new PersonMapper())
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("insert into person values (5,?,?,?)",
                person.getName(), person.getAge(), person.getMail());
    }


    public void update(Person person, int id) {
        jdbcTemplate.update("update person set name=?,age=?,mail=? where id=?",
                person.getName(), person.getAge(), person.getMail(), id);

    }

    //метод для удаления с помощью лямбда
    public void delete(int id) {
//      //при true будет удален из списка(если id == p.getId())
//      people.removeIf(p -> id == p.getId());
        jdbcTemplate.update("delete from person where id=?", id);

    }
}
