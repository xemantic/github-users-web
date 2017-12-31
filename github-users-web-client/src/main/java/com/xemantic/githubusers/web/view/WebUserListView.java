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

import com.xemantic.ankh.shared.event.Trigger;
import com.xemantic.ankh.web.Elements;
import com.xemantic.ankh.web.IncrementalDom;
import com.xemantic.githubusers.logic.user.UserListView;
import com.xemantic.githubusers.logic.user.UserView;
import elemental2.dom.Element;
import elemental2.dom.HTMLButtonElement;
import elemental2.dom.HTMLElement;
import io.reactivex.Observable;

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

  private final Observable<Trigger> loadMoreActions$;

  @Inject
  public WebUserListView() {
    element = IncrementalDom.create(Templates::userList);
    Elements elements = new Elements(element);
    userTiles = elements.get(".user-tiles");
    loadMoreButton = elements.getButton(".load-more-action");
    loadMoreActions$ = Elements.observeClicksOf(loadMoreButton);
  }

  @Override
  public void add(UserView userView) {
    Element tile = ((WebView) userView).asElement();
    tile.parentNode.removeChild(tile);
    userTiles.appendChild(tile);
  }

  @Override
  public void clear() {
    Elements.removeChildren(userTiles);
  }

  @Override
  public Observable<Trigger> loadMoreIntent$() {
    return loadMoreActions$;
  }

  @Override
  public void enableLoadMore(boolean enabled) {
    loadMoreButton.disabled = ! enabled;
  }

  @Override
  public void loadingFirstPage(boolean loading) {
    // TODO does nothing for now, should disable all displayed elements
  }

  @Override
  public Element asElement() {
    return element;
  }

}
