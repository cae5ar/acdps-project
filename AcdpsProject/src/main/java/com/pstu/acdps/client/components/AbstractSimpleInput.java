package com.pstu.acdps.client.components;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

/***
 * Bootstrap Horizontal Form
 * 
 * @author Kasimov A.D.
 */
public abstract class AbstractSimpleInput extends Composite {

    protected FlowPanel panel = new FlowPanel();
    protected FlowPanel controlGroup = new FlowPanel();
    protected Element label = DOM.createLabel();
    protected Element inputWrap = DOM.createSpan();
    protected Element helpInline = DOM.createSpan();

    public AbstractSimpleInput() {
        initWidget(panel);
        addStyleName("form-horizontal");
        controlGroup.addStyleName("form-group");

        label.addClassName("control-label");
        controlGroup.getElement().appendChild(label);

        helpInline.addClassName("help-block");
        panel.add(controlGroup);
    }

    public void setCaption(String tagName) {
        label.setInnerText(tagName);
    }

    public void setHelpBlock(String html) {
        controlGroup.getElement().appendChild(helpInline);
        helpInline.setInnerHTML(html);
    }
    public void setHelpBlock(Element el) {
        controlGroup.getElement().appendChild(helpInline);
        helpInline.appendChild(el);
    }

    protected void wrapInput(Widget input) {
        controlGroup.add(input);
        input.addStyleName("form-control");
    }

    public void setErrorStyle(boolean isError) {
        if (isError) {
            if (!controlGroup.getStyleName().contains("has-error")) controlGroup.addStyleName("has-error");
        }
        else {
            controlGroup.removeStyleName("has-error");
        }
    }

    public void addLabelStyleName(String style) {
        label.addClassName(style);
    }

    public void removeLabelStyleName(String style) {
        label.removeClassName(style);
    }

    public abstract void addInputStyleName(String style);
}
