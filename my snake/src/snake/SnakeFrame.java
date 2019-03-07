package snake;

import snake.Snake;
import snake.SnakePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class SnakeFrame implements ActionListener, KeyListener {
    public static final int SCALE = 20;
    public static int SPEED = 10; // 5 - HARD	10 - NORMAL	20 - EASY
    public static int multiplier = 10; // 20 - HARD	10 - NORMAL	5 - EASY

    public static SnakeFrame snakeFrame;
    
    boolean theEnd = false, started = false;
    int lastStep = -1;
    int width, height;
    private int ticks = 0, step = 0;

    public TreeSet<Integer> scoreboard = new TreeSet<>(); 
    
    Snake snake;

    Point apple;

    public Random random = new Random();

    public JFrame jframe;
    public SnakePanel panel;

    public Timer timer = new Timer(5 , this);

    public Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

    public SnakeFrame(int width, int height, int color) {
        this.width = width;
        this.height = height;

        jframe = new JFrame();
        jframe.setTitle("Snake");
        jframe.setVisible(true);
        jframe.setSize(width+15, height+35);
        jframe.setLocation((dimension.width - width) / 2, (dimension.height - height) / 2);

        panel = new SnakePanel(0, width, 0, height, color);

        jframe.add(panel);
        jframe.addKeyListener(this);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        snake = new Snake();

        apple = new Point(width/10, height/10);
        newApple();
        
        reset();
    }

    @Override
    public void actionPerformed(ActionEvent arg) {
        panel.repaint();
        ticks++;


        if (ticks % SPEED == 0 && snake.head != null && !theEnd && started) {

            if (apple != null) {
                if (snake.head.equals(apple)) {
                    snake.score++;
                    snake.taillength++;
                    newApple();
                }
            }

            snake.snakeParts.add(new Point(snake.head.x, snake.head.y));
            if (snake.direction == Snake.UP) {
                if (snake.head.y - 1 >= 0 && !collision(snake.head.x, snake.head.y-1)) {
                    snake.head = new Point(snake.head.x, snake.head.y - 1);
                    step++;
                } else {
                    snake.head = new Point(snake.head.x, snake.head.y - 1);
                    snake.score *=multiplier;
                	scoreboard.add(snake.score);
                	theEnd = true;
                    if(scoreboard.size()>5) 
                    	scoreboard.pollFirst();
                }
            }
            if (snake.direction == Snake.DOWN) {
                if (snake.head.y + 1 < height/SCALE && !collision(snake.head.x, snake.head.y+1)) {
                    snake.head = new Point(snake.head.x, snake.head.y + 1);
                    step++;
                } else {
                    snake.head = new Point(snake.head.x, snake.head.y + 1);
                    snake.score *=multiplier;
                	scoreboard.add(snake.score);
                	theEnd = true;
                    if(scoreboard.size()>5) 
                    	scoreboard.pollFirst();
                }
            }
            if (snake.direction == Snake.LEFT) {
                if (snake.head.x - 1 >= 0 && !collision(snake.head.x-1, snake.head.y)) {
                    snake.head = new Point(snake.head.x - 1, snake.head.y);
                    step++;
                } else {
                	snake.head = new Point(snake.head.x - 1, snake.head.y);
                    snake.score *=multiplier;
                	scoreboard.add(snake.score);
                   
                    theEnd = true;
                    if(scoreboard.size()>5) 
                    	scoreboard.pollFirst();
                }
            }
            if (snake.direction == Snake.RIGHT) {
                if (snake.head.x + 1 < width / SCALE && !collision(snake.head.x + 1, snake.head.y)) {
                    snake.head = new Point(snake.head.x + 1, snake.head.y);
                    step++;
                } else {
                    snake.head = new Point(snake.head.x + 1, snake.head.y);
                    snake.score *=multiplier;
                	scoreboard.add(snake.score);
                	theEnd = true;
                    if(scoreboard.size()>5) 
                    	scoreboard.pollFirst();
                }
            }

            if(snake.snakeParts.size() > snake.taillength && !theEnd){
                snake.snakeParts.remove(0);
            }

        }

    }


    private void newApple(){
        boolean done = false;
        while (!done) {
            done = true;
            apple.setLocation(random.nextInt(width/SCALE), random.nextInt(height/SCALE));
            for(Point p: snake.snakeParts){
                if (apple.equals(p))
                    done = false;
            }
        }

    }

    private boolean collision(int x, int y){
        for(Point p: snake.snakeParts){
            if(p.x == x && p.y == y) {
            	return true;
            }
        }
        return false;
    }

    public static void main(int width, int height, int color){
        snakeFrame = new SnakeFrame(width, height, color);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(lastStep < step && !theEnd && started) {
            lastStep = step;

            if ((key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) && snake.direction != Snake.RIGHT) {
                snake.direction = Snake.LEFT;
            }
            if ((key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) && snake.direction != Snake.LEFT) {
                snake.direction = Snake.RIGHT;
            }
            if ((key == KeyEvent.VK_W || key == KeyEvent.VK_UP) && snake.direction != Snake.DOWN) {
                snake.direction = Snake.UP;
            }
            if ((key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) && snake.direction != Snake.UP) {
                snake.direction = Snake.DOWN;
            }
        }

        if(!started) {
        	if(key == KeyEvent.VK_1) {
            	SPEED = 20;
            	multiplier = 5;
        	}
        	if(key == KeyEvent.VK_2) {
            	SPEED = 10;
            	multiplier = 10;
        	}
            if(key == KeyEvent.VK_3) {
            	SPEED = 5;
            	multiplier = 20;
        	}
        	
        	if(key == KeyEvent.VK_SPACE) {
                started = true;
                reset();
            }
            
        }

        if(theEnd){
        	if(key == KeyEvent.VK_1) {
            	SPEED = 20;
            	multiplier = 5;
        	}
        	if(key == KeyEvent.VK_2) {
            	SPEED = 10;
            	multiplier = 10;
        	}
            if(key == KeyEvent.VK_3) {
            	SPEED = 5;
            	multiplier = 20;
        	}
        	
        	if(key == KeyEvent.VK_SPACE) {
                started = true;
                reset();
            }
        	
            if(key == KeyEvent.VK_SPACE)
                reset();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    void reset(){
    	snake.reset();

        newApple();
        ticks = 0;
        step = 0;
        lastStep = -1;

        theEnd = false;
        timer.restart();

    }
}
