package com.workshop.controller;

import com.workshop.bean.Student;
import com.workshop.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by msjo on 6/14/2015.
 */
@RestController
@RequestMapping("/students")
public class SpringRestController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Student> findAll() {
        return studentService.findAll();
    }


    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Student findById(@PathVariable("id") String anId) {
        return studentService.findStudentById(Integer.parseInt(anId));
    }


    @RequestMapping(value = "search/{query}", method = RequestMethod.GET)
    public List<Student> search(@PathVariable("query") String aQuery) {
        // If student is not static return new list.
        List<Student> newList = new ArrayList<Student>();
        newList.add(studentService.findStudentByName(aQuery));
        return newList;
    }


    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable("id") int anId) {
        studentService.removeStudentById(anId);
    }


    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public void update(@RequestBody Student aStudent) {
        studentService.saveOrUpdateStudent(aStudent);
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public void addStudent(@RequestBody Student aStudent) {
        studentService.saveOrUpdateStudent(aStudent);
    }


}
