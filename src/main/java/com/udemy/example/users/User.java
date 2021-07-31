package com.udemy.example.users;

import java.time.LocalDate;

public class User {
    private Integer id;
    private String name;
    private LocalDate dob;

    protected User() {
    }

    public User(Integer id, String name, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.dob = dob;
    }

    public String getName() {
        return this.name;
    }

    public int getID() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDob() {
        return this.dob;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                '}';
    }
}
