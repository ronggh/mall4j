package com.yami.shop.bean.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WeGroupSchoolVO  implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "学校Id")
    private Integer schoolId;
    @ApiModelProperty(value = "学校中文名称")
    private String schoolCnname;
    @ApiModelProperty(value = "学校英文名称")
    private String schoolEnname;
}
