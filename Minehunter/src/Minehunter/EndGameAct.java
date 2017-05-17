package Minehunter;

import javax.swing.JLabel;

class EndGameAct extends Thread {
	private MainFrame win;
	boolean check;
	
	public EndGameAct(MainFrame win, boolean check){
		this.win = win;
		this.check = check;
	}
	public void run(){
		//win.revalidate();
		//win.repaint();
		
		try{Thread.sleep(3000);}
		catch(InterruptedException err){} 
		
		try{win.ct.remove(win.gm);}catch(NullPointerException e){}
		try{win.ct.remove(win.cu);}catch(NullPointerException e){}
		try{win.ct.remove(win.backM);}catch(NullPointerException e){}

		win.setSize(615, 435);
		win.frameSize = win.getSize();
		win.setLocation((win.screenSize.width-win.frameSize.width)/2, (win.screenSize.height-win.frameSize.height)/2);
		
		
		if(check == true) win.endL = new JLabel(MyImg.clear);
		else win.endL = new JLabel(MyImg.fail);
		win.endL.setBounds(100, 80, 400, 150);
		
		win.backM = new BackButton(win, 3);
		win.backM.addListener();
		win.backM.setBounds(200, 250, 200, 50);
		
		win.ct.add(win.endL);
		win.ct.add(win.backM);
		win.revalidate();
		win.repaint();
	}
}