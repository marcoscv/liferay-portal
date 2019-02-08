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

package com.liferay.headless.workflow.internal.resource.v1_0;

import com.liferay.headless.workflow.dto.v1_0.WorkflowTask;
import com.liferay.headless.workflow.resource.v1_0.WorkflowTaskResource;
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
public abstract class BaseWorkflowTaskResourceImpl
	implements WorkflowTaskResource {

	@Override
	public Page<WorkflowTask> getRolesWorkflowTasksPage(
			Long rolesId, Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	@Override
	public WorkflowTask getWorkflowTask(Long workflowTasksId) throws Exception {
		return new WorkflowTask();
	}

	@Override
	public Page<WorkflowTask> getWorkflowTasksPage(
			Object genericParentId, Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	@Override
	public WorkflowTask postWorkflowTasksAssignToMe(
			Long workflowTasksId, WorkflowTask workflowTask)
		throws Exception {

		return new WorkflowTask();
	}

	@Override
	public WorkflowTask postWorkflowTasksAssignToUser(
			Long workflowTasksId, WorkflowTask workflowTask)
		throws Exception {

		return new WorkflowTask();
	}

	@Override
	public WorkflowTask postWorkflowTasksChangeTransition(
			Long workflowTasksId, WorkflowTask workflowTask)
		throws Exception {

		return new WorkflowTask();
	}

	@Override
	public WorkflowTask postWorkflowTasksUpdateDueDate(
			Long workflowTasksId, WorkflowTask workflowTask)
		throws Exception {

		return new WorkflowTask();
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