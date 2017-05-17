package Minehunter;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

class CountUp extends JPanel  {
	private static final long serialVersionUID = 1L;
	
	private MainFrame win;
	JImagePanel timeP;
	Tstart ts;
	JLabel watchImg, cuN;
	
	public CountUp(MainFrame win) {
		this.win = win;
		setLayout(null);
		setBounds(50, MyButton.SIZE*Game.max_y + 70, 120, 100);
		setBackground(new Color(230, 255, 255)); //panel 색 조정
		
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
				
				for(int i=0; i<5; i++){
					try{Thread.sleep(200);}
					catch(InterruptedException e){}
					win.preventionActBug = true;
				}
				
				time++;
				cuN.setText(Integer.toString(time));					
				
				win.revalidate();
				win.repaint();
			}
		}
	}
}