/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.data.engine.web.internal.graphql.model;

import java.util.List;

/**
 * @author Leonardo Barros
 */
public class DataDefinitionFieldType {

	public DataDefinitionFieldType(String name, String type) {
		_name = name;
		_type = type;
	}

	public String getDefaultValue() {
		return _defaultValue;
	}

	public List<LocalizedValueType> getLabels() {
		return _labels;
	}

	public String getName() {
		return _name;
	}

	public List<LocalizedValueType> getTips() {
		return _tips;
	}

	public String getType() {
		return _type;
	}

	public boolean isIndexable() {
		return _indexable;
	}

	public boolean isLocalizable() {
		return _localizable;
	}

	public boolean isRepeatable() {
		return _repeatable;
	}

	public boolean isRequired() {
		return _required;
	}

	public void setDefaultValue(String defaultValue) {
		_defaultValue = defaultValue;
	}

	public void setIndexable(boolean indexable) {
		_indexable = indexable;
	}

	public void setLabels(List<LocalizedValueType> labels) {
		_labels = labels;
	}

	public void setLocalizable(boolean localizable) {
		_localizable = localizable;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setRepeatable(boolean repeatable) {
		_repeatable = repeatable;
	}

	public void setRequired(boolean required) {
		_required = required;
	}

	public void setTips(List<LocalizedValueType> tips) {
		_tips = tips;
	}

	public void setType(String type) {
		_type = type;
	}

	private String _defaultValue;
	private boolean _indexable = true;
	private List<LocalizedValueType> _labels;
	private boolean _localizable;
	private String _name;
	private boolean _repeatable;
	private boolean _required;
	private List<LocalizedValueType> _tips;
	private String _type;

}