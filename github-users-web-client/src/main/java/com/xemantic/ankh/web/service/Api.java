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

package com.xemantic.ankh.web.service;

import com.google.common.collect.ImmutableSet;
import elemental2.core.Global;
import elemental2.dom.XMLHttpRequest;
import io.reactivex.Single;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Builder of API requests.
 *
 * @author morisil
 */
public class Api {

  private static final Set<Integer> SUCCESS_STATUSES = ImmutableSet.of(200, 201, 204, 1223/*MSIE*/);

  private final String baseUrl;

  private final ParameterEncoder parameterEncoder;

  private final JsonParser jsonParser;

  public Api(String baseUrl) {
    this(baseUrl, Global::encodeURIComponent, Global.JSON::parse);
  }

  // used for testing
  Api(
      String baseUrl,
      ParameterEncoder parameterEncoder,
      JsonParser jsonParser) {

    this.baseUrl = Objects.requireNonNull(baseUrl);
    this.parameterEncoder = parameterEncoder;
    this.jsonParser = jsonParser;
  }

  public <T> Builder<T> resource(String resource) {
    return new Builder<>(resource);
  }

  public class Builder<T> {

    private final String resource;

    private Map<String, String> parameterMap = new HashMap<>();

    private Builder(String resource) {
      this.resource = Objects.requireNonNull(resource);
    }

    public Builder<T> and(String name, Object value) {
      parameterMap.put(name, value.toString());
      return this;
    }

    public Single<T> get() {
      return Single.create(em -> {
        XMLHttpRequest request = new XMLHttpRequest();
        request.addEventListener("load", e -> {
          if (SUCCESS_STATUSES.contains(request.status)) {
            em.onSuccess((T) jsonParser.parse(request.responseText));
          } else {
            em.onError(new ApiException(request.status, request.statusText));
          }
        });
        request.addEventListener("error",
            e -> em.onError(new ApiException(request.status, request.statusText))
        );
        em.setCancellable(request::abort);
        request.open("GET", getUrl(), true);
        request.send();
      });
    }

    private String getUrl() {
      return baseUrl + resource + "?" + getParameters();
    }

    private String getParameters() {
      return parameterMap.entrySet()
          .stream()
          .map(param ->
              param.getKey() + "="
                  + parameterEncoder.encode(param.getValue())
          )
          .collect(Collectors.joining("&"));
    }

  }

}
