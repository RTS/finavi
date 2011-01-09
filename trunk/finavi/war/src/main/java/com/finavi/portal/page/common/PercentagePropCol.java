package com.finavi.portal.page.common;

import java.util.Date;

import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

class PercentagePropCol extends PropertyColumn {
	private static final long serialVersionUID = -2026659159054172652L;

	public PercentagePropCol(IModel displayModel, String propertyExpression) {
		super(displayModel, propertyExpression);
	}

	IModel createlabelmodel(IModel rowmodel) {
		final IModel prop = super.createLabelModel(rowmodel);
		return new AbstractReadOnlyModel<Object>() {
			public Object getObject() {
				Date date = (Date) prop.getObject();
				String str = "";
				return str;
			}
		};
	}
}