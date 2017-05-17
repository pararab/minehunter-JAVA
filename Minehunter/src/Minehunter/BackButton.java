package Minehunter;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class BackButton extends JButton {
	private static final long serialVersionUID = 1L;
	MainFrame win;
	BackMainFrameListener backMListener;
		
	public BackButton(MainFrame win, int num) {
		this.win = win;
			
		setContentAreaFilled(false);
		setBorderPainted(false);
		
		switch(num){
			case 1: setIcon(MyImg.backImg1); break;
			case 2: setIcon(MyImg.backImg2); break;
			case 3: setIcon(MyImg.backImg3); break;
		}
	}
	public void addListener() {
		addActionListener(backMListener = new BackMainFrameListener(win));
	}
	public void removeListener() {
		removeActionListener(backMListener);
	}
}
	
class BackMainFrameListener implements ActionListener, Runnable{
	MainFrame win;
		
	public BackMainFrameListener(MainFrame win) {
		this.win = win;
	}
		
	public void run() {
		try{win.cu.ts.terminate();}catch(NullPointerException e){}
		try{win.ct.remove(win.menu);}catch(NullPointerException e){}
		try{win.ct.remove(win.gm);}catch(NullPointerException e){}
		try{win.ct.remove(win.cu);}catch(NullPointerException e){}
		try{win.ct.remove(win.backM);}catch(NullPointerException e){}
		try{win.ct.remove(win.endL);}catch(NullPointerException e){}
		
		win.addMenu();
		
		win.revalidate();
		win.repaint();
	}

	public void actionPerformed(ActionEvent e) {	
		new Thread(this).start();
	}
}