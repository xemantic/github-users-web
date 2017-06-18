*lists GitHub users*

Minimal app demonstrating cross-platform app development (Web, Android, iOS)
where core logic is shared and transpiled from Java to JavaScript and
Objective-C. This project delivers Web version.

The core logic is provided by the [github-users](https://github.com/xemantic/github-users)
project.

# Running the project during development

Running the project during development requires starting the codeserver (GWT Super Dev Mode)
in parallel with regular Jetty servlet container serving static resources and in the future
also server part of the application.

/!\ Note: In order to start jetty in the `github-users-web-server`
module you will need to install dependant modules first:

    $ mvn install

It's one time action which will also verify if the project builds correctly in your
environment.

Open 2 terminals and invoke these commends:

    $ mvn gwt:codeserver -pl *-client -am

And then:

    $ cd *-server
    $ mvn jetty:run -Denv=dev

Then heat your browser to:

http://localhost:8080/index-dev.html

The regular `index.html` will work as well after invoking `mvn install` on the whole
project, but it will server the JS code which is already compiled and minified
by the Closure Compiler making it a bit more cumbersome to debug, but closer to
expected production environment.

# Project objectives

## Establish toolchain

Interoperability between pure JS code, GWT compiled code, Closure Library and Closure Compiler
requires quite complex setup. Especially when development process is based on uncompiled
JavaScript and GWT Super Dev Mode and highly processed version of these sources
will be used in production. This projects attempts to cover all the tools needed when
developing and building such a project and also automate as much as possible in the process.

## Maven archetype with predefined toolchain

Once this project proofs to fulfill all the requirements, it might be used as a base for
generalized maven archetype automating creation of such an application.

# Project modules

## github-users-web-client

GWT client code which will be transpiled to JS. When building the project, this module
will provide an artifact it the form of WAR archive containing only compiled static web
resources.

During development the `codeserver` can be started out of this module as well.

## github-users-web-server

This module contains rest of the JavaScript code and resources and is responsible for
the final packaging of application WAR archive which might be deployed on the
servlet container.

As this application does not provide any server-side logic at the moment, it is also
possible to extract web resources from the WAR archive and serve them statically.

## toolchain modules

This project contains 3 special technical modules:

* `github-users-web-install-js`
* `github-users-web-compiler-templates`
* `github-users-web-compiler-js`

They should be a part of `github-users-web-server` build definition, but are separated as a
workaround to inflexibility of the `maven-exec-plugin` which does not allow separate classpaths
for each forked java process, unless it is separate maven module. These tools should be
also executed in specified order which is achieved thanks to explicit dependency chain.

# TODO

## Search box

### Search box look

Style it the same way as it is done here:

https://google.github.io/closure-library/api/

### Search box feel

* The search box should gain focus on startup
* The search prompt should be localized

## Progress bar

There should be some horizontal progress bar, just below the toolbar,
indicating any operation which might take some time to finish -
in this case provision of search results.

https://material-components-web.appspot.com/linear-progress.html

The core logic of `github-users` should be extended for this purpose.

## I18N

Localized message templates should be stored as `.soy` closure templates
and provided by the `github-users` project. The question remains how to unpack them
from the archive and reference in the `github-users-web` code?
