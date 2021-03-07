package com.www.eight.collection.list;

public class Student implements Comparable<Student> {

    private Integer studentId ;
    private Integer age ;
    private String name ;

    public Student(){

    }



    public Student(Integer studentId, Integer age, String name) {
        this.studentId = studentId;
        this.age = age;
        this.name = name;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Student student) {
        int i = this.getAge() - student.getAge();
        if (i == 0) {
            return this.getStudentId() -student.getStudentId() ;
        }
        return i ;
    }

}
