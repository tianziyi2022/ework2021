package com.hebutgo.ework.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <p>
 * 
 * </p>
 *
 * @author tianziyi
 * @since 2021-02-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="StudentSpring2021对象", description="")
public class UpdateSpringRequest extends Model<UpdateSpringRequest> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String phone;

    private String address;

    private String wayToTianjin;

    private String route;

    private String carId;

    private String wayToSchool;

    private String station;

    private String wayToStation;

    private String seat;

    private String transit;

    private String departureDate;

    private String arriveTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
