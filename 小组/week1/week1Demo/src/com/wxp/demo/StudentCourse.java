package com.wxp.demo;

public class StudentCourse {
    private int id;
    private String studentName;
    private String courseName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "StudentCourse{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
