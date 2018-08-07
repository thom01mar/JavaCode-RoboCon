/**
    Name:         Mary Y. Yu
    Email:        thom01@gmail.com
    Compilation:  javac Robot.java
    Execution:    java Robot
    Dependencies: RoboCon.java 
    Description:  This is a supporting Program for RobotCon.java. 
                  This program creates the Robot (emulated by a 
                  rectangle). This program paints after each movement.
 */

import java.awt.*;   // Using AWT's Graphics and Color
 
public class Robot 
{
   // Variables declaration and initialization.
   int x, y, width, height; // Use an rectangle for illustration
   Color color = Color.RED; // Color of the object
 
   // Constructor
   public Robot(int x, int y, int width, int height, Color color) 
   {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      this.color = color;
   }
 
   // Paint itself given Graphics g
   public void paint(Graphics g) 
   {
      g.setColor(color);
      g.fillRect(x, y, width, height); // Fill a rectangle
   }
}