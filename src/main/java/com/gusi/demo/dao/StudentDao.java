package com.gusi.demo.dao;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.gusi.demo.idao.IStudent;
import com.gusi.demo.pojo.Student;
import com.gusi.demo.utils.MybatisUtils;

public class StudentDao {
	MybatisUtils mybatisUtils = new MybatisUtils();
	SqlSession sqlSession = null;

	public Student queryOneStudent(Long id) {
		Student student = null;
		try {
			sqlSession = mybatisUtils.getSqlSession();
			IStudent iStudent = sqlSession.getMapper(IStudent.class);
			student = iStudent.queryOneStudent(id);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return student;
	}

	public void insertOneStudent(Student student) {
		try {
			sqlSession = mybatisUtils.getSqlSession();
			IStudent iStudent = sqlSession.getMapper(IStudent.class);
			iStudent.insertOneStudent(student);
			sqlSession.commit();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

	public void insertBatchStudent(List<Student> students) {
		try {
			sqlSession = mybatisUtils.getSqlSession();
			IStudent iStudent = sqlSession.getMapper(IStudent.class);
			iStudent.insertBatchStudent(students);
			sqlSession.commit();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
}
