/*
 * github-users-web - lists GitHub users. Minimal app demonstrating
 * cross-platform app development (Web, Android, iOS) where core
 * logic is shared and transpiled from Java to JavaScript and
 * Objective-C. This project delivers Web version.
 *
 * Copyright (C) 2017  Kazimierz Pogoda
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.xemantic.githubusers.web.service;

import com.intendia.gwt.autorest.client.ResourceVisitor;
import com.xemantic.githubusers.logic.model.SearchResult;
import com.xemantic.githubusers.logic.service.UserService;
import com.xemantic.githubusers.web.model.WebSearchResult;
import com.xemantic.githubusers.web.service.json.JsonSearchResult;
import rx.Single;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Service adapting AJAX calls to the {@link UserService} contract.
 *
 * @author morisil
 */
@Singleton
public class WebUserService implements UserService {

  private final ResourceVisitor.Supplier path;

  private final GitHubApiErrorHandler errorHandler;

  @Inject
  public WebUserService(
      ResourceVisitor.Supplier path,
      GitHubApiErrorHandler errorHandler) {

    this.path = path;
    this.errorHandler = errorHandler;
  }

  @SuppressWarnings("unchecked")
  @Override
  public Single<SearchResult> find(String query, int page, int perPage) {
    return path.get()
        .method("GET")
        .path("search", "users")
        .param("q", query)
        .param("page", page)
        .param("per_page", perPage)
        .<Single<JsonSearchResult>>as(Single.class, JsonSearchResult.class)
        .doOnError(errorHandler::handleError)
        .map(WebSearchResult::new);
  }

}
