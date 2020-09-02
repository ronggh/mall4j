package com.yami.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yami.shop.bean.model.WeGroup;
import com.yami.shop.bean.vo.WeGroupVO;

public interface WeGroupMapper extends BaseMapper<WeGroup> {
    /**
     * 根据状态，获取未审核，审核通过，审核未通过的社群列表,分页显示
     * 
     * @param page
     * @param verifyFlag
     * @return
     */
    @Select("select gu.group_id,gu.group_name,gu.group_desc,gu.apply_reason,gu.group_head_img,gu.verify_flag,gu.group_mark,gu.member_tip,gu.note_tip,gu.create_uid,gu.nickname,gu.real_name,gu.school_id,s.school_enname,s.school_cnname "
            + " from ( select g.group_id,g.group_name,g.group_desc,g.apply_reason,g.group_head_img,g.verify_flag,g.group_mark,g.member_tip,g.note_tip,g.create_uid,u.nickname,u.real_name,g.school_id "
            + " from weback_group g,weback_user u "
            + " where g.create_uid = u.uid and g.verify_flag=#{verifyFlag}) as gu "
            + " LEFT OUTER JOIN weback_school s "
            + " on gu.school_id = s.school_id ")
    List<WeGroupVO> getWeGroupList(@Param("page") Page<WeGroupVO> page, @Param("verifyFlag") String verifyFlag);
}
