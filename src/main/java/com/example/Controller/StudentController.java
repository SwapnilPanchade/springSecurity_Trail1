package com.example.Controller;

import com.example.Entity.Student;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    List<Student> studentList = new ArrayList<>(List.of(
            new Student(1,"swapnil","male"),
            new Student(2,"vishal", "male")
    ));

    @GetMapping("/students")
    public List<Student> getStudents(){
        return studentList;
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){
        studentList.add(student);
        return student;
    }

}
