# Notes Keeping Mobile App

This is a simple note keeping mobile application. It allows users to create new notes, modify or edit already existing notes.

# Built With
* Android Studio
* Kotlin
* Gradle
* Mvvm Achitecture

The application stores the notes in a local database created using room. 

The application also makes use of the databinding library and livedata to automatically notify the UI about data changes

The Mvvm Achitecture was also employed to manage data for the User Interface and enable the app data survive device configuration changes

The room database was built with coroutines to enable making of asynchronous calls to the database



