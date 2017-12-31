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

package com.xemantic.githubusers.web.view;

import com.xemantic.ankh.shared.snackbar.SnackbarView;
import com.xemantic.githubusers.logic.drawer.DrawerView;
import com.xemantic.githubusers.logic.user.UserListView;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * View reflecting visual component hierarchy of this app.
 *
 * @author morisil
 */
@Singleton
public class WebAppView {

  private final WebScreen webScreen;

  @Inject
  public WebAppView(WebScreen webScreen) {
    this.webScreen = webScreen;
  }

  public void setDrawerView(DrawerView view) {
    insertInBody(view);
  }

  public void setSnackbarView(SnackbarView view) {
    insertInBody(view);
  }

  public void setUserListView(UserListView view) {
    webScreen.insertUserList(((WebView) view).asElement());
  }

  private void insertInBody(Object view) {
    webScreen.insertInBody(((WebView) view).asElement());
  }

}
