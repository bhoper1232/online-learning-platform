package com.bhoper.client.course;


public class Course {

    String id;
    String name;
    String description;
    String instructor;

    public Course() {
    }

    public Course(String id, String name, String description, String instructor) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.instructor = instructor;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
