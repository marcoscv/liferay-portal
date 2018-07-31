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

package com.liferay.document.library.asset.auto.tagger.tensorflow.internal;

import com.liferay.asset.auto.tagger.AssetAutoTagProvider;
import com.liferay.document.library.asset.auto.tagger.tensorflow.internal.configuration.TensorFlowImageAssetAutoTagProviderConfiguration;
import com.liferay.document.library.asset.auto.tagger.tensorflow.internal.util.InceptionImageLabeler;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.capabilities.TemporaryFileEntriesCapability;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alejandro Tardín
 */
@Component(
	configurationPid = "com.liferay.document.library.asset.auto.tagger.tensorflow.internal.configuration.TensorFlowImageAssetAutoTagProviderConfiguration",
	property = "model.class.name=com.liferay.document.library.kernel.model.DLFileEntry",
	service = AssetAutoTagProvider.class
)
public class TensorFlowImageAssetAutoTagProvider
	implements AssetAutoTagProvider<FileEntry> {

	@Override
	public List<String> getTagNames(FileEntry fileEntry) {
		if (_tensorFlowImageAutoTaggerConfiguration.enabled() &&
			!_isTemporary(fileEntry)) {

			try {
				FileVersion fileVersion = fileEntry.getFileVersion();

				if (_isSupportedMimeType(fileVersion.getMimeType())) {
					return _inceptionImageLabeler.label(
						FileUtil.getBytes(fileVersion.getContentStream(false)),
						_tensorFlowImageAutoTaggerConfiguration.
							confidenceThreshold());
				}
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		return Collections.emptyList();
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_tensorFlowImageAutoTaggerConfiguration =
			ConfigurableUtil.createConfigurable(
				TensorFlowImageAssetAutoTagProviderConfiguration.class,
				properties);
	}

	private boolean _isSupportedMimeType(String mimeType) {
		return _supportedMimeTypes.contains(mimeType);
	}

	private boolean _isTemporary(FileEntry fileEntry) {
		return fileEntry.isRepositoryCapabilityProvided(
			TemporaryFileEntriesCapability.class);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		TensorFlowImageAssetAutoTagProvider.class);

	private static final Set<String> _supportedMimeTypes = new HashSet<>(
		Arrays.asList(
			ContentTypes.IMAGE_BMP, ContentTypes.IMAGE_JPEG,
			ContentTypes.IMAGE_PNG));

	@Reference
	private InceptionImageLabeler _inceptionImageLabeler;

	private volatile TensorFlowImageAssetAutoTagProviderConfiguration
		_tensorFlowImageAutoTaggerConfiguration;

}