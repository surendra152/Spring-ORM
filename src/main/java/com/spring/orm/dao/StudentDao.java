package com.spring.orm.dao;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.spring.orm.entities.Student;

public class StudentDao {


	private HibernateTemplate hibernateTemplate;


	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}



	//save student
	@Transactional
	public int insert(Student student)
	{	
		//insert
		Integer i =(Integer) this.hibernateTemplate.save(student);
		return i;
	}

	//get the single data(object)
	public Student getStudent(int studentId)
	{
		Student student1 = this.hibernateTemplate.get(Student.class, studentId);

		return student1;

	}

	//get all students(all rows)
	public List<Student> getAllStudent(){
		
		List<Student> students = this.hibernateTemplate.loadAll(Student.class);
		
		return students;
	}
	
	//delete the data
	@Transactional
	public void deleteStudent(int studentId)
	{
		Student student2 = this.hibernateTemplate.get(Student.class,studentId);
		
		this.hibernateTemplate.delete(student2);
		
	}
	
	
	//updating the data
	@Transactional
	public void updateStudent(Student student) {
		
		this.hibernateTemplate.update(student);
	}


}
