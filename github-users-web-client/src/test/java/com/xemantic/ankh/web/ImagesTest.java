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
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Test of the {@link Images}.
 *
 * @author morisil
 */
public class ImagesTest {

  @Test(expected = NullPointerException.class)
  public void preload_nullUrl_shouldThrowException() {
    // when
    Images.preload(null);

    // then should fail
  }

  @Test
  public void preload_properUrl_shouldReturnSingle() {
    // given
    String url = "http://foo.com/image.png";

    // when
    Single<Image> single = Images.preload(url);

    // then
    assertThat(single, notNullValue());
  }

}