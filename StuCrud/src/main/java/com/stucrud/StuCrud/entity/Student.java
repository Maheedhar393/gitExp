package com.stucrud.StuCrud.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "student_details")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Data
public class Student{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "stu_roll")
    private int rollNo;

    @Column(name = "stu_name")
    private String name;

    @Column(name = "stu_age")
    private int age;

    @Column(name = "stu_standard")
    private int standard;

    @Column(name = "stu_section")
    private String section;

    @Column(name = "password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shoe_id", referencedColumnName = "shoe_Id")
    private Shoe shoe;

}
