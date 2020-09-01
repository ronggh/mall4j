package com.yami.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yami.shop.bean.model.WeGroupMember;
import com.yami.shop.dao.WeGroupMemberMapper;
import com.yami.shop.service.WeGroupMemberService;

@Service
public class WeGroupMemberServiceImpl extends ServiceImpl<WeGroupMemberMapper, WeGroupMember>
    implements WeGroupMemberService {
    @Autowired
    private WeGroupMemberMapper weGroupMemberMapper;

    @Override
    public void setGroupAdmins(Integer groupId, List<Integer> uidList) {
        // 先清除原来的管理员
        weGroupMemberMapper.clearGroupAdmins(groupId);
        // 设置新的管理员
        weGroupMemberMapper.setGroupAdmins(groupId, uidList);
    }
}
