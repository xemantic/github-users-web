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

import com.xemantic.ankh.shared.snackbar.SnackbarPresenter;
import com.xemantic.ankh.shared.snackbar.SnackbarView;
import com.xemantic.ankh.web.error.GwtErrorHandler;
import com.xemantic.githubusers.logic.drawer.DrawerPresenter;
import com.xemantic.githubusers.logic.drawer.DrawerView;
import com.xemantic.githubusers.logic.user.UserListPresenter;
import com.xemantic.githubusers.logic.user.UserListView;
import com.xemantic.githubusers.logic.user.UserQueryPresenter;
import com.xemantic.githubusers.web.navigation.UserSelectionNavigator;
import com.xemantic.githubusers.web.view.WebAppView;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * The main app starting all the subsystems.
 * It will be instantiated by Dagger with all the dependencies injected.
 *
 * @author morisil
 */
@Singleton
public class GitHubUsersApp {

  @Inject GwtErrorHandler gwtErrorHandler;
  @Inject UserSelectionNavigator userSelectionNavigator;
  @Inject UserQueryPresenter userQueryPresenter;
  @Inject UserListView userListView;
  @Inject UserListPresenter userListPresenter;
  @Inject WebAppView appView;
  @Inject DrawerView drawerView;
  @Inject DrawerPresenter drawerPresenter;
  @Inject SnackbarView snackbarView;
  @Inject SnackbarPresenter snackbarPresenter;

  @Inject
  public GitHubUsersApp() { /* Dagger needs constructor annotated with @Inject */ }

  public void start() {
    gwtErrorHandler.start();
    snackbarPresenter.start();
    userSelectionNavigator.start();
    drawerPresenter.start();
    userListPresenter.start();
    userQueryPresenter.start();
    appView.setDrawerView(drawerView);
    appView.setSnackbarView(snackbarView);
    appView.setUserListView(userListView);
    GitHubUsersJsApp.start();
  }

}
