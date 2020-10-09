package com.yami.shop.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.yami.shop.bean.dto.WeGroupDTO;
import com.yami.shop.bean.dto.WeGroupSchoolDTO;
import com.yami.shop.bean.enums.WeGroupVerifyFlag;
import com.yami.shop.bean.vo.WeGroupSchoolVO;
import com.yami.shop.bean.vo.WeGroupVO;
import com.yami.shop.service.WeGroupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/***
 * @author YaJie
 */
@Api(tags = {"社群管理"})
@RestController
@RequestMapping("/group")
public class WeGroupController {
    @Autowired
    private WeGroupService weGroupService;


    /**
     * 获取所有未审核的社群列表，分页显示，支持根据社群名称搜索
     * @param dto
     * @return
     */
    @ApiOperation(position = 1, value = "1-未审核社群列表", notes = "获取未审核的社群列表，分页显示，支持根据社群名称搜索")
    @RequestMapping(value = "/list/nonVerifyList", method = RequestMethod.POST)
//    @PreAuthorize("@pms.hasPermission('group:list:nonverify')")
    @ApiOperationSupport(includeParameters = {"dto.groupName","dto.currentPage","dto.pageSize"})
    public ResponseEntity<Page<WeGroupVO>> nonVerifyList(@RequestBody WeGroupDTO dto) {
        Page<WeGroupVO> list = weGroupService.getWeGroupList(dto, WeGroupVerifyFlag.NONVERIFY.value());
        return ResponseEntity.ok(list);
    }

    /**
     * 获取审核通过的社群列表，分页显示，支持根据社群名称搜索
     * @param dto
     * @return
     */
    @ApiOperation(position = 2, value = "2-已通过社群列表", notes = "获取审核通过的社群列表，分页显示，支持根据社群名称搜索")
    @RequestMapping(value = "/list/verifiedList", method = RequestMethod.POST)
//    @PreAuthorize("@pms.hasPermission('group:list:verified')")
    @ApiOperationSupport(includeParameters = {"dto.groupName","dto.currentPage","dto.pageSize"})
    public ResponseEntity<Page<WeGroupVO>> verifiedList(@RequestBody WeGroupDTO dto) {
        Page<WeGroupVO> list = weGroupService.getWeGroupList(dto, WeGroupVerifyFlag.VERIFIED.value());
        return ResponseEntity.ok(list);
    }

    /**
     * 获取审核未通过的社群列表，分页显示，支持根据社群名称搜索
     * @param dto
     * @return
     */
    @ApiOperation(position = 3, value = "3-未通过社群列表", notes = "获取审核未通过的社群列表分页显示，支持根据社群名称搜索")
    @RequestMapping(value = "/list/unverifiedList", method = RequestMethod.POST)
//    @PreAuthorize("@pms.hasPermission('group:list:unverified')")
    @ApiOperationSupport(includeParameters = {"dto.groupName","dto.currentPage","dto.pageSize"})
    public ResponseEntity<Page<WeGroupVO>> unverifiedList(@RequestBody WeGroupDTO dto) {
        Page<WeGroupVO> list = weGroupService.getWeGroupList(dto, WeGroupVerifyFlag.UNVERIFIED.value());
        return ResponseEntity.ok(list);
    }

    /**
     * 获取所有被关闭的社群列表，分页显示，支持根据社群名称搜索
     * @param dto
     * @return
     */
    @ApiOperation(position = 4, value = "4-已关闭社群列表", notes = "获取所有被关闭的社群列表，分页显示，支持根据社群名称搜索")
    @RequestMapping(value = "/list/closedList", method = RequestMethod.POST)
//    @PreAuthorize("@pms.hasPermission('group:list:closed')")
    @ApiOperationSupport(includeParameters = {"dto.groupName","dto.currentPage","dto.pageSize"})
    public ResponseEntity<Page<WeGroupVO>> closedList(@RequestBody WeGroupDTO dto) {
        Page<WeGroupVO> list = weGroupService.getWeGroupList(dto, WeGroupVerifyFlag.CLOSED.value());
        return ResponseEntity.ok(list);
    }

    /**
     * 审核社群
     * @param dto
     * @return
     */
    @ApiOperation(position = 5, value = "5-审核社群", notes = "审核社群,verifyFlag = 1,设置为通过审核; verifyFlag = 2 ,审核未通过")
    @ApiOperationSupport(includeParameters = {"dto.groupId", "dto.verifyFlag"})
    @RequestMapping(value = "/info/verify", method = RequestMethod.POST)
//    @PreAuthorize("@pms.hasPermission('group:info:verify')")
    public ResponseEntity<String> verify(@RequestBody WeGroupDTO dto) {
        Integer groupId = dto.getGroupId();
        String verifyFlag = dto.getVerifyFlag();
        weGroupService.verifyWeGroup(groupId, verifyFlag);
        return ResponseEntity.ok("success");
    }

    /**
     * 获取指定社群详细信息，包括管理员和成员列表
     * @param dto
     * @return
     */
    @ApiOperation(position = 6, value = "6-社群详情", notes = "获取指定社群详细信息，包括管理员和成员列表")
    @RequestMapping(value = "/info/getGroupDetail", method = RequestMethod.POST)
//    @PreAuthorize("@pms.hasPermission('group:info:adminGroup')")
    @ApiOperationSupport(includeParameters = {"dto.groupId"})
    public ResponseEntity<WeGroupVO> getGroupDetail(@RequestBody WeGroupDTO dto) {
        WeGroupVO weGroupVO = weGroupService.getGroupDetail(dto.getGroupId());
        return ResponseEntity.ok(weGroupVO);
    }

    /**
     * 设置群:包括标签、关联学校、设置管理员
     * @param dto
     * @return
     */
    @ApiOperation(position = 7, value = "7-设置社群", notes = "包括标签、关联学校、设置管理员")
    @ApiOperationSupport(includeParameters = {"dto.groupId", "dto.groupMark","dto.schoolId","dto.admins"})
    @RequestMapping(value = "/info/adminGroup", method = RequestMethod.POST)
//    @PreAuthorize("@pms.hasPermission('group:info:adminGroup')")
    public ResponseEntity<String> adminGroup(@RequestBody WeGroupDTO dto) {
        weGroupService.adminGroup(dto);
        return ResponseEntity.ok("success");
    }


    /**
     * 关闭群
     * @param dto
     * @return
     */
    @ApiOperation(position = 8, value = "8-关闭社群", notes = "关闭社群")
    @ApiOperationSupport(includeParameters = {"dto.groupId"})
    @RequestMapping(value = "/info/closeGroup", method = RequestMethod.POST)
//    @PreAuthorize("@pms.hasPermission('group:info:closeGroup')")
    public ResponseEntity<String> closeGroup(@RequestBody WeGroupDTO dto) {
        Integer groupId = dto.getGroupId();
        weGroupService.closeGroup(groupId);
        return ResponseEntity.ok("success");
    }

    /**
     * 解禁社群
     * @param dto
     * @return
     */
    @ApiOperation(position = 9, value = "9-解禁社群", notes = "解禁社群")
    @ApiOperationSupport(includeParameters = {"dto.groupId"})
    @RequestMapping(value = "/info/openGroup", method = RequestMethod.POST)
//    @PreAuthorize("@pms.hasPermission('group:info:openGroup')")
    public ResponseEntity<String> openGroup(@RequestBody WeGroupDTO dto) {
        Integer groupId = dto.getGroupId();
        weGroupService.openGroup(groupId);
        return ResponseEntity.ok("success");
    }

    @ApiOperation(position = 10, value = "10-学校列表", notes = "获取学校列表")
    @RequestMapping(value = "/list/getSchoolList", method = RequestMethod.POST)
//    @PreAuthorize("@pms.hasPermission('group:info:adminGroup')")
    @ApiOperationSupport(includeParameters = {"dto.schoolName"})
    public ResponseEntity<List<WeGroupSchoolVO>> getSchoolList(@RequestBody WeGroupSchoolDTO dto) {
        String  schoolName = dto.getSchoolName();
        List<WeGroupSchoolVO> list = weGroupService.getAllSchoolList(schoolName);
        return ResponseEntity.ok(list);
    }
}
