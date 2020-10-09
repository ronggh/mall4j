package com.yami.shop.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yami.shop.bean.dto.WeGroupDTO;
import com.yami.shop.bean.model.WeGroup;
import com.yami.shop.bean.vo.WeGroupSchoolVO;
import com.yami.shop.bean.vo.WeGroupVO;

/**
 * @author : YaJie
 */
public interface WeGroupService extends IService<WeGroup> {
    /**
     * 根据状态，获取未审核、审核通过、审核未通过、已关闭的社群列表,分页显示
     * 支持根据社群名称进行搜索
     * @param dto
     * @param verifyFlag
     * @return
     */
    Page<WeGroupVO> getWeGroupList(WeGroupDTO dto, String verifyFlag);

    /**
     * 获取所有的学校信息，支持按中英文学校名称搜索
     * @param schoolName
     * @return
     */
    List<WeGroupSchoolVO> getAllSchoolList(String schoolName);


    /**
     * 设置群审核状态
     * 
     * @param groupId
     * @param verifyFlag
     */
    void verifyWeGroup(Integer groupId, String verifyFlag);

    /**
     * 解禁社群
     * @param groupId
     */
    void openGroup(Integer groupId);

    /**
     * 关闭社群
     * @param groupId
     */
    void closeGroup(Integer groupId);

    /**
     * 设置群：包括群标签、关联学校、设置管理员
     * @param dto
     */
    void adminGroup(WeGroupDTO dto);


    /**
     * 根据groupId获取社群详细信息，包括成员和管理员列表
     * @param groupId
     * @return
     */
    WeGroupVO getGroupDetail(Integer groupId);

    /**
     * 暂时无用
     * 搜索所有的删除状态的社群，
     * @param dto
     * @return
     */
    Page<WeGroupVO> getDeletedWeGroupList(WeGroupDTO dto);

    /**
     * 设置社群状态，暂时没用
     *
     * @param groupId
     * @param groupStatus：“1”：有效群；“0”：无效群
     */
    void setGroupStatus(Integer groupId, String groupStatus);

}
