package snake;

import snake.Snake;
import snake.SnakeFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SnakePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	
	public final int MyScale = SnakeFrame.SCALE/5;
    public static int currentColor;
    private int x1, x2, y1, y2;
    
    SnakeFrame snakeframe = SnakeFrame.snakeFrame;

    public SnakePanel(int x1, int x2, int y1, int y2, int color){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        currentColor = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(currentColor));
        g.fillRect(x1, y1, x2, y2);

        snakeframe = SnakeFrame.snakeFrame;

        if(snakeframe.started) {
            g.setColor(Color.BLACK);
            g.fillRect(snakeframe.apple.x * SnakeFrame.SCALE, snakeframe.apple.y * SnakeFrame.SCALE,
                    SnakeFrame.SCALE, SnakeFrame.SCALE);


            for (Point e : snakeframe.snake.snakeParts) {
                g.setColor(Color.BLACK);
                g.fillRect(e.x * SnakeFrame.SCALE, e.y * SnakeFrame.SCALE, SnakeFrame.SCALE, SnakeFrame.SCALE);
                g.setColor(new Color(currentColor));
                if (SnakeFrame.SCALE >= 10)
                    g.fillRect(e.x * SnakeFrame.SCALE + MyScale, e.y * SnakeFrame.SCALE + MyScale,
                            SnakeFrame.SCALE - 2 * MyScale, SnakeFrame.SCALE - 2 * MyScale);
            }

        } else {
            g.setColor(Color.BLACK);
            g.setFont(new Font("ComicSans", Font.BOLD, 20));
            g.drawString("PRESS SPACE TO START", 20, snakeframe.height/2);
            
            g.setFont(new Font("ComicSans", Font.BOLD, 16));
            
            if(snakeframe.SPEED == 20)
            	g.drawString("SELECTED LEVEL EASY" , 20, snakeframe.height/2 + 20);
            if(snakeframe.SPEED == 10)
            	g.drawString("SELECTED LEVEL NORMAL" , 20, snakeframe.height/2 + 20);
            if(snakeframe.SPEED == 5)
            	g.drawString("SELECTED LEVEL HARD" , 20, snakeframe.height/2 + 20);
            
            g.drawString("TO CHANGE LEVEL PRESS:", 20, snakeframe.height/2 + 60);
            g.drawString("1: EASY  2: NORMAL  3: HARD", 20, snakeframe.height/2 + 80);
        }
        if(snakeframe.theEnd){
            g.setColor(Color.BLACK);
            g.setFont(new Font("ComicSans", Font.BOLD, 16));
            g.drawString("GAME OVER", 20, snakeframe.height/2);
            g.drawString("YOUR SCORE: " + snakeframe.snake.score, 20, snakeframe.height/2 + 15);

            g.setFont(new Font("ComicSans", Font.BOLD, 20));
            g.drawString("TO PLAY AGAIN PRESS SPACE", 20, snakeframe.height/2 +40);
            
            
            g.setFont(new Font("ComicSans", Font.BOLD, 16));

            if(snakeframe.SPEED == 20)
            	g.drawString("SELECTED LEVEL EASY" , 20, snakeframe.height/2 + 60);
            if(snakeframe.SPEED == 10)
            	g.drawString("SELECTED LEVEL NORMAL" , 20, snakeframe.height/2 + 60);
            if(snakeframe.SPEED == 5)
            	g.drawString("SELECTED LEVEL HARD" , 20, snakeframe.height/2 + 60);
            
            g.drawString("TO CHANGE LEVEL PRESS:", 20, snakeframe.height/2 + 100);
            g.drawString("1: EASY  2: NORMAL  3: HARD", 20, snakeframe.height/2 + 120);
            
            drawScoreboard(g);
        }
    }
    
    
    private void drawScoreboard(Graphics g) {
    	int size = snakeframe.scoreboard.size();
    	int height = 20;
    	g.setColor(Color.BLUE);
    	g.drawString("TOP 5 SCORES:", snakeframe.width/2, height);
    	height += 20*size;
    	for(int score: snakeframe.scoreboard) {
    		g.drawString(score + " POINTS", snakeframe.width/2, height);
    		height -= 20;
    	}
    	
    }
}
