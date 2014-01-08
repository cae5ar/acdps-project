package com.pstu.acdps.client.mvp;

import java.util.Date;
import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.TreeItem;
import com.pstu.acdps.client.SimpleAsyncCallback;
import com.pstu.acdps.client.Site;
import com.pstu.acdps.client.components.AlertDialogBox;
import com.pstu.acdps.client.components.CfoEditPopup;
import com.pstu.acdps.client.components.AlertDialogBox.EAlertType;
import com.pstu.acdps.client.components.CfoEditPopup.CfoSaveHandler;
import com.pstu.acdps.client.components.CustomPopup;
import com.pstu.acdps.client.components.TreeWidget;
import com.pstu.acdps.client.components.TreeWidget.ObjectsOpenHandler;
import com.pstu.acdps.client.mvp.activity.MainAbstractActivity;
import com.pstu.acdps.client.mvp.place.CfoPagePlace;
import com.pstu.acdps.client.mvp.presenter.CfoPagePresenter;
import com.pstu.acdps.client.mvp.view.CfoPageView;
import com.pstu.acdps.client.type.ActionType;
import com.pstu.acdps.shared.dto.CfoDto;
import com.pstu.acdps.shared.dto.SectionDto;
import com.pstu.acdps.shared.type.SystemConstants;

public class CfoPageActivity extends MainAbstractActivity implements CfoPagePresenter {

    @SuppressWarnings("unused")
    private CfoPagePlace place;
    private CfoPageView view;
    private TreeWidget<SectionDto> tree = new TreeWidget<SectionDto>(new ObjectsOpenHandler<SectionDto>() {
        public void selected(SectionDto object, final TreeItem source) {
            Site.service.getSectionChilds(object.getId(), new Date(), new SimpleAsyncCallback<List<SectionDto>>() {
                @Override
                public void onSuccess(List<SectionDto> result) {
                    tree.addItemList(result, source);
                }
            });
        }
    }, new SectionDto(null, "Все статьи","", null, new Date()));

    private ActionHandler hanlder = new ActionHandler() {
        @Override
        public void doAction(ActionType actionType, CfoDto dto) {
            switch (actionType) {
                case EDIT:
                    editCfo(dto);
                    break;
                case REMOVE:
                    removeCfo(dto.getId());
                    break;
            }
        }
    };

    public CfoPageActivity(CfoPagePlace place, ClientFactory clientFactory) {
        this.place = place;
        this.clientFactory = clientFactory;
    }

    @Override
    public void start(final AcceptsOneWidget container, EventBus eventBus) {
        if (!Site.hasUserRole(SystemConstants.roleDirectoryIdent)) {
            container.setWidget(clientFactory.getAccessDeniedView());
        }
        else {
            view = new CfoPageView();
            view.setPresenter(this);
            view.reset();
            container.setWidget(view);
        }
    }

    @Override
    public ActionHandler getActionHandler() {
        return hanlder;
    }

    @Override
    public void loadAllCfo() {
        Site.service.getAllCfo(new SimpleAsyncCallback<List<CfoDto>>() {
            public void onSuccess(List<CfoDto> result) {
                view.setItems(result);
            }
        });
    }

    protected void removeCfo(Long id) {
        Site.service.removeCfo(id, new SimpleAsyncCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                AlertDialogBox.showDialogBox("Изменения успешно сохранены", EAlertType.SUCCESS);
                view.reset();
            }
        });
    }

    protected void editCfo(CfoDto dto) {
        if (dto == null) {
            dto = new CfoDto();
        }
        CfoEditPopup popup = new CfoEditPopup(new CfoSaveHandler() {
            public void save(CfoDto dto, final CustomPopup sender) {
                Site.service.saveCfo(dto, new SimpleAsyncCallback<Long>() {
                    @Override
                    public void onSuccess(Long result) {
                        AlertDialogBox.showDialogBox("Изменения успешно сохранены", EAlertType.SUCCESS);
                        sender.hide();
                        view.reset();
                    }
                });
            }
        }, dto, tree);
        popup.show();

    }

}
