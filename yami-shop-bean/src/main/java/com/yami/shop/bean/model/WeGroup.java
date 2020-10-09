package com.yami.shop.bean.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;

import lombok.Data;

@Data
@TableName("we_group")
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
    // 关联学校可以为null
    @TableField(strategy=FieldStrategy.IGNORED)
    private Integer schoolId;
    private String memberTip;
    private String noteTip;
    private String status;
    private String groupMark;
    private Date createtime;
    private Date updatetime;
}
