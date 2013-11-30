package com.pstu.acdps.client.components;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.pstu.acdps.client.SimpleAsyncCallback;
import com.pstu.acdps.client.Site;
import com.pstu.acdps.client.components.Btn.EButtonStyle;
import com.pstu.acdps.client.components.CustomDialogBox.EAlertType;
import com.pstu.acdps.shared.dto.SSPObjectDto;

public class DepartmentEditWidget extends Composite {
    FlowPanel panel = new FlowPanel();
    CustomDateBox dateBox = new CustomDateBox();
    CustomTextBox nameTextBox = new CustomTextBox();
    CustomTextBox pareniIdtextBox = new CustomTextBox();
    Btn save = new Btn("Сохранить", EButtonStyle.INFO, new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
            Site.service.saveDepartment(getItem(), new SimpleAsyncCallback<Long>() {
                @Override
                public void onSuccess(Long result) {
                    CustomDialogBox.showDialogBox("ОК", "id departmnet: " + result, EAlertType.SUCCES);
                }
            });
        }
    });

    public DepartmentEditWidget() {
        initWidget(panel);
        panel.addStyleName("row");
        panel.add(nameTextBox);
        panel.add(pareniIdtextBox);
        panel.add(dateBox);
        panel.add(save);
    }

    public SSPObjectDto getItem() {
        SSPObjectDto dto = new SSPObjectDto();
        dto.setName(nameTextBox.getValue());
        dto.setStartDate(dateBox.getValue());
        dto.setParentId(pareniIdtextBox.getValue() == null || pareniIdtextBox.getValue().equals("") ? null : Long.parseLong(pareniIdtextBox.getValue()));
        return dto;
    }

}
