package com.kd.rest.restClient.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kd.rest.restClient.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	static List<Student> list = new ArrayList<>();
	static {
		Student s1 = new Student(1001, "Kumar");
		Student s2 = new Student(1002, "Suresh");
		list.add(s1);
		list.add(s2);
	}
	
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello !!";
	}
	
	@GetMapping(value="/students", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Student>> getStudent(){
		return new ResponseEntity<List<Student>>(list, HttpStatus.OK);
	}
	
	@PostMapping(value="/students", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Student>> createStudent(@RequestBody Student student){
		list.add(student);
		return new ResponseEntity<List<Student>>(list, HttpStatus.CREATED);
	}
	
	@PutMapping(value="/students/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Student>> updateStudent(@PathVariable("id") int id, @RequestBody Student student){
		for(Student std:list) {
			if(std.getId() == id) {
				std.setName(student.getName());
				break;
			}
		}
		return new ResponseEntity<List<Student>>(list, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/students/{id}")
	public ResponseEntity<List<Student>> deleteStudent(@PathVariable("id") int id){
		for(Student std:list) {
			if(std.getId() == id) {
				list.remove(std);
				break;
			}
		}
		return new ResponseEntity<List<Student>>(list, HttpStatus.OK);
	}

}
