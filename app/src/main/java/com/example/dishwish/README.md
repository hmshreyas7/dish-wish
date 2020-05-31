# User Interface

This section includes specific details about various components of the user interface and how they are handled.

## AddDishActivity

The setting up of a separate screen that allows the user to add a new dish to one of the lists is done here.

The screen consists of three components:

1. Name of the Dish - Input text field for entering a title for the dish
2. Dish Type - Dropdown menu to choose between "Savory" (default) and "Sweet"
3. Category - Dropdown menu to choose which list to add the dish to: "To Cook" (default) or "To Eat"

The system keyboard pops up by default to make it convenient for the user to immediately start typing the dish name. Clicking anywhere outside the text field hides it.

The toolbar at the top has an "up" button that takes the user back to the main screen. This works similar to (but is slightly different from) using the back button on the phone.

Additionally, the toolbar contains a "done" button that appears only when user input is valid. Input is only considered to be valid when the name of the dish (ignoring leading and trailing whitespaces) is not empty and the overall length of the text in this field does not exceed the limit mentioned below it (50 characters at the moment).

Clicking the "done" button adds the dish to the selected list and the user is taken back to the main screen with an appropriate message popping up.

It is also possible to open this screen in "edit mode" which happens when a list item on the main screen is clicked. In this mode, the title of the screen is modified appropriately and all the relevant information for the chosen item is loaded.

Clicking the "done" button in this case updates the existing dish rather than creating a new one. Updates may involve modifying details about the dish or even changing which list it should belong to. Furthermore, there exists a "delete" button that allows the user to remove the dish from the database. Since this is a destructive action, a confirmation dialog is displayed first. Based on the action performed, a message is displayed after this step.

In either mode, the user may decide to leave the screen after modifying the dish title field. If this happens, a confirmation dialog appears in this case as well to check if they really meant to do this.

## DishCursorAdapter

All of the items in large lists cannot fit on the user's screen. This means that when the user is scrolling through their lists, they are only able to view a subset of them at any given time. The list items that are hidden serve no purpose and this leads to inefficient memory usage. Instead of creating list items for all the items from the database at one time, this class helps in creating a fixed set of items for the ones that are visible and as the user scrolls, the data in each item is modified accordingly.

## DishFragment

This class corresponds to each tab on the main screen. The appropriate data is fetched from the database and loaded as a list here. This is done in such a way that the data automatically refreshes when the user adds a new dish or updates an existing one. Clicking on any list item opens `AddDishActivity` in "edit mode".

## DishFragmentPagerAdapter

Individual fragments are "combined" together here. This is required to view them as tabs on the main screen. The ordering of these tabs and their corresponding title text can be modified in this class.

## MainActivity

The two lists are displayed as tabs here with the help of `DishFragmentPagerAdapter`. There is a "+" button on the bottom right of the screen that takes the user to `AddDishActivity` and thus, allows them to add a new dish.