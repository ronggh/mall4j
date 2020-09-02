package com.yami.shop.bean.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WeNoteDetailVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "笔记信息")
    private WeNoteVO note;
    @ApiModelProperty(value = "图片列表")
    private List<WeNoteImageVO> noteImages;

}
