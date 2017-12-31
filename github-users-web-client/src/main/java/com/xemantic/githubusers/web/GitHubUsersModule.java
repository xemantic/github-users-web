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

import com.xemantic.ankh.shared.error.AnkhExceptionHandler;
import com.xemantic.ankh.shared.error.ErrorMessageProvider;
import com.xemantic.ankh.shared.snackbar.SnackbarView;
import com.xemantic.ankh.web.AnkhWebModule;
import com.xemantic.githubusers.logic.drawer.DrawerView;
import com.xemantic.githubusers.logic.event.UserQueryEventModule;
import com.xemantic.githubusers.logic.event.UserSelectedEventModule;
import com.xemantic.githubusers.logic.service.UserService;
import com.xemantic.githubusers.logic.user.UserListView;
import com.xemantic.githubusers.logic.user.UserQueryView;
import com.xemantic.githubusers.logic.user.UserView;
import com.xemantic.githubusers.web.error.WebErrorMessageProvider;
import com.xemantic.githubusers.web.service.WebUserService;
import com.xemantic.githubusers.web.view.*;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.logging.Logger;

/**
 * Dagger module defining component binding for the whole app.
 * <p>
 *   Note: might be split in the future into separate modules for different
 *   subsystems.
 * </p>
 *
 * @author morisil
 */
@Module(includes = {
    AnkhWebModule.class,
    UserSelectedEventModule.class,
    UserQueryEventModule.class
})
public abstract class GitHubUsersModule {

  @Provides
  @Singleton
  @Named("gitHubApiUrl")
  static String getGitHubApiUrl() {
    return "https://api.github.com";
  }

  @Provides
  @Singleton
  @Named("userListPageSize")
  static int getUserListPageSize() {
    return 100;
  }

  @Provides
  @Singleton
  @Named("gitHubUserSearchLimit")
  static int getGitHubUserSearchLimit() {
    return 1000;
  }

  @Provides
  @Singleton
  @Named("projectGitHubUrl")
  static String getProjectGitHubUrl() {
    return "https://github.com/xemantic/github-users-web";
  }

  @Provides
  @Singleton
  static Logger logger() {
    return Logger.getLogger(AnkhExceptionHandler.class.getName());
  }

  @Binds
  @Singleton
  abstract ErrorMessageProvider errorMessageProvider(WebErrorMessageProvider provider);

  @Binds
  @Singleton
  abstract Thread.UncaughtExceptionHandler uncaughtExceptionHandler(AnkhExceptionHandler handler);

  @Binds
  @Singleton
  abstract UserService getUserService(WebUserService service);

  @Binds
  @Singleton
  abstract DrawerView getDrawerView(WebDrawerView view);

  @Binds
  @Singleton
  abstract SnackbarView getSnackbarView(WebSnackbarView view);

  @Binds
  @Singleton
  abstract UserQueryView getUserQueryView(WebUserQueryView view);

  @Binds
  @Singleton
  abstract UserListView userListView(WebUserListView view);

  @Binds
  abstract UserView getUserView(WebUserView view);

}
