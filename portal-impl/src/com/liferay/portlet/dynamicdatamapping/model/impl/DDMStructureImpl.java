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

package com.liferay.portlet.dynamicdatamapping.model.impl;

import com.liferay.portal.LocaleException;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PredicateFilter;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Attribute;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.kernel.xml.XPath;
import com.liferay.portal.model.CacheField;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.dynamicdatamapping.StructureFieldException;
import com.liferay.portlet.dynamicdatamapping.model.DDMForm;
import com.liferay.portlet.dynamicdatamapping.model.DDMFormField;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.util.DDMFormXSDDeserializerUtil;
import com.liferay.portlet.dynamicdatamapping.util.DDMFormXSDSerializerUtil;
import com.liferay.portlet.dynamicdatamapping.util.DDMXMLUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Brian Wing Shun Chan
 */
public class DDMStructureImpl extends DDMStructureBaseImpl {

	@Override
	public String[] getAvailableLanguageIds() {
		DDMForm ddmForm = getDDMForm();

		List<Locale> availableLocales = ddmForm.getAvailableLocales();

		String[] availableLanguageIds = new String[availableLocales.size()];

		for (int i = 0; i < availableLocales.size(); i++) {
			availableLanguageIds[i] = LocaleUtil.toLanguageId(
				availableLocales.get(i));
		}

		return availableLanguageIds;
	}

	@Override
	public List<String> getChildrenFieldNames(String fieldName)
		throws PortalException {

		List<String> fieldNames = new ArrayList<String>();

		Map<String, Map<String, String>> fieldsMap = getFieldsMap(true);

		for (Map<String, String> field : fieldsMap.values()) {
			String parentNameKey = _getPrivateAttributeKey("parentName");

			String parentName = field.get(parentNameKey);

			if (fieldName.equals(parentName)) {
				fieldNames.add(field.get("name"));
			}
		}

		return fieldNames;
	}

	@Override
	public DDMForm getDDMForm() {
		if (_ddmForm == null) {
			try {
				_ddmForm = DDMFormXSDDeserializerUtil.deserialize(getXsd());
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		return _ddmForm;
	}

	@Override
	public String getDefaultLanguageId() {
		DDMForm ddmForm = getDDMForm();

		return LocaleUtil.toLanguageId(ddmForm.getDefaultLocale());
	}

	@Override
	public String getFieldDataType(String fieldName) throws PortalException {
		return getFieldProperty(fieldName, "dataType");
	}

	@Override
	public String getFieldLabel(String fieldName, Locale locale)
		throws PortalException {

		return getFieldLabel(fieldName, LocaleUtil.toLanguageId(locale));
	}

	@Override
	public String getFieldLabel(String fieldName, String locale)
		throws PortalException {

		return GetterUtil.getString(
			getFieldProperty(fieldName, "label", locale), fieldName);
	}

	@Override
	public Set<String> getFieldNames() throws PortalException {
		Map<String, Map<String, String>> fieldsMap = getFieldsMap();

		return fieldsMap.keySet();
	}

	@Override
	public String getFieldProperty(String fieldName, String property)
		throws PortalException {

		return getFieldProperty(fieldName, property, getDefaultLanguageId());
	}

	@Override
	public String getFieldProperty(
			String fieldName, String property, String locale)
		throws PortalException {

		if (!hasField(fieldName)) {
			throw new StructureFieldException();
		}

		Map<String, Map<String, String>> fieldsMap = getFieldsMap(locale, true);

		Map<String, String> field = fieldsMap.get(fieldName);

		return field.get(property);
	}

	@Override
	public boolean getFieldRepeatable(String fieldName) throws PortalException {
		return GetterUtil.getBoolean(getFieldProperty(fieldName, "repeatable"));
	}

	@Override
	public boolean getFieldRequired(String fieldName) throws PortalException {
		return GetterUtil.getBoolean(getFieldProperty(fieldName, "required"));
	}

	@Override
	public Map<String, String> getFields(
		String fieldName, String attributeName, String attributeValue) {

		return getFields(
			fieldName, attributeName, attributeValue, getDefaultLanguageId());
	}

	@Override
	public Map<String, String> getFields(
		String fieldName, String attributeName, String attributeValue,
		String locale) {

		try {
			if ((attributeName == null) || (attributeValue == null)) {
				return null;
			}

			Map<String, Map<String, String>> fieldsMap = getTransientFieldsMap(
				locale);

			for (Map<String, String> fields : fieldsMap.values()) {
				String parentName = fields.get(
					_getPrivateAttributeKey("parentName"));

				if (!fieldName.equals(parentName)) {
					continue;
				}

				if (attributeValue.equals(fields.get(attributeName))) {
					return fields;
				}
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return null;
	}

	@Override
	public Map<String, Map<String, String>> getFieldsMap()
		throws PortalException {

		return getFieldsMap(getDefaultLanguageId());
	}

	@Override
	public Map<String, Map<String, String>> getFieldsMap(
			boolean includeTransientFields)
		throws PortalException {

		return getFieldsMap(getDefaultLanguageId(), includeTransientFields);
	}

	@Override
	public Map<String, Map<String, String>> getFieldsMap(String locale)
		throws PortalException {

		return getFieldsMap(locale, false);
	}

	@Override
	public Map<String, Map<String, String>> getFieldsMap(
			String locale, boolean includeTransientFields)
		throws PortalException {

		_indexFieldsMap(locale);

		Map<String, Map<String, Map<String, String>>> fieldsMap = null;

		if (includeTransientFields) {
			fieldsMap = getLocalizedFieldsMap();
		}
		else {
			fieldsMap = getLocalizedPersistentFieldsMap();
		}

		return fieldsMap.get(locale);
	}

	@Override
	public String getFieldTip(String fieldName, Locale locale)
		throws PortalException {

		return getFieldTip(fieldName, LocaleUtil.toLanguageId(locale));
	}

	@Override
	public String getFieldTip(String fieldName, String locale)
		throws PortalException {

		return GetterUtil.getString(
			getFieldProperty(fieldName, "tip", locale), fieldName);
	}

	@Override
	public String getFieldType(String fieldName) throws PortalException {
		return getFieldProperty(fieldName, "type");
	}

	@Override
	public DDMForm getFullHierarchyDDMForm()
		throws PortalException, SystemException {

		DDMForm ddmForm = getDDMForm();

		DDMStructure parentDDMStructure = getParentDDMStructure();

		if (parentDDMStructure != null) {
			DDMForm ancestorsDDMForm =
				parentDDMStructure.getFullHierarchyDDMForm();

			List<DDMFormField> ddmFormFields = ddmForm.getDDMFormFields();

			ddmFormFields.addAll(ancestorsDDMForm.getDDMFormFields());
		}

		return ddmForm;
	}

	@Override
	public Map<String, Map<String, Map<String, String>>>
		getLocalizedFieldsMap() {

		if (_localizedFieldsMap == null) {
			_localizedFieldsMap =
				new ConcurrentHashMap
					<String, Map<String, Map<String, String>>>();
		}

		return _localizedFieldsMap;
	}

	@Override
	public Map<String, Map<String, Map<String, String>>>
		getLocalizedPersistentFieldsMap() {

		if (_localizedPersistentFieldsMap == null) {
			_localizedPersistentFieldsMap =
				new ConcurrentHashMap
					<String, Map<String, Map<String, String>>>();
		}

		return _localizedPersistentFieldsMap;
	}

	@Override
	public Map<String, Map<String, Map<String, String>>>
		getLocalizedTransientFieldsMap() {

		if (_localizedTransientFieldsMap == null) {
			_localizedTransientFieldsMap =
				new ConcurrentHashMap
					<String, Map<String, Map<String, String>>>();
		}

		return _localizedTransientFieldsMap;
	}

	@Override
	public Map<String, Map<String, String>> getPersistentFieldsMap(
			String locale)
		throws PortalException {

		_indexFieldsMap(locale);

		Map<String, Map<String, Map<String, String>>>
			localizedPersistentFieldsMap = getLocalizedPersistentFieldsMap();

		Map<String, Map<String, String>> fieldsMap =
			localizedPersistentFieldsMap.get(locale);

		return fieldsMap;
	}

	@Override
	public List<String> getRootFieldNames() throws PortalException {
		List<String> fieldNames = new ArrayList<String>();

		Map<String, Map<String, String>> fieldsMap = getFieldsMap(true);

		for (Map.Entry<String, Map<String, String>> entry :
				fieldsMap.entrySet()) {

			Map<String, String> field = entry.getValue();

			String parentNameKey = _getPrivateAttributeKey("parentName");

			if (!field.containsKey(parentNameKey)) {
				fieldNames.add(entry.getKey());
			}
		}

		return fieldNames;
	}

	@Override
	public List<DDMTemplate> getTemplates() {
		return DDMTemplateLocalServiceUtil.getTemplates(getStructureId());
	}

	@Override
	public Map<String, Map<String, String>> getTransientFieldsMap(String locale)
		throws PortalException {

		_indexFieldsMap(locale);

		Map<String, Map<String, Map<String, String>>>
			localizedTransientFieldsMap = getLocalizedTransientFieldsMap();

		Map<String, Map<String, String>> fieldsMap =
			localizedTransientFieldsMap.get(locale);

		return fieldsMap;
	}

	@Override
	public String getUnambiguousName(
			List<DDMStructure> structures, long groupId, final Locale locale)
		throws PortalException {

		if (getGroupId() == groupId ) {
			return getName(locale);
		}

		boolean hasAmbiguousName = ListUtil.exists(
			structures,
			new PredicateFilter<DDMStructure>() {

				@Override
				public boolean filter(DDMStructure structure) {
					String name = structure.getName(locale);

					if (name.equals(getName(locale)) &&
						(structure.getStructureId() != getStructureId())) {

						return true;
					}

					return false;
				}

			});

		if (hasAmbiguousName) {
			Group group = GroupLocalServiceUtil.getGroup(getGroupId());

			return group.getUnambiguousName(getName(locale), locale);
		}

		return getName(locale);
	}

	/**
	 * Returns the WebDAV URL to access the structure.
	 *
	 * @param  themeDisplay the theme display needed to build the URL. It can
	 *         set HTTPS access, the server name, the server port, the path
	 *         context, and the scope group.
	 * @param  webDAVToken the WebDAV token for the URL
	 * @return the WebDAV URL
	 */
	@Override
	public String getWebDavURL(ThemeDisplay themeDisplay, String webDAVToken) {
		StringBundler sb = new StringBundler(11);

		boolean secure = false;

		if (themeDisplay.isSecure() ||
			PropsValues.WEBDAV_SERVLET_HTTPS_REQUIRED) {

			secure = true;
		}

		String portalURL = PortalUtil.getPortalURL(
			themeDisplay.getServerName(), themeDisplay.getServerPort(), secure);

		sb.append(portalURL);

		sb.append(themeDisplay.getPathContext());
		sb.append(StringPool.SLASH);
		sb.append("webdav");

		Group group = themeDisplay.getScopeGroup();

		sb.append(group.getFriendlyURL());

		sb.append(StringPool.SLASH);
		sb.append(webDAVToken);
		sb.append(StringPool.SLASH);
		sb.append("Structures");
		sb.append(StringPool.SLASH);
		sb.append(getStructureId());

		return sb.toString();
	}

	@Override
	public boolean hasField(String fieldName) throws PortalException {
		Map<String, Map<String, String>> fieldsMap = getFieldsMap(true);

		boolean hasField = fieldsMap.containsKey(fieldName);

		if (!hasField && (getParentStructureId() > 0)) {
			DDMStructure parentStructure =
				DDMStructureLocalServiceUtil.getStructure(
					getParentStructureId());

			hasField = parentStructure.hasField(fieldName);
		}

		return hasField;
	}

	@Override
	public boolean isFieldPrivate(String fieldName) throws PortalException {
		return GetterUtil.getBoolean(getFieldProperty(fieldName, "private"));
	}

	@Override
	public boolean isFieldRepeatable(String fieldName) throws PortalException {
		return GetterUtil.getBoolean(getFieldProperty(fieldName, "repeatable"));
	}

	@Override
	public boolean isFieldTransient(String fieldName) throws PortalException {
		if (!hasField(fieldName)) {
			throw new StructureFieldException();
		}

		Map<String, Map<String, String>> transientFieldsMap =
			getTransientFieldsMap(getDefaultLanguageId());

		return transientFieldsMap.containsKey(fieldName);
	}

	@Override
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {

		super.prepareLocalizedFieldsForImport(defaultImportLocale);

		Locale ddmStructureDefaultLocale = LocaleUtil.fromLanguageId(
			getDefaultLanguageId());

		try {
			setXsd(
				DDMXMLUtil.updateXMLDefaultLocale(
					getXsd(), ddmStructureDefaultLocale, defaultImportLocale));
		}
		catch (Exception e) {
			throw new LocaleException(LocaleException.TYPE_EXPORT_IMPORT, e);
		}
	}

	@Override
	public void setDDMForm(DDMForm ddmForm) {
		_ddmForm = ddmForm;
	}

	@Override
	public void setLocalizedFieldsMap(
		Map<String, Map<String, Map<String, String>>> localizedFieldsMap) {

		_localizedFieldsMap = localizedFieldsMap;
	}

	@Override
	public void setLocalizedPersistentFieldsMap(
		Map<String, Map<String, Map<String, String>>>
			localizedPersistentFieldsMap) {

		_localizedPersistentFieldsMap = localizedPersistentFieldsMap;
	}

	@Override
	public void setLocalizedTransientFieldsMap(
		Map<String, Map<String, Map<String, String>>>
			localizedTransientFieldsMap) {

		_localizedTransientFieldsMap = localizedTransientFieldsMap;
	}

	@Override
	public void setXsd(String xsd) {
		super.setXsd(xsd);

		_ddmForm = null;
		_localizedFieldsMap = null;
		_localizedPersistentFieldsMap = null;
		_localizedTransientFieldsMap = null;
	}

	@Override
	public void updateDDMForm(DDMForm ddmForm) {
		setXsd(DDMFormXSDSerializerUtil.serialize(ddmForm));
	}

	protected DDMStructure getParentDDMStructure()
		throws PortalException, SystemException {

		if (getParentStructureId() == 0) {
			return null;
		}

		DDMStructure parentStructure =
			DDMStructureLocalServiceUtil.getStructure(getParentStructureId());

		return parentStructure;
	}

	private Document _getDocument() throws PortalException {
		try {
			return SAXReaderUtil.read(getXsd());
		}
		catch (DocumentException de) {
			throw new PortalException(de);
		}
	}

	private Map<String, String> _getField(Element element, String locale) {
		Map<String, String> field = new HashMap<String, String>();

		String[] availableLanguageIds = getAvailableLanguageIds();

		if ((locale != null) &&
			!ArrayUtil.contains(availableLanguageIds, locale)) {

			locale = getDefaultLanguageId();
		}

		locale = HtmlUtil.escapeXPathAttribute(locale);

		String xPathExpression =
			"meta-data[@locale=".concat(locale).concat("]");

		XPath xPathSelector = SAXReaderUtil.createXPath(xPathExpression);

		Node node = xPathSelector.selectSingleNode(element);

		Element metaDataElement = (Element)node.asXPathResult(node.getParent());

		if (metaDataElement != null) {
			List<Element> childMetaDataElements = metaDataElement.elements();

			for (Element childMetaDataElement : childMetaDataElements) {
				String name = childMetaDataElement.attributeValue("name");
				String value = childMetaDataElement.getText();

				field.put(name, value);
			}
		}

		for (Attribute attribute : element.attributes()) {
			field.put(attribute.getName(), attribute.getValue());
		}

		Element parentElement = element.getParent();

		if (parentElement != null) {
			String parentName = parentElement.attributeValue("name");

			if (Validator.isNotNull(parentName)) {
				field.put(_getPrivateAttributeKey("parentName"), parentName);
			}
		}

		return field;
	}

	private String _getPrivateAttributeKey(String attributeName) {
		return StringPool.UNDERLINE.concat(attributeName).concat(
			StringPool.UNDERLINE);
	}

	private Map<String, String> _getPrivateField(String privateFieldName) {
		Map<String, String> privateField = new HashMap<String, String>();

		String dataType = PropsUtil.get(
			PropsKeys.DYNAMIC_DATA_MAPPING_STRUCTURE_PRIVATE_FIELD_DATATYPE,
			new Filter(privateFieldName));

		privateField.put("dataType", dataType);

		privateField.put("name", privateFieldName);
		privateField.put("private", Boolean.TRUE.toString());

		String repeatable = PropsUtil.get(
			PropsKeys.DYNAMIC_DATA_MAPPING_STRUCTURE_PRIVATE_FIELD_REPEATABLE,
			new Filter(privateFieldName));

		privateField.put("repeatable", repeatable);

		return privateField;
	}

	private void _indexFieldsMap(String locale) throws PortalException {
		Map<String, Map<String, Map<String, String>>> localizedFieldsMap =
			getLocalizedFieldsMap();

		if (localizedFieldsMap.containsKey(locale)) {
			return;
		}

		Map<String, Map<String, Map<String, String>>>
			localizedPersistentFieldsMap = getLocalizedPersistentFieldsMap();
		Map<String, Map<String, Map<String, String>>>
			localizedTransientFieldsMap = getLocalizedTransientFieldsMap();
		Map<String, Map<String, String>> fieldsMap =
			new LinkedHashMap<String, Map<String, String>>();
		Map<String, Map<String, String>> persistentFieldsMap =
			new LinkedHashMap<String, Map<String, String>>();
		Map<String, Map<String, String>> transientFieldsMap =
			new LinkedHashMap<String, Map<String, String>>();

		if (getParentStructureId() > 0) {
			DDMStructure parentStructure =
				DDMStructureLocalServiceUtil.getStructure(
					getParentStructureId());

			fieldsMap.putAll(parentStructure.getFieldsMap(locale, true));
			persistentFieldsMap.putAll(
				parentStructure.getPersistentFieldsMap(locale));
			transientFieldsMap.putAll(
				parentStructure.getTransientFieldsMap(locale));
		}

		XPath xPathSelector = SAXReaderUtil.createXPath("//dynamic-element");

		List<Node> nodes = xPathSelector.selectNodes(_getDocument());

		for (Node node : nodes) {
			Element element = (Element)node;

			String name = element.attributeValue("name");

			fieldsMap.put(name, _getField(element, locale));

			if (Validator.isNotNull(element.attributeValue("dataType"))) {
				persistentFieldsMap.put(name, _getField(element, locale));
			}
			else {
				transientFieldsMap.put(name, _getField(element, locale));
			}
		}

		String[] privateFieldNames =
			PropsValues.DYNAMIC_DATA_MAPPING_STRUCTURE_PRIVATE_FIELD_NAMES;

		for (String privateFieldName : privateFieldNames) {
			Map<String, String> privateField = _getPrivateField(
				privateFieldName);

			fieldsMap.put(privateFieldName, privateField);
			persistentFieldsMap.put(privateFieldName, privateField);
		}

		localizedFieldsMap.put(locale, fieldsMap);
		localizedPersistentFieldsMap.put(locale, persistentFieldsMap);
		localizedTransientFieldsMap.put(locale, transientFieldsMap);
	}

	private static Log _log = LogFactoryUtil.getLog(DDMStructureImpl.class);

	@CacheField
	private DDMForm _ddmForm;

	@CacheField
	private Map<String, Map<String, Map<String, String>>> _localizedFieldsMap;

	@CacheField
	private Map<String, Map<String, Map<String, String>>>
		_localizedPersistentFieldsMap;

	@CacheField
	private Map<String, Map<String, Map<String, String>>>
		_localizedTransientFieldsMap;

}