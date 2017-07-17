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

import elemental2.dom.DomGlobal;
import elemental2.dom.Element;
import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsMethod;

/**
 * Utility class exposing selected methods of Google's
 * <a href="http://google.github.io/incremental-dom/#about">Incremental DOM</a>
 * library.
 *
 * @author morisil
 */
public final class IncrementalDom {

  private IncrementalDom() { /* util class, non-instantiable */ }

  @SuppressWarnings("unchecked")
  public static <E extends Element> E create(Patcher patcher) {
    Element parent = DomGlobal.document.createElement("div");
    patch(parent, patcher);
    return (E) parent.firstChild;
  }

  @JsMethod(namespace = "com.xemantic.ankh.incrementaldom", name = "patch")
  public static native void patch(Element element, Patcher patcher);

  @JsMethod(namespace = "com.xemantic.ankh.incrementaldom", name = "patchOuter")
  public static native void patchOuter(Element element, Patcher patcher);

  @JsFunction
  @FunctionalInterface
  public interface Patcher {
    void apply();
  }

}
