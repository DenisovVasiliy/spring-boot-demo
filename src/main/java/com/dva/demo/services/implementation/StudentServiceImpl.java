package com.dva.demo.services.implementation;

import com.dva.demo.model.Student;
import com.dva.demo.model.converters.GroupConverter;
import com.dva.demo.model.converters.StudentConverter;
import com.dva.demo.model.dto.GroupDto;
import com.dva.demo.model.dto.StudentDto;
import com.dva.demo.repository.StudentRepository;
import com.dva.demo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentConverter studentConverter;
    private final GroupConverter groupConverter;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository,
                              StudentConverter studentConverter,
                              GroupConverter groupConverter) {
        this.studentRepository = studentRepository;
        this.studentConverter = studentConverter;
        this.groupConverter = groupConverter;
    }

    @Override
    public List<StudentDto> findAll() {
        return studentRepository.findAll()
                .stream().map(studentConverter::toDto).collect(toList());
    }

    @Override
    public List<StudentDto> findMarked(boolean deleted) {
        return studentRepository.findMarked(deleted)
                .stream().map(studentConverter::toDto).collect(toList());
    }

    @Override
    public StudentDto findById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.map(studentConverter::toDto).orElse(null);
    }

    @Override
    public StudentDto save(StudentDto studentDto) {
        return studentConverter.toDto(studentRepository.save(studentConverter.toEntity(studentDto)));
    }

    @Override
    public void delete(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            studentRepository.markDeleted(optionalStudent.get());
        } else throw new EntityNotFoundException(format("Student(id = %s) not found.", id));
    }

    @Override
    public List<StudentDto> findByGroup(GroupDto groupDto) {
        return studentRepository.findByGroup(groupConverter.toEntity(groupDto)).stream()
                .filter(s -> !s.isDeleted()).map(studentConverter::toDto).collect(toList());
    }
}
