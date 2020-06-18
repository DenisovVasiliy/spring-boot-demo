package com.dva.demo.services.implementation;

import com.dva.demo.DemoApplication;
import com.dva.demo.model.Student;
import com.dva.demo.model.converters.StudentConverter;
import com.dva.demo.model.dto.StudentDto;
import com.dva.demo.repository.StudentRepository;
import com.dva.demo.services.StudentService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(classes = DemoApplication.class)
class StudentServiceImplTest {

    @MockBean
    private StudentRepository studentRepository;
    @MockBean
    private StudentConverter studentConverter;
    @Autowired
    private StudentService studentService;
    private static Student student = new Student();
    private static StudentDto studentDto = new StudentDto();

    @BeforeAll
    public static void setUp() {
        student.setId(1L);
        student.setFirstName("Ivan");
        student.setLastName("Petrov");
        student.setDeleted(false);
        studentDto.setId(1L);
        studentDto.setFirstName("Ivan");
        studentDto.setLastName("Petrov");
        studentDto.setDeleted(false);
    }

    @Test
    public void shouldCallRepositoryFindAllAndReturnTransformedToDtoResultList() {
        when(studentConverter.toDto(student)).thenReturn(studentDto);
        when(studentRepository.findAll()).thenReturn(singletonList(student));

        List<StudentDto> actual = studentService.findAll();

        verify(studentRepository).findAll();
        verify(studentConverter).toDto(student);
        assertEquals(singletonList(studentDto), actual);
    }
}