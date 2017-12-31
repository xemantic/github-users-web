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

package com.xemantic.githubusers.web.error;

import com.xemantic.ankh.shared.error.ErrorMessageProvider;
import com.xemantic.ankh.web.service.ApiException;
import io.reactivex.Maybe;

import javax.inject.Inject;

/**
 * @author morisil
 */
public class WebErrorMessageProvider implements ErrorMessageProvider {

  @Inject
  public WebErrorMessageProvider() {}

  @Override
  public Maybe<String> getMessage(Throwable throwable) {
    if (throwable instanceof ApiException) {
      return Maybe.just(getMessageForGitHubRequest(
          ((ApiException) throwable).getStatusCode())
      );
    }
    return Maybe.just(throwable.getMessage());
  }

  private String getMessageForGitHubRequest(int statusCode) {
    String message = null;
    switch (statusCode) {
      case 403: message = "Only 10 request per minute allowed. Try again in a while"; break;
      case 0: message = "You are offline"; break;
    }
    return message;
  }

}
