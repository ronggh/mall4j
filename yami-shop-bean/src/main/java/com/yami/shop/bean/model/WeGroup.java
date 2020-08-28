package com.yami.shop.bean.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("weback_group")
public class WeGroup implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "group_id", type = IdType.AUTO)
    private Integer groupId;
    private String groupName;
    private String groupDesc;
    private String applyReason;
    private String groupHeadImg;
    private String verifyFlag;
    private Integer createUid;
    private Integer schoolId;
    private String memberTip;
    private String noteTip;
    private String status;
    private String groupMark;
    private LocalDateTime createtime;
    private LocalDateTime updatetime;
}
