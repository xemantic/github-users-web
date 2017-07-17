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

import jsinterop.annotations.JsMethod;

/**
 * Util class providing access to the {@code com.xemantic.githubusers.app}
 * JS module. See the {@code github-users-web-server} module and
 * the {@code src/main/webapp/js/modules/com/xemantic/githubusers/web/app.js}
 * file for details.
 *
 * @author morisil
 */
public final class GitHubUsersJsApp {

  private GitHubUsersJsApp() { /* util class, non-instantiable */ }

  @JsMethod(namespace = "com.xemantic.githubusers.web.app", name = "start")
  public static native void start();

}
