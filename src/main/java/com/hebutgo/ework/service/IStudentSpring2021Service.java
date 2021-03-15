package com.hebutgo.ework.service;

import com.hebutgo.ework.entity.StudentSpring2021;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hebutgo.ework.entity.StudentSpring2021Vo;
import com.hebutgo.ework.entity.UpdateSpringRequest;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tianziyi
 * @since 2021-02-20
 */
public interface IStudentSpring2021Service extends IService<StudentSpring2021> {

    public StudentSpring2021Vo getInfo(String studentId, String idCard);

    public String correct(Integer id);

    public String wrong(Integer id);

    public String update(UpdateSpringRequest updateSpringRequest);

    public List<StudentSpring2021> getAllInfo();
}
