package Minehunter;

class SetGame implements Runnable{
	MainFrame win;

	public SetGame(MainFrame win){
		this.win = win;
	}
	
	public void run(){
		try{win.ct.remove(win.menu);}
		catch(NullPointerException error){}
		try{win.ct.remove(win.backM);}
		catch(NullPointerException error){}

		win.setSize(Game.max_x*MyButton.SIZE + 115, Game.max_y*MyButton.SIZE + 185);
		win.frameSize = win.getSize();
		win.setLocation((win.screenSize.width-win.frameSize.width)/2, (win.screenSize.height-win.frameSize.height)/2);
		
		win.cu = new CountUp(win);
		win.gm = new Game(win);
		
		win.ct.add(win.cu);
		win.ct.add(win.gm);
	
		win.revalidate();
		win.repaint();
		
		new Thread(win.gm).start();
	}
}