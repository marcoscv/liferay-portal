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

package com.liferay.portal.tools.rest.builder.internal.util;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.source.formatter.SourceFormatter;
import com.liferay.source.formatter.SourceFormatterArgs;

import java.io.File;

import java.util.Collections;

/**
 * @author Peter Shin
 */
public class FormatUtil {

	public static String fixWhitespace(File file, String content) {
		String newContent = content.replaceAll("(?m)^\n+", "\n");

		newContent = newContent.trim();

		if (!StringUtil.endsWith(file.getName(), ".java")) {
			return newContent;
		}

		int index = newContent.indexOf("\npublic ");

		if (index == -1) {
			return newContent;
		}

		String oldSub = newContent.substring(0, index);

		String newSub = oldSub.replaceAll("(?m)^\".+\",$", "\t\t$0");

		newContent = newContent.replace(oldSub, newSub);

		index = newContent.indexOf("\npublic ");

		oldSub = newContent.substring(
			newContent.indexOf("\n", index + 2), newContent.lastIndexOf("}"));

		newSub = oldSub.replaceAll(
			"(?m)^\t*(@|public|protected|private)", "\t$1");

		newSub = newSub.replaceAll("(?m)^\t*}$", "\t}");

		return newContent.replace(oldSub, newSub);
	}

	public static String format(File file) throws Exception {
		SourceFormatterArgs sourceFormatterArgs = new SourceFormatterArgs();

		sourceFormatterArgs.setFileNames(
			Collections.singletonList(file.getCanonicalPath()));
		sourceFormatterArgs.setIncludeGeneratedFiles(true);
		sourceFormatterArgs.setPrintErrors(false);
		sourceFormatterArgs.setSkipCheckNames(
			Collections.singletonList("JavaOSGiReferenceCheck"));

		SourceFormatter sourceFormatter = new SourceFormatter(
			sourceFormatterArgs);

		if (!StringUtil.endsWith(file.getName(), "Resource.java")) {
			sourceFormatter.format();
		}

		return FileUtil.read(file);
	}

}