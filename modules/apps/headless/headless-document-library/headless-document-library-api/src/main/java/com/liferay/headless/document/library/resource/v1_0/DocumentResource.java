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

package com.liferay.headless.document.library.resource.v1_0;

import com.liferay.headless.document.library.dto.v1_0.Comment;
import com.liferay.headless.document.library.dto.v1_0.Creator;
import com.liferay.headless.document.library.dto.v1_0.Document;
import com.liferay.headless.document.library.dto.v1_0.Folder;
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
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o/headless-document-library/v1.0
 *
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
@Path("/v1.0")
public interface DocumentResource {

	@DELETE
	@Path("/document/{document-id}")
	@Produces("application/json")
	@RequiresScope("headless-document-library-application.read")
	public Response deleteDocument( @PathParam("document-id") Long documentId ) throws Exception;

	@GET
	@Path("/document/{document-id}")
	@Produces("application/json")
	@RequiresScope("headless-document-library-application.read")
	public Document getDocument( @PathParam("document-id") Long documentId ) throws Exception;

	@GET
	@Path("/documents-repository/{documents-repository-id}/document")
	@Produces("application/json")
	@RequiresScope("headless-document-library-application.read")
	public Page<Document> getDocumentsRepositoryDocumentPage( @PathParam("documents-repository-id") Long documentsRepositoryId , @Context Pagination pagination ) throws Exception;

	@Consumes("application/json")
	@POST
	@Path("/documents-repository/{documents-repository-id}/document")
	@Produces("application/json")
	@RequiresScope("headless-document-library-application.read")
	public Document postDocumentsRepositoryDocument( @PathParam("documents-repository-id") Long documentsRepositoryId , Document document ) throws Exception;

	@Consumes("application/json")
	@POST
	@Path("/documents-repository/{documents-repository-id}/document/batch-create")
	@Produces("application/json")
	@RequiresScope("headless-document-library-application.write")
	public Document postDocumentsRepositoryDocumentBatchCreate( @PathParam("documents-repository-id") Long documentsRepositoryId , Document document ) throws Exception;

	@GET
	@Path("/folder/{folder-id}/document")
	@Produces("application/json")
	@RequiresScope("headless-document-library-application.read")
	public Page<Document> getFolderDocumentPage( @PathParam("folder-id") Long folderId , @Context Pagination pagination ) throws Exception;

	@Consumes("application/json")
	@POST
	@Path("/folder/{folder-id}/document")
	@Produces("application/json")
	@RequiresScope("headless-document-library-application.read")
	public Document postFolderDocument( @PathParam("folder-id") Long folderId , Document document ) throws Exception;

	@Consumes("application/json")
	@POST
	@Path("/folder/{folder-id}/document/batch-create")
	@Produces("application/json")
	@RequiresScope("headless-document-library-application.write")
	public Document postFolderDocumentBatchCreate( @PathParam("folder-id") Long folderId , Document document ) throws Exception;

}