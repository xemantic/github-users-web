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

package com.xemantic.ankh.components;

import com.xemantic.ankh.mdc.MDCSnackbar;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Convenient wrapper around {@link MDCSnackbar}. As there should be only one snackbar,
 * it will provide singleton component operating over provided {@link MDCSnackbar}
 * instance. If you need a snackbar then just {@link Inject} this one in your components
 * and use {@link #show(String)} method.
 *
 * @author morisil
 */
@Singleton
public class Snackbar {

  private final MDCSnackbar mdcSnackbar;

  @Inject
  public Snackbar(MDCSnackbar mdcSnackbar) {
    this.mdcSnackbar = mdcSnackbar;
  }

  public void show(String message) {
    MDCSnackbar.Data data = new MDCSnackbar.Data();
    data.message = message;
    mdcSnackbar.show(data);
  }

}
