package com.wxp.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Function {
    public Function() {
    }

    public static void createUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("===== 用户注册 =====");
        System.out.println("请输入用户名：");
        String username = sc.next();
        String passWord = null;
        while (true) {
            System.out.println("请您输入您的账号密码：");
             passWord = sc.next();
            System.out.println("请您再次输入您的密码：");
            String okWord = sc.next();
            if (passWord.equals(okWord)) {
                break;
            } else {
                System.out.println("两次输入的密码不一致！请重新输入~");
            }
        }

        System.out.println("请输入手机号：");
        String phoneNumber = sc.next();

        while (true) {
            System.out.println("请选择角色（输入 1 代表管理员，2 代表学生）：");
            int userType = sc.nextInt();
            if (userType == 1 || userType == 2) {
                String sql1 = "insert into users(username,password,user_type,phone_number) value(?,?,?,?)";
                try{
                    Connection conn = JDBCUtil.getConnect();
                    PreparedStatement pstsm = conn.prepareStatement(sql1);
                    pstsm.setString(1,username);
                    pstsm.setString(2,passWord);
                    pstsm.setInt(3,userType);
                    pstsm.setString(4,phoneNumber);
                    pstsm.executeUpdate();
                    if(userType == 2){
                        String sql2 = "insert into students(student_name,phone_number) value(?,?)";
                        Connection conn2 = JDBCUtil.getConnect();
                        PreparedStatement pstsm2 = conn2.prepareStatement(sql2);
                        pstsm2 = conn.prepareStatement(sql2);
                        pstsm2.setString(1,username);
                        pstsm2.setString(2,phoneNumber);
                        pstsm2.executeUpdate();
                        JDBCUtil.close(conn2,pstsm2,null);
                    }
                    JDBCUtil.close(conn,pstsm,null);
                    System.out.println("注册成功！请返回主界面登录。");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                return;
            }
            else {
                System.out.println("选择无效，请重新选择！");
            }
        }
    }

    public static List<Object> login(){
        Scanner sc = new Scanner(System.in);
        List<Object> list = new ArrayList<>();
        System.out.println("===== 用户登录 =====");
            System.out.println("请输入用户名：");
            String username = sc.next();
            System.out.println("请输入密码：");
            String password = sc.next();
            String sql = "select * from users where username = ?";
            int flag = 0;
            try {
                Connection conn = JDBCUtil.getConnect();
                PreparedStatement pstsm = conn.prepareStatement(sql);
                pstsm.setString(1,username);
                ResultSet rs = pstsm.executeQuery();
                if(rs.next()){
                    String okWord = rs.getString("password");
                    int userType = rs.getInt("user_type");
                    while(true){
                        if(password.equals(okWord)){
                            break;
                        }else{
                            System.out.println("密码不正确~请重新输入~");
                            System.out.println("请输入密码：");
                            password = sc.next();
                        }
                    }
                    if(userType == 1) {
                        System.out.println("登录成功！你的角色是：管理员");
                        flag = 1;
                    }
                    else{
                        System.out.println("登录成功！你的角色是：学生");
                        flag = 2;
                    }
                }else{
                    System.out.println("无此用户~");
                }
            JDBCUtil.close(conn,pstsm,rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
            list.add(flag);
            list.add(username);
            return list;
    }

    public static void queryCourses(){
        List<Course> courses = new ArrayList<>();
        String sql = "select * from courses";
        try{
            Connection conn = JDBCUtil.getConnect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("course_id");
                String courseName = rs.getString("course_name");
                int isOpened = rs.getInt("is_opened");
                double courseCredit = rs.getDouble("course_credit");
                Course course = new Course();
                course.setCourseId(id);
                course.setCourseName(courseName);
                course.setIsOpened(isOpened);
                course.setCourseCredit(courseCredit);
                courses.add(course);
            }
            System.out.println(courses);
            JDBCUtil.close(conn,pstmt,rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void selectCourse(String studentName){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您要选择的课程的名字：");
        String courseName = sc.next();
        String sql = "select * from courses where course_name = ?";
        try{
            Connection conn = JDBCUtil.getConnect();
            PreparedStatement pstsm = conn.prepareStatement(sql);
            pstsm.setString(1,courseName);
            ResultSet rs = pstsm.executeQuery();
            if(rs.next()){
                String sql2 = "insert into student_courses(student_name,course_name) value(?,?)";
                Connection conn2 = JDBCUtil.getConnect();
                PreparedStatement pstsm2 = conn.prepareStatement(sql2);
                pstsm2.setString(1,studentName);
                pstsm2.setString(2,courseName);
                pstsm2.executeUpdate();
                JDBCUtil.close(conn2,pstsm2,null);
                System.out.println("选课成功~");
            }else{
                System.out.println("查无此课~");
            }
            JDBCUtil.close(conn,pstsm,rs);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void deleteStudentCourse(String studentName){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您要退选的课程名称：");
        String courseName = sc.next();
        String sql = "delete from student_courses where student_name = ? and course_name = ?";
        try{
            Connection conn = JDBCUtil.getConnect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,studentName);
            pstmt.setString(2,courseName);
            pstmt.executeUpdate();
            System.out.println("退选成功~");
            JDBCUtil.close(conn,pstmt,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void queryMyCourse(String studentName){
        Scanner sc = new Scanner(System.in);
        List<StudentCourse> list = new ArrayList<>();
        System.out.println("您选择的课程有：");
        String sql = "select * from student_courses where student_name = ?";
        try {
            Connection conn = JDBCUtil.getConnect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,studentName);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String courseName = rs.getString("course_name");
                StudentCourse studentCourse = new StudentCourse();
                studentCourse.setId(id);
                studentCourse.setStudentName(studentName);
                studentCourse.setCourseName(courseName);
                list.add(studentCourse);
            }
            System.out.println(list);
            JDBCUtil.close(conn,pstmt,rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updatePhoneNumber(String studentName){
        Scanner sc = new Scanner(System.in);
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

}
