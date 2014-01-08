package com.pstu.acdps.client.components;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.pstu.acdps.client.components.Btn.EButtonStyle;
import com.pstu.acdps.shared.dto.CfoDto;
import com.pstu.acdps.shared.dto.SSPObjectDto;
import com.pstu.acdps.shared.dto.SectionCFODto;
import com.pstu.acdps.shared.type.SystemConstants;

public class CfoSectionsTable extends Composite {

    private FlowPanel panel = new FlowPanel();
    private FlexTable table = new FlexTable();
    private List<SectionCFODto> list = new ArrayList<SectionCFODto>();
    private int index = 0;
    private HTML emptyText;

    public CfoSectionsTable() {
        initWidget(panel);
        emptyText = new HTML("<h5>Пусто</h5>");
        panel.add(emptyText);
        panel.add(table);
        table.addStyleName("table cfo-sections-table");
        emptyText.addStyleName("text-center");
    }

    public void addCfoSectionList(List<SectionCFODto> list) {
        for (SectionCFODto dto : list) {
            addCfoSectionDto(dto);
        }
    }

    public void addCfoSection(CfoDto cfoDto, SSPObjectDto sspObjectDto) {
        addCfoSectionDto(new SectionCFODto(cfoDto, sspObjectDto, new Date(), SystemConstants.endDate));
    }

    public void addCfoSectionDto(final SectionCFODto dto) {
        if (list.isEmpty()) {
            emptyText.removeFromParent();
        }
        list.add(dto);
        table.setHTML(index, 0, dto.getSection().getName());
        table.getCellFormatter().setStyleName(index, 0, "section-name");
        table.setHTML(index, 1, "c");
        CustomDateBox leftDate = new CustomDateBox();
        leftDate.setValue(dto.getStartDate());
        table.setWidget(index, 2, leftDate);
        table.setHTML(index, 3, "по");
        CustomDateBox rightDate = new CustomDateBox();
        rightDate.setValue(dto.getEndDate());
        table.setWidget(index, 4, rightDate);
        Btn removeBtn = new Btn("<span class='glyphicon glyphicon-remove'></span>", EButtonStyle.LINK);
        removeBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                list.remove(dto);
                table.removeRow(new Integer(index));
                index--;
            }
        });
        table.setWidget(index, 5, removeBtn);
        index++;
    }

    public List<SectionCFODto> getList() {
        return list;
    }
}
