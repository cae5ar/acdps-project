package com.pstu.acdps.client;

import java.util.Date;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TreeItem;
import com.pstu.acdps.client.components.AlertDialogBox;
import com.pstu.acdps.client.components.AlertDialogBox.EAlertType;
import com.pstu.acdps.client.components.Btn;
import com.pstu.acdps.client.components.Btn.EButtonStyle;
import com.pstu.acdps.client.components.CustomDateBox;
import com.pstu.acdps.client.components.SSPObjectEditPopup;
import com.pstu.acdps.client.components.SSPObjectEditPopup.SSPObjectSaveHandler;
import com.pstu.acdps.client.components.TreeWidget;
import com.pstu.acdps.client.components.TreeWidget.ObjectsOpenHandler;
import com.pstu.acdps.shared.dto.SSPObjectDto;

public class SSPObjectView extends Composite {
    private FlowPanel panel = new FlowPanel();
    private FlowPanel headerPanel = new FlowPanel();
    private CustomDateBox dateBox = new CustomDateBox();
    private Element header;
    private FlowPanel contentPanel = new FlowPanel();
    private Btn refreshBtn = new Btn("<span class='glyphicon glyphicon-refresh'></span>", EButtonStyle.DEFAULT);
    private Btn addBtn = new Btn("<span class='glyphicon glyphicon-plus'></span>", EButtonStyle.DEFAULT);
    private Btn editBtn = new Btn("<span class='glyphicon glyphicon-pencil'></span>", EButtonStyle.DEFAULT);
    private Btn removeBtn = new Btn("<span class='glyphicon glyphicon-remove'></span>", EButtonStyle.DEFAULT);
    private ScrollPanel scroll = new ScrollPanel(contentPanel);
    private TreeWidget<SSPObjectDto> tree;
    private Date selectedDate;

    public SSPObjectView() {
        initWidget(panel);
        panel.addStyleName("sspobject-view");
        headerPanel.addStyleName("sspobject-view-header");
        panel.add(headerPanel);
        panel.add(scroll);
        scroll.addStyleName("sspobject-view-scroll");
        contentPanel.addStyleName("content-panel object-selector");
        buildHeader();
        tree = new TreeWidget<SSPObjectDto>(new ObjectsOpenHandler<SSPObjectDto>() {
            public void selected(SSPObjectDto object, final TreeItem source) {
                Site.service.getDepartmentChilds(object.getId(), selectedDate, new SimpleAsyncCallback<List<SSPObjectDto>>() {
                    @Override
                    public void onSuccess(List<SSPObjectDto> result) {
                        tree.addItemList(result, source);
                    }
                });
            }
        });
        contentPanel.add(tree);
        dateBox.setValue(new Date());
        reset();
    }

    private void buildHeader() {
        header = DOM.createElement("h2");
        header.addClassName("view-caption");
        header.setInnerText("тут шапка");
        dateBox.addStyleName("header-control");
        dateBox.setCaption("Выберите дату:");
        refreshBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                refreshTree(null, dateBox.getValue());
            }
        });
        addBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                SSPObjectEditPopup popup = new SSPObjectEditPopup(new SSPObjectSaveHandler() {
                    public void save(SSPObjectDto dto, SSPObjectEditPopup sender) {}
                }, null);
                popup.show();
            }
        });
        refreshBtn.addStyleName("header-control");
        addBtn.addStyleName("header-control");
        editBtn.addStyleName("header-control");
        removeBtn.addStyleName("header-control");
        headerPanel.getElement().appendChild(header);
        headerPanel.add(refreshBtn);
        headerPanel.add(dateBox);
        headerPanel.add(removeBtn);
        headerPanel.add(editBtn);
        headerPanel.add(addBtn);
    }

    private void refreshTree(Long parentId, Date date) {
        if (date == null) {
            AlertDialogBox.showDialogBox("Дата не указана", "", EAlertType.WARNING);
            return;
        }
        tree.reset();
        selectedDate = date;
        Site.service.getDepartmentChilds(null, selectedDate, new SimpleAsyncCallback<List<SSPObjectDto>>() {
            @Override
            public void onSuccess(List<SSPObjectDto> result) {
                tree.addItemList(result, null);
            }
        });
    }

    public void reset() {
        refreshTree(null, new Date());
    }

    public SSPObjectView(String caption) {
        this();
        header.setInnerHTML(caption);
    }

    public void clearContent() {
        contentPanel.clear();
    }
}
