package com.pstu.acdps.client.components;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.dom.client.Style.Display;
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
    private Map<CustomDateBox, SectionCFODto> map = new HashMap<CustomDateBox, SectionCFODto>();

    // private int index = 0;
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

    public void addCfoSectionDto(SectionCFODto dto) {
        if (map.isEmpty()) {
            setVisibleEmptyWidget(false);
        }
        table.setHTML(table.getRowCount(), 0, dto.getSection().getName());
        int rowIndex = table.getRowCount() - 1;
        table.getCellFormatter().setStyleName(rowIndex, 0, "section-name");
        table.setHTML(rowIndex, 1, "c");
        final CustomDateBox leftDate = new CustomDateBox();
        map.put(leftDate, dto);
        leftDate.setValue(dto.getStartDate());
        table.setWidget(rowIndex, 2, leftDate);
        table.setHTML(rowIndex, 3, "по");
        CustomDateBox rightDate = new CustomDateBox();
        rightDate.setValue(dto.getEndDate());
        table.setWidget(rowIndex, 4, rightDate);
        final Btn removeBtn = new Btn("<span class='glyphicon glyphicon-remove'></span>", EButtonStyle.LINK);
        removeBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                map.remove(leftDate);
                removeBtn.getElement().getParentElement().getParentElement().removeFromParent();
                if (map.isEmpty()) {
                    setVisibleEmptyWidget(true);
                }
            }
        });
        table.setWidget(rowIndex, 5, removeBtn);
    }

    private void setVisibleEmptyWidget(boolean visible) {
        if (visible) {
            emptyText.getElement().getStyle().setDisplay(Display.BLOCK);
        }
        else {
            emptyText.getElement().getStyle().setDisplay(Display.NONE);
        }
    }

    public List<SectionCFODto> getSectionCfoList() {
        List<SectionCFODto> outList = new ArrayList<SectionCFODto>();
        for (int i = 0; i < table.getRowCount(); i++) {
            CustomDateBox leftDateInput = (CustomDateBox) table.getWidget(i, 2);
            CustomDateBox rightDateInput = (CustomDateBox) table.getWidget(i, 4);
            Date leftDate = leftDateInput.getValue();
            Date rightDate = rightDateInput.getValue();
            if (leftDate != null && rightDate != null && leftDate.before(rightDate)) {
                SectionCFODto sectionCFODto = map.get(leftDateInput);
                sectionCFODto.setStartDate(leftDate);
                sectionCFODto.setEndDate(rightDate);
                outList.add(sectionCFODto);
            }
            else {
                return null;
            }
        }
        return outList;
    }

}
