package game;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class sprite {
	
	public int xPos;
	public int yPos;
	public int zPos;
	
	public int width;
	public int height;
	
	public String imgPath;
	
	public ImageIcon imageIcon = new ImageIcon();
	
	public void setIcon() {
	 imageIcon.setImage(setSpriteImage(imgPath, width, height));
	
	}
	
	public Image setSpriteImage(String imgPath, int w, int h) {
		BufferedImage img = null;
        try {
        	System.out.println(new File(imgPath).exists());
            img = ImageIO.read(new File(imgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Sets the imgage size to the size of the JLabel
        Image dimg = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		
		return dimg;
	}
	

}
