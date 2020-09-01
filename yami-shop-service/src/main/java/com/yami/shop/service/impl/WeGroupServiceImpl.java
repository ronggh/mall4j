package com.yami.shop.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yami.shop.bean.dto.BaseDTO;
import com.yami.shop.bean.model.WeGroup;
import com.yami.shop.bean.vo.WeGroupVO;
import com.yami.shop.dao.WeGroupMapper;
import com.yami.shop.service.WeGroupService;

@Service
public class WeGroupServiceImpl extends ServiceImpl<WeGroupMapper, WeGroup> implements WeGroupService {
    @Autowired
    private WeGroupMapper weGroupMapper;

    /**
     * 根据verifyFlag的值，分别获取未审核、审核通过、审核未通过的社群列表，分页显示
     * 
     * @param dto
     * @param verifyFlag
     * @return
     */
    @Override
    public Page<WeGroupVO> getWeGroupList(BaseDTO dto, String verifyFlag) {
        Page<WeGroupVO> page = new Page<>();
        page.setCurrent(dto.getCurrentPage());
        page.setSize(dto.getPageSize());
        List<WeGroupVO> list = weGroupMapper.getWeGroupList(page, verifyFlag);
        page.setRecords(list);
        return page;
    }

    /**
     * 社群审核
     * 
     * @param groupId
     * @param verifyFlag
     */
    @Override
    public void verifyWeGroup(Integer groupId, String verifyFlag) {
        WeGroup weGroup = new WeGroup();
        weGroup.setGroupId(groupId);
        weGroup.setVerifyFlag(verifyFlag);
        weGroup.setUpdatetime(LocalDateTime.now());
        weGroupMapper.updateById(weGroup);
    }

    /***
     * 社群关联到学校
     * 
     * @param groupId
     * @param schoolId
     */
    @Override
    public void relateSchool(Integer groupId, Integer schoolId) {
        WeGroup weGroup = new WeGroup();
        weGroup.setGroupId(groupId);
        weGroup.setSchoolId(schoolId);
        weGroup.setUpdatetime(LocalDateTime.now());
        weGroupMapper.updateById(weGroup);
    }

    /**
     * 设置群标签，多个时用逗号分隔
     * 
     * @param groupId
     * @param groupMark
     */
    @Override
    public void setGroupMark(Integer groupId, String groupMark) {
        WeGroup weGroup = new WeGroup();
        weGroup.setGroupId(groupId);
        weGroup.setGroupMark(groupMark);
        weGroup.setUpdatetime(LocalDateTime.now());
        weGroupMapper.updateById(weGroup);
    }

    /**
     * 设置社群状态
     *
     * @param groupId
     * @param groupStatus：“1”：有效群；“0”：无效群
     */
    @Override
    public void setGroupStatus(Integer groupId, String groupStatus) {
        WeGroup weGroup = new WeGroup();
        weGroup.setGroupId(groupId);
        weGroup.setStatus(groupStatus);
        weGroup.setUpdatetime(LocalDateTime.now());
        weGroupMapper.updateById(weGroup);
    }
}
