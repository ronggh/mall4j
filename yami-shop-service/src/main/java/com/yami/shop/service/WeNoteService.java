package com.yami.shop.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yami.shop.bean.dto.WeNoteSearchDTO;
import com.yami.shop.bean.model.WeNote;
import com.yami.shop.bean.vo.WeNoteDetailVO;
import com.yami.shop.bean.vo.WeNoteVO;

public interface WeNoteService extends IService<WeNote> {
    /**
     * 搜索笔记，分页显示
     * @param dto
     * @return
     */
    Page<WeNoteVO> getNoteList(WeNoteSearchDTO dto);

    /**
     * 获取提定笔记的详细信息
     * @param noteId
     * @return
     */
    WeNoteDetailVO getNoteDetail(Integer noteId);

    /**
     * 删除指定的笔记
     * @param noteId
     * @return
     */
    int delNoteById(Integer noteId);
}
