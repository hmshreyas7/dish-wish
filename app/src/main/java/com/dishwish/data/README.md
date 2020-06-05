# Database

This section includes specific details about database components and how queries are executed.

## DishContract

All database constants such as table names, column names, allowed values for columns, and content URIs are defined here within inner classes which individually correspond to a table in the database. Essentially, this class is used to define the layout of the database and its tables. This information is then used by `DishDbHelper` to create it.

## DishDbHelper

The name of the database and its version are defined here. Using the constants from `DishContract`, this class creates the database if it does not already exist. If it exists, a connection to it is established instead.

## DishProvider

The CRUD operations are handled here. A readable connection is established using `DishDbHelper` to read from the database and the results of the read query are returned as a cursor. A notification URI is then set on this cursor to let it know when it needs to be updated.

All the other operations involve using a writable connection to execute their queries. Once successfully executed, the appropriate cursors are notified that their source of data has changed. This makes the corresponding `CursorLoader` to refresh itself and this is how the data visible to the user is always up-to-date.

Another point to note is that all queries are executed based on the format of the content URI received. There are two possible cases: (1) the query runs on the whole table, or (2) the query is for a specific row in the table. This is handled using `UriMatcher`.