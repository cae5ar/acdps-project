package com.pstu.acdps.client.mvp;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;

public class AboutPageView extends Composite {
    public AboutPageView() {
        StringBuilder sb = new StringBuilder();
        sb.append("<h3>Назначение АСОИУ</h3><h4>Формирование и контроль исполнения сметы предприятия<h4>");
        sb.append("<h3>Основные функции системы</h3>");
        sb.append("<ol>");
        sb.append("<h4>");
        sb.append("<li>Ведение иерархического справочника статей сметы (структура и содержимое справочника зависит от времени).</li>");
        sb.append("<li>Ведение справочника центров финансовой ответственности (далее ЦФО).</li>");
        sb.append("<li>Закрепление за каждой статьей одного или несколько ЦФО.</li>");
        sb.append("<li>Создание сметы ЦФО (плановый период – 1 год).</li>");
        sb.append("<li>Формирование единой сметы предприятия (консолидация смет ЦФО).</li>");
        sb.append("<li>Утверждение сметы и создание лимитов для каждого ЦФО.</li>");
        sb.append("<li>Создание документа «Счет на оплату» (для документа должно быть предусмотрено два состояния: новый, оплачен).</li>");
        sb.append("<li>При создание счета должны резервироваться средства из лимита, при его переходе в состояние «Оплачен», средства должны списываться. При отсутствии средств должно выдаваться предупреждение.</li>");
        sb.append("</h4>");
        sb.append("</ol>");
        sb.append("<h3>Структура системы</h3>");
        sb.append("<ol>");
        sb.append("<h4>");
        sb.append("<li>Блок справочников.</li>");
        sb.append("<li>Блок формирования смет ЦФО.</li>");
        sb.append("<li>Блок формирование сметы предприятия.</li>");
        sb.append("<li>Блок формирования счетов на оплату.</li>");
        sb.append("<li>Подсистема отчетности.</li>");
        sb.append("</h4>");
        sb.append("</ol>");
        sb.append("<h3>Справочники системы</h3>");
        sb.append("<ul>");
        sb.append("<h4>");
        sb.append("<li>статьи сметы;</li>");
        sb.append("<li>ЦФО;</li>");
        sb.append("<li>подразделения;</li>");
        sb.append("<li>сотрудники.</li>");
        sb.append("</h4>");
        sb.append("</ul>");
        sb.append("<h3>Дополнительные функции системы</h3>");
        sb.append("<ul>");
        sb.append("<h4>");
        sb.append("<li>формирование отчетов по использованию лимитов;</li>");
        sb.append("<li>администрирование; </li>");
        sb.append("<li>аудит действий пользователя и изменений значений полей таблиц.</li>");
        sb.append("</h4>");
        sb.append("</ul>");
        HTML html = new HTML(sb.toString());
        initWidget(html);
    }

}
