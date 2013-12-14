package com.pstu.acdps.client.mvp.presenter;

import java.util.Date;

import com.pstu.acdps.client.components.TreeWidget;
import com.pstu.acdps.shared.dto.SSPObjectDto;

public interface SSPObjectPresenter {
    
    public void deleteSSPObject(SSPObjectDto dto);

    public void saveSSPObject(SSPObjectDto dto);

    public TreeWidget<SSPObjectDto> getSSPObjectTree();

    public void getSSPObjectChilds(TreeWidget<SSPObjectDto> tree, Date date);
}
