# Resources

This section includes information about various resource files that are used for different purposes in the app.

## \layout

The main screen uses a tab layout which can be found in `activity_main`. The individual tabs are designed in `fragment_main` and the list items that fill up these tabs are designed in `item_dish`.

Adding dishes takes place in another screen and its layout is defined in `activity_add_dish` and the dropdown menus that it includes are defined in `dropdown_menu_items`.

Both these screens use `toolbar_main` at the top.

## \menu

The "done" button used to add a new dish is added to the toolbar using `app_bar_options`.

## \values

All the different colors used in the app are listed in `colors` and the app theme is defined in `styles`. Specific values that are used for things like spacing between components are defined in `dimens` and other things like page titles, labels, dropdown choices, and messages displayed to the user are listed in `strings`.