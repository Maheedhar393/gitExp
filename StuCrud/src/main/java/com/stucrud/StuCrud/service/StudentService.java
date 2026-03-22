package com.stucrud.StuCrud.service;


import com.stucrud.StuCrud.entity.Student;
import com.stucrud.StuCrud.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService{

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> list = new ArrayList<>();

    public void saveStudent(Student student) {
        int con = 300;
        System.out.println("Inside saveStudent method, con value: " + con);
        studentRepository.save(student);
    }

    public Student getStudentByRollNo(int rollNo) {
        return studentRepository.findById(rollNo).orElse(null);
    }

}
