package com.yami.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yami.shop.bean.model.WeGroupMember;

public interface WeGroupMemberMapper extends BaseMapper<WeGroupMember> {
    /**
     * @param groupId
     */
    @Update("update we_group_member set user_role='2' where status='1' and group_id=#{groupId}")
    void clearGroupAdmins(@Param("groupId") Integer groupId);

    /**
     * 设置群管理员
     *  MyBatis不支持直接在SQL语句里写IN
     * @param groupId
     * @param admins
     */
    @Update("<script>" + " update we_group_member set user_role='1' where status='1' "
        + " and group_id =#{groupId} and uid in "
        + "<foreach item='item' index='index' collection='admins' open='(' separator=',' close=')'>"
            + "#{item}"
        + "</foreach>"
        + "</script>")
    void setGroupAdmins(@Param("groupId") Integer groupId, @Param("admins") List<Integer> admins);
}
