package com.gusi.demo.idao;

import java.util.List;

import com.gusi.demo.pojo.Student;

public interface IStudent {
	public Student queryOneStudent(Long id);

	public void insertOneStudent(Student student);

	public void insertBatchStudent(List<Student> students);
}
