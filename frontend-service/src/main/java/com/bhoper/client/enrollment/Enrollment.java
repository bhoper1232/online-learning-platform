package com.bhoper.client.enrollment;


public class Enrollment {

    private Long id;
    private String username;
    private String courseId;
    private String status;

    public Enrollment() {
    }

    public Enrollment(Long id, String username, String courseId, String status) {
        this.id = id;
        this.username = username;
        this.courseId = courseId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
