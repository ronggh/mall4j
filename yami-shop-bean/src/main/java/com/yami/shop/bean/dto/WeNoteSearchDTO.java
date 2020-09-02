package com.yami.shop.bean.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WeNoteSearchDTO extends BaseDTO {

    @ApiModelProperty(value = "按笔记标题或内容搜索字符串")
    private String content;
}
