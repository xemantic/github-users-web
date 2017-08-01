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

import com.intendia.gwt.autorest.client.RequestResourceBuilder.FailedStatusCodeException;
import com.xemantic.githubusers.logic.event.SnackbarMessageEvent;

import java.util.function.Consumer;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Error handler for GitHub APIs.
 *
 * @author morisil
 */
@Singleton
public class GitHubApiErrorHandler {

  private final Consumer<SnackbarMessageEvent> snackbarMessageConsumer;

  @Inject
  public GitHubApiErrorHandler(Consumer<SnackbarMessageEvent> snackbarMessageConsumer) {
    this.snackbarMessageConsumer = snackbarMessageConsumer;
  }

  public void handleError(Throwable throwable) {
    if (!(
        (throwable instanceof FailedStatusCodeException)
        && handleStatusCode((FailedStatusCodeException) throwable)
        )) {
      throw new RuntimeException("Unexpected GitHub API error", throwable);
    }
  }

  private boolean handleStatusCode(FailedStatusCodeException e) {
    String message = getMessage(e.getStatusCode());
    if (message != null) {
      snackbarMessageConsumer.accept(new SnackbarMessageEvent(message));
      return true;
    }
    return false;
  }

  private String getMessage(int statusCode) {
    String message = null;
    switch (statusCode) {
      case 403: message = "Only 10 request per minute allowed. Try again in a while"; break;
      case 0: message = "You are offline"; break;
    }
    return message;
  }

}
