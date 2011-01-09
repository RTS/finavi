package com.finavi.portal.page.common;

import java.text.MessageFormat;
import java.util.Date;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.convert.converters.DateConverter;

public class MessageFormatColumn<T> extends PropertyColumn<T> {
	private final String pattern;

	public MessageFormatColumn(IModel<String> displayModel,
			String propertyExpression, String pattern) {
		super(displayModel, propertyExpression);
		this.pattern = pattern;
	}

	public MessageFormatColumn(IModel<String> displayModel,
			String sortProperty, String propertyExpression, String pattern) {
		super(displayModel, sortProperty, propertyExpression);
		this.pattern = pattern;
	}

	@Override
	protected IModel<?> createLabelModel(IModel<T> itemModel) {
		IModel<?> superModel = super.createLabelModel(itemModel);
		return new Model<String>(MessageFormat.format(pattern,
				superModel.getObject()));
	}
}