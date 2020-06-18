package com.dva.demo.model.converters;

import com.dva.demo.model.Student;
import com.dva.demo.model.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentConverter {

    private final GroupConverter groupConverter;

    @Autowired
    public StudentConverter(GroupConverter groupConverter) {
        this.groupConverter = groupConverter;
    }


    public StudentDto toDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setDeleted(student.isDeleted());
        if (student.getGroup() != null) {
            studentDto.setGroup(groupConverter.toDto(student.getGroup()));
        }
        return studentDto;
    }

    public Student toEntity(StudentDto studentDto) {
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        if (studentDto.getGroup() != null) {
            student.setGroup(groupConverter.toEntity(studentDto.getGroup()));
        }
        student.setDeleted(studentDto.isDeleted());
        return student;
    }
}
