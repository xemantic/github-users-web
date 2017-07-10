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
  This module is exporting generated incrementaldom templates module making
  it accessible to other JS libraries and the code compiled by GWT in particular.

  After adding new template in the templates.soy file, the template name should be
  exported at the end of this file. This process might be also automated in the
  future.

  Note: this module might be not needed at all if there is a way to depend on
  goog.module from the GWT code without globally exported symbols. Some more research
  is needed here.
 */

goog.module("com.xemantic.githubusers.web.view.template");
goog.require("com.xemantic.githubusers.web.view.template.incrementaldom");

function getModule() {
  return goog.module.get("com.xemantic.githubusers.web.view.template.incrementaldom");
}

var mod = getModule();

goog.exportSymbol("com.xemantic.githubusers.web.view.template.header", mod.header);
goog.exportSymbol("com.xemantic.githubusers.web.view.template.main", mod.main);
goog.exportSymbol("com.xemantic.githubusers.web.view.template.drawer", mod.drawer);
goog.exportSymbol("com.xemantic.githubusers.web.view.template.snackbar", mod.snackbar);
goog.exportSymbol("com.xemantic.githubusers.web.view.template.userList", mod.userList);
goog.exportSymbol("com.xemantic.githubusers.web.view.template.user", mod.user);
