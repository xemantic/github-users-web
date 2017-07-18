*lists GitHub users*

Minimal app demonstrating cross-platform app development (Web, Android, iOS)
where core logic is shared and transpiled from Java to JavaScript and
Objective-C. This project delivers Web version.

Note: shared logic is provided by the [github-users](https://github.com/xemantic/github-users)
project.

# Demo

https://github-users-web.appspot.com/

# Status

As you can see from the demo, this project already delivers the full build toolchain for Web
application. Still there are several things [TODO](TODO.md).

# Development

Running the project during development requires starting the codeserver (GWT Super Dev Mode)
in parallel with regular Jetty servlet container serving static resources and in the future
also server part of the application.

/!\ Note: In order to start jetty from within the `github-users-web-server`
module you will need to install dependant modules first:

    $ mvn install

It's one time action which will also verify if the project builds correctly in your
environment.

Open 2 terminals and invoke these commands:

    $ mvn gwt:codeserver -pl *-client -am -Denv=dev

And then:

    $ cd *-server
    $ mvn jetty:run -Denv=dev

Head your browser to:

http://localhost:8080/index-dev.html

The regular `index.html` will work as well after invoking the whole toolchain
(e.g. `mvn package`), but it will server the JS code which is already compiled
and minified by the Closure Compiler, making it a bit more cumbersome to debug,
but closer to expected production environment.

If you are planing to extend this project or to borrow the toolchain and
use it somewhere else, than check [conventions](CONVENTIONS.md) used in this project.

# Project objectives

## Establish toolchain

Interoperability between pure JS code, GWT compiled code, Closure Library and Closure Compiler
requires quite complex setup. Especially when development process is based on uncompiled
JavaScript and GWT Super Dev Mode but highly processed version of these sources
will be used in production. This projects attempts to cover all the tools needed when
developing and building such a project and also automate as much as possible in the process.

## Maven archetype with predefined toolchain

Once this project proofs to fulfill all the requirements, it might be used as a base for
generalized maven archetype automating creation of such an application.

# Project modules

## github-users-web-client

GWT client code which will be transpiled to JS. When building the project, this module
will provide an artifact in the form of WAR archive containing only compiled static web
resources.

During development the `codeserver` can be started out of this module as well.

## github-users-web-server

This module contains rest of the JavaScript code and resources and is responsible for
the final packaging of application WAR archive which might be deployed on the
servlet container.

As this application does not provide any server-side logic at the moment, it is also
possible to extract web resources from the WAR archive and serve them statically.

## toolchain modules

This project contains special technical modules:

* `github-users-web-install-tools`
* `github-users-web-install-js`
* `github-users-web-compiler-templates`
* `github-users-web-compiler-js`

They should be a part of `github-users-web-server` build definition, but are separated as a
workaround to inflexibility of the `maven-exec-plugin` which does not allow separate classpaths
for each forked java process, unless it is separate maven module. These tools should be
also executed in specified order which is achieved thanks to explicit dependency chain.

## github-users-web-appengine

The `appengine` module will take care of deploying this project in the Google App Engine.

It is separated from the `github-users-web-server` module to allow stripping down the
WAR file from all excessive JS sources. In the future only files listed in the
`compiled.js.map` should be retained.

# Deployment

    $ mvn clean install
    $ mvn -pl github-users-web-appengine appengine:deploy
