package com.pstu.acdps.client.components;

import java.util.Date;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.pstu.acdps.shared.dto.DateRange;

public class CustomDateBox extends AbstractSimpleInput {

    private DateBox leftDate = new DateBox();
    private DateBox rightDate = new DateBox();
    private FlowPanel valuePanel = new FlowPanel();
    private DateTimeFormat df = DateTimeFormat.getFormat("dd.MM.yyyy");

    public CustomDateBox(Boolean isPeriod) {
        inputWrap.removeFromParent();
        helpInline.removeFromParent();
        valuePanel.add(leftDate);
        if (isPeriod) {
            Element span = DOM.createSpan();
            span.setInnerText(" по ");
            valuePanel.getElement().appendChild(span);
            valuePanel.add(rightDate);
        }
        controlGroup.add(valuePanel);
        leftDate.setFormat(new DateBox.DefaultFormat(getDateFormat()));
        rightDate.setFormat(new DateBox.DefaultFormat(getDateFormat()));
        valuePanel.getElement().getStyle().setDisplay(Display.INLINE);
    }

    public CustomDateBox() {
        this(false);
    }

    public Date getValue() {
        return leftDate.getValue();
    }

    public void setValue(Date d) {
        setLeftValue(d);
    }

    public void setLeftValue(String strValue) {
        Date date = null;
        if (strValue != null && !strValue.isEmpty()) {
            date = getDateFormat().parse(strValue);
        }
        setLeftValue(date);
    }

    public void setRightValue(String strValue) {
        Date date = null;
        if (strValue != null && !strValue.isEmpty()) {
            date = getDateFormat().parse(strValue);
        }
        setRightValue(date);
    }

    public void setLeftValue(Date date) {
        leftDate.setValue(date);
    }

    public void setRightValue(Date date) {
        rightDate.setValue(date);
    }

    public void addInputStyleName(String style) {
        valuePanel.addStyleName(style);
    }

    public void setPeriodValue(DateRange period) {
        if (period != null) {
            setLeftValue(period.getLeftDate());
            setRightValue(period.getRightDate());
        }
        else {
            setLeftValue("");
            setRightValue("");
        }
    }

    public DateTimeFormat getDateFormat() {
        return df;
    }

    public void setDateFormat(DateTimeFormat df) {
        this.df = df;
    }
    
}
