package Minehunter;

import javax.swing.ImageIcon;

class Bomb extends Thread {
	Game gm;
	MyButton mb;
	BombOnce bombonce;
	
	int time = 1500;
	
	public Bomb(Game gm, MyButton mb) {
		this.gm = gm;
		this.mb = mb;
	}
	
	public void run(){
		try{sleep(600);}
		catch(InterruptedException er){}

		bombonce =new BombOnce(gm.win, mb);
		bombonce.start();
		try{bombonce.sleep(time);}
		catch(InterruptedException er){}
		time = 900;
		
		for(int i=0; i<Game.totalmineN; i++){
			if(mb != gm.mb[gm.mx[i]][gm.my[i]]){
				bombonce = new BombOnce(gm.win, gm.mb[gm.mx[i]][gm.my[i]]);
				bombonce.start();
				try{bombonce.sleep(time);}
				catch(InterruptedException er){}
				if(time > 100)time = (time * 6 / 10);
			}
		}
				
		gm.win.revalidate();
		gm.win.repaint();
				 
		gm.win.ega = new EndGameAct(gm.win, false);
		gm.win.ega.start();
	}
}

class BombOnce extends Thread {
		MainFrame win;
		MyButton mb;
		
		public BombOnce(MainFrame win, MyButton mb) {
			this.win = win;
			this.mb = mb;
		}
		
		public void run() {
			for(int i=0; i<6; i++){
				for(int j=0; j<10; j++){
					mb.jl.setIcon(new ImageIcon(MyImg.respath + "/bomb/" + i + j + ".gif"));
					try{sleep(10);}
					catch(InterruptedException e){}
				
					win.revalidate();
					win.repaint();
				}
				mb.jl.setIcon(MyImg.bendImg);
			}
		}
	}