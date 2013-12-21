package com.pstu.acdps.client.components;

import java.util.Map;
import java.util.Map.Entry;

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
import com.pstu.acdps.shared.utils.StringUtils;

public class EmployeeEditPopup extends CustomPopup {
    public interface EmployeeSaveHandler {
        public void save(JobPosDto dto, CustomPopup sender);
    }

    private FlowPanel panel = new FlowPanel();
    private ScrollPanel scroll = new ScrollPanel(panel);
    private FlowPanel employeeEditInputsPanel = new FlowPanel();
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
    private CustomDateBox birthDay;
    private CustomTextBox surname;
    private CustomTextBox patronymic;
    private Element selectedDepartment;
    private TreeWidget<SSPObjectDto> tree;
    private CustomListBox position;
    private Map<Long, String> jobMap;

    public EmployeeEditPopup(EmployeeSaveHandler handler, JobPosDto dto, TreeWidget<SSPObjectDto> tree,
            Map<Long, String> jobMap) {
        super();
        this.dto = dto;
        this.jobMap = jobMap;
        this.tree = tree;
        tree.setSelectHandler(new ObjectsSelectHandler<SSPObjectDto>() {
            @Override
            public void selected(SSPObjectDto object) {
                selectedDepartment.setInnerText(object.getNodeName());
            }
        });
        setHandler(handler);
        setHeader("Редатирование сотрудника");
        employeeEditInputsPanel.addStyleName("input");
        panel.add(employeeEditInputsPanel);
        modalBody.add(scroll);
        scroll.addStyleName("edit-popup-scroll");
        modalFooter.addStyleName("text-left");
        modalFooter.add(saveBtn);
        modalFooter.add(cancel);
        createItem(dto);
    }

    private void createItem(JobPosDto dto) {
        final FlowPanel itemPanel = new FlowPanel();
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
        birthDay = new CustomDateBox();
        birthDay.addLabelStyleName("label-left");
        birthDay.addInputStyleName("horizontal-input");
        birthDay.setCaption("Дата рождения:");
        birthDay.setLeftValue(dto.getStartDate());
        itemPanel.add(birthDay);
        position = new CustomListBox();
        position.addLabelStyleName("label-left");
        position.addInputStyleName("horizontal-input");
        position.setCaption("Должность:");
        for (Entry<Long, String> entry : jobMap.entrySet()) {
            position.addValue(entry.getValue(), entry.getKey().toString());
        }
        itemPanel.add(position);
        Label parentTextLabel = new Label("Подразделение: ");
        selectedDepartment = DOM.createSpan();
        parentTextLabel.getElement().appendChild(selectedDepartment);
        itemPanel.add(parentTextLabel);
        ScrollPanel sp = new ScrollPanel(tree);
        sp.addStyleName("tree-scroll-wrap object-selector");
        itemPanel.add(sp);
        employeeEditInputsPanel.add(itemPanel);
    }

    protected void saveAllChanges() {
        if (handler != null) {
            boolean commit = true;
            if (StringUtils.isEmpty(name.getValue())) {
                commit = false;
                AlertDialogBox.showDialogBox("Поле 'Фамилия' не может быть пустым");
            }
            if (StringUtils.isEmpty(surname.getValue())) {
                commit = false;
                AlertDialogBox.showDialogBox("Поле 'Имя' не может быть пустым");
            }
            if (StringUtils.isEmpty(patronymic.getValue())) {
                commit = false;
                AlertDialogBox.showDialogBox("Поле 'Отчество' не может быть пустым");
            }
            if (birthDay.getValue() == null) {
                commit = false;
                AlertDialogBox.showDialogBox("Поле 'Дата рождения' обязательно для заполнения");
            }
            if (StringUtils.isEmpty(position.getSelectedValue())) {
                commit = false;
                AlertDialogBox.showDialogBox("Поле 'Должность' не может быть пустым");
            }
            if (tree.getSelectedNode() == null) {
                commit = false;
                AlertDialogBox.showDialogBox("Поле 'Подразделение' не может быть пустым");
            }
            if (tree.getSelectedNode().getId() == null) {
                commit = false;
                AlertDialogBox.showDialogBox("Выбранное подразделение не применимо к этому полю");
            }
            if (commit) {
                dto.getEmployeeDto().setFirstName(name.getValue());
                dto.getEmployeeDto().setSecondName(surname.getValue());
                dto.getEmployeeDto().setMiddleName(patronymic.getValue());
                dto.getEmployeeDto().setBirthday(birthDay.getValue());
                dto.setJob(Long.parseLong(position.getSelectedValue()));
                dto.setDepartmentId(tree.getSelectedNode().getId());
                dto.setDepartmentName(tree.getSelectedNode().getName());
                handler.save(dto, EmployeeEditPopup.this);
            }
        }

    }

    public void setHandler(EmployeeSaveHandler handler) {
        this.handler = handler;
    }
}
