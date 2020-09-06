package com.yami.shop.bean.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "第几页数据")
    private Integer currentPage;

    @ApiModelProperty(value = "一页有几条数据")
    private Integer pageSize;
}
