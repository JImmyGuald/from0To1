package com.java8.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;

/**
 * stream流操作
 */
public class Test3 {

    private static List<Person> phpProgrammers;
    private static List<Person> javaProgrammers;

    public static class Person {

        private String firstName, lastName, job, gender;
        private int salary, age;

        public Person(String firstName, String lastName, String job,
                      String gender, int age, int salary)       {
            this.firstName = firstName;
            this.lastName = lastName;
            this.gender = gender;
            this.age = age;
            this.job = job;
            this.salary = salary;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

     public static void init(){
         phpProgrammers = new ArrayList<Person>() {
             {
                 add(new Person("Jarrod", "Pace", "PHP programmer", "male", 34, 1550));
                 add(new Person("Clarette", "Cicely", "PHP programmer", "female", 23, 1200));
                 add(new Person("Victor", "Channing", "PHP programmer", "male", 32, 1600));
                 add(new Person("Tori", "Sheryl", "PHP programmer", "female", 21, 1000));
                 add(new Person("Osborne", "Shad", "PHP programmer", "male", 32, 1100));
                 add(new Person("Rosalind", "Layla", "PHP programmer", "female", 25, 1300));
                 add(new Person("Fraser", "Hewie", "PHP programmer", "male", 36, 1100));
                 add(new Person("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
                 add(new Person("Alvin", "Lance", "PHP programmer", "male", 38, 1600));
                 add(new Person("Evonne", "Shari", "PHP programmer", "female", 40, 1800));
             }
         };

         javaProgrammers = new ArrayList<Person>() {
             {
                 add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
                 add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
                 add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
                 add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
                 add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));
                 add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
                 add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));
                 add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
                 add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
                 add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));
             }
         };
     }

    /**
     * 显示月薪超过 $1,400 的PHP程序员
     */
    public static void demo1(){
        phpProgrammers.stream().filter(n->(n.getSalary()>1400)).forEach(n->{
            System.out.printf("%s %s; ", n.getFirstName(), n.getLastName());
        });
    }

    /**
     * 过滤器重用
     */
    public static void demo2(){
        // 定义 filters
        Predicate<Person> ageFilter = (p) -> (p.getAge() > 25);
        Predicate<Person> salaryFilter = (p) -> (p.getSalary() > 1400);
        Predicate<Person> genderFilter = (p) -> ("female".equals(p.getGender()));

        System.out.println("下面是年龄大于 24岁且月薪在$1,400以上的女PHP程序员:");
        phpProgrammers.stream()
                .filter(ageFilter)
                .filter(salaryFilter)
                .filter(genderFilter)
                .forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));

        // 重用filters
        System.out.println("年龄大于 24岁的女性 Java programmers:");
        javaProgrammers.stream()
                .filter(ageFilter)
                .filter(genderFilter)
                .forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));
    }

    public static void demo3(){
        //最前面的3个 Java programmers
        System.out.println("最前面的3个 Java programmers");
        javaProgrammers.stream().limit(3).forEach(p-> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));
        System.out.println("最前面的3个女性 Java programmers:");
        Predicate<Person> genderFilter = (p) -> ("female".equals(p.getGender()));
        javaProgrammers.stream()
                .filter(genderFilter)
                .limit(3)
                .forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));
    }

    public static void demo4(){
        System.out.println("根据 name 排序,并显示前5个 Java programmers:");
        javaProgrammers.stream().sorted((p1,p2)->(p1.getFirstName().compareTo(p2.getFirstName()))).limit(5).forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));
    }

    public static void demo5(){
        System.out.println("工资最低的 Java programmer:");
        Person p = javaProgrammers.stream().min((p1,p2)->(p1.getSalary()-p2.getSalary())).get();
        System.out.printf("%s %s; ", p.getFirstName(), p.getLastName());
    }

    /**
     * collect结合map方法的使用
     */
    public static void demo6(){
        System.out.println("将 PHP programmers 的 first name 拼接成字符串:");
        System.out.println(phpProgrammers.stream().map(Person::getFirstName).collect(joining(" ; ")));
        System.out.println("将 Java programmers 的 first name 存放到 Set:");
        Set<String> javaDevFirstName = javaProgrammers
                .stream()
                .map(Person::getFirstName)
                .collect(toSet());
        javaDevFirstName.forEach((p) -> System.out.println(p));
    }

    /**
     * Streams 并行计算
     */
    public static void demo7(){
        int totalSalary = javaProgrammers
                .parallelStream()
                .mapToInt(p -> p.getSalary())
                .sum();
        System.out.println(totalSalary);
    }

    public static void demo8() {
        System.out.println("haha");
        try{
            int i=10/0;
        }catch (Exception e) {
            throw e;
        }
        System.out.println("haha");
    }

    public static void main(String[] args) {
        /*init();
        demo1();
        demo3();
        demo4();
        demo5();
        demo6();
        demo7();*/

        demo8();
    }
}
