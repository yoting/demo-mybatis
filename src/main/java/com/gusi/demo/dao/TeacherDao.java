package com.gusi.demo.dao;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;

import com.gusi.demo.idao.ITeacher;
import com.gusi.demo.pojo.Teacher;
import com.gusi.demo.utils.MybatisUtils;

public class TeacherDao {
	MybatisUtils mybatisUtils = new MybatisUtils();
	SqlSession sqlSession = null;

	public Teacher queryOneTeacher(Long id) {
		Teacher teacher = null;
		try {
			sqlSession = mybatisUtils.getSqlSession();
			ITeacher iTeacher = sqlSession.getMapper(ITeacher.class);
			teacher = iTeacher.queryOneTeacher(id);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return teacher;
	}

	public Teacher queryOneTeacherWithStudent(Long id) {
		Teacher teacher = null;
		try {
			sqlSession = mybatisUtils.getSqlSession();
			ITeacher iTeacher = sqlSession.getMapper(ITeacher.class);
			teacher = iTeacher.queryOneTeacherWithStudent(id);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return teacher;
	}

	public int insertOneTeacher(Teacher teacher) {
		int count = 0;
		try {
			sqlSession = mybatisUtils.getSqlSession();
			ITeacher iTeacher = sqlSession.getMapper(ITeacher.class);
			count = iTeacher.insertOneTeacher(teacher);
			sqlSession.commit();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return count;
	}
}
