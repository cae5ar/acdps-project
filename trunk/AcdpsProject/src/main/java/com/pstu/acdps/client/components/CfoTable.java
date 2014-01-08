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
import com.pstu.acdps.client.mvp.presenter.CfoPagePresenter;
import com.pstu.acdps.client.type.ActionType;
import com.pstu.acdps.shared.dto.CfoDto;

public class CfoTable extends Composite {
    private FlowPanel panel = new FlowPanel();
    private FlexTable table = new FlexTable();
    private int index = 0;
    private CfoPagePresenter presenter;

    public CfoTable() {
        initWidget(panel);
        table.addStyleName("table table-striped");
        panel.add(table);
        createThead();
    }

    private void createThead() {
        Element thead = DOM.createTHead();
        Element tr = DOM.createTR();
        Element th = DOM.createTH();
        th.setInnerText("Наименование ЦФО");
        th.addClassName("col-xs-10");
        tr.appendChild(th);
        th = DOM.createTH();
        th.setInnerHTML("<span class='glyphicon glyphicon-pencil' style='margin: 6px 12px'></span>");
        th.addClassName("col-xs-1");
        tr.appendChild(th);
        th = DOM.createTH();
        th.setInnerHTML("<span class='glyphicon glyphicon-remove' style='margin: 6px 12px'></span>");
        th.addClassName("col-xs-1");
        tr.appendChild(th);
        thead.appendChild(tr);
        Element tbody = table.getElement().getElementsByTagName("tbody").getItem(0);
        tbody.removeFromParent();
        table.getElement().appendChild(thead);
        table.getElement().appendChild(tbody);
    }

    public void addCfoList(List<CfoDto> dtoList) {
        if (dtoList != null) {
            for (CfoDto cfo : dtoList) {
                addCfo(cfo);
            }
        }
    }

    public void addCfo(final CfoDto dto) {
        table.setHTML(index, 0, dto.getName());
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
        table.setWidget(index, 1, editBtn);
        table.setWidget(index, 2, removeBtn);
        index++;
    }

    public void reset() {
        table.removeAllRows();
        index = 0;
    }

    public void setPresenter(CfoPagePresenter presenter) {
        this.presenter = presenter;
    }

}
