package com.spring.orm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.orm.dao.StudentDao;
import com.spring.orm.entities.Student;


public class App 
{
	public static void main( String[] args )
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("Config.xml");
		StudentDao sDao= context.getBean("studentDao",StudentDao.class);

		/*
		 * Student result=new Student(203,"Surendra","Surat"); int r = sDao.insert(result);
		 * System.out.println("Done "+r);
		 */

		//get user input 
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

		boolean go=true;
		while(go)
		{
			System.out.println("press 1 for add new student");
			System.out.println("press 2 for display all students");
			System.out.println("press 3 for get details of single students");
			System.out.println("press 4 for delete a students");
			System.out.println("presss 5 for update students");
			System.out.println("press 6 for exit");
			System.out.println();

			try {
				System.out.print("Select from above option: ");

				int input = Integer.parseInt(br.readLine());

				/*we can do by using if else also
				 * if(input==1) { //add a new students
				 * 
				 * } else if(input==2) { //display students }
				 */

				//by using switch 
				switch (input) {
				case 1:
					//add a new student
					//taking inputs from user
					System.out.print("Enter user Id: ");
					int uId=Integer.parseInt(br.readLine());

					Student student = sDao.getStudent(uId);

					if(student==null)
					{
						System.out.print("Enter user name: ");
						String uName=br.readLine();

						System.out.print("Enter user city: ");
						String uCity=br.readLine();

						//creating student object
						Student s=new Student();

						//setting student values
						s.setStudentId(uId);
						s.setStudentname(uName);
						s.setStudentCity(uCity);

						//saving the data to database by insert method
						int r = sDao.insert(s);
						System.out.println(r+" Student added ");
						System.out.println("*********************************************");
					}else {
						System.out.println("please enter a unique user id.");
						System.out.println("*********************************************");
					}


					break;
				case 2:
					//display all students
					System.out.println("*********************************************");
					List<Student> allStudent = sDao.getAllStudent();

					for(Student sAll:allStudent)
					{
						System.out.println(" Id: "+sAll.getStudentId());
						System.out.println(" Name: "+sAll.getStudentname());
						System.out.println(" City: "+sAll.getStudentCity());
						System.out.println("-----------------------------------------------");
					}

					System.out.println("*********************************************");
					break;
				case 3:
					//get details of single students

					System.out.print("Enter user Id: ");
					int userId=Integer.parseInt(br.readLine());


					Student student2 = sDao.getStudent(userId);

					if(student2==null)
					{
						System.out.println("You have entered a Invalid user Id!!");
						System.out.println("*********************************************");

					}else{

						System.out.println(" Id: "+student2.getStudentId());
						System.out.println(" Name: "+student2.getStudentname());
						System.out.println(" City: "+student2.getStudentCity());
						System.out.println("-----------------------------------------------");


					}


					break;
				case 4:
					//delete single students

					System.out.print("Enter user Id: ");
					int Id=Integer.parseInt(br.readLine());


					sDao.deleteStudent(Id);

					System.out.println("Student deleted..");

					System.out.println("*********************************************");

					break;
				case 5:
					//update students

					System.out.print("Enter user New Id: ");
					int UId=Integer.parseInt(br.readLine());

					Student student3 = sDao.getStudent(UId);

					if(student3==null)
					{
				
						System.out.println("You have entered a Invalid user Id!!");
						System.out.println("*********************************************");
						
					}else {
						
						System.out.print("Enter user New name: ");
						String UName=br.readLine();

						System.out.print("Enter user New city: ");
						String UCity=br.readLine();

						//creating student object
						Student s2=new Student();

						//setting student values
						s2.setStudentId(UId);
						s2.setStudentname(UName);
						s2.setStudentCity(UCity);

						sDao.updateStudent(s2);

						System.out.println("Student updated...");

						System.out.println("*********************************************");
					}
						

						break;
					case 6:
						//for exit
						go=false;
						break;

					default:
						break;
					}


				}catch (Exception e) {
					System.out.println("Invalid input try with another one.");
					System.out.println(e.getMessage());

				}
			}
			System.out.println("Thank you for using my application.");
			System.out.println("See you soon!!");

		}
	}
