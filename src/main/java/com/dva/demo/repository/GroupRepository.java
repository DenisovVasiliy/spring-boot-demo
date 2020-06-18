package com.dva.demo.repository;

import com.dva.demo.model.Group;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupRepository extends ParentEntityRepository<Group> {

    @Query("select t from #{#entityName} t where t.deleted = ?1")
    List<Group> findMarked(boolean deleted);
}
