package com.firstapp.Controller;

import com.firstapp.Model.Student;
import com.firstapp.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;

import java.util.List;

@CrossOrigin
@RestController

public class ApiController {
    @Autowired
    private StudentRepository studentRepository;
        @GetMapping("/")
    public String getPage(){
            return "Hello";
    }
    @GetMapping("students")
    public List<Student>  getStudents(){
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
            return studentRepository.findAll(sort);
    }@PostMapping("save")
    public void saveStudent(@RequestBody Student student) {
        if (student.getName() == null || student.getName().isEmpty()
                || student.getEmail() == null || student.getEmail().isEmpty()
                || student.getAge() == null) {
            throw new IllegalArgumentException("All fields are required");
        }
        studentRepository.save(student);
    }@PutMapping("update/{id}")
    public void updateStudent(@PathVariable Long id, @RequestBody Student student){
        Student updateStudent = studentRepository.findById(id).get();
        updateStudent.setName(student.getName());
        updateStudent.setEmail(student.getEmail());
        updateStudent.setAge(student.getAge());
        studentRepository.save(updateStudent);
    }@DeleteMapping("delete/{id}")
    public void  deleteStudent(@PathVariable Long id){
        Student deleteStudent = studentRepository.findById(id).get();
        studentRepository.delete(deleteStudent);
    }
}
