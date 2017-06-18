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

/*
  Main application module starting non-GWT part of the application.
 */

goog.module("com.xemantic.githubusers.web.app");

// List of modules not necessarily needed by this app module, but required by other parts of GWT code
goog.require("com.xemantic.ankh.incrementaldom");
goog.require("com.xemantic.githubusers.web.template");

function start() {

  mdc.autoInit();

  /* menu */
  var drawerElement = document.querySelector(".mdc-temporary-drawer");
  var drawer = new mdc.drawer.MDCTemporaryDrawer(drawerElement);
  document.querySelector(".menu")
      .addEventListener("click", () => drawer.open = true);

  /* it will center the grid and make it react to window resizing */
  mdc.gridList.MDCGridList.attachTo(
    document.querySelector(".mdc-grid-list")
  );
}

goog.exportSymbol("com.xemantic.githubusers.web.GitHubUsersJsApp.start", start);
