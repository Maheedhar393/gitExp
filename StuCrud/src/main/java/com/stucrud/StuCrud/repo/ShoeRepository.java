package com.stucrud.StuCrud.repo;

import com.stucrud.StuCrud.entity.Shoe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoeRepository extends JpaRepository<Shoe, Integer> {

}
