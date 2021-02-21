package com.hebutgo.ework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hebutgo.ework.common.exception.BizException;
import com.hebutgo.ework.entity.StudentSpring2021;
import com.hebutgo.ework.entity.StudentSpring2021Vo;
import com.hebutgo.ework.entity.UpdateSpringRequest;
import com.hebutgo.ework.mapper.StudentSpring2021Mapper;
import com.hebutgo.ework.service.IStudentSpring2021Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tianziyi
 * @since 2021-02-20
 */
@Service
public class StudentSpring2021ServiceImpl extends ServiceImpl<StudentSpring2021Mapper, StudentSpring2021> implements IStudentSpring2021Service {

    @Resource
    StudentSpring2021Mapper StudentSpring2021Mapper;

    @Override
    public StudentSpring2021Vo getInfo(String studentId, String idCard) {
        StudentSpring2021 studentSpring20210 = StudentSpring2021.builder().
                studentId(studentId).
                idCard(idCard).
                build();
        QueryWrapper<StudentSpring2021> studentSpring2021QueryWrapper = new QueryWrapper<>();
        studentSpring2021QueryWrapper.setEntity(studentSpring20210);
        StudentSpring2021 StudentSpring2021 = StudentSpring2021Mapper.selectOne(studentSpring2021QueryWrapper);
        if(Objects.isNull(StudentSpring2021)){
            throw new BizException("查询不到符合条件的信息");
        }
        StudentSpring2021Vo studentSpring2021Vo = new StudentSpring2021Vo();
        BeanUtils.copyProperties(StudentSpring2021,studentSpring2021Vo);
        return studentSpring2021Vo;
    }

    @Override
    public String correct(Integer id) {
        StudentSpring2021 studentSpring2021 = StudentSpring2021Mapper.selectById(id);
        if(Objects.isNull(studentSpring2021)){
            throw new BizException("查询不到符合条件的信息");
        }
        StudentSpring2021Mapper.updateById(studentSpring2021);
        return "信息核验成功";
    }

    @Override
    public String wrong(Integer id) {
        StudentSpring2021 studentSpring2021 = StudentSpring2021Mapper.selectById(id);
        if(Objects.isNull(studentSpring2021)){
            throw new BizException("查询不到符合条件的信息");
        }
        StudentSpring2021Mapper.updateById(studentSpring2021);
        return "信息核验成功";
    }

    @Override
    public String update(UpdateSpringRequest updateSpringRequest) {
        StudentSpring2021 studentSpring2021 = StudentSpring2021Mapper.selectById(updateSpringRequest.getId());
        if(Objects.isNull(studentSpring2021)){
            throw new BizException("查询不到符合条件的信息");
        }
        BeanUtils.copyProperties(updateSpringRequest,studentSpring2021);
        studentSpring2021.setUpdateTime(new Timestamp(System.currentTimeMillis()+28800000));
        studentSpring2021.setTimes(studentSpring2021.getTimes()+1);
        StudentSpring2021Mapper.updateById(studentSpring2021);
        return "信息修改成功";
    }
}
