# Java Classes - UI

## Overview

In this section, very specific details about the Java classes that handle the user interfaces can be found.

## AddDishActivity

The setting up of a separate screen that allows the user to add a new dish to one of the lists is done here.

The screen consists of three components:

1. Name of the Dish - Input text field for entering a title for the dish
2. Dish Type - Dropdown menu to choose between "Savory" (default) and "Sweet"
3. Category - Dropdown menu to choose which list to add the dish to: "To Cook" (default) or "To Eat"

The system keyboard pops up by default to make it convenient for the user to immediately start typing the dish name. Clicking anywhere outside the text field hides it.

The toolbar at the top has an "up" button that takes the user back to the main screen. This works similar to (but is slightly different from) using the back button on the phone.

Additionally, the toolbar contains a "done" button that appears only when user input is valid. Input is only considered to be valid when the name of the dish (ignoring leading and trailing whitespaces) is not empty and the overall length of the text in this field does not exceed the limit mentioned below it (50 characters at the moment).

Clicking the "done" button adds the dish to the selected list and the user is taken back to the main screen with a message popping up that either says "New dish added" (if successful) or "Failed to add dish" (if unsuccessful).

## CookFragment

This class corresponds to the "To Cook" tab on the main screen. The appropriate data is fetched from the database and loaded as a list here. This is done in such a way that the data automatically refreshes when the user adds a new dish.

## CustomPagerAdapter

`CookFragment` and `EatFragment` are "combined" together here. This is required to view them as two tabs on the main screen. The ordering of these tabs and their corresponding title text can be modified in this class.

## DishCursorAdapter

All of the items in large lists cannot fit on the user's screen. This means that when the user is scrolling through their lists, they are only able to view a subset of them at any given time. The list items that are hidden serve no purpose and this leads to inefficient memory usage. Instead of creating list items for all the items from the database at one time, this class helps in creating a fixed set of items for the ones that are visible and as the user scrolls, the data in each item is modified accordingly.

## EatFragment

This class corresponds to the "To Eat" tab in the main screen and works exactly like `CookFragment`. This is unnecessary and has been documented as an [issue](https://github.com/hmshreyas7/dish-wish/issues/6) for now.

## MainActivity

The two lists are displayed as tabs here with the help of `CustomPagerAdapter`. There is a "+" button on the bottom right of the screen that takes the user to `AddDishActivity` and thus, allows them to add a new dish.