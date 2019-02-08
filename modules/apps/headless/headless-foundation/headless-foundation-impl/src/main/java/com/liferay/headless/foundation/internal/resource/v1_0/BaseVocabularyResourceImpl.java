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

package com.liferay.headless.foundation.internal.resource.v1_0;

import com.liferay.headless.foundation.dto.v1_0.Vocabulary;
import com.liferay.headless.foundation.resource.v1_0.VocabularyResource;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.vulcan.context.AcceptLanguage;
import com.liferay.portal.vulcan.context.Pagination;
import com.liferay.portal.vulcan.dto.Page;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import javax.annotation.Generated;

import javax.ws.rs.core.Context;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public abstract class BaseVocabularyResourceImpl implements VocabularyResource {

	@Override
	public Page<Vocabulary> getContentSpaceVocabulariesPage(
			Long contentSpaceId, Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	@Override
	public Vocabulary getVocabularies(Long vocabulariesId) throws Exception {
		return new Vocabulary();
	}

	@Override
	public Vocabulary postContentSpaceVocabularies(
			Long contentSpaceId, Vocabulary vocabulary)
		throws Exception {

		return new Vocabulary();
	}

	@Override
	public Vocabulary postContentSpaceVocabulariesBatchCreate(
			Long contentSpaceId, Vocabulary vocabulary)
		throws Exception {

		return new Vocabulary();
	}

	@Override
	public Vocabulary putVocabularies(
			Long vocabulariesId, Vocabulary vocabulary)
		throws Exception {

		return new Vocabulary();
	}

	protected <T, R> List<R> transform(
		List<T> list, Function<T, R> transformFunction) {

		return TransformUtil.transform(list, transformFunction);
	}

	@Context
	protected AcceptLanguage acceptLanguage;

	@Context
	protected Company company;

}