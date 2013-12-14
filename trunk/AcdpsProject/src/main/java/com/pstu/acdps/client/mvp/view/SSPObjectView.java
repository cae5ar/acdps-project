package com.pstu.acdps.client.mvp.view;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.pstu.acdps.client.components.AlertDialogBox;
import com.pstu.acdps.client.components.AlertDialogBox.EAlertType;
import com.pstu.acdps.client.components.Btn;
import com.pstu.acdps.client.components.Btn.EButtonStyle;
import com.pstu.acdps.client.components.CustomDateBox;
import com.pstu.acdps.client.components.SSPObjectEditPopup;
import com.pstu.acdps.client.components.SSPObjectEditPopup.SSPObjectSaveHandler;
import com.pstu.acdps.client.components.TreeWidget;
import com.pstu.acdps.client.mvp.presenter.SSPObjectPresenter;
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
    private SSPObjectPresenter presenter;

    public SSPObjectView(String caption, SSPObjectPresenter presenter) {
        this.presenter = presenter;
        initWidget(panel);
        panel.addStyleName("sspobject-view");
        headerPanel.addStyleName("sspobject-view-header");
        panel.add(headerPanel);
        panel.add(scroll);
        scroll.addStyleName("sspobject-view-scroll");
        contentPanel.addStyleName("content-panel object-selector");
        buildHeader(caption);
        tree = presenter.getSSPObjectTree();
        contentPanel.add(tree);
        dateBox.setValue(new Date());
        reset();
    }

    private void buildHeader(String caption) {
        header = DOM.createElement("h2");
        header.addClassName("view-caption");
        header.setInnerText(caption);
        dateBox.addStyleName("header-control");
        dateBox.setCaption("Выберите дату:");
        refreshBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                refreshTree(dateBox.getValue());
            }
        });
        addBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                SSPObjectDto dto = new SSPObjectDto();
                dto.setStartDate(selectedDate);
                SSPObjectEditPopup popup = new SSPObjectEditPopup(new SSPObjectSaveHandler() {
                    public void save(SSPObjectDto dto, SSPObjectEditPopup sender) {
                        presenter.saveSSPObject(dto);
                        sender.hide();
                    }
                }, dto, presenter);
                popup.show();
            }
        });
        refreshBtn.addStyleName("header-control");
        addBtn.addStyleName("header-control");
        editBtn.addStyleName("header-control");
        editBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (tree.getSelectedNode() != null) {
                    SSPObjectEditPopup popup = new SSPObjectEditPopup(new SSPObjectSaveHandler() {
                        public void save(SSPObjectDto dto, SSPObjectEditPopup sender) {
                            presenter.saveSSPObject(dto);
                            sender.hide();
                        }
                    }, tree.getSelectedNode(), presenter);
                    popup.show();
                }
                else {
                    AlertDialogBox.showDialogBox("Выберите объект для изменения");
                }
            }
        });
        removeBtn.addStyleName("header-control");
        removeBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (tree.getSelectedNode() != null) {
                    presenter.deleteSSPObject(tree.getSelectedNode());
                }
                else {
                    AlertDialogBox.showDialogBox("Выберите объект для удаления");
                }
            }
        });
        headerPanel.getElement().appendChild(header);
        headerPanel.add(refreshBtn);
        headerPanel.add(dateBox);
        headerPanel.add(removeBtn);
        headerPanel.add(editBtn);
        headerPanel.add(addBtn);
    }

    private void refreshTree(Date date) {
        if (date == null) {
            AlertDialogBox.showDialogBox("Дата не указана", "", EAlertType.WARNING);
            return;
        }
        tree.reset();
        selectedDate = date;
        presenter.getSSPObjectChilds(tree,date);
    }

    public void reset() {
        refreshTree(new Date());
    }

    public void clearContent() {
        contentPanel.clear();
    }

    public void refresh() {
        refreshTree(selectedDate);
    }

    public Date getSelectedDate() {
        return selectedDate;
    }
}
