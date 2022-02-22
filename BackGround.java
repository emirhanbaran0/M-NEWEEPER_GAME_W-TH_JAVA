import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BackGround implements MouseListener {
	JFrame frame;
	Buttons[][] board = new Buttons[10][10];
	Random rand = new Random();
	int openCell = 0;

	BackGround() {
		frame = new JFrame("MineWeeper");
		frame.setSize(600, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(10, 10));
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				Buttons b = new Buttons(row, col);
				frame.add(b);
				b.addMouseListener(this);
				board[row][col] = b;

			}
		}
		generateMine();
		mineNumber();
		//printMine();
		frame.setVisible(true);
	}

	public void open(int row, int col) {
		if (row < 0 || row >= board.length || col < 0 || col >= board[0].length
				|| board[row][col].getText().length() > 0 || board[row][col].isEnabled() == false) {
			return;
		} 
		else
		{
			if (board[row][col].getCount() != 0) {
				board[row][col].setText(board[row][col].getCount() + "");
				board[row][col].setEnabled(false);
				openCell++;
			} else {
				openCell++;
				board[row][col].setText(board[row][col].getCount() + "");
				board[row][col].setEnabled(false);
				open(row - 1, col);
				open(row + 1, col);
				open(row, col - 1);
				open(row, col + 1);

			}
		}
			
	}

	public void print() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				Image img = new ImageIcon(this.getClass().getResource("images.png")).getImage();
				if (board[row][col].isMine() == true) {
					board[row][col].setIcon(new ImageIcon(img));
				} else {
					board[row][col].setText(board[row][col].getCount() + "");
					board[row][col].setEnabled(false);
				}

			}
		}
	}

	public void mineNumber() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				if (board[row][col].isMine()) {
					counting(row, col);

				}

			}
		}
	}

	public void counting(int row, int col) {

		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = col - 1; j <= col + 1; j++) {
				try {
					int value = board[i][j].getCount();
					board[i][j].setCount(++value);
				} catch (Exception E) {

				}
			}
		}

	}

	public void generateMine() {
		int i = 0;
		while (i < 10) {
			int randRow, randCol;
			randRow = rand.nextInt(board.length);
			randCol = rand.nextInt(board[0].length);
			if (board[randRow][randCol].isMine() == false) {
				board[randRow][randCol].setMine(true);
				i++;
			}

		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Buttons b = (Buttons) e.getComponent();
		Image imgFlag = new ImageIcon(this.getClass().getResource("flag.png")).getImage();
		Image imgMine = new ImageIcon(this.getClass().getResource("images.png")).getImage();
		if (e.getButton() == 1) {
			//System.out.print("Sol Tik");
			{
				if (b.isMine()) {
					JOptionPane.showMessageDialog(frame, "You Clicked the Mine GAME OVER!");
					print();
				} else {
					open(b.getRow(), b.getCol());
					if (openCell == (board.length * board[0].length) - 10) {
						JOptionPane.showMessageDialog(frame, "CONGRATULATİONS YOU FİNİSHED THE GAME!");
						print();
					}
				}
			}
		}  if (e.getButton() == 3) {

			//System.out.print("Sağ Tik");
			if (b.isFlag() == false) {
				b.setIcon(new ImageIcon(imgFlag));
				b.setFlag(true);
			} else {
				b.setIcon(null);
				b.setFlag(false);
			}
		}

	}

	public void printMine() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				Image img = new ImageIcon(this.getClass().getResource("images.png")).getImage();
				if (board[row][col].isMine() == true) {
					board[row][col].setIcon(new ImageIcon(img));
				}

			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
