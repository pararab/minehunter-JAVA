package Minehunter;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

class ListenerBN implements MouseListener {
	Game gm;
	MyButton mb;
	Scan scan;
	
	public ListenerBN (Game gm, MyButton mb){
		this.gm = gm;
		this.mb = mb;
	}
	public void mousePressed(MouseEvent e){
		 if(e.getButton() == MouseEvent.BUTTON3){ // 오른쪽 마우스 클릭
			 	if(gm.win.preventionActBug == true){
			 		gm.win.preventionActBug = false;
			 		if(mb.form == 0){
			 			mb.jb.setIcon(MyImg.bingoImg);
			 			mb.form++;
			 			mb.jb.removeMouseListener(mb.listener);
			 			mb.jb.addMouseListener(mb.listener = new ListenerBN(gm, mb));
			 			
			 			gm.mineCount--;
			 			gm.mineNL.setText(Integer.toString(gm.mineCount));
			 		}
			 		else if(mb.form == 1){
			 			mb.jb.setIcon(MyImg.bqImg);
			 			mb.form++;
			 			mb.jb.removeMouseListener(mb.listener);
			 			mb.jb.addMouseListener(mb.listener = new ListenerBN(gm, mb));
			 			
			 			gm.mineCount++;
			 			gm.mineNL.setText(Integer.toString(gm.mineCount));
			 		}
			 		else {
			 			mb.jb.setIcon(MyImg.bfirst);
			 			mb.form = 0;
			 			mb.jb.removeMouseListener(mb.listener);
			 			mb.jb.addMouseListener(mb.listener = new ListenerBN(gm, mb));
			 		}

					gm.win.revalidate();
					gm.win.repaint();
			 	}
		  }
		  else{       // 왼쪽 마우스 클릭	

			  if(mb.form != 1){
				  gm.remove(mb.jb);
				  gm.remove(mb.jb);
				  mb.jl = new JLabel(MyImg.bnumImg[mb.mineN]);
				  mb.jl.setBounds(mb.x*MyButton.SIZE+50, mb.y*MyButton.SIZE+50, MyButton.SIZE, MyButton.SIZE);
				
				  gm.add(mb.jl);
				  mb.checkpush = true;
			  	
				  gm.win.revalidate();
				  gm.win.repaint();

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
		  }
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
}