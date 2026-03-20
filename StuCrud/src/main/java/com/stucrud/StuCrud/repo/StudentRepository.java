package com.stucrud.StuCrud.repo;


import com.stucrud.StuCrud.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    public Optional<Student> findByName(String name);

    @Query(value = "select * from student_details where stu_name = :nam", nativeQuery = true)
    public Map<String, Object> getStudent(String name);

    @Modifying
    @Transactional
    @Query(value =  "update student_details set stu_name = :name where stu_roll = :roll", nativeQuery = true)
    public int updateStudent(String name, int roll);
}
