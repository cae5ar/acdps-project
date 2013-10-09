package com.pstu.acdps.server.dao;

import org.springframework.stereotype.Repository;

import com.pstu.acdps.server.domain.File;

@Repository
public class FileDao extends JpaDao<File> {

    @Override
    public Class<File> getEntityClass() {
        return File.class;
    }
}
