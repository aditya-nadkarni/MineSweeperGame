import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import javax.swing.JPanel;

class Tile extends JPanel{
	private boolean blank = false;
	private boolean revealed;
	private boolean mine = false;
	private boolean flagged = false;
	private int number = -1;

	public Tile() {
		revealed = false;
	}

	public void reveal(){
		//setBackground(Color.WHITE);
		if (blank){
			setBackground(Color.WHITE);
		}
		if (number > 0){
			//setBackground(Color.WHITE);
			Graphics g = getGraphics();
			g.setColor(Color.BLACK);
			g.drawString(number + "", 10, 10);
			//repaint();

			//setBackground(Color.WHITE);
		}
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(!revealed){
			BufferedImage image = null;
			try {
				image = ImageIO.read(new File("src\\asteroid.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Can't Read");
				e.printStackTrace();
			}
			g.drawImage(image, 0, 0, null);
			//repaint();
		}
	}

	// Blank
	public boolean isBlank() {
		return blank;
	}

	public void setBlank(boolean blank) {
		this.blank = blank;
	}

	// Revealed
	public boolean isRevealed() {
		return revealed;
	}

	public void setRevealed(boolean revealed) {
		this.revealed = revealed;
	}

	// Mine
	public boolean isMine() {
		return mine;
	}

	public void setMine(boolean mine) {
		this.mine = mine;
	}

	// Number
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean isFlagged() {
		return flagged;
	}


	public void setFlagged(boolean flagged) {
		this.flagged = flagged;
	}
}
