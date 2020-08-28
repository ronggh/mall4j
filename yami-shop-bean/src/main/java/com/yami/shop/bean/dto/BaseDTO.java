package com.yami.shop.bean.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseDTO {
    @ApiModelProperty(value = "第几页数据", example = "1")
    private Integer currentPage;

    @ApiModelProperty(value = "一页有几条数据", example = "10")
    private Integer pageSize;
}
