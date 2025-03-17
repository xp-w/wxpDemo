package com.wxp.demo;

public class Course {
    private int courseId;
    private String courseName;
    private int isOpened;
    private double courseCredit;

    public Course(int courseId, String courseName, int isOpened, double courseCredit) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.isOpened = isOpened;
        this.courseCredit = courseCredit;
    }

    public Course() {
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getIsOpened() {
        return isOpened;
    }

    public void setIsOpened(int isOpened) {
        this.isOpened = isOpened;
    }

    public double getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(double courseCredit) {
        this.courseCredit = courseCredit;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", isOpened=" + isOpened +
                ", courseCredit=" + courseCredit +
                '}';
    }
}
