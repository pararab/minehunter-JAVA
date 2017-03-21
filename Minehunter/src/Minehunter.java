import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

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
	ImageIcon plusImg = new ImageIcon(path.getAbsolutePath() + "/rss/text_+.gif");
	ImageIcon axesImg = new ImageIcon(path.getAbsolutePath() + "/rss/text_axes.gif");
	ImageIcon colonImg = new ImageIcon(path.getAbsolutePath() + "/rss/text_colon.gif");
	
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
			win.changeJP(new Game(win)/*, new CountDown(win)*/);
		}
	}
}

class Game extends JPanel {
	private Main win;
	private int score = 0;
	
	JButton jb[][];
	JLabel jl[][];
	
	public Game(Main win) {
		setLayout(null);
		this.win = win;
		//setBackground(new Color(153, 255, 153)); //panel 색 조정
		setBounds(50, 50, 500, 250); //위치, 크기 조정 이를 위해 Container와 JPanel 모두 layout을 null로 지정해줘야 한다.
		
		jb = new JButton[20][10];
		
		for (int i = 0; i< 20; i++){
			for (int j = 0; j< 10; j++){
			jb[i][j] = new JButton();
			jb[i][j].setBounds(25*i, 25*j, 25, 25);
			add(jb[i][j]);
			}
		}
		
		//j1 = new JButton("hi");
		//jl = new JLabel("hi");
		//j1.addActionListener(new AnyactionListener(jl));
	
		//j1.setBounds(0, 0, 300, 50);
		//jl.setBounds(50, 50, 100, 50);
		
		//add(j1);
		//add(jl);
	}
	class AnyactionListener implements ActionListener {
		JLabel jl;
		public AnyactionListener(JLabel jl){
			this.jl = jl;
		}
		public void actionPerformed(ActionEvent e){
			jl.setText("OK");
		}
	}
}

class CountDown extends JPanel  {
	private Main win;
	private TextImg ti;
	
	Tstart ts;
	JLabel jl1, jl2, jl3, jl4;
	int time;
	
	public CountDown(Main win) {
		this.win = win;
		setBounds(207, 335, 200, 100);
		//setBackground(new Color(153, 255, 153)); //panel 색 조정
		
		ti = new TextImg();
		
		jl1 = new JLabel(ti.numImg[5]);
		jl2 = new JLabel(ti.colonImg);
		jl3 = new JLabel(ti.numImg[0]);
		jl4 = new JLabel(ti.numImg[0]);		
		add(jl1);
		add(jl2);
		add(jl3);
		add(jl4);
		
		time = 300;
		ts = new Tstart(this, time);
	}
	
	class Tstart extends Thread implements Runnable {
		private CountDown cd;
		int time;
		
		public Tstart(CountDown cd, int time){
			this.cd = cd;
			this.time = time;
		}
		
		public void run(){
			while(time > 0){
				try{Thread.sleep(1000);}
				catch(InterruptedException e){}
				time--;
				
				cd.jl1 = new JLabel(ti.numImg[(time / 60)]);
				cd.jl3 = new JLabel(ti.numImg[((time % 60) / 10)]);
				cd.jl4 = new JLabel(ti.numImg[(time % 10)]);	
				
				cd.removeAll();
				cd.add(jl1);
				cd.add(jl2);
				cd.add(jl3);
				cd.add(jl4);
			
				win.revalidate();
				win.repaint();
			}
		}
	}
	
}

class Main extends JFrame {
	JPanel menu, game;
	Container ct;
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //프래임 종료시 프로그램 종료, 생성자 초반부에 작성해준다.
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
	public void changeJP(JPanel jp){
			getContentPane().removeAll();
			getContentPane().add(jp);
			if(jp instanceof Game){
				CountDown tmp = new CountDown(this);
				getContentPane().add(tmp);
				revalidate();
				repaint();
				tmp.ts.start();
			}
			else{
			revalidate();
			repaint();
			}
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
