*TODO list*

# Search box

## Search box look

Style it the same way as it is done here:

https://google.github.io/closure-library/api/

## Search box feel

* The search box should gain focus on startup
* The search prompt should be localized

# Progress bar

There should be some horizontal progress bar, just below the toolbar,
indicating any operation which might take some time to finish -
in this case provision of search results.

https://material-components-web.appspot.com/linear-progress.html

The core logic of `github-users` should be extended for this purpose.

# I18N

Localized message templates should be stored as `.soy` closure templates
and provided by the `github-users` project. The question remains how to unpack them
from the archive and reference in the `github-users-web` code?

# Selenium tests

Automated selenium tests should become part of this project. It would be nice
to also define them in platform independent way, possibly as use cases being
a part of `github-users` common logic, and then provide adapter here using
`WebDriver` and CSS selectors. Tools like `Cucumber` might come handy for such a
purpose.

# npm dependencies

Add proper `package.json`
