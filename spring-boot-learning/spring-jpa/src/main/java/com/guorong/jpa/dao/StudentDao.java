package com.guorong.jpa.dao;

import com.guorong.jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author guorong
 */
public interface StudentDao extends JpaRepository<Student, Long> {


}
