<%--
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
--%>

<%@ include file="/html/portlet/portlet_configuration/init.jsp" %>

<%
String tabs2 = ParamUtil.getString(request, "tabs2", "export");

Layout exportableLayout = ExportImportHelperUtil.getExportableLayout(themeDisplay);

String errorMessageKey = StringPool.BLANK;

Group group = themeDisplay.getScopeGroup();

Group stagingGroup = group;

Group liveGroup = stagingGroup.getLiveGroup();

Layout targetLayout = null;

if (!layout.isTypeControlPanel()) {
	if (liveGroup == null) {
		errorMessageKey = "this-portlet-is-placed-in-a-page-that-does-not-exist-in-the-live-site-publish-the-page-first";
	}
	else {
		try {
			if (stagingGroup.isLayout()) {
				targetLayout = LayoutLocalServiceUtil.getLayout(liveGroup.getClassPK());
			}
			else {
				targetLayout = LayoutLocalServiceUtil.getLayoutByUuidAndGroupId(exportableLayout.getUuid(), liveGroup.getGroupId(), exportableLayout.isPrivateLayout());
			}
		}
		catch (NoSuchLayoutException nsle) {
			errorMessageKey = "this-portlet-is-placed-in-a-page-that-does-not-exist-in-the-live-site-publish-the-page-first";
		}

		if (targetLayout != null) {
			LayoutType layoutType = targetLayout.getLayoutType();

			if (!(layoutType instanceof LayoutTypePortlet) || !((LayoutTypePortlet)layoutType).hasPortletId(selPortlet.getPortletId())) {
				errorMessageKey = "this-portlet-has-not-been-added-to-the-live-page-publish-the-page-first";
			}
		}
	}
}
else if (stagingGroup.isLayout()) {
	if (liveGroup == null) {
		errorMessageKey = "a-portlet-is-placed-in-this-page-of-scope-that-does-not-exist-in-the-live-site-publish-the-page-first";
	}
	else {
		try {
			targetLayout = LayoutLocalServiceUtil.getLayout(liveGroup.getClassPK());
		}
		catch (NoSuchLayoutException nsle) {
			errorMessageKey = "a-portlet-is-placed-in-this-page-of-scope-that-does-not-exist-in-the-live-site-publish-the-page-first";
		}
	}
}

PortletURL portletURL = currentURLObj;

portletURL.setParameter("tabs3", "current-and-previous");
%>

<c:choose>
	<c:when test="<%= (themeDisplay.getURLPublishToLive() == null) && !layout.isTypeControlPanel() %>">
	</c:when>
	<c:when test="<%= Validator.isNotNull(errorMessageKey) %>">
		<liferay-ui:message key="<%= errorMessageKey %>" />
	</c:when>
	<c:otherwise>
		<liferay-ui:tabs
			names="new-publication-process,current-and-previous"
			param="tabs3"
			refresh="<%= false %>"
		>
			<liferay-ui:section>

				<%
				int incompleteBackgroundTaskCount = BackgroundTaskLocalServiceUtil.getBackgroundTasksCount(themeDisplay.getScopeGroupId(), selPortlet.getPortletId(), PortletStagingBackgroundTaskExecutor.class.getName(), false);
				%>

				<div class="<%= (incompleteBackgroundTaskCount == 0) ? "hide" : "in-progress" %>" id="<portlet:namespace />incompleteProcessMessage">
					<liferay-util:include page="/html/portlet/layouts_admin/incomplete_processes_message.jsp">
						<liferay-util:param name="incompleteBackgroundTaskCount" value="<%= String.valueOf(incompleteBackgroundTaskCount) %>" />
					</liferay-util:include>
				</div>

				<portlet:actionURL var="publishPortletURL">
					<portlet:param name="struts_action" value="/portlet_configuration/export_import" />
				</portlet:actionURL>

				<aui:form action="<%= publishPortletURL %>" cssClass="lfr-export-dialog" method="post" name="fm1" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "publishToLive();" %>'>
					<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.PUBLISH_TO_LIVE %>" />
					<aui:input name="tabs1" type="hidden" value="export_import" />
					<aui:input name="tabs2" type="hidden" value="<%= tabs2 %>" />
					<aui:input name="redirect" type="hidden" value="<%= portletURL %>" />
					<aui:input name="plid" type="hidden" value="<%= exportableLayout.getPlid() %>" />
					<aui:input name="groupId" type="hidden" value="<%= themeDisplay.getScopeGroupId() %>" />
					<aui:input name="portletResource" type="hidden" value="<%= portletResource %>" />

					<div class="export-dialog-tree">

						<%
						PortletDataHandler portletDataHandler = selPortlet.getPortletDataHandlerInstance();

						PortletDataHandlerControl[] configurationControls = portletDataHandler.getExportConfigurationControls(company.getCompanyId(), themeDisplay.getScopeGroupId(), selPortlet, exportableLayout.getPlid(), false);
						%>

						<c:if test="<%= ArrayUtil.isNotEmpty(configurationControls) %>">
							<aui:fieldset cssClass="options-group" label="application">
								<ul class="lfr-tree list-unstyled select-options">
									<li class="options">
										<ul class="portlet-list">
											<li class="tree-item">
												<aui:input name="<%= PortletDataHandlerKeys.PORTLET_CONFIGURATION %>" type="hidden" value="<%= true %>" />

												<aui:input label="configuration" name="<%= PortletDataHandlerKeys.PORTLET_CONFIGURATION + StringPool.UNDERLINE + selPortlet.getRootPortletId() %>" type="checkbox" value="<%= true %>" />

												<div class="hide" id="<portlet:namespace />configuration_<%= selPortlet.getRootPortletId() %>">
													<ul class="lfr-tree list-unstyled">
														<li class="tree-item">
															<aui:fieldset cssClass="portlet-type-data-section" label="configuration">
																<ul class="lfr-tree list-unstyled">

																	<%
																	request.setAttribute("render_controls.jsp-action", Constants.PUBLISH);
																	request.setAttribute("render_controls.jsp-controls", configurationControls);
																	request.setAttribute("render_controls.jsp-portletId", selPortlet.getRootPortletId());
																	%>

																	<liferay-util:include page="/html/portlet/layouts_admin/render_controls.jsp" />
																</ul>
															</aui:fieldset>
														</li>
													</ul>
												</div>

												<ul class="hide" id="<portlet:namespace />showChangeConfiguration_<%= selPortlet.getRootPortletId() %>">
													<li>
														<span class="selected-labels" id="<portlet:namespace />selectedConfiguration_<%= selPortlet.getRootPortletId() %>"></span>

														<%
														Map<String,Object> data = new HashMap<String,Object>();

														data.put("portletid", selPortlet.getRootPortletId());
														%>

														<aui:a cssClass="configuration-link modify-link" data="<%= data %>" href="javascript:;" label="change" method="get" />
													</li>
												</ul>

												<aui:script>
													Liferay.Util.toggleBoxes('<portlet:namespace /><%= PortletDataHandlerKeys.PORTLET_CONFIGURATION + StringPool.UNDERLINE + selPortlet.getRootPortletId() %>', '<portlet:namespace />showChangeConfiguration<%= StringPool.UNDERLINE + selPortlet.getRootPortletId() %>');
												</aui:script>
											</li>
										</ul>
									</li>
								</ul>
							</aui:fieldset>
						</c:if>

						<c:if test="<%= !portletDataHandler.isDisplayPortlet() %>">

							<%
							DateRange dateRange = ExportImportDateUtil.getDateRange(renderRequest, themeDisplay.getScopeGroupId(), false, exportableLayout.getPlid(), selPortlet.getPortletId(), ExportImportDateUtil.RANGE_FROM_LAST_PUBLISH_DATE);

							Date startDate = dateRange.getStartDate();
							Date endDate = dateRange.getEndDate();

							PortletDataContext portletDataContext = PortletDataContextFactoryUtil.createPreparePortletDataContext(themeDisplay, startDate, endDate);

							portletDataHandler.prepareManifestSummary(portletDataContext, portletPreferences);

							ManifestSummary manifestSummary = portletDataContext.getManifestSummary();

							long exportModelCount = portletDataHandler.getExportModelCount(manifestSummary);

							long modelDeletionCount = manifestSummary.getModelDeletionCount(portletDataHandler.getDeletionSystemEventStagedModelTypes());
							%>

							<c:if test="<%= (exportModelCount != 0) || (modelDeletionCount != 0) || (startDate != null) || (endDate != null) %>">
								<aui:fieldset cssClass="options-group" label="content">
									<ul class="lfr-tree list-unstyled select-options">
										<li class="tree-item">
											<div class="hide" id="<portlet:namespace />range">
												<aui:fieldset cssClass="date-range-options" label="date-range">
													<aui:input data-name='<%= LanguageUtil.get(pageContext, "all") %>' id="rangeAll" label="all" name="range" type="radio" value="all" />

													<aui:input checked="<%= true %>" data-name='<%= LanguageUtil.get(pageContext, "from-last-publish-date") %>' id="rangeLastPublish" label="from-last-publish-date" name="range" type="radio" value="fromLastPublishDate" />

													<aui:input data-name='<%= LanguageUtil.get(pageContext, "date-range") %>' helpMessage="export-date-range-help" id="rangeDateRange" label="date-range" name="range" type="radio" value="dateRange" />

													<%
													Calendar endCalendar = CalendarFactoryUtil.getCalendar(timeZone, locale);

													if (endDate != null) {
														endCalendar.setTime(endDate);
													}

													Calendar startCalendar = CalendarFactoryUtil.getCalendar(timeZone, locale);

													if (startDate != null) {
														startCalendar.setTime(startDate);
													}
													else {
														startCalendar.add(Calendar.DATE, -1);
													}
													%>

													<ul class="date-range-options hide list-unstyled" id="<portlet:namespace />startEndDate">
														<li>
															<aui:fieldset label="start-date">
																<liferay-ui:input-date
																	dayParam="startDateDay"
																	dayValue="<%= startCalendar.get(Calendar.DATE) %>"
																	disabled="<%= false %>"
																	firstDayOfWeek="<%= startCalendar.getFirstDayOfWeek() - 1 %>"
																	monthParam="startDateMonth"
																	monthValue="<%= startCalendar.get(Calendar.MONTH) %>"
																	name="startDate"
																	yearParam="startDateYear"
																	yearValue="<%= startCalendar.get(Calendar.YEAR) %>"
																/>

																&nbsp;

																<liferay-ui:input-time
																	amPmParam='<%= "startDateAmPm" %>'
																	amPmValue="<%= startCalendar.get(Calendar.AM_PM) %>"
																	dateParam="startDateTime"
																	dateValue="<%= startCalendar.getTime() %>"
																	disabled="<%= false %>"
																	hourParam='<%= "startDateHour" %>'
																	hourValue="<%= startCalendar.get(Calendar.HOUR) %>"
																	minuteParam='<%= "startDateMinute" %>'
																	minuteValue="<%= startCalendar.get(Calendar.MINUTE) %>"
																	name="startTime"
																/>
															</aui:fieldset>
														</li>

														<li>
															<aui:fieldset label="end-date">
																<liferay-ui:input-date
																	dayParam="endDateDay"
																	dayValue="<%= endCalendar.get(Calendar.DATE) %>"
																	disabled="<%= false %>"
																	firstDayOfWeek="<%= endCalendar.getFirstDayOfWeek() - 1 %>"
																	monthParam="endDateMonth"
																	monthValue="<%= endCalendar.get(Calendar.MONTH) %>"
																	name="endDate"
																	yearParam="endDateYear"
																	yearValue="<%= endCalendar.get(Calendar.YEAR) %>"
																/>

																&nbsp;

																<liferay-ui:input-time
																	amPmParam='<%= "endDateAmPm" %>'
																	amPmValue="<%= endCalendar.get(Calendar.AM_PM) %>"
																	dateParam="startDateTime"
																	dateValue="<%= endCalendar.getTime() %>"
																	disabled="<%= false %>"
																	hourParam='<%= "endDateHour" %>'
																	hourValue="<%= endCalendar.get(Calendar.HOUR) %>"
																	minuteParam='<%= "endDateMinute" %>'
																	minuteValue="<%= endCalendar.get(Calendar.MINUTE) %>"
																	name="endTime"
																/>
															</aui:fieldset>
														</li>
													</ul>

													<aui:input id="rangeLast" label='<%= LanguageUtil.get(pageContext, "last") + StringPool.TRIPLE_PERIOD %>' name="range" type="radio" value="last" />

													<ul class="hide list-unstyled" id="<portlet:namespace />rangeLastInputs">
														<li>
															<aui:select cssClass="relative-range" label="" name="last">
																<aui:option label='<%= LanguageUtil.format(pageContext, "x-hours", "12", false) %>' value="12" />
																<aui:option label='<%= LanguageUtil.format(pageContext, "x-hours", "24", false) %>' value="24" />
																<aui:option label='<%= LanguageUtil.format(pageContext, "x-hours", "48", false) %>' value="48" />
																<aui:option label='<%= LanguageUtil.format(pageContext, "x-days", "7", false) %>' value="168" />
															</aui:select>
														</li>
													</ul>
												</aui:fieldset>
											</div>

											<liferay-util:buffer var="selectedLabelsHTML">
												<span class="selected-labels" id="<portlet:namespace />selectedRange"></span>

												<aui:a cssClass="modify-link" href="javascript:;" id="rangeLink" label="change" method="get" />
											</liferay-util:buffer>

											<liferay-ui:icon
												iconCssClass="icon-calendar"
												label="<%= true %>"
												message='<%= LanguageUtil.get(locale, "date-range") + selectedLabelsHTML %>'
											/>
										</li>

										<c:if test="<%= (exportModelCount != 0) || (modelDeletionCount != 0) %>">
											<li class="options">
												<ul class="portlet-list">
													<li class="tree-item">
														<aui:input name="<%= PortletDataHandlerKeys.PORTLET_DATA_CONTROL_DEFAULT %>" type="hidden" value="<%= false %>" />

														<aui:input name="<%= PortletDataHandlerKeys.PORTLET_DATA %>" type="hidden" value="<%= true %>" />

														<liferay-util:buffer var="badgeHTML">
															<span class="badge badge-info"><%= exportModelCount > 0 ? exportModelCount : StringPool.BLANK %></span>
															<span class="badge badge-warning" id="<portlet:namespace />deletions"><%= modelDeletionCount > 0 ? (modelDeletionCount + StringPool.SPACE + LanguageUtil.get(pageContext, "deletions")) : StringPool.BLANK %></span>
														</liferay-util:buffer>

														<aui:input label='<%= LanguageUtil.get(pageContext, "content") + badgeHTML %>' name="<%= PortletDataHandlerKeys.PORTLET_DATA + StringPool.UNDERLINE + selPortlet.getRootPortletId() %>" type="checkbox" value="<%= true %>" />

														<%
														PortletDataHandlerControl[] exportControls = portletDataHandler.getExportControls();
														PortletDataHandlerControl[] metadataControls = portletDataHandler.getExportMetadataControls();

														if (ArrayUtil.isNotEmpty(exportControls) || ArrayUtil.isNotEmpty(metadataControls)) {
														%>

															<div class="hide" id="<portlet:namespace />content_<%= selPortlet.getRootPortletId() %>">
																<ul class="lfr-tree list-unstyled">
																	<li class="tree-item">
																		<aui:fieldset cssClass="portlet-type-data-section" label="content">
																			<aui:field-wrapper label='<%= ArrayUtil.isNotEmpty(metadataControls) ? "content" : StringPool.BLANK %>'>
																				<ul class="lfr-tree list-unstyled">
																					<li class="tree-item">
																						<aui:input data-name='<%= LanguageUtil.get(locale, "delete-portlet-data") %>' label="delete-portlet-data-before-importing" name="<%= PortletDataHandlerKeys.DELETE_PORTLET_DATA %>" type="checkbox" />

																						<div id="<portlet:namespace />showDeleteContentWarning">
																							<div class="alert alert-block">
																								<liferay-ui:message key="delete-content-before-importing-warning" />

																								<liferay-ui:message key="delete-content-before-importing-suggestion" />
																							</div>
																						</div>
																					</li>
																				</ul>

																				<aui:script>
																					Liferay.Util.toggleBoxes('<portlet:namespace /><%= PortletDataHandlerKeys.DELETE_PORTLET_DATA %>', '<portlet:namespace />showDeleteContentWarning');
																				</aui:script>

																				<c:if test="<%= exportControls != null %>">

																					<%
																					request.setAttribute("render_controls.jsp-action", Constants.PUBLISH);
																					request.setAttribute("render_controls.jsp-controls", exportControls);
																					request.setAttribute("render_controls.jsp-manifestSummary", manifestSummary);
																					request.setAttribute("render_controls.jsp-portletDisabled", !portletDataHandler.isPublishToLiveByDefault());
																					%>

																					<ul class="lfr-tree list-unstyled">
																						<liferay-util:include page="/html/portlet/layouts_admin/render_controls.jsp" />
																					</ul>
																				</c:if>
																			</aui:field-wrapper>

																			<c:if test="<%= metadataControls != null %>">

																				<%
																				for (PortletDataHandlerControl metadataControl : metadataControls) {
																					PortletDataHandlerBoolean control = (PortletDataHandlerBoolean)metadataControl;

																					PortletDataHandlerControl[] childrenControls = control.getChildren();

																					if (ArrayUtil.isNotEmpty(childrenControls)) {
																						request.setAttribute("render_controls.jsp-controls", childrenControls);
																					%>

																						<aui:field-wrapper label="content-metadata">
																							<ul class="lfr-tree list-unstyled">
																								<liferay-util:include page="/html/portlet/layouts_admin/render_controls.jsp" />
																							</ul>
																						</aui:field-wrapper>

																					<%
																					}
																				}
																				%>

																			</c:if>
																		</aui:fieldset>
																	</li>
																</ul>
															</div>

															<ul id="<portlet:namespace />showChangeContent_<%= selPortlet.getRootPortletId() %>">
																<li>
																	<span class="selected-labels" id="<portlet:namespace />selectedContent_<%= selPortlet.getRootPortletId() %>"></span>

																	<%
																	Map<String,Object> data = new HashMap<String,Object>();

																	data.put("portletid", selPortlet.getRootPortletId());
																	%>

																	<aui:a cssClass="content-link modify-link" data="<%= data %>" href="javascript:;" id='<%= "contentLink_" + selPortlet.getRootPortletId() %>' label="change" method="get" />
																</li>
															</ul>

															<aui:script>
																Liferay.Util.toggleBoxes('<portlet:namespace /><%= PortletDataHandlerKeys.PORTLET_DATA + StringPool.UNDERLINE + selPortlet.getRootPortletId() %>', '<portlet:namespace />showChangeContent<%= StringPool.UNDERLINE + selPortlet.getRootPortletId() %>');
															</aui:script>

														<%
														}
														%>

													</li>
												</ul>

												<ul>
													<aui:fieldset cssClass="content-options" label="for-each-of-the-selected-content-types,-publish-their">
														<span class="selected-labels" id="<portlet:namespace />selectedContentOptions"></span>

														<aui:a cssClass="modify-link" href="javascript:;" id="contentOptionsLink" label="change" method="get" />

														<div class="hide" id="<portlet:namespace />contentOptions">
															<ul class="lfr-tree list-unstyled">
																<li class="tree-item">
																	<aui:input label="comments" name="<%= PortletDataHandlerKeys.COMMENTS %>" type="checkbox" value="<%= true %>" />

																	<aui:input label="ratings" name="<%= PortletDataHandlerKeys.RATINGS %>" type="checkbox" value="<%= true %>" />

																	<c:if test="<%= modelDeletionCount != 0 %>">

																		<%
																		String deletionsLabel = LanguageUtil.get(pageContext, "deletions") + (modelDeletionCount > 0 ? " (" + modelDeletionCount + ")" : StringPool.BLANK);
																		%>

																		<aui:input data-name="<%= deletionsLabel %>" helpMessage="deletions-help" label="<%= deletionsLabel %>" name="<%= PortletDataHandlerKeys.DELETIONS %>" type="checkbox" value="<%= true %>" />
																	</c:if>
																</li>
															</ul>
														</div>
													</aui:fieldset>
												</ul>
											</li>
										</c:if>
									</ul>
								</aui:fieldset>
							</c:if>

							<aui:fieldset cssClass="options-group" label="permissions">
								<%@ include file="/html/portlet/layouts_admin/export_configuration/permissions.jspf" %>
							</aui:fieldset>
						</c:if>

						<aui:button-row>
							<aui:button type="submit" value="publish-to-live" />

							<aui:button onClick='<%= renderResponse.getNamespace() + "copyFromLive();" %>' value="copy-from-live" />
						</aui:button-row>
					</div>
				</aui:form>
			</liferay-ui:section>

			<liferay-ui:section>
				<div class="process-list" id="<portlet:namespace />publishProcesses">
					<liferay-util:include page="/html/portlet/portlet_configuration/publish_portlet_processes.jsp" />
				</div>
			</liferay-ui:section>
		</liferay-ui:tabs>

		<aui:script use="liferay-export-import">
			<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" var="publishProcessesURL">
				<portlet:param name="struts_action" value="/portlet_configuration/export_import" />
				<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.PUBLISH %>" />
				<portlet:param name="<%= SearchContainer.DEFAULT_CUR_PARAM %>" value="<%= ParamUtil.getString(request, SearchContainer.DEFAULT_CUR_PARAM) %>" />
				<portlet:param name="<%= SearchContainer.DEFAULT_DELTA_PARAM %>" value="<%= ParamUtil.getString(request, SearchContainer.DEFAULT_DELTA_PARAM) %>" />
				<portlet:param name="groupId" value="<%= String.valueOf(themeDisplay.getScopeGroupId()) %>" />
				<portlet:param name="portletResource" value="<%= portletResource %>" />
			</liferay-portlet:resourceURL>

			new Liferay.ExportImport(
				{
					commentsNode: '#<%= PortletDataHandlerKeys.COMMENTS %>',
					deletePortletDataNode: '#<%= PortletDataHandlerKeys.DELETE_PORTLET_DATA %>',
					deletionsNode: '#<%= PortletDataHandlerKeys.DELETIONS %>',
					form: document.<portlet:namespace />fm1,
					incompleteProcessMessageNode: '#<portlet:namespace />incompleteProcessMessage',
					namespace: '<portlet:namespace />',
					processesNode: '#publishProcesses',
					processesResourceURL: '<%= publishProcessesURL.toString() %>',
					rangeAllNode: '#rangeAll',
					rangeDateRangeNode: '#rangeDateRange',
					rangeLastNode: '#rangeLast',
					rangeLastPublishNode: '#rangeLastPublish',
					ratingsNode: '#<%= PortletDataHandlerKeys.RATINGS %>'
				}
			);
		</aui:script>

		<aui:script>
			function <portlet:namespace />copyFromLive() {
				if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-copy-from-live-and-update-the-existing-staging-portlet-information") %>')) {
					document.<portlet:namespace />fm1.<portlet:namespace /><%= Constants.CMD %>.value = 'copy_from_live';

					submitForm(document.<portlet:namespace />fm1);
				}
			}

			function <portlet:namespace />publishToLive() {
				if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-publish-to-live-and-update-the-existing-portlet-data") %>')) {
					submitForm(document.<portlet:namespace />fm1);
				}
			}

			Liferay.Util.toggleRadio('<portlet:namespace />portletMetaDataFilter', '<portlet:namespace />portletMetaDataList');
			Liferay.Util.toggleRadio('<portlet:namespace />portletMetaDataAll', '', ['<portlet:namespace />portletMetaDataList']);

			Liferay.Util.toggleRadio('<portlet:namespace />rangeAll', '', ['<portlet:namespace />startEndDate', '<portlet:namespace />rangeLastInputs']);
			Liferay.Util.toggleRadio('<portlet:namespace />rangeDateRange', '<portlet:namespace />startEndDate', '<portlet:namespace />rangeLastInputs');
			Liferay.Util.toggleRadio('<portlet:namespace />rangeLastPublish', '', ['<portlet:namespace />startEndDate', '<portlet:namespace />rangeLastInputs']);
			Liferay.Util.toggleRadio('<portlet:namespace />rangeLast', '<portlet:namespace />rangeLastInputs', ['<portlet:namespace />startEndDate']);
		</aui:script>
	</c:otherwise>
</c:choose>