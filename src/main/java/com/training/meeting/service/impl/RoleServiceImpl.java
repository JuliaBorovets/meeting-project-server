package com.training.meeting.service.impl;

import com.training.meeting.domain.user.Role;
import com.training.meeting.exception.RoleCanNotFindException;
import com.training.meeting.repository.user.RoleRepository;
import com.training.meeting.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findRoleByName(String name) throws RoleCanNotFindException {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RoleCanNotFindException("can not find role with name=" + name));
    }
}
