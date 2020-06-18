package com.dva.demo.repository;

import com.dva.demo.model.Group;
import com.dva.demo.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends ParentEntityRepository<Student> {

    List<Student> findByGroup(Group group);

    @Query("select t from #{#entityName} t where t.deleted = ?1")
    List<Student> findMarked(boolean deleted);
}
