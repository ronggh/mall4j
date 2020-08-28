package com.yami.shop.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yami.shop.bean.dto.BaseDTO;
import com.yami.shop.bean.model.WeGroup;

public interface WeGroupService extends IService<WeGroup> {
    /**
     * 根据状态，获取未审核，审核通过，审核未通过的社群列表,分页显示
     * 
     * @param dto
     * @param verifyFlag
     * @return
     */
    Page<WeGroup> getWeGroupList(BaseDTO dto, String verifyFlag);

    /**
     * 设置群审核状态
     * 
     * @param groupId
     * @param verifyFlag
     */
    void verifyWeGroup(Integer groupId, String verifyFlag);

    /**
     * 社群关联到学校
     * 
     * @param groupId
     * @param schoolId
     */
    void relateSchool(Integer groupId, Integer schoolId);

    /**
     * 设置群标签，多个时用逗号分隔
     * 
     * @param groupId
     * @param groupMark
     */
    void setGroupMark(Integer groupId, String groupMark);

    /**
     * 设置社群状态
     * 
     * @param groupId
     * @param groupStatus：“1”：有效群；“0”：无效群
     */
    void setGroupStatus(Integer groupId, String groupStatus);
}
