package com.wxp.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class menu {

    public static void mainMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("===========================");
        System.out.println("\uD83C\uDF93 学生选课管理系统");
        System.out.println("===========================");
        System.out.println("1. 登录");
        System.out.println("2. 注册");
        System.out.println("3. 退出");
        System.out.println("请选择操作（输入 1-3）：");
        int choice = sc.nextInt();
        switch (choice){
            case 1 :
                List<Object> list = new ArrayList<>();
                list = Function.login();
                if(list.get(0).equals(1)) adminMenu();
                else if(list.get(0).equals(2)) studentMenu((String) list.get(1));
                else System.out.println("登陆失败~");
                break;
            case 2:
                Function.createUser();
                return;
            case 3:
                System.out.println("退出成功~");
                return;
            default:
                System.out.println("输入无效~");
                break;
        }
    }

    public static void studentMenu(String studentName) {
        Scanner sc = new Scanner(System.in);
        System.out.println("===== 学生菜单 =====");
        System.out.println("1. 查看可选课程");
        System.out.println("2. 选择课程");
        System.out.println("3. 退选课程");
        System.out.println("4. 查看已选课程");
        System.out.println("5. 修改手机号");
        System.out.println("6. 退出");
        while (true) {
            System.out.println("请选择操作（输入 1-6）：");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    Function.queryCourses();
                    break;
                case 2:
                    Function.selectCourse(studentName);
                    break;
                case 3:
                    Function.deleteStudentCourse(studentName);
                    break;
                case 4:
                    Function.queryMyCourse(studentName);
                    break;
                case 5:
                    Function.updatePhoneNumber(studentName);
                    break;
                case 6:
                    System.out.println("退出成功~");
                    return;
                default:
                    System.out.println("输入无效~");
                    break;
            }
        }
    }
    public static void adminMenu(){
        Scanner sc= new Scanner(System.in);
        System.out.println("===== 管理员菜单 =====");
        System.out.println("1. 查询所有学生");
        System.out.println("2. 修改学生手机号");
        System.out.println("3. 查询所有课程");
        System.out.println("4. 修改课程学分");
        System.out.println("5. 查询某课程的学生名单");
        System.out.println("6. 查询某学生的选课情况");
        System.out.println("7. 退出");
        while(true){
            System.out.println("请选择操作（输入 1-7）：");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    AdminFunction.queryAllStudent();
                    break;
                case 2:
                    AdminFunction.updateStudentPhoneNumber();
                    break;
                case 3:
                    Function.queryCourses();
                    break;
                case 4:
                    AdminFunction.updateCourseCredit();
                    break;
                case 5:
                    AdminFunction.queryOneCourseAllStudent();
                    break;
                case 6:
                    AdminFunction.queryOneStudentAllCourse();
                    break;
                case 7:
                    System.out.println("退出成功~");
                    return;
                default:
                    System.out.println("无效选择~");
                    break;
            }
        }
    }



}
