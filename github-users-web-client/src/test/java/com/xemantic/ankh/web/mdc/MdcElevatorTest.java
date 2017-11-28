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

import elemental2.dom.DOMTokenList;
import elemental2.dom.Element;
import elemental2.dom.EventListener;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * Test of the {@link MdcElevator}.
 *
 * @author morisil
 */
public class MdcElevatorTest {

  @SuppressWarnings("ConstantConditions")
  @Test(expected = NullPointerException.class)
  public void new_nullElement_shouldThrowException() {
    // given
    Element element = null;

    // when
    new MdcElevator(element);

    // then exception should be thrown
  }

  @Test
  public void new_noInteraction_shouldNotChangeCssClasses() {
    // given
    Element element = mock(Element.class);
    element.classList = mock(DOMTokenList.class);
    given(element.classList.getLength()).willReturn(0);

    // when
    new MdcElevator(element); // and no interaction

    // then
    verify(element.classList).getLength();
    verifyNoMoreInteractions(element, element.classList);
  }

  @Test
  public void getLevel_noCssClasses_shouldReturnLevel0() {
    // given
    Element element = mock(Element.class);
    element.classList = mock(DOMTokenList.class);
    given(element.classList.getLength()).willReturn(0);

    // when
    int level = MdcElevator.getLevel(element);

    // then
    verify(element.classList).getLength();
    verifyNoMoreInteractions(element, element.classList);
    assertThat(level, is(0));
  }

  @Test
  public void getLevel_1NonElevationCssClass_shouldReturnLevel0() {
    // given
    Element element = mock(Element.class);
    element.classList = mock(DOMTokenList.class);
    given(element.classList.getLength()).willReturn(1);
    given(element.classList.getAt(0)).willReturn("foo");

    // when
    int level = MdcElevator.getLevel(element);

    // then
    verify(element.classList).getLength();
    verify(element.classList).getAt(0);
    verifyNoMoreInteractions(element, element.classList);
    assertThat(level, is(0));
  }

  @Test
  public void getLevel_1ElevationCssClass_shouldReturnDefinedElevation() {
    // given
    Element element = mock(Element.class);
    element.classList = mock(DOMTokenList.class);
    given(element.classList.getLength()).willReturn(1);
    given(element.classList.getAt(0)).willReturn("mdc-elevation--z12");

    // when
    int level = MdcElevator.getLevel(element);

    // then
    verify(element.classList).getLength();
    verify(element.classList).getAt(0);
    verifyNoMoreInteractions(element, element.classList);
    assertThat(level, is(12));
  }

  @Test
  public void liftTo_fromLevel0_shouldAddElevationClassName() {
    // given
    Element element = mock(Element.class);
    element.classList = mock(DOMTokenList.class);
    MdcElevator elevator = new MdcElevator(element);

    // when
    elevator.liftTo(8);

    // then
    InOrder inOrder = inOrder(element.classList);
    inOrder.verify(element.classList).getLength();
    inOrder.verify(element.classList).add("mdc-elevation--z8");
    verifyNoMoreInteractions(element, element.classList);
  }

  @Test
  public void liftTo_fromNon0Level_shouldReplaceElevationClassNames() {
    // given
    Element element = mock(Element.class);
    element.classList = mock(DOMTokenList.class);
    given(element.classList.getLength()).willReturn(1);
    given(element.classList.getAt(0)).willReturn("mdc-elevation--z2");
    MdcElevator elevator = new MdcElevator(element);

    // when
    elevator.liftTo(8);

    // then
    InOrder inOrder = inOrder(element.classList);
    inOrder.verify(element.classList).getLength();
    inOrder.verify(element.classList).getAt(0);
    inOrder.verify(element.classList).remove("mdc-elevation--z2");
    inOrder.verify(element.classList).add("mdc-elevation--z8");
    verifyNoMoreInteractions(element, element.classList);
  }

  @Test
  public void liftTo_sameLevel_shouldDoNothing() {
    // given
    Element element = mock(Element.class);
    element.classList = mock(DOMTokenList.class);
    given(element.classList.getLength()).willReturn(1);
    given(element.classList.getAt(0)).willReturn("mdc-elevation--z2");
    MdcElevator elevator = new MdcElevator(element);

    // when
    elevator.liftTo(2);

    // then
    verify(element.classList).getLength();
    verify(element.classList).getAt(0);
    verifyNoMoreInteractions(element, element.classList);
  }

  @Test(expected = IllegalArgumentException.class)
  public void liftTo_negativeLevel_shouldThrowException() {
    // given
    Element element = mock(Element.class);
    element.classList = mock(DOMTokenList.class);
    given(element.classList.getLength()).willReturn(0);
    MdcElevator elevator = new MdcElevator(element);

    // when
    elevator.liftTo(-1);

    // then exception should be thrown
  }

  @Test(expected = IllegalArgumentException.class)
  public void liftTo_levelAboveLimit_shouldThrowException() {
    // given
    Element element = mock(Element.class);
    element.classList = mock(DOMTokenList.class);
    given(element.classList.getLength()).willReturn(0);
    MdcElevator elevator = new MdcElevator(element);

    // when
    elevator.liftTo(25);

    // then exception should be thrown
  }

  @Test
  public void liftToInitialLevel_alreadyOnInitialLevel0_shouldNotChangeCssClasses() {
    // given
    Element element = mock(Element.class);
    element.classList = mock(DOMTokenList.class);
    given(element.classList.getLength()).willReturn(0);
    MdcElevator elevator = new MdcElevator(element);

    // when
    elevator.liftToInitialLevel();

    // then
    verify(element.classList).getLength();
    verifyNoMoreInteractions(element, element.classList);
  }

  @Test
  public void liftToInitialLevel_alreadyOnInitialLevelNon0_shouldNotChangeCssClasses() {
    // given
    Element element = mock(Element.class);
    element.classList = mock(DOMTokenList.class);
    given(element.classList.getLength()).willReturn(1);
    given(element.classList.getAt(0)).willReturn("mdc-elevation--z1");
    MdcElevator elevator = new MdcElevator(element);

    // when
    elevator.liftToInitialLevel();

    // then
    verify(element.classList).getLength();
    verify(element.classList).getAt(0);
    verifyNoMoreInteractions(element, element.classList);
  }

  @Test
  public void liftToInitialLevel_whereInitialLevel0_shouldRemoveElevationCssClass() {
    // given
    Element element = mock(Element.class);
    element.classList = mock(DOMTokenList.class);
    given(element.classList.getLength()).willReturn(0);
    MdcElevator elevator = new MdcElevator(element);
    elevator.liftTo(1);

    // when
    elevator.liftToInitialLevel();

    // then
    InOrder inOrder = inOrder(element.classList);
    inOrder.verify(element.classList).getLength();
    inOrder.verify(element.classList).add("mdc-elevation--z1");
    inOrder.verify(element.classList).remove("mdc-elevation--z1");
    verifyNoMoreInteractions(element, element.classList);
  }

  @Test
  public void liftToInitialLevel_whereInitialLevelNon0_shouldReplaceElevationCssClass() {
    // given
    Element element = mock(Element.class);
    element.classList = mock(DOMTokenList.class);
    given(element.classList.getLength()).willReturn(1);
    given(element.classList.getAt(0)).willReturn("mdc-elevation--z1");
    MdcElevator elevator = new MdcElevator(element);
    elevator.liftTo(2);

    // when
    elevator.liftToInitialLevel();

    // then
    InOrder inOrder = inOrder(element.classList);
    inOrder.verify(element.classList).getLength();
    inOrder.verify(element.classList).getAt(0);
    inOrder.verify(element.classList).remove("mdc-elevation--z1");
    inOrder.verify(element.classList).add("mdc-elevation--z2");
    inOrder.verify(element.classList).remove("mdc-elevation--z2");
    inOrder.verify(element.classList).add("mdc-elevation--z1");
    verifyNoMoreInteractions(element, element.classList);
  }

  @Test
  public void whenOver_noInitialCssElevationSpecified_shouldAddAndRemoveElevation() {
    // given
    Element element = mock(Element.class);
    element.classList = mock(DOMTokenList.class);
    given(element.classList.getLength()).willReturn(0);
    MdcElevator.whenOver(element).liftTo(8);
    ArgumentCaptor<EventListener> enterCaptor = ArgumentCaptor.forClass(EventListener.class);
    ArgumentCaptor<EventListener> leaveCaptor = ArgumentCaptor.forClass(EventListener.class);
    verify(element).addEventListener(eq("mouseenter"), enterCaptor.capture());
    verify(element).addEventListener(eq("mouseleave"), leaveCaptor.capture());

    // when
    enterCaptor.getValue().handleEvent(null); // event payload doesn't matter
    leaveCaptor.getValue().handleEvent(null);

    // then
    InOrder inOrder = inOrder(element.classList);
    inOrder.verify(element.classList).getLength();
    inOrder.verify(element.classList).add("mdc-elevation--z8");
    inOrder.verify(element.classList).remove("mdc-elevation--z8");
    verifyNoMoreInteractions(element, element.classList);
  }

  @Test
  public void whenOver_initialCssElevationSpecified_shouldReplaceAndRestoreElevation() {
    // given
    Element element = mock(Element.class);
    element.classList = mock(DOMTokenList.class);
    given(element.classList.getLength()).willReturn(1);
    given(element.classList.getAt(0)).willReturn("mdc-elevation--z2");
    MdcElevator.whenOver(element).liftTo(8);
    ArgumentCaptor<EventListener> enterCaptor = ArgumentCaptor.forClass(EventListener.class);
    ArgumentCaptor<EventListener> leaveCaptor = ArgumentCaptor.forClass(EventListener.class);
    verify(element).addEventListener(eq("mouseenter"), enterCaptor.capture());
    verify(element).addEventListener(eq("mouseleave"), leaveCaptor.capture());

    // when
    enterCaptor.getValue().handleEvent(null); // event payload doesn't matter
    leaveCaptor.getValue().handleEvent(null);

    // then
    InOrder inOrder = inOrder(element.classList);
    inOrder.verify(element.classList).getLength();
    inOrder.verify(element.classList).getAt(0);
    inOrder.verify(element.classList).remove("mdc-elevation--z2");
    inOrder.verify(element.classList).add("mdc-elevation--z8");
    inOrder.verify(element.classList).remove("mdc-elevation--z8");
    inOrder.verify(element.classList).add("mdc-elevation--z2");
    verifyNoMoreInteractions(element, element.classList);
  }

}
