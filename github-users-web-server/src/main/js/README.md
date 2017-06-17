The *externs* are used by the Closure Compiler to mark symbols forming API
of external libraries. These API calls should not be renamed during advanced
code optimizations.

Currently it covers API of selected components from the following libraries:

* [Material Components for the Web](https://material.io/components/web/)

The [externs_templates.js](externs_templates.js) file is used to avoid renaming
properties of data object being passed to Closure Templates (Incremental DOM
flavor). It is necessary as these properties are set by name from the
GWT code which is not processed by Closure Compiler currently.

Note: in ideal case these *extern* files should remain empty as libraries like
`Material Components for the Web` should be also processed directly by the
Closure Compiler. The same applies to the GWT compiled JS code which
should be post-processed by the Closure Compiler as well.
It should be delivered in next iterations of this project.
