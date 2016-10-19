# NewsExercise
Android Proficiency Exercise for Pactera

Android app which ingests a feed from https://dl.dropboxusercontent.com/u/746330/facts.json containing a title and a list of rows.
Displays the content in a ListView where each row of the list has a blue title on the top, the description underneath it in black, and the image (when one exists) somewhere on the right.
The images should be loaded lazily.
It should have a refresh function that should not block the UI when loading the data.
