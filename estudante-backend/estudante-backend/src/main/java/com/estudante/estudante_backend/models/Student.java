package com.estudante.estudante_backend.models;

public class Student {
    private int id;
    private String name;
    private String email;
    private String phone;
    private int idCourse;
    private String period;

    public Student() {
    }

    public Student(int id, String name, String email, String phone, int idCourse, String period) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.idCourse = idCourse;
        this.period = period;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getphone() {
        return phone;
    }

    public void setphone(String phone) {
        this.phone = phone;
    }

    public int getidCourse() {
        return idCourse;
    }

    public void setidCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public String getperiod() {
        return period;
    }

    public void setperiod(String period) {
        this.period = period;
    }
}
