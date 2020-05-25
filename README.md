# Dish Wish

## Overview

Dish Wish is an Android app that allows users to maintain two types of wish lists: "To Cook" and "To Eat". These are presented as two separate tabs when the user opens the app and includes the option to add more items to either list.

## Project Structure

This project is organized just like any other Android project built using Android Studio. The key files of interest can be found in `\app`. Within that, the two most important ones are `..\java` and `..\res`.

More specifically, `..\java` contains classes for:

1. Adding a dish
2. Displaying the "To Cook" list items
3. Combining the two lists into tabs
4. Loading the data from the database
5. Displaying the "To Eat" list items
6. Setting up the main screen

In addition to these, there is another directory here called `..\data`. Within this, all the database-related operations are implemented such as:

1. Defining the schema and other database constants
2. Creating a new database / connecting to an existing one
3. Handling CRUD operations

Within `..\res`, there are three main directories:

1. `\layout` - The screens, tabs, dropdown menus, list items, and toolbars are designed here.
2. `\menu` - Menu items for the toolbars are designed here.
3. `\values` - Multiple app constants for colors, dimensions, strings, and styles are defined here.