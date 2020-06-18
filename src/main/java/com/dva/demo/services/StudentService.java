package com.dva.demo.services;

import com.dva.demo.model.dto.GroupDto;
import com.dva.demo.model.dto.StudentDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StudentService extends GenericService<StudentDto> {
    List<StudentDto> findByGroup(GroupDto groupDto);
}
