goog.provide("app.templates");  // assures creation of namespace
goog.require("com.xemantic.githubusers.web.template.incrementaldom");

goog.scope(function() {
  var module = goog.module.get("com.xemantic.githubusers.web.template.incrementaldom");
  var ns = app.templates;
  ns.userTile = module.userTile;
});

goog.exportSymbol("app.templates", app.templates);
