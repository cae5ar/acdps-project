package com.pstu.acdps.client.components;

import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.pstu.acdps.client.components.Btn.EButtonStyle;
import com.pstu.acdps.client.mvp.presenter.EmployeesPresenter;
import com.pstu.acdps.client.type.ActionType;
import com.pstu.acdps.shared.dto.JobPosDto;

public class EmployeesTable extends Composite {
    FlowPanel panel = new FlowPanel();
    private FlexTable table = new FlexTable();
    private EmployeesPresenter presenter;
    private int index = 0;
    private DateTimeFormat df = DateTimeFormat.getFormat("dd MMMM yyyy");

    public EmployeesTable(EmployeesPresenter presenter) {
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
        th.setInnerText("Фамилия");
        th.addClassName("col-xs-2");
        tr.appendChild(th);
        th = DOM.createTH();
        th.setInnerText("Имя");
        th.addClassName("col-xs-2");
        tr.appendChild(th);
        th = DOM.createTH();
        th.setInnerText("Отчество");
        th.addClassName("col-xs-2");
        tr.appendChild(th);
        th = DOM.createTH();
        th.setInnerText("Дата рождения");
        th.addClassName("col-xs-2");
        tr.appendChild(th);
        th = DOM.createTH();
        th.setInnerText("Подразделение");
        th.addClassName("col-xs-2");
        tr.appendChild(th);
        th = DOM.createTH();
        th.setInnerText("Должность");
        th.addClassName("col-xs-2");
        tr.appendChild(th);
        th = DOM.createTH();
        th.setInnerHTML("<span class='glyphicon glyphicon-pencil' style='margin: 6px 12px'></span>");
        tr.appendChild(th);
        th = DOM.createTH();
        th.setInnerHTML("<span class='glyphicon glyphicon-remove' style='margin: 6px 12px'></span>");
        tr.appendChild(th);
        thead.appendChild(tr);
        Element tbody = table.getElement().getElementsByTagName("tbody").getItem(0);
        tbody.removeFromParent();
        table.getElement().appendChild(thead);
        table.getElement().appendChild(tbody);
    }

    public void addEmployeeList(List<JobPosDto> dtoList) {
        if (dtoList != null) {
            for (JobPosDto e : dtoList) {
                addEmployee(e);
            }
        }
    }

    public void addEmployee(final JobPosDto dto) {
        table.setText(index, 0, dto.getEmployeeDto().getFirstName());
        table.setText(index, 1, dto.getEmployeeDto().getMiddleName());
        table.setText(index, 2, dto.getEmployeeDto().getSecondName());
        table.setText(index, 3, df.format(dto.getEmployeeDto().getBirthday()));
        table.setText(index, 4, dto.getDepartmentName());
        table.setText(index, 5, presenter.getAllJobs().get(dto.getJob()));
        Btn editBtn = new Btn("<span class='glyphicon glyphicon-pencil'></span>", EButtonStyle.LINK);
        Btn removeBtn = new Btn("<span class='glyphicon glyphicon-remove'></span>", EButtonStyle.LINK);
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
        table.setWidget(index, 6, editBtn);
        table.setWidget(index, 7, removeBtn);
        index++;
    }

    public void reset() {
        table.removeAllRows();
    }
}
