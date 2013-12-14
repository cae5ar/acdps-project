package com.pstu.acdps.client.mvp.activity;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.pstu.acdps.client.components.Btn;
import com.pstu.acdps.client.components.Btn.EButtonStyle;
import com.pstu.acdps.client.mvp.presenter.EmployeesPresenter;


public class EmployeesView extends Composite {
    
    private FlowPanel panel = new FlowPanel();
    private FlowPanel headerPanel = new FlowPanel();
    private Element header;
    private FlowPanel contentPanel = new FlowPanel();
    private ScrollPanel scroll = new ScrollPanel(contentPanel);
    private EmployeesPresenter presenter;
    private EmpoyeesTable table = new EmpoyeesTable();
    private Btn addBtn = new Btn("<span class='glyphicon glyphicon-plus'></span><span> Добавить сотрудника</span>", EButtonStyle.DEFAULT);
    
    public EmployeesView(EmployeesPresenter presenter) {
        this.presenter = presenter;
        initWidget(panel);
        panel.addStyleName("sspobject-view");
        headerPanel.addStyleName("sspobject-view-header");
        panel.add(headerPanel);
        panel.add(scroll);
        scroll.addStyleName("sspobject-view-scroll");
        contentPanel.addStyleName("content-panel");
        buildHeader();
        contentPanel.add(table);
    }
    
    private void buildHeader() {
        header = DOM.createElement("h2");
        header.addClassName("view-caption");
        header.setInnerText("сотрудники");
        addBtn.addStyleName("header-control");
        headerPanel.getElement().appendChild(header);
        headerPanel.add(addBtn);
    }
}
