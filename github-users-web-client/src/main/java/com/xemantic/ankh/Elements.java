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

package com.xemantic.ankh;

import com.intendia.rxgwt.elemental2.RxElemental2;
import com.xemantic.githubusers.logic.eventbus.Trigger;
import elemental2.dom.Element;
import elemental2.dom.HTMLButtonElement;
import elemental2.dom.HTMLElement;
import mdc.ripple.MDCRipple;
import rx.Observable;

import java.util.Objects;

/**
 * Utilities for {@link Element}s and {@code MDC} components.
 *
 * @author morisil
 */
public class Elements {

  private final Element element;

  public Elements(Element element) {
    this.element = Objects.requireNonNull(element);
  }

  public static Observable<Trigger> observeClicksOf(Element element) {
    Objects.requireNonNull(element);
    return RxElemental2.fromEvent(element, RxElemental2.click).map(e -> Trigger.INSTANCE);
  }

  public static void removeChildren(Element element) {
    Objects.requireNonNull(element);
    while (element.hasChildNodes()) {
      element.removeChild(element.lastChild);
    }
  }

  public HTMLElement get(String selector) {
    return (HTMLElement) element.querySelector(selector);
  }

  public HTMLButtonElement getButton(String selector) {
    HTMLButtonElement button = (HTMLButtonElement) element.querySelector(selector);
    MDCRipple.attachTo(button);
    return button;
  }

  public Observable<Trigger> observeClicksOf(String selector) {
    Element child = element.querySelector(selector);
    return RxElemental2.fromEvent(child, RxElemental2.click).map(e -> Trigger.INSTANCE);
  }

}
