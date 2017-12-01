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

class TheGame extends JFrame implements ActionListener{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	int dimension = 0;
	Board board;
	int numMines = 0;
	Timer timer;
	int timePassed;
	JLabel time;

	public TheGame() {
		setBounds(0, 0, 800, 800);
		setMaximumSize(new Dimension(800, 800));
		setMinimumSize(new Dimension (800, 800));
		setPreferredSize(new Dimension (800, 800));
		setLayout(new GridBagLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().setBackground(Color.CYAN);
		board = new Board();
		GridBagConstraints g = new GridBagConstraints();
		g.anchor = GridBagConstraints.CENTER;



		JMenuBar menuBar = new JMenuBar();

		JMenu gameMenu = new JMenu("Game");
		JMenuItem menuNewGame = new JMenuItem("New Game", 'n');
		JMenuItem menuExit = new JMenuItem("Exit", 'x');

		JMenu optionsMenu = new JMenu("Options");
		JMenuItem menuTotalMines = new JMenuItem("Total Mines", 'm');

		JMenu helpMenu = new JMenu("Help");
		JMenuItem menuHowToPlay = new JMenuItem("How To Play", 'h');
		JMenuItem menuAbout = new JMenuItem("About", 'a');

		gameMenu.add(menuNewGame);
		gameMenu.add(menuExit);

		optionsMenu.add(menuTotalMines);

		helpMenu.add(menuHowToPlay);
		helpMenu.add(menuAbout);

		menuBar.add(gameMenu);
		menuBar.add(optionsMenu);
		menuBar.add(helpMenu);
		menuBar.setVisible(true);

		setJMenuBar(menuBar);

		menuNewGame.addActionListener(this);
		menuTotalMines.addActionListener(this);

		timer = new Timer(1000, this);
		timer.setActionCommand("timer");

		GridBagConstraints g2 = new GridBagConstraints();
		g2.anchor = GridBagConstraints.SOUTH;
		JPanel timeDisplay = new JPanel();
		timeDisplay.setBounds(500, 650, 100, 50);
		timeDisplay.setPreferredSize(new Dimension(100, 40));
		timeDisplay.setMinimumSize(new Dimension (100, 40));
		timePassed = 0;
		time = new JLabel();
		TitledBorder border = new TitledBorder("Time Passed");
		timeDisplay.setBorder(border);
		time.setText(timePassed + "");

		timeDisplay.add(time);
		add(timeDisplay);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() != null){
			if (e.getActionCommand().equals("Total Mines")){
				String mines = JOptionPane.showInputDialog("Total Number of Mines");
				if (mines == null){
					return;
				}
				while(mines.equals("")){
					mines = JOptionPane.showInputDialog("Total Number of Mines");
					if (mines == null){
						return;
					}
				}
				numMines = Integer.parseInt(mines);
				System.out.println(numMines);
				board.makeBoard(30, 30, numMines);
				add(board.getPlayingBoard());
				pack();
				timer.start();
			}
			if (e.getActionCommand().equals("New Game")){
				String mines = JOptionPane.showInputDialog("Total Number of Mines");
				if (mines == null){
					return;
				}
				while(mines.equals("")){
					mines = JOptionPane.showInputDialog("Total Number of Mines");
					if (mines == null){
						return;
					}
				}
				numMines = Integer.parseInt(mines);
				System.out.println(numMines);
				board.makeBoard(30, 30, numMines);
				add(board.getPlayingBoard());
				pack();
				timer.start();
			}
			if (e.getActionCommand().equals("timer")){
				timePassed++;
				time.setText(timePassed + "");
			}
		}
	}

}
