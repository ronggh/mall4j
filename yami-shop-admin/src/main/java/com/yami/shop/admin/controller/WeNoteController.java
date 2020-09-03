package com.yami.shop.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yami.shop.bean.dto.WeNoteIdDTO;
import com.yami.shop.bean.dto.WeNoteSearchDTO;
import com.yami.shop.bean.vo.WeNoteDetailVO;
import com.yami.shop.bean.vo.WeNoteVO;
import com.yami.shop.service.WeNoteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/***
 * @author YaJie
 */
@Api(tags = {"笔记管理"})
@RestController
@RequestMapping("/note")
public class WeNoteController {
    @Autowired
    private WeNoteService weNoteService;

    /**
     * 搜索笔记列表
     * @param dto
     * @return
     */
    @ApiOperation(position = 1, value = "1-搜索笔记列表", notes = "搜索显示笔记列表")
    @RequestMapping(value = "/list/noteList", method = RequestMethod.POST)
    @PreAuthorize("@pms.hasPermission('note:list:noteList')")
    public ResponseEntity<Page<WeNoteVO>> noteList(@RequestBody WeNoteSearchDTO dto) {
        Page<WeNoteVO> list = weNoteService.getNoteList(dto);
        return ResponseEntity.ok(list);
    }

    /**
     * 获取笔记详情
     * @param dto
     * @return
     */
    @ApiOperation(position = 2, value = "2-获取笔记详情", notes = "获取笔记详情")
    @RequestMapping(value = "/list/noteDetail", method = RequestMethod.POST)
    @PreAuthorize("@pms.hasPermission('note:list:noteDetail')")
    public ResponseEntity<WeNoteDetailVO> getNoteDetail(@RequestBody WeNoteIdDTO dto) {
        Integer noteId = dto.getNoteId();
        WeNoteDetailVO weNoteDetailVO = weNoteService.getNoteDetail(noteId);
        return ResponseEntity.ok(weNoteDetailVO);
    }

    /**
     * 删除指定的笔记
     * @param dto
     * @return
     */
    @ApiOperation(position = 3, value = "3-删除指定的笔记", notes = "删除指定的笔记")
    @RequestMapping(value = "/info/delNote", method = RequestMethod.POST)
    @PreAuthorize("@pms.hasPermission('note:info:delNote')")
    public ResponseEntity<Void> delNoteById(@RequestBody WeNoteIdDTO dto) {
        Integer noteId = dto.getNoteId();
        weNoteService.delNoteById(noteId);
        return ResponseEntity.ok().build();
    }

}
