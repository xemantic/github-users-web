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

package com.xemantic.githubusers.web.navigation;

import com.xemantic.ankh.shared.driver.UrlOpener;
import com.xemantic.ankh.shared.presenter.Presenter;
import com.xemantic.githubusers.logic.event.UserSelectedEvent;
import io.reactivex.Observable;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Will open user profile page if the user is selected.
 *
 * @author morisil
 */
@Singleton
public class UserSelectionNavigator extends Presenter {

  @Inject
  public UserSelectionNavigator(
      Observable<UserSelectedEvent> userSelected$,
      UrlOpener urlOpener) {

    super(userSelected$
        .map(event -> event.getUser().getHtmlUrl())
        .doOnNext(urlOpener::openUrl)
    );
  }

}
