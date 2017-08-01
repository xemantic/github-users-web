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

package com.xemantic.githubusers.web;

import com.intendia.gwt.autorest.client.RequestResourceBuilder;
import com.intendia.gwt.autorest.client.ResourceVisitor;
import com.xemantic.githubusers.logic.driver.UrlOpener;
import com.xemantic.githubusers.logic.error.ErrorAnalyzer;
import com.xemantic.githubusers.logic.event.SnackbarMessageEvent;
import com.xemantic.githubusers.logic.event.UserQueryEvent;
import com.xemantic.githubusers.logic.event.UserSelectedEvent;
import com.xemantic.githubusers.logic.service.UserService;
import com.xemantic.githubusers.logic.view.*;
import com.xemantic.githubusers.web.driver.WebUrlOpener;
import com.xemantic.githubusers.web.error.DefaultErrorAnalyzer;
import com.xemantic.githubusers.web.view.*;
import com.xemantic.githubusers.web.service.WebUserService;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import java.util.function.Consumer;
import javax.inject.Named;
import javax.inject.Singleton;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Dagger module defining component binding for the whole app.
 * <p>
 *   Note: might be split in the future into separate modules for different
 *   subsystems.
 * </p>
 *
 * @author morisil
 */
@Module
public abstract class GitHubUsersModule {

  @Provides
  @Named("userListPageSize")
  static int getUserListPageSize() {
    return 100;
  }

  @Provides
  @Named("gitHubUserSearchLimit")
  static int getGitHubUserSearchLimit() {
    return 1000;
  }

  @Provides
  @Named("projectGitHubUrl")
  static String getProjectGitHubUrl() {
    return "https://github.com/xemantic/github-users-web";
  }

  // Snackbar message event bus
  @Provides @Singleton static PublishSubject<SnackbarMessageEvent> snackbarMessageBus() { return PublishSubject.create(); }
  @Provides static Consumer<SnackbarMessageEvent> snackbarMessageConsumer(PublishSubject<SnackbarMessageEvent> bus) { return bus::onNext; }
  @Binds abstract Observable<SnackbarMessageEvent> snackbarMessageObservable(PublishSubject<SnackbarMessageEvent> bus);

  // User query event bus
  @Provides @Singleton static PublishSubject<UserQueryEvent> userQueryBus() { return PublishSubject.create(); }
  @Provides static Consumer<UserQueryEvent> userQueryConsumer(PublishSubject<UserQueryEvent> bus) { return bus::onNext; }
  @Binds abstract Observable<UserQueryEvent> userQueryObservable(PublishSubject<UserQueryEvent> bus);

  // User selected event bus
  @Provides @Singleton static PublishSubject<UserSelectedEvent> userSelectedBus() { return PublishSubject.create(); }
  @Provides static Consumer<UserSelectedEvent> userSelectedConsumer(PublishSubject<UserSelectedEvent> bus) { return bus::onNext; }
  @Binds abstract Observable<UserSelectedEvent> userSelectedObservable(PublishSubject<UserSelectedEvent> bus);

  @Binds
  abstract ErrorAnalyzer getErrorAnalyzer(DefaultErrorAnalyzer analyzer);

  @Binds
  abstract UrlOpener getUrlOpener(WebUrlOpener opener);

  @Binds
  abstract UserService getUserService(WebUserService service);

  @Binds
  abstract DrawerView getDrawerView(WebDrawerView view);

  @Binds
  abstract SnackbarView getSnackbarView(WebSnackbarView view);

  @Binds
  abstract UserQueryView getUserQueryView(WebUserQueryView view);

  @Binds
  abstract UserListView getUserListView(WebUserListView view);

  @Provides
  static UserView getUserView() {
    return new WebUserView();
  }

  @Provides
  @Singleton
  static ResourceVisitor.Supplier getResourceVisitorSupplier() {
    return () -> new RequestResourceBuilder().path("https://api.github.com");
  }

}
