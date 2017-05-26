/* menu */
var drawer = new mdc.drawer.MDCTemporaryDrawer(document.querySelector('.mdc-temporary-drawer'));
document.querySelector('.menu').addEventListener('click', () => drawer.open = true);

/* it will center the grid and make it react to window resizing */

mdc.gridList.MDCGridList.attachTo(document.querySelector(".mdc-grid-list"));
