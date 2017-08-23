# Pre-work - *To Do List*

**To Do List** is an android app that allows building a todo list and basic todo items management functionality including adding new items, editing and deleting an existing item.

Submitted by: **Saurabh Athavale**

Time spent: **8** hours spent in total

## User Stories

The following **required** functionality is completed:

* [] User can **successfully add and remove items** from the todo list
* [] User can **tap a todo item in the list and bring up an edit screen for the todo item** and then have any changes to the text reflected in the todo list.
* [] User can **persist todo items** and retrieve them properly on app restart

The following **optional** features are implemented:

* [] Persist the todo items [into SQLite](http://guides.codepath.com/android/Persisting-Data-to-the-Device#sqlite) instead of a text file
* [] Improve style of the todo items in the list [using a custom adapter](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView)
* [] Add support for completion due dates for todo items (and display within listview item)
* [] Add support for selecting the priority of each todo item (and display in listview item)
* [] Tweak the style improving the UI / UX, play with colors, images or backgrounds

The following **additional** features are implemented:

* [ ] List anything else that you can get done to improve the app functionality!

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='ToDoGif.gif' title='Video Walkthrough' width='400' alt='Video Walkthrough' />


## Project Analysis

As part of your pre-work submission, please reflect on the app and answer the following questions below:

**Question 1:** "What are your reactions to the Android app development platform so far? Compare and contrast Android's approach to layouts and user interfaces in past platforms you've used."

**Answer:** I think  Android App Development platform is pretty good.I have used eclipse IDE before and moved from eclipse to Android studio was very convinient.
Android Studio provde easy way to create activity,Fragments and XML.

**Question 2:** "Take a moment to reflect on the `ArrayAdapter` used in your pre-work. How would you describe an adapter in this context and what is its function in Android? Why do you think the adapter is important? Explain the purpose of the `convertView` in the `getView` method of the `ArrayAdapter`."

**Answer:** ArrayAdapter is uswd for converting the ArrayList into View.
Use of Adapter in the given application is to populate the RecycleView with the Arraylist.
ArrayAdapter configures two aspects:
1.Which array to use as the data source for the list
2.How to convert any given item in the array into a corresponding View object

Adapter is an interface whose implementations provide data and control the display of that data. 

ConverView  is used to reuse old view
E.g
So if a list is of 10 items, but window can show only 5 items, then at first convertView would be null,
and we need to create new views for these five items, but when you scroll down, we re-use old views and load new data into these views.
Adapter and convertView provides this functionality

## Notes

Describe any challenges encountered while building the app.
1.Integrating RecycleView 
2.Provide Delete and OnItemClick functionality on List.


## License

    Copyright [yyyy] [name of copyright owner]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
