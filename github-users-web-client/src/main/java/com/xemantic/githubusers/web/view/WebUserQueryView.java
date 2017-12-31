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

import com.intendia.rxgwt2.elemento.RxElemento;
import com.xemantic.githubusers.logic.user.UserQueryView;
import elemental2.dom.HTMLInputElement;
import io.reactivex.Observable;
import org.jboss.gwt.elemento.core.EventType;

import javax.inject.Inject;

/**
 * Web version of the {@link UserQueryView}.
 *
 * @author morisil
 */
public class WebUserQueryView implements UserQueryView {

  private final Observable<String> query$;

  @Inject
  public WebUserQueryView(WebScreen webScreen) {
    HTMLInputElement input = webScreen.getUserQueryInputElement();
    query$ = RxElemento.fromEvent(input, EventType.input)
        .map(event -> input.value);
  }

  @Override
  public Observable<String> queryInput$() {
    return query$;
  }

}
