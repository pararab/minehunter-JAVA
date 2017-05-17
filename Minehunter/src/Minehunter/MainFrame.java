package Minehunter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;

public class MainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	Container ct;
	Menu menu;
	CountUp cu;
	Game gm;
	BackButton backM;
	JLabel endL;
	
	EndGameAct ega;
	Dimension frameSize;
	Dimension screenSize;
	
	volatile boolean preventionActBug = true;
	
	
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //프래임 종료시 프로그램 종료, 생성자 초반부에 작성해준다.
		ct = getContentPane();
		ct.setLayout(null);
		ct.setBackground(new Color(230, 255, 255));
		setTitle("Event Test3");
		
		
		addMenu();
	}
	
	public void addMenu() {
		
		menu = new Menu(this);
		ct.add(menu);
		
		setSize(615, 435); //in my screen it is 600,400;
		
		frameSize = getSize();
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width-frameSize.width)/2, (screenSize.height-frameSize.height)/2);
		
		setResizable(false);
		setVisible(true);
	}
}