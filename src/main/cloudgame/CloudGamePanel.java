package cloudgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

/**
 * Creates the Panel that is used to run & interact with the game.
 *
 * @author Alex Yarkosky
 */
public class CloudGamePanel extends JPanel implements Runnable, MouseListener, MouseMotionListener {
  /**
   * final int panelWidth used to define how long the displayed window will be.
   * final int panelHeight used to define how tall the displayed window will be.
   * Thread thread used to start the game.
   * Cloud cloud used as the user controlled object in playing the game.
   * Lightning lightning1 used as an obstacle the user tries to avoid.
   * Lightning lightning2 used as an obstacle the user tries to avoid.
   * boolean gameOver used to determine when the user has lost the game.
   * int mouseX used to store the x location of the mouse.
   * int startTime used to store the starting time of the game & to determine the user's score.
   * int endTime used to store the end time of the game when & to determine the user's score.
   */
  public final int panelWidth = 800;
  public final int panelHeight = 450;
  private Thread thread;
  private Cloud cloud;
  private Lightning lightning1;
  private Lightning lightning2;
  private boolean gameOver;
  private int mouseX;
  private int startTime;
  private int endTime;

  /**
   * Instantiates all nonfinal variables as well as adding MouseMotionListener & MouseListener along
   * with setting the background color & defining the dimensions of the displayed window.
   */

  public CloudGamePanel() {
    lightning1 = new Lightning(Color.YELLOW);
    lightning2 = new Lightning(new Color(55, 255, 255));
    cloud = new Cloud(lightning1, lightning2);
    mouseX = 0;
    gameOver = false;
    this.addMouseMotionListener(this);
    this.addMouseListener(this);
    this.setBackground(Color.DARK_GRAY);
    this.setPreferredSize(new Dimension(panelWidth, panelHeight));
    thread = new Thread(this);
    thread.start();
  }

  /**
   * Creates the visual display of the objects in the game as well as the game over screen with the
   * calculated score based off of ten times the seconds the user survived.
   */

  public void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    if (gameOver) {
      graphics.setColor(Color.YELLOW);
      graphics.setFont(new Font("Courier", Font.BOLD, 30));
      graphics.drawString("GAME OVER", 330, 200);
      graphics.setColor(new Color(55, 255, 255));
      graphics.drawString("SCORE:  " + (endTime - startTime), 320, 250);
    } else {
      cloud.paint(graphics);
      lightning1.paint(graphics);
      lightning2.paint(graphics);
    }
  }

  /**
   * updates the game by moving the Cloud object if necessary, checking the Cloud's location
   * thereafter, attempting to create a new lightning bolt, & checking if the game is over.
   */

  public void run() {
    for (; ; ) {
      cloud.move(mouseX);
      cloud.checkOutOfBounds();
      lightning1.newBolt();
      lightning2.newBolt();
      cloud.checkImmunity();
      cloud.checkFirstStrike();
      cloud.checkSecondStrike();
      repaint();
      checkGameOver();
      if (gameOver) {
        break;
      }
      try {
        Thread.sleep(15);
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
    }
  }

  /**
   * Determines if the user has lost by checking if the Cloud has no segments left & if so sets the
   * gameOver variable to true.
   */

  public void checkGameOver() {
    if (cloud.getCloudSegments() == 0) {
      endTime = (int) Math.round(System.currentTimeMillis() / 100.0);
      gameOver = true;
    }
  }

  /**
   * Sets startTime to the time that the game was started at, allows the Cloud object to move, &
   * resets the game if the game was already lost.
   */

  public void mouseClicked(MouseEvent event) {
    if (!cloud.getIsMoving()) {
      startTime = (int) Math.round(System.currentTimeMillis() / 100.0);
    }
    cloud.startMoving();
    if (gameOver) {
      lightning1 = new Lightning(Color.YELLOW);
      lightning2 = new Lightning(new Color(55, 255, 255));
      cloud = new Cloud(lightning1, lightning2);
      mouseX = 0;
      gameOver = false;
      thread = new Thread(this);
      thread.start();
    }
  }

  /** not used. */

  public void mouseEntered(MouseEvent event) {}

  /** not used. */

  public void mouseExited(MouseEvent event) {}

  /** not used. */

  public void mousePressed(MouseEvent event) {}

  /** not used. */

  public void mouseReleased(MouseEvent event) {}

  /** not used. */

  public void mouseDragged(MouseEvent event) {}

  /**
   * sets the mouseX variable to the current position of the mouse minus 30 as to center the mouse
   * in the middle of the Cloud object visual when it needs to move no more.
   */

  public void mouseMoved(MouseEvent mouse) {
    mouseX = mouse.getX() - 30;
  }
}
