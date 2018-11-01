package cloudgame;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Creates Cloud object used in CloudGamePanel that is controlled by user
 *
 * @author Alex Yarkosky
 */
public class Cloud {
  /**
   * Lightning l created to allow interactions between Cloud & Lightning classes int x used to keep
   * track of Cloud's x location final final int Y used to define the Cloud's permanent y location
   * final int SPD used to change Cloud's x location final int DIAM used to define the diameter of
   * each circle used to create the entire Cloud object boolean isMoving used to determine if the
   * user has begun the game & therefore if the Cloud should be moving or not int cloudSegments used
   * to determine how many segments to draw boolean immune used to store whether the cloud is able
   * to be struck int wait used to determine when the immunity ends
   */
  private Lightning l1;

  private Lightning l2;
  private int x;
  private final int Y = 250;
  private final int SPD = 10;
  private final int DIAM = 30;
  private boolean isMoving;
  private int cloudSegments;
  private boolean immune;
  private int wait;
  /**
   * Instantiates the Cloud object
   *
   * @param lightningBoltOne used for interactions between a first Lightning & Cloud classes
   * @param lightningBoltTwo used for interactions between a second Lightning & Cloud classes
   */
  public Cloud(Lightning lightningBoltOne, Lightning lightningBoltTwo) {
    x = 355;
    isMoving = false;
    l1 = lightningBoltOne;
    l2 = lightningBoltTwo;
    cloudSegments = 6;
    immune = false;
    wait = 0;
  }
  /**
   * Defines the Cloud object's visual location & colors depending on the number of cloud segments
   * as well as information text when the user has not started the current game
   *
   * @param g used to create the shapes used in creating the Cloud object visual
   */
  public void paint(Graphics g) {
    g.setColor(Color.WHITE);
    if (cloudSegments >= 1) g.fillOval(x, Y, DIAM, DIAM);
    if (cloudSegments >= 3) g.fillOval(x + 15, Y - 15, DIAM, DIAM);
    g.setColor(Color.GRAY);
    if (cloudSegments >= 2) g.fillOval(x + 15, Y + 15, DIAM, DIAM);
    g.setColor(Color.WHITE);
    if (cloudSegments >= 4) g.fillOval(x + 30, Y, DIAM, DIAM);
    if (cloudSegments == 6) g.fillOval(x + 45, Y - 15, DIAM, DIAM);
    g.setColor(Color.GRAY);
    if (cloudSegments >= 5) g.fillOval(x + 45, Y + 15, DIAM, DIAM);
    if (!isMoving) {
      g.setColor(Color.YELLOW);
      g.drawString("CLICK TO START", 345, 100);
      g.setColor(new Color(55, 255, 255));
      g.drawString("USE MOUSE TO MOVE", 330, 150);
    }
  }
  /**
   * Checks if the Cloud is moving & then changes the Cloud's x variable according to the parameter
   *
   * @param mouseX used to move Cloud object towards the x location of the mouse
   */
  public void move(int mouseX) {
    if (isMoving) {
      if (mouseX > x) x += SPD;
      if (mouseX < x) x -= SPD;
    }
  }
  /**
   * Checks to see if the Cloud is out of the bounds of being visually displayed & if so moves it
   * fully to the other side of the screen
   */
  public void checkOutOfBounds() {
    if (x <= 0) {
      x = 730;
    }
    if (x >= 740) {
      x = 10;
    }
  }
  /**
   * Checks to see if the first Lightning class bolt has hit the Cloud object based off of their x
   * locations
   */
  public void checkFirstStrike() {
    int additional = 0;
    if (cloudSegments == 6 || cloudSegments == 5) additional = 60;
    else if (cloudSegments == 4) additional = 45;
    else if (cloudSegments == 3 || cloudSegments == 2) additional = 30;
    else if (cloudSegments == 1) additional = 15;
    if (isMoving) {
      if ((l1.getX() + 10 >= x && l1.getX() <= x)
          || (l1.getX() + 10 >= x + additional && l1.getX() <= x + additional)
          || ((x <= l1.getX() && x + additional >= l1.getX())
              && (x <= l1.getX() && x + additional >= l1.getX()))) {
        if (l1.getWait() > 0 && l1.getWait() < 45) {
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
   * locations
   */
  public void checkSecondStrike() {
    int additional = 0;
    if (cloudSegments == 6 || cloudSegments == 5) additional = 60;
    else if (cloudSegments == 4) additional = 45;
    else if (cloudSegments == 3 || cloudSegments == 2) additional = 30;
    else if (cloudSegments == 1) additional = 15;
    if (isMoving) {
      if ((l2.getX() + 10 >= x && l2.getX() <= x)
          || (l2.getX() + 10 >= x + 60 && l2.getX() <= x + 60)
          || ((x <= l2.getX() && x + 60 >= l2.getX()) && (x <= l2.getX() && x + 60 >= l2.getX()))) {
        if (l2.getWait() > 0 && l1.getWait() < 35) {
          if (!immune) {
            cloudSegments--;
            immune = true;
          }
        }
      }
    }
  }
  /**
   * Used to check if the game is over when there are no more segments
   *
   * @return the number of cloudSegments let in the Cloud
   */
  public int getCloudSegments() {
    return cloudSegments;
  }
  /**
   * Checks if the Cloud is currently immune to being struck by a bolt & if so counts to when it is
   * no longer immune
   */
  public void checkImmunity() {
    if (immune) wait++;
    if (wait == 30) {
      immune = false;
      wait = 0;
    }
  }
  /** Allows the cloud to start moving at the user's chosen beginning of the game */
  public void startMoving() {
    isMoving = true;
  }
  /**
   * Used to make sure the score is not reset if the user clicks while playing
   *
   * @return used to get the isMoving variable
   */
  public boolean getIsMoving() {
    return isMoving;
  }
}
