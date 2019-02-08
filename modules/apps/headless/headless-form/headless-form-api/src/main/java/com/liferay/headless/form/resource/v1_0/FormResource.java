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

package com.liferay.headless.form.resource.v1_0;

import com.liferay.headless.form.dto.v1_0.Creator;
import com.liferay.headless.form.dto.v1_0.Form;
import com.liferay.headless.form.dto.v1_0.FormDocument;
import com.liferay.headless.form.dto.v1_0.FormRecord;
import com.liferay.headless.form.dto.v1_0.FormStructure;
import com.liferay.oauth2.provider.scope.RequiresScope;
import com.liferay.portal.vulcan.context.Pagination;
import com.liferay.portal.vulcan.dto.Page;

import java.util.Date;

import javax.annotation.Generated;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * To access this resource, run:
 *
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o/headless-form/v1.0
 *
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
@Path("/v1.0")
public interface FormResource {

	@GET
	@Path("/content-space/{content-space-id}/form")
	@Produces("application/json")
	@RequiresScope("headless-form-application.read")
	public Page<Form> getContentSpaceFormPage( @PathParam("content-space-id") Long contentSpaceId , @Context Pagination pagination ) throws Exception;

	@GET
	@Path("/form/{form-id}")
	@Produces("application/json")
	@RequiresScope("headless-form-application.read")
	public Form getForm( @PathParam("form-id") Long formId ) throws Exception;

	@Consumes("application/json")
	@POST
	@Path("/form/{form-id}/evaluate-context")
	@Produces("application/json")
	@RequiresScope("headless-form-application.write")
	public Form postFormEvaluateContext( @PathParam("form-id") Long formId , Form form ) throws Exception;

	@GET
	@Path("/form/{form-id}/fetch-latest-draft")
	@Produces("application/json")
	@RequiresScope("headless-form-application.read")
	public Form getFormFetchLatestDraft( @PathParam("form-id") Long formId ) throws Exception;

	@Consumes("application/json")
	@POST
	@Path("/form/{form-id}/upload-file")
	@Produces("application/json")
	@RequiresScope("headless-form-application.write")
	public Form postFormUploadFile( @PathParam("form-id") Long formId , Form form ) throws Exception;
}