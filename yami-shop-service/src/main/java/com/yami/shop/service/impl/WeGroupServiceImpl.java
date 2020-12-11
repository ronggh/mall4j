package com.yami.shop.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aliyuncs.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yami.shop.bean.dto.WeGroupDTO;
import com.yami.shop.bean.enums.WeGroupVerifyFlag;
import com.yami.shop.bean.model.WeGroup;
import com.yami.shop.bean.model.WeGroupSchool;
import com.yami.shop.bean.vo.WeGroupSchoolVO;
import com.yami.shop.bean.vo.WeGroupUserVO;
import com.yami.shop.bean.vo.WeGroupVO;
import com.yami.shop.dao.WeGroupMapper;
import com.yami.shop.dao.WeGroupMemberMapper;
import com.yami.shop.dao.WeGroupSchoolMapper;
import com.yami.shop.service.WeGroupService;

/**
 * @author : YaJie
 */
@Service
public class WeGroupServiceImpl extends ServiceImpl<WeGroupMapper, WeGroup> implements WeGroupService {
    @Autowired
    private WeGroupMapper weGroupMapper;

    @Autowired
    private WeGroupMemberMapper weGroupMemberMapper;

    @Autowired
    private WeGroupSchoolMapper weGroupSchoolMapper;

    /**
     * 根据verifyFlag的值，分别获取未审核、审核通过、审核未通过、被关闭的社群列表， 分页显示，支持按社群名称搜索
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
        if (null != groupName && !"".equals(groupName)) {
            gName = "%" + groupName + "%";
        }
        //
        List<WeGroupVO> list = weGroupMapper.getWeGroupList(page, verifyFlag, gName);
        if (null != list && list.size() > 0) {
            for (WeGroupVO groupVO : list) {
                List<WeGroupSchoolVO> schools = weGroupMapper.getGroupSchoolList(groupVO.getGroupId());
                groupVO.setGroupSchools(schools);
            }
        }
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
        // 判断
        if (groupId == null || groupId <= 0) {
            return;
        }

        //
        WeGroup weGroup = new WeGroup();

        weGroup.setGroupId(groupId);
        weGroup.setVerifyFlag(verifyFlag);
        weGroup.setUpdatetime(new Date());
        //
        weGroupMapper.updateById(weGroup);
    }

    /**
     * 解禁社群
     * 
     * @param groupId
     */
    @Override
    public void openGroup(Integer groupId) {
        verifyWeGroup(groupId, WeGroupVerifyFlag.VERIFIED.value());
    }

    /**
     * 关闭社群
     * 
     * @param groupId
     */
    @Override
    public void closeGroup(Integer groupId) {
        verifyWeGroup(groupId, WeGroupVerifyFlag.CLOSED.value());
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
        List<WeGroupSchoolVO> schools = weGroupMapper.getGroupSchoolList(groupId);
        weGroupVO.setGroupAdmins(admins);
        weGroupVO.setGroupMembers(members);
        weGroupVO.setGroupSchools(schools);

        return weGroupVO;
    }

    /**
     * 2020-12-09增加：可修改社群名称和简介 <br/>
     * 7-设置群：包括群标签、关联学校、设置管理员
     *
     * @param dto
     */
    @Override
    public ResponseEntity<String> adminGroup(WeGroupDTO dto) {
        Integer groupId = dto.getGroupId();

        String groupMark = dto.getGroupMark();
        String groupName = dto.getGroupName();
        String groupDesc = dto.getGroupDesc();

        // 1.
        if (null == groupId || groupId == 0) {
            return ResponseEntity.ok("请求参数错误");
        }

        // 2.前端也需要控制不为空
        if (StringUtils.isEmpty(groupName)) {
            return ResponseEntity.ok("社群名称不能为空");
        }

        // 3. 判断名称是否重复, 排除自己
        QueryWrapper<WeGroup> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("group_name", groupName).eq("status", "1").ne("group_id", groupId);
        Integer result = weGroupMapper.selectCount(queryWrapper);
        if (result > 0) {
            return ResponseEntity.ok("该社群名称已存在");
        }

        // 4. 保存
        WeGroup weGroup = weGroupMapper.selectById(groupId);
        if (null != weGroup) {
            // 4.1 更新社群信息
            weGroup.setUpdatetime(new Date());

            if (null != groupMark) {
                weGroup.setGroupMark(groupMark);
            }

            weGroup.setGroupName(groupName);
            if (null != groupDesc) {
                weGroup.setGroupDesc(groupDesc);
            }
            weGroupMapper.updateById(weGroup);

            // 4.2 设置管理员
            List<Integer> admins = dto.getAdmins();
            if (null != admins && admins.size() > 0) {
                // 先清除原来的管理员
                weGroupMemberMapper.clearGroupAdmins(groupId);
                // 设置新的管理员
                weGroupMemberMapper.setGroupAdmins(groupId, admins);
            }

            // 4.3 关联学校
            List<Integer> schoolIds = dto.getSchoolIds();
            System.out.println("schoolIds ====" + schoolIds.toArray().toString());
            if (null != schoolIds) {
                // 先清除原来关联的学校
                weGroupSchoolMapper.clearGroupSchools(groupId);
                // 设置新的关联学校
                for (Integer schoolId : schoolIds) {
                    WeGroupSchool entity = new WeGroupSchool();
                    entity.setGroupId(groupId);
                    entity.setSchoolId(schoolId);
                    entity.setCreatetime(new Date());
                    entity.setUpdatetime(new Date());
                    weGroupSchoolMapper.insert(entity);
                }

            }

            return ResponseEntity.ok("success");
        }

        // 5.
        return ResponseEntity.ok("不存在该条数据");

    }

    /**
     * 获取所有学校列表，支持按学校中英文名称搜索
     * 
     * @param schoolName
     * @return
     */
    @Override
    public List<WeGroupSchoolVO> getAllSchoolList(String schoolName) {
        String sName = "%%";
        if (null != schoolName && !"".equals(schoolName)) {
            sName = "%" + schoolName + "%";
        }
        List<WeGroupSchoolVO> list = weGroupMapper.getAllSchoolList(sName);
        return list;
    }

    /**
     * 暂时没用 分页显示所有删除状态的社群，支持按社群名称搜索
     *
     * @param dto
     * @return
     */
    @Override
    public Page<WeGroupVO> getDeletedWeGroupList(WeGroupDTO dto) {
        Page<WeGroupVO> page = new Page<>();
        page.setCurrent(dto.getCurrentPage());
        page.setSize(dto.getPageSize());

        String groupName = "%%";
        if (null != dto.getGroupName()) {
            groupName = "%" + dto.getGroupName() + "%";
        }

        //
        List<WeGroupVO> list = weGroupMapper.getDeletedWeGroupList(page, groupName);
        page.setRecords(list);

        return page;
    }

    /**
     * 暂时没用 设置社群删除状态
     *
     * @param groupId
     * @param groupStatus：“1”：有效数据；“0”：无效数据
     */
    @Override
    public void setGroupStatus(Integer groupId, String groupStatus) {
        WeGroup weGroup = new WeGroup();
        weGroup.setGroupId(groupId);
        weGroup.setStatus(groupStatus);
        weGroup.setUpdatetime(new Date());
        weGroupMapper.updateById(weGroup);
    }
}
