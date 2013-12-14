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
        }, new SSPObjectDto(null, "Все подразделения", null, new Date()));
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
                refreshTree(dateBox.getValue());
            }
        });
        addBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                    SSPObjectDto dto = new SSPObjectDto();
                    SSPObjectEditPopup popup = new SSPObjectEditPopup(new SSPObjectSaveHandler() {
                        public void save(SSPObjectDto dto, SSPObjectEditPopup sender) {
                            saveSSPObject(dto);
                            sender.hide();
                        }
                    }, dto, selectedDate);
                    popup.show();
                }
            }
        );
        refreshBtn.addStyleName("header-control");
        addBtn.addStyleName("header-control");
        editBtn.addStyleName("header-control");
        editBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (tree.getSelectedNode() != null) {
                    SSPObjectEditPopup popup = new SSPObjectEditPopup(new SSPObjectSaveHandler() {
                        public void save(SSPObjectDto dto, SSPObjectEditPopup sender) {
                            saveSSPObject(dto);
                            sender.hide();
                        }
                    }, tree.getSelectedNode(),selectedDate);
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
                    deleteSSPObject(tree.getSelectedNode());
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

    protected void deleteSSPObject(SSPObjectDto dto) {
        Site.service.removeDepartment(dto.getId(), new SimpleAsyncCallback<Void>() {
            public void onSuccess(Void result) {
                AlertDialogBox.showDialogBox("Отлично", "Изменения успешно сохранены", EAlertType.SUCCESS);
                refreshTree(selectedDate);
            }
        });
    }

    private void saveSSPObject(SSPObjectDto dto) {
        Site.service.saveDepartment(dto, new SimpleAsyncCallback<Long>() {
            public void onSuccess(Long result) {
                AlertDialogBox.showDialogBox("Отлично", "Изменения успешно сохранены", EAlertType.SUCCESS);
                refreshTree(selectedDate);
            }
        });
    }

    private void refreshTree(Date date) {
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
        refreshTree(new Date());
    }

    public SSPObjectView(String caption) {
        this();
        header.setInnerHTML(caption);
    }

    public void clearContent() {
        contentPanel.clear();
    }
}
