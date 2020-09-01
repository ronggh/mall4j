package com.yami.shop.bean.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WeGroupAdminDTO {
    @ApiModelProperty(value = "社群Id")
    private Integer groupId;
    @ApiModelProperty(value = "社群管理员uid，多个用逗号分隔")
    private List<Integer> uidList;
}
