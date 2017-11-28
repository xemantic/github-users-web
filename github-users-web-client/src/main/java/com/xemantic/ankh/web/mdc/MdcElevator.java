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

package com.xemantic.ankh.web.mdc;

import com.google.common.base.Preconditions;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import elemental2.dom.Element;

import java.util.Objects;

/**
 * Controls {@code mdc-elevation} class.
 *
 * @author morisil
 */
public class MdcElevator {

  private final static RegExp ELEVATION_CLASS_REG_EXP = RegExp.compile("^mdc-elevation--z([0-9][0-9]?)$");

  private final Element element;

  private final int initialLevel;

  private int currentLevel;

  public MdcElevator(Element element) {
    this.element = Objects.requireNonNull(element);
    initialLevel = getLevel(element);
    currentLevel = initialLevel;
  }

  public static int getLevel(Element element) {
    int level = 0;
    for (int i = 0, length = element.classList.getLength(); i < length; i++) {
      String klass = element.classList.getAt(i);
      level += getLevel(klass);
      if (level > 0) {
        break;
      }
    }
    return level;
  }

  public void liftTo(int level) {
    Preconditions.checkArgument((level >= 0) && (level <= 24), "level range: 0..24");
    if (currentLevel == level) {
      return;
    }
    if (currentLevel > 0) {
      element.classList.remove("mdc-elevation--z" + currentLevel);
    }
    if (level > 0) {
      element.classList.add("mdc-elevation--z" + level);
    }
    currentLevel = level;
  }

  public void liftToInitialLevel() {
    liftTo(initialLevel);
  }

  private static int getLevel(String klass) {
    MatchResult result = ELEVATION_CLASS_REG_EXP.exec(klass);
    return result != null
        ? Integer.parseInt(result.getGroup(1))
        : 0;
  }

  public static OverBuilder whenOver(Element element) {
    return new OverBuilder(element);
  }

  public static class OverBuilder {

    private final Element element;

    private OverBuilder(Element element) {
      this.element = element;
    }

    public void liftTo(int level) {
      MdcElevator elevator = new MdcElevator(element);
      element.addEventListener("mouseenter", e -> elevator.liftTo(level));
      element.addEventListener("mouseleave", e -> elevator.liftToInitialLevel());
    }

  }

}
