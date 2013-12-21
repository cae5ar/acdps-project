package com.pstu.acdps.client.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.pstu.acdps.client.Site;
import com.pstu.acdps.client.components.Btn.EButtonStyle;
import com.pstu.acdps.shared.dto.EmployeeDto;
import com.pstu.acdps.shared.dto.RoleDto;
import com.pstu.acdps.shared.dto.UserDto;
import com.pstu.acdps.shared.utils.StringUtils;

public class UserEditPopup extends CustomPopup {

    public interface UserSaveHandler {
        public void save(UserDto dto, String password, CustomPopup sender);
    }

    private FlowPanel panel = new FlowPanel();
    private ScrollPanel scroll = new ScrollPanel(panel);
    private FlowPanel userEditInputsPanel = new FlowPanel();
    private UserSaveHandler handler = null;
    private CustomTextBox login;
    private CustomPasswordTextBox passwordInput;
    private CustomPasswordTextBox passwordInputConfirm;
    private UserDto dto;
    private CustomListBox employeeListBox;
    private Map<Long, EmployeeDto> employeesMap = new HashMap<Long, EmployeeDto>();

    private Btn cancel = new Btn("Отменить", EButtonStyle.DEFAULT, new ClickHandler() {
        public void onClick(ClickEvent event) {
            UserEditPopup.this.hide();
        }
    });

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

    public UserEditPopup(UserSaveHandler userSaveHandler, UserDto dto, List<EmployeeDto> employees) {
        super();
        this.dto = dto;
        setHeader("Редатирование пользователя");
        setHandler(userSaveHandler);
        userEditInputsPanel.addStyleName("input");
        panel.add(userEditInputsPanel);
        modalBody.add(scroll);
        scroll.addStyleName("edit-popup-scroll");
        modalFooter.addStyleName("text-left");
        modalFooter.add(saveBtn);
        modalFooter.add(cancel);
        createItem(dto, employees);
    }

    private void createItem(UserDto dto, List<EmployeeDto> employees) {
        final FlowPanel itemPanel = new FlowPanel();
        itemPanel.addStyleName("user-edit-block");
        login = new CustomTextBox();
        login.addLabelStyleName("label-left");
        login.addInputStyleName("horizontal-input");
        login.setValue(dto.getLogin());
        login.setCaption("Логин:");
        login.getTextBox().addKeyDownHandler(enterPressHandler);
        itemPanel.add(login);
        login.setFocus();

        passwordInput = new CustomPasswordTextBox();
        passwordInput.addLabelStyleName("label-left");
        passwordInput.addInputStyleName("horizontal-input");
        passwordInput.setCaption("Пароль:");
        passwordInput.getTextBox().addKeyDownHandler(enterPressHandler);
        itemPanel.add(passwordInput);

        passwordInputConfirm = new CustomPasswordTextBox();
        passwordInputConfirm.addLabelStyleName("label-left");
        passwordInputConfirm.addInputStyleName("horizontal-input");
        passwordInputConfirm.setCaption("Подтвердите пароль:");
        passwordInputConfirm.getTextBox().addKeyDownHandler(enterPressHandler);
        itemPanel.add(passwordInputConfirm);

        employeeListBox = new CustomListBox();
        employeeListBox.addLabelStyleName("label-left");
        employeeListBox.addInputStyleName("horizontal-input");
        employeeListBox.setCaption("Сотрудник:");
        for (EmployeeDto e : employees) {
            employeeListBox.addValue(e.getFullName(), e.getId().toString());
            employeesMap.put(e.getId(), e);
        }
        itemPanel.add(employeeListBox);
        userEditInputsPanel.add(itemPanel);
        itemPanel.add(createRoleSelectBlock(dto.getRoles()));
    }

    private Widget createRoleSelectBlock(List<RoleDto> list) {
        FlowPanel roleSelectPanel = new FlowPanel();
        roleSelectPanel.addStyleName("user-role-select");
        HTML text = new HTML("Роли пользователя:");
        text.setStyleName("caption text-right");
        roleSelectPanel.add(text);

        final List<RoleDto> newUserRoleList = new ArrayList<RoleDto>();
        dto.setRoles(newUserRoleList);
        for (final RoleDto role : Site.roleList) {
            final CustomCheckBox cb = new CustomCheckBox();
            cb.addLabelStyleName("label-left");
            cb.addInputStyleName("horizontal-input");
            cb.setCaption(role.getName() + ":");
            if (hasUserRole(role, list)) {
                cb.setValue(true);
                newUserRoleList.add(role);
            }
            cb.getNativeCheckBox().addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    if (cb.getValue()) {
                        if (!newUserRoleList.contains(role)) {
                            newUserRoleList.add(role);
                        }
                    }
                    else {
                        newUserRoleList.remove(role);
                    }
                }
            });
            roleSelectPanel.add(cb);
        }
        return roleSelectPanel;
    }

    private boolean hasUserRole(RoleDto role, List<RoleDto> userRoleList) {
        for (RoleDto userRole : userRoleList) {
            if (userRole.getIdent().equals(role.getIdent())) return true;
        }
        return false;
    }

    protected void saveAllChanges() {
        if (handler != null) {
            boolean commit = true;
            if (StringUtils.isEmpty(login.getValue())) {
                commit = false;
                AlertDialogBox.showDialogBox("Поле 'Логин' не может быть пустым");
            }
            if (dto.getId() == null) {
                if (StringUtils.isEmpty(passwordInput.getValue())) {
                    commit = false;
                    AlertDialogBox.showDialogBox("Поле 'Пароль' не может быть пустым");
                }
                if (getPassword() == null) {
                    commit = false;
                    AlertDialogBox.showDialogBox("Введеные пароли не совпадают");
                }
            }else{
                if(!StringUtils.isEmpty(passwordInput.getValue())){
                    if(getPassword() == null){
                        commit = false;
                        AlertDialogBox.showDialogBox("Введеные пароли не совпадают");
                    }
                }
            }
            if (commit) {
                dto.setLogin(login.getValue());
                try {
                    Long selectedValue = Long.parseLong(employeeListBox.getSelectedValue());
                    dto.setEmployee(employeesMap.get(selectedValue));
                }
                catch (Exception e) {}
                dto.setName("УБРАТЬ ЭТО ПОЛЕ");
                handler.save(dto, getPassword(), UserEditPopup.this);
            }
        }
    }

    public String getPassword() {
        if (!passwordInput.getValue().isEmpty() && passwordInput.getValue().equals(passwordInputConfirm.getValue())) {
            return passwordInput.getValue();
        }
        else
            return null;
    }

    private void setHandler(UserSaveHandler handler) {
        this.handler = handler;
    }
}
