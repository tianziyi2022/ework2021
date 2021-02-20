package com.hebutgo.ework.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author tianziyi
 */
@Data
@ApiModel(value = "核验学生信息请求参数")
public class CheckRequest {

    @ApiModelProperty(value = "序号")
    private Integer id;

}
