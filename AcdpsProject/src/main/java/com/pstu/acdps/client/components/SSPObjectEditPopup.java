package com.pstu.acdps.client.components;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.pstu.acdps.client.components.Btn.EButtonStyle;
import com.pstu.acdps.client.components.TreeWidget.ObjectsSelectHandler;
import com.pstu.acdps.client.mvp.presenter.SSPObjectPresenter;
import com.pstu.acdps.shared.dto.SSPObjectDto;

public class SSPObjectEditPopup extends CustomPopup {
    public interface SSPObjectSaveHandler {
        public void save(SSPObjectDto dto, SSPObjectEditPopup sender);
    }

    private FlowPanel panel = new FlowPanel();
    private ScrollPanel scroll = new ScrollPanel(panel);
    private FlowPanel sspobejctEditInputsPanel = new FlowPanel();
    private TreeWidget<SSPObjectDto> tree;
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
    Element selectedDepartment;

    public SSPObjectEditPopup(SSPObjectSaveHandler handler, SSPObjectDto dto, SSPObjectPresenter presenter) {
        super();
        tree = presenter.getSSPObjectTree();
        tree.setSelectHandler(new ObjectsSelectHandler<SSPObjectDto>() {
            @Override
            public void selected(SSPObjectDto object) {
                if(object.getId()!=null){
                    selectedDepartment.setInnerText(object.getNodeName());
                }else{
                    selectedDepartment.setInnerText("");
                }
            }
        });
        this.dto = dto;
        addStyleName("sspobejct-edit-popup");
        setHandler(handler);
        setHeader("Редатирование подразделения");
        sspobejctEditInputsPanel.addStyleName("input");
        panel.add(sspobejctEditInputsPanel);
        modalBody.add(scroll);
        scroll.addStyleName("edit-popup-scroll");
        modalFooter.addStyleName("text-left");
        modalFooter.add(saveBtn);
        modalFooter.add(cancel);
        createItem(dto);
    }

    private void createItem(SSPObjectDto dto) {
        final FlowPanel itemPanel = new FlowPanel();
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
        Label parentTextLabel = new Label("Родительский объект: ");
        selectedDepartment = DOM.createSpan();
        parentTextLabel.getElement().appendChild(selectedDepartment);
        itemPanel.add(parentTextLabel);
        ScrollPanel sp = new ScrollPanel(tree);
        sp.addStyleName("tree-scroll-wrap object-selector");
        itemPanel.add(sp);
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
            if (dto.getParentId() == null && tree.getSelectedNode() == null) {
                AlertDialogBox.showDialogBox("Выберите родительский элемент для добавляемого узла");
            }
            if (commit) {
                dto.setName(name.getValue());
                dto.setStartDate(date.getLeftValue());
                dto.setEndDate(date.getRightValue());
                dto.setParentId(tree.getSelectedNode().getId());
                handler.save(dto, SSPObjectEditPopup.this);
            }
        }

    }

    public void setHandler(SSPObjectSaveHandler handler) {
        this.handler = handler;
    }

}
