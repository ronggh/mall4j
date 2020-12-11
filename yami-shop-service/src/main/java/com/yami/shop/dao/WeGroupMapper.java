package com.yami.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yami.shop.bean.model.WeGroup;
import com.yami.shop.bean.vo.WeGroupSchoolVO;
import com.yami.shop.bean.vo.WeGroupUserVO;
import com.yami.shop.bean.vo.WeGroupVO;

public interface WeGroupMapper extends BaseMapper<WeGroup> {
    /**
     * 根据状态，获取未审核，审核通过，审核未通过、被关闭的社群列表,分页显示
     * 
     * @param page
     * @param verifyFlag
     * @param groupName
     * @return
     */
    @Select("select gu.group_id,gu.group_name,gu.group_desc,gu.apply_reason,gu.group_head_img,gu.verify_flag,gu.group_mark,gu.member_tip,gu.note_tip,gu.create_uid,gu.nickname,gu.real_name,gu.createtime,gu.need_auth "
            + " from ( select g.group_id,g.group_name,g.group_desc,g.apply_reason,g.group_head_img,g.verify_flag,g.group_mark,g.member_tip,g.note_tip,g.create_uid,u.nickname,u.real_name,g.createtime,g.need_auth "
            + " from we_group g,we_user u "
            + " where g.create_uid = u.uid and g.verify_flag=#{verifyFlag} and g.status = '1' and g.group_name like #{groupName})  as gu  "
            + "  order by gu.createtime desc")
    List<WeGroupVO> getWeGroupList(@Param("page") Page<WeGroupVO> page, @Param("verifyFlag") String verifyFlag,@Param("groupName") String groupName);


    /**
     * 根据groupId获取社群信息
     * @param groupId
     * @return
     */
    @Select("select gu.group_id,gu.group_name,gu.group_desc,gu.apply_reason,gu.group_head_img,gu.verify_flag,gu.group_mark,gu.member_tip,gu.note_tip,gu.create_uid,gu.nickname,gu.real_name,gu.need_auth "
            + " from ( select g.group_id,g.group_name,g.group_desc,g.apply_reason,g.group_head_img,g.verify_flag,g.group_mark,g.member_tip,g.note_tip,g.create_uid,u.nickname,u.real_name,g.need_auth "
            + " from we_group g,we_user u "
            + " where g.group_id = #{groupId} and g.create_uid = u.uid and g.verify_flag='1' and g.status = '1' ) as gu ")
    WeGroupVO getGroupVO(@Param("groupId") Integer groupId);

    /**
     * 根据groupId获取社群详成员信息，包括管理员，成员列表
     * @param  userRole, 1-管理员，2-普通成员
     * @param groupId
     * @return
     */
    @Select("select m.uid,m.user_role,u.nickname,u.head_img,u.school_id,u.degree_id, s.school_cnname, d.degree_name "
            +" from we_group_member m  "
            +" left join we_user u on m.uid = u.uid "
            +" left join we_school s on u.school_id = s.school_id "
            +" left join we_degree d on u.degree_id = d.degree_id "
            +" where m.group_id = #{groupId} and m.status =  '1' and m.user_role = #{userRole}" )
    List<WeGroupUserVO> getGroupMemberList(@Param("groupId") Integer groupId,@Param("userRole") String userRole);

    @Select("select s.school_id,s.school_cnname,s.school_enname from we_school s "
            + " inner join we_group_school gs on gs.school_id = s.school_id "
            +" where gs.group_id = #{groupId}")
    List<WeGroupSchoolVO> getGroupSchoolList(@Param("groupId") Integer groupId);
    /**
     * 获取所有学校信息，支持按校名中英文搜索
     * @param schoolName
     * @return
     */
    @Select("select school_id, school_cnname ,school_enname "
            +" from we_school "
            +" where status ='1' and school_cnname is not null "
            + " and (school_cnname like #{schoolName} or school_enname like #{schoolName})"
            + " order by convert(school_cnname using gbk) ")
    List<WeGroupSchoolVO> getAllSchoolList(@Param("schoolName") String schoolName);

    /**
     * 暂时没用
     * 根据名称，搜索删除状态的社群列表，分页显示，支持社群名称搜索
     * @param page
     * @param groupName
     * @return
     */
    @Select("select gu.group_id,gu.group_name,gu.group_desc,gu.apply_reason,gu.group_head_img,gu.verify_flag,gu.group_mark,gu.member_tip,gu.note_tip,gu.create_uid,gu.nickname,gu.real_name "
            + " from ( select g.group_id,g.group_name,g.group_desc,g.apply_reason,g.group_head_img,g.verify_flag,g.group_mark,g.member_tip,g.note_tip,g.create_uid,u.nickname,u.real_name "
            + " from we_group g,we_user u "
            + " where g.create_uid = u.uid and  g.status = '0' and g.group_name like #{groupName}) as gu ")
    List<WeGroupVO> getDeletedWeGroupList(@Param("page") Page<WeGroupVO> page, @Param("groupName") String groupName);
}
