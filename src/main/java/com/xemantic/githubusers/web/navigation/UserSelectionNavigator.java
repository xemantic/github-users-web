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

import com.google.gwt.user.client.Window;
import com.xemantic.githubusers.logic.event.UserSelectedEvent;
import com.xemantic.githubusers.logic.eventbus.EventBus;

import javax.inject.Inject;

/**
 * @author morisil
 */
public class UserSelectionNavigator {

  private final EventBus eventBus;

  @Inject
  public UserSelectionNavigator(EventBus eventBus) {
    this.eventBus = eventBus;
  }

  public void start() {
    eventBus.observe(UserSelectedEvent.class)
        .subscribe(event -> Window.open( // TODO replace this window with element window
            event.getUser().getHtmlUrl(), "_self", null)
        );
  }

}