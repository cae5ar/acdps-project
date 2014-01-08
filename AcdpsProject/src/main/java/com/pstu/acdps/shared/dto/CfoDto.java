package com.pstu.acdps.shared.dto;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class CfoDto extends EntityDto {

    private String name;
    private List<SectionCFODto> sectionCfo = null;

    public CfoDto() {
        super();
        name = "";
        sectionCfo = new ArrayList<SectionCFODto>();
    }

    public CfoDto(Long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SectionCFODto> getSectionCfo() {
        return sectionCfo;
    }

    public void setSectionCfo(List<SectionCFODto> sectionCfo) {
        this.sectionCfo = sectionCfo;
    }

}
