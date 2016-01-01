# AllyFix5x
Xposed module for Ally Mobile app to fix Nexus 5x camera orientation

The new Google/LG Nexus 5x has a reverse-landscape mounted rear camera, and using it to do mobile deposits in the Ally Mobile app was frustrating with the viewfinder and preview images being mirrored and flipped 180 degrees. 

This module will override the camera's display orientation, which fixes the viewfinder, and it will force the JPEG orientation to 180, which should display the preview images correctly.

With any luck, Ally will update the app themselves, so this module shouldn't be necessary forever. But given that they've been contacted numerous times (by myself, and I'm sure many others) and their app has horrible reviews on the Play store, it doesn't seem like they're interested in fixing it any time soon.
