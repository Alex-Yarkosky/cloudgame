package cloudgame;

import javax.swing.JFrame;

/**
 * Creates the main that will run & display the CloudGamePanel.
 *
 * @author Alex Yarkosky
 */
public class CloudGameMain {
  /**
   * Creates the JFrame that will be used to run & display the game
   * as defined by the CloudGamePanel.
   *
   * @param args basic argument used within the structure of a main method
   */
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(new CloudGamePanel());
    frame.pack();
    frame.setVisible(true);
  }
}
