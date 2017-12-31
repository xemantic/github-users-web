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

package com.xemantic.ankh.web.driver;

import com.xemantic.ankh.shared.driver.UrlOpener;
import elemental2.dom.DomGlobal;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Web version of the {@link UrlOpener}.
 *
 * @author morisil
 */
@Singleton
public class WebUrlOpener implements UrlOpener {

  @Inject
  public WebUrlOpener() { /* empty constructor annotated with @Inject is required by dagger */ }

  @Override
  public void openUrl(String url) {
    DomGlobal.window.open(url);
  }

}
