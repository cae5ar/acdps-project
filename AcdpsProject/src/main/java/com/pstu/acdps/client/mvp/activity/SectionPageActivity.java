package com.pstu.acdps.client.mvp.activity;

import java.util.Date;
import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.TreeItem;
import com.pstu.acdps.client.SimpleAsyncCallback;
import com.pstu.acdps.client.Site;
import com.pstu.acdps.client.components.AlertDialogBox;
import com.pstu.acdps.client.components.TreeWidget;
import com.pstu.acdps.client.components.AlertDialogBox.EAlertType;
import com.pstu.acdps.client.components.TreeWidget.ObjectsOpenHandler;
import com.pstu.acdps.client.mvp.ClientFactory;
import com.pstu.acdps.client.mvp.place.SectionPagePlace;
import com.pstu.acdps.client.mvp.presenter.SSPObjectPresenter;
import com.pstu.acdps.client.mvp.view.SSPObjectView;
import com.pstu.acdps.shared.dto.SSPObjectDto;

public class SectionPageActivity extends MainAbstractActivity implements SSPObjectPresenter {

    @SuppressWarnings("unused")
    private SectionPagePlace place;
    private SSPObjectView view;
    private String caption = "cтатьи";

    public SectionPageActivity(SectionPagePlace place, ClientFactory clientFactory) {
        this.place = place;
        this.clientFactory = clientFactory;
    }

    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        view = new SSPObjectView(caption, this);
        panel.setWidget(view);
    }

    public void deleteSSPObject(SSPObjectDto dto) {
        Site.service.removeSection(dto.getId(), new SimpleAsyncCallback<Void>() {
            public void onSuccess(Void result) {
                AlertDialogBox.showDialogBox("Отлично", "Изменения успешно сохранены", EAlertType.SUCCESS);
                view.refresh();
            }
        });
    }

    public void saveSSPObject(SSPObjectDto dto) {
        Site.service.saveSection(dto, new SimpleAsyncCallback<Long>() {
            public void onSuccess(Long result) {
                AlertDialogBox.showDialogBox("Отлично", "Изменения успешно сохранены", EAlertType.SUCCESS);
                view.refresh();
            }
        });
    }

    public TreeWidget<SSPObjectDto> getSSPObjectTree() {
        final TreeWidget<SSPObjectDto> tree = new TreeWidget<SSPObjectDto>(new SSPObjectDto(null, "Все " + caption, null, new Date()));
        tree.setOpenHandler(new ObjectsOpenHandler<SSPObjectDto>() {
            public void selected(SSPObjectDto object, final TreeItem source) {
                Site.service.getSectionChilds(object.getId(), view.getSelectedDate(), new SimpleAsyncCallback<List<SSPObjectDto>>() {
                    @Override
                    public void onSuccess(List<SSPObjectDto> result) {
                        tree.addItemList(result, source);
                    }
                });
            }
        });
        return tree;
    }

    public void getSSPObjectChilds(final TreeWidget<SSPObjectDto> tree, Date date) {
        Site.service.getSectionChilds(null, date, new SimpleAsyncCallback<List<SSPObjectDto>>() {
            @Override
            public void onSuccess(List<SSPObjectDto> result) {
                tree.addItemList(result, null);
            }
        });
    }

}
