# babylon
Sample app built with retrofit2.0, Butterknife, OrmLite and Picasso.
How does it works:
When the app is launched in onCreate() it will start one service to get de contacts and the avatars from diferent endpoints endpoint. The Http connection is managed with Retrofit. Then, once we have the contacts the it will start another task to save the data in the data base. I have implemented this in two ways: with SQLite and a Content Provider that will insert the contacts with all their information. The other implementation is an OrmLite, in this case will save and read the stored data and it will logged it. 
Through the Retrofit callback  (which will serve the data from the endpoint) a sendbroadcast with the avatars for the contacts will be send it to the Activity, who will handle the intent.
A Cursor Loader will manage the display of the first name and the surname in a list, and the avatars will fill the imageviews (in this case we don´t keep in data bases the images). 
If the user press the row a second activity will be launched displaying the details of the contact and the avatar.
In case the would have to support multi panes I use it fragments . 
Finally I have used Butterknife to handle the views. 
