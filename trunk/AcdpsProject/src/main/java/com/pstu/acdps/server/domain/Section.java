package com.pstu.acdps.server.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "SSP_SECTION")
public class Section extends SSPObject {

	@Column(nullable = false, name = "name")
	private String name;
	
	@Column(name = "code")
	private String code;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "section", cascade = CascadeType.ALL)
	private List<SectionCFO> sectionCFO = new ArrayList<SectionCFO>();

	public List<SectionCFO> getSectionCFO() {
		return sectionCFO;
	}

	public void setSectionCFO(List<SectionCFO> sectionCFO) {
		this.sectionCFO = sectionCFO;
	}

}
