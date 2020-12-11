package com.yami.shop.bean.dto;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * WeGroupDTO
 * @author :YaJie
 */
@Data
public class WeGroupDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "社群Id")
    private Integer groupId;

    @ApiModelProperty(value = "社群名称")
    private String groupName;

    @ApiModelProperty(value = "社群简介")
    private String groupDesc;

    @ApiModelProperty(value = "审核标志，1:通过，2：未通过", example = "1")
    private String verifyFlag;


    @ApiModelProperty(value = "社群标签，多个用逗号分隔")
    private String groupMark;
    @ApiModelProperty(value = "社群状态，1：有效，0：无效")
    private String status;

    // 2020-11-03增加
    @ApiModelProperty(value = "加入社群是否需要学历认证")
    private Integer needAuth;

    @ApiModelProperty(value = "群管理员")
    private List<Integer> admins;

    /**
     * 2020-12-11 增加，可以关联多个学校
     */
    @ApiModelProperty(value = "学校Ids")
    List<Integer> schoolIds;
}
