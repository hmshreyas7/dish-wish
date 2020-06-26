# Resources

This section includes information about various resource files that are used for different purposes in the app.

## \anim

Screens appear to slide from right to left when opened and left to right when closed. Such transitions are implemented using the files here: `slide_in_left`, `slide_in_right`, `slide_out_left`, and `slide_out_right`.

## \drawable

The key icons used in the app for various actions are defined in `ic_add_black_24dp`, `ic_delete_black_24dp`, and `ic_done_black_24dp`.

## \layout

The main screen uses a tab layout which can be found in `activity_main`. The individual tabs are designed in `fragment_main` and the list items that fill up these tabs are designed in `item_dish`.

Adding dishes takes place in another screen and its layout is defined in `activity_add_dish` and the dropdown menus that it includes are defined in `dropdown_menu_items`.

Both these screens use `toolbar_main` at the top.

## \menu

The "done" button used to add a new dish and the "delete" button used to remove an existing one are added to the toolbar using `app_bar_options`.

## \mipmap

There are multiple versions of this directory. Each contains the app logo in different formats and resolutions in order to adapt to various devices.

## \values

All the different colors used in the app are listed in `colors` and the app theme is defined in `styles`. Specific values that are used for things like spacing between components are defined in `dimens` and other things like page titles, labels, dropdown choices, and messages displayed to the user are listed in `strings`.

## \xml

Each individual preference is defined in `settings` using the Preference API provided by Android.