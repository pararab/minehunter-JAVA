package Minehunter;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.MouseListener;

class MyButton {
	public static final int SIZE = 25;
	JButton jb;
	JLabel jl;
	int x;
	int y;
	int mineN = 0;
	int form = 0;
	boolean checkpush = false;
	boolean mine = false;
	
	MouseListener listener;
	
	public MyButton(int x, int y){
		this.x = x;
		this.y = y;
	}
}