package com.training.meeting.service;

import com.training.meeting.domain.user.Role;
import com.training.meeting.exception.RoleCanNotFindException;

public interface RoleService {
    Role findRoleByName(String name) throws RoleCanNotFindException;
}
