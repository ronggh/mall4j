package com.yami.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yami.shop.bean.model.WeGroup;

public interface WeGroupMapper extends BaseMapper<WeGroup> {
    /**
     * 根据状态，获取未审核，审核通过，审核未通过的社群列表,分页显示
     * 
     * @param page
     * @param verifyFlag
     * @return
     */
    @Select("select * from weback_group where status = '1' and verify_flag = #{verifyFlag}")
    List<WeGroup> getWeGroupList(Page<WeGroup> page, @Param("verifyFlag") String verifyFlag);
}
