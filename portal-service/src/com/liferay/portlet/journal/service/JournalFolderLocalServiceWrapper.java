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

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link JournalFolderLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see JournalFolderLocalService
 * @generated
 */
@ProviderType
public class JournalFolderLocalServiceWrapper
	implements JournalFolderLocalService,
		ServiceWrapper<JournalFolderLocalService> {
	public JournalFolderLocalServiceWrapper(
		JournalFolderLocalService journalFolderLocalService) {
		_journalFolderLocalService = journalFolderLocalService;
	}

	/**
	* Adds the journal folder to the database. Also notifies the appropriate model listeners.
	*
	* @param journalFolder the journal folder
	* @return the journal folder that was added
	*/
	@Override
	public com.liferay.portlet.journal.model.JournalFolder addJournalFolder(
		com.liferay.portlet.journal.model.JournalFolder journalFolder) {
		return _journalFolderLocalService.addJournalFolder(journalFolder);
	}

	/**
	* Creates a new journal folder with the primary key. Does not add the journal folder to the database.
	*
	* @param folderId the primary key for the new journal folder
	* @return the new journal folder
	*/
	@Override
	public com.liferay.portlet.journal.model.JournalFolder createJournalFolder(
		long folderId) {
		return _journalFolderLocalService.createJournalFolder(folderId);
	}

	/**
	* Deletes the journal folder with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param folderId the primary key of the journal folder
	* @return the journal folder that was removed
	* @throws PortalException if a journal folder with the primary key could not be found
	*/
	@Override
	public com.liferay.portlet.journal.model.JournalFolder deleteJournalFolder(
		long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalFolderLocalService.deleteJournalFolder(folderId);
	}

	/**
	* Deletes the journal folder from the database. Also notifies the appropriate model listeners.
	*
	* @param journalFolder the journal folder
	* @return the journal folder that was removed
	*/
	@Override
	public com.liferay.portlet.journal.model.JournalFolder deleteJournalFolder(
		com.liferay.portlet.journal.model.JournalFolder journalFolder) {
		return _journalFolderLocalService.deleteJournalFolder(journalFolder);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _journalFolderLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _journalFolderLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _journalFolderLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator) {
		return _journalFolderLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _journalFolderLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _journalFolderLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.portlet.journal.model.JournalFolder fetchJournalFolder(
		long folderId) {
		return _journalFolderLocalService.fetchJournalFolder(folderId);
	}

	/**
	* Returns the journal folder with the matching UUID and company.
	*
	* @param uuid the journal folder's UUID
	* @param companyId the primary key of the company
	* @return the matching journal folder, or <code>null</code> if a matching journal folder could not be found
	*/
	@Override
	public com.liferay.portlet.journal.model.JournalFolder fetchJournalFolderByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _journalFolderLocalService.fetchJournalFolderByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns the journal folder matching the UUID and group.
	*
	* @param uuid the journal folder's UUID
	* @param groupId the primary key of the group
	* @return the matching journal folder, or <code>null</code> if a matching journal folder could not be found
	*/
	@Override
	public com.liferay.portlet.journal.model.JournalFolder fetchJournalFolderByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _journalFolderLocalService.fetchJournalFolderByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the journal folder with the primary key.
	*
	* @param folderId the primary key of the journal folder
	* @return the journal folder
	* @throws PortalException if a journal folder with the primary key could not be found
	*/
	@Override
	public com.liferay.portlet.journal.model.JournalFolder getJournalFolder(
		long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalFolderLocalService.getJournalFolder(folderId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _journalFolderLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.portal.kernel.lar.PortletDataContext portletDataContext) {
		return _journalFolderLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalFolderLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalFolderLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the journal folder with the matching UUID and company.
	*
	* @param uuid the journal folder's UUID
	* @param companyId the primary key of the company
	* @return the matching journal folder
	* @throws PortalException if a matching journal folder could not be found
	*/
	@Override
	public com.liferay.portlet.journal.model.JournalFolder getJournalFolderByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalFolderLocalService.getJournalFolderByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns the journal folder matching the UUID and group.
	*
	* @param uuid the journal folder's UUID
	* @param groupId the primary key of the group
	* @return the matching journal folder
	* @throws PortalException if a matching journal folder could not be found
	*/
	@Override
	public com.liferay.portlet.journal.model.JournalFolder getJournalFolderByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalFolderLocalService.getJournalFolderByUuidAndGroupId(uuid,
			groupId);
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
	@Override
	public java.util.List<com.liferay.portlet.journal.model.JournalFolder> getJournalFolders(
		int start, int end) {
		return _journalFolderLocalService.getJournalFolders(start, end);
	}

	/**
	* Returns the number of journal folders.
	*
	* @return the number of journal folders
	*/
	@Override
	public int getJournalFoldersCount() {
		return _journalFolderLocalService.getJournalFoldersCount();
	}

	/**
	* Updates the journal folder in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param journalFolder the journal folder
	* @return the journal folder that was updated
	*/
	@Override
	public com.liferay.portlet.journal.model.JournalFolder updateJournalFolder(
		com.liferay.portlet.journal.model.JournalFolder journalFolder) {
		return _journalFolderLocalService.updateJournalFolder(journalFolder);
	}

	@Override
	public void addDDMStructureJournalFolder(long structureId, long folderId) {
		_journalFolderLocalService.addDDMStructureJournalFolder(structureId,
			folderId);
	}

	@Override
	public void addDDMStructureJournalFolder(long structureId,
		com.liferay.portlet.journal.model.JournalFolder journalFolder) {
		_journalFolderLocalService.addDDMStructureJournalFolder(structureId,
			journalFolder);
	}

	@Override
	public void addDDMStructureJournalFolders(long structureId, long[] folderIds) {
		_journalFolderLocalService.addDDMStructureJournalFolders(structureId,
			folderIds);
	}

	@Override
	public void addDDMStructureJournalFolders(long structureId,
		java.util.List<com.liferay.portlet.journal.model.JournalFolder> JournalFolders) {
		_journalFolderLocalService.addDDMStructureJournalFolders(structureId,
			JournalFolders);
	}

	@Override
	public void clearDDMStructureJournalFolders(long structureId) {
		_journalFolderLocalService.clearDDMStructureJournalFolders(structureId);
	}

	@Override
	public void deleteDDMStructureJournalFolder(long structureId, long folderId) {
		_journalFolderLocalService.deleteDDMStructureJournalFolder(structureId,
			folderId);
	}

	@Override
	public void deleteDDMStructureJournalFolder(long structureId,
		com.liferay.portlet.journal.model.JournalFolder journalFolder) {
		_journalFolderLocalService.deleteDDMStructureJournalFolder(structureId,
			journalFolder);
	}

	@Override
	public void deleteDDMStructureJournalFolders(long structureId,
		long[] folderIds) {
		_journalFolderLocalService.deleteDDMStructureJournalFolders(structureId,
			folderIds);
	}

	@Override
	public void deleteDDMStructureJournalFolders(long structureId,
		java.util.List<com.liferay.portlet.journal.model.JournalFolder> JournalFolders) {
		_journalFolderLocalService.deleteDDMStructureJournalFolders(structureId,
			JournalFolders);
	}

	/**
	* Returns the structureIds of the d d m structures associated with the journal folder.
	*
	* @param folderId the folderId of the journal folder
	* @return long[] the structureIds of d d m structures associated with the journal folder
	*/
	@Override
	public long[] getDDMStructurePrimaryKeys(long folderId) {
		return _journalFolderLocalService.getDDMStructurePrimaryKeys(folderId);
	}

	@Override
	public java.util.List<com.liferay.portlet.journal.model.JournalFolder> getDDMStructureJournalFolders(
		long structureId) {
		return _journalFolderLocalService.getDDMStructureJournalFolders(structureId);
	}

	@Override
	public java.util.List<com.liferay.portlet.journal.model.JournalFolder> getDDMStructureJournalFolders(
		long structureId, int start, int end) {
		return _journalFolderLocalService.getDDMStructureJournalFolders(structureId,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.portlet.journal.model.JournalFolder> getDDMStructureJournalFolders(
		long structureId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator) {
		return _journalFolderLocalService.getDDMStructureJournalFolders(structureId,
			start, end, orderByComparator);
	}

	@Override
	public int getDDMStructureJournalFoldersCount(long structureId) {
		return _journalFolderLocalService.getDDMStructureJournalFoldersCount(structureId);
	}

	@Override
	public boolean hasDDMStructureJournalFolder(long structureId, long folderId) {
		return _journalFolderLocalService.hasDDMStructureJournalFolder(structureId,
			folderId);
	}

	@Override
	public boolean hasDDMStructureJournalFolders(long structureId) {
		return _journalFolderLocalService.hasDDMStructureJournalFolders(structureId);
	}

	@Override
	public void setDDMStructureJournalFolders(long structureId, long[] folderIds) {
		_journalFolderLocalService.setDDMStructureJournalFolders(structureId,
			folderIds);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _journalFolderLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_journalFolderLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public com.liferay.portlet.journal.model.JournalFolder addFolder(
		long userId, long groupId, long parentFolderId, java.lang.String name,
		java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalFolderLocalService.addFolder(userId, groupId,
			parentFolderId, name, description, serviceContext);
	}

	@Override
	public com.liferay.portlet.journal.model.JournalFolder deleteFolder(
		com.liferay.portlet.journal.model.JournalFolder folder)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalFolderLocalService.deleteFolder(folder);
	}

	@Override
	public com.liferay.portlet.journal.model.JournalFolder deleteFolder(
		com.liferay.portlet.journal.model.JournalFolder folder,
		boolean includeTrashedEntries)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalFolderLocalService.deleteFolder(folder,
			includeTrashedEntries);
	}

	@Override
	public com.liferay.portlet.journal.model.JournalFolder deleteFolder(
		long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalFolderLocalService.deleteFolder(folderId);
	}

	@Override
	public com.liferay.portlet.journal.model.JournalFolder deleteFolder(
		long folderId, boolean includeTrashedEntries)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalFolderLocalService.deleteFolder(folderId,
			includeTrashedEntries);
	}

	@Override
	public void deleteFolders(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_journalFolderLocalService.deleteFolders(groupId);
	}

	@Override
	public com.liferay.portlet.journal.model.JournalFolder fetchFolder(
		long folderId) {
		return _journalFolderLocalService.fetchFolder(folderId);
	}

	@Override
	public com.liferay.portlet.journal.model.JournalFolder fetchFolder(
		long groupId, long parentFolderId, java.lang.String name) {
		return _journalFolderLocalService.fetchFolder(groupId, parentFolderId,
			name);
	}

	@Override
	public com.liferay.portlet.journal.model.JournalFolder fetchFolder(
		long groupId, java.lang.String name) {
		return _journalFolderLocalService.fetchFolder(groupId, name);
	}

	@Override
	public java.util.List<com.liferay.portlet.journal.model.JournalFolder> getCompanyFolders(
		long companyId, int start, int end) {
		return _journalFolderLocalService.getCompanyFolders(companyId, start,
			end);
	}

	@Override
	public int getCompanyFoldersCount(long companyId) {
		return _journalFolderLocalService.getCompanyFoldersCount(companyId);
	}

	@Override
	public com.liferay.portlet.journal.model.JournalFolder getFolder(
		long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalFolderLocalService.getFolder(folderId);
	}

	@Override
	public java.util.List<com.liferay.portlet.journal.model.JournalFolder> getFolders(
		long groupId) {
		return _journalFolderLocalService.getFolders(groupId);
	}

	@Override
	public java.util.List<com.liferay.portlet.journal.model.JournalFolder> getFolders(
		long groupId, long parentFolderId) {
		return _journalFolderLocalService.getFolders(groupId, parentFolderId);
	}

	@Override
	public java.util.List<com.liferay.portlet.journal.model.JournalFolder> getFolders(
		long groupId, long parentFolderId, int status) {
		return _journalFolderLocalService.getFolders(groupId, parentFolderId,
			status);
	}

	@Override
	public java.util.List<com.liferay.portlet.journal.model.JournalFolder> getFolders(
		long groupId, long parentFolderId, int start, int end) {
		return _journalFolderLocalService.getFolders(groupId, parentFolderId,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.portlet.journal.model.JournalFolder> getFolders(
		long groupId, long parentFolderId, int status, int start, int end) {
		return _journalFolderLocalService.getFolders(groupId, parentFolderId,
			status, start, end);
	}

	@Override
	public java.util.List<java.lang.Object> getFoldersAndArticles(
		long groupId, long folderId) {
		return _journalFolderLocalService.getFoldersAndArticles(groupId,
			folderId);
	}

	@Override
	public java.util.List<java.lang.Object> getFoldersAndArticles(
		long groupId, long folderId, int status) {
		return _journalFolderLocalService.getFoldersAndArticles(groupId,
			folderId, status);
	}

	@Override
	public java.util.List<java.lang.Object> getFoldersAndArticles(
		long groupId, long folderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return _journalFolderLocalService.getFoldersAndArticles(groupId,
			folderId, start, end, obc);
	}

	@Override
	public int getFoldersAndArticlesCount(long groupId,
		java.util.List<java.lang.Long> folderIds, int status) {
		return _journalFolderLocalService.getFoldersAndArticlesCount(groupId,
			folderIds, status);
	}

	@Override
	public int getFoldersAndArticlesCount(long groupId, long folderId) {
		return _journalFolderLocalService.getFoldersAndArticlesCount(groupId,
			folderId);
	}

	@Override
	public int getFoldersAndArticlesCount(long groupId, long folderId,
		int status) {
		return _journalFolderLocalService.getFoldersAndArticlesCount(groupId,
			folderId, status);
	}

	@Override
	public int getFoldersCount(long groupId, long parentFolderId) {
		return _journalFolderLocalService.getFoldersCount(groupId,
			parentFolderId);
	}

	@Override
	public int getFoldersCount(long groupId, long parentFolderId, int status) {
		return _journalFolderLocalService.getFoldersCount(groupId,
			parentFolderId, status);
	}

	@Override
	public long getInheritedWorkflowFolderId(long folderId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.journal.NoSuchFolderException {
		return _journalFolderLocalService.getInheritedWorkflowFolderId(folderId);
	}

	@Override
	public java.util.List<com.liferay.portlet.journal.model.JournalFolder> getNoAssetFolders() {
		return _journalFolderLocalService.getNoAssetFolders();
	}

	@Override
	public long getOverridedDDMStructuresFolderId(long folderId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.journal.NoSuchFolderException {
		return _journalFolderLocalService.getOverridedDDMStructuresFolderId(folderId);
	}

	@Override
	public void getSubfolderIds(java.util.List<java.lang.Long> folderIds,
		long groupId, long folderId) {
		_journalFolderLocalService.getSubfolderIds(folderIds, groupId, folderId);
	}

	@Override
	public com.liferay.portlet.journal.model.JournalFolder moveFolder(
		long folderId, long parentFolderId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalFolderLocalService.moveFolder(folderId, parentFolderId,
			serviceContext);
	}

	@Override
	public com.liferay.portlet.journal.model.JournalFolder moveFolderFromTrash(
		long userId, long folderId, long parentFolderId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalFolderLocalService.moveFolderFromTrash(userId, folderId,
			parentFolderId, serviceContext);
	}

	@Override
	public com.liferay.portlet.journal.model.JournalFolder moveFolderToTrash(
		long userId, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalFolderLocalService.moveFolderToTrash(userId, folderId);
	}

	@Override
	public void rebuildTree(long companyId) {
		_journalFolderLocalService.rebuildTree(companyId);
	}

	@Override
	public void restoreFolderFromTrash(long userId, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_journalFolderLocalService.restoreFolderFromTrash(userId, folderId);
	}

	@Override
	public void subscribe(long userId, long groupId, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_journalFolderLocalService.subscribe(userId, groupId, folderId);
	}

	@Override
	public void unsubscribe(long userId, long groupId, long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_journalFolderLocalService.unsubscribe(userId, groupId, folderId);
	}

	@Override
	public void updateAsset(long userId,
		com.liferay.portlet.journal.model.JournalFolder folder,
		long[] assetCategoryIds, java.lang.String[] assetTagNames,
		long[] assetLinkEntryIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		_journalFolderLocalService.updateAsset(userId, folder,
			assetCategoryIds, assetTagNames, assetLinkEntryIds);
	}

	@Override
	public com.liferay.portlet.journal.model.JournalFolder updateFolder(
		long userId, long folderId, long parentFolderId, java.lang.String name,
		java.lang.String description, boolean mergeWithParentFolder,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalFolderLocalService.updateFolder(userId, folderId,
			parentFolderId, name, description, mergeWithParentFolder,
			serviceContext);
	}

	@Override
	public com.liferay.portlet.journal.model.JournalFolder updateFolder(
		long userId, long folderId, long parentFolderId, java.lang.String name,
		java.lang.String description, long[] ddmStructureIds,
		int restrictionType, boolean mergeWithParentFolder,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _journalFolderLocalService.updateFolder(userId, folderId,
			parentFolderId, name, description, ddmStructureIds,
			restrictionType, mergeWithParentFolder, serviceContext);
	}

	@Override
	public void updateFolderDDMStructures(
		com.liferay.portlet.journal.model.JournalFolder folder,
		long[] ddmStructureIdsArray) {
		_journalFolderLocalService.updateFolderDDMStructures(folder,
			ddmStructureIdsArray);
	}

	@Override
	public com.liferay.portlet.journal.model.JournalFolder updateStatus(
		long userId, com.liferay.portlet.journal.model.JournalFolder folder,
		int status) throws com.liferay.portal.kernel.exception.PortalException {
		return _journalFolderLocalService.updateStatus(userId, folder, status);
	}

	@Override
	public void validateFolderDDMStructures(long folderId, long parentFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_journalFolderLocalService.validateFolderDDMStructures(folderId,
			parentFolderId);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public JournalFolderLocalService getWrappedJournalFolderLocalService() {
		return _journalFolderLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedJournalFolderLocalService(
		JournalFolderLocalService journalFolderLocalService) {
		_journalFolderLocalService = journalFolderLocalService;
	}

	@Override
	public JournalFolderLocalService getWrappedService() {
		return _journalFolderLocalService;
	}

	@Override
	public void setWrappedService(
		JournalFolderLocalService journalFolderLocalService) {
		_journalFolderLocalService = journalFolderLocalService;
	}

	private JournalFolderLocalService _journalFolderLocalService;
}