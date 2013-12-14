package com.pstu.acdps.client.components;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasTreeItems;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.pstu.acdps.shared.dto.HasChild;

public class TreeWidget<E extends HasChild> extends Composite {
    private Map<TreeItem, E> nodes = new HashMap<TreeItem, E>();
    private HandlerRegistration addOpenHandler;
    private HandlerRegistration addSelectHandler;
    private Tree dynamicTree;
    private HasTreeItems parentNode;
    private E fakeParent;

    public interface ObjectsOpenHandler<E> {
        public void selected(E object, TreeItem source);
    }

    public interface ObjectsSelectHandler<E> {
        public void selected(E object);
    }

    public TreeWidget() {
        dynamicTree = new Tree();
        initWidget(dynamicTree);
        dynamicTree.setStyleName("object-groups-tree");
    }

    public TreeWidget(final ObjectsOpenHandler<E> handler, E fakeParent) {
        this.fakeParent = fakeParent;
        dynamicTree = new Tree();
        initWidget(dynamicTree);
        dynamicTree.setStyleName("object-groups-tree");
        setOpenHandler(handler);
        if (fakeParent != null) {
            TreeItem node = dynamicTree.addTextItem(fakeParent.getNodeName());
            node.setStyleName("object-node");
            node.addStyleName("has-child");
            node.addTextItem("ЗАГРУЗКА СПИСКА...");
            nodes.put(node, fakeParent);
            parentNode = node;
        }
        else {
            parentNode = dynamicTree;
        }
    }

    public void setOpenHandler(final ObjectsOpenHandler<E> handler) {
        if (addOpenHandler != null) addOpenHandler.removeHandler();
        addOpenHandler = dynamicTree.addOpenHandler(new OpenHandler<TreeItem>() {
            public void onOpen(OpenEvent<TreeItem> event) {
                TreeItem item = event.getTarget();
                item.setState(false, false);
                if (item.getChildCount() == 1 && !nodes.containsKey(item.getChild(0))) {
                    handler.selected(nodes.get(item), item);
                }
                item.setState(true, false);
            }
        });
    }

    public void setSelectHandler(final ObjectsSelectHandler<E> handler) {
        if (addSelectHandler != null) addSelectHandler.removeHandler();
        addSelectHandler = dynamicTree.addSelectionHandler(new SelectionHandler<TreeItem>() {
            public void onSelection(SelectionEvent<TreeItem> event) {
                TreeItem item = event.getSelectedItem();
                handler.selected(nodes.get(item));
            }
        });
    }

    public void addItemList(List<E> list, HasTreeItems parent) {
        if (parent == null) {
            parent = parentNode;
        }
        else {
            ((TreeItem) parent).getChild(0).remove();
        }
        for (E e : list) {
            addItemNode(e, parent);
        }
    }

    public void addItemNode(E e, HasTreeItems parent) {
        if (parent == null) {
            parent = parentNode;
        }
        TreeItem child = parent.addTextItem(e.getNodeName());
        child.setStyleName("object-node");
        if (e.hasChild()) {
            child.addStyleName("has-child");
            child.addTextItem("ЗАГРУЗКА СПИСКА...");
        }
        else {
            child.addStyleName("is-layer-node");
        }
        nodes.put(child, e);
    }

    public E getSelectedNode() {
        TreeItem selectedItem = dynamicTree.getSelectedItem();
        return nodes.get(selectedItem);
    }

    public void reset() {
        parentNode.removeItems();
        nodes.clear();
        nodes.put((TreeItem) parentNode, fakeParent);
    }
}
