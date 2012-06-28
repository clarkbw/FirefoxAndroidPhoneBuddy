# Firefox Phone Buddy Android Application

Open this Application as an Eclipse project.

Choose Run from the Eclipse menu.

The title bar of your emulator should say something like <NUMBER>:<NAME> where NUMBER is the id number of the emulator and NAME is the AVD name (see Android Device Manager). 

If you haven't setup your Android Device Manager devices then you'll need to do that now.

## Simulate an Incoming call

From a terminal window

`telnet localhost <NUMBER>`

`gsm call +17789874133`

You should see a Toast for the incoming call from the phone number "+1-778-987-4133"

## Simulate and Outgoing call

From the running Android Emulator choose the Dialer application.

Dial a phone number and you should see a Toast for the outgoing call number that you dialed


## Requirements

 * Eclipse		- http://eclipse.org/
 * Eclipse ADT	- https://dl-ssl.google.com/android/eclipse/ 
 * Eclipse EGit - http://wiki.eclipse.org/EGit/User_Guide
 * Android SDK