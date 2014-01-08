package com.pstu.acdps.client.components;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.pstu.acdps.client.components.Btn.EButtonStyle;
import com.pstu.acdps.client.components.TreeWidget.ObjectsSelectHandler;
import com.pstu.acdps.shared.dto.CfoDto;
import com.pstu.acdps.shared.dto.SectionDto;

public class CfoEditPopup extends CustomPopup {

    public interface CfoSaveHandler {
        public void save(CfoDto dto, CustomPopup sender);
    }

    private FlowPanel panel = new FlowPanel();
    private ScrollPanel scroll = new ScrollPanel(panel);
    private CfoSaveHandler handler = null;
    private CfoDto dto;
    private CustomTextBox name;
    private TreeWidget<SectionDto> tree;
    private CfoSectionsTable sectionsTable;
    private Btn addSectionBtn = new Btn("Добавить", EButtonStyle.PRIMARY, new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
            if (tree.getSelectedNode() != null && tree.getSelectedNode().getId() != null) {
                sectionsTable.addCfoSection(dto, tree.getSelectedNode());
            }
        }
    });
    private Btn cancel = new Btn("Отменить", EButtonStyle.DEFAULT, new ClickHandler() {
        public void onClick(ClickEvent event) {
            CfoEditPopup.this.hide();
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

    public CfoEditPopup(CfoSaveHandler handler, CfoDto dto, TreeWidget<SectionDto> tree) {
        super();
        addStyleName("cfo-edit-popup");
        this.dto = dto;
        this.tree = tree;
        tree.setSelectHandler(new ObjectsSelectHandler<SectionDto>() {
            @Override
            public void selected(SectionDto object) {
                setEnableAddSectionButton(object);
            }
        });
        setHandler(handler);
        setHeader("Редактирование ЦФО");
        scroll.addStyleName("content-scroll");
        modalBody.add(scroll);
        modalFooter.addStyleName("text-left");
        modalFooter.add(saveBtn);
        modalFooter.add(cancel);
        createInputs();
    }

    private void createInputs() {
        name = new CustomTextBox();
        name.addLabelStyleName("label-left");
        name.addInputStyleName("horizontal-input");
        name.setValue(dto.getName());
        name.setCaption("Наименование:");
        name.getTextBox().addKeyDownHandler(enterPressHandler);
        panel.add(name);
        name.setFocus();
        Label allSectionText = new Label("Статьи привязанные к ЦФО:");
        panel.add(allSectionText);
        sectionsTable = new CfoSectionsTable();
        sectionsTable.addCfoSectionList(dto.getSectionCfo());
        panel.add(sectionsTable);

        DisclosurePanel dp = new DisclosurePanel();
        FlowPanel dpContent = new FlowPanel();
        dpContent.addStyleName("text-right");
        ScrollPanel treeWrap = new ScrollPanel(tree);
        treeWrap.addStyleName("tree-scroll-wrap object-selector text-left");
        dpContent.add(treeWrap);
        dpContent.add(addSectionBtn);
        addSectionBtn.setEnabled(false);
        dp.setStyleName("disclosure-panel");
        HTML headerWidget = new HTML("<span class='glyphicon glyphicon-plus' style='font-size:15px'></span><span > Добавить статью в ЦФО </span>");
        headerWidget.addStyleName("header-text");
        dp.setHeader(headerWidget);
        dp.setContent(dpContent);
        panel.add(dp);
    }

    private void saveAllChanges() {
        if (handler != null) {
            boolean commit = true;
            if (name.getValue() == null || name.getValue().isEmpty()) {
                commit = false;
                AlertDialogBox.showDialogBox("Поле 'Наименование' не может быть пустым");
            }
            if (sectionsTable.getSectionCfoList() == null) {
                commit = false;
                AlertDialogBox.showDialogBox("В добавленных статьях указаны неверные временные диапазоны");
            }
            if (commit) {
                dto.setName(name.getValue());
                dto.setSectionCfo(sectionsTable.getSectionCfoList());
                handler.save(dto, CfoEditPopup.this);
            }
        }
    }

    private void setEnableAddSectionButton(SectionDto object) {
        if (object != null && object.getId() != null) {
            addSectionBtn.setEnabled(true);
        }
        else {
            addSectionBtn.setEnabled(false);
        }
    }

    public void setHandler(CfoSaveHandler handler) {
        this.handler = handler;
    }

}
