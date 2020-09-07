package com.yami.shop.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yami.shop.bean.dto.WeGroupDTO;
import com.yami.shop.bean.model.WeGroup;
import com.yami.shop.bean.vo.WeGroupSchoolVO;
import com.yami.shop.bean.vo.WeGroupVO;

public interface WeGroupService extends IService<WeGroup> {
    /**
     * 根据状态，获取未审核，审核通过，审核未通过的社群列表,分页显示
     * 
     * @param dto
     * @param verifyFlag
     * @return
     */
    Page<WeGroupVO> getWeGroupList(WeGroupDTO dto, String verifyFlag);

    /**
     *
     * @param schoolName
     * @return
     */
    List<WeGroupSchoolVO> getAllSchoolList(String schoolName);

    /**
     * 搜索所有的被关闭的社群
     * @param dto
     * @return
     */
    Page<WeGroupVO> getClosedWeGroupList(WeGroupDTO dto);

    /**
     * 设置群审核状态
     * 
     * @param groupId
     * @param verifyFlag
     */
    void verifyWeGroup(Integer groupId, String verifyFlag);

    /**
     * 设置群：包括群标签、关联学校、设置管理员
     * @param dto
     */
    void adminGroup(WeGroupDTO dto);

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

    /**
     * 根据groupId获取社群详细信息，包括成员和管理员列表
     * @param groupId
     * @return
     */
    WeGroupVO getGroupDetail(Integer groupId);


}
