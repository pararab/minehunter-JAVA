import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Random;


class TextImg {
	File path = new File("");
	
	ImageIcon numImg[] = {new ImageIcon(path.getAbsolutePath() + "/rss/text_0.gif"), 
			 new ImageIcon(path.getAbsolutePath() + "/rss/text_1.gif"),
			 new ImageIcon(path.getAbsolutePath() + "/rss/text_2.gif"),
			 new ImageIcon(path.getAbsolutePath() + "/rss/text_3.gif"),
			 new ImageIcon(path.getAbsolutePath() + "/rss/text_4.gif"),
			 new ImageIcon(path.getAbsolutePath() + "/rss/text_5.gif"),
			 new ImageIcon(path.getAbsolutePath() + "/rss/text_6.gif"),
			 new ImageIcon(path.getAbsolutePath() + "/rss/text_7.gif"),
			 new ImageIcon(path.getAbsolutePath() + "/rss/text_8.gif"),
			 new ImageIcon(path.getAbsolutePath() + "/rss/text_9.gif")};
	ImageIcon colonImg = new ImageIcon(path.getAbsolutePath() + "/rss/text_colon.gif");
	
}
class ButtonImg {
	File path = new File("");
	
	ImageIcon bfirst = new ImageIcon(path.getAbsolutePath() + "/rss/bimg_first.gif");
	ImageIcon bnumImg[] = {new ImageIcon(path.getAbsolutePath() + "/rss/bimg_0.gif"),
			 new ImageIcon(path.getAbsolutePath() + "/rss/bimg_1.gif"), 
			 new ImageIcon(path.getAbsolutePath() + "/rss/bimg_2.gif"),
			 new ImageIcon(path.getAbsolutePath() + "/rss/bimg_3.gif"),
			 new ImageIcon(path.getAbsolutePath() + "/rss/bimg_4.gif"),
			 new ImageIcon(path.getAbsolutePath() + "/rss/bimg_5.gif"),
			 new ImageIcon(path.getAbsolutePath() + "/rss/bimg_6.gif"),
			 new ImageIcon(path.getAbsolutePath() + "/rss/bimg_7.gif"),
			 new ImageIcon(path.getAbsolutePath() + "/rss/bimg_8.gif")};
	ImageIcon bmineImg = new ImageIcon(path.getAbsolutePath() + "/rss/bimg_mine.gif");
	ImageIcon bendImg = new ImageIcon(path.getAbsolutePath() + "/rss/bimg_end.gif");
	ImageIcon bingoImg = new ImageIcon(path.getAbsolutePath() + "/rss/bimg_bingo.gif");
	ImageIcon bqImg = new ImageIcon(path.getAbsolutePath() + "/rss/bimg_q.gif");
	ImageIcon clear = new ImageIcon(path.getAbsolutePath() + "/rss/clear.gif");
	ImageIcon fail = new ImageIcon(path.getAbsolutePath() + "/rss/fail.gif");
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
		JButton endB;
		if(check == true) endB = new JButton(win.bimg.clear);
		else endB = new JButton(win.bimg.fail);
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
	private Main win;

	File path = new File("");
	JLabel titleL;
	JButton startB;
	
	public Menu(Main win) {
		setLayout(null);
		this.win = win;
		//setPreferredSize(new Dimension(200, 300)); //panel 크기 조정
		setBackground(new Color(153, 255, 153)); //panel 색 조정
		setBounds(50, 70, 500, 300); //위치, 크기 조정 이를 위해 Container와 JPanel 모두 layout을 null로 지정해줘야 한다.
		
		startB = new JButton(new ImageIcon(path.getAbsolutePath() + "/rss/start.gif"));
		titleL = new JLabel( new ImageIcon(path.getAbsolutePath() + "/rss/Title.gif"));
		
		startB.setContentAreaFilled(false); //버튼 내부공간 지우기
		startB.setBorderPainted(false); //버튼 테두리 지우기
		
		startB.setBounds(0, 180, 500, 50);
		titleL.setBounds(0, 10, 500, 150);
		
		startB.addActionListener(new StartBListener());
		//jb2.addActionListener(new EventClass(jl)); //이벤트 처리
		
		add(startB);
		add(titleL);
	}
	class StartBListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			win.startGame();
		}
	}
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
class ListenerBN implements MouseListener {
	Game gm;
	MyButton mb;
	ButtonImg bimg;
	
	public ListenerBN (Game gm, MyButton mb){
		this.gm = gm;
		this.mb = mb;
		this.bimg = gm.bimg;
	}
	public void mousePressed(MouseEvent e){
		 if(e.getButton() == MouseEvent.BUTTON3){ // 오른쪽 마우스 클릭
			 	if(gm.win.preventionActBug == true){
			 		gm.win.preventionActBug = false;
			 		gm.remove(mb.jb);
			 		if(mb.form == 0){
			 			mb.jb = new JButton(bimg.bingoImg);
			 			mb.form++;
			 			mb.jb.setBounds(mb.x*MyButton.SIZE, mb.y*MyButton.SIZE, MyButton.SIZE, MyButton.SIZE);
			 			mb.jb.addMouseListener(new ListenerBN(gm, mb));
			 		}
			 		else if(mb.form == 1){
			 			mb.jb = new JButton(bimg.bqImg);
			 			mb.form++;
			 			mb.jb.setBounds(mb.x*MyButton.SIZE, mb.y*MyButton.SIZE, MyButton.SIZE, MyButton.SIZE);
			 			mb.jb.addMouseListener(new ListenerBN(gm, mb));
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
			  gm.remove(mb.jb);
			  mb.jl = new JLabel(bimg.bnumImg[mb.mineN]);
			  mb.jl.setBounds(mb.x*MyButton.SIZE, mb.y*MyButton.SIZE, MyButton.SIZE, MyButton.SIZE);
				
			  gm.add(mb.jl);
			  mb.checkpush = true;
			  
			  if(gm.minecheckN == gm.ttlmN){
				  boolean check = true;
				  for(int i=0; i<Game.MAX_X; i++){
					  for(int j=0; j<Game.MAX_Y; j++){
						  if(gm.mb[i][j].mine == false 
							&& gm.mb[i][j].checkpush == false) {
							  check = false; break;
						  }
					  }
				  }
				  if(check == true){
					  gm.win.cd.ts.terminate();
					  gm.win.ega = new EndGameAct(gm.win, true);
					  gm.win.ega.start();
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
	ButtonImg bimg;
	
	public ListenerB0 (Game gm, MyButton mb){
		this.gm = gm;
		this.mb = mb;
		this.bimg = gm.bimg;
	}
	public void mousePressed(MouseEvent e){
		 if((e.getButton() == MouseEvent.BUTTON3)){    // 오른쪽 마우스 클릭
			 	if(gm.win.preventionActBug == true) {
			 		gm.win.preventionActBug = false;
				 	gm.remove(mb.jb);
			  		if(mb.form == 0){
			  			mb.jb = new JButton(bimg.bingoImg);
			  			mb.form++;
			  			mb.jb.setBounds(mb.x*MyButton.SIZE, mb.y*MyButton.SIZE, MyButton.SIZE, MyButton.SIZE);
			  			mb.jb.addMouseListener(new ListenerB0(gm, mb));
			  		}
			  		else if(mb.form == 1){
			  			mb.jb = new JButton(bimg.bqImg);
			  			mb.form++;
			  			mb.jb.setBounds(mb.x*MyButton.SIZE, mb.y*MyButton.SIZE, MyButton.SIZE, MyButton.SIZE);
			  			mb.jb.addMouseListener(new ListenerB0(gm, mb));
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
			  if(gm.minecheckN == gm.ttlmN){
				  boolean check = true;
				  mb.checkpush = true;
				  for(int i=0; i<Game.MAX_X; i++){
					  for(int j=0; j<Game.MAX_Y; j++){
						  if(gm.mb[i][j].mine == false 
							&& gm.mb[i][j].checkpush == false) {
							  check = false; break;
						  }
					  }
				  }
				  if(check == true){
					  gm.win.cd.ts.terminate();
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
				mb.jl = new JLabel(bimg.bnumImg[mb.mineN]);
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
						else if(mb.y == 9){
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
					else if(mb.x == 19){
						if(mb.y == 0){
							ci = new ActionofLB0(gm.mb[mb.x-1][mb.y]); ci.run();
							ci = new ActionofLB0(gm.mb[mb.x-1][mb.y+1]); ci.run();
							ci = new ActionofLB0(gm.mb[mb.x][mb.y+1]); ci.run();
						}
						else if(mb.y == 9){
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
						else if(mb.y == 9){
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
	ButtonImg bimg;
	
	public ListenerMine (Game gm, MyButton mb){
		this.gm = gm;
		this.mb = mb;
		this.bimg = gm.bimg;
	}
	
	public void mousePressed(MouseEvent e) { 
		if(e.getButton() == MouseEvent.BUTTON3){    // 오른쪽 마우스 클릭
			if(gm.win.preventionActBug == true){
				gm.remove(mb.jb);
				gm.win.preventionActBug = false;
			  	if(mb.form == 0){
			  		mb.jb = new JButton(bimg.bingoImg);
			  		mb.form++;
			  		mb.jb.setBounds(mb.x*MyButton.SIZE, mb.y*MyButton.SIZE, MyButton.SIZE, MyButton.SIZE);
			  		mb.jb.addMouseListener(new ListenerMine(gm, mb));
			  		gm.minecheckN++;
			  		
			  		if(gm.minecheckN == gm.ttlmN){
			  			boolean check = true;
			  			for(int i=0; i<Game.MAX_X; i++){
			  				for(int j=0; j<Game.MAX_Y; j++){
			  					if(gm.mb[i][j].mine == false 
			  							&& gm.mb[i][j].checkpush == false) {
			  						check = false; break;
			  					}
			  				}
			  			}
			  			if(check == true){
							  gm.win.cd.ts.terminate();
							  gm.win.ega = new EndGameAct(gm.win, true);
							  gm.win.ega.start();
						  }
			  		}
			  	}
			  	else if(mb.form == 1){
			  		mb.jb = new JButton(bimg.bqImg);
			  		mb.form++;
			  		mb.jb.setBounds(mb.x*MyButton.SIZE, mb.y*MyButton.SIZE, MyButton.SIZE, MyButton.SIZE);
			  		mb.jb.addMouseListener(new ListenerMine(gm, mb));
			  		gm.minecheckN--;
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
			 	gm.remove(mb.jb);	
			 	for(int i=0; i<gm.ttlmN; i++){
					gm.remove(gm.mb[gm.mx[i]][gm.my[i]].jb);
					gm.mb[gm.mx[i]][gm.my[i]].jl = new JLabel(bimg.bmineImg);
					gm.mb[gm.mx[i]][gm.my[i]].jl.setBounds(
								gm.mb[gm.mx[i]][gm.my[i]].x*MyButton.SIZE,
								gm.mb[gm.mx[i]][gm.my[i]].y*MyButton.SIZE, 
								MyButton.SIZE, MyButton.SIZE);
					gm.add(gm.mb[gm.mx[i]][gm.my[i]].jl);
				}
			 	gm.remove(mb.jl);
				mb.jl = new JLabel(bimg.bendImg);
				mb.jl.setBounds(mb.x*MyButton.SIZE, mb.y*MyButton.SIZE, MyButton.SIZE, MyButton.SIZE);
				gm.add(mb.jl);
				
				gm.win.cd.ts.terminate();
				 
				gm.win.ega = new EndGameAct(gm.win, false);
				gm.win.ega.start();
		 }
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseClicked(MouseEvent e){}
}
class Game extends JPanel {
	public static final int MAX_X = 20;
	public static final int MAX_Y = 10;
	
	Main win;	
	MyButton mb[][];
	ButtonImg bimg;
	
	int[] mx;
	int[] my;
	int minecheckN = 0;
	int ttlmN = 15;
	
	void makemine(MyButton mb[][], int mx[], int my[]){
		Random random = new Random();
	
		mx[0] = random.nextInt(MAX_X);
		my[0] = random.nextInt(MAX_Y);
		
		for(int i=1; i<ttlmN; i++){
			boolean pass = true;
			do
			{
				mx[i] = random.nextInt(MAX_X);
				my[i] = random.nextInt(MAX_Y);
				
				pass = true;
				for(int j = 0; j<i; j++){
					if(mx[i] == mx[j] && my[i] == my[j])
						pass = false;
				}
			} while(!pass);
		}
		
		for(int i=0; i<ttlmN; i++){
			mb[mx[i]][my[i]].mine = true;
			if(mx[i] == 0){
				if(my[i] == 0){
					mb[mx[i]+1][my[i]].mineN++;
					mb[mx[i]][my[i]+1].mineN++;
					mb[mx[i]+1][my[i]+1].mineN++;
				}
				else if(my[i] == MAX_Y-1){
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
			else if(mx[i] == MAX_X-1){
				if(my[i] == 0){
					mb[mx[i]-1][my[i]].mineN++;
					mb[mx[i]][my[i]+1].mineN++;
					mb[mx[i]-1][my[i]+1].mineN++;
				}
				else if(my[i] == MAX_Y-1){
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
				else if(my[i] == MAX_Y-1){
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
		
		for(int i=0; i<MAX_X; i++){
			for(int j=0; j<MAX_Y; j++){
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
		//setBackground(new Color(153, 255, 153)); //panel 색 조정
		setBounds(50, 50, MyButton.SIZE * MAX_X, MyButton.SIZE * MAX_Y); //위치, 크기 조정 이를 위해 Container와 JPanel 모두 layout을 null로 지정해줘야 한다.
		
		mb = new MyButton[20][10];
		mx = new int[ttlmN];
		my = new int[ttlmN];
		bimg = win.bimg;
		
		for (int i = 0; i< MAX_X; i++){
			for (int j = 0; j< MAX_Y; j++){
			mb[i][j] = new MyButton(i, j);
			mb[i][j].jb = new JButton(bimg.bfirst);
			mb[i][j].jb.setBounds(MyButton.SIZE*i, MyButton.SIZE*j, MyButton.SIZE, MyButton.SIZE);
			add(mb[i][j].jb);
			}
		}
		
		makemine(mb, mx, my);
	}
}

class CountDown extends JPanel  {
	private Main win;
	private TextImg ti;
	
	Tstart ts;
	JLabel jl1, jl2, jl3, jl4, jl5;
	int time;
	
	public CountDown(Main win) {
		this.win = win;
		setBounds(182, 320, 250, 100);
		setBackground(new Color(153, 255, 153)); //panel 색 조정
		
		this.ti = win.ti;
		
		jl1 = new JLabel(ti.numImg[2]);
		jl2 = new JLabel(ti.numImg[0]);
		jl3 = new JLabel(ti.colonImg);
		jl4 = new JLabel(ti.numImg[0]);
		jl5 = new JLabel(ti.numImg[0]);		
		add(jl1);
		add(jl2);
		add(jl3);
		add(jl4);
		add(jl5);
		
		time = 1200;
		ts = new Tstart(this, time);
	}
	
	public class Tstart extends Thread implements Runnable {
		private CountDown cd;
		int time;
		private volatile  boolean running = true;
		//int count = 100;
		
		public Tstart(CountDown cd, int time){
			this.cd = cd;
			this.time = time;
		}
		public void terminate() {
		        running = false;
		}
		public void run(){
			while(running && time != 0){
				try{Thread.sleep(1000);}
				catch(InterruptedException e){}
				time--;
					cd.jl1 = new JLabel(ti.numImg[(time / 600)]);
					cd.jl2 = new JLabel(ti.numImg[((time % 600) / 60)]);
					cd.jl4 = new JLabel(ti.numImg[(((time % 600) % 60) / 10)]);
					cd.jl5 = new JLabel(ti.numImg[(time % 10)]);	
				
					cd.removeAll();
					cd.add(jl1);
					cd.add(jl2);
					cd.add(jl3);
					cd.add(jl4);
					cd.add(jl5);
					
					win.preventionActBug = true;
					win.revalidate();
					win.repaint();
				//}
				//else count--;
			}
			if(time <= 0){
				win.ega = new EndGameAct(win, false);
				win.ega.start();
			}
		}
	}
	
}

class Main extends JFrame{
	Container ct;
	Menu menu;
	CountDown cd;
	Game gm;
	TextImg ti;
	ButtonImg bimg;
	EndGameAct ega;
	
	boolean preventionActBug = true;
	
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //프래임 종료시 프로그램 종료, 생성자 초반부에 작성해준다.
		ct = getContentPane();
		//ct.setLayout(new FlowLayout(FlowLayout.CENTER, 500, 10));
		ct.setLayout(null);
		ct.setBackground(new Color(153, 255, 153));
		
		ti = new TextImg();
		bimg = new ButtonImg();
		
		menu = new Menu(this);
		ct.add(menu);
		setTitle("Event Test3");
		setSize(615, 435); //in my screen it is 600,400;
		setVisible(true);
	}
	public void startGame(){
		getContentPane().removeAll();
		cd = new CountDown(this);
		gm = new Game(this);
		getContentPane().add(cd);
		getContentPane().add(gm);
		revalidate();
		repaint();
		cd.ts.start();
	}
}

public class Minehunter {
	public static void main(String[] args) {
		new Main();
	}
}
