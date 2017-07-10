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

import com.xemantic.ankh.incrementaldom.IncrementalDom;
import elemental2.dom.DomGlobal;
import elemental2.dom.Element;
import elemental2.dom.HTMLInputElement;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Abstraction over HTML page hosting the app.
 * <p>
 *   Note: it is marked as singleton and will be provided early
 *   as many other components depend on the {@code WebScreen} to
 *   be injected.
 * </p>
 *
 * @author morisil
 */
@Singleton
public class WebScreen {

  private final Element header;

  private final Element userListContainer;

  @Inject
  public WebScreen() {
    header = DomGlobal.document.querySelector("header");
    Element main = DomGlobal.document.querySelector("main");
    IncrementalDom.patchOuter(header, Templates::header);
    IncrementalDom.patchOuter(main, Templates::main);
    userListContainer = main.querySelector(".user-list-container");
  }

  public void insertUserList(Element userList) {
    userListContainer.appendChild(userList);
  }

  public void insertInBody(Element element) {
    DomGlobal.document.body.appendChild(element);
  }

  public HTMLInputElement getUserQueryInputElement() {
    return (HTMLInputElement) header.querySelector(".user-query-input");
  }

  public Element getDrawerHandleElement() {
    return header.querySelector(".drawer-handle");
  }

}
