# D18-12-android-app-dev-Task
This app lets managers add and assign projects to their employees and maintain an internal assignment log
------------------------------------------------------------------------------

PURPOSE :- 

An office situation where the manager is often unaware of the amount of work their sub-ordinates are allocated.
They are usually not clear about who is fully occupied (40 hr/ week) or partially occupied.
This will help them assign projects to employees at ease.
------------------------------------------------------------------------------

FUNCTIONALITIES/ FEATURES :-

EMPLOYEES module - Add employess by entering Fname, Lname, Type, Gender
PROJECT module - Add projects by entering Project Name, Domain, Start date, End date.
ASSIGNMENT module - Add assignments by either assigning an employee to a project and vice versa.
------------------------------------------------------------------------------
APPROACH/ TECHNIQUES:- 

In order to 

Step 1 - Create Employees and Project Entities
Step 2 - Create respective Data Access Layers ANDROID ROOM db commands.
         which has the relevant queries to Insert, Update and Delete 
         Most importantly LiveData Collections object which watches for changes to Data objects 
         and updates Asynchronosly.
Step 3 - Create a Singleton db using the ANDROID ROOM Persistence Libraries to avoid 
         multiple instances during app startup.
Step 4 - Create the View Models that acts as a layer between the Activity and Database. This helps in rapidly 
         send or return data without having to hit the database layer everytime.
Step 5 - Create Activities that consists of onCreate, onClick, swipe events that a user can perform actions on the app screen.
         Also collect any text entry and reference as a data input into the db.
Step 6 - Create relevant layouts for each activity page and create page objects such as buttons, text fields, etc.
Step 7 - Create the Recycler views for the Activity view that would display a list of View holders. For cases where we want to
         scroll through a list of such adapter views  we need to extend the Android's recycler view class.
Step 8 - Add the relevant dependencies pertaining to Room db in the Gradle file.

------------------------------------------------------------------------------
PROJECT DEMO - video link goes here


