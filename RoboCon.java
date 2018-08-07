/**
    Name:         Mary Y. Yu
    Email:        thom01@gmail.com
    Compilation:  javac RobotCon.java
    Execution:    java RoboCon
    Dependencies: Robo.java is needed
    Description:  This program emulate the control of the basic Robot movements. A 2D rectangle represents 
                  the Robot. Robot mpves North, South, East, West, and at customized angle (and # steps). 
                  Also included is Robot laying down and standing up.
*/

import java.awt.*;              // For Color, frame format, etc.
import java.awt.event.*;        // For event classes & listener interfaces.
import javax.swing.*;           // Forcomponents and containers.
import java.lang.Math;          // Distance calculation.
import javax.swing.border.*;    // For JFrame Borders
import javax.swing.JOptionPane; // For JOptionpanes

public class RoboCon extends JFrame 
{
    // Define piblic constants for Canvas.
    public static final int CANVAS_WIDTH = 500;
    public static final int CANVAS_HEIGHT = 500;
    public static final Color CANVAS_BG_COLOR = Color.CYAN;
         
    private DrawCanvas canvas; // custom drawing canvas (an object of inner Class "DawCanvas")
    private Robot robot;     // the moving object of Class MoveObj.
         
    // Constructor to set up the GUI components and event handlers
    public RoboCon() 
    {   
        String varU = "up", varD = "down";
        //Define Borders.
        Border outer = BorderFactory.createEmptyBorder(0, 15, 0, 0);
        Border inner = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border combined = BorderFactory.createCompoundBorder(outer, inner);
        Font myFont = new Font("Veranda", Font.BOLD, 14);       

        // Set Frame title bar text.
        setTitle("EMULATION: CONTROL OF ROBOT MOVEMENT.");
        
        //Specify an action for the frame exit.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Add a BorderLayout manager to the content pane.
        //setLayout(new BorderLayout(10, 10));
        setLayout(new FlowLayout(FlowLayout.CENTER));
         
        // create a panel and buttons, and add buttons to panel.
        //Create a button JPanel with FlowLayout.
        JPanel btnPanel = new JPanel(new GridLayout(2, 4));
        btnPanel.setSize (210, 200);

        // Create four buttons for North, South, East, and West movements.
        // Also create Custom Direction move, Lay Headdown, Lay Facedown. 
        JButton btnmNorth = new JButton("Move North"); 
        btnmNorth.setSize (new Dimension(50, 50));
        btnmNorth.setBorder(inner);
        btnmNorth.setFont(myFont);
        JButton btnmSouth = new JButton("Move South");
        btnmSouth.setSize (new Dimension(50, 50));
        btnmSouth.setBorder(inner);
        btnmSouth.setFont(myFont);
        JButton btnmEast = new JButton("Move East");
        btnmEast.setSize (new Dimension(50, 50));
        btnmEast.setBorder(inner);
        btnmEast.setFont(myFont);        
        JButton btnmWest = new JButton("Move West");
        btnmWest.setSize (new Dimension(50, 50));
        btnmWest.setBorder(inner);
        btnmWest.setFont(myFont);
        JButton btnCmove = new JButton("Custom Move");
        btnCmove.setSize (new Dimension(60, 50));
        btnCmove.setBorder(combined);
        btnCmove.setFont(myFont);
        JButton btnexit = new JButton("EXIT");
        btnexit.setSize (new Dimension(50, 50));
        btnexit.setBorder(inner);
        btnexit.setFont(myFont);
        btnexit.setForeground(Color.RED);
        JButton btnLayDn = new JButton("Lay Down");
        btnLayDn.setSize (new Dimension(60, 50));
        btnLayDn.setBorder(combined);
        btnLayDn.setFont(myFont);
        JButton btnStand = new JButton("Stand Up");
        btnStand.setSize (new Dimension(50, 50));
        btnStand.setBorder(inner);
        btnStand.setFont(myFont);
               
        // Add buttons to the button Panel.      
        btnPanel.add(btnmNorth);
        btnPanel.add(btnmSouth);
        btnPanel.add(btnCmove);
        btnPanel.add(btnexit);
        btnPanel.add(btnmEast);
        btnPanel.add(btnmWest);
        btnPanel.add(btnLayDn);
        btnPanel.add(btnStand);
        
        // Call DrawCanvas() method to customize canvas JPanel.
        canvas = new DrawCanvas();
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
         
        // Add button panel and canvas panel to content Pane.
        //add(canvas, BorderLayout.CENTER);
        //add(btnPanel, BorderLayout.SOUTH);
        add(canvas);
        add(btnPanel);

       // Calls Robot.java program to create the Robot with 
       // given x, y, width, height, color.
        robot = new Robot(CANVAS_WIDTH / 2 - 5, CANVAS_HEIGHT / 2 - 40,
                    10, 80, Color.RED);

        // The follwoing are Action listeners and Key listeners:
        //Move North Button.
        btnmNorth.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                mNorth();
                requestFocusInWindow(); // request for JFrame focus to receive KeyEvent
            }
        });
        
        //South Button.       
        btnmSouth.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                mSouth();
                requestFocusInWindow(); // request for JFrame focus to receive KeyEvent
            }
        });
        
        //East Button.
        btnmEast.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                mEast();
                requestFocusInWindow(); // request for JFrame focus to receive KeyEvent
            }
        });
        
        // West Button.
        btnmWest.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                mWest();
                requestFocusInWindow(); // request for JFrame focus to receive KeyEvent
            }
        });      
        
        // Custom Move Button.
        btnCmove.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                cMove();
                requestFocusInWindow(); // request for JFrame focus to receive KeyEvent
            }
        });      
             
        // exit Button.
        btnexit.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                exit();
                requestFocusInWindow(); // request for JFrame focus to receive KeyEvent
            }
        });      

        // Lay Down Button.
        btnLayDn.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                udPos(varD);
                requestFocusInWindow(); // request for JFrame focus to receive KeyEvent
            }
        });      

        // Standup Button.
        btnStand.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                udPos(varU);
                requestFocusInWindow(); // request for JFrame focus to receive KeyEvent
            }
        });      
                
        // KeyListener interface KeyPressed method, extends the
        // abstract KeyAdapter class that overrides movement key events.
        addKeyListener(new KeyAdapter() 
        {
            @Override
            public void keyPressed(KeyEvent evt) 
            {
                switch(evt.getKeyCode())
                {
                    case KeyEvent.VK_UP: mNorth(); break;
                    case KeyEvent.VK_DOWN: mSouth(); break;
                    case KeyEvent.VK_RIGHT: mEast();  break;
                    case KeyEvent.VK_LEFT: mWest(); break;
                    case KeyEvent.VK_F21: cMove(); break;
                    case KeyEvent.VK_F22: exit(); break;
                    case KeyEvent.VK_F23: udPos(varD); break;
                    case KeyEvent.VK_F24: udPos(varU); break;                   
                }
            }
        });
         
        pack();                     // pack all the components in the JFrame.
        setVisible(true);           // Make everything visible
        setSize(500, 600);          // Size of JFrame.
        requestFocusInWindow();     // Request JFrame as focus window to receive KeyEvent
    }

    // Inner class DrawCanvas creates a Canvas JPanel.
    // Calls Robot() to paint itself
    class DrawCanvas extends JPanel 
    {
        @Override
        public void paintComponent(Graphics g) 
        {
            super.paintComponent(g);            //Will Paint Background.
            setBackground(CANVAS_BG_COLOR);     // Set background of Canvas
            robot.paint(g);                     // the robot paints itself
        }
    }
    //Private Method to move the Robot North.
    private void mNorth() 
    {
        // Make a copy of current dimensions for repaint to erase the robot
        int copyx = robot.x;
        int copyy = robot.y;
        
        // update robot        
        robot.x -= (int) (Math.cos(1.57) * (10.0));
        robot.y -= (int) (Math.sin(1.57) * (10.0));

        if (copyy > 10)
        {
            // Move North within Canvas North boundary. 
            // Repaint only the affected void area and paint new location.
            canvas.repaint(copyx, copyy, robot.width, robot.height); // Clear old area to background
            canvas.repaint(robot.x, robot.y, robot.width, robot.height); // Paint new location
        }
        else
        {
            int copy2y = robot.y;
            robot.y = copyy;
            canvas.repaint(copyx, copy2y, robot.width, robot.height); // Clear old area to background
            canvas.repaint(robot.x, robot.y, robot.width, robot.height); // Paint new location            
        }
    }
     
    // Private method to move the Robot South.
    private void mSouth() 
    {
        // Make a copy of current dimensions for repaint to erase the robot
        int copyx = robot.x;
        int copyy = robot.y;
        
        // update robot        
        robot.x += (int) (Math.cos(1.57) * (10.0));
        robot.y += (int) (Math.sin(1.57) * (10.0));

        if ((copyy + robot.height) <  (CANVAS_HEIGHT - 10))
        {
            // Move south within Canvas South boundary. 
            // Repaint only the affected void area and paint new location.
            canvas.repaint(copyx, copyy, robot.width, robot.height); // Clear old area to background
            canvas.repaint(robot.x, robot.y, robot.width, robot.height); // Paint new location
        }
        else
        {
            int copy2y = robot.y;
            robot.y = copyy;
            canvas.repaint(copyx, copy2y, robot.width, robot.height); // Clear old area to background
            canvas.repaint(robot.x, robot.y, robot.width, robot.height); // Paint new location            
        }
    }

    // Private method to move the Robot East
    private void mEast() 
    {
        int copyx = robot.x;
        int copyy = robot.y;
        
        // update robot        
        robot.x += (int) (Math.cos(0) * (10.0));
        robot.y += (int) (Math.sin(0) * (10.0));

        if ((copyx + robot.width) <  (CANVAS_WIDTH - 15))
        {
            
            // Move East within Canvas East boundary. 
            // Repaint only the affected void area and paint new location.
            canvas.repaint(copyx, copyy, robot.width, robot.height); // Clear old area to background
            canvas.repaint(robot.x, robot.y, robot.width, robot.height); // Paint new location
        }
        else
        {
            int copy2x = robot.x;
            robot.x = copyx;
            canvas.repaint(copy2x, copyy, robot.width, robot.height); // Clear old area to background
            canvas.repaint(robot.x, robot.y, robot.width, robot.height); // Paint new location            
        }
    }
   
    //Private Method to move the Robot West
    private void mWest() 
    {
        // Make a copy of current dimensions for repaint to erase the robot
        int copyx = robot.x;
        int copyy = robot.y;
        
        // update robot        
        robot.x -= (int) (Math.cos(0) * (10.0));
        robot.y -= (int) (Math.sin(0) * (10.0));

        if (copyx > 15)
        {            
            // Move West within Canvas West boundary. 
            // Repaint only the affected void area and paint new location.
            canvas.repaint(copyx, copyy, robot.width, robot.height); // Clear old area to background
            canvas.repaint(robot.x, robot.y, robot.width, robot.height); // Paint new location
    
        }
        else
        {
            int copy2x = robot.x;
            robot.x = copyx;
            canvas.repaint(copy2x, copyy, robot.width, robot.height); // Clear old area to background
            canvas.repaint(robot.x, robot.y, robot.width, robot.height); // Paint new location            
        }    
    }
    
    //Private Method to move the Robot base on user input.
    private void cMove() 
    {
        int steps = 0, angle = 0;
        // Create input JoptionPane.
        JPanel fieldPanel = new JPanel();
     
        // Create and format input text fields and labels
        JTextField angFld = new JTextField(20);
        JTextField stpFld = new JTextField(20);
        JLabel angLbl = new JLabel("Angle in degrees: ");
        JLabel stpLbl = new JLabel("Number of Steps: ");        
        //Add components to JPanel
        fieldPanel.add(angLbl);
        fieldPanel.add(angFld);     
        fieldPanel.add(stpLbl);
        fieldPanel.add(stpFld);    
        
        int result = JOptionPane.showConfirmDialog(null, fieldPanel, 
                 "Customized Movement.", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION)
        {
            angle = Integer.parseInt(angFld.getText());
            if ((angFld.getText()).equalsIgnoreCase(""))
            {
                String in = JOptionPane.showInputDialog("ERROR! Please Enter Angle in degrees");
                angle = Integer.parseInt(angFld.getText());
            }
            steps = Integer.parseInt(stpFld.getText());
            if ((stpFld.getText()).equalsIgnoreCase(""))
            {
                String in = JOptionPane.showInputDialog("ERROR! Please enter number of Steps.");
                steps = Integer.parseInt(stpFld.getText());
            }
        }

        // Make a copy of current dimensions for repaint to erase the robot
        int copyx = robot.x;
        int copyy = robot.y;
        
        // update robot        
        robot.x += (int) ((Math.cos(angle*(3.14/180))) * (10.0*steps));
        robot.y -= (int) ((Math.sin(angle*(3.14/180)))* (10.0*steps));

        // Repaint only the affected area.
        canvas.repaint(copyx, copyy, robot.width, robot.height); // Clear old area to background
        canvas.repaint(robot.x, robot.y, robot.width, robot.height); // Paint new location
    }     
     
    //Private Method to exit program.
    private void exit() 
    {
        System.exit(0);
    }
    
    //Private Method to have Robot lay down and stand up.
    private void udPos(String pos) 
    {
        if (pos.equals("down"))
        {
            // Laying down.
            if (robot.height > robot.width)
            {
                int copyh = robot.height;
                int copyw = robot.width;
                int copyy = robot.y;
                robot.height = copyw;
                robot.width = copyh;
                robot.y = ((robot.y)+70);
                // Repaint only the affected area.
                canvas.repaint(robot.x, copyy, copyh, copyw); // Clear old area to background
                canvas.repaint(robot.x, robot.y, robot.height, robot.width); // Paint new location
            }
            else
            {
                // Already laying down.
                JOptionPane.showMessageDialog(null, "I'm already laying down");
            }
        }
        else if (pos.equals("up"))
        {
            // Stand up.
            if (robot.height < robot.width)
            {
                int copyh = robot.height;
                int copyw = robot.width;
                int copyy = robot.y;
                robot.height = copyw;
                robot.width = copyh;
                robot.y = ((robot.y)-70);
                // Repaint only the affected area.
                canvas.repaint(robot.x, copyy, copyh, copyw); // Clear old area to background
                canvas.repaint(robot.x, robot.y, robot.height, robot.width); // Paint new location
            }
            else
            {
                // Already laying down.
                JOptionPane.showMessageDialog(null, "I'm already standing up.");
            }
   
        }
    }     
               
    // The entry main() method
    public static void main(String[] args)
    {
        // Queue instance creation on he event dispatching thread 
        // (i.e until all pending events have been are processed)
        SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                new RoboCon(); // An instance of RoboCon()/
            }
        });
    }
}