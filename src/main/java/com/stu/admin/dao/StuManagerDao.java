package com.stu.admin.dao;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
@Component
public interface StuManagerDao {

	int getCountStu(String keywords);

	List<Map<String,Object>> getAllStu(String keywords, int begin, int rows);

	void addsstudent(String addName, String addIdcard, String addSex);
	void addstudent(String Username,String Password,int Id);
	void delStudent(String id);

	void delMoreStudent(List<String> idList);

	Map<String,Object> getStudent(String id);

	void updateStudent(String updateName, String updateIdcard, String updateSex, String updatePhone, String updateQQ, String updateEmail, String updateAddress,String id);

	void delStudentInCourse(String id);

	int getSwitch(String name);

	void setSwitch(String name, String num);

	List<Map<String,Object>> getstudentCombox();

}
