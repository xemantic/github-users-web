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

package com.xemantic.ankh.web;

import elemental2.dom.DocumentFragment;
import elemental2.dom.DomGlobal;
import elemental2.dom.Element;
import elemental2.dom.Node;
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
    DocumentFragment fragment = DomGlobal.document.createDocumentFragment();
    patch(fragment, patcher);
    return (E) fragment.firstChild;
  }

  @JsMethod(namespace = "com.xemantic.ankh.web.incrementaldom", name = "patch")
  public static native void patch(Node element, Patcher patcher);

  @JsMethod(namespace = "com.xemantic.ankh.web.incrementaldom", name = "patchOuter")
  public static native void patchOuter(Node element, Patcher patcher);

  @JsFunction
  @FunctionalInterface
  public interface Patcher {
    void apply();
  }

}
