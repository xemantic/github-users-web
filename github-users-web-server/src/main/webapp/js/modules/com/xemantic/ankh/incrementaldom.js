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
  This module is exporting a selection of incremental-dom module API.
  In the GWT code it is represented as IncrementalDom util class.
 */

goog.module("com.xemantic.ankh.incrementaldom");
goog.module.declareLegacyNamespace();

const _mod = goog.require("incrementaldom");

exports.patch = _mod.patch;
exports.patchOuter = _mod.patchOuter;

goog.exportSymbol("com.xemantic.ankh.incrementaldom.patch", _mod.patch);
goog.exportSymbol("com.xemantic.ankh.incrementaldom.patchOuter", _mod.patchOuter);
