package Minehunter;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

class Scan extends Thread {
	MainFrame win;
	Game gm;
	JPanel slidePanel;
	JLabel slide;
	
	public Scan(Game gm){
		this.gm = gm;
		this.win = gm.win;
		
		gm.slidePanel.setLayout(null);
		gm.slidePanel.setOpaque(false);
		gm.slidePanel.setBounds(50, MyButton.SIZE * Game.max_y + 50, MyButton.SIZE * Game.max_x, 0);
		
		gm.slide.setIcon(new ImageIcon(MyImg.respath + "/scan/slide.gif"));
		gm.slide.setBounds(0, 0, MyButton.SIZE * Game.max_x, 30);
		
		gm.slidePanel.add(gm.slide);
	}
	public void run(){
		try{sleep(1000);} catch(InterruptedException er){}
		
		for(int i = 1; i <= 30; i++) {
			try{sleep(10);} catch(InterruptedException er){}
			gm.slidePanel.setBounds(50, MyButton.SIZE * Game.max_y + 50 - i, MyButton.SIZE * Game.max_x, i);
			
			if((i%25) == 0){
				for(int j = 0; j<Game.max_x; j++){
					if(gm.mb[j][Game.max_y - (i/25)].mine == true){
						gm.mb[j][Game.max_y - (i/25)].jb.setIcon(MyImg.bmineImg);
					}
				}
			}
			
			win.revalidate();
			win.repaint();
		}

		for(int i = 31; i <= MyButton.SIZE * Game.max_y; i++) {
			try{sleep(10);} catch(InterruptedException er){}
			gm.slidePanel.setBounds(50, MyButton.SIZE * Game.max_y + 50 - i, MyButton.SIZE * Game.max_x, 30);

			if((i%25) == 0){
				for(int j = 0; j<Game.max_x; j++){
					if(gm.mb[j][Game.max_y - (i/25)].mine == true){
						gm.mb[j][Game.max_y - (i/25)].jb.setIcon(MyImg.bmineImg);
					}
				}
			}
			
			win.revalidate();
			win.repaint();
		}

		for(int i = 1; i <= 30; i++) {
			try{sleep(10);} catch(InterruptedException er){}
			gm.slide.setBounds(0, -i, MyButton.SIZE * Game.max_x, 30);

			if((i%25) == 0){
				for(int j = 0; j<Game.max_x; j++){
					if(gm.mb[j][Game.max_y - (i/25)].mine == true){
						gm.mb[j][Game.max_y - (i/25)].jb.setIcon(MyImg.bmineImg);
					}
				}
			}
			
			win.revalidate();
			win.repaint();
		}
		
		win.revalidate();
		win.repaint();
		
		try{sleep(1000);} catch(InterruptedException er){}
		
		win.ega = new EndGameAct(gm.win, true);
		win.ega.start();
	}
}