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

import com.google.gwt.core.client.GWT;
import com.xemantic.githubusers.logic.event.SnackbarMessageEvent;

import java.util.function.Consumer;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observer;

/**
 * Handler of uncaught exceptions in this app. It will log the error using
 * {@link GWT#log(String, Throwable)} and possibly send {@link SnackbarMessageEvent}
 * to be displayed.
 *
 * @author morisil
 */
@Singleton
public class ExceptionHandler implements GWT.UncaughtExceptionHandler {

  private final Consumer<SnackbarMessageEvent> snackbarMessageConsumer;

  @Inject
  public ExceptionHandler(Consumer<SnackbarMessageEvent> snackbarMessageConsumer) {
    this.snackbarMessageConsumer = snackbarMessageConsumer;
  }

  /** {@inheritDoc} */
  @Override
  public void onUncaughtException(Throwable e) {
    GWT.log(e.getMessage(), e);
  }

  // use this method if you need to notify user about certain errors.
  private void postSnackbarMessage(String message) {
    snackbarMessageConsumer.accept(new SnackbarMessageEvent(message));
  }

}
