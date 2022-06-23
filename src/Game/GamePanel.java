/*
      author @ammar
 */
package Game;

import Controller.KeyboardController;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;



public class GamePanel extends JPanel {
    
   

    private Timer gameTimer;
    private final KeyboardController controller;
    private final int gameWidth = 800;
    private final int gameHeight = 800;



    // Added Counters
    Random r = new Random();
    private int score = 0;
    private int level = 1;
    private int numberOfLives = 3;
    private static int bossHealth = 20;
    


    // Added Objects
    private Ship playerShip;
    private Ship singleLife;
    private Enemy enemy;
    private Shield shield;
    private Bullet bullet;
    private Beam beam, beam2, beam3;

    // Added Booleans
    private boolean newBulletCanFire = true;
    private boolean newBeamCanFire = true;
    

    // Added Array Lists
    private  ArrayList<Ship> lifeList = new ArrayList();
    private  ArrayList<Enemy> enemyList = new ArrayList();
    private  ArrayList<Shield> shieldList = new ArrayList();
    private ArrayList<Beam> beamList = new ArrayList();
    private ImageIcon background = new ImageIcon("images/backgroundSkin.jpg");

    private int time,time1 ;
    
    


    
// Used in the Enemy class to help with the draw method for the boss
    public static int getBossHealth() {
        return bossHealth;
    }


//SETUP GAME METHOD

    public  void setupGame() {
       
        // Sets enemies for normal levels
        if (level != 3 ) {
            for (int row = 0; row < 1; row++) {
                for (int column = 0; column < 4; column++) {
                    enemy = new Enemy((20 + (column * 100)), (20 + (row * 60)), level, 0, 1, null, 40, 40); // Enemy speed will increase each level
                    enemyList.add(enemy);
                }
            }
        }


    
        // Sets enemy for boss levels
        if (level == 3 ) {

            enemy = new Enemy(20, 20, 3, 0, 100, null, 150, 150);
            enemyList.add(enemy);
        }
        // Gives directions on level 1
        if (level == 1) {
            JOptionPane.showMessageDialog(null, "Welcome to Space Intruders!\n\nTHINGS TO KNOW:\n\n- Use left/right arrow keys to move\n- Press spacebar to shoot\n- The enemies get faster every level"
                    + "\n- BOSS on 3rd level\n\nHAVE FUN!");
        }
       

        // Sets the player's ship values   
        playerShip = new Ship(375, 730, null, controller);

        // Sets the life counter Ships
        for (int column = 0; column < numberOfLives; column++) {
            singleLife = new Ship(48 + (column * 20), 10, Color.WHITE, null);
            lifeList.add(singleLife);
        }

        // Sets the values for 3 rows and 3 columns of shields
        for (int row = 0;row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                shield = new Shield(100 + (column * 250), 650 - (row * 10), 70, 10, Color.RED);
                shieldList.add(shield);
            }
        }
    }
    
    
// PAINT
    @Override
    public void paint(Graphics g) {
        
       
        // Sets background image
        background.paintIcon(this, g,0 ,-150 );

       
        // Draws the player's ship
        playerShip.draw(g);
        for (int index = 0; index < beamList.size(); index++) {
            if (beamList.get(index).isColliding(playerShip)) {

                beamList.remove(index);
                playerShip.drawExplosion(g);
                time1 = 0;
                lifeList.remove(lifeList.size() - 1); // Removes life if hit by bullet
                
            }
        }

        // Draws 3 evenly-spaced shields 
        for (int index = 0; index < shieldList.size(); index++) {
            shieldList.get(index).draw(g);
        }
            
          
       

        //Draws 3 different kinds of aliens
        try {
            for (int index = 0; index < enemyList.size(); index++) {
                enemyList.get(index).draw(g);
                   
            }  
        } catch (IndexOutOfBoundsException e) {
        }
        
        

        // Draw a bullet on space bar press
        int key = controller.getKey();
        if (key == KeyEvent.VK_SPACE) {
            if (newBulletCanFire) {
                bullet = new Bullet(playerShip.getXPosition() + 22, playerShip.getYPosition() - 20, 0, Color.RED);
                
                newBulletCanFire = false;
            }
        }
        // Only attempts to draw bullet after key press
        if (bullet != null) {
            bullet.draw(g);
        }

        // Generates random beams shot from enemies
        if (level != 3 ) {
            if (newBeamCanFire) {
                for (int index = 0; index < enemyList.size(); index++) {
                    if (r.nextInt(30) == index) {
                        beam = new Beam(enemyList.get(index).getXPosition(), enemyList.get(index).getYPosition(), 0, Color.YELLOW);
                        beamList.add(beam);
                        
                    }
                    newBeamCanFire = false;
                }
            }
        }
        // Generates beams at a faster rate for boss
        if (level == 3) {
            if (newBeamCanFire) {
                for (int index = 0; index < enemyList.size(); index++) {
                    if (r.nextInt(5) == index) {
                        beam = new Beam(enemyList.get(index).getXPosition() + 75, enemyList.get(index).getYPosition() + 140, 0, Color.YELLOW);
                        beam2 = new Beam(enemyList.get(index).getXPosition(), enemyList.get(index).getYPosition() + 110, 0, Color.YELLOW);
                        beam3 = new Beam(enemyList.get(index).getXPosition() + 150, enemyList.get(index).getYPosition() + 110, 0, Color.YELLOW);
                        beamList.add(beam);
                        beamList.add(beam2);
                        beamList.add(beam3);
                       
                    }
                    newBeamCanFire = false;
                }
            }
        }
        // Draws the generated beams
        for (int index = 0; index < beamList.size(); index++) {
            beamList.get(index).draw(g);
        }
      

        // Sets the score display
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 260, 20);

        // Sets the life counter display
        g.setColor(Color.WHITE);
        g.drawString("Lives:", 11, 20);
        for (int index = 0; index < lifeList.size(); index++) {
            lifeList.get(index).lifeDraw(g);
        }
        // Sets level display
        g.setColor(Color.WHITE);
        g.drawString("Level " + level, 750, 20);
    
     
                

      
        // Draws a health display for boss level
        if (level == 3 ) {
            g.setColor(Color.WHITE);
            g.drawString("Boss Health: " + bossHealth, 352, 600);
        }
        //for level up displayed on scvreen
        if (level == 3 || level == 2)
        {
              if(time<100)
              {
                  g.setColor(Color.RED);
                  g.drawString("LEVEL UP!", 370, 400);   
              }
        }
        //for you lost a life displayed on screen
        if(lifeList.size() == 2 || lifeList.size() == 1)
        {
            if(time1<60)
            {
                g.setColor(Color.RED);
                g.drawString("You Lost a Life !",350,400);
            }

        }
                   
                
         
            
        }
    
    

// UPDATE GAME STATE
    
    public void updateGameState() {
        
        // Allows player to move left and right
        playerShip.move();
       //timer for you lost a life
        if(lifeList.size() == 2 ||  lifeList.size() == 1)
        {
            time1 += 1;
        }
     
       //timer for level up
        if(level == 2 || level == 3 ){
            time +=1;
        }
        

        // Makes enemies move and change direction at borders
        if ((enemyList.get(enemyList.size() - 1).getXPosition() + enemyList.get(enemyList.size() - 1).getXVelocity()) > 760 || (enemyList.get(0).getXPosition() + enemyList.get(0).getXVelocity()) < 0) {
            for (int index = 0; index < enemyList.size(); index++) {
                enemyList.get(index).setXVelocity(enemyList.get(index).getXVelocity() * -1);
                enemyList.get(index).setYPosition(enemyList.get(index).getYPosition() + 10);
            }
        } else {
            for (int index = 0; index < enemyList.size(); index++) {
                enemyList.get(index).move();
            }
        }

        // Move bullet
       if (bullet != null) {
            bullet.setYPosition(bullet.getYPosition() - 15);
            if (bullet.getYPosition() < 0) {
                newBulletCanFire = true;
            }

            // Checks for collisions with normal enemies
            for (int index = 0; index < enemyList.size(); index++) {
                if (bullet.isColliding(enemyList.get(index))) {
                    
                    

                    bullet = new Bullet(0, 0, 0, null);
                    newBulletCanFire = true;
                    // Updates score for normal levels
                    if (level != 3 ) {
                        score += 100;
                       
                        enemyList.remove(index);

                    }
                    // Updates score for boss levels
                    if (level == 3 ) {
                       
                        bossHealth -= 1;
                        if (bossHealth == 0) {
                            enemyList.remove(index);
                            score += 9000;
                        }
                    }
                }
            }

            // Checks for collisions with shield and bullets
            for (int index = 0; index < shieldList.size(); index++) {
                if (bullet.isColliding(shieldList.get(index))) {
                    // Each if statement changes color of the shield, indicating "strength"
                    // STRONG
                    if (shieldList.get(index).getColor() == Color.RED) {
                        shieldList.get(index).setColor(Color.ORANGE);
    
                       bullet = new Bullet(0, 0, 0, null);//destroys bullet when collides
                        newBulletCanFire = true;
                    // GOOD
                    } else if (shieldList.get(index).getColor() == Color.ORANGE) {
                        shieldList.get(index).setColor(Color.YELLOW);
                       
                       bullet = new Bullet(0, 0, 0, null);
                       newBulletCanFire = true;
                    // OKAY
                    } else if (shieldList.get(index).getColor() == Color.YELLOW) {
                        shieldList.get(index).setColor(Color.WHITE);
                       
                       bullet = new Bullet(0, 0, 0, null);
                       newBulletCanFire = true;
                    // WEAK, BREAKS ON HIT
                    } else if (shieldList.get(index).getColor() == Color.WHITE) {
                        shieldList.remove(index);
                        
                        bullet = new Bullet(0, 0, 0, null);
                        newBulletCanFire = true;
                    }
                }
            }
        }
  
      

        // Moves beams on normal levels
        if (level != 3 ) {
            if (beam != null) {
                for (int index = 0; index < beamList.size(); index++) {
                    beamList.get(index).setYPosition(beamList.get(index).getYPosition() + (4));
                    if (beamList.get(index).getYPosition() > 800) {
                        beamList.remove(index);
                    }
                }
            }
        }
        // Moves beams at a faster speed for boss
        if (level == 3 ) {
            if (beam != null) {
                for (int index = 0; index < beamList.size(); index++) {
                    beamList.get(index).setYPosition(beamList.get(index).getYPosition() + (6)); 
                    if (beamList.get(index).getYPosition() > 800) {
                        beamList.remove(index);
                    }
                }
            }
        }

        // Checks for beam and shield collisions
        try {
            for (int j = 0; j < shieldList.size(); j++) {
                for (int index = 0; index < beamList.size(); index++) {
                    if (beamList.get(index).isColliding(shieldList.get(j))) {
                        // STRONG
                        if (shieldList.get(j).getColor() == Color.RED) {
                            shieldList.get(j).setColor(Color.ORANGE);

                            beamList.remove(index);
                        // GOOD
                        } else if (shieldList.get(j).getColor() == Color.ORANGE) {
                            shieldList.get(j).setColor(Color.YELLOW);
                           
                            beamList.remove(index);
                        // OKAY
                        } else if (shieldList.get(j).getColor() == Color.YELLOW) {
                            shieldList.get(j).setColor(Color.WHITE);
                            
                            beamList.remove(index);
                        // WEAK, BREAKS ON HIT
                        } else if (shieldList.get(j).getColor() == Color.WHITE) {
                            shieldList.remove(j);
                            
                            beamList.remove(index);
                        }
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Exception thrown : indexOutOfBounds");
        }

        
       

        // Paces beam shooting by only allowing new beams to be fired once all old beams are off screen or have collided
        if (beamList.isEmpty()) {
            newBeamCanFire = true;
        }

        //Destroys shields if aliens collide with them
        for (int input = 0; input < enemyList.size(); input++) {
            for (int j = 0; j < shieldList.size(); j++) {
                if (enemyList.get(input).isColliding(shieldList.get(j))) {
                    shieldList.remove(j);
                }
            }
            // If aliens exceed this X Position, you reset the level and lose a life
            if (enemyList.get(input).getYPosition() + 50 >= 750) {
                enemyList.clear();
                shieldList.clear();
                lifeList.clear();
                beamList.clear();
                bossHealth = 20;
                numberOfLives -= 1;
                 
                setupGame();
            }
        }

        //Updates the life counter display 
        if (playerShip.isColliding) {
            int index = lifeList.size() - 1;
            lifeList.remove(index);
        } 
        // Ends game if player runs out of lives
        else if (lifeList.isEmpty()) {

            // Gives the player an option to play again or exit
            int answer = JOptionPane.showConfirmDialog(null, "Would you like to play again?", "You lost the game with " + score + " points", 0);
            // If they choose to play again, this resets every element in the game
            if (answer == 0) {
                lifeList.clear();
                enemyList.clear();
                shieldList.clear();
                beamList.clear();
 
                score = 0;
                level = 1;
                bossHealth = 20;
                numberOfLives = 3;
                newBulletCanFire = true;
                newBeamCanFire = true;
                
                setupGame();
            }
            // If they choose not to play again, it closes the game
            if (answer == 1) {
                System.exit(0);
            }
        }

        // Goes to next level, resets all lists, sets all counters to correct values
        if (enemyList.isEmpty()) {
            beamList.clear();
            shieldList.clear();
            
            lifeList.clear();

            level += 1;
            time = 0;
            time1 = 0;
        

            
            if(level > 3){
                int answer1 = JOptionPane.showConfirmDialog(null, "YOU WON"+"\nWould you like to play again?", "You won the game with " + score + " points", 0);
                 if (answer1 == 0) {
                        lifeList.clear();
                        enemyList.clear();
                        shieldList.clear();
                        beamList.clear();

                        score = 0;
                        level = 1;
                        bossHealth = 20;
                        numberOfLives = 3;
                        newBulletCanFire = true;
                        newBeamCanFire = true;

                        
            }
              else if (answer1 == 1) {
                System.exit(0);
            }
                   

            }
           
            bossHealth = 20;
            setupGame();
          
        }
        
       
    }

// GAME PANEL    
    
    public GamePanel() {
        // Set the size of the Panel
    
        this.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.setBackground(Color.BLACK);
        this.setLayout(null);

        // Register KeyboardController as KeyListener
        controller = new KeyboardController();
        this.addKeyListener(controller);

        // Call setupGame to initialize fields
        this.setupGame();
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    
     // Method to start the Timer for animation of the game.
    public void start() {
    
        gameTimer = new Timer(8 , new ActionListener(){
        
        
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the game's state and repaint the screen after every 8ms
        
                updateGameState();
                repaint();
            }
        });
        

        gameTimer.setRepeats(true);
        gameTimer.start();
       
    }


}

