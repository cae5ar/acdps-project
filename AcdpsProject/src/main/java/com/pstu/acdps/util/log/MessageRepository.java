package com.pstu.acdps.util.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pstu.acdps.server.dao.JpaDao;
import com.pstu.acdps.server.dao.UserDao;
import com.pstu.acdps.shared.exception.AnyServiceException;

@Repository
public class MessageRepository extends JpaDao<MessageEntity> {

    @Autowired
    private UserDao userDao;

    @Transactional
    public void save(Message message) {
        MessageEntity entity = new MessageEntity();
        entity.setUser(userDao.findByLogin(message.getUserName()));
        entity.setMethod(message.getMethod());
        entity.setMessage(message.getMessage());
        entity.setStartTime(message.getStart());
        entity.setEndTime(message.getEnd());
        try {
            persist(entity);
        }
        catch (AnyServiceException e) {
            e.printStackTrace();
        }
    }

    public void save(MessageEntity entity) throws AnyServiceException {
        persist(entity);
    }

    @Override
    public Class<MessageEntity> getEntityClass() {
        return MessageEntity.class;
    }

}
