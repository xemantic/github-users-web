*Code conventions used across the project*

# Adapting JSON to Java objects

Note: it should be possible to generate all this adapting
code out of the JSON schema, or from the model interfaces defined
in the `github-users` project if their attributes were annotated
with the name of actual JSON attribute.

## Representing JSON

Any native JSON can be represented as an instance of specific
Java class with strongly-typed attributes. Example:

```java
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class JsonUser {
  public String login;
  public String avatar_url;
  public String html_url;
  public String[] tags;
}
```

The *namespace* should be global and the *name* should be `Object`. Attributes
must be public and their names should follow naming convention used in
the actual JSON schema unless `@JsProperty` annotation is used.
 
Collection attributes are represented as arrays.

All the strongly-typed Java representations of JSON payloads are named with the
`Json` prefix.

These classes are needed for providing implementation of the service layer
therefore they reside in the
[com.xemantic.githubusers.web.service.json](github-users-web-client/src/main/java/com/xemantic/githubusers/web/service/json)
package.

## Adapting JSON payloads to Java model

The data model returned by the service is represented as Java interfaces
defined in the `github-users` project with typical bean getters.
E.g. `com.xemantic.githubusers.web.model.SearchResult`. Therefore implementation
of these interfaces is needed in the `github-users-web` and usually it is
about delegating getters to proper attribute in the JSON payload.
The JSON payload is always passed as the constructor parameter. Example:

```java
public class WebSearchResult implements SearchResult {
 
  private final JsonSearchResult _payload;
 
  public WebSearchResult(JsonSearchResult payload) {
    _payload = payload;
  }
 
  @Override
  public int getTotalCount() {
    return _payload.total_count;
  }
 
  @Override
  public boolean isIncompleteResult() {
    return _payload.incomplete_result;
  }
 
  @Override
  public List<User> getItems() {
    List<User> list = new ArrayList<>();
    for (JsonUser user : _payload.items) {
      list.add(new WebUser(user));
    }
    return list;
  }
 
}
```

The full implementation can be found in the
[com.xemantic.githubusers.web.model](github-users-web-client/src/main/java/com/xemantic/githubusers/web/model)
package.

Note: the `_payload` field should be always named with the underscore prefix
as this special naming convention will be used in the
[PrepareGwtJs](github-users-web-tools/src/main/java/com/xemantic/githubusers/web/tool/PrepareGwtJs.java)
tool to change Object attribute references from `_payload.foo` to `_paload['foo']`.
Thanks to this modification the Closure Compiler will not try to rename these
attributes while performing `ADVANCED_OPTIMIZATIONS`.

# Closure Modules

This app depends on some JS
[modules](github-users-web-server/src/main/webapp/js/modules)
written according to Closure Library conventions.

These modules can either provide some custom JavaScript
code or adapt existing modules and libraries to the requirements of
GWT code (e.g. Closure Templates, Incremental-DOM). 

Each module requires double export mechanism in order to be accessible
either from application running in the GWT Super Dev mode
(`goog.exportSymbol`) or from the code processed by the Closure Compiler
(`exports.`*foo*). Also `goog.module.declareLegacyNamespace();` should
be always set to allow using fully qualified references in other modules.

# Closure externs

Currently the code of this project depends on external
[Material Components for the Web](https://material.io/components/web/) library
which cannot be easily compiled with Closure Compiler until
[these goals](https://github.com/material-components/material-components-web/blob/master/docs/closure-compiler.md)
are reached. For this reasons symbols of this library are listed in the
[externs.js](github-users-web-server/src/main/js/externs.js) file.

If there are any other JS libraries in the future, which are not supposed
to be processed with the Closure Compiler, and they don't come with own *extern*
file, then their symbols should be added to `externs.js` as well. The
whitelist of all the symbols being in use is sufficient.
