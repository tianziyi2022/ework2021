package com.hebutgo.ework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hebutgo.ework.common.exception.BizException;
import com.hebutgo.ework.entity.Student;
import com.hebutgo.ework.entity.StudentVo;
import com.hebutgo.ework.mapper.StudentMapper;
import com.hebutgo.ework.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tianziyi
 * @since 2020-10-06
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    @Resource
    StudentMapper studentMapper;

    @Override
    public StudentVo getInfo(String stuNumber, String phone) {
        Student student0 = Student.builder().
                stuNumber(stuNumber).
                phone(phone).
                build();
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.setEntity(student0);
        Student student = studentMapper.selectOne(studentQueryWrapper);
        if(Objects.isNull(student)){
            throw new BizException("查询不到符合条件的信息");
        }
        StudentVo studentVo = new StudentVo();
        BeanUtils.copyProperties(student,studentVo);
        switch (student.getStatus()){
            case 0:
                studentVo.setStatus("未核验");
                break;
            case 1:
                studentVo.setStatus("已确认，信息正确");
                break;
            case 2:
                studentVo.setStatus("已核验，信息有误");
                break;
            default:
                studentVo.setStatus("状态异常");
        }
        return studentVo;
    }

    @Override
    public String correct(Integer id) {
        Student student = studentMapper.selectById(id);
        if(Objects.isNull(student)){
            throw new BizException("查询不到符合条件的信息");
        }

        student.setStatus(1);
        studentMapper.updateById(student);
        return "信息核验成功";
    }

    @Override
    public String wrong(Integer id) {
        Student student = studentMapper.selectById(id);
        if(Objects.isNull(student)){
            throw new BizException("查询不到符合条件的信息");
        }
        student.setStatus(2);
        studentMapper.updateById(student);
        return "信息核验成功";
    }
}
