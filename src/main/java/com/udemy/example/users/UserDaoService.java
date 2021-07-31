package com.udemy.example.users;

import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static List<Integer> ids = new ArrayList<>();

    static {
        users.add(new User(1, "Adam", LocalDate.of(2000, Month.MARCH, 1)));
        ids.add(1);
        users.add(new User(2, "Eve", LocalDate.of(2000, Month.FEBRUARY, 2)));
        ids.add(2);
        users.add(new User(3, "Jack", LocalDate.of(2000, Month.JANUARY, 3)));
        ids.add(3);
    }


    public List<User> getUsers() {
        return users;
    }

    public User getUser(int id) {
        for (User user : users) {
            if (user.getID() == id) {
                return user;
            }
        }
        return null;
    }

    public User createUser(String name, LocalDate dob) {
        Integer id = getNewID();
        users.add(new User(
                id,
                name,
                dob
        ));
        return users.get(id - 1);
    }

    public Integer getNewID() {
        Integer id = 1;
        while (ids.contains(id)) {
            id++;
        }
        ids.add(id);
        return id;
    }

    public void removeId(Integer id) {
        ids.remove(id);
    }

    public User createUser(User user) {
        user.setId(getNewID());
        users.add(user);
        return user;
    }

    public void deleteUser(Integer id) {
        users.removeIf(user -> user.getID() == id);
        removeId(id);
    }
}
