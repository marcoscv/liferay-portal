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

package com.liferay.portlet.journal.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for JournalFolder. This utility wraps
 * {@link com.liferay.portlet.journal.service.impl.JournalFolderLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see JournalFolderLocalService
 * @see com.liferay.portlet.journal.service.base.JournalFolderLocalServiceBaseImpl
 * @see com.liferay.portlet.journal.service.impl.JournalFolderLocalServiceImpl
 * @generated
 */
@ProviderType
public class JournalFolderLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portlet.journal.service.impl.JournalFolderLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the journal folder to the database. Also notifies the appropriate model listeners.
	*
	* @param journalFolder the journal folder
	* @return the journal folder that was added
	*/
	public static com.liferay.portlet.journal.model.JournalFolder addJournalFolder(
		com.liferay.portlet.journal.model.JournalFolder journalFolder) {
		return getService().addJournalFolder(journalFolder);
	}

	/**
	* Creates a new journal folder with the primary key. Does not add the journal folder to the database.
	*
	* @param folderId the primary key for the new journal folder
	* @return the new journal folder
	*/
	public static com.liferay.portlet.journal.model.JournalFolder createJournalFolder(
		long folderId) {
		return getService().createJournalFolder(folderId);
	}

	/**
	* Deletes the journal folder with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param folderId the primary key of the journal folder
	* @return the journal folder that was removed
	* @throws PortalException if a journal folder with the primary key could not be found
	*/
	public static com.liferay.portlet.journal.model.JournalFolder deleteJournalFolder(
		long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteJournalFolder(folderId);
	}

	/**
	* Deletes the journal folder from the database. Also notifies the appropriate model listeners.
	*
	* @param journalFolder the journal folder
	* @return the journal folder that was removed
	*/
	public static com.liferay.portlet.journal.model.JournalFolder deleteJournalFolder(
		com.liferay.portlet.journal.model.JournalFolder journalFolder) {
		return getService().deleteJournalFolder(journalFolder);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.journal.model.impl.JournalFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.journal.model.impl.JournalFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.portlet.journal.model.JournalFolder fetchJournalFolder(
		long folderId) {
		return getService().fetchJournalFolder(folderId);
	}

	/**
	* Returns the journal folder with the matching UUID and company.
	*
	* @param uuid the journal folder's UUID
	* @param companyId the primary key of the company
	* @return the matching journal folder, or <code>null</code> if a matching journal folder could not be found
	*/
	public static com.liferay.portlet.journal.model.JournalFolder fetchJournalFolderByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().fetchJournalFolderByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns the journal folder matching the UUID and group.
	*
	* @param uuid the journal folder's UUID
	* @param groupId the primary key of the group
	* @return the matching journal folder, or <code>null</code> if a matching journal folder could not be found
	*/
	public static com.liferay.portlet.journal.model.JournalFolder fetchJournalFolderByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchJournalFolderByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the journal folder with the primary key.
	*
	* @param folderId the primary key of the journal folder
	* @return the journal folder
	* @throws PortalException if a journal folder with the primary key could not be found
	*/
	public static com.liferay.portlet.journal.model.JournalFolder getJournalFolder(
		long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getJournalFolder(folderId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.portal.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the journal folder with the matching UUID and company.
	*
	* @param uuid the journal folder's UUID
	* @param companyId the primary key of the company
	* @return the matching journal folder
	* @throws PortalException if a matching journal folder could not be found
	*/
	public static com.liferay.portlet.journal.model.JournalFolder getJournalFolderByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getJournalFolderByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns the journal folder matching the UUID and group.
	*
	* @param uuid the journal folder's UUID
	* @param groupId the primary key of the group
	* @return the matching journal folder
	* @throws PortalException if a matching journal folder could not be found
	*/
	public static com.liferay.portlet.journal.model.JournalFolder getJournalFolderByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getJournalFolderByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the journal folders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.journal.model.impl.JournalFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of journal folders
	* @param end the upper bound of the range of journal folders (not inclusive)
	* @return the range of journal folders
	*/
	public static java.util.List<com.liferay.portlet.journal.model.JournalFolder> getJournalFolders(
		int start, int end) {
		return getService().getJournalFolders(start, end);
	}

	/**
	* Returns the number of journal folders.
	*
	* @return the number of journal folders
	*/
	public static int getJournalFoldersCount() {
		return getService().getJournalFoldersCount();
	}

	/**
	* Updates the journal folder in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param journalFolder the journal folder
	* @return the journal folder that was updated
	*/
	public static com.liferay.portlet.journal.model.JournalFolder updateJournalFolder(
		com.liferay.portlet.journal.model.JournalFolder journalFolder) {
		return getService().updateJournalFolder(journalFolder);
	}

	public static void addDDMStructureJournalFolder(long structureId,
		long folderId) {
		getService().addDDMStructureJournalFolder(structureId, folderId);
	}

	public static void addDDMStructureJournalFolder(long structureId,
		com.liferay.portlet.journal.model.JournalFolder journalFolder) {
		getService().addDDMStructureJournalFolder(structureId, journalFolder);
	}

	public static void addDDMStructureJournalFolders(long structureId,
		long[] folderIds) {
		getService().addDDMStructureJournalFolders(structureId, folderIds);
	}

	public static void addDDMStructureJournalFolders(long structureId,
		java.util.List<com.liferay.portlet.journal.model.JournalFolder> JournalFolders) {
		getService().addDDMStructureJournalFolders(structureId, JournalFolders);
	}

	public static void clearDDMStructureJournalFolders(long structureId) {
		getService().clearDDMStructureJournalFolders(structureId);
	}

	public static void deleteDDMStructureJournalFolder(long structureId,
		long folderId) {
		getService().deleteDDMStructureJournalFolder(structureId, folderId);
	}

	public static void deleteDDMStructureJournalFolder(long structureId,
		com.liferay.portlet.journal.model.JournalFolder journalFolder) {
		getService().deleteDDMStructureJournalFolder(structureId, journalFolder);
	}

	public static void deleteDDMStructureJournalFolders(long structureId,
		long[] folderIds) {
		getService().deleteDDMStructureJournalFolders(structureId, folderIds);
	}

	public static void deleteDDMStructureJournalFolders(long structureId,
		java.util.List<com.liferay.portlet.journal.model.JournalFolder> JournalFolders) {
		getService()
			.deleteDDMStructureJournalFolders(structureId, JournalFolders);
	}

	/**
	* Returns the structureIds of the d d m structures associated with the journal folder.
	*
	* @param folderId the folderId of the journal folder
	* @return long[] the structureIds of d d m structures associated with the journal folder
	*/
	public static long[] getDDMStructurePrimaryKeys(long folderId) {
		return getService().getDDMStructurePrimaryKeys(folderId);
	}

	public static java.util.List<com.liferay.portlet.journal.model.JournalFolder> getDDMStructureJournalFolders(
		long structureId) {
		return getService().getDDMStructureJournalFolders(structureId);
	}

	public static java.util.List<com.liferay.portlet.journal.model.JournalFolder> getDDMStructureJournalFolders(
		long structureId, int start, int end) {
		return getService()
				   .getDDMStructureJournalFolders(structureId, start, end);
	}

	public static java.util.List<com.liferay.portlet.journal.model.JournalFolder> getDDMStructureJournalFolders(
		long structureId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator) {
		return getService()
				   .getDDMStructureJournalFolders(structureId, start, end,
			orderByComparator);
	}

	public static int getDDMStructureJournalFoldersCount(long structureId) {
		return getService().getDDMStructureJournalFoldersCount(structureId);
	}

	public static boolean hasDDMStructureJournalFolder(long structureId,
		long folderId) {
		return getService().hasDDMStructureJournalFolder(structureId, folderId);
	}

	public static boolean hasDDMStructureJournalFolders(long structureId) {
		return getService().hasDDMStructureJournalFolders(structureId);
	}

	public static void setDDMStructureJournalFolders(long structureId,
		long[] folderIds) {
		getService().setDDMStructureJournalFolders(structureId, folderIds);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static com.liferay.portlet.journal.model.JournalFolder addFolder(
		long userId, long groupId, long parentFolderId, java.lang.String name,
		java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addFolder(userId, groupId, parentFolderId, name,
			description, serviceContext);
	}

	public static com.liferay.portlet.journal.model.JournalFolder deleteFolder(
		com.liferay.portlet.journal.model.JournalFolder folder)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteFolder(folder);
	}

	public static com.liferay.portlet.journal.model.JournalFolder deleteFolder(
		com.liferay.portlet.journal.model.JournalFolder folder,
		boolean includeTrashedEntries)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteFolder(folder, includeTrashedEntries);
	}

	public static com.liferay.portlet.journal.model.JournalFolder deleteFolder(
		long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteFolder(folderId);
	}

	public static com.liferay.portlet.journal.model.JournalFolder deleteFolder(
		long folderId, boolean includeTrashedEntries)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteFolder(folderId, includeTrashedEntries);
	}

	public static void deleteFolders(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteFolders(groupId);
	}

	public static com.liferay.portlet.journal.model.JournalFolder fetchFolder(
		long folderId) {
		return getService().fetchFolder(folderId);
	}

	public static com.liferay.portlet.journal.model.JournalFolder fetchFolder(
		long groupId, long parentFolderId, java.lang.String name) {
		return getService().fetchFolder(groupId, parentFolderId, name);
	}

	public static com.liferay.portlet.journal.model.JournalFolder fetchFolder(
		long groupId, java.lang.String name) {
		return getService().fetchFolder(groupId, name);
	}

	public static java.util.List<com.liferay.portlet.journal.model.JournalFolder> getCompanyFolders(
		long companyId, int start, int end) {
		return getService().getCompanyFolders(companyId, start, end);
	}

	public static int getCompanyFoldersCount(long companyId) {
		return getService().getCompanyFoldersCount(companyId);
	}

	public static com.liferay.portlet.journal.model.JournalFolder getFolder(
		long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getFolder(folderId);
	}

	public static java.util.List<com.liferay.portlet.journal.model.JournalFolder> getFolders(
		long groupId) {
		return getService().getFolders(groupId);
	}

	public static java.util.List<com.liferay.portlet.journal.model.JournalFolder> getFolders(
		long groupId, long parentFolderId) {
		return getService().getFolders(groupId, parentFolderId);
	}

	public static java.util.List<com.liferay.portlet.journal.model.JournalFolder> getFolders(
		long groupId, long parentFolderId, int status) {
		return getService().getFolders(groupId, parentFolderId, status);
	}

	public static java.util.List<com.liferay.portlet.journal.model.JournalFolder> getFolders(
		long groupId, long parentFolderId, int start, int end) {
		return getService().getFolders(groupId, parentFolderId, start, end);
	}

	public static java.util.List<com.liferay.portlet.journal.model.JournalFolder> getFolders(
		long groupId, long parentFolderId, int status, int start, int end) {
		return getService()
				   .getFolders(groupId, parentFolderId, status, start, end);
	}

	public static java.util.List<java.lang.Object> getFoldersAndArticles(
		long groupId, long folderId) {
		return getService().getFoldersAndArticles(groupId, folderId);
	}

	public static java.util.List<java.lang.Object> getFoldersAndArticles(
		long groupId, long folderId, int status) {
		return getService().getFoldersAndArticles(groupId, folderId, status);
	}

	public static java.util.List<java.lang.Object> getFoldersAndArticles(
		long groupId, long folderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getService()
				   .getFoldersAndArticles(groupId, folderId, start, end, obc);
	}

	public static int getFoldersAndArticlesCount(long groupId,
		java.util.List<java.lang.Long> folderIds, int status) {
		return getService()
				   .getFoldersAndArticlesCount(groupId, folderIds, status);
	}

	public static int getFoldersAndArticlesCount(long groupId, long folderId) {
		return getService().getFoldersAndArticlesCount(groupId, folderId);
	}

	public static int getFoldersAndArticlesCount(long groupId, long folderId,
		int status) {
		return getService().getFoldersAndArticlesCount(groupId, folderId, status);
	}

	public static int getFoldersCount(long groupId, long parentFolderId) {
		return getService().getFoldersCount(groupId, parentFolderId);
	}

	public static int getFoldersCount(long groupId, long parentFolderId,
		int status) {
		return getService().getFoldersCount(groupId, parentFolderId, status);
	}

	public static long getInheritedWorkflowFolderId(long folderId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.journal.NoSuchFolderException {
		return getService().getInheritedWorkflowFolderId(folderId);
	}

	public static java.util.List<com.liferay.portlet.journal.model.JournalFolder> getNoAssetFolders() {
		return getService().getNoAssetFolders();
	}

	public static long getOverridedDDMStructuresFolderId(long folderId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.journal.NoSuchFolderException {
		return getService().getOverridedDDMStructuresFolderId(folderId);
	}

	public static void getSubfolderIds(
		java.util.List<java.lang.Long> folderIds, long groupId, long folderId) {
		getService().getSubfolderIds(folderIds, groupId, folderId);
	}

	public static com.liferay.portlet.journal.model.JournalFolder moveFolder(
		long folderId, long parentFolderId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().moveFolder(folderId, parentFolderId, serviceContext);
	}

	public static com.liferay.portlet.journal.model.JournalFolder moveFolderFromTrash(
		long userId, long folderId, long parentFolderId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .moveFolderFromTrash(userId, folderId, parentFolderId,
			serviceContext);
	}

	public static com.liferay.portlet.journal.model.JournalFolder moveFolderToTrash(
		long userId, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().moveFolderToTrash(userId, folderId);
	}

	public static void rebuildTree(long companyId) {
		getService().rebuildTree(companyId);
	}

	public static void restoreFolderFromTrash(long userId, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().restoreFolderFromTrash(userId, folderId);
	}

	public static void subscribe(long userId, long groupId, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().subscribe(userId, groupId, folderId);
	}

	public static void unsubscribe(long userId, long groupId, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().unsubscribe(userId, groupId, folderId);
	}

	public static void updateAsset(long userId,
		com.liferay.portlet.journal.model.JournalFolder folder,
		long[] assetCategoryIds, java.lang.String[] assetTagNames,
		long[] assetLinkEntryIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.updateAsset(userId, folder, assetCategoryIds, assetTagNames,
			assetLinkEntryIds);
	}

	public static com.liferay.portlet.journal.model.JournalFolder updateFolder(
		long userId, long folderId, long parentFolderId, java.lang.String name,
		java.lang.String description, boolean mergeWithParentFolder,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateFolder(userId, folderId, parentFolderId, name,
			description, mergeWithParentFolder, serviceContext);
	}

	public static com.liferay.portlet.journal.model.JournalFolder updateFolder(
		long userId, long folderId, long parentFolderId, java.lang.String name,
		java.lang.String description, long[] ddmStructureIds,
		int restrictionType, boolean mergeWithParentFolder,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateFolder(userId, folderId, parentFolderId, name,
			description, ddmStructureIds, restrictionType,
			mergeWithParentFolder, serviceContext);
	}

	public static void updateFolderDDMStructures(
		com.liferay.portlet.journal.model.JournalFolder folder,
		long[] ddmStructureIdsArray) {
		getService().updateFolderDDMStructures(folder, ddmStructureIdsArray);
	}

	public static com.liferay.portlet.journal.model.JournalFolder updateStatus(
		long userId, com.liferay.portlet.journal.model.JournalFolder folder,
		int status) throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateStatus(userId, folder, status);
	}

	public static void validateFolderDDMStructures(long folderId,
		long parentFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().validateFolderDDMStructures(folderId, parentFolderId);
	}

	public static JournalFolderLocalService getService() {
		if (_service == null) {
			_service = (JournalFolderLocalService)PortalBeanLocatorUtil.locate(JournalFolderLocalService.class.getName());

			ReferenceRegistry.registerReference(JournalFolderLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(JournalFolderLocalService service) {
	}

	private static JournalFolderLocalService _service;
}