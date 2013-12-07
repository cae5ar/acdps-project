package com.pstu.acdps.client.components;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.pstu.acdps.client.components.Btn.EButtonStyle;
import com.pstu.acdps.shared.dto.SSPObjectDto;

public class SSPObjectEditPopup extends CustomPopup {
    public interface SSPObjectSaveHandler {
        public void save(SSPObjectDto dto, SSPObjectEditPopup sender);
    }

    private FlowPanel panel = new FlowPanel();
    private ScrollPanel scroll = new ScrollPanel(panel);
    private FlowPanel sspobejctEditInputsPanel = new FlowPanel();

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

    public SSPObjectEditPopup(SSPObjectSaveHandler handler, SSPObjectDto dto) {
        super();
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
        createItem(dto == null ? new SSPObjectDto() : dto);
    }

    private void createItem(final SSPObjectDto dto) {
        final FlowPanel itemPanel = new FlowPanel();
        itemPanel.addStyleName("sspobejct-edit-block");
        CustomTextBox name = new CustomTextBox();
        name.addLabelStyleName("label-left");
        name.addInputStyleName("horizontal-input");
        name.setValue(dto.getName());
        name.setCaption("Название:");
        itemPanel.add(name);
        name.getTextBox().addKeyDownHandler(enterPressHandler);
        name.setFocus();
        CustomDateBox date = new CustomDateBox(true);
        date.addLabelStyleName("label-left");
        date.addInputStyleName("horizontal-input");
        date.setCaption("Период:");
        date.setLeftValue(dto.getStartDate());
        date.setLeftValue(dto.getEndDate());
        itemPanel.add(date);
        sspobejctEditInputsPanel.add(itemPanel);
    }

    protected void saveAllChanges() {
        if (handler != null) {
            handler.save(null, SSPObjectEditPopup.this);
        }

    }

    public void setHandler(SSPObjectSaveHandler handler) {
        this.handler = handler;
    }

}
