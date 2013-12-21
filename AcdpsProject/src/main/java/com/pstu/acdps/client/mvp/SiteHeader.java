package com.pstu.acdps.client.mvp;

import java.util.List;

import com.pstu.acdps.shared.dto.RoleDto;

/**
 * Верхняя шапка сайта
 * 
 * @author Kasimov A.D.
 */
public interface SiteHeader {

    void setVisibleOperatorButtons(List<RoleDto> list);

    void setVisibleAdminButtons();

    void setVisibleFooterAndHeader();

    void setVisibleOperatorButtons();
}
