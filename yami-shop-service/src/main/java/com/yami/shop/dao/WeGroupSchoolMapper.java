package com.yami.shop.dao;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yami.shop.bean.model.WeGroupSchool;

public interface WeGroupSchoolMapper extends BaseMapper<WeGroupSchool> {
    @Delete("delete from we_group_school where group_id = #{groupId}")
    void clearGroupSchools(@Param("groupId") Integer groupId);

}
