package com.wxp.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminFunction {
    public AdminFunction() {
    }

    public static void queryAllStudent(){
        List<Student> list = new ArrayList<>();
        System.out.println("所有学生信息如下：");
        String sql = "select * from students";
        try {
            Connection conn = JDBCUtil.getConnect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                int id = rs.getInt("student_id");
                String studentName = rs.getString("student_name");
                String phoneNumber = rs.getString("phone_number");
                Student student = new Student();
                student.setStudentId(id);
                student.setStudentName(studentName);
                student.setPhoneNumber(phoneNumber);
                list.add(student);
            }
            System.out.println(list);
            JDBCUtil.close(conn,pstmt,rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateStudentPhoneNumber(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您要修改手机号的学生姓名：");
        String studentName = sc.next();
        System.out.println("请输入您要修改的新的电话号码：");
        String newPhoneNumber = sc.next();
        String sql = "update students set phone_number = ? where student_name = ?";
        String sql2 = "update users set phone_number = ? where username = ?";
        try {
            Connection conn = JDBCUtil.getConnect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,newPhoneNumber);
            pstmt.setString(2,studentName);
            pstmt.executeUpdate();
            Connection conn2 = JDBCUtil.getConnect();
            PreparedStatement pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setString(1,newPhoneNumber);
            pstmt2.setString(2,studentName);
            pstmt2.executeUpdate();
            System.out.println("修改手机号成功~");
            JDBCUtil.close(conn,pstmt,null);
            JDBCUtil.close(conn2,pstmt2,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateCourseCredit(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您要修改学分的课程：");
        String courseName = sc.next();
        System.out.println("请输入您要更改的学分：");
        double courseCredit = sc.nextDouble();
        String sql = "select * from courses where course_name = ?";
        String sql2 = "update courses set course_credit = ? where course_name = ?";
        try {
            Connection conn = JDBCUtil.getConnect();
            PreparedStatement pstsm = conn.prepareStatement(sql);
            pstsm.setString(1,courseName);
            ResultSet rs = pstsm.executeQuery();
            if(rs.next()) {
                Connection conn2 = JDBCUtil.getConnect();
                PreparedStatement pstmt2 = conn2.prepareStatement(sql2);
                pstmt2.setDouble(1, courseCredit);
                pstmt2.setString(2, courseName);
                pstmt2.executeUpdate();
                System.out.println("修改成功~");
                JDBCUtil.close(conn2, pstmt2, null);
            }else {
                System.out.println("查无此课~");
            }
            JDBCUtil.close(conn,pstsm,rs);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void queryOneCourseAllStudent(){
        Scanner sc = new Scanner(System.in);
        List<StudentCourse> list = new ArrayList<>();
        System.out.println("请输入您要查找情况的课程名字：");
        String courseName = sc.next();
        String sql = "select * from student_courses where course_name = ?";
        try {
            Connection conn = JDBCUtil.getConnect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,courseName);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                String studentName = rs.getString("student_name");
                StudentCourse studentCourse = new StudentCourse();
                studentCourse.setId(id);
                studentCourse.setStudentName(studentName);
                studentCourse.setCourseName(courseName);
                list.add(studentCourse);
                while (rs.next()){
                    id = rs.getInt("id");
                    studentName = rs.getString("student_name");
                    StudentCourse studentCourse2 = new StudentCourse();
                    studentCourse2.setId(id);
                    studentCourse2.setStudentName(studentName);
                    studentCourse2.setCourseName(courseName);
                    list.add(studentCourse2);
                }
                System.out.println(list);
            }
            else{
                System.out.println("此课无人选择~");
            }
            JDBCUtil.close(conn,pstmt,rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void queryOneStudentAllCourse(){
        Scanner sc = new Scanner(System.in);
        List<StudentCourse> list = new ArrayList<>();
        System.out.println("请输入您要查找选择课程情况的学生名字：");
        String studentName = sc.next();
        String sql = "select * from student_courses where student_name = ?";
        try {
            Connection conn = JDBCUtil.getConnect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,studentName);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                String courseName = rs.getString("course_name");
                StudentCourse studentCourse = new StudentCourse();
                studentCourse.setId(id);
                studentCourse.setStudentName(studentName);
                studentCourse.setCourseName(courseName);
                list.add(studentCourse);
                while (rs.next()){
                    id = rs.getInt("id");
                    courseName = rs.getString("course_name");
                    StudentCourse studentCourse2 = new StudentCourse();
                    studentCourse2.setId(id);
                    studentCourse2.setStudentName(studentName);
                    studentCourse2.setCourseName(courseName);
                    list.add(studentCourse2);
                }
                System.out.println(list);
            }
            else{
                System.out.println("这个学生没有选择任何课程~");
            }
            JDBCUtil.close(conn,pstmt,rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
