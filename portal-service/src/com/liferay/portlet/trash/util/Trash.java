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

package com.liferay.portlet.trash.util;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.TrashedModel;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.trash.model.TrashEntry;

import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Julio Camarero
 */
@ProviderType
public interface Trash {

	public static final String TRASH_TIME_SEPARATOR = "_TRASH_TIME_";

	public void addBaseModelBreadcrumbEntries(
			HttpServletRequest request, String className, long classPK,
			PortletURL containerModelURL)
		throws PortalException;

	public void addContainerModelBreadcrumbEntries(
			HttpServletRequest request, String className, long classPK,
			PortletURL containerModelURL)
		throws PortalException;

	public void addTrashSessionMessages(
		ActionRequest actionRequest, List<TrashedModel> trashedModels);

	public void addTrashSessionMessages(
		ActionRequest actionRequest, List<TrashedModel> trashedModels,
		String cmd);

	public void addTrashSessionMessages(
		ActionRequest actionRequest, TrashedModel trashedModel);

	public void addTrashSessionMessages(
		ActionRequest actionRequest, TrashedModel trashedModel, String cmd);

	public void deleteEntriesAttachments(
			long companyId, long repositoryId, Date date,
			String[] attachmentFileNames)
		throws PortalException;

	public List<TrashEntry> getEntries(Hits hits)
		throws PortalException;

	public OrderByComparator getEntryOrderByComparator(
		String orderByCol, String orderByType);

	public int getMaxAge(Group group) throws PortalException;

	public String getNewName(String oldName, String token);

	public String getNewName(
			ThemeDisplay themeDisplay, String className, long classPK,
			String oldName)
		throws PortalException;

	public String getOriginalTitle(String title);

	public String getTrashTime(String title, String separator);

	public String getTrashTitle(long trashEntryId);

	public PortletURL getViewContentURL(
			HttpServletRequest request, String className, long classPK)
		throws PortalException;

	public boolean isInTrash(String className, long classPK)
		throws PortalException;

	public boolean isTrashEnabled(long groupId)
		throws PortalException;

}