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
	
	ImageIcon bnumImg[] = {new ImageIcon(path.getAbsolutePath() + "/rss/bimg_0.gif"),
			 new ImageIcon(path.getAbsolutePath() + "/rss/bimg_1.gif"), 
			 new ImageIcon(path.getAbsolutePath() + "/rss/bimg_2.gif"),
			 new ImageIcon(path.getAbsolutePath() + "/rss/bimg_3.gif"),
			 new ImageIcon(path.getAbsolutePath() + "/rss/bimg_4.gif"),
			 new ImageIcon(path.getAbsolutePath() + "/rss/bimg_5.gif"),
			 new ImageIcon(path.getAbsolutePath() + "/rss/bimg_6.gif"),
			 new ImageIcon(path.getAbsolutePath() + "/rss/bimg_7.gif"),
			 new ImageIcon(path.getAbsolutePath() + "/rss/bimg_8.gif")};
	ImageIcon BmineImg = new ImageIcon(path.getAbsolutePath() + "/rss/bimg_mine.gif");
	ImageIcon BendImg = new ImageIcon(path.getAbsolutePath() + "/rss/bimg_end.gif");
	ImageIcon BbingoImg = new ImageIcon(path.getAbsolutePath() + "/rss/bimg_bingo.gif");
	ImageIcon BqImg = new ImageIcon(path.getAbsolutePath() + "/rss/bimg_q.gif");
	
}

class Menu extends JPanel  {
	private Main win;

	File path = new File("");
	JLabel titleL;
	JButton startB;
	
	public Menu(Main win) {
		setLayout(null);
		this.win = win;
		//setPreferredSize(new Dimension(200, 300)); //panel ũ�� ����
		setBackground(new Color(153, 255, 153)); //panel �� ����
		setBounds(50, 70, 500, 300); //��ġ, ũ�� ���� �̸� ���� Container�� JPanel ��� layout�� null�� ��������� �Ѵ�.
		
		startB = new JButton(new ImageIcon(path.getAbsolutePath() + "/rss/start.gif"));
		titleL = new JLabel( new ImageIcon(path.getAbsolutePath() + "/rss/Title.gif"));
		
		startB.setContentAreaFilled(false); //��ư ���ΰ��� �����
		startB.setBorderPainted(false); //��ư �׵θ� �����
		
		startB.setBounds(0, 180, 500, 50);
		titleL.setBounds(0, 10, 500, 150);
		
		startB.addActionListener(new StartBListener());
		//jb2.addActionListener(new EventClass(jl)); //�̺�Ʈ ó��
		
		add(startB);
		add(titleL);
	}
	class StartBListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			win.changeJP(/*new Game(win)*/ new CountDown(win));
		}
	}
}
class MyButton {
	JButton jb = new JButton();
	JLabel jl = new JLabel();
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
	ButtonImg bimg = new ButtonImg();
	
	public ListenerBN (Game gm, MyButton mb){
		this.gm = gm;
		this.mb = mb;
	}
	public void mouseClicked(MouseEvent e){
		 gm.remove(mb.jb);
		 if(e.getButton() == e.BUTTON3){    // ������ ���콺 Ŭ��
			  if(mb.form == 0){
				  mb.jb = new JButton(bimg.BbingoImg);
				  mb.form++;
				  mb.jb.setBounds(mb.x*25, mb.y*25, 25, 25);
				  mb.jb.addMouseListener(this);
			  }
			  else if(mb.form == 1){
				  mb.jb = new JButton(bimg.BqImg);
				  mb.form++;
				  mb.jb.setBounds(mb.x*25, mb.y*25, 25, 25);
				  mb.jb.addMouseListener(this);
			  }
			  else {
				  mb.jb = new JButton();
				  mb.form = 0;
				  mb.jb.setBounds(mb.x*25, mb.y*25, 25, 25);
				  mb.jb.addMouseListener(this);
			  }
			  gm.add(mb.jb);
		  }
		  else{       // ���� ���콺 Ŭ��	  
			  mb.jl = new JLabel(bimg.bnumImg[mb.mineN]);
			  mb.jl.setBounds(mb.x*25, mb.y*25, 25, 25);
				
			  gm.add(mb.jl);
			  mb.checkpush = true;
		  }
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
}
class ListenerB0 implements MouseListener {
	Game gm;
	MyButton mb;
	ButtonImg bimg = new ButtonImg();
	
	public ListenerB0 (Game gm, MyButton mb){
		this.gm = gm;
		this.mb = mb;
	}
	public void mouseClicked(MouseEvent e){
		 gm.remove(mb.jb);
		 if(e.getButton() == e.BUTTON3){    // ������ ���콺 Ŭ��
			  if(mb.form == 0){
				  mb.jb = new JButton(bimg.BbingoImg);
				  mb.form++;
				  mb.jb.setBounds(mb.x*25, mb.y*25, 25, 25);
				  mb.jb.addMouseListener(this);
			  }
			  else if(mb.form == 1){
				  mb.jb = new JButton(bimg.BqImg);
				  mb.form++;
				  mb.jb.setBounds(mb.x*25, mb.y*25, 25, 25);
				  mb.jb.addMouseListener(this);
			  }
			  else {
				  mb.jb = new JButton();
				  mb.form = 0;
				  mb.jb.setBounds(mb.x*25, mb.y*25, 25, 25);
				  mb.jb.addMouseListener(this);
			  }
			  gm.add(mb.jb);
		  }
		  else{       // ���� ���콺 Ŭ��	  
			  ChangeImg ci = new ChangeImg(mb);
			  ci.run();
		  }
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	
	class ChangeImg extends Thread implements Runnable{
		MyButton mb;
		ChangeImg ci;
		public ChangeImg(MyButton mb){
			this.mb = mb;
		}
			
		public void run(){	
			if(mb.checkpush == false){
				gm.remove(mb.jb);
				mb.jl = new JLabel(bimg.bnumImg[mb.mineN]);
				mb.jl.setBounds(mb.x*25, mb.y*25, 25, 25);
				mb.checkpush = true;
				gm.add(mb.jl);

				if(mb.mineN == 0){
					if(mb.x == 0){
						if(mb.y == 0){
							ci = new ChangeImg(gm.mb[mb.x+1][mb.y]); ci.run();
							ci = new ChangeImg(gm.mb[mb.x][mb.y+1]); ci.run();
							ci = new ChangeImg(gm.mb[mb.x+1][mb.y+1]); ci.run();
						}
						else if(mb.y == 9){
							ci = new ChangeImg(gm.mb[mb.x][mb.y-1]); ci.run();
							ci = new ChangeImg(gm.mb[mb.x+1][mb.y-1]); ci.run();
							ci = new ChangeImg(gm.mb[mb.x+1][mb.y]); ci.run();
						}
						else{
							ci = new ChangeImg(gm.mb[mb.x][mb.y-1]); ci.run();
							ci = new ChangeImg(gm.mb[mb.x+1][mb.y-1]); ci.run();
							ci = new ChangeImg(gm.mb[mb.x+1][mb.y]); ci.run();
							ci = new ChangeImg(gm.mb[mb.x][mb.y+1]); ci.run();
							ci = new ChangeImg(gm.mb[mb.x+1][mb.y+1]); ci.run();
						}
					}
					else if(mb.x == 19){
						if(mb.y == 0){
							ci = new ChangeImg(gm.mb[mb.x-1][mb.y]); ci.run();
							ci = new ChangeImg(gm.mb[mb.x-1][mb.y+1]); ci.run();
							ci = new ChangeImg(gm.mb[mb.x][mb.y+1]); ci.run();
						}
						else if(mb.y == 9){
							ci = new ChangeImg(gm.mb[mb.x-1][mb.y-1]); ci.run();
							ci = new ChangeImg(gm.mb[mb.x][mb.y-1]); ci.run();
							ci = new ChangeImg(gm.mb[mb.x-1][mb.y]); ci.run();
						}
						else{
							ci = new ChangeImg(gm.mb[mb.x-1][mb.y-1]); ci.run();
							ci = new ChangeImg(gm.mb[mb.x][mb.y-1]); ci.run();
							ci = new ChangeImg(gm.mb[mb.x-1][mb.y]); ci.run();
							ci = new ChangeImg(gm.mb[mb.x-1][mb.y+1]); ci.run();
							ci = new ChangeImg(gm.mb[mb.x][mb.y+1]); ci.run();
						}
					}
					else{
						if(mb.y == 0){
							ci = new ChangeImg(gm.mb[mb.x-1][mb.y]); ci.run();
							ci = new ChangeImg(gm.mb[mb.x+1][mb.y]); ci.run();
							ci = new ChangeImg(gm.mb[mb.x-1][mb.y+1]); ci.run();
							ci = new ChangeImg(gm.mb[mb.x][mb.y+1]); ci.run();
							ci = new ChangeImg(gm.mb[mb.x+1][mb.y+1]); ci.run();
						}
						else if(mb.y == 9){
							ci = new ChangeImg(gm.mb[mb.x-1][mb.y-1]); ci.run();
							ci = new ChangeImg(gm.mb[mb.x][mb.y-1]); ci.run();
							ci = new ChangeImg(gm.mb[mb.x+1][mb.y-1]); ci.run();
							ci = new ChangeImg(gm.mb[mb.x-1][mb.y]); ci.run();
							ci = new ChangeImg(gm.mb[mb.x+1][mb.y]); ci.run();
						}
						else{
							ci = new ChangeImg(gm.mb[mb.x-1][mb.y-1]); ci.run();
							ci = new ChangeImg(gm.mb[mb.x][mb.y-1]); ci.run();
							ci = new ChangeImg(gm.mb[mb.x+1][mb.y-1]); ci.run();
							ci = new ChangeImg(gm.mb[mb.x-1][mb.y]); ci.run();
							ci = new ChangeImg(gm.mb[mb.x+1][mb.y]); ci.run();
							ci = new ChangeImg(gm.mb[mb.x-1][mb.y+1]); ci.run();
							ci = new ChangeImg(gm.mb[mb.x][mb.y+1]); ci.run();
							ci = new ChangeImg(gm.mb[mb.x+1][mb.y+1]); ci.run();
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
	ButtonImg bimg = new ButtonImg();
	
	public ListenerMine (Game gm, MyButton mb){
		this.gm = gm;
		this.mb = mb;
	}
	
	public void mouseClicked(MouseEvent e) {
		gm.remove(mb.jb); 
		if(e.getButton() == e.BUTTON3){    // ������ ���콺 Ŭ��
			  if(mb.form == 0){
				  mb.jb = new JButton(bimg.BbingoImg);
				  mb.form++;
				  mb.jb.setBounds(mb.x*25, mb.y*25, 25, 25);
				  mb.jb.addMouseListener(this);
				  gm.minecheckN++;
			  }
			  else if(mb.form == 1){
				  mb.jb = new JButton(bimg.BqImg);
				  mb.form++;
				  mb.jb.setBounds(mb.x*25, mb.y*25, 25, 25);
				  mb.jb.addMouseListener(this);
				  gm.minecheckN--;
			  }
			  else {
				  mb.jb = new JButton();
				  mb.form = 0;
				  mb.jb.setBounds(mb.x*25, mb.y*25, 25, 25);
				  mb.jb.addMouseListener(this);
			  }
			  gm.add(mb.jb);
		 }
		 else{
			 for(int i=0; i<gm.ttlmN; i++){
					gm.remove(gm.mb[gm.mx[i]][gm.my[i]].jb);
					gm.mb[gm.mx[i]][gm.my[i]].jl = new JLabel(bimg.BmineImg);
					gm.mb[gm.mx[i]][gm.my[i]].jl.setBounds(
								gm.mb[gm.mx[i]][gm.my[i]].x*25,
								gm.mb[gm.mx[i]][gm.my[i]].y*25, 25, 25);
					gm.add(gm.mb[gm.mx[i]][gm.my[i]].jl);
				}
			 	gm.remove(mb.jl);
				mb.jl = new JLabel(bimg.BendImg);
				mb.jl.setBounds(mb.x*25, mb.y*25, 25, 25);
				gm.add(mb.jl);
		 }
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e){}
}
class Game extends JPanel {
	private Main win;
	private int score = 0;
	
	MyButton mb[][];
	int[] mx;
	int[] my;
	int minecheckN = 0;
	int ttlmN = 10;
	
	void makemine(MyButton mb[][], int mx[], int my[]){
		Random random = new Random();
	
		mx[0] = random.nextInt(20);
		my[0] = random.nextInt(10);
		
		for(int i=1; i<ttlmN; i++){
			boolean pass = true;
			do
			{
				mx[i] = random.nextInt(20);
				my[i] = random.nextInt(10);
				
				for(int j = 0; j<i; j++){
					if(mx[i] == mx[j] && my[i] == my[j])
						pass = false;
					else pass = true;
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
				else if(my[i] == 9){
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
			else if(mx[i] == 19){
				if(my[i] == 0){
					mb[mx[i]-1][my[i]].mineN++;
					mb[mx[i]][my[i]+1].mineN++;
					mb[mx[i]-1][my[i]+1].mineN++;
				}
				else if(my[i] == 9){
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
				else if(my[i] == 9){
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
		
		for(int i=0; i<20; i++){
			for(int j=0; j<10; j++){
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
		//setBackground(new Color(153, 255, 153)); //panel �� ����
		setBounds(50, 50, 500, 250); //��ġ, ũ�� ���� �̸� ���� Container�� JPanel ��� layout�� null�� ��������� �Ѵ�.
		
		mb = new MyButton[20][10];
		mx = new int[10];
		my = new int[10];
		
		for (int i = 0; i< 20; i++){
			for (int j = 0; j< 10; j++){
			mb[i][j] = new MyButton(i, j);
			mb[i][j].jb.setBounds(25*i, 25*j, 25, 25);
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
		setBackground(new Color(153, 255, 153)); //panel �� ����
		
		ti = new TextImg();
		
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
	
	class Tstart extends Thread implements Runnable {
		private CountDown cd;
		int time;
		//int count = 10;
		
		public Tstart(CountDown cd, int time){
			this.cd = cd;
			this.time = time;
		}
		
		public void run(){
			while(!Thread.currentThread().isInterrupted() || time > 0){
				try{Thread.sleep(1000);}
				catch(InterruptedException e){}
				time--;
				//if(count == 0){
					//count = 10; // ���� �����ӿ��� �����ؾߵ�...
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
			
					win.revalidate();
					win.repaint();
				//}
				//else count--;
			}
		}
	}
	
}

class Main extends JFrame {
	JPanel menu, game;
	Container ct;
	CountDown cd;
	
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //������ ����� ���α׷� ����, ������ �ʹݺο� �ۼ����ش�.
		ct = getContentPane();
		//ct.setLayout(new FlowLayout(FlowLayout.CENTER, 500, 10));
		ct.setLayout(null);
		ct.setBackground(new Color(153, 255, 153));
		Menu menu = new Menu(this);
		ct.add(menu);
		setTitle("Event Test3");
		setSize(615, 435); //in my screen it is 600,400;
		setVisible(true);
	}
	public void changeJP(CountDown jp){
		cd = jp;
		getContentPane().removeAll();
		getContentPane().add(jp);
		if(jp instanceof CountDown){
			Game tmp = new Game(this);
			getContentPane().add(tmp);
			revalidate();
			repaint();
		}
		else{
		revalidate();
		repaint();
		}
		jp.ts.start();
	}
	public void changeJP(JPanel jp1, JPanel jp2){
		getContentPane().removeAll();
		getContentPane().add(jp1);
		getContentPane().add(jp2);
		revalidate();
		repaint();
}
}

public class Minehunter {
	public static void main(String[] args) {
		new Main();
	}
}
