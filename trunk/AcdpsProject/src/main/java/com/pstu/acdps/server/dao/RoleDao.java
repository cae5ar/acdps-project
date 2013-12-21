package com.pstu.acdps.server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pstu.acdps.server.domain.Role;
import com.pstu.acdps.shared.dto.RoleDto;
import com.pstu.acdps.shared.exception.AnyServiceException;

@Repository
public class RoleDao extends JpaDao<Role> {

    @Override
    public Class<Role> getEntityClass() {
        return Role.class;
    }

    public void save(RoleDto bean) throws AnyServiceException {
        Role entity = null;
        if (bean.getId() == null) {
            entity = new Role();
        }
        else {
            entity = findById(bean.getId());
        }

        entity.setIdent(bean.getIdent());
        entity.setName(bean.getName());
        persist(entity);
        entity.getId();
    }

    @SuppressWarnings("unchecked")
    public List<RoleDto> getAllRoles() {

        List<RoleDto> result = new ArrayList<RoleDto>();
        Query roleQuery = em.createQuery("select role from Role role");

        List<Role> resultList = roleQuery.getResultList();

        for (Role r : resultList) {
            RoleDto dto = new RoleDto(r.getId(), r.getName(), r.getIdent());
            result.add(dto);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public RoleDto getRoleByIdent(String ident) {

        RoleDto result = null;

        Query roleQuery = em.createQuery("select role from Role role where role.ident = :ident");
        roleQuery.setParameter("ident", ident);

        List<Role> roles = roleQuery.getResultList();

        if (roles.size() > 0) {
            Role r = roles.get(0);
            result = new RoleDto(r.getId(), r.getName(), r.getIdent());
        }

        return result;
    }
}
