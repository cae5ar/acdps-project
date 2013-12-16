package com.pstu.acdps.server.dao;

import org.springframework.stereotype.Repository;

import com.pstu.acdps.server.domain.Document;
import com.pstu.acdps.shared.dto.DocumentDto;

@Repository
public class DocumentDao extends JpaDao<Document> {

    @Override
    public Class<Document> getEntityClass() {
        return Document.class;
    }
    
    public Long save(DocumentDto bean){
    	return null;
    }

}
