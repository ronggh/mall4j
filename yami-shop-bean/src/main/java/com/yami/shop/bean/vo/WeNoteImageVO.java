package com.yami.shop.bean.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WeNoteImageVO   implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Id")
    private Integer id;
    @ApiModelProperty(value = "笔记Id")
    private Integer noteId;
    @ApiModelProperty(value = "图片url")
    private String imgUrl;
    @ApiModelProperty(value = "图片序号")
    private Integer imgOrder;

}
