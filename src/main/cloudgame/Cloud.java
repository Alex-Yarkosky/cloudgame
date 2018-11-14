package cloudgame;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Creates Cloud object used in CloudGamePanel that is controlled by user.
 *
 * @author Alex Yarkosky
 */
public class Cloud {
  /**
   * Lightning lightning1 created to allow interactions between Cloud & Lightning classes.
   * Lightning lightning2 created to allow interactions between Cloud & Lightning classes.
   * int posX used to keep track of Cloud's x location.
   * final int posY used to define the Cloud's permanent y location.
   * final int speed used to change Cloud's x location.
   * final int diameter used to define the diameter of each circle
   * used to create the entire Cloud object.
   * boolean isMoving used to determine if the user has begun the game
   * & therefore if the Cloud should be moving or not.
   * int cloudSegments used to determine how many segments to draw.
   * boolean immune used to store whether the cloud is able to be struck.
   * int wait used to determine when the immunity ends.
   */
  private Lightning lightning1;
  private Lightning lightning2;
  private int posX;
  private final int posY = 250;
  private final int speed = 10;
  private final int diameter = 30;
  private boolean isMoving;
  private int cloudSegments;
  private boolean immune;
  private int wait;

  /**
   * Instantiates the Cloud object.
   *
   * @param lightningBoltOne used for interactions between a first Lightning & Cloud classes
   * @param lightningBoltTwo used for interactions between a second Lightning & Cloud classes
   */

  public Cloud(Lightning lightningBoltOne, Lightning lightningBoltTwo) {
    posX = 355;
    isMoving = false;
    lightning1 = lightningBoltOne;
    lightning2 = lightningBoltTwo;
    cloudSegments = 6;
    immune = false;
    wait = 0;
  }

  /**
   * Defines the Cloud object's visual location & colors depending on the number of cloud segments
   * as well as information text when the user has not started the current game.
   *
   * @param graphics used to create the shapes used in creating the Cloud object visual
   */

  public void paint(Graphics graphics) {
    graphics.setColor(Color.WHITE);
    if (cloudSegments >= 1) {
      graphics.fillOval(posX, posY, diameter, diameter);
    }
    if (cloudSegments >= 3) {
      graphics.fillOval(posX + 15, posY - 15, diameter, diameter);
    }
    graphics.setColor(Color.GRAY);
    if (cloudSegments >= 2) {
      graphics.fillOval(posX + 15, posY + 15, diameter, diameter);
    }
    graphics.setColor(Color.WHITE);
    if (cloudSegments >= 4) {
      graphics.fillOval(posX + 30, posY, diameter, diameter);
    }
    if (cloudSegments == 6) {
      graphics.fillOval(posX + 45, posY - 15, diameter, diameter);
    }
    graphics.setColor(Color.GRAY);
    if (cloudSegments >= 5) {
      graphics.fillOval(posX + 45, posY + 15, diameter, diameter);
    }
    if (!isMoving) {
      graphics.setColor(Color.YELLOW);
      graphics.drawString("CLICK TO START", 345, 100);
      graphics.setColor(new Color(55, 255, 255));
      graphics.drawString("USE MOUSE TO MOVE", 330, 150);
    }
  }

  /**
   * Checks if the Cloud is moving & then changes the Cloud's x variable according to the parameter.
   *
   * @param mouseX used to move Cloud object towards the x location of the mouse
   */

  public void move(int mouseX) {
    if (isMoving) {
      if (mouseX > posX) {
        posX += speed;
      }
      if (mouseX < posX) {
        posX -= speed;
      }
    }
  }

  /**
   * Checks to see if the Cloud is out of the bounds of being visually displayed & if so moves it
   * fully to the other side of the screen.
   */

  public void checkOutOfBounds() {
    if (posX <= 0) {
      posX = 730;
    }
    if (posX >= 740) {
      posX = 10;
    }
  }

  /**
   * Checks to see if the first Lightning class bolt has hit the Cloud object based off of their x
   * locations.
   */

  public void checkFirstStrike() {
    int additional = 0;
    if (cloudSegments == 6 || cloudSegments == 5) {
      additional = 60;
    } else if (cloudSegments == 4) {
      additional = 45;
    } else if (cloudSegments == 3 || cloudSegments == 2) {
      additional = 30;
    } else if (cloudSegments == 1) {
      additional = 15;
    }
    if (isMoving) {
      if ((lightning1.getX() + 10 >= posX && lightning1.getX() <= posX)
          || (lightning1.getX() + 10 >= posX + additional && lightning1.getX() <= posX + additional)
          || ((posX <= lightning1.getX() && posX + additional >= lightning1.getX())
              && (posX <= lightning1.getX() && posX + additional >= lightning1.getX()))) {
        if (lightning1.getWait() > 0 && lightning1.getWait() < 45) {
          if (!immune) {
            cloudSegments--;
            immune = true;
          }
        }
      }
    }
  }

  /**
   * Checks to see if the second Lightning class bolt has hit the Cloud object based off of their x
   * locations.
   */

  public void checkSecondStrike() {
    int additional = 0;
    if (cloudSegments == 6 || cloudSegments == 5) {
      additional = 60;
    } else if (cloudSegments == 4) {
      additional = 45;
    } else if (cloudSegments == 3 || cloudSegments == 2) {
      additional = 30;
    } else if (cloudSegments == 1) {
      additional = 15;
    }
    if (isMoving) {
      if ((lightning2.getX() + 10 >= posX && lightning2.getX() <= posX)
          || (lightning2.getX() + 10 >= posX + 60 && lightning2.getX() <= posX + 60)
          || ((posX <= lightning2.getX() && posX + 60 >= lightning2.getX())
          && (posX <= lightning2.getX() && posX + 60 >= lightning2.getX()))) {
        if (lightning2.getWait() > 0 && lightning1.getWait() < 35) {
          if (!immune) {
            cloudSegments--;
            immune = true;
          }
        }
      }
    }
  }

  /**
   * Used to check if the game is over when there are no more segments.
   *
   * @return the number of cloudSegments let in the Cloud
   */

  public int getCloudSegments() {
    return cloudSegments;
  }

  /**
   * Checks if the Cloud is currently immune to being struck by a bolt & if so counts to when it is
   * no longer immune.
   */

  public void checkImmunity() {
    if (immune) {
      wait++;
    }
    if (wait == 30) {
      immune = false;
      wait = 0;
    }
  }

  /** Allows the cloud to start moving at the user's chosen beginning of the game. */

  public void startMoving() {
    isMoving = true;
  }

  /**
   * Used to make sure the score is not reset if the user clicks while playing.
   *
   * @return used to get the isMoving variable
   */

  public boolean getIsMoving() {
    return isMoving;
  }
}
