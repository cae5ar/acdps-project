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
import com.pstu.acdps.shared.dto.JobPosDto;
import com.pstu.acdps.shared.dto.SSPObjectDto;

public class EmployeeEditPopup extends CustomPopup {
    public interface EmployeeSaveHandler {
        public void save(JobPosDto dto, CustomPopup sender);
    }

    private FlowPanel panel = new FlowPanel();
    private ScrollPanel scroll = new ScrollPanel(panel);
    private FlowPanel sspobejctEditInputsPanel = new FlowPanel();
    private Btn cancel = new Btn("Отменить", EButtonStyle.DEFAULT, new ClickHandler() {
        public void onClick(ClickEvent event) {
            EmployeeEditPopup.this.hide();
        }
    });
    private EmployeeSaveHandler handler = null;
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
    private JobPosDto dto;
    private CustomTextBox name;
    private CustomDateBox date;
    private CustomTextBox surname;
    private CustomTextBox patronymic;
    private Element selectedDepartment;
    private TreeWidget<SSPObjectDto> tree;
    private CustomTextBox position;

    public EmployeeEditPopup(EmployeeSaveHandler handler, JobPosDto dto, TreeWidget<SSPObjectDto> tree) {
        super();
        this.dto = dto;
        this.tree = tree;
        tree.setSelectHandler(new ObjectsSelectHandler<SSPObjectDto>() {
            @Override
            public void selected(SSPObjectDto object) {
                selectedDepartment.setInnerText(object.getNodeName());
            }
        });
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

    private void createItem(JobPosDto dto) {
        final FlowPanel itemPanel = new FlowPanel();
        itemPanel.addStyleName("sspobejct-edit-block");
        name = new CustomTextBox();
        name.addLabelStyleName("label-left");
        name.addInputStyleName("horizontal-input");
        name.setValue(dto.getEmployeeDto().getFirstName());
        name.setCaption("Фамилия:");
        name.getTextBox().addKeyDownHandler(enterPressHandler);
        itemPanel.add(name);
        name.setFocus();
        surname = new CustomTextBox();
        surname.addLabelStyleName("label-left");
        surname.addInputStyleName("horizontal-input");
        surname.setValue(dto.getEmployeeDto().getSecondName());
        surname.setCaption("Имя:");
        surname.getTextBox().addKeyDownHandler(enterPressHandler);
        itemPanel.add(surname);
        patronymic = new CustomTextBox();
        patronymic.addLabelStyleName("label-left");
        patronymic.addInputStyleName("horizontal-input");
        patronymic.setValue(dto.getEmployeeDto().getMiddleName());
        patronymic.setCaption("Отчество:");
        patronymic.getTextBox().addKeyDownHandler(enterPressHandler);
        itemPanel.add(patronymic);
        position = new CustomTextBox();
        position.addLabelStyleName("label-left");
        position.addInputStyleName("horizontal-input");
        position.setValue(dto.getEmployeeDto().getMiddleName());
        position.setCaption("Должность:");
        position.getTextBox().addKeyDownHandler(enterPressHandler);
        itemPanel.add(position);
        Label parentTextLabel = new Label("Подразделение: ");
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
            if (commit) {
//                dto.setName(name.getValue());
                dto.setStartDate(date.getLeftValue());
                dto.setEndDate(date.getRightValue());
                handler.save(dto, EmployeeEditPopup.this);
            }
        }

    }

    public void setHandler(EmployeeSaveHandler handler) {
        this.handler = handler;
    }
}
