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
import com.xemantic.githubusers.logic.error.ErrorAnalyzer;
import com.xemantic.githubusers.logic.eventbus.DefaultEventBus;
import com.xemantic.githubusers.logic.eventbus.EventBus;
import com.xemantic.githubusers.logic.service.UserService;
import com.xemantic.githubusers.logic.view.UserListView;
import com.xemantic.githubusers.logic.view.UserQueryView;
import com.xemantic.githubusers.logic.view.UserView;
import com.xemantic.githubusers.web.elemental.Elements;
import com.xemantic.githubusers.web.error.DefaultErrorAnalyzer;
import com.xemantic.githubusers.web.mdc.MDCSnackbar;
import com.xemantic.githubusers.web.service.DefaultUserSevice;
import com.xemantic.githubusers.web.view.DefaultUserListView;
import com.xemantic.githubusers.web.view.DefaultUserQueryView;
import com.xemantic.githubusers.web.view.DefaultUserView;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * @author morisil
 */
@Module
public abstract class GitHubUsersModule {

  @Binds
  abstract ErrorAnalyzer bindErrorAnalyzer(DefaultErrorAnalyzer analyzer);

  @Provides
  @Singleton
  static EventBus getEventBus() {
    return new DefaultEventBus();
  }

  @Provides
  @Singleton
  static ResourceVisitor.Supplier getResourceVisitorSupplier() {
    return () -> new RequestResourceBuilder().path("https://api.github.com");
  }

  @Binds
  @Singleton
  abstract UserService getUserService(DefaultUserSevice sevice);

  @Provides
  @Singleton
  static MDCSnackbar getMDCSnackbar() {
    return new MDCSnackbar(Elements.query(".mdc-snackbar"));
  }

  @Binds
  abstract UserQueryView getUserQueryView(DefaultUserQueryView view);

  @Binds
  abstract UserListView getUserListView(DefaultUserListView view);

  @Provides
  static UserView getUserView() { //(DefaultUserViewProvider provider) {
    return new DefaultUserView();
  }

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

}
