# Welcome to Cloud Game

This repository started as a final project for a computer science class I took in eleventh grade back in 2016. It has been uploaded into this repository for continued development to take place.

## The Premise

The idea of this simple game is to move the cloud in order to avoid the lightning strikes that periodically appear on the screen. If the cloud touches either of the lighning bolts, one of the circles that make it up will disappear signifying the lose of a life. There are six circles in total, but it is not all bad news if you start loosing lives early on. As enough circles are removed to warrant there being a smaller hit box for the cloud, it will get smaller. Small semi-circles will briefly appear at the top of the screen before a lighning bolt strikes so that there they do not come without any indication at all.

## Controls

The cloud will move from side to side following your cursor. If you have your cursor go off the screen to either side, the cloud will still follow and will end up wrapping around to the other side of the screen.

## Point System

The point system is a simplistic one based off of how many second you survive for multiplied by ten.

## Download

This repository can be downloaded from the terminal with the following command:

``git clone https://github.com/Alex-Yarkosky/cloudgame.git``

Otherwise, you can manually download above as a ZIP file.

## Building

If you wish to build Cloud Game from the command line with Gradle, then you will first need to install Gradle on your workstation. If you have already installed Gradle, then please go directly to the next section. Otherwise, follow the installation guidlines at https://gradle.org/install/. Following this:

1. Navigate to the root directory containing your installation of Cloud Game.
2. Type the following command to build the tool: `gradle build` Gradle will build the project from scratch, downloading all the required dependencies for the project automatically.
3. A folder called `build` should have been created within the root directory; if this folder does not exist, then the installation with the command line and Gradle failed and you will not yet be able to play the game. Please ensure that Gradle is using an up to date version with the command `gradle -version`. Please try these steps again after ensuring this.

## Playing

If you have already installed and built Cloud Game as detailed in the previous section, you can run the program and play the game with the following command:

``gradle run``

## The Game

When you get the game to run, you should be greeted with a window similar to this one:

![Image of game start](https://github.com/Alex-Yarkosky/cloudgame/images/cloudgamestart.png)
