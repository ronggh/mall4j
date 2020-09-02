package com.yami.shop.bean.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WeNoteIdDTO {
    @ApiModelProperty(value = "笔记Id")
    private Integer noteId;
}
