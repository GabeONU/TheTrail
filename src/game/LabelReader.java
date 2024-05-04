package game;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LabelReader {
	
	public sprite sprts = new sprite();

	
	private static final String COMMA_DELIMITER = ",";

	
	public ArrayList<JLabel> makeLabels(String path) {
		
		ArrayList<JLabel> allLabels = new ArrayList<>();

		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line;
			String[] thing = null;
            	while ((line = br.readLine()) != null) {
            		
                	thing = line.split(COMMA_DELIMITER);
                	JLabel lbl = new JLabel(thing[0]);
            		lbl.setFont(new Font(thing[5], Font.PLAIN, Integer.parseInt(thing[6])));
            		lbl.setBounds(Integer.parseInt(thing[1]), Integer.parseInt(thing[2]), Integer.parseInt(thing[3]), Integer.parseInt(thing[4]));
            		if(thing.length > 7) {
            			ImageIcon imageIcon = new ImageIcon(sprts.setSpriteImage(thing[7],Integer.parseInt(thing[3]),Integer.parseInt(thing[4])));
            			lbl.setIcon(imageIcon);
            		}
            		allLabels.add(lbl);

                	
            	}
                
            }
         catch (IOException e) {
            e.printStackTrace();
        }
		
		return allLabels;
	}
	
public JLabel makeLabel(String path, String name, int x, int y) {
		
		JLabel lbl = new JLabel(name);
		lbl.setSize(x, y);
		if(name.equals("for")) {
			lbl.setBounds(-500, -50, x, y);
		}
		if(name.equals("mid")) {
			lbl.setBounds(-100, 90, x, y);
		}
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
					
            		ImageIcon imageIcon = new ImageIcon(sprts.setSpriteImage(path,x,y));
            		lbl.setIcon(imageIcon);
            		

                	
            	}
                
            
         catch (IOException e) {
            e.printStackTrace();
        }
		
		return lbl;
	}
	
	public JLabel randLabel(String path) {
		
		JLabel lbl = new JLabel();
		
		Random rand = new Random();

        // Generate a random integer between 0 and 99
        int randomDist = rand.nextInt(3) + 1;
        int randomImg = rand.nextInt(2) + 1;
        String name = "for";
        String img = "src/game.imgs/tree01.png";
        int size = 64;
        int x = -70;
        int y = 90;
        
        switch(randomDist) {
        case 1:
        	name = "for";
        	size = 96;
        	y = 90;
        	break;
        case 2:
        	name = "mid";
        	size = 64;
        	y = 60;
        	break;
        case 3:
        	name = "bac";
        	size = 32;
        	x = -20;
        	y = 35;
        	break;
        
        }
        switch(randomImg) {
        case 1:
            img = "src/game/imgs/tree01.png";
        	break;
        case 2:
            img = "src/game/imgs/tree02.png";
        	break;
        }

		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line;
			String[] thing = null;
            	line = br.readLine();
            		
                	thing = line.split(COMMA_DELIMITER);
					ImageIcon imageIcon = new ImageIcon(sprts.setSpriteImage(img,size,size));
                	lbl = new JLabel(imageIcon);
					lbl.setLayout(null);
					lbl.setName(name);
            		lbl.setFont(new Font("Microsoft San Serif", Font.PLAIN, size));
            		lbl.setBounds(x, y, size, size);

            		
                	
            	
                
            }
         catch (IOException e) {
            e.printStackTrace();
        }
		
		
		return lbl;
	}

}
