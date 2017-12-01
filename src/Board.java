import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

class Board implements MouseListener{
	JPanel playingBoard;
	Tile[][] board;
	int totalNumMines = 0;
	int totalNumFlags = 0;
	int totalNumAccurateFlags = 0;

	public void makeBoard(int rows, int cols, int numMinesWanted){
		playingBoard = new JPanel();
		board = new Tile[rows+2][cols+2];
		playingBoard.setLayout(new GridLayout(rows, cols));
		for (int row = 0; row < rows+2; row++){
			for (int col = 0; col < cols+2; col++){
				board[row][col] = new Tile();
			}
		}

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				JPanel tilePanel = new JPanel();
				tilePanel.setBackground(Color.white);
				Tile tile = new Tile();
				tile.setMaximumSize(new Dimension(20, 20));
				tile.setMinimumSize(new Dimension(20, 20));
				tile.setPreferredSize(new Dimension(20, 20));
				//tile.repaint();
				//tile.setBackground(Color.MAGENTA);
				LineBorder border = new LineBorder(Color.BLACK, 1);
				tile.setBorder(border);
				//tile.addMouseListener(tile);
				playingBoard.add(tile);
				board[row+1][col+1] = tile;
			}
		}

		playingBoard.addMouseListener(this);
		addMines(numMinesWanted);
		markBoard(rows, cols);
	}

	public void addMines(int numMines){
		for (int i = 0; i < numMines; i++){
			Random rand = new Random();
			int randRow;
			int randCol;
			do{
				randRow = rand.nextInt(29)+1;
				randCol = rand.nextInt(29)+1;
			} while (board[randRow][randCol].isMine());
			board[randRow][randCol].setMine(true);
			totalNumMines++;
			board[randRow][randCol].setBlank(false);
			System.out.println((i+1) + "--> (" + randRow + "," + randCol + ")");
			/*Random rand = new Random();
			int randRow = rand.nextInt(29)+1;
			int randCol = rand.nextInt(29)+1;
			if (!board[randRow][randCol].isMine()){
				board[randRow][randCol].setMine(true);
				board[randRow][randCol].setBlank(false);
				System.out.println("(" + randRow + "," + randCol + ")");
			}
			else{
				i--;
			}*/
		}
	}

	public void markBoard(int rows, int cols){
		int numMines = 0;
		for (int row = 1; row <= rows; row++){
			for (int col = 1; col <= cols; col++){
				if (!board[row][col].isMine()){
					// 1
					if (board[row - 1][col - 1].isMine()) {
						numMines++;
					}
					//2
					if (board[row - 1][col].isMine()) {
						numMines++;
					}
					//3
					if (board[row - 1][col + 1].isMine()) {
						numMines++;
					}
					//4
					if (board[row][col - 1].isMine()) {
						numMines++;
					}
					//5
					if (board[row][col + 1].isMine()) {
						numMines++;
					}
					//6
					if (board[row + 1][col - 1].isMine()) {
						numMines++;
					}
					//7
					if (board[row + 1][col].isMine()) {
						numMines++;
					}
					//8
					if (board[row + 1][col + 1].isMine()) {
						numMines++;
					}

					board[row][col].setNumber(numMines);
					if (numMines == 0){
						board[row][col].setBlank(true);
					}
					numMines = 0;
				}
			}
		}
	}

	public void clearTiles(int row, int col){
		Graphics g = board[row][col].getGraphics();
		Graphics2D g2 = (Graphics2D)g;
		if (board[row][col].isRevealed() || board[row][col].isMine() || board[row][col].isFlagged()){
			;
		}
		else{
			//System.out.println(row + ", " + col);
			board[row][col].setRevealed(true);
			if (board[row][col].isBlank()){
				//board[row][col].setBackground(Color.WHITE);
				board[row][col].reveal();
			}
			if (board[row][col].getNumber() > 0){
				//board[row][col].setBackground(Color.WHITE);
				/*g2.setColor(Color.BLACK);
				g2.drawString(board[row][col].getNumber() +"", 10, 10);*/
				board[row][col].reveal();

				//setBackground(Color.WHITE);
			}else{
				if(row-1 > 0 && col-1 > 0)
					clearTiles(row-1, col-1);
				if (row-1 > 0){
					clearTiles(row-1, col);
				}
				if (row-1 > 0 && col+1 <= 30){
					clearTiles(row-1, col+1);
				}
				if (col-1 > 0){
					clearTiles(row, col-1);
				}
				if (col+1 <= 30){
					clearTiles(row, col+1);
				}
				if(row+1 <= 30 && col-1 > 0){
					clearTiles(row+1, col-1);
				}
				if (row+1 <= 30){
					clearTiles(row+1, col);
				}
				if (row+1 <= 30 && col+1 <= 30){
					clearTiles(row+1, col+1);
				}
			}
		}
		//board[row][col].setRevealed(true);
	}

	public JPanel getPlayingBoard() {
		return playingBoard;
	}

	public Tile[][] getBoard() {
		return board;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int xPos = 1+ ((e.getX() - (e.getX()%20))/20);
		int yPos = 1+ ((e.getY() - (e.getY()%20))/20);
		if(e.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK){
			if (board[yPos][xPos].isMine()){
				board[yPos][xPos].setBackground(Color.RED);
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, "YOU LOSE!!");
			}
			else{
				//board[yPos][xPos].setRevealed(true);
				clearTiles(yPos, xPos);
			}
		}
		else if (e.getModifiersEx() == MouseEvent.BUTTON3_DOWN_MASK){
			Color c = board[yPos][xPos].getBackground();
			if (board[yPos][xPos].isRevealed() || c == Color.RED){
				;
			}
			else if (c != Color.MAGENTA){
				board[yPos][xPos].setBackground(Color.MAGENTA);
			}
			else if (c == Color.MAGENTA){
				board[yPos][xPos].setFlagged(true);
				totalNumFlags++;
				if (board[yPos][xPos].isMine()){
					totalNumAccurateFlags++;
				}
				board[yPos][xPos].setBackground(Color.GRAY);
				if (totalNumFlags == totalNumMines && totalNumMines == totalNumAccurateFlags){
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "YOU WIN!!");
				}
			}
		}

		/*if (e.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK){
			if (getBackground() == Color.GRAY){
				;
			}
			else{
				Graphics g = this.getGraphics();
				Graphics2D g2 = (Graphics2D)g;
				revealed = true;
				if (mine){
					setBackground(Color.RED);
				}
				else if (number > 0){
					g2.setColor(Color.BLACK);
					g2.drawString(number+"", 10, 10);
					//setBackground(Color.WHITE);
				}
				else if (blank){
					setBackground(Color.WHITE);
				}
			}
		}
		else if (e.getModifiersEx() == MouseEvent.BUTTON3_DOWN_MASK){
			Color c = getBackground();
			if (c == Color.RED){
				;
			}
			else if (c != Color.MAGENTA){
				setBackground(Color.MAGENTA);
			}
			else if (c == Color.MAGENTA){
				setBackground(Color.GRAY);
			}
		}*/
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


}
