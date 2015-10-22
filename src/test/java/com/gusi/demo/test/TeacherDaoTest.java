package com.gusi.demo.test;

import java.util.Arrays;

import org.junit.Test;

import com.gusi.demo.dao.StudentDao;
import com.gusi.demo.dao.TeacherDao;
import com.gusi.demo.pojo.Student;
import com.gusi.demo.pojo.Teacher;

public class TeacherDaoTest {

	@Test
	public void testInsertTeacher() {
		Teacher teacher = new Teacher();
		teacher.setName("qqq");
		teacher.setCourse("computer");

		TeacherDao dao = new TeacherDao();
		dao.insertOneTeacher(teacher);
		System.out.println(teacher.getId());
	}

	@Test
	public void testQueryOneTeacher() {
		TeacherDao dao = new TeacherDao();
		Teacher teacher = dao.queryOneTeacher(1L);
		System.out.println(teacher);
	}

	@Test
	public void testQueryTeacherWithStudent() {
		TeacherDao dao = new TeacherDao();
		Teacher teacher = dao.queryOneTeacherWithStudent(1L);
		System.out.println(teacher);
	}

	@Test
	public void testInsertTeacherWithStudent() {
		TeacherDao daoT = new TeacherDao();
		StudentDao daoS = new StudentDao();

		Teacher t = new Teacher();
		t.setName("teacher1");
		t.setCourse("ep");
		daoT.insertOneTeacher(t);

		Student s1 = new Student();
		s1.setName("s1");
		s1.setSex(false);
		s1.setTeacherId(t.getId());
		Student s2 = new Student();
		s2.setName("s2");
		s2.setSex(false);
		s2.setTeacherId(t.getId());

		daoS.insertBatchStudent(Arrays.asList(s1, s2));
	}
}
