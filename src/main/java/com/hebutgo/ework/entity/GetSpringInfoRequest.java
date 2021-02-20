package com.hebutgo.ework.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author tianziyi
 */
@Data
@ApiModel(value = "查看学生信息请求参数")
public class GetSpringInfoRequest {

    @ApiModelProperty(value = "学号")
    private String studentId;

    @ApiModelProperty(value = "身份证号")
    private String idCard;

}
