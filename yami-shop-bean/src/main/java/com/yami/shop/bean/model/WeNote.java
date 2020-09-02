package com.yami.shop.bean.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("we_note")
public class WeNote implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "note_id", type = IdType.AUTO)
    @ApiModelProperty(value = "笔记Id")
    private Integer noteId;

    @ApiModelProperty(value = "笔记标题")
    private String title;

    @ApiModelProperty(value = "笔记内容")
    private String content;

    @ApiModelProperty(value = "话题ID")
    private Integer topicId;

    @ApiModelProperty(value = "位置")
    private String location;

    @ApiModelProperty(value = "经度")
    private Double longitude;

    @ApiModelProperty(value = "纬度")
    private Double latitude;

    @ApiModelProperty(value = "geohash编码（用于计算距离）")
    private String geohashCode;

    @ApiModelProperty(value = "草稿标识：0-草稿 1-已正式发布")
    private String isFormal;

    @ApiModelProperty(value = "隐藏标识：1-隐藏，0-不隐藏")
    private String isHide;

    @ApiModelProperty(value = "用户ID")
    private Integer createUid;

    @ApiModelProperty(value = "否同步个人首页：1-同步（默认），0-不同步")
    private String isSync;

    @ApiModelProperty(value = "社群ID，可为空")
    private Integer groupId;

    @ApiModelProperty(value = "笔记标签")
    private String noteMark;

    @ApiModelProperty(value = "点赞数")
    private Integer praiseNum;

    @ApiModelProperty(value = "收藏数")
    private Integer colletNum;

    @ApiModelProperty(value = "评论数")
    private Integer commentNum;

    @ApiModelProperty(value = "推荐优先级积分=点赞数+收藏数+评论数X2")
    private Integer score;

    @ApiModelProperty(value = "状态  1-有效 0-无效")
    @TableLogic(value = "1", delval = "0")
    private String status;

    @ApiModelProperty(value = "创建时间")
    private Date createtime;

    @ApiModelProperty(value = "更新时间")
    private Date updatetime;
}
