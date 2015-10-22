package com.gusi.demo.test;

import java.util.Arrays;

import org.junit.Test;

import com.gusi.demo.dao.StudentDao;
import com.gusi.demo.pojo.Student;

public class StudentDaoTest {
	@Test
	public void testInsertOneStudent() {
		Student student = new Student();
		student.setName("d");
		student.setSex(true);
		student.setTeacherId(1L);

		StudentDao dao = new StudentDao();
		dao.insertOneStudent(student);
	}

	@Test
	public void testInsertBatchStudent() {
		Student student1 = new Student();
		student1.setName("batch1");
		student1.setSex(true);
		student1.setTeacherId(1L);
		Student student2 = new Student();
		student2.setName("batch2");
		student2.setSex(true);
		student2.setTeacherId(1L);
		Student student3 = new Student();
		student3.setName("batch3");
		student3.setSex(true);
		student3.setTeacherId(1L);

		StudentDao dao = new StudentDao();
		dao.insertBatchStudent(Arrays.asList(student1, student2, student3));
	}

	@Test
	public void testQueryOneStudent() {
		StudentDao dao = new StudentDao();
		Student student = dao.queryOneStudent(1L);
		System.out.println(student);
	}
}
