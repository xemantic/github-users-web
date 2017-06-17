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
  This file is needed to avoid renaming properties of data objects used inside
  compiled Closure Templates (Incremental DOM flavor)

  Note: in the future this file can be auto-generated based on the function
  Params in the generated templates.js file. Most likely it also won't be needed
  at all when GWT compiled JS file will be process by Closure Compiler as well.
*/
var _app = {}; // the _app prefix is randomly chosen, but somehow it works

/**
 * @typedef {{
 *  login: *,
 *  avatarUrl: *
 * }}
 */
_app.templates.userTile.params;
