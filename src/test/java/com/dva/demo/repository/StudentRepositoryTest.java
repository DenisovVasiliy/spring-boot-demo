package com.dva.demo.repository;

import com.dva.demo.DemoApplication;
import com.dva.demo.model.Group;
import com.dva.demo.model.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(classes = DemoApplication.class)
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;
    private static Student student = new Student();
    private static Group group = new Group();

    @BeforeAll
    public static void setUp() {
        student.setId(1L);
        student.setFirstName("Ivan");
        student.setLastName("Ivanov");
        student.setDeleted(false);
        group.setId(1L);
    }

    @Test
    public void shouldFindActiveStudents() {
        List<Student> actual = studentRepository.findMarked(false);
        assertEquals(singletonList(student), actual);
    }

    @Test
    public void shouldFindStudentsByGroup() {
        List<Student> actual = studentRepository.findByGroup(group);
        assertEquals(singletonList(student), actual);
    }
}