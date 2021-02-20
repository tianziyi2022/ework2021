package com.hebutgo.ework.service;

import com.hebutgo.ework.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hebutgo.ework.entity.StudentVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tianziyi
 * @since 2020-10-06
 */
public interface IStudentService extends IService<Student> {

    public StudentVo getInfo(String stuNumber, String phone);

    public String correct(Integer id);

    public String wrong(Integer id);

}
