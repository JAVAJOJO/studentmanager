package com.stu.admin.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stu.admin.dao.StuManagerDao;
@Service
@Transactional
public class StuManagerService {
	@Autowired
	private StuManagerDao stuManagerDao;
    /**
     * 获取开关状态
     * @return
     */
    public Map<String,Object> getSwitch(String name) {
        Map<String,Object> result = new HashMap<>();
        int data = stuManagerDao.getSwitch(name);
        result.put("code",true);
        result.put("data",data);
        return result;
    }
    /**
     * 修改switch开关状态
     * @param name
     * @param state
     * @return
     */
    public Map<String,Object> setSwitch(String name, boolean state) {
        Map<String,Object> result = new HashMap<>();
        String num = "0";
        if(state==true){
            num="1";
        }
        stuManagerDao.setSwitch(name,num);
        result.put("code",true);
        return result;
    }

	/**
	 * 获取全部学生信息
	 * @return
	 */
	public Map<String, Object> getAllstu(String keywords,int page,int rows) {
		Map<String,Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
		if(keywords==null || keywords=="null"){
			keywords = "";
		}
		int begin = (page-1)*rows;
		int total = stuManagerDao.getCountStu(keywords);
		data = stuManagerDao.getAllStu(keywords,begin,rows);
		result.put("total",total);
		result.put("rows",data);
		return result;
	}
	/**
	 * 添加学生
	 * @param addName
	 * @param addIdcard
	 * @param addSex
	 * @return
	 */
	public Map<String,Object> addsstudent(String addName,String addIdcard, String addSex) {
		Map<String,Object> result = new HashMap<String,Object>();
		if(addName==null){
			result.put("code",false);
			result.put("msg","学生姓名不能为空!");
			return result;
		}
		if(addIdcard==null){
			addIdcard = "";
		}
		stuManagerDao.addsstudent(addName,addIdcard,addSex);
		result.put("code",true);
		result.put("msg","添加成功!");
		return result;
	}
	/**
	 * 添加学生
	 * @param Username
	 * @param Password
	 * @return
	 */
	public Map<String,Object> addstudent(String Username,String Password,int Id) {
		Map<String,Object> result = new HashMap<String,Object>();
		stuManagerDao.addstudent(Username,Password,Id);
		result.put("code",true);
		result.put("msg","添加成功!");
		return result;
	}
	/**
	 * 删除学生信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> delStudent(String id) {
		Map<String,Object> result = new HashMap<String,Object>();
		//删除学生表中数据
		stuManagerDao.delStudent(id);
		//删除学生选课记录
		stuManagerDao.delStudentInCourse(id);
		result.put("code",true);
		result.put("msg","删除成功!");
		return result;
	}
//	/**
//	 * 多选删除
//	 * @param ids
//	 * @return
//	 */
//	public Map<String,Object> delMoreStudent(String[] ids) {
//		Map<String,Object> result = new HashMap<String,Object>();
//		if(ids==null){
//			result.put("code", false);
//			result.put("msg", "没有选择行!");
//			return result;
//		}
//		List<String> idList = new ArrayList<>();
//		for(String str:ids){
//			idList.add(str);
//		}
//		stuManagerDao.delMoreStudent(idList);
//		result.put("code", true);
//		result.put("msg", "删除成功!");
//		return result;
//	}

    /**
     * 根据学生ID查找学生
     * @param id
     * @return
     */
    public Map<String,Object> getStudent(String id) {
        Map<String,Object> result = new HashMap<String,Object>();
        Map<String,Object> data = new HashMap<String,Object>();

        if(id!=null && id!="null" && id!=""){
				data = stuManagerDao.getStudent(id);
            result.put("code", true);
            result.put("data", data);
            return result;
        }
        result.put("code", false);
        result.put("msg", "查找失败");
        return result;
    }

    /**
     * 修改学生信息
     * @param updateName
     * @param updateIdcard
     * @param updateSex
     * @param updatePhone
     * @param updateQQ
     * @param updateEmail
     * @param updateAddress
     * @return
     */
    public Map<String,Object> updateStudent(String updateName, String updateIdcard, String updateSex, String updatePhone, String updateQQ, String updateEmail, String updateAddress,String id) {
        Map<String,Object> result = new HashMap<String,Object>();
        if(updateName==null){
            updateName="";
        }
        if(updateIdcard==null){
            updateIdcard="";
        }
        if(updateSex==null){
            updateSex="";
        }
        if(updatePhone==null){
            updatePhone="";
        }
        if(updateQQ==null){
            updateQQ="";
        }
        if(updateEmail==null){
            updateEmail="";
        }
        if(updateAddress==null){
            updateAddress="";
        }
        stuManagerDao.updateStudent(updateName,updateIdcard,updateSex,updatePhone,updateQQ,updateEmail,updateAddress,id);
        result.put("code",true);
        result.put("msg","修改成功");
        return result;
    }

	/**
	 * 获取学生下拉列表
	 * @return
	 */
	public List<Map<String,Object>> getStudentCombox() {
		List<Map<String,Object>> studentCombox = new ArrayList<>();
		studentCombox = stuManagerDao.getstudentCombox();
		return studentCombox;
	}
}
