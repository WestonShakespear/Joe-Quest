package Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Board implements Runnable {

JFrame frame;

static int joeX = 260;
static int joeY = 260;
int up = 0;
int down = 0;
int right = 0;
int left = 0;
int wait = 12;
int canvasHeight = 516; 
int canvasWidth = 510;
int  screenHeight = 1000;
int  screenWidth = 1000;
static int x = 0;
static int y = 0;
int tryMove = 3;
static int npcX = x;
static int npcY = y;
static int followX;
static int followY;
static int npcSpeed = 25;
static int diAg = 0;
static int evod = 0;
static int bulY = joeY + 2;
static int bulX = joeX + 5;
static int start = 2;
static int go = 3;
static int loop = 4;

Canvas canvas;

BufferStrategy bufferStrategy;

boolean running = true;
boolean moveUp = true;
boolean moveDown = true;
boolean moveRight = true;
boolean moveLeft = true;
boolean Edge = false;
boolean interUp = true;
boolean interDown = true;
boolean interLeft = true;
boolean interRight = true;
static boolean waterMove = true;
static boolean NPCAlive = true;
static boolean moveX = joeX == npcX;
static boolean moveY = joeY == npcY;
static boolean fire = false;
static boolean bullets = true;
static boolean bulletsfire = false;


Rectangle bottomHit;
Rectangle topHit;
Rectangle rightHit;
Rectangle leftHit;
Rectangle horizHit;
Rectangle vertiHit;

Rectangle joeRect;
Rectangle npcRect;
Rectangle bottomColl;
Rectangle topColl;
Rectangle rightColl;
Rectangle leftColl;

Rectangle topBorder;
Rectangle bottomBorder;
Rectangle leftBorder;
Rectangle rightBorder;

Rectangle swimRect;

Rectangle bullet;

private String grass = "/GrassTile.png";
private static String water = "/WaterTile3.png";
private static String bridge = "/BridgeTile3.png";
private String code = "/CodeTile.png";
private String stone = "/StoneTile.png";

private static String Joe = "/Joe1Right.png";
private static String NPC = "/npc1Right.png";

private String Swim = "/hideBody.png";
private String bulletPic = "/bullet.png";


private Image grassII;
private Image waterII;
private Image bridgeII;

private Image codeII;
private Image stoneII;

public String direction;
public String leg;

private Image JoeII;
private Image NPCII;

private Image swimII;
private Image bulletII;

    
public Board() throws IOException {
	
frame = new JFrame("Joe's Adventures");

JPanel panel = (JPanel) frame.getContentPane();

panel.setLayout(new FlowLayout());
panel.setPreferredSize(new Dimension(canvasHeight, canvasWidth));
panel.setLayout(null);
canvas = new Canvas();
canvas.setBounds(0, 0, screenHeight, screenWidth);
canvas.setIgnoreRepaint(true);
panel.add(canvas);
canvas.addKeyListener(new KeyAdapter() {
@Override
public void keyPressed(KeyEvent evt) {
try {
moveIt(evt);
} catch (InterruptedException e) {
System.err.print("Error");
e.printStackTrace();
}
}
});

// Determine the new location of the window
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.pack();
frame.setResizable(false);
frame.setVisible(true);
frame.setLocationRelativeTo(null);
frame.setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/CodeTile.png"))); 

Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

canvas.createBufferStrategy(2);
bufferStrategy = canvas.getBufferStrategy();
canvas.requestFocus();
}
public void run() {
while (running = true) {
Paint();
try {
Thread.sleep(25);
} catch (InterruptedException e) {
}
}
}
public static void main(String[] args) throws IOException {
Board ex = new Board();
new Thread(ex).start();
npc.start();
waves.start();
fireThread.start();
}
public void Paint() {
Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
g.clearRect(0, 0, screenHeight, screenWidth);
PaintGame(g);
bufferStrategy.show();
}

protected void PaintGame (Graphics2D g)
{
	joeRect = new Rectangle(joeY + 5, joeX + 5, 5, 5);
	topBorder = new Rectangle(0, x - 128, 516, 16);
	bottomBorder = new Rectangle(0, x + 656, 516, 16);
	leftBorder = new Rectangle(y - 332, 0, 16, 516);
	rightBorder = new Rectangle(y + 672, 0, 16, 516);
	topHit = new Rectangle(0, 0, 516, 16);
	rightHit = new Rectangle(500, 0, 16, 516);
	leftHit = new Rectangle(0, 0, 16, 516);
	bottomHit = new Rectangle(0, 494, 516, 16);
	horizHit = new Rectangle(0, 259, 516, 3);
	vertiHit = new Rectangle(259, 0, 3, 510);
	npcRect = new Rectangle(npcY + 5, npcX + 5, 5, 5);
	bullet = new Rectangle(bulY, bulX, 5, 5);
	swimRect = new Rectangle(y - 304, x + 288, 964, 95);
	   g.fill(horizHit);
	   g.draw(horizHit);
	
 	
    Rectangle rect1 = new Rectangle(0, 0, 516, 510);
   // g.setColor(Color.BLACK);
    g.fill(rect1);
    g.fillRect(0, 0, 100, 100);
 	

    
    
    ImageIcon grassImg = new ImageIcon(this.getClass().getResource(grass));
    ImageIcon  waterImg = new ImageIcon(this.getClass().getResource(water));
    ImageIcon  bridgeImg = new ImageIcon(this.getClass().getResource(bridge));

    ImageIcon  codeImg = new ImageIcon(this.getClass().getResource(code));
    ImageIcon  stoneImg = new ImageIcon(this.getClass().getResource(stone));

    ImageIcon  JoeImg = new ImageIcon(this.getClass().getResource(Joe));
    
    ImageIcon  NPCImg = new ImageIcon(this.getClass().getResource(NPC));
    ImageIcon  bulletImg = new ImageIcon(this.getClass().getResource(bulletPic));
   

    grassII = grassImg.getImage();
    waterII = waterImg.getImage();
    bridgeII = bridgeImg.getImage();
    codeII = codeImg.getImage();
    stoneII = stoneImg.getImage();

    JoeII = JoeImg.getImage();
    NPCII = NPCImg.getImage();
    
    bulletII = bulletImg.getImage();
    
    
    //drawing the detection square
   g.fill(horizHit);
   g.draw(horizHit);
   
   g.fill(vertiHit);
   g.draw(vertiHit);
   
   g.fill(topHit);
   g.draw(topHit);
   
   g.fill(topBorder);
   g.draw(topBorder);
   
   g.fill(bottomBorder);
   g.draw(bottomBorder);
   
   g.fill(bottomHit);
   g.draw(bottomHit);
   
   g.fill(leftHit);
   g.draw(leftHit);

   g.fill(leftBorder);
   g.draw(leftBorder);
   
   g.fill(joeRect);
   g.draw(joeRect);
   
   g.fill(npcRect);
   g.draw(npcRect);
   
   g.fill(rightHit);
   g.draw(rightHit);
   
   g.fill(rightBorder);
   g.draw(rightBorder);
   
   g.fill(swimRect);
   g.draw(swimRect);
   
   //drawing the water, grass, and upper stone
   for(int i = -300; i < 664;i+=16)
   {
  // g.drawImage(stoneII, y + i, x, null);
   g.drawImage(stoneII, y + i, x - 128, null);
   g.drawImage(stoneII, y + i, x - 112, null);
   g.drawImage(grassII, y + i, x - 96, null);
   g.drawImage(grassII, y + i, x - 80, null);
   g.drawImage(grassII, y + i, x - 64, null);
   g.drawImage(grassII, y + i, x - 48, null);
   g.drawImage(grassII, y + i, x - 32, null);
   g.drawImage(grassII, y + i, x - 16, null);
   g.drawImage(grassII, y + i, x, null);
   g.drawImage(grassII, y + i, x + 16, null);
   g.drawImage(grassII, y + i, x + 32, null);
   g.drawImage(grassII, y + i, x + 48, null);
   g.drawImage(grassII, y + i, x + 64, null);
   g.drawImage(grassII, y + i, x + 80, null);
   g.drawImage(grassII, y + i, x + 96, null);
   g.drawImage(grassII, y + i, x + 112, null);
   g.drawImage(grassII, y + i, x + 128, null);
   g.drawImage(grassII, y + i, x + 144, null);
   g.drawImage(grassII, y + i, x + 160, null);
   g.drawImage(grassII, y + i, x + 176, null);
   g.drawImage(grassII, y + i, x + 192, null);
   g.drawImage(grassII, y + i, x + 208, null);
   g.drawImage(grassII, y + i, x + 224, null);
   g.drawImage(grassII, y + i, x + 240, null);
   g.drawImage(grassII, y + i, x + 256, null);
   g.drawImage(grassII, y + i, x + 256, null);
   g.drawImage(grassII, y + i, x + 272, null);
   g.drawImage(waterII, y + i, x + 288, null);
   g.drawImage(waterII, y + i, x + 304, null);
   g.drawImage(waterII, y + i, x + 320, null);
   g.drawImage(waterII, y + i, x + 336, null);
   g.drawImage(waterII, y + i, x + 352, null);
   g.drawImage(waterII, y + i, x + 368, null);
   
   g.drawImage(grassII, y + i, x + 384, null);
   g.drawImage(grassII, y + i, x + 400, null);
   g.drawImage(grassII, y + i, x + 416, null);
   g.drawImage(grassII, y + i, x + 432, null);
   g.drawImage(grassII, y + i, x + 448, null);
   g.drawImage(grassII, y + i, x + 464, null);
   g.drawImage(grassII, y + i, x + 480, null);
   g.drawImage(grassII, y + i, x + 496, null);
   g.drawImage(grassII, y + i, x + 512, null);
   g.drawImage(grassII, y + i, x + 528, null);
   g.drawImage(grassII, y + i, x + 544, null);
   g.drawImage(grassII, y + i, x + 560, null);
   g.drawImage(grassII, y + i, x + 576, null);
   g.drawImage(grassII, y + i, x + 592, null);
   g.drawImage(grassII, y + i, x + 608, null);
   g.drawImage(grassII, y + i, x + 624, null);
   g.drawImage(grassII, y + i, x + 640, null);
   g.drawImage(stoneII, y + i, x + 656, null);
   
  // g.drawImage(stoneII, y + i, x + 112, null);
   //g.drawImage(stoneII, y + i, x + 128, null);
   }
   
   //drawing the right stone
   for(int i = -128;i< 648; i+=16)
   {
	  // g.drawImage(stoneII, y - 300, x + i, null); 
	   g.drawImage(grassII, y - 316, x + i, null);
	   g.drawImage(stoneII, y - 332, x + i, null);
	   

   }
   
   //drawing the left stone
   for(int i = -128;i< 648; i+=16)
   {
	  // g.drawImage(stoneII, y - 300, x + i, null); 
	   g.drawImage(grassII, y + 656, x + i, null);
	   g.drawImage(stoneII, y + 672, x + i, null);
	   

   }
  // g.drawImage(waterII, y + i, x + 288, null);
  // g.drawImage(waterII, y + i, x + 304, null);
  // g.drawImage(waterII, y + i, x + 320, null);
  // g.drawImage(waterII, y + i, x + 336, null);
   //g.drawImage(waterII, y + i, x + 352, null);
  // g.drawImage(waterII, y + i, x + 368, null);
   
   
   
   //drawing the bridge
   g.drawImage(bridgeII, y + 36, x + 288, null);
   g.drawImage(bridgeII, y + 36, x + 304, null);
   g.drawImage(bridgeII, y + 36, x + 320, null);
   g.drawImage(bridgeII, y + 36, x + 336, null);
   g.drawImage(bridgeII, y + 36, x + 352, null);
   g.drawImage(bridgeII, y + 36, x + 368, null);
   
   
   g.fill(bullet);
   g.draw(bullet);
   
  if (bulletsfire == true)
  {
	   g.drawImage(bulletII, bulY - 5, bulX - 5 , null);
  }
  
 //drawing joe
   g.drawImage(JoeII, joeX, joeY, null);
   
   
   
   g.drawImage(NPCII, npcY, npcX, null);
   
   
   
   
   
  
   //stopping joe at the stone
   if (joeRect.intersects(topHit)) {
       moveUp = false;
   }
   if (joeRect.intersects(bottomHit)) {
       moveDown = false;
   }
   if (joeRect.intersects(leftHit)) {
       moveLeft = false;
   }
   if (joeRect.intersects(rightHit)) {
       moveRight = false;
   }
	   
   //making the walking simulation
   if (joeRect.intersects(horizHit)) {
       interUp = true;
       interDown = true;
   }
   
   //stopping the grid when stone comes in view
   if (topBorder.intersects(topHit)) {
       interUp = false;
   }
   if (bottomBorder.intersects(bottomHit)) {
       interDown = false;
   }
   
   if (leftBorder.intersects(leftHit)) {
       interLeft = false;
   }
   
   if (rightBorder.intersects(rightHit)) {
       interRight = false;
   }
   
   //detect if player is swimming
   if (joeRect.intersects(swimRect)) {
       
   }
   if (npcRect.intersects(joeRect)) {
       System.out.println("Game Over");
       System.exit(0);
   }
   if (bullet.intersects(npcRect)) {
       System.out.println("Game Won");
       System.exit(0);
   }
   
   
}
public void moveIt(KeyEvent evt) throws InterruptedException {
switch (evt.getKeyCode()) {
case KeyEvent.VK_UP:
	tryMove++;
	if (tryMove == loop) tryMove = start;
	if (tryMove == go)
	{
	moveDown = true;
	moveRight = true;
	moveLeft = true;
	if (moveUp == true)
	{
	if (interUp == true)
	{
		//interDown = true;
		up++;
		if ( (up & 1) == 0 )
		{
			moveUpEvenInter();
		}
		else
			moveUpOddInter();
		if (up == 10)
		{
			up = 2;
		}
		x += 2;
		npcX += 2;
	}
	if (interUp == false)
	{

		up++;
		if ( (up & 1) == 0 )
		{
			moveUpEven();
		}
		else
			moveUpOdd();
		if (up == 10)
		{
			up = 2;
		}

	}
	}
	}

break;
case KeyEvent.VK_LEFT:
	tryMove++;
	if (tryMove == loop) tryMove = start;
	if (tryMove == go)
	{
	moveDown = true;
	moveRight = true;
	moveUp = true;
	if (moveLeft == true)
	{
	if (interLeft == true)
	{
		//interDown = true;
		up++;
		if ( (up & 1) == 0 )
		{
			moveLeftEvenInter();
		}
		else
			moveLeftOddInter();
		if (up == 10)
		{
			up = 2;
		}
		y += 2;
		npcY += 2;
	}
	if (interLeft == false)
	{
		up++;
		if ( (up & 1) == 0 )
		{
			moveLeftEven();
		}
		else
			moveLeftOdd();
		if (up == 10)
		{
			up = 2;
		}
	}
	}
	}

break;
case KeyEvent.VK_RIGHT:
	tryMove++;
	if (tryMove == loop) tryMove = start;
	if (tryMove == go)
	{
	moveDown = true;
	moveUp = true;
	moveLeft = true;
	if (moveRight == true)
	{
	if (interRight == true)
	{
		//interDown = true;
		up++;
		if ( (up & 1) == 0 )
		{
			moveRightEvenInter();
		}
		else
			moveRightOddInter();
		if (up == 10)
		{
			up = 2;
		}
		y -= 2;
		npcY -= 2;
	}
	if (interRight == false)
	{
		up++;
		if ( (up & 1) == 0 )
		{
			moveRightEven();
		}
		else
			moveRightOdd();
		if (up == 10)
		{
			up = 2;
		}
	}
	}
	}
break;
case KeyEvent.VK_DOWN:
	tryMove++;
	if (tryMove == loop) tryMove = start;
	if (tryMove == go)
	{
	moveUp = true;
	moveRight = true;
	moveLeft = true;
	if (moveDown == true)
	{
	if (interDown == true)
	{
		//interUp = true;
		up++;
		if ( (up & 1) == 0 )
		{
			moveDownEvenInter();
		}
		else
			moveDownOddInter();
		if (up == 10)
		{
			up = 2;
		}
		x -= 2;
		npcX -= 2;
	}
	if (interDown == false)
	{
		up++;
		if ( (up & 1) == 0 )
		{
			moveDownEven();
		}
		else
			moveDownOdd();
		if (up == 10)
		{
			up = 2;
		}
	}
	}
	}
break;
case KeyEvent.VK_SPACE:
	bulletsfire = true;
	for(int b = 0;b < 100;b++)
	{
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bulY -= 1;
	}
	bulletsfire = false;
	fire = false;
	bulY = joeY;
break;
}
}
public void moveRightEven()
{

	joeX += 2;
	bulX += 2;
	Joe = "/Joe4Right.png";
	
}
public void moveRightEvenInter()
{

	
	Joe = "/Joe4Right.png";
	
}
public void moveRightOdd()
{
	try {
		Thread.sleep(25);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	joeX += 2;
	bulX +=2;
	Joe = "/Joe4Left.png";
	
}
public void moveRightOddInter()
{
	try {
		Thread.sleep(25);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}

	Joe = "/Joe4Left.png";
	
}
public void moveLeftEven()
{

	joeX -= 2;
	bulX -= 2;
	Joe = "/Joe3Right.png";
}
public void moveLeftEvenInter()
{


	Joe = "/Joe3Right.png";
}
public void moveLeftOdd()
{
	try {
		Thread.sleep(25);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	joeX -= 2;
	bulX -= 2;
	Joe = "/Joe3Left.png";
}
public void moveLeftOddInter()
{
	try {
		Thread.sleep(25);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}

	Joe = "/Joe3Left.png";
}
public void moveUpEven()
{

	joeY -= 2;
	bulY -= 2;
	Joe = "/Joe2Right.png";
}
public void moveUpEvenInter()
{

	try {
		Thread.sleep(25);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	Joe = "/Joe2Right.png";
	
}
public void moveUpOdd()
{
	try {
		Thread.sleep(25);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	joeY -= 2;
	bulY -= 2;
	Joe = "/Joe2Left.png";
}
public void moveUpOddInter()
{
	try {
		Thread.sleep(25);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
		
	Joe = "/Joe2Left.png";
	
}
public void moveDownEven()
{
	
	joeY += 2;
	bulY += 2;
	Joe = "/Joe1Right.png";
}
public void moveDownEvenInter()
{
	

	Joe = "/Joe1Right.png";
}
public void moveDownOdd()
{
	try {
		Thread.sleep(25);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	joeY += 2;
	Joe = "/Joe1Left.png";
}
public void moveDownOddInter()
{
	try {
		Thread.sleep(25);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}

	Joe = "/Joe1Left.png";
}





static Thread waves = new Thread(){
    public void run(){
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
    	  water = "/WaterTile1.png";
    	  bridge = "/BridgeTile1.png";
      while (waterMove == true)
      {
    	  try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
    	  water = "/WaterTile2.png";
    	  bridge = "/BridgeTile2.png";
    	  try {
  			Thread.sleep(1000);
  		} catch (InterruptedException e) {

  			e.printStackTrace();
  		}
      	  water = "/WaterTile3.png";
      	bridge = "/BridgeTile3.png";
      	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
    	  water = "/WaterTile1.png";
    	  bridge = "/BridgeTile1.png";
      }
    }
};


static Thread npc = new Thread(){
    public void run(){
    	while (NPCAlive == true)
    	{
    	
    	if (npcY != joeY)
    	{
    		followY();
    	}
    	if (npcX != joeX)
    	{
    		followX();
    	}
    	}
    	
    }
};
static Thread fireThread = new Thread(){
    public void run(){
    	while (bullets)
    	{
    	if (fire == true)
    	{
    		bulletsfire = true;
    		for(int b = 0;b < 100;b++)
    		{
    			try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			bulY -= 1;
    		}
    		bulletsfire = false;
    		fire = false;
    		bulY = joeY;
    	}
    	
    	}
    	
    }
};


public static void followXY()
{
	
}
public static void followX()
{
	while(moveX != true)
	{
		followX = npcX - joeX;
		try {
			Thread.sleep(npcSpeed);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (followX > 0)
		{
			evod++;
			if (evod == 10) evod = 2;
			if ( (evod & 1) == 0 )
			{
			NPC = "/npc2Right.png";
			}
			else
				NPC = "/npc2Left.png";
			npcX -= 1;
			diAg++;
			if (diAg == 10) diAg = 2;
			if (diAg == 3)
			{
			break;
			}
		}
		if (followX < 0)
		{
			evod++;
			if (evod == 10) evod = 2;
			if ( (evod & 1) == 0 )
			{
				NPC = "/npc1Left.png";
			}
			else
			
			NPC = "/npc1Right.png";
			npcX += 1;
			diAg++;
			if (diAg == 10) diAg = 2;
			if (diAg == 3)
			{
			break;
			}
		}
		if (followX == 0)
		{
			break;
		}
	}
}
public static void followY()
{
	while(moveY != true)
	{
	followY = npcY - joeY;
	try {
		Thread.sleep(npcSpeed);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if (followY > 0)
	{
		evod++;
		if (evod == 10) evod = 2;
		if ( (evod & 1) == 0 )
		{
			NPC = "/npc3Left.png";
		}
		else
		NPC = "/npc3Right.png";
		npcY -= 1;
		diAg++;
		if (diAg == 10) diAg = 2;
		if (diAg == 3)
		{
		break;
		}
	}
	if (followY < 0)
	{
		
		evod++;
		if (evod == 10) evod = 2;
		if ( (evod & 1) == 0 )
		{
			NPC = "/npc4Left.png";
		}
		else
		NPC = "/npc4Right.png";
		npcY += 1;
		diAg++;
		if (diAg == 10) diAg = 2;
		if (diAg == 3)
		{
		break;
		}
	}
	if (followY == 0)
	{
		break;
	}
	}
}
}












