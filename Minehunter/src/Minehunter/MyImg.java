package Minehunter;

import javax.swing.ImageIcon;
import java.io.File;


class MyImg {
	static String respath = new File("").getAbsolutePath() + "/rss";
	
	static ImageIcon bfirst = new ImageIcon(respath + "/bimg_first.gif");
	static ImageIcon bnumImg[] = {new ImageIcon(respath + "/bimg_0.gif"),
			 new ImageIcon(respath + "/bimg_1.gif"), 
			 new ImageIcon(respath + "/bimg_2.gif"),
			 new ImageIcon(respath + "/bimg_3.gif"),
			 new ImageIcon(respath + "/bimg_4.gif"),
			 new ImageIcon(respath + "/bimg_5.gif"),
			 new ImageIcon(respath + "/bimg_6.gif"),
			 new ImageIcon(respath + "/bimg_7.gif"),
			 new ImageIcon(respath + "/bimg_8.gif")};
	static ImageIcon bmineImg = new ImageIcon(respath + "/bimg_mine.gif");
	static ImageIcon bendImg = new ImageIcon(respath + "/bimg_end.gif");
	static ImageIcon bingoImg = new ImageIcon(respath + "/bimg_bingo.gif");
	static ImageIcon bqImg = new ImageIcon(respath + "/bimg_q.gif");
	static ImageIcon clear = new ImageIcon(respath + "/clear.gif");
	static ImageIcon fail = new ImageIcon(respath + "/fail.gif");
	static ImageIcon watchImg = new ImageIcon(respath + "/watch.gif");
	static ImageIcon mineImg = new ImageIcon(respath + "/mine.gif");
	static ImageIcon numJPbgi = new ImageIcon(respath + "/numJPbgi.gif");
	static ImageIcon backImg1 = new ImageIcon(respath + "/back1.gif");
	static ImageIcon backImg2 = new ImageIcon(respath + "/back2.gif");
	static ImageIcon backImg3 = new ImageIcon(respath + "/back3.gif");
}