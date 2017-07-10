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
import com.xemantic.ankh.incrementaldom.IncrementalDom;
import com.xemantic.githubusers.logic.eventbus.Trigger;
import com.xemantic.githubusers.logic.view.DrawerView;
import elemental2.dom.Element;
import mdc.drawer.MDCTemporaryDrawer;
import rx.Observable;

import javax.inject.Inject;

/**
 * Web version of the {@link DrawerView}.

 * @author morisil
 */
public class WebDrawerView implements DrawerView, WebView {

  private final Element element;

  private final MDCTemporaryDrawer drawer;

  private final Observable<Trigger> openDrawer$;

  private final Observable<Trigger> readAbout$;

  private final Observable<Trigger> openProjectOnGithub$;

  private final Observable<Trigger> selectLanguage$;

  @Inject
  public WebDrawerView(WebScreen webScreen) {
    element = IncrementalDom.create(Templates::drawer);
    drawer = new MDCTemporaryDrawer(element);
    Elements elements = new Elements(element);
    Element drawerHandle = webScreen.getDrawerHandleElement();
    openDrawer$ = Elements.observeClicksOf(drawerHandle);
//    doesn't seem to work, see issue #1
//    MDCRipple ripple = new MDCRipple(drawerHandle);
//    ripple.unbounded = true;
    readAbout$ = elements.observeClicksOf(".read-about-action");
    openProjectOnGithub$ = elements.observeClicksOf(".open-project-on-github-action");
    selectLanguage$ = elements.observeClicksOf(".select-language-action");
  }

  @Override
  public Observable<Trigger> observeOpenDrawerIntent() {
    return openDrawer$;
  }

  @Override
  public Observable<Trigger> observeReadAboutIntent() {
    return readAbout$;
  }

  @Override
  public Observable<Trigger> observeOpenProjectOnGitHubIntent() {
    return openProjectOnGithub$;
  }

  @Override
  public Observable<Trigger> observeSelectLanguageIntent() {
    return selectLanguage$;
  }

  @Override
  public void openDrawer(boolean open) {
    drawer.open = open;
  }

  @Override
  public Element asElement() {
    return element;
  }

}
