package com.pstu.acdps.client.mvp.view;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.pstu.acdps.client.components.Btn;
import com.pstu.acdps.client.components.Btn.EButtonStyle;
import com.pstu.acdps.client.components.CfoTable;
import com.pstu.acdps.client.mvp.presenter.CfoPagePresenter;
import com.pstu.acdps.client.type.ActionType;
import com.pstu.acdps.shared.dto.CfoDto;

public class CfoPageView extends Composite {
    private FlowPanel panel = new FlowPanel();
    private FlowPanel headerPanel = new FlowPanel();
    private Element header;
    private FlowPanel contentPanel = new FlowPanel();
    private ScrollPanel scroll = new ScrollPanel(contentPanel);
    private CfoPagePresenter presenter;
    private CfoTable table;
    private Btn addBtn = new Btn("<span class='glyphicon glyphicon-plus'></span><span> Создать новый ЦФО</span>", EButtonStyle.DEFAULT, new ClickHandler() {
        public void onClick(ClickEvent event) {
            if (presenter.getActionHandler() != null) {
                presenter.getActionHandler().doAction(ActionType.EDIT, null);
            }
        }
    });

    public CfoPageView() {
        initWidget(panel);
        table = new CfoTable();
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
        header.setInnerText("центры финансовой отчетности");
        addBtn.addStyleName("header-control");
        headerPanel.getElement().appendChild(header);
        headerPanel.add(addBtn);
    }

    public void setItems(List<CfoDto> dtoList) {
        table.addCfoList(dtoList);
    }

    public void reset() {
        table.reset();
        presenter.loadAllCfo();
    }

    public void setPresenter(CfoPagePresenter presenter) {
        this.presenter = presenter;
        table.setPresenter(presenter);
    }

}
