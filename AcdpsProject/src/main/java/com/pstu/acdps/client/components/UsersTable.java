package com.pstu.acdps.client.components;

import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.pstu.acdps.client.components.Btn.EButtonStyle;
import com.pstu.acdps.client.mvp.presenter.UsersPresenter;
import com.pstu.acdps.client.type.ActionType;
import com.pstu.acdps.shared.dto.RoleDto;
import com.pstu.acdps.shared.dto.UserDto;

public class UsersTable extends Composite {

    FlowPanel panel = new FlowPanel();
    private FlexTable table = new FlexTable();
    private int index = 0;
    private UsersPresenter presenter;

    public UsersTable(UsersPresenter presenter) {
        this.presenter = presenter;
        initWidget(panel);
        table.addStyleName("table table-stripped");
        panel.add(table);
        createThead();
    }

    private void createThead() {
        Element thead = DOM.createTHead();
        Element tr = DOM.createTR();
        Element th = DOM.createTH();
        th.setInnerText("Сотрудник");
        th.addClassName("col-xs-5");
        tr.appendChild(th);
        th = DOM.createTH();
        th.setInnerText("Логин");
        th.addClassName("col-xs-2");
        tr.appendChild(th);
        th = DOM.createTH();
        th.setInnerText("Роли");
        th.addClassName("col-xs-3");
        tr.appendChild(th);
        th = DOM.createTH();
        th.setInnerHTML("<span class='glyphicon glyphicon-pencil' style='margin: 6px 12px'></span>");
        th.addClassName("col-xs-1");
        tr.appendChild(th);
        th = DOM.createTH();
        th.setInnerHTML("<span class='glyphicon glyphicon-minus' style='margin: 6px 12px'></span>");
        th.addClassName("col-xs-1");
        tr.appendChild(th);
        thead.appendChild(tr);
        Element tbody = table.getElement().getElementsByTagName("tbody").getItem(0);
        tbody.removeFromParent();
        table.getElement().appendChild(thead);
        table.getElement().appendChild(tbody);
    }

    public void addUserList(List<UserDto> dtoList) {
        if (dtoList != null) {
            for (UserDto u : dtoList) {
                addUser(u);
            }
        }
    }

    public void addUser(final UserDto dto) {

        table.setHTML(index, 0, dto.getEmployee() == null ? "<em>нет привязки к сотруднику</em>" : dto.getEmployee().getFullName());
        table.setText(index, 1, dto.getLogin());
        table.setHTML(index, 2, formatRoles(dto.getRoles(), dto.getAdmin()));
        Btn editBtn = new Btn("<span class='glyphicon glyphicon-pencil'></span>", EButtonStyle.LINK);
        Btn removeBtn = new Btn("<span class='glyphicon glyphicon-minus'></span>", EButtonStyle.LINK);
        editBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (presenter.getActionHandler() != null) {
                    presenter.getActionHandler().doAction(ActionType.EDIT, dto);
                }
            }
        });
        removeBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (presenter.getActionHandler() != null) {
                    presenter.getActionHandler().doAction(ActionType.REMOVE, dto);
                }
            }
        });
        table.setWidget(index, 3, editBtn);
        table.setWidget(index, 4, removeBtn);
        index++;
    }

    private String formatRoles(List<RoleDto> roles, boolean isAdmin) {
        StringBuilder sb = new StringBuilder();
        if (isAdmin) {
            sb.append("Администратор");
        }
        else{
            for (int i = 0; i < roles.size(); i++) {
                if(i != 0){
                    sb.append(",<br>");
                }
                sb.append(roles.get(i).getName());
            }
        }
        return sb.toString();
    }

    public void reset() {
        table.removeAllRows();
    }

}
