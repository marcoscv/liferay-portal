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

<%@ include file="/html/taglib/ui/search_toggle/init.jsp" %>

<%
boolean advancedSearch = displayTerms.isAdvancedSearch();
%>

<div class="taglib-search-toggle">
	<div class="col-xs-12 form-search">
		<div class="advanced-search input-group" id="<%= id %>simple">
			<input class="form-control search-query" <%= advancedSearch ? "disabled" : StringPool.BLANK %> id="<%= id + displayTerms.KEYWORDS %>" name="<portlet:namespace /><%= displayTerms.KEYWORDS %>" placeholder="<liferay-ui:message key="keywords" />" title="keywords" type="text" value="<%= HtmlUtil.escapeAttribute(displayTerms.getKeywords()) %>" />

			<span class="input-group-btn">
				<button class="btn btn-default" type="submit">
					<%= LanguageUtil.get(pageContext, buttonLabel, "search") %>
				</button>
			</span>
		</div>

		<a class="toggle-advanced" href="javascript:;" id="<%= id %>toggleAdvanced">
			<i class="icon-search"></i>
			<i class="caret"></i>
		</a>
	</div>
</div>

<div class="taglib-search-toggle-advanced-wrapper">
	<div class="taglib-search-toggle-advanced <%= advancedSearch ? "toggler-content-expanded" : "toggler-content-collapsed" %>" id="<%= id %>advanced">
		<input id="<%= id + displayTerms.ADVANCED_SEARCH %>" name="<portlet:namespace /><%= displayTerms.ADVANCED_SEARCH %>" type="hidden" value="<%= advancedSearch %>" />

		<aui:button cssClass="close pull-right" name="closeAdvancedSearch" value="&times;" />

		<div class="taglib-search-toggle-advanced-content" id="<%= id %>advancedContent">
			<div class="form-group form-group-inline">
				<aui:select cssClass="input-medium" label="match" name="<%= displayTerms.AND_OPERATOR %>" wrapperCssClass="match-fields">
					<aui:option label="all" selected="<%= displayTerms.isAndOperator() %>" value="1" />
					<aui:option label="any" selected="<%= !displayTerms.isAndOperator() %>" value="0" />
				</aui:select>

				<span class="match-fields-legend">
					<liferay-ui:message key="of-the-following-fields" />
				</span>
			</div>