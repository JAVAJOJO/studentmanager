package com.stu.user.service;
import com.stu.user.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
@Service
@Transactional
public class StudentService {
    @Autowired
    private StudentDao studentDao;
    /**
     * 获取学生信息
     * @param stuId
     * @return
     */
    public Map<String,Object> getStuMsg(int stuId) {
        Map<String,Object> result = new HashMap<String,Object>();
        Map<String,Object> data = new HashMap<String,Object>();
        data = studentDao.getStuMsg(stuId);
        result.put("data",data);
        return result;
    }
    /**
     * 修改学生信息
     * @param stuId
     * @param idcard
     * @param sex
     * @param phone
     * @param qq
     * @param email
     * @param address
     * @return
     */
    public Map<String,Object> updateMsg(int stuId, String idcard, String sex, String phone, String qq, String email, String address) {
        Map<String,Object> result = new HashMap<String,Object>();
        //查看开关状态
        int flag = studentDao.getSwitch();
        if(flag==1) {
            studentDao.updateMsg(stuId, idcard, sex, phone, qq, email, address);
            result.put("msg", "修改成功！");
            return result;
        }else{
            result.put("msg", "修改资料暂时未开通，请联系管理员开通");
            return result;
        }
    }
}
