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

package com.liferay.headless.collaboration.internal.jaxrs.application;

import javax.annotation.Generated;

import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;

/**
 * @author Javier Gamarra
 * @generated
 */
@Component(
	property = {
		"oauth2.scope.checker.type=annotations",
		"osgi.jaxrs.application.base=/headless-collaboration",
		"osgi.jaxrs.extension.select=(osgi.jaxrs.name=Liferay.Vulcan.AcceptLanguageContextProvider)",
		"osgi.jaxrs.extension.select=(osgi.jaxrs.name=Liferay.Vulcan.CompanyContextProvider)",
		"osgi.jaxrs.extension.select=(osgi.jaxrs.name=Liferay.Vulcan.JSONMessageBodyReader)",
		"osgi.jaxrs.extension.select=(osgi.jaxrs.name=Liferay.Vulcan.JSONMessageBodyWriter)",
		"osgi.jaxrs.extension.select=(osgi.jaxrs.name=Liferay.Vulcan.NoSuchModelExceptionMapper)",
		"osgi.jaxrs.extension.select=(osgi.jaxrs.name=Liferay.Vulcan.PaginationContextProvider)",
		"osgi.jaxrs.extension.select=(osgi.jaxrs.name=Liferay.Vulcan.PortalExceptionMapper)",
		"osgi.jaxrs.extension.select=(osgi.jaxrs.name=Liferay.Vulcan.PrincipalExceptionMapper)",
		"osgi.jaxrs.name=headless-collaboration-application"
	},
	service = Application.class
)
@Generated("")
public class HeadlessCollaborationApplication extends Application {
}