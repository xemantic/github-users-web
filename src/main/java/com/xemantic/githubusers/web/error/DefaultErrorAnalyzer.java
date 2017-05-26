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

package com.xemantic.githubusers.web.error;

import com.xemantic.githubusers.logic.error.ErrorAnalyzer;
import com.xemantic.githubusers.logic.eventbus.EventBus;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author morisil
 */
@Singleton
public class DefaultErrorAnalyzer implements ErrorAnalyzer {

  @Inject
  public DefaultErrorAnalyzer() {}

  @Override
  public boolean isRecoverable(Throwable throwable) {
    return true; // for now always true
  }

}
