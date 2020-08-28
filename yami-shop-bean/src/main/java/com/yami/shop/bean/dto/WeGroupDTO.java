package com.yami.shop.bean.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WeGroupDTO extends BaseDTO {
    @ApiModelProperty(value = "社群Id")
    private Integer groupId;
    @ApiModelProperty(value = "审核标志，1:通过，2：未通过", example = "1")
    private String verifyFlag;
    @ApiModelProperty(value = "学校Id")
    private Integer schoolId;
    @ApiModelProperty(value = "社群标签，多个用逗号分隔")
    private String groupMark;
    @ApiModelProperty(value = "社群状态，1：有效，0：无效")
    private String status;
}
