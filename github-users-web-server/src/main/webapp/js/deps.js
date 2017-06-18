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
  I still haven't found a way to autogenerate this file. Fortunately it's
  relatively convenient to manage it by hand.
 */
goog.addDependency("../../../../js/lib/incemental-dom/incremental-dom-closure.js", ['incrementaldom'], [], true);
goog.addDependency("../../../../js/lib/closure-templates/soyutils_idom.js", ['soy.idom'], ['goog.soy.data.SanitizedHtml', 'incrementaldom', 'goog.soy'], true);
goog.addDependency("../../../../js/generated/templates.js", ['com.xemantic.githubusers.web.template.incrementaldom'], ['incrementaldom', 'soy.idom'], true);
goog.addDependency("../../../../js/modules/com/xemantic/ankh/incrementaldom.js", ['com.xemantic.ankh.incrementaldom'], ['incrementaldom'], true);
goog.addDependency("../../../../js/modules/com/xemantic/githubusers/web/template.js", ['com.xemantic.githubusers.web.template'], ['com.xemantic.githubusers.web.template.incrementaldom'], true);
goog.addDependency("../../../../js/modules/com/xemantic/githubusers/web/app.js", ['com.xemantic.githubusers.web.app'], ['com.xemantic.githubusers.web.template', 'com.xemantic.ankh.incrementaldom'], true);
