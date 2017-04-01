import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Random;


class MyImg {
	static File path = new File("");
	
	static ImageIcon bfirst = new ImageIcon(path.getAbsolutePath() + "/rss/bimg_first.gif");
	static ImageIcon bnumImg[] = {new ImageIcon(path.getAbsolutePath() + "/rss/bimg_0.gif"),
			new ImageIcon(path.getAbsolutePath() + "/rss/bimg_1.gif"), 
			 new ImageIcon(path.getAbsolutePath() + "/rss/bimg_2.gif"),
			 new ImageIcon(path.getAbsolutePath() + "/rss/bimg_3.gif"),
			 new ImageIcon(path.getAbsolutePath() + "/rss/bimg_4.gif"),
			 new ImageIcon(path.getAbsolutePath() + "/rss/bimg_5.gif"),
			 new ImageIcon(path.getAbsolutePath() + "/rss/bimg_6.gif"),
			 new ImageIcon(path.getAbsolutePath() + "/rss/bimg_7.gif"),
			 new ImageIcon(path.getAbsolutePath() + "/rss/bimg_8.gif")};
	static ImageIcon bmineImg = new ImageIcon(path.getAbsolutePath() + "/rss/bimg_mine.gif");
	static ImageIcon bendImg = new ImageIcon(path.getAbsolutePath() + "/rss/bimg_end.gif");
	static ImageIcon bingoImg = new ImageIcon(path.getAbsolutePath() + "/rss/bimg_bingo.gif");
	static ImageIcon bqImg = new ImageIcon(path.getAbsolutePath() + "/rss/bimg_q.gif");
	static ImageIcon clear = new ImageIcon(path.getAbsolutePath() + "/rss/clear.gif");
	static ImageIcon fail = new ImageIcon(path.getAbsolutePath() + "/rss/fail.gif");
	static ImageIcon watchImg = new ImageIcon(path.getAbsolutePath() + "/rss/watch.gif");
	static ImageIcon mineImg = new ImageIcon(path.getAbsolutePath() + "/rss/mine.gif");
	static ImageIcon numJPbgi = new ImageIcon((path.getAbsolutePath() + "/rss/numJPbgi.gif"));
}

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
	
	public MyButton(int x, int y){
		this.x = x;
		this.y = y;
	}
}
class JImagePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private ImageIcon img;
	public JImagePanel(ImageIcon img) {
		this.img = img;
	}
	public void paintComponent(Graphics g){
		g.drawImage(img.getImage(), 0, 0, null);
	}
}

class EndGameAct extends Thread implements Runnable{
	private Main win;
	boolean check;
	
	public EndGameAct(Main win, boolean check){
		this.win = win;
		this.check = check;
	}
	public void run(){
		win.revalidate();
		win.repaint();
		try{Thread.sleep(3000);}
		catch(InterruptedException err){} 
		
		win.getContentPane().removeAll();
		win.setSize(615, 435);
		win.frameSize = win.getSize();
		win.setLocation((win.screenSize.width-win.frameSize.width)/2, (win.screenSize.height-win.frameSize.height)/2);
		
		JButton endB;
		if(check == true) endB = new JButton(MyImg.clear);
		else endB = new JButton(MyImg.fail);
		endB.setContentAreaFilled(false); //버튼 내부공간 지우기
		endB.setBorderPainted(false); //버튼 테두리 지우기
		endB.setBounds(100, 100, 400, 200);
		endB.addActionListener(new ResetListener(win));
		win.ct.add(endB);
		win.revalidate();
		win.repaint();
	}
}
class ResetListener implements ActionListener{
	private Main win;
	
	public ResetListener(Main win){
		this.win = win;
	}
	public void actionPerformed(ActionEvent e) {
		win.getContentPane().removeAll();
		win.menu = new Menu(win);
		win.getContentPane().add(win.menu);
		win.revalidate();
		win.repaint();
	}
}

class Menu extends JPanel  {
	private static final long serialVersionUID = 1L;

	private Main win;

	File path = new File("");
	JLabel titleL;
	JButton startB;
	JButton lvl1, lvl2, lvl3;
	//JButton lvlfree;
	
	public Menu(Main win) {
		setLayout(null);
		this.win = win;
		setBackground(new Color(153, 255, 153)); //panel 색 조정
		setBounds(50, 0, 500, 400); //위치, 크기 조정 이를 위해 Container와 JPanel 모두 layout을 null로 지정해줘야 한다.
		
		startB = new JButton(new ImageIcon(path.getAbsolutePath() + "/rss/start.gif"));
		titleL = new JLabel( new ImageIcon(path.getAbsolutePath() + "/rss/Title.gif"));
		
		startB.setContentAreaFilled(false); //버튼 내부공간 지우기
		startB.setBorderPainted(false); //버튼 테두리 지우기
		
		startB.setBounds(0, 250, 500, 50);
		titleL.setBounds(0, 80, 500, 150);
		
		startB.addActionListener(new StartBListener());
		
		add(startB);
		add(titleL);
	}
	class StartBListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			removeAll();
			lvl1 = new JButton(new ImageIcon(path.getAbsolutePath() + "/rss/Blvl.gif"));
			lvl2 = new JButton(new ImageIcon(path.getAbsolutePath() + "/rss/Ilvl.gif"));
			lvl3 = new JButton(new ImageIcon(path.getAbsolutePath() + "/rss/Vlvl.gif"));
			//lvlfree = new JButton("User defined level");
			
			lvl1.setContentAreaFilled(false);
			lvl1.setBorderPainted(false);
			lvl2.setContentAreaFilled(false);
			lvl2.setBorderPainted(false);
			lvl3.setContentAreaFilled(false);
			lvl3.setBorderPainted(false);
			
			lvl1.setBounds(100, 50, 350, 80);
			lvl2.setBounds(100, 170, 350, 80);
			lvl3.setBounds(100, 290, 350, 80);
			//lvlfree.setBounds(0, 210, 550, 50);
			
			lvl1.addActionListener(new LvlBListener(10, 10, 10));
			lvl2.addActionListener(new LvlBListener(30, 20, 50));
			lvl3.addActionListener(new LvlBListener(40, 20, 99));
			//lvlfree.addActionListener(new LvlBListener());
			
			add(lvl1);
			add(lvl2);
			add(lvl3);
			//add(lvlfree);
			
			win.revalidate();
			win.repaint();
		}
	}
	
	class LvlBListener implements ActionListener {
		int max_x, max_y, totalmineN;
		
		public LvlBListener(int max_x, int max_y, int totalmineN) {
			this.max_x = max_x;
			this.max_y = max_y;
			this.totalmineN = totalmineN;
		}
		
		//public LvlBListener() {}  lvlfree
		
		public void actionPerformed(ActionEvent e) {
			win.startGame(max_x, max_y, totalmineN);
		}
	}
	
}

class ListenerBN implements MouseListener {
	Game gm;
	MyButton mb;
	
	public ListenerBN (Game gm, MyButton mb){
		this.gm = gm;
		this.mb = mb;
	}
	public void mousePressed(MouseEvent e){
		 if(e.getButton() == MouseEvent.BUTTON3){ // 오른쪽 마우스 클릭
			 	if(gm.win.preventionActBug == true){
			 		gm.win.preventionActBug = false;
			 		gm.remove(mb.jb);
			 		if(mb.form == 0){
			 			mb.jb = new JButton(MyImg.bingoImg);
			 			mb.form++;
			 			mb.jb.setBounds(mb.x*MyButton.SIZE, mb.y*MyButton.SIZE, MyButton.SIZE, MyButton.SIZE);
			 			mb.jb.addMouseListener(new ListenerBN(gm, mb));
			 			
			 			gm.mineNP.removeAll();
			 			gm.mineCount--;
			 			gm.mineNL = new JLabel(Integer.toString(gm.mineCount));
			 			gm.mineNL.setBounds(0, 0, 50, 50);
			 			gm.mineNL.setFont(new Font("배경", Font.ITALIC, 20));
			 			gm.mineNP.add(gm.mineNL);
			 		}
			 		else if(mb.form == 1){
			 			mb.jb = new JButton(MyImg.bqImg);
			 			mb.form++;
			 			mb.jb.setBounds(mb.x*MyButton.SIZE, mb.y*MyButton.SIZE, MyButton.SIZE, MyButton.SIZE);
			 			mb.jb.addMouseListener(new ListenerBN(gm, mb));
			 			
			 			gm.mineNP.removeAll();
			 			gm.mineCount++;
			 			gm.mineNL = new JLabel(Integer.toString(gm.mineCount));
			 			gm.mineNL.setBounds(0, 0, 50, 50);
			 			gm.mineNL.setFont(new Font("배경", Font.ITALIC, 20));
			 			gm.mineNP.add(gm.mineNL);
			 		}
			 		else {
			 			mb.jb = new JButton();
			 			mb.form = 0;
			 			mb.jb.setBounds(mb.x*MyButton.SIZE, mb.y*MyButton.SIZE, MyButton.SIZE, MyButton.SIZE);
			 			mb.jb.addMouseListener(new ListenerBN(gm, mb));
			 		}
			 		gm.add(mb.jb);
			 	}
		  }
		  else{       // 왼쪽 마우스 클릭	 
			  if(mb.form != 1){
				  gm.remove(mb.jb);
				  mb.jl = new JLabel(MyImg.bnumImg[mb.mineN]);
				  mb.jl.setBounds(mb.x*MyButton.SIZE, mb.y*MyButton.SIZE, MyButton.SIZE, MyButton.SIZE);
				
				  gm.add(mb.jl);
				  mb.checkpush = true;
			  
				  if(gm.minecheckN == Game.totalmineN){
					  boolean check = true;
					  for(int i=0; i<Game.max_x; i++){
						  for(int j=0; j<Game.max_y; j++){
							  if(gm.mb[i][j].mine == false 
									  && gm.mb[i][j].checkpush == false) {
								  check = false; break;
							  }
						  }
					  }
					  if(check == true){
						  gm.win.cu.ts.terminate();
						  gm.win.ega = new EndGameAct(gm.win, true);
						  gm.win.ega.start();
					  }
				  }
			  }
		  }
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
}
class ListenerB0 implements MouseListener {
	Game gm;
	MyButton mb;
	
	public ListenerB0 (Game gm, MyButton mb){
		this.gm = gm;
		this.mb = mb;
	}
	public void mousePressed(MouseEvent e){
		 if((e.getButton() == MouseEvent.BUTTON3)){    // 오른쪽 마우스 클릭
			 	if(gm.win.preventionActBug == true) {
			 		gm.win.preventionActBug = false;
				 	gm.remove(mb.jb);
			  		if(mb.form == 0){
			  			mb.jb = new JButton(MyImg.bingoImg);
			  			mb.form++;
			  			mb.jb.setBounds(mb.x*MyButton.SIZE, mb.y*MyButton.SIZE, MyButton.SIZE, MyButton.SIZE);
			  			mb.jb.addMouseListener(new ListenerB0(gm, mb));
			  			
			  			gm.mineNP.removeAll();
			 			gm.mineCount--;
			 			gm.mineNL = new JLabel(Integer.toString(gm.mineCount));
			 			gm.mineNL.setBounds(0, 0, 50, 50);
			 			gm.mineNL.setFont(new Font("배경", Font.ITALIC, 20));
			 			gm.mineNP.add(gm.mineNL);
			  		}
			  		else if(mb.form == 1){
			  			mb.jb = new JButton(MyImg.bqImg);
			  			mb.form++;
			  			mb.jb.setBounds(mb.x*MyButton.SIZE, mb.y*MyButton.SIZE, MyButton.SIZE, MyButton.SIZE);
			  			mb.jb.addMouseListener(new ListenerB0(gm, mb));
			  			
			  			gm.mineNP.removeAll();
			 			gm.mineCount++;
			 			gm.mineNL = new JLabel(Integer.toString(gm.mineCount));
			 			gm.mineNL.setBounds(0, 0, 50, 50);
			 			gm.mineNL.setFont(new Font("배경", Font.ITALIC, 20));
			 			gm.mineNP.add(gm.mineNL);
			  		}
			  		else {
			  			mb.jb = new JButton();
			  			mb.form = 0;
			  			mb.jb.setBounds(mb.x*MyButton.SIZE, mb.y*MyButton.SIZE, MyButton.SIZE, MyButton.SIZE);
			  			mb.jb.addMouseListener(new ListenerB0(gm, mb));
			  		}
			  		gm.add(mb.jb);
			 	}
		  }
		  else{       // 왼쪽 마우스 클릭	
			  if(mb.form != 1){
				  if(gm.minecheckN == Game.totalmineN){
					  boolean check = true;
					  mb.checkpush = true;
					  for(int i=0; i<Game.max_x; i++){
						  for(int j=0; j<Game.max_y; j++){
							  if(gm.mb[i][j].mine == false 
								  && gm.mb[i][j].checkpush == false) {
								  check = false; break;
							  }
						  }
					  }
					  if(check == true){
						  gm.win.cu.ts.terminate();
						  gm.win.ega = new EndGameAct(gm.win, true);
						  gm.win.ega.start();
					  }
				  }
				  else{
					  gm.remove(mb.jb);
					  mb.checkpush = false;
					  ActionofLB0 ci = new ActionofLB0(mb);
					  ci.run();
				  }
			  }
		  }
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	
	class ActionofLB0 extends Thread implements Runnable{
		MyButton mb;
		ActionofLB0 ci;
		public ActionofLB0(MyButton mb){
			this.mb = mb;
		}
			
		public void run(){	
			if(mb.checkpush == false){
				gm.remove(mb.jb);
				mb.jl = new JLabel(MyImg.bnumImg[mb.mineN]);
				mb.jl.setBounds(mb.x*MyButton.SIZE, mb.y*MyButton.SIZE, MyButton.SIZE, MyButton.SIZE);
				mb.checkpush = true;
				gm.add(mb.jl);

				if(mb.mineN == 0){
					if(mb.x == 0){
						if(mb.y == 0){
							ci = new ActionofLB0(gm.mb[mb.x+1][mb.y]); ci.run();
							ci = new ActionofLB0(gm.mb[mb.x][mb.y+1]); ci.run();
							ci = new ActionofLB0(gm.mb[mb.x+1][mb.y+1]); ci.run();
						}
						else if(mb.y == Game.max_y-1){
							ci = new ActionofLB0(gm.mb[mb.x][mb.y-1]); ci.run();
							ci = new ActionofLB0(gm.mb[mb.x+1][mb.y-1]); ci.run();
							ci = new ActionofLB0(gm.mb[mb.x+1][mb.y]); ci.run();
						}
						else{
							ci = new ActionofLB0(gm.mb[mb.x][mb.y-1]); ci.run();
							ci = new ActionofLB0(gm.mb[mb.x+1][mb.y-1]); ci.run();
							ci = new ActionofLB0(gm.mb[mb.x+1][mb.y]); ci.run();
							ci = new ActionofLB0(gm.mb[mb.x][mb.y+1]); ci.run();
							ci = new ActionofLB0(gm.mb[mb.x+1][mb.y+1]); ci.run();
						}
					}
					else if(mb.x == Game.max_x-1){
						if(mb.y == 0){
							ci = new ActionofLB0(gm.mb[mb.x-1][mb.y]); ci.run();
							ci = new ActionofLB0(gm.mb[mb.x-1][mb.y+1]); ci.run();
							ci = new ActionofLB0(gm.mb[mb.x][mb.y+1]); ci.run();
						}
						else if(mb.y == Game.max_y-1){
							ci = new ActionofLB0(gm.mb[mb.x-1][mb.y-1]); ci.run();
							ci = new ActionofLB0(gm.mb[mb.x][mb.y-1]); ci.run();
							ci = new ActionofLB0(gm.mb[mb.x-1][mb.y]); ci.run();
						}
						else{
							ci = new ActionofLB0(gm.mb[mb.x-1][mb.y-1]); ci.run();
							ci = new ActionofLB0(gm.mb[mb.x][mb.y-1]); ci.run();
							ci = new ActionofLB0(gm.mb[mb.x-1][mb.y]); ci.run();
							ci = new ActionofLB0(gm.mb[mb.x-1][mb.y+1]); ci.run();
							ci = new ActionofLB0(gm.mb[mb.x][mb.y+1]); ci.run();
						}
					}
					else{
						if(mb.y == 0){
							ci = new ActionofLB0(gm.mb[mb.x-1][mb.y]); ci.run();
							ci = new ActionofLB0(gm.mb[mb.x+1][mb.y]); ci.run();
							ci = new ActionofLB0(gm.mb[mb.x-1][mb.y+1]); ci.run();
							ci = new ActionofLB0(gm.mb[mb.x][mb.y+1]); ci.run();
							ci = new ActionofLB0(gm.mb[mb.x+1][mb.y+1]); ci.run();
						}
						else if(mb.y == Game.max_y-1){
							ci = new ActionofLB0(gm.mb[mb.x-1][mb.y-1]); ci.run();
							ci = new ActionofLB0(gm.mb[mb.x][mb.y-1]); ci.run();
							ci = new ActionofLB0(gm.mb[mb.x+1][mb.y-1]); ci.run();
							ci = new ActionofLB0(gm.mb[mb.x-1][mb.y]); ci.run();
							ci = new ActionofLB0(gm.mb[mb.x+1][mb.y]); ci.run();
						}
						else{
							ci = new ActionofLB0(gm.mb[mb.x-1][mb.y-1]); ci.run();
							ci = new ActionofLB0(gm.mb[mb.x][mb.y-1]); ci.run();
							ci = new ActionofLB0(gm.mb[mb.x+1][mb.y-1]); ci.run();
							ci = new ActionofLB0(gm.mb[mb.x-1][mb.y]); ci.run();
							ci = new ActionofLB0(gm.mb[mb.x+1][mb.y]); ci.run();
							ci = new ActionofLB0(gm.mb[mb.x-1][mb.y+1]); ci.run();
							ci = new ActionofLB0(gm.mb[mb.x][mb.y+1]); ci.run();
							ci = new ActionofLB0(gm.mb[mb.x+1][mb.y+1]); ci.run();
						}
					}
				}
			}
		}
	}
}
class ListenerMine implements MouseListener {
	Game gm;
	MyButton mb;
	
	public ListenerMine (Game gm, MyButton mb){
		this.gm = gm;
		this.mb = mb;
	}
	
	public void mousePressed(MouseEvent e) { 
		if(e.getButton() == MouseEvent.BUTTON3){    // 오른쪽 마우스 클릭
			if(gm.win.preventionActBug == true){
				gm.remove(mb.jb);
				gm.win.preventionActBug = false;
			  	if(mb.form == 0){
			  		mb.jb = new JButton(MyImg.bingoImg);
			  		mb.form++;
			  		mb.jb.setBounds(mb.x*MyButton.SIZE, mb.y*MyButton.SIZE, MyButton.SIZE, MyButton.SIZE);
			  		mb.jb.addMouseListener(new ListenerMine(gm, mb));
			  		gm.minecheckN++;
			  		
			  		gm.mineNP.removeAll();
		 			gm.mineCount--;
		 			gm.mineNL = new JLabel(Integer.toString(gm.mineCount));
		 			gm.mineNL.setBounds(0, 0, 50, 50);
		 			gm.mineNL.setFont(new Font("배경", Font.ITALIC, 20));
		 			gm.mineNP.add(gm.mineNL);
			  		
			  		if(gm.minecheckN == Game.totalmineN){
			  			boolean check = true;
			  			for(int i=0; i<Game.max_x; i++){
			  				for(int j=0; j<Game.max_y; j++){
			  					if(gm.mb[i][j].mine == false 
			  							&& gm.mb[i][j].checkpush == false) {
			  						check = false; break;
			  					}
			  				}
			  			}
			  			if(check == true){
							  gm.win.cu.ts.terminate();
							  gm.win.ega = new EndGameAct(gm.win, true);
							  gm.win.ega.start();
						  }
			  		}
			  	}
			  	else if(mb.form == 1){
			  		mb.jb = new JButton(MyImg.bqImg);
			  		mb.form++;
			  		mb.jb.setBounds(mb.x*MyButton.SIZE, mb.y*MyButton.SIZE, MyButton.SIZE, MyButton.SIZE);
			  		mb.jb.addMouseListener(new ListenerMine(gm, mb));
			  		gm.minecheckN--;
			  		
			  		gm.mineNP.removeAll();
		 			gm.mineCount++;
		 			gm.mineNL = new JLabel(Integer.toString(gm.mineCount));
		 			gm.mineNL.setBounds(0, 0, 50, 50);
		 			gm.mineNL.setFont(new Font("배경", Font.ITALIC, 20));
		 			gm.mineNP.add(gm.mineNL);
			  	}
			  	else {
			  		mb.jb = new JButton();
			  		mb.form = 0;
			  		mb.jb.setBounds(mb.x*MyButton.SIZE, mb.y*MyButton.SIZE, MyButton.SIZE, MyButton.SIZE);
			  		mb.jb.addMouseListener(new ListenerMine(gm, mb));
			  	}
			  	gm.add(mb.jb);
			}
		 }
		 else{
			 if(mb.form != 1){
			 	gm.remove(mb.jb);	
			 	for(int i=0; i<Game.totalmineN; i++){
					gm.remove(gm.mb[gm.mx[i]][gm.my[i]].jb);
					gm.mb[gm.mx[i]][gm.my[i]].jl = new JLabel(MyImg.bmineImg);
					gm.mb[gm.mx[i]][gm.my[i]].jl.setBounds(
								gm.mb[gm.mx[i]][gm.my[i]].x*MyButton.SIZE,
								gm.mb[gm.mx[i]][gm.my[i]].y*MyButton.SIZE, 
								MyButton.SIZE, MyButton.SIZE);
					gm.add(gm.mb[gm.mx[i]][gm.my[i]].jl);
				}
			 	gm.remove(mb.jl);
				mb.jl = new JLabel(MyImg.bendImg);
				mb.jl.setBounds(mb.x*MyButton.SIZE, mb.y*MyButton.SIZE, MyButton.SIZE, MyButton.SIZE);
				gm.add(mb.jl);
				
				gm.win.cu.ts.terminate();
				 
				gm.win.ega = new EndGameAct(gm.win, false);
				gm.win.ega.start();
			 }
		 }
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseClicked(MouseEvent e){}
}
class Game extends JPanel {
	private static final long serialVersionUID = 1L;
	
	Main win;	
	MyButton mb[][];
	JImagePanel mineNP;
	JLabel mineImg, mineNL;
	
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
											new ListenerB0(this, mb[i][j]));
					else mb[i][j].jb.addMouseListener(new ListenerBN(this, mb[i][j]));
				}
				else mb[i][j].jb.addMouseListener(new ListenerMine(this, mb[i][j]));
			}
		}
	}
	
	public Game(Main win) {
		setLayout(null);
		this.win = win;
		setBackground(new Color(153, 255, 153)); //panel 색 조정
		setBounds(50, 50, MyButton.SIZE * max_x, MyButton.SIZE * max_y+80); //위치, 크기 조정 이를 위해 Container와 JPanel 모두 layout을 null로 지정해줘야 한다.
		
		mb = new MyButton[Game.max_x][Game.max_y];
		mx = new int[totalmineN];
		my = new int[totalmineN];
		
		for (int i = 0; i< max_x; i++){
			for (int j = 0; j< max_y; j++){
			mb[i][j] = new MyButton(i, j);
			mb[i][j].jb = new JButton(MyImg.bfirst);
			mb[i][j].jb.setBounds(MyButton.SIZE*i, MyButton.SIZE*j, MyButton.SIZE, MyButton.SIZE);
			add(mb[i][j].jb);
			}
		}
		
		mineImg = new JLabel(MyImg.mineImg);
		mineImg.setBounds(MyButton.SIZE * max_x-100, MyButton.SIZE * max_y+18, 50, 50);
		
		mineNP = new JImagePanel(MyImg.numJPbgi);
		mineNP.setBounds(MyButton.SIZE * max_x-50, MyButton.SIZE * max_y+28, 50, 32);
		
		mineCount = Game.totalmineN;
		mineNL = new JLabel(Integer.toString(mineCount));
		mineNL.setBounds(0, 0, 50, 50);
		mineNL.setFont(new Font("배경", Font.ITALIC, 20));
		mineNP.add(mineNL);
		
		add(mineNP);
		add(mineImg);
		
		makemine(mb, mx, my);
	}
}

class CountUp extends JPanel  {
	private static final long serialVersionUID = 1L;
	
	private Main win;
	JImagePanel timeP;
	Tstart ts;
	JLabel watchImg, cuN;
	
	public CountUp(Main win) {
		this.win = win;
		setLayout(null);
		setBounds(50, MyButton.SIZE*Game.max_y + 70, 120, 100);
		setBackground(new Color(153, 255, 153)); //panel 색 조정
		
		watchImg = new JLabel(MyImg.watchImg);
		watchImg.setBounds(0, 0, 50, 50);
		
		timeP = new JImagePanel(MyImg.numJPbgi);
		//timeP.setLayout(null);
		timeP.setBounds(50, 8, 50, 32);
		cuN = new JLabel("0");
		cuN.setBounds(0, 0, 50, 50);
		cuN.setFont(new Font("배경", Font.ITALIC, 20));
		timeP.add(cuN);
		
		add(timeP);
		add(watchImg);
		
		
		ts = new Tstart(this);
	}
	
	public class Tstart extends Thread implements Runnable {
		int time = 0;
		CountUp cu;
		
		private volatile  boolean running = true;
		
		public Tstart(CountUp cu){
			this.cu = cu;
		}
		
		public void terminate() {
		        running = false;
		}
		public void run(){
			while(running){
				try{Thread.sleep(1000);}
				catch(InterruptedException e){}
				time++;
					//cu.jl2 = new JLabel(ti.numImg[(time / 600)]);
					//cu.jl3 = new JLabel(ti.numImg[((time % 600) / 60)]);
					//cu.jl5 = new JLabel(ti.numImg[(((time % 600) % 60) / 10)]);
					//cu.jl6 = new JLabel(ti.numImg[(time % 10)]);	
				
				timeP.removeAll();
				cuN = new JLabel(Integer.toString(time));
				cuN.setFont(new Font("배경", Font.ITALIC, 20));
				timeP.add(cuN);
					
					
					
				win.preventionActBug = true;
				win.revalidate();
				win.repaint();
			}
		}
	}
}

class Main extends JFrame{
	private static final long serialVersionUID = 1L;
	Container ct;
	Menu menu;
	CountUp cu;
	Game gm;
	EndGameAct ega;
	Dimension frameSize;
	Dimension screenSize;
	
	boolean preventionActBug = true;
	
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //프래임 종료시 프로그램 종료, 생성자 초반부에 작성해준다.
		
		ct = getContentPane();
		//ct.setLayout(new FlowLayout(FlowLayout.CENTER, 500, 10));
		ct.setLayout(null);
		ct.setBackground(new Color(153, 255, 153));
		
		menu = new Menu(this);
		ct.add(menu);
		setTitle("Event Test3");
		setSize(615, 435); //in my screen it is 600,400;
		
		frameSize = getSize();
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width-frameSize.width)/2, (screenSize.height-frameSize.height)/2);
		
		setResizable(false);
		setVisible(true);
	}
	public void startGame(int max_x, int max_y, int totalmineN){
		getContentPane().removeAll();
		
		Game.max_x = max_x;
		Game.max_y = max_y;
		Game.totalmineN = totalmineN;
		
		setSize(Game.max_x*MyButton.SIZE + 115, Game.max_y*MyButton.SIZE + 185);
		frameSize = getSize();
		setLocation((screenSize.width-frameSize.width)/2, (screenSize.height-frameSize.height)/2);
		
		cu = new CountUp(this);
		gm = new Game(this);
		getContentPane().add(cu);
		getContentPane().add(gm);
	
		revalidate();
		repaint();
		cu.ts.start();
	}
}

public class Minehunter {
	public static void main(String[] args) {
		new Main();
	}
}
