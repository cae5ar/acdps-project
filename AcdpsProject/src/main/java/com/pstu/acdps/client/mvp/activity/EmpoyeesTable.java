package com.pstu.acdps.client.mvp.activity;

import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.pstu.acdps.shared.dto.EmployeeDto;

public class EmpoyeesTable extends Composite {
    FlowPanel panel = new FlowPanel();
    private FlexTable table = new FlexTable();
   
    public EmpoyeesTable() {
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
        th.setInnerHTML("<span class='glyphicon glyphicon-pencil' style='margin: 6px 12px'></span>");
        th.addClassName("col-xs-1");
        tr.appendChild(th);
        th = DOM.createTH();
        th.setInnerHTML("<span class='glyphicon glyphicon-minus' style='margin: 6px 12px'></span>");
        tr.appendChild(th);
        th.addClassName("col-xs-1");
        thead.appendChild(tr);
        Element tbody = table.getElement().getElementsByTagName("tbody").getItem(0);
        tbody.removeFromParent();
        table.getElement().appendChild(thead);
        table.getElement().appendChild(tbody);
    }

    public void addEmployeeList(List<EmployeeDto> dto) {

    }

    public void addEmployee(EmployeeDto dto) {

    }
}
