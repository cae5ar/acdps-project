package com.pstu.acdps.server.dao;

import com.pstu.acdps.server.domain.Document;
import com.pstu.acdps.shared.dto.DocumentDto;

public class DocumentDao extends JpaDao<Document> {

    @Override
    public Class<Document> getEntityClass() {
        return Document.class;
    }
    
    public Long save(DocumentDto bean){
        //TODO: продолжить
        return null;
    }

}
