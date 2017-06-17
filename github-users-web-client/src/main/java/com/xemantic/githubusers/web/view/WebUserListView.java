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

import com.intendia.rxgwt.elemental2.RxElemental2;
import com.xemantic.githubusers.logic.eventbus.Trigger;
import com.xemantic.githubusers.logic.view.UserListView;
import com.xemantic.githubusers.logic.view.UserView;
import com.xemantic.ankh.elemental.Elements;
import elemental2.dom.Element;
import elemental2.dom.HTMLButtonElement;
import elemental2.dom.HTMLElement;
import rx.Observable;

import javax.inject.Inject;

/**
 * @author morisil
 */
public class WebUserListView implements UserListView {

  private final HTMLElement userTiles;

  private final HTMLButtonElement loadMore;

  private final Observable<Trigger> loadMore$;

  @Inject
  public WebUserListView() {
    userTiles = (HTMLElement) Elements.query("#userTiles");
    loadMore = (HTMLButtonElement) Elements.query("#loadMore");
    loadMore$ = RxElemental2.fromEvent(loadMore, RxElemental2.click) // TODO do I need touch as well ?
        .map(event -> Trigger.INSTANCE);
  }

  @Override
  public void add(UserView userView) {
    Element tile = ((WebUserView) userView).asElement();
    userTiles.appendChild(tile);
  }

  @Override
  public void clear() {
    while (userTiles.hasChildNodes()) {
      userTiles.removeChild(userTiles.lastChild);
    }
  }

  @Override
  public Observable<Trigger> observeLoadMore() {
    return loadMore$;
  }

  @Override
  public void enableLoadMore(boolean enabled) {
    loadMore.disabled = ! enabled;
  }

}
