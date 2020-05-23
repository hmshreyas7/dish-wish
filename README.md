# Dish Wish

## Overview

Dish Wish is an Android app that allows users to maintain 2 types of wish lists: "To Cook" and "To Eat". These are presented in the main screen as 2 separate tabs when the user opens the app and more items can be added to either list by using the add button.

## Project Structure

This project is organized just like any other Android project built using Android Studio. The key files of interest can be found in `\app`. Within that, the 2 most important ones are `..\java` and `..\res`.

More specifically, `..\java` contains classes for:

1. Adding a dish
2. Displaying the "To Cook" list items
3. Combining the 2 lists into tabs
4. Loading the data from the database
5. Displaying the "To Eat" list items
6. Setting up the main screen

In addition to these, there is another directory here called `\data`. Within this, all the database-related operations are implemented such as:

1. Defining the schema and other database constants
2. Creating a new database / connecting to an existing one
3. Handling CRUD operations

Within `..\res`, there are 3 main directories:

1. `\layout` - The screens, tabs, dropdown menus, list items, and toolbars are designed here.
2. `\menu` - Menu items for the toolbars are defined here.
3. `\values` - Multiple app constants for colors, dimensions, strings, and styles are defined here.