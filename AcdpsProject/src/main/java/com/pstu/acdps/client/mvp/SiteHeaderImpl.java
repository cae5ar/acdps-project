package com.pstu.acdps.client.mvp;

import java.util.List;

import com.pstu.acdps.client.Site;
import com.pstu.acdps.shared.dto.RoleDto;
import com.pstu.acdps.shared.type.SystemConstants;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class SiteHeaderImpl implements SiteHeader {

    private JQuery logoutLink;

    public SiteHeaderImpl() {
        logoutLink = JQuery.select("#logout-link");
        logoutLink.click(new EventHandler() {
            @Override
            public void eventComplete(JQEvent event, JQuery currentJQuery) {
                Site.logout();
            }
        });

    }

    public void setVisibleAdminButtons() {
        JQuery.select("#admin-button").removeClass("hide");
    }

    @Override
    public void setVisibleOperatorButtons(List<RoleDto> list) {
        for (RoleDto dto : list) {
            if (dto.getIdent().equals(SystemConstants.roleDirectoryIdent)) {
                JQuery.select("#" + SystemConstants.roleDirectoryIdent).removeClass("hide");
            }
            if (dto.getIdent().equals(SystemConstants.roleReportIdent)) {
                JQuery.select("#" + SystemConstants.roleReportIdent).removeClass("hide");
            }
            if (dto.getIdent().equals(SystemConstants.roleEstimateIdent)) {
                JQuery.select("#DOCUMENTS").removeClass("hide");
                JQuery.select("#" + SystemConstants.roleEstimateIdent).removeClass("hide");
            }
            if (dto.getIdent().equals(SystemConstants.rolePaymentIdent)) {
                JQuery.select("#DOCUMENTS").removeClass("hide");
                JQuery.select("#" + SystemConstants.rolePaymentIdent).removeClass("hide");
            }
        }
    }

    @Override
    public void setVisibleFooterAndHeader() {
        JQuery.select("#footer").removeClass("hide");
        JQuery.select("#site-navbar").removeClass("hide");
    }

    @Override
    public void setVisibleOperatorButtons() {
        JQuery.select("#operator-buttons li.hide").removeClass("hide");
    }
}
