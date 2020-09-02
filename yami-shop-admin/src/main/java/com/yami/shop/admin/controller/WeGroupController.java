package com.yami.shop.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.yami.shop.bean.dto.BaseDTO;
import com.yami.shop.bean.dto.WeGroupAdminDTO;
import com.yami.shop.bean.dto.WeGroupDTO;
import com.yami.shop.bean.enums.WeGroupVerifyFlag;
import com.yami.shop.bean.vo.WeGroupVO;
import com.yami.shop.service.WeGroupMemberService;
import com.yami.shop.service.WeGroupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"社群管理"})
@EnableKnife4j
@RestController
@RequestMapping("/group")
public class WeGroupController {
    @Autowired
    private WeGroupService weGroupService;
    @Autowired
    private WeGroupMemberService weGroupMemberService;

    @ApiOperation(position = 1, value = "1-获取未审核的社群列表", notes = "获取未审核的社群列表")
    @RequestMapping(value = "/list/nonVerifyList", method = RequestMethod.POST)
    @PreAuthorize("@pms.hasPermission('group:list:nonVerifyList')")
    public ResponseEntity<Page<WeGroupVO>> nonVerifyList(@RequestBody BaseDTO dto) {
        Page<WeGroupVO> list = weGroupService.getWeGroupList(dto, WeGroupVerifyFlag.NONVERIFY.value());
        return ResponseEntity.ok(list);
    }

    @ApiOperation(position = 2, value = "2-获取审核通过的社群列表", notes = "获取审核通过的社群列表")
    @RequestMapping(value = "/list/verifiedList", method = RequestMethod.POST)
    @PreAuthorize("@pms.hasPermission('group:list:verifiedList')")
    public ResponseEntity<Page<WeGroupVO>> verifiedList(@RequestBody BaseDTO dto) {
        Page<WeGroupVO> list = weGroupService.getWeGroupList(dto, WeGroupVerifyFlag.VERIFIED.value());
        return ResponseEntity.ok(list);
    }

    @ApiOperation(position = 3, value = "3-获取审核未通过的社群列表", notes = "获取审核未通过的社群列表")
    @RequestMapping(value = "/list/unverifiedList", method = RequestMethod.POST)
    @PreAuthorize("@pms.hasPermission('group:list:unverifiedList')")
    public ResponseEntity<Page<WeGroupVO>> unverifiedList(@RequestBody BaseDTO dto) {
        Page<WeGroupVO> list = weGroupService.getWeGroupList(dto, WeGroupVerifyFlag.UNVERIFIED.value());
        return ResponseEntity.ok(list);
    }

    @ApiOperation(position = 4, value = "4-审核社群", notes = "1：设置为通过审核，2：审核未通过")
    @ApiOperationSupport(includeParameters = {"dto.groupId", "dto.verifyFlag"})
    @RequestMapping(value = "/info/verify", method = RequestMethod.POST)
    @PreAuthorize("@pms.hasPermission('group:info:verify')")
    public ResponseEntity<Void> verify(@RequestBody WeGroupDTO dto) {
        Integer groupId = dto.getGroupId();
        String verifyFlag = dto.getVerifyFlag();
        weGroupService.verifyWeGroup(groupId, verifyFlag);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(position = 5, value = "5-关联学校", notes = "社群关联到学校")
    @ApiOperationSupport(includeParameters = {"dto.groupId", "dto.schoolId"})
    @RequestMapping(value = "/info/relateSchool", method = RequestMethod.POST)
    @PreAuthorize("@pms.hasPermission('group:info:relateSchool')")
    public ResponseEntity<Void> relateSchool(@RequestBody WeGroupDTO dto) {
        Integer groupId = dto.getGroupId();
        Integer schoolId = dto.getSchoolId();
        weGroupService.relateSchool(groupId, schoolId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(position = 6, value = "6-设置群标签", notes = "多个时用逗号分隔")
    @ApiOperationSupport(includeParameters = {"dto.groupId", "dto.groupMark"})
    @RequestMapping(value = "/info/setGroupMark", method = RequestMethod.POST)
    @PreAuthorize("@pms.hasPermission('group:info:setGroupMark')")
    public ResponseEntity<Void> setGroupMark(@RequestBody WeGroupDTO dto) {
        Integer groupId = dto.getGroupId();
        String groupMark = dto.getGroupMark();
        weGroupService.setGroupMark(groupId, groupMark);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(position = 7, value = "7-关闭群", notes = "关闭群")
    @ApiOperationSupport(includeParameters = {"dto.groupId"})
    @RequestMapping(value = "/info/closeGroup", method = RequestMethod.POST)
    @PreAuthorize("@pms.hasPermission('group:info:closeGroup')")
    public ResponseEntity<Void> closeGroup(@RequestBody WeGroupDTO dto) {
        Integer groupId = dto.getGroupId();
        weGroupService.setGroupStatus(groupId, "0");
        return ResponseEntity.ok().build();
    }

    @ApiOperation(position = 8, value = "8-解禁群", notes = "解禁群")
    @ApiOperationSupport(includeParameters = {"dto.groupId"})
    @RequestMapping(value = "/info/openGroup", method = RequestMethod.POST)
    @PreAuthorize("@pms.hasPermission('group:info:openGroup')")
    public ResponseEntity<Void> openGroup(@RequestBody WeGroupDTO dto) {
        Integer groupId = dto.getGroupId();
        weGroupService.setGroupStatus(groupId, "1");
        return ResponseEntity.ok().build();
    }

    @ApiOperation(position = 9, value = "9-设置群管理员", notes = "设置群管理员")
    @RequestMapping(value = "/info/setGroupAdmins", method = RequestMethod.POST)
    @PreAuthorize("@pms.hasPermission('group:info:setGroupAdmins')")
    public ResponseEntity<Void> setGroupAdmins(@RequestBody WeGroupAdminDTO dto) {
        Integer groupId = dto.getGroupId();
        List<Integer> uidList = dto.getUidList();
        weGroupMemberService.setGroupAdmins(groupId, uidList);
        return ResponseEntity.ok().build();
    }

}
