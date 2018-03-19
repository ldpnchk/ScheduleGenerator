package com.sg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sg.entity.Specialty;
import com.sg.entity.Student;
import com.sg.service.SpecialtyService;
import com.sg.service.StudentService;

@Controller
@RequestMapping("/student/")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private SpecialtyService specialtyService;

	@ResponseBody
	@RequestMapping(value = "/getAllByDiscipline", method = RequestMethod.POST, produces = "application/json")
	public List<Student> getStudents(@RequestParam int id) {
		List<Student> students = studentService.getAllByDiscipline(id);
		return students;
	}

	@ResponseBody
	@RequestMapping(value = "/getAllSpecialties/{wsId}", method = RequestMethod.POST, produces = "application/json")
	public List<Specialty> getAllSpecialties(@PathVariable int wsId) {
		List<Specialty> specialties = specialtyService.getAllByWorksheet(wsId);
		return specialties;
	}

	@ResponseBody
	@RequestMapping(value = "/studentsByYearAndSpecialty/{year}/{specialty}", method = RequestMethod.POST, produces = "application/json")
	public List<Student> getByYearAndSpecialty(@PathVariable int year, @PathVariable int specialty) {
		List<Student> students = studentService.getAllByCourseAndSpecialty(year, specialty);
		return students;
	}

	@ResponseBody
	@RequestMapping(value = "/studentsNotInByDisciplineAndCourseAndSpecialty/{course}/{idSpecialty}/{idDiscipline}", method = RequestMethod.POST, produces = "application/json")
	public List<Student> getNotEnrolledStudentsByDisciplineAndCourseAndSpecialty(@PathVariable int course, @PathVariable int idSpecialty, @PathVariable int idDiscipline) {
		List<Student> students = studentService.getNotEnrolledStudentsByDisciplineAndCourseAndSpecialty(course, idSpecialty, idDiscipline);
		return students;
	}

	@ResponseBody
	@RequestMapping(value = "/create/{wsId}", method = RequestMethod.POST)
	public String createStudent(@PathVariable int wsId, @RequestParam String name, @RequestParam int year, @RequestParam int specialtyId){
		Student student = new Student();
		student.setName(name);
		student.setYear(year);
		List<Specialty> specialties = specialtyService.getAllByWorksheet(wsId);
		Specialty specialty = null;
		for (Specialty temp : specialties){
			if(temp.getId()==specialtyId){
				specialty = temp;
			}
		}
		student.setSpecialty(specialty);
		System.out.println(name+year+specialty.getName());
		studentService.add(student);
		return "success";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteStudent(@PathVariable int id, Model model){
		studentService.delete(id);
		model.addAttribute("students", studentService.getAllByCourseAndSpecialty(0, 0));
		return "students";
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String viewDiscipline(@PathVariable int id, Model model){
		List<Student> students = studentService.getAllByCourseAndSpecialty(0, 0);
		Student student = null;
		for(Student temp : students){
			if(temp.getId()==id){
				student = temp;
			}
		}
		model.addAttribute("student", student);
		return "studentDetails";
	}

}
