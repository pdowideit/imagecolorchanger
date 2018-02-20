package imagecolorchanger;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class Main {

	public String[] images = {"./img/room/1.jpg","./img/room/2.jpg","./img/room/3.jpg","./img/room/4.jpg"};
	public static int imageNum = 1;
	public static void main(String[] args) {
		MainUI ui = new MainUI();
		ui.startup();
	}
	
	public static String prevImg(){
		imageNum--;
		if(imageNum < 1)
		{
			imageNum = 4;
		}
		return "./img/room/"+imageNum+".jpg";
	}
	
	public static String nextImg(){
		imageNum++;
		if(imageNum > 4)
		{
			imageNum = 1;
		}
		return "./img/room/"+imageNum+".jpg";
	}
	
	public static Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}

}
