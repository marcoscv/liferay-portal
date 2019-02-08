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

import com.liferay.headless.foundation.dto.v1_0.Organization;
import com.liferay.headless.foundation.resource.v1_0.OrganizationResource;
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
public abstract class BaseOrganizationResourceImpl
	implements OrganizationResource {

	@Override
	public Page<Organization> getMyUserAccountOrganizationPage(
			Long myUserAccountId, Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	@Override
	public Organization getOrganization(Long organizationId) throws Exception {
		return new Organization();
	}

	@Override
	public Page<Organization> getOrganizationOrganizationPage(
			Long organizationId, Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	@Override
	public Page<Organization> getOrganizationPage(Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	@Override
	public Page<Organization> getUserAccountOrganizationPage(
			Long userAccountId, Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
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