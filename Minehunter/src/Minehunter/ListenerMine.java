package Minehunter;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

class ListenerMine implements MouseListener {
	Game gm;
	MyButton mb;
	Bomb bomb;
	Scan scan;
	
	public ListenerMine (Game gm, MyButton mb){
		this.gm = gm;
		this.mb = mb;
	}
	
	public void mousePressed(MouseEvent e) { 
		if(e.getButton() == MouseEvent.BUTTON3){	// 오른쪽 마우스 클릭
			if(gm.win.preventionActBug == true){
				gm.win.preventionActBug = false;
			  	if(mb.form == 0){
			  		mb.jb.setIcon(MyImg.bingoImg);
			  		mb.form++;
			  		mb.jb.removeMouseListener(mb.listener);
			  		mb.jb.addMouseListener(mb.listener = new ListenerMine(gm, mb));
			  		gm.minecheckN++;
			  		
		 			gm.mineCount--;
		 			gm.mineNL.setText(Integer.toString(gm.mineCount));
			  		
			  		if(gm.minecheckN == Game.totalmineN){
			  			boolean check = true;
			  			for(int i=0; i<Game.max_x; i++){
			  				for(int j=0; j<Game.max_y; j++){
			  					if(gm.mb[i][j].mine == false 
			  							&& gm.mb[i][j].checkpush == false) {
			  						check = false; break;
			  					}
			  				}
			  			}
			  			if(check == true){
							gm.win.backM.removeListener();
							
							gm.win.cu.ts.terminate();
							
							for(int i=0; i<Game.max_x; i++){
								for(int j=0; j<Game.max_y; j++){
									try{gm.mb[i][j].jb.removeMouseListener(gm.mb[i][j].listener);}
									catch(NullPointerException err){}
								}
							}
							
							scan = new Scan(gm);
							scan.start();
						  }
			  		}
			  	}
			  	else if(mb.form == 1){
			  		mb.jb.setIcon(MyImg.bqImg);
			  		mb.form++;
			  		mb.jb.removeMouseListener(mb.listener);
			  		mb.jb.addMouseListener(mb.listener = new ListenerMine(gm, mb));
			  		gm.minecheckN--;
			  		
		 			gm.mineCount++;
		 			gm.mineNL.setText(Integer.toString(gm.mineCount));
			  	}
			  	else {
			  		mb.jb.setIcon(MyImg.bfirst);
			  		mb.form = 0;
			  		mb.jb.removeMouseListener(mb.listener);
			  		mb.jb.addMouseListener(mb.listener = new ListenerMine(gm, mb));
			  	}

				gm.win.revalidate();
				gm.win.repaint();
			}
		 }
		 else{
			 if(mb.form != 1){
				gm.remove(mb.jb);
				gm.win.backM.removeListener();
			 	
				for(int i=0; i<Game.max_x; i++){
					for(int j=0; j<Game.max_y; j++){
						try{gm.mb[i][j].jb.removeMouseListener(gm.mb[i][j].listener);
						
						}
						catch(NullPointerException err){}
					}
				}
				
			 	for(int i=0; i<Game.totalmineN; i++){
					gm.remove(gm.mb[gm.mx[i]][gm.my[i]].jb);
					gm.mb[gm.mx[i]][gm.my[i]].jl = new JLabel(MyImg.bmineImg);
					gm.mb[gm.mx[i]][gm.my[i]].jl.setBounds(
								gm.mb[gm.mx[i]][gm.my[i]].x*MyButton.SIZE+50,
								gm.mb[gm.mx[i]][gm.my[i]].y*MyButton.SIZE+50, 
								MyButton.SIZE, MyButton.SIZE);
					gm.add(gm.mb[gm.mx[i]][gm.my[i]].jl);
				}
				gm.win.revalidate();
				gm.win.repaint();

				gm.win.cu.ts.terminate();

				bomb = new Bomb(gm, mb);
				bomb.start();
			 }
		 }
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseClicked(MouseEvent e){}
}