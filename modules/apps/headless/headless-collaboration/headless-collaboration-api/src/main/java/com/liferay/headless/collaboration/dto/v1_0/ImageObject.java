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

package com.liferay.headless.collaboration.dto.v1_0;

import javax.annotation.Generated;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
@XmlRootElement(name = "ImageObject")
public class ImageObject {

	public String getContentUrl() {
		return _contentUrl;
	}

	public String getEncodingFormat() {
		return _encodingFormat;
	}

	public String getFileExtension() {
		return _fileExtension;
	}

	public Long getId() {
		return _id;
	}

	public String getSelf() {
		return _self;
	}

	public Number getSizeInBytes() {
		return _sizeInBytes;
	}

	public String getTitle() {
		return _title;
	}

	public void setContentUrl(String contentUrl) {
		_contentUrl = contentUrl;
	}

	public void setEncodingFormat(String encodingFormat) {
		_encodingFormat = encodingFormat;
	}

	public void setFileExtension(String fileExtension) {
		_fileExtension = fileExtension;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setSelf(String self) {
		_self = self;
	}

	public void setSizeInBytes(Number sizeInBytes) {
		_sizeInBytes = sizeInBytes;
	}

	public void setTitle(String title) {
		_title = title;
	}

	private String _contentUrl;
	private String _encodingFormat;
	private String _fileExtension;
	private Long _id;
	private String _self;
	private Number _sizeInBytes;
	private String _title;

}