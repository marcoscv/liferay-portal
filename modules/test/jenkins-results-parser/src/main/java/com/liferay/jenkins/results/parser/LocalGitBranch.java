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

package com.liferay.jenkins.results.parser;

/**
 * @author Michael Hashimoto
 */
public class LocalGitBranch extends BaseGitBranch {

	public GitWorkingDirectory getGitWorkingDirectory() {
		LocalRepository localRepository = getLocalRepository();

		return localRepository.getGitWorkingDirectory();
	}

	public LocalRepository getLocalRepository() {
		return _localRepository;
	}

	@Override
	public String toString() {
		LocalRepository localRepository = getLocalRepository();

		StringBuilder sb = new StringBuilder();

		sb.append(localRepository.getDirectory());
		sb.append(" (");
		sb.append(getName());
		sb.append(" - ");
		sb.append(getSHA());
		sb.append(")");

		return sb.toString();
	}

	protected LocalGitBranch(
		LocalRepository localRepository, String name, String sha) {

		super(name, sha);

		if (localRepository == null) {
			throw new IllegalArgumentException("Local repository is null");
		}

		_localRepository = localRepository;
	}

	private final LocalRepository _localRepository;

}