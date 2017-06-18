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
import com.xemantic.githubusers.logic.model.User;
import com.xemantic.githubusers.logic.view.UserView;
import com.xemantic.ankh.incrementaldom.IncrementalDom;
import com.xemantic.githubusers.web.template.Templates;
import com.xemantic.githubusers.web.template.UserTileParams;
import elemental2.dom.DomGlobal;
import elemental2.dom.Element;
import rx.Observable;

/**
 * Web version of the {@link UserView}.
 *
 * @author morisil
 */
public class WebUserView implements UserView {

  // TODO rework UserView creation to be more explicitly dependant on parent state

  private final Element tile;
  {
    Element parent = DomGlobal.document.querySelector("#userTiles");
    tile = DomGlobal.document.createElement("li");
    parent.appendChild(tile);
  }

  private final Observable<Trigger> selection$ = RxElemental2.fromEvent(tile, RxElemental2.click)
      .map(event -> Trigger.INSTANCE);

  @Override
  public void displayUser(User user) {
    UserTileParams data = new UserTileParams();
    data.login = user.getLogin();
    data.avatarUrl = user.getAvatarUrl() + "&s=200"; // we can rescale server side here
    IncrementalDom.patchOuter(tile, () -> Templates.userTile(data));

    // it would be also possible to patch initially without data and patch it again here for update
  }

  @Override
  public Observable<Trigger> observeSelection() {
    return selection$;
  }

  Element asElement() {
    return tile;
  }

}
