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

package com.liferay.portlet.dynamicdatamapping.util;

import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portlet.dynamicdatamapping.BaseDDMTest;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.springframework.mock.web.MockPageContext;

/**
 * @author Pablo Carvalho
 */
public class DDMXSDImplTest extends BaseDDMTest {

	@Before
	@Override
	public void setUp() {
		super.setUp();

		_document = createSampleDocument();

		_fieldsContextKey = _PORTLET_NAMESPACE.concat(
			_NAMESPACE).concat("fieldsContext");
	}

	@Test
	public void testGetFieldsContext() throws Exception {
		_ddmXSD.getFieldsContext(
			_mockPageContext, _PORTLET_NAMESPACE, _NAMESPACE);

		Assert.assertNotNull(_mockPageContext.getAttribute(_fieldsContextKey));
	}

	@Test
	public void testGetLocalizableFieldContext() throws Exception {
		Element rootElement = _document.getRootElement();

		Element fieldElement = addTextElement(
			rootElement, "Localizable", "Localizable", true);

		Map<String, Object> fieldContext = _ddmXSD.getFieldContext(
			_mockPageContext, _PORTLET_NAMESPACE, _NAMESPACE, fieldElement,
			LocaleUtil.US);

		Assert.assertFalse(fieldContext.containsKey("disabled"));
	}

	@Test
	public void testGetTranslatingLocalizableFieldContext() throws Exception {
		Element rootElement = _document.getRootElement();

		Element fieldElement = addTextElement(
			rootElement, "Localizable", "Localizable", true);

		Map<String, Object> fieldContext = _ddmXSD.getFieldContext(
			_mockPageContext, _PORTLET_NAMESPACE, _NAMESPACE, fieldElement,
			LocaleUtil.BRAZIL);

		Assert.assertFalse(fieldContext.containsKey("disabled"));
	}

	@Test
	public void testGetTranslatingUnlocalizableFieldContext() throws Exception {
		Element rootElement = _document.getRootElement();

		Element fieldElement = addTextElement(
			rootElement, "Unlocalizable", "Unlocalizable", false);

		Map<String, Object> fieldContext = _ddmXSD.getFieldContext(
			_mockPageContext, _PORTLET_NAMESPACE, _NAMESPACE, fieldElement,
			LocaleUtil.BRAZIL);

		Assert.assertEquals(
			Boolean.TRUE.toString(), fieldContext.get("disabled"));
	}

	@Test
	public void testGetUnlocalizableFieldContext() throws Exception {
		Element rootElement = _document.getRootElement();

		Element fieldElement = addTextElement(
			rootElement, "Unlocalizable", "Unlocalizable", false);

		Map<String, Object> fieldContext = _ddmXSD.getFieldContext(
			_mockPageContext, _PORTLET_NAMESPACE, _NAMESPACE, fieldElement,
			LocaleUtil.US);

		Assert.assertFalse(fieldContext.containsKey("disabled"));
	}

	private static final String _NAMESPACE = "_namespace_";

	private static final String _PORTLET_NAMESPACE = "_portletNamespace_";

	private DDMXSDImpl _ddmXSD = new DDMXSDImpl();
	private Document _document;
	private String _fieldsContextKey;
	private MockPageContext _mockPageContext = new MockPageContext();

}