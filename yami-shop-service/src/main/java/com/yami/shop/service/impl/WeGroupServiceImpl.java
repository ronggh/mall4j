package com.yami.shop.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yami.shop.bean.dto.WeGroupDTO;
import com.yami.shop.bean.model.WeGroup;
import com.yami.shop.bean.vo.WeGroupSchoolVO;
import com.yami.shop.bean.vo.WeGroupUserVO;
import com.yami.shop.bean.vo.WeGroupVO;
import com.yami.shop.dao.WeGroupMapper;
import com.yami.shop.dao.WeGroupMemberMapper;
import com.yami.shop.service.WeGroupService;

@Service
public class WeGroupServiceImpl extends ServiceImpl<WeGroupMapper, WeGroup> implements WeGroupService {
    @Autowired
    private WeGroupMapper weGroupMapper;

    @Autowired
    private WeGroupMemberMapper weGroupMemberMapper;

    /**
     * 根据verifyFlag的值，分别获取未审核、审核通过、审核未通过的社群列表，分页显示
     * 
     * @param dto
     * @param verifyFlag
     * @return
     */
    @Override
    public Page<WeGroupVO> getWeGroupList(WeGroupDTO dto, String verifyFlag) {
        //
        Page<WeGroupVO> page = new Page<>();
        page.setCurrent(dto.getCurrentPage());
        page.setSize(dto.getPageSize());

        String groupName = dto.getGroupName();
        String gName = "%%";
        if(null != groupName && !"".equals(groupName)){
            gName = "%"+groupName +"%";
        }
        //
        List<WeGroupVO> list = weGroupMapper.getWeGroupList(page, verifyFlag,gName);
        page.setRecords(list);

        return page;
    }

    /**
     * 搜索所有的被关闭的社群
     * 
     * @param dto
     * @return
     */
    @Override
    public Page<WeGroupVO> getClosedWeGroupList(WeGroupDTO dto) {
        Page<WeGroupVO> page = new Page<>();
        page.setCurrent(dto.getCurrentPage());
        page.setSize(dto.getPageSize());

        String groupName = "%%";
        if (null != dto.getGroupName()) {
            groupName = "%" + dto.getGroupName() + "%";
        }

        //
        List<WeGroupVO> list = weGroupMapper.getClosedWeGroupList(page, groupName);
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
        //
        WeGroup weGroup = new WeGroup();
        weGroup.setGroupId(groupId);
        weGroup.setVerifyFlag(verifyFlag);
        weGroup.setUpdatetime(LocalDateTime.now());
        //
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

    /**
     * 根据groupId获取社群详细信息，包括成员和管理员列表
     * 
     * @param groupId
     * @return
     */
    @Override
    public WeGroupVO getGroupDetail(Integer groupId) {
        WeGroupVO weGroupVO = weGroupMapper.getGroupVO(groupId);
        List<WeGroupUserVO> admins = weGroupMapper.getGroupMemberList(groupId, "1");
        List<WeGroupUserVO> members = weGroupMapper.getGroupMemberList(groupId, "2");
        weGroupVO.setGroupAdmins(admins);
        weGroupVO.setGroupMembers(members);

        return weGroupVO;
    }

    /**
     * 设置群：包括群标签、关联学校、设置管理员
     * 
     * @param dto
     */
    @Override
    public void adminGroup(WeGroupDTO dto) {
        Integer groupId = dto.getGroupId();
        Integer schoolId = dto.getSchoolId();
        String groupMark = dto.getGroupMark();
        if(null == groupId || groupId == 0){
            return;
        }
        WeGroup weGroup = new WeGroup();
        weGroup.setGroupId(groupId);
        weGroup.setUpdatetime(LocalDateTime.now());
        if (null != schoolId) {
            weGroup.setSchoolId(schoolId);
        }
        if (null != groupMark) {
            weGroup.setGroupMark(groupMark);
        }
        weGroupMapper.updateById(weGroup);

        // 设置管理员
        List<Integer> admins = dto.getAdmins();
        if (null != admins && admins.size() > 0) {
            // 先清除原来的管理员
            weGroupMemberMapper.clearGroupAdmins(groupId);
            // 设置新的管理员
            weGroupMemberMapper.setGroupAdmins(groupId, admins);
        }

    }

    @Override
    public List<WeGroupSchoolVO> getAllSchoolList(String schoolName){
        String sName = "%%";
        if( null != schoolName && !"".equals(schoolName)){
            sName ="%" + schoolName +"%";
        }
        List<WeGroupSchoolVO> list = weGroupMapper.getAllSchoolList(sName);
        return list;
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
}
