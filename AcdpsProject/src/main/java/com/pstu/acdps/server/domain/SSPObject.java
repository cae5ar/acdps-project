package com.pstu.acdps.server.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "SSP_OBJECT")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class SSPObject extends AbstractEntity {
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sspObject", cascade = CascadeType.ALL)
	private List<SSPObjectHierachy> hierrachies = new ArrayList<SSPObjectHierachy>();

	public List<SSPObjectHierachy> getHierrachies() {
		return hierrachies;
	}

	public void setHierrachies(List<SSPObjectHierachy> hierrachies) {
		this.hierrachies = hierrachies;
	}

}
