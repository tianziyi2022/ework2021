package com.hebutgo.ework.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author tianziyi
 */
@Data
@ApiModel(value = "查看学生信息请求参数")
public class GetInfoRequest {

    @ApiModelProperty(value = "学号")
    private String stuNumber;

    @ApiModelProperty(value = "手机号码")
    private String phone;

}
