package com.yami.shop.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yami.shop.bean.model.WeGroupMember;

public interface WeGroupMemberService extends IService<WeGroupMember> {
    void setGroupAdmins(Integer groupId, List<Integer> uidList);
}
