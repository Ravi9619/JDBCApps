package com.test.service;

import com.test.dto.Student;

public interface IStudentService {
	
	public String addStudent(String sname, Integer age, String saddress, String gender);
	
	public Student searchStudent(Integer sid);
	
	public String updateStudent(Integer sid,String sname, Integer age, String saddress, String gender);
	
	public String deleteStudent(Integer sid);
}
