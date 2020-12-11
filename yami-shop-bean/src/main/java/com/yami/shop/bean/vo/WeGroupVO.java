package com.yami.shop.bean.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WeGroupVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "社群ID")
    private Integer groupId;

    @ApiModelProperty(value = "社群名称")
    private String groupName;

    @ApiModelProperty(value = "社群介绍")
    private String groupDesc;

    @ApiModelProperty(value = "申请理由")
    private String applyReason;

    @ApiModelProperty(value = "社群头像")
    private String groupHeadImg;

    @ApiModelProperty(value = "审核标志")
    private String verifyFlag;
    @ApiModelProperty(value = "新增成员提醒")
    private String memberTip;

    @ApiModelProperty(value = "发布消息提醒")
    private String noteTip;

    @ApiModelProperty(value = "社群标签")
    private String groupMark;

    // 2020-11-03增加
    @ApiModelProperty(value = "加入社群是否需要学历认证")
    private Integer needAuth;

    @ApiModelProperty(value = "创建群的用户id")
    private Integer createUid;
    @ApiModelProperty(value = "创建群的用户昵称")
    private String nickname;
    @ApiModelProperty(value = "创建群的用户真名")
    private String realName;



    /**
     * 社群成员列表
     */
    @ApiModelProperty(value = "社群成员列表")
    List<WeGroupUserVO> groupMembers;

    /**
     * 社群管理员列表
     */
    @ApiModelProperty(value = "社群管理员列表")
    List<WeGroupUserVO> groupAdmins;

    /**
     * 社群关联到的学校列表
     */
    @ApiModelProperty(value = "社群关联到的学校列表")
    List<WeGroupSchoolVO> groupSchools;

}
