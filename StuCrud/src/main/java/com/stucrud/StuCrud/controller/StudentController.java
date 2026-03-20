package com.stucrud.StuCrud.controller;


import com.stucrud.StuCrud.entity.Student;
import com.stucrud.StuCrud.entity.Users;
import com.stucrud.StuCrud.repo.StudentRepository;
import com.stucrud.StuCrud.repo.UserDetailsRepository;
import com.stucrud.StuCrud.service.CustomUserDetailsService;
import com.stucrud.StuCrud.service.StudentService;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository sr;

    @Autowired
    UserDetailsRepository udr;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    StudentService studentService;

    List<Student> list = studentService.list;

    @PostMapping("/insert")
    @PreAuthorize("hasAuthority('STUDENT_WRITE')")
    public ResponseEntity<String> insertStudent(@Valid @RequestBody Student st) {
        sr.save(st);
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/getStudents")
    @PreAuthorize("hasAuthority('STUDENT_READ')")
    public List<Student> getStudents() {
        return sr.findAll();
    }

    @DeleteMapping("/deleteStudent/{id}")
    @PreAuthorize("hasAuthority('STUDENT_DELETE')")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        sr.deleteById(id);
        return ResponseEntity.ok("Student with roll number " + id + "is deleted");
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable int id, @RequestBody Student st) {
        Optional<Student> op = sr.findById(id);
        Student st1 = Student.builder().age(st.getAge()).name(st.getName()).standard(st.getStandard()).section(st.getSection()).rollNo(id).build();
        sr.save(st1);
        return ResponseEntity.ok("The student with id " + id + " is updated.");
    }

    @GetMapping("/getByName/{name}")
    @PreAuthorize("hasAuthority('STUDENT_READ')")
    @PostAuthorize("returnObject.name == authentication.name")
    public  Student getStudent(@PathVariable String name){
        Optional<Student> op = sr.findByName(name);
        return op.get();
    }

    @GetMapping("/getByNameObject/{name}")
    @PreAuthorize("hasAuthority('STUDENT_READ')")
    //@PostAuthorize("returnObject.name == authentication.username')")
    public Student getStudentObject(@PathVariable String name){
        Student st = new Student();
        Map<String, Object> map = sr.getStudent(name);
        st.setRollNo((int)map.get("stu_roll"));
        st.setName((String)map.get("stu_name"));
        st.setAge((int)map.get("stu_age"));
        st.setStandard((int)map.get("stu_standard"));
        st.setSection((String)map.get("stu_section"));
        //st.setShoe((Shoe)map.get("shoe_id"));
        return st;
    }


    @PutMapping(value = "/updateStudent/{name}/{roll}")
    public  int updateStudentName(@PathVariable String name, @PathVariable int roll){
        return sr.updateStudent(name, roll);
    }


    @PostMapping("/addUsers")
    @PreAuthorize("hasAuthority('STUDENT_WRITE')")
    public ResponseEntity<String> addUsers(@RequestBody Users us){
        us.setPassword(passwordEncoder.encode(us.getPassword()));
        System.out.println(us.getPassword());
        udr.save(us);
        return ResponseEntity.ok("User added");
    }

    @GetMapping("/getStudentById/{id}")
    @PreAuthorize("hasAuthority('STUDENT_READ')")
    public ResponseEntity<Student> getStudentById(@PathVariable int id){
        Optional<Student> op = sr.findById(id);
        if(op.isPresent()){
            return ResponseEntity.ok(op.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
