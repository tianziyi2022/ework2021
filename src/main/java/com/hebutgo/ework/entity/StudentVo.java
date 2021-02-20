package com.hebutgo.ework.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tianziyi
 * @since 2020-10-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Student返回对象", description="")
public class StudentVo extends Model<StudentVo> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "序号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "班级")
    private String stuClass;

    @ApiModelProperty(value = "学号")
    private String stuNumber;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "专业")
    private String major;

    @ApiModelProperty(value = "qq号")
    private String qqNumber;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "微信号")
    private String wechatNumber;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "宿舍号")
    private String apart;

    @ApiModelProperty(value = "出生年月")
    private String birth;

    @ApiModelProperty(value = "民族")
    private String nation;

    @ApiModelProperty(value = "宗教信仰（“无”、“基督教”、“伊斯兰教”、“佛教”、“其他”）")
    private String religion;

    @ApiModelProperty(value = "政治面貌（“群众”、“共青团员”、“中共预备党员”、“中共党员”；若为小党派人士则填写相应的政治面貌即可）")
    private String politics;

    @ApiModelProperty(value = "家庭住址")
    private String address;

    @ApiModelProperty(value = "家庭固定电话")
    private String familyPhone;

    @ApiModelProperty(value = "父亲姓名")
    private String fatherName;

    @ApiModelProperty(value = "父亲工作单位")
    private String fatherCompany;

    @ApiModelProperty(value = "父亲联系电话")
    private String fatherPhone;

    @ApiModelProperty(value = "母亲姓名")
    private String motherName;

    @ApiModelProperty(value = "母亲工作单位")
    private String motherCompany;

    @ApiModelProperty(value = "母亲联系电话")
    private String motherPhone;

    @ApiModelProperty(value = "曾担任职务")
    private String work;

    @ApiModelProperty(value = "获奖情况（何时、何地、何种奖项）")
    private String reward;

    @ApiModelProperty(value = "特长")
    private String speciality;

    @ApiModelProperty(value = "兴趣爱好")
    private String habit;

    @ApiModelProperty(value = "获资助情况（何时、何地、何资助）")
    private String aid;

    @ApiModelProperty(value = "邮编")
    private String postCode;

    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "状态（0未查验，1正确，2错误）")
    private String status;

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
