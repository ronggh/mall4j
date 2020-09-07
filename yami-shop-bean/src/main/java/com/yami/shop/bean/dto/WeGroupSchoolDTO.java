package com.yami.shop.bean.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WeGroupSchoolDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "学校Id")
    private Integer schoolId;
    @ApiModelProperty(value = "学校名称")
    private String schoolName;
}
