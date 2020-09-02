package com.yami.shop.bean.vo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WeNoteVO  implements Serializable {
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

    @ApiModelProperty(value = "用户ID")
    private Integer createUid;
    @ApiModelProperty(value = "用户昵称")
    private String nickname;
    @ApiModelProperty(value = "用户真名")
    private String realName;
    @ApiModelProperty(value = "用户头像")
    private String headImg;
    @ApiModelProperty(value = "用户学历")
    private String degreeName;
    @ApiModelProperty(value = "学校英文名")
    private String schoolEnname;
    @ApiModelProperty(value = "学校中文名")
    private String schoolCnname;


    @ApiModelProperty(value = "社群ID，可为空")
    private Integer groupId;
    @ApiModelProperty(value = "社群名称")
    private String groupName;


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
    private String status;

    @ApiModelProperty(value = "创建时间")
    private Date createtime;

    @ApiModelProperty(value = "更新时间")
    private Date updatetime;
}
