package com.hebutgo.ework.controller;


import com.hebutgo.ework.common.ApiResponse;
import com.hebutgo.ework.common.CommonConstant;
import com.hebutgo.ework.common.ErrorCodeEnum;
import com.hebutgo.ework.common.exception.BizException;
import com.hebutgo.ework.entity.CheckRequest;
import com.hebutgo.ework.entity.GetSpringInfoRequest;
import com.hebutgo.ework.entity.StudentSpring2021;
import com.hebutgo.ework.entity.StudentSpring2021Vo;
import com.hebutgo.ework.service.IStudentSpring2021Service;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tianziyi
 * @since 2021-02-20
 */
@RestController
@RequestMapping("/ework/studentSpring2021")
public class StudentSpring2021Controller {

    Logger logger = LoggerFactory.getLogger(StudentSpring2021.class);

    @Autowired
    IStudentSpring2021Service iStudentSpring2021Service;

    @CrossOrigin
    @ApiOperation(value = "信息查询",tags = CommonConstant.STUDENTSPRING2021)
    @PostMapping("/getInfo")
    public ApiResponse<StudentSpring2021Vo> getInfo(
            @RequestBody GetSpringInfoRequest getSpringInfoRequest
    ){
        StudentSpring2021Vo studentSpring2021Vo;
        try{
            studentSpring2021Vo = iStudentSpring2021Service.getInfo(getSpringInfoRequest.getStudentId(),getSpringInfoRequest.getIdCard());
        }catch (BizException e) {
            logger.error("信息查询失败" + getSpringInfoRequest.toString(), e);
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            logger.error("信息查询失败" + getSpringInfoRequest.toString(), e);
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        logger.info("信息查询成功" + studentSpring2021Vo.getName() + " " +studentSpring2021Vo.getStudentId() );
        return ApiResponse.success(studentSpring2021Vo);
    }

    @CrossOrigin
    @ApiOperation(value = "信息核验（正确）",tags = CommonConstant.STUDENTSPRING2021)
    @PostMapping("/correct")
    public ApiResponse<String> correct(
            @RequestBody CheckRequest checkRequest
    ){
        String string;
        try{
            string = iStudentSpring2021Service.correct(checkRequest.getId());
        }catch (BizException e) {
            logger.error("信息核验（正确）失败"+checkRequest.getId(), e);
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            logger.error("信息核验（正确）失败"+checkRequest.getId(), e);
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        logger.info("信息核验（正确）成功"+checkRequest.getId());
        return ApiResponse.success(string);
    }

    @CrossOrigin
    @ApiOperation(value = "信息核验（错误）",tags = CommonConstant.STUDENTSPRING2021)
    @PostMapping("/wrong")
    public ApiResponse<String> wrong(
            @RequestBody CheckRequest checkRequest
    ){
        String string;
        try{
            string = iStudentSpring2021Service.wrong(checkRequest.getId());
        }catch (BizException e) {
            logger.error("信息核验（错误）失败"+checkRequest.getId(), e);
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            logger.error("信息核验（错误）失败"+checkRequest.getId(), e);
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        logger.info("信息核验（错误）成功"+checkRequest.getId());
        return ApiResponse.success(string);
    }
}
