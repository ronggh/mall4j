package com.yami.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yami.shop.bean.model.WeNote;
import com.yami.shop.bean.vo.WeNoteImageVO;
import com.yami.shop.bean.vo.WeNoteVO;

public interface WeNoteMapper extends BaseMapper<WeNote> {

    /**
     * 获取笔记列表，分页显示
     * @param page
     * @param content
     * @return
     */
    @Select("select n.note_id,n.title,n.content,n.topic_id,n.location,n.create_uid,n.group_id, "
            + "  n.note_mark,n.praise_num,n.collet_num,n.comment_num,n.score,n.status,n.createtime,n.updatetime,"
            + "  u.nickname,u.real_name,u.head_img,d.degree_name,s.school_cnname,s.school_enname,g.group_name "
            + " from we_note n "
            + " left join we_user u  on  n.create_uid = u.uid "
            + " left join we_degree d on d.degree_id = u.degree_id "
            + " left join we_school s on u.school_id = s.school_id "
            + " left join we_group g on n.group_id = g.group_id "
            + " where n.status = '1' and n.is_formal = '1' and (n.title like #{content} or n.content like #{content} )")
    List<WeNoteVO> getNoteList(@Param("page") Page<WeNoteVO> page, @Param("content") String content);

    /**
     * 获取提定的笔记详情
     * @param noteId
     * @return
     */
    @Select("select n.note_id,n.title,n.content,n.topic_id,n.location,n.create_uid,n.group_id, "
            + " n.note_mark,n.praise_num,n.collet_num,n.comment_num,n.score,n.status,n.createtime,n.updatetime,"
            + " u.nickname,u.real_name,u.head_img,d.degree_name,s.school_cnname,s.school_enname,g.group_name "
            + " from we_note n "
            + " left join we_user u  on  n.create_uid = u.uid "
            + " left join we_degree d on d.degree_id = u.degree_id "
            + " left join we_school s on u.school_id = s.school_id "
            + " left join we_group g on n.group_id = g.group_id "
            + " where n.status = '1' and n.note_id =  #{noteId}")
    WeNoteVO getNoteDetail(@Param("noteId") Integer noteId);

    /**
     * 获取批定笔记的附属图片信息
     * @param noteId
     * @return
     */
    @Select("select note_id,img_url,img_order from we_note_image where status='1' and note_id=#{noteId} order by img_order")
    List<WeNoteImageVO> getNoteImageList(@Param("noteId") Integer noteId);
}
