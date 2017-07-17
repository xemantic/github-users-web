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

import com.xemantic.ankh.IncrementalDom;
import com.xemantic.githubusers.logic.view.SnackbarView;
import elemental2.dom.Element;
import mdc.snackbar.MDCSnackbar;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Web version of the {@link SnackbarView}.

 * @author morisil
 */
@Singleton
public class WebSnackbarView implements SnackbarView, WebView {

  private final Element element;

  private MDCSnackbar snackbar;

  @Inject
  public WebSnackbarView() {
    element = IncrementalDom.create(Templates::snackbar);
    snackbar = new MDCSnackbar(element);
  }

  @Override
  public void show(String message) {
    MDCSnackbar.Data data = new MDCSnackbar.Data();
    data.message = message;
    snackbar.show(data);
  }

  @Override
  public Element asElement() {
    return element;
  }

}
