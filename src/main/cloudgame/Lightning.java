package cloudgame;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Creates the obstacle in the CloudGamePanel that the user has to avoid.
 *
 * @author Alex Yarkosky
 */
public class Lightning {
  /**
   * int posX used to store the bolt's x location.
   * int wait used to wait before a new bolt is created & have a period without that bolt.
   * final posY used to store the bolt's y location.
   * int newX used to create a new x location for a new bolt.
   * boolean alert used to know when to alert the user of an upcoming new bolt.
   * final int diameter used to define the diameter of the displayed warning arcs of upcoming bolts.
   * Color col used to define the color of the bolt.
   */
  private int posX;
  private int wait;
  private final int posY = 0;
  private int newX;
  private boolean alert;
  private final int diameter = 30;
  private Color col;

  /** Instantiates the Lightning object with a random x location. */

  public Lightning(Color color) {
    posX = (int) (Math.random() * 32) * 25;
    wait = 0;
    newX = 0;
    alert = false;
    col = color;
  }

  /**
   * Defines the Lightning object's visual location & colors as well as the semi circle used to give
   * the user a warning of the location of the upcoming bolt.
   *
   * @param graphics used to create the shapes used in creating the Lightning object visual
   */

  public void paint(Graphics graphics) {
    graphics.setColor(col);
    if (alert) {
      graphics.fillArc(newX - diameter / 2, posY - diameter / 2, diameter, diameter, 180, 180);
    }
    graphics.fillRect(posX, posY, 10, 450);
  }

  /**
   * Attempts to create a bolt at a new random x location if enough time has passed as determined by
   * the wait variable & when to give a break in lightning strikes as well as when to display the
   * warning semi circle.
   */

  public void newBolt() {
    if (col == Color.YELLOW) {
      wait++;
      if (wait > 45) {
        posX = -15;
      }
      if (wait == 30) {
        newX = (int) (Math.random() * 32) * 25;
        while (newX == posX) {
          newX = (int) (Math.random() * 32) * 25;
        }
      }
      if (wait > 45 && wait < 60) {
        alert = true;
      }
      if (wait > 60) {
        alert = false;
        posX = newX;
        wait = 0;
      }
    } else {
      wait++;
      if (wait > 35) {
        posX = -15;
      }
      if (wait == 35) {
        newX = (int) (Math.random() * 32) * 25;
        while (newX == posX) {
          newX = (int) (Math.random() * 32) * 25;
        }
      }
      if (wait > 35 && wait < 50) {
        alert = true;
      }
      if (wait > 50) {
        alert = false;
        posX = newX;
        wait = 0;
      }
    }
  }

  /**
   * Used to determine if the Cloud has been struck.
   *
   * @return used to get to x location of the bolt
   */

  public int getX() {
    return posX;
  }

  /**
   * Used in determining if the Cloud becomes immune.
   *
   * @return used to get the wait variable
   */

  public int getWait() {
    return wait;
  }
}
