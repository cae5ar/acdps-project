package com.pstu.acdps.client.components;

import java.util.Date;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TreeItem;
import com.pstu.acdps.client.SimpleAsyncCallback;
import com.pstu.acdps.client.Site;
import com.pstu.acdps.client.components.Btn.EButtonStyle;
import com.pstu.acdps.client.components.TreeWidget.ObjectsOpenHandler;
import com.pstu.acdps.shared.dto.SSPObjectDto;

public class SSPObjectEditPopup extends CustomPopup {
    public interface SSPObjectSaveHandler {
        public void save(SSPObjectDto dto, SSPObjectEditPopup sender);
    }

    private FlowPanel panel = new FlowPanel();
    private ScrollPanel scroll = new ScrollPanel(panel);
    private FlowPanel sspobejctEditInputsPanel = new FlowPanel();
    private TreeWidget<SSPObjectDto> tree = new TreeWidget<SSPObjectDto>(new ObjectsOpenHandler<SSPObjectDto>() {
        public void selected(SSPObjectDto object, final TreeItem source) {
            Site.service.getDepartmentChilds(object.getId(), selectedDate, new SimpleAsyncCallback<List<SSPObjectDto>>() {
                @Override
                public void onSuccess(List<SSPObjectDto> result) {
                    tree.addItemList(result, source);
                }
            });
        }
    }, new SSPObjectDto(null, "Все подразделения", null, new Date()));
    private Btn cancel = new Btn("Отменить", EButtonStyle.DEFAULT, new ClickHandler() {
        public void onClick(ClickEvent event) {
            SSPObjectEditPopup.this.hide();
        }
    });
    private SSPObjectSaveHandler handler = null;
    private Btn saveBtn = new Btn("Сохранить изменения", EButtonStyle.SUCCESS, new ClickHandler() {
        public void onClick(ClickEvent event) {
            saveAllChanges();
        }
    });
    private KeyDownHandler enterPressHandler = new KeyDownHandler() {
        public void onKeyDown(KeyDownEvent event) {
            if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) saveAllChanges();
        }
    };
    private SSPObjectDto dto;
    private CustomTextBox name;
    private CustomDateBox date;
    private Date selectedDate;

    public SSPObjectEditPopup(SSPObjectSaveHandler handler, SSPObjectDto dto, Date selectedDate) {
        super();
        this.dto = dto;
        this.selectedDate = selectedDate;
        addStyleName("sspobejct-edit-popup");
        setHandler(handler);
        setHeader("Редатирование подразделения");
        sspobejctEditInputsPanel.addStyleName("input");
        panel.add(sspobejctEditInputsPanel);
        modalBody.add(scroll);
        scroll.addStyleName("sspobject-edit-scroll");
        modalFooter.addStyleName("text-left");
        modalFooter.add(saveBtn);
        modalFooter.add(cancel);
        createItem(dto);
    }

    private void createItem(SSPObjectDto dto) {
        final FlowPanel itemPanel = new FlowPanel();
        itemPanel.addStyleName("sspobejct-edit-block");
        name = new CustomTextBox();
        name.addLabelStyleName("label-left");
        name.addInputStyleName("horizontal-input");
        name.setValue(dto.getName());
        name.setCaption("Название:");
        itemPanel.add(name);
        name.getTextBox().addKeyDownHandler(enterPressHandler);
        name.setFocus();
        date = new CustomDateBox(true);
        date.addLabelStyleName("label-left");
        date.addInputStyleName("horizontal-input");
        date.setCaption("Период:");
        date.setLeftValue(dto.getStartDate());
        date.setRightValue(dto.getEndDate());
        itemPanel.add(date);
        if (selectedDate != null) {
            itemPanel.add(tree);
        }
        sspobejctEditInputsPanel.add(itemPanel);
    }

    protected void saveAllChanges() {
        if (handler != null) {
            boolean commit = true;
            if (name.getValue() == null || name.getValue().isEmpty()) {
                commit = false;
                AlertDialogBox.showDialogBox("Поле имени не может быть пустым");
            }
            if (date.getValue() == null) {
                commit = false;
                AlertDialogBox.showDialogBox("Поле 'начало периода' обязательно для заполнения");
            }
            if (commit) {
                dto.setName(name.getValue());
                dto.setStartDate(date.getLeftValue());
                dto.setEndDate(date.getRightValue());
                handler.save(dto, SSPObjectEditPopup.this);
            }
        }

    }

    public void setHandler(SSPObjectSaveHandler handler) {
        this.handler = handler;
    }

}
