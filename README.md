# Dish Wish

Dish Wish is an Android app that allows users to maintain two types of wish lists: "To Cook" and "To Eat". These are presented as two separate tabs when the user opens the app. The user can then add more items or update existing ones.

## Project Structure

This project is organized just like any other Android project built using Android Studio. The key files of interest can be found in `\app`. Within that, the two most important ones are `..\java` and `..\res`.

### ..\java

This directory contains classes for:

1. Adding / updating a dish
2. Loading the data from the database
3. Displaying the list items
4. Combining the two lists into tabs
5. Setting up the main screen

In addition to these, there is another directory here called `..\data`. Within this, all the database-related operations are implemented such as:

1. Defining the schema and other database constants
2. Creating a new database / connecting to an existing one
3. Handling CRUD operations

### ..\res

There are three main directories:

1. `\layout` - The screens, tabs, dropdown menus, list items, and toolbars are designed here.
2. `\menu` - Menu items for the toolbars are designed here.
3. `\values` - Multiple app constants for colors, dimensions, strings, and styles are defined here.

## Specific Details

More information about the above classes and resource files can be found using the following links:

1. [User Interface](https://github.com/hmshreyas7/dish-wish/tree/master/app/src/main/java/com/example/dishwish)
2. [Database](https://github.com/hmshreyas7/dish-wish/tree/master/app/src/main/java/com/example/dishwish/data)
3. [Resources](https://github.com/hmshreyas7/dish-wish/tree/master/app/src/main/res)