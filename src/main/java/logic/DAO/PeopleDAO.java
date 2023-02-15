package logic.DAO;

import logic.models.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PeopleDAO {
    private static int PEOPLE_COUNT;
    private static final String URL = "jdbc:mysql://localhost:3306/forspring";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "1992svyat";

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PeopleDAO() {

    }

    public List<Person> getPeople() {
        List<Person> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "select * from person";
            statement.executeQuery(sql);
            ResultSet res = statement.getResultSet();
            while (res.next()) {
                Person person = new Person();
                person.setId(res.getInt("id"));
                person.setName(res.getString("name"));
                person.setMail(res.getString("mail"));
                person.setAge(res.getInt("age"));
                people.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return people;
    }

    public Person getPersonById(int id) {
//стрим поиска человека по ID
//        Person person = people.stream().filter(p -> p.getId() == id).findAny().orElse(null);
        Person person = null;
        try {
            PreparedStatement statement =
                    connection.prepareStatement("select * from person where id=?");
            statement.setInt(1, id);
            statement.executeQuery();
            person = new Person();
            ResultSet res = statement.getResultSet();
            while (res.next()) {
                person.setId(res.getInt("id"));
                person.setName(res.getString("name"));
                person.setMail(res.getString("mail"));
                person.setAge(res.getInt("age"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return person;
    }

    public void save(Person person) {
        try {
            PreparedStatement statement =
                    connection.prepareStatement("insert into person values (5,?,?,?)");
            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            statement.setString(3, person.getMail());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void update(Person person, int id) {

        try {
            PreparedStatement statement =
                    connection.prepareStatement("update person set name=?,age=?,mail=? where id=?");
            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            statement.setString(3, person.getMail());
            statement.setInt(4, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //метод для удаления с помощью лямбда
    public void delete(int id) {
//      //при true будет удален из списка(если id == p.getId())
//      people.removeIf(p -> id == p.getId());
        PreparedStatement statement = null;
        try {
            statement =
                    connection.prepareStatement("delete from person where id=?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
