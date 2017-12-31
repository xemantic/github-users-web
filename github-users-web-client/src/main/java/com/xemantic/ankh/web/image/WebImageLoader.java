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

package com.xemantic.ankh.web.image;

import elemental2.dom.Image;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.Objects;

/**
 * @author morisil
 */
public class WebImageLoader implements ImageLoader<Image> {

  private final Provider<Image> imageProvider;

  // TODO Provider for testability
  @Inject
  public WebImageLoader(Provider<Image> imageProvider) {
    this.imageProvider = imageProvider;
  }

  @Override
  public Single<Image> load(String url) {
    Objects.requireNonNull(url);
    return Single.create(emitter -> {
      Image image = imageProvider.get();
      image.onload = event -> {
        emitter.onSuccess(image);
        return null;
      };
      image.onerror = event -> {
        // TODO it should be specific exception
        // TODO which property of ((ProgressEvent) event) indicates error
        emitter.onError(new ImageLoadingException("", url));
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
