package com.hebutgo.ework.controller;


import com.hebutgo.ework.common.ApiResponse;
import com.hebutgo.ework.common.CommonConstant;
import com.hebutgo.ework.common.ErrorCodeEnum;
import com.hebutgo.ework.common.exception.BizException;
import com.hebutgo.ework.entity.CheckRequest;
import com.hebutgo.ework.entity.GetInfoRequest;
import com.hebutgo.ework.entity.Student;
import com.hebutgo.ework.entity.StudentVo;
import com.hebutgo.ework.service.IStudentService;
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
 * @since 2020-10-06
 */
@RestController
@RequestMapping("/ework/student")
public class StudentController {

    Logger logger = LoggerFactory.getLogger(Student.class);

    @Autowired
    IStudentService iStudentService;

    @CrossOrigin
    @ApiOperation(value = "信息查询",tags = CommonConstant.STUDENT)
    @PostMapping("/getInfo")
    public ApiResponse<StudentVo> getInfo(
            @RequestBody GetInfoRequest getInfoRequest
            ){
        StudentVo studentVo;
        try{
            studentVo = iStudentService.getInfo(getInfoRequest.getStuNumber(),getInfoRequest.getPhone());
        }catch (BizException e) {
            logger.error("信息查询失败", e);
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            logger.error("信息查询失败", e);
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        logger.info("信息查询成功");
        return ApiResponse.success(studentVo);
    }

    @CrossOrigin
    @ApiOperation(value = "信息核验（正确）",tags = CommonConstant.STUDENT)
    @PostMapping("/correct")
    public ApiResponse<String> correct(
            @RequestBody CheckRequest checkRequest
    ){
        String string;
        try{
            string = iStudentService.correct(checkRequest.getId());
        }catch (BizException e) {
            logger.error("信息核验（正确）失败", e);
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            logger.error("信息核验（正确）失败", e);
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        logger.info("信息核验（正确）成功");
        return ApiResponse.success(string);
    }

    @CrossOrigin
    @ApiOperation(value = "信息核验（错误）",tags = CommonConstant.STUDENT)
    @PostMapping("/wrong")
    public ApiResponse<String> wrong(
            @RequestBody CheckRequest checkRequest
    ){
        String string;
        try{
            string = iStudentService.wrong(checkRequest.getId());
        }catch (BizException e) {
            logger.error("信息核验（错误）失败", e);
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            logger.error("信息核验（错误）失败", e);
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        logger.info("信息核验（错误）成功");
        return ApiResponse.success(string);
    }

}
