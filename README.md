# Dish Wish

Dish Wish is an Android app that allows users to maintain two types of wish lists: "To Cook" and "To Eat". These are presented as two separate tabs when the user opens the app where they can then add more items, update existing ones, or delete them.

<a href="https://play.google.com/store/apps/details?id=com.dishwish"><img src="https://i.imgur.com/ggoeysc.png" width="20%"></a>

<img src="https://i.imgur.com/pu8hU93.png" width="20%"/> <img src="https://i.imgur.com/esvqRaG.png" width="20%"/> <img src="https://i.imgur.com/atoku2s.png" width="20%"/> <img src="https://i.imgur.com/8QS2eGN.png" width="20%"/>

Release notes can be found [here](https://github.com/hmshreyas7/dish-wish/blob/master/Release%20Notes.md).

## Project Structure

This project is organized just like any other Android project built using Android Studio. The key files of interest can be found in `\app`. Within that, the two most important ones are `..\java` and `..\res`.

### ..\java

This directory contains classes for:

1. Adding / updating / deleting a dish
2. Loading the data from the database
3. Displaying the list items
4. Combining the two lists into tabs
5. Displaying app settings
6. Handling the actions of each setting
7. Setting up the main screen

In addition to these, there is another directory here called `..\data`. Within this, all the database-related operations are implemented such as:

1. Defining the schema and other database constants
2. Creating a new database / connecting to an existing one
3. Handling CRUD operations

### ..\res

There are seven main directories:

1. `\anim` - Screen to screen transitions are defined here.
2. `\drawable` - Icons used for different purposes can be found here.
3. `\layout` - Screens, tabs, dropdown menus, list items, and toolbars are designed here.
4. `\menu` - Menu items for toolbars are designed here.
5. `\mipmap` - Multiple versions of the app logo can be found here.
6. `\values` - Several app constants for colors, dimensions, strings, and styles are defined here.
7. `\xml` - Layout of app settings is designed here..

## Specific Details

More information about the above classes and resource files can be found using the following links:

1. [User Interface](https://github.com/hmshreyas7/dish-wish/tree/master/app/src/main/java/com/dishwish)
2. [Database](https://github.com/hmshreyas7/dish-wish/tree/master/app/src/main/java/com/dishwish/data)
3. [Resources](https://github.com/hmshreyas7/dish-wish/tree/master/app/src/main/res)