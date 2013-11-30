package com.pstu.acdps.server.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

@MappedSuperclass
public abstract class AbstractEntity {
    
	@Id  
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "my_entity_seq_gen")
    @SequenceGenerator(name = "my_entity_seq_gen", sequenceName = "ssp_seq_std")
    protected Long id;

    public AbstractEntity() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj.getClass().getCanonicalName() != this.getClass().getCanonicalName()) return false;
        if (this.getId() == null || ((AbstractEntity) obj).getId() == null) return false;
        return ((AbstractEntity) obj).getId().equals(this.getId());
    }
}
