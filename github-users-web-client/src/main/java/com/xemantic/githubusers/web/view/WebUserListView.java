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

import com.xemantic.ankh.Elements;
import com.xemantic.ankh.IncrementalDom;
import com.xemantic.githubusers.logic.event.Trigger;
import com.xemantic.githubusers.logic.view.UserListView;
import com.xemantic.githubusers.logic.view.UserView;
import elemental2.dom.Element;
import elemental2.dom.HTMLButtonElement;
import elemental2.dom.HTMLElement;
import mdc.gridList.MDCGridList;
import rx.Observable;

import javax.inject.Inject;

/**
 * Web version of the {@link UserListView}.
 *
 * @author morisil
 */
public class WebUserListView implements UserListView, WebView {

  private final Element element;

  private final HTMLElement userTiles;

  private final HTMLButtonElement loadMoreButton;

  private final Observable<Trigger> loadMore$;

  @Inject
  public WebUserListView() {
    element = IncrementalDom.create(Templates::userList);
    Elements elements = new Elements(element);
    /* it will center the grid and make it react to window resizing */
    MDCGridList.attachTo(elements.get(".mdc-grid-list"));
    userTiles = elements.get(".user-tiles");
    loadMoreButton = elements.getButton(".load-more-action");
    loadMore$ = Elements.observeClicksOf(loadMoreButton);
  }

  @Override
  public void add(UserView userView) {
    Element tile = ((WebView) userView).asElement();
    userTiles.appendChild(tile);
  }

  @Override
  public void clear() {
    Elements.removeChildren(userTiles);
  }

  @Override
  public Observable<Trigger> observeLoadMore() {
    return loadMore$;
  }

  @Override
  public void enableLoadMore(boolean enabled) {
    loadMoreButton.disabled = ! enabled;
  }

  @Override
  public Element asElement() {
    return element;
  }

}
