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

import elemental2.dom.Image;
import io.reactivex.Single;

import java.util.Objects;

/**
 * Image utilities.
 *
 * @author morisil
 */
public final class Images {

  private Images() { /* util class, non-instantiable */ }

  public static Single<Image> preload(String url) {
    Objects.requireNonNull(url);
    return Single.create(emitter -> {
      Image image = new Image();
      image.onload = event -> {
        emitter.onSuccess(image);
        return null;
      };
      image.onerror = event -> {
        // TODO it should be specific exception
        emitter.onError(new RuntimeException("Could not load image: " + url));
        return null;
      };
      emitter.setCancellable(() -> {
        image.onerror = null;
        image.src = "";
      });
      image.src = url;
    });
  }

}
