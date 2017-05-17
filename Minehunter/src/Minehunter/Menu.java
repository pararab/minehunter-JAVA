package Minehunter;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

class Menu extends JPanel  {
	private static final long serialVersionUID = 1L;

	private MainFrame win;

	File path = new File("");
	JLabel titleL;
	JButton startB;
	JButton lvl1, lvl2, lvl3;
	
	//JButton lvlfree;
	
	public Menu(MainFrame win) {
		setLayout(null);
		this.win = win;
		setBackground(new Color(230, 255, 255)); //panel 색 조정
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
			remove(startB);
			remove(titleL);
			setBounds(120, 0, 370, 400);
			
			lvl1 = new JButton(new ImageIcon(path.getAbsolutePath() + "/rss/Blvl.gif"));
			lvl2 = new JButton(new ImageIcon(path.getAbsolutePath() + "/rss/Ilvl.gif"));
			lvl3 = new JButton(new ImageIcon(path.getAbsolutePath() + "/rss/Vlvl.gif"));
			//lvlfree = new JButton("User defined level");
			win.backM = new BackButton(win, 1);
			win.backM.addListener();
			
			lvl1.setContentAreaFilled(false);
			lvl1.setBorderPainted(false);
			lvl2.setContentAreaFilled(false);
			lvl2.setBorderPainted(false);
			lvl3.setContentAreaFilled(false);
			lvl3.setBorderPainted(false);
			
			lvl1.setBounds(20, 50, 350, 80);
			lvl2.setBounds(20, 170, 350, 80);
			lvl3.setBounds(20, 290, 350, 80);
			//lvlfree.setBounds(0, 210, 550, 50);
			win.backM.setBounds(0, 320, 130, 50);
			
			lvl1.addActionListener(new LvlBListener(10, 10, 10));
			lvl2.addActionListener(new LvlBListener(20, 15, 40));
			lvl3.addActionListener(new LvlBListener(40, 20, 150));
			//lvlfree.addActionListener(new LvlBListener());
			
			add(lvl1);
			add(lvl2);
			add(lvl3);
			//add(lvlfree);
			win.add(win.backM);
			
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
			Game.max_x = max_x;
			Game.max_y = max_y;
			Game.totalmineN = totalmineN;

			new Thread(new SetGame(win)).start();
		}
	}
}