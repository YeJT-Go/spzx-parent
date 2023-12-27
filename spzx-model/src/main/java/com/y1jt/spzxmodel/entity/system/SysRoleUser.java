package com.y1jt.spzxmodel.entity.system;

import com.y1jt.spzxmodel.entity.base.BaseEntity;
import lombok.Data;

@Data
public class SysRoleUser extends BaseEntity {

    private Long roleId;       // 角色id
    private Long userId;       // 用户id

}
