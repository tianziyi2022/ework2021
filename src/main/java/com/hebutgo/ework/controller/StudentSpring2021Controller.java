package com.hebutgo.ework.controller;


import com.hebutgo.ework.common.ApiResponse;
import com.hebutgo.ework.common.CommonConstant;
import com.hebutgo.ework.common.ErrorCodeEnum;
import com.hebutgo.ework.common.exception.BizException;
import com.hebutgo.ework.common.utils.ExcelUtil;
import com.hebutgo.ework.entity.*;
import com.hebutgo.ework.service.IStudentSpring2021Service;
import io.swagger.annotations.ApiOperation;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
        logger.info("信息查询成功 " + studentSpring2021Vo.getName() + " " +studentSpring2021Vo.getStudentId() );
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

    @CrossOrigin
    @ApiOperation(value = "修改信息",tags = CommonConstant.STUDENTSPRING2021)
    @PostMapping("/update")
    public ApiResponse<String> update(
            @RequestBody UpdateSpringRequest updateSpringRequest
            ){
        String string;
        try{
            string = iStudentSpring2021Service.update(updateSpringRequest);
        }catch (BizException e) {
            logger.error("信息修改失败"+updateSpringRequest.getId(), e);
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            logger.error("信息修改失败"+updateSpringRequest.getId(), e);
            return ApiResponse.error(ErrorCodeEnum.SYSTEM_DEFAULT_ERROR);
        }
        logger.info("信息修改成功"+updateSpringRequest.toString());
        return ApiResponse.success(string);
    }

    @CrossOrigin
    @ApiOperation(value = "导出全部学生信息到Excel", tags = CommonConstant.STUDENTSPRING2021)
    @GetMapping("/exportAllToExcel")
    public ApiResponse exportAllOrderToExcel(
            @RequestParam String adminId,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        List<StudentSpring2021> studentSpring2021s;
        try {
            if("admin".equals(adminId)){
                studentSpring2021s = iStudentSpring2021Service.getAllInfo();
                // 列名
                String[] columnNames = {"记录id", "学号", "身份证号", "姓名", "联系电话", "（返校前）居住地址（具体到门牌号）", "自驾/公共交通",
                        "返津路线（非自驾填“无”）", "返津自驾车牌号（非自驾填“无”）", "返津主要交通方式（火车/飞机/大巴车/天津市内公共交通）（非公共交通填“无”）",
                        "具体车次/航班号、车厢号、座位号（非公共交通填“无”）", "抵津场站（东站/西站/南站/机场/市内交通）（非公共交通填“无”）", "从居住地到机场、车站交通方式（具体到车牌号）（非公共交通填“无”）",
                        "交通中转地（自驾返回，可填写“无”）", "出发时间", "抵津日期（具体到某日某时，填写格式24小时制）", "最后修改时间", "修改次数"};
                // map中的key
                String[] keys = {"id", "studentId", "idCard", "name", "phone", "address", "wayToTianjin",
                        "route", "carId", "wayToSchool", "seat", "station", "wayToStation",
                        "transit", "departureDate", "arriveTime", "updateTime", "times"};
                Date dNow = new Date( );
                SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddHHmmss");
                String fileName = "2021春学生返校信息" + ft.format(dNow) + ".xls";
                ExcelUtil.exportExcel(request, response, fileName, studentSpring2021s, columnNames, keys);
            } else {
                throw new BizException("管理员口令验证失败");
            }
        } catch (IOException e) {
            logger.error("导出全部学生信息到Excel失败!", e);
        } catch (BizException e) {
            logger.error("导出全部学生信息到Excel失败", e);
            return ApiResponse.error(e.getErrMessage());
        } catch (Exception e) {
            logger.error("导出全部学生信息到Excel失败", e);
        }
        logger.info("管理员导出全部学生信息到Excel成功" );
//        return ApiResponse.success();
        return null;
    }
}
