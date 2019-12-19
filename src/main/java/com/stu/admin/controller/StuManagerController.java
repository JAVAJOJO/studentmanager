package com.stu.admin.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stu.admin.service.StuManagerService;
import com.stu.util.ResponseUtil;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Controller
@RequestMapping(value = "/stu/stuManager" ,method = {RequestMethod.POST,RequestMethod.GET})
public class StuManagerController {
	@Autowired
	private StuManagerService stuManagerService;
	/**
	 * 获取管理员的登录状态
	 * @param
	 */
	@RequestMapping("/getAdminLoginStatus.do")
	public void getAdminLoginStatus(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		Map<String,Object> result = new HashMap<String,Object>();

		if(request.getSession().getAttribute("adminDisplayName")!=null)
		{
			result.put("code",true);
		}
		else
		{
			result.put("code",false);
		}
		ResponseUtil.returnJson(result,response);
	}
	/**
	 * 获取switch开关状态
	 * @param name
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/getSwitch.do")
	public void getSwitch(String name,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> result = new HashMap<String,Object>();
		result = stuManagerService.getSwitch(name);
		ResponseUtil.returnJson(result,response);
	}
	/**
	 * 修改switch开关状态
	 * @param name
	 * @param state
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/setSwitch.do")
	public void setSwitch(String name,boolean state,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> result = new HashMap<String,Object>();
		result = stuManagerService.setSwitch(name,state);
		ResponseUtil.returnJson(result,response);
	}
	/**
	 * 获取全部学生信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getAllStu.do",method = {RequestMethod.POST,RequestMethod.GET})
	public void getAllStu(String keywords,int page,int rows ,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> data = new HashMap<String,Object>();
		data = stuManagerService.getAllstu(keywords,page,rows);
		ResponseUtil.returnJson(data, response);
	}
	/**
	 * 添加学生
	 * @param name
	 * @param idcard
	 * @param sex
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/addsstudent.do",method = {RequestMethod.POST,RequestMethod.GET})
	public void addsstudent(String name,String idcard,String sex ,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> data = new HashMap<String,Object>();
		data = stuManagerService.addsstudent(name,idcard,sex);
		ResponseUtil.returnJson(data, response);
	}
	/**
	 * 添加
	 * @param username
	 * @param password
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/addstudent.do",method = {RequestMethod.POST,RequestMethod.GET})
	public void addstudent(String username,String password,int id,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> data = new HashMap<String,Object>();
		data = stuManagerService.addstudent(username,password,id);
		ResponseUtil.returnJson(data, response);
	}
	/**
	 * 删除学生信息
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/delStudent.do",method = {RequestMethod.POST,RequestMethod.GET})
	public void delStudent(String id ,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> data = new HashMap<String,Object>();
		data = stuManagerService.delStudent(id);
		ResponseUtil.returnJson(data, response);
	}
	/**
	 * 根据学生ID查找学生
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/getStudent.do",method = {RequestMethod.POST,RequestMethod.GET})
	public void getStudent(String id,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> data = new HashMap<String,Object>();
		data = stuManagerService.getStudent(id);
		ResponseUtil.returnJson(data, response);
	}
	/**
	 * 修改学生信息
	 * @param id
	 * @param name
	 * @param idcard
	 * @param sex
	 * @param phone
	 * @param qq
	 * @param email
	 * @param address
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/updateStudent.do",method = {RequestMethod.POST,RequestMethod.GET})
	public void updateStudent(String id,String name,String idcard,String sex,String phone,String qq,String email,String address,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> data = new HashMap<String,Object>();
		data = stuManagerService.updateStudent(name,idcard,sex,phone,qq,email,address,id);
		ResponseUtil.returnJson(data, response);
	}

	/**
	 * 获取学生下拉列表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/getStudentCombox.do",method = {RequestMethod.POST,RequestMethod.GET})
	public void getStudentCombox(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Map<String,Object>> data = new ArrayList<>();
		data = stuManagerService.getStudentCombox();
		ResponseUtil.returnJson(data, response);
	}

}
