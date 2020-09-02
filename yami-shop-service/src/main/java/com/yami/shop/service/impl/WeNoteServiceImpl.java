package com.yami.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yami.shop.bean.dto.WeNoteSearchDTO;
import com.yami.shop.bean.model.WeNote;
import com.yami.shop.bean.vo.WeNoteDetailVO;
import com.yami.shop.bean.vo.WeNoteImageVO;
import com.yami.shop.bean.vo.WeNoteVO;
import com.yami.shop.dao.WeNoteMapper;
import com.yami.shop.service.WeNoteService;

@Service
public class WeNoteServiceImpl extends ServiceImpl<WeNoteMapper, WeNote> implements WeNoteService {
    @Autowired
    private WeNoteMapper weNoteMapper;

    /**
     * 搜索笔记，分页显示
     * 
     * @param dto
     * @return
     */
    @Override
    public Page<WeNoteVO> getNoteList(WeNoteSearchDTO dto) {
        //
        Page<WeNoteVO> page = new Page<>();
        page.setCurrent(dto.getCurrentPage());
        page.setSize(dto.getPageSize());
        String content = "%" + dto.getContent() +"%";

        //
        List<WeNoteVO> list = weNoteMapper.getNoteList(page,content);
        page.setRecords(list);
        return page;
    }

    /**
     * 获取提定笔记的详细信息
     * @param noteId
     * @return
     */
    @Override
    public WeNoteDetailVO getNoteDetail(Integer noteId){
       WeNoteDetailVO noteDetailVO = new WeNoteDetailVO();

       WeNoteVO noteVO =  weNoteMapper.getNoteDetail(noteId);
       List<WeNoteImageVO> images = weNoteMapper.getNoteImageList(noteId);
        noteDetailVO.setNote(noteVO);
        noteDetailVO.setNoteImages(images);
       return noteDetailVO;
    }

    /**
     * 删除指定的笔记
     * @param noteId
     */
    @Override
    public int delNoteById(Integer noteId){
        return weNoteMapper.deleteById(noteId);
    }
}
