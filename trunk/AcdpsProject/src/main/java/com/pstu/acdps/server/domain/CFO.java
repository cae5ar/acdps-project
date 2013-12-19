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
@Table(name = "SSP_CFO")
public class CFO extends SSPObject {

	@Column(nullable = false, name = "name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cfo", cascade = CascadeType.ALL)
	private List<CFODepartment> cfoDepartment = new ArrayList<CFODepartment>();

	public List<CFODepartment> getCfoDepartment() {
		return cfoDepartment;
	}

	public void setCFODepartment(List<CFODepartment> cfoDepartment) {
		this.cfoDepartment = cfoDepartment;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cfo", cascade = CascadeType.ALL)
	private List<SectionCFO> sectionCfo = new ArrayList<SectionCFO>();

	public List<SectionCFO> getSectionCfo() {
		return sectionCfo;
	}

	public void setSectionCfo(List<SectionCFO> sectionCfo) {
		this.sectionCfo = sectionCfo;
	}

}
