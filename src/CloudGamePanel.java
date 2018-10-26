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
 * Creates the Panel that is used to run & interact with the game
 *
 * @author Alex Yarkosky
 */
public class CloudGamePanel extends JPanel implements Runnable, MouseListener, MouseMotionListener {
  /**
   * final int PANEL_WIDTH used to define how long the displayed window will be final int
   * PANEL_HEIGHT used to define how tall the displayed window will be Thread thread used to start
   * the game Cloud c used as the user controlled object in playing the game Lightning l used as the
   * obstacles the user tries to avoid boolean gameOver used to determine when the user has lost the
   * game int mouseX used to store the x location of the mouse int startTime used to store the
   * starting time of the game & to determine the user's score int endTime used to store the end
   * time of the game when & to determine the user's score
   */
  public final int PANEL_WIDTH = 800;

  public final int PANEL_HEIGHT = 450;
  private Thread thread;
  private Cloud c;
  private Lightning l1;
  private Lightning l2;
  private boolean gameOver;
  private int mouseX;
  private int startTime;
  private int endTime;
  /**
   * Instantiates all nonfinal variables as well as adding MouseMotionListener & MouseListener along
   * with setting the background color & defining the dimensions of the displayed window
   */
  public CloudGamePanel() {
    l1 = new Lightning(Color.YELLOW);
    l2 = new Lightning(new Color(55, 255, 255));
    c = new Cloud(l1, l2);
    mouseX = 0;
    gameOver = false;
    this.addMouseMotionListener(this);
    this.addMouseListener(this);
    this.setBackground(Color.DARK_GRAY);
    this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
    thread = new Thread(this);
    thread.start();
  }
  /**
   * Creates the visual display of the objects in the game as well as the game over screen with the
   * calculated score based off of ten times the seconds the user survived
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (gameOver) {
      g.setColor(Color.YELLOW);
      g.setFont(new Font("Courier", Font.BOLD, 30));
      g.drawString("GAME OVER", 330, 200);
      g.setColor(new Color(55, 255, 255));
      g.drawString("SCORE:  " + (endTime - startTime), 320, 250);
    } else {
      c.paint(g);
      l1.paint(g);
      l2.paint(g);
    }
  }
  /**
   * updates the game by moving the Cloud object if necessary, checking the Cloud's location
   * thereafter, attempting to create a new lightning bolt, & checking if the game is over
   */
  public void run() {
    for (; ; ) {
      c.move(mouseX);
      c.checkOutOfBounds();
      l1.newBolt();
      l2.newBolt();
      c.checkImmunity();
      c.checkFirstStrike();
      c.checkSecondStrike();
      repaint();
      checkGameOver();
      if (gameOver) break;
      try {
        Thread.sleep(15);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  /**
   * Determines if the user has lost by checking if the Cloud has no segments left & if so sets the
   * gameOver variable to true
   */
  public void checkGameOver() {
    if (c.getCloudSegments() == 0) {
      endTime = (int) Math.round(System.currentTimeMillis() / 100.0);
      gameOver = true;
    }
  }
  /**
   * Sets startTime to the time that the game was started at, allows the Cloud object to move, &
   * resets the game if the game was already lost
   */
  public void mouseClicked(MouseEvent e) {
    if (!c.getIsMoving()) startTime = (int) Math.round(System.currentTimeMillis() / 100.0);
    c.startMoving();
    if (gameOver) {
      l1 = new Lightning(Color.YELLOW);
      l2 = new Lightning(new Color(55, 255, 255));
      c = new Cloud(l1, l2);
      mouseX = 0;
      gameOver = false;
      thread = new Thread(this);
      thread.start();
    }
  }
  /** not used */
  public void mouseEntered(MouseEvent e) {}
  /** not used */
  public void mouseExited(MouseEvent e) {}
  /** not used */
  public void mousePressed(MouseEvent e) {}
  /** not used */
  public void mouseReleased(MouseEvent e) {}
  /** not used */
  public void mouseDragged(MouseEvent e) {}
  /**
   * sets the mouseX variable to the current position of the mouse minus 30 as to center the mouse
   * in the middle of the Cloud object visual when it needs to move no more
   */
  public void mouseMoved(MouseEvent mouse) {
    mouseX = mouse.getX() - 30;
  }
}
