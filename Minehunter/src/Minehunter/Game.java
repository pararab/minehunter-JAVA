package Minehunter;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;

class Game extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	
	MainFrame win;	
	MyButton mb[][];
	JImagePanel mineNP;
	JLabel mineImg, mineNL;
	
	JPanel slidePanel;
	JLabel slide;
	
	int[] mx;
	int[] my;
	int minecheckN = 0;
	static int totalmineN = 15;
	static int max_x = 20;
	static int max_y = 10;
	
	public int mineCount;
	
	void makemine(MyButton mb[][], int mx[], int my[]){
		Random random = new Random();
	
		mx[0] = random.nextInt(max_x);
		my[0] = random.nextInt(max_y);
		
		for(int i=1; i<totalmineN; i++){
			boolean pass = true;
			do
			{
				mx[i] = random.nextInt(max_x);
				my[i] = random.nextInt(max_y);
				
				pass = true;
				for(int j = 0; j<i; j++){
					if(mx[i] == mx[j] && my[i] == my[j])
						pass = false;
				}
			} while(!pass);
		}
		
		for(int i=0; i<totalmineN; i++){
			mb[mx[i]][my[i]].mine = true;
			if(mx[i] == 0){
				if(my[i] == 0){
					mb[mx[i]+1][my[i]].mineN++;
					mb[mx[i]][my[i]+1].mineN++;
					mb[mx[i]+1][my[i]+1].mineN++;
				}
				else if(my[i] == max_y-1){
					mb[mx[i]+1][my[i]].mineN++;
					mb[mx[i]][my[i]-1].mineN++;
					mb[mx[i]+1][my[i]-1].mineN++;
				}
				else {
					mb[mx[i]][my[i]-1].mineN++;
					mb[mx[i]+1][my[i]-1].mineN++;
					mb[mx[i]+1][my[i]].mineN++;
					mb[mx[i]][my[i]+1].mineN++;
					mb[mx[i]+1][my[i]+1].mineN++;
				}
			}
			else if(mx[i] == max_x-1){
				if(my[i] == 0){
					mb[mx[i]-1][my[i]].mineN++;
					mb[mx[i]][my[i]+1].mineN++;
					mb[mx[i]-1][my[i]+1].mineN++;
				}
				else if(my[i] == max_y-1){
					mb[mx[i]-1][my[i]].mineN++;
					mb[mx[i]][my[i]-1].mineN++;
					mb[mx[i]-1][my[i]-1].mineN++;
				}
				else {
					mb[mx[i]-1][my[i]-1].mineN++;
					mb[mx[i]][my[i]-1].mineN++;
					mb[mx[i]-1][my[i]].mineN++;
					mb[mx[i]-1][my[i]+1].mineN++;
					mb[mx[i]][my[i]+1].mineN++;
				}
			}
			else {
				if(my[i] == 0){
					mb[mx[i]-1][my[i]].mineN++;
					mb[mx[i]+1][my[i]].mineN++;
					mb[mx[i]-1][my[i]+1].mineN++;
					mb[mx[i]][my[i]+1].mineN++;
					mb[mx[i]+1][my[i]+1].mineN++;
				}
				else if(my[i] == max_y-1){
					mb[mx[i]-1][my[i]-1].mineN++;
					mb[mx[i]][my[i]-1].mineN++;
					mb[mx[i]+1][my[i]-1].mineN++;
					mb[mx[i]-1][my[i]].mineN++;
					mb[mx[i]+1][my[i]].mineN++;
				}
				else {
					mb[mx[i]-1][my[i]-1].mineN++;
					mb[mx[i]][my[i]-1].mineN++;
					mb[mx[i]+1][my[i]-1].mineN++;
					mb[mx[i]-1][my[i]].mineN++;
					mb[mx[i]+1][my[i]].mineN++;
					mb[mx[i]-1][my[i]+1].mineN++;
					mb[mx[i]][my[i]+1].mineN++;
					mb[mx[i]+1][my[i]+1].mineN++;
				}
			}
		}
		
		for(int i=0; i<max_x; i++){
			for(int j=0; j<max_y; j++){
				if(mb[i][j].mine == false){
					if(mb[i][j].mineN == 0) mb[i][j].jb.addMouseListener(
								mb[i][j].listener = new ListenerB0(this, mb[i][j]));
					else mb[i][j].jb.addMouseListener(mb[i][j].listener = new ListenerBN(this, mb[i][j]));
				}
				else mb[i][j].jb.addMouseListener(mb[i][j].listener = new ListenerMine(this, mb[i][j]));
			}
		}
	}
	
	public Game(MainFrame win) {
		this.win = win;
		win.backM = new BackButton(win, 2);
		win.backM.setBounds(0, 10, 150, 40);
		win.add(win.backM);
		
		mb = new MyButton[Game.max_x][Game.max_y];		
		mx = new int[totalmineN];
		my = new int[totalmineN];	

		setLayout(null);
		setBackground(new Color(230, 255, 255)); //panel 색 조정
		setBounds(0, 0, MyButton.SIZE * max_x+50, MyButton.SIZE * max_y+130);
		
		slidePanel = new JPanel();
		slide = new JLabel();
		add(slidePanel);
		add(slide);
	}
	
	public void run() {
		mineImg = new JLabel(MyImg.mineImg);
		mineImg.setBounds(MyButton.SIZE * max_x-50, MyButton.SIZE * max_y+68, 50, 50);

		//win.revalidate();
		//win.repaint();

		mineNP = new JImagePanel(MyImg.numJPbgi);
		mineNP.setBounds(MyButton.SIZE * max_x, MyButton.SIZE * max_y+78, 50, 32);
		
		mineCount = Game.totalmineN;
		mineNL = new JLabel(Integer.toString(mineCount));
		mineNL.setBounds(0, 0, 50, 50);
		mineNL.setFont(new Font("배경", Font.ITALIC, 20));
		mineNP.add(mineNL);
		
		add(mineNP);
		add(mineImg);

		//win.revalidate();
		//win.repaint();
		
		for (int j = 0; j< max_y; j++){
			for (int i = 0; i< max_x; i++){
			mb[i][j] = new MyButton(i, j);
			mb[i][j].jb = new JButton(MyImg.bfirst);
			mb[i][j].jb.setBounds(MyButton.SIZE*i+50 -1, MyButton.SIZE*j+50, MyButton.SIZE + 1, MyButton.SIZE);
			add(mb[i][j].jb);
			win.revalidate();
			win.repaint();
			}
		}
		
		makemine(mb, mx, my);
		win.backM.addListener();
		win.cu.ts.start();
	}
}