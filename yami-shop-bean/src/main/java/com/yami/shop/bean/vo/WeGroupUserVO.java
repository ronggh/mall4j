package com.yami.shop.bean.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WeGroupUserVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    private Integer uid;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String headimg;

    @ApiModelProperty(value = "实名认证标志")
    private String verifyflag;

    @ApiModelProperty(value = "学校Id")
    private Integer schoolId;
    @ApiModelProperty(value = "学校名称")
    private String schoolCnname;

    @ApiModelProperty(value = "学历Id")
    private  Integer degreeId;
    @ApiModelProperty(value = "学历名称")
    private String degreeName;
}
