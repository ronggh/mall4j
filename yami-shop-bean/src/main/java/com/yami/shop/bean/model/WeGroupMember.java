package com.yami.shop.bean.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("we_group_member")
public class WeGroupMember implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer groupId;
    private Integer uid;
    private String userRole;
    private String isForbidden;

    private String status;

    private LocalDateTime createtime;

    private LocalDateTime updatetime;
}
