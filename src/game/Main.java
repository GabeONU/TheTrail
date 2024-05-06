package game;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class Main {
	
	private static LabelReader lblMake = new LabelReader();
	
	public static int day;
	public static int miliSecondCound = 0;
	public static int secondCount = 0;
	
	private static ArrayList<JLabel> allLabels = new ArrayList<>();	


	private static JFrame frame;
	
	private static boolean traveling = false;

	private static JLabel riverLabel = null;
	private static JLabel fortLabel = new JLabel();
	private static boolean fortEvent = false;
	private static boolean riverDecisionMade;
	private static boolean fortDecisionMade;
	private static JLabel flagLabel;
	private static boolean flagDescisionMade;
	private static boolean flagEvent = false;

	private static Wagon wagon = new Wagon();

	private static final String COMMA_DELIMITER = ",";
	private static JCheckBox[] checkBoxArray = new JCheckBox[40];

	private static Item[] itmArray = new Item[40];
	private static Person crew = new Person();
	private static boolean riverEvent = false;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		


	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 450);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().setLayout(null);
		
		frame.setLayout(null);
		
		JButton btnNewButton = new JButton("Play Game!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wagonScreen();
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 19));
		btnNewButton.setBounds(173, 101, 436, 199);
		frame.getContentPane().add(btnNewButton);
		
		
		
		
	}

	public static void gameScreen() {
    	frame.getContentPane().removeAll();
		frame.repaint();
	
		JButton btnNewButton = new JButton("Travel");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					traveling = !traveling;
				}
			});
			btnNewButton.setFont(new Font("Arial", Font.PLAIN, 13));
			btnNewButton.setBounds(700, 60, 60, 40);
			frame.getContentPane().add(btnNewButton);

			JButton txt = new JButton("Store");
			txt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					storeScreen();
				}
			});
			txt.setFont(new Font("Arial", Font.PLAIN, 13));
			txt.setBounds(638, 60, 60, 40);
			frame.getContentPane().add(txt);
			
			JButton btnDebug = new JButton("Debug");
			btnDebug.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					debugButtons();
				}
			});
			btnDebug.setFont(new Font("Arial", Font.PLAIN, 13));
			btnDebug.setBounds(700, 102, 60, 40);
			frame.getContentPane().add(btnDebug);
    		
    		JLabel wag = new JLabel("Wagon");
    		wag = lblMake.makeLabel("src/game/imgs/Wagon.png","wag",64,64);
    		wag.setBounds(360,150,64,64);
    		
       		JPanel topPanel = new JPanel();
 	        //topPanel.setBackground(Color.RED);
       		topPanel.setOpaque(false);
 	        topPanel.setSize(new Dimension(frame.getWidth(), frame.getHeight()));

 	        JPanel middlePanel = new JPanel();
 	        //middlePanel.setBackground(Color.GREEN);
 	        middlePanel.setOpaque(false);
 	        middlePanel.setSize(new Dimension(frame.getWidth(), frame.getHeight()));

 	        JPanel bottomPanel = new JPanel();
 	        //bottomPanel.setBackground(Color.BLUE);
 	        bottomPanel.setOpaque(false);
 	        bottomPanel.setSize(new Dimension(frame.getWidth(), frame.getHeight()));
 	        
 	       JPanel backgorund = new JPanel();
 	      backgorund.setOpaque(false);
 	      backgorund.setSize(new Dimension(frame.getWidth(), frame.getHeight()));
 	        
	 	   frame.add(backgorund, 1);
 	       frame.add(topPanel, 2);
 	       frame.add(middlePanel, 3);
 	       frame.add(bottomPanel, 4);
			topPanel.add(wag);
    		
			MyLabel foodAmmount = new MyLabel();
			foodAmmount.setBounds(100,340,150,20);
			backgorund.add(foodAmmount);

			MyLabel days = new MyLabel();
			days.setBounds(100,360,150,20);
			backgorund.add(days);

			for(int i = 0; i < wagon.getNumOx() - 1; i++) {
				JLabel ox = lblMake.makeLabel("src/game/imgs/ox.png","ox",64,64);
				ox.setBounds(315 - (i * 32),150,64,64);
				topPanel.add(ox);
			}
 	       	 	      
		
		frame.repaint();
	
		Timer timer = new Timer(41, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

			

            if(traveling == true) {
            	
				if(wagon.food > 0){
 

        		miliSecondCound = miliSecondCound + 1;
        		if(miliSecondCound >= 24) {
        			
        			secondCount++;
        			miliSecondCound = 0;
					days.setText("" + secondCount);	
					foodAmmount.setText("Food: " + (wagon.food -= 3) + "lbs");
					
        			allLabels.add(lblMake.randLabel("src/csv/images.csv"));
				}

				if(secondCount > 6 && riverEvent == false) {
					riverLabel = lblMake.makeLabel("src/game/imgs/river.png","for",800, 450);
					allLabels.add(riverLabel);
					riverEvent = true;
				}

				if(secondCount > 10 && fortEvent == false) {
					fortLabel.setName("mid");
					fortLabel = lblMake.makeLabel("src/game/imgs/fort.png","mid",64, 64);
					allLabels.add(fortLabel);
					fortEvent = true;
				}

				if(secondCount > 17 && flagEvent == false) {
					flagLabel = lblMake.makeLabel("src/game/imgs/flag.png","for",800, 450);
					allLabels.add(flagLabel);
					flagEvent = true;

				}

        		if(riverLabel != null) {

        			if(riverLabel.getX() >= -20 && riverDecisionMade == false) { 
        				
        				traveling = false; 
        				riverOptions();
        				
        			}
					
        		}

				if(fortLabel != null) {

        			if(fortLabel.getX() >= 336 && fortDecisionMade == false) { 
        				
        				traveling = false; 
        				fortOptions();
        				
        			}
					
        		}

				if(flagLabel != null) {

        			if(flagLabel.getX() >= -20 && flagDescisionMade == false) { 
        				
						winScreen();
        				traveling = false; 
        				
        				
        			}
					
        		}


				


        			for(int i = 0; i < allLabels.size(); i++) {
            			if(allLabels.get(i).getName().equals("for")) {
							allLabels.get(i).setLayout(null);
            				topPanel.add(allLabels.get(i));
            			}
            			if(allLabels.get(i).getName().equals("mid")) {
							allLabels.get(i).setLayout(null);
            				middlePanel.add(allLabels.get(i));
            			}
            			if(allLabels.get(i).getName().equals("bac")) {
							allLabels.get(i).setLayout(null);
            				bottomPanel.add(allLabels.get(i));
            			} 

            			
            			if(allLabels.get(i).getX() > 900) {
            				allLabels.remove(i);
            			}
            		}
            		
            		
            		for(int i = 0; i < allLabels.size(); i++) {			
            			if(allLabels.get(i).getName().equals("for")) {
            				
            				allLabels.get(i).setBounds(allLabels.get(i).getX() + 5, allLabels.get(i).getY(), allLabels.get(i).getWidth(), allLabels.get(i).getHeight());
            				          			
            				}
            			if(allLabels.get(i).getName().equals("mid")) {
            				
            				allLabels.get(i).setBounds(allLabels.get(i).getX() + 3, allLabels.get(i).getY(), allLabels.get(i).getWidth(), allLabels.get(i).getHeight());
           			
            				}
            			if(allLabels.get(i).getName().equals("bac")) {
            				
            				allLabels.get(i).setBounds(allLabels.get(i).getX() + 1, allLabels.get(i).getY(), allLabels.get(i).getWidth(), allLabels.get(i).getHeight());
            			
            				}

            			frame.repaint();  
            			
            		}            		
        			
 
        			
        		}  else {
					JOptionPane.showMessageDialog(null, "You Have Run Out Of Food! \n You Have Died! \n Game Over!");
            	
			}
			}
            }
        });

        timer.start(); // Start the timer

    }
	
	private static void debugButtons() {
		JButton river = new JButton("River");
		river.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				riverLabel = lblMake.makeLabel("src/game/imgs/river.png","for",800, 450);
    			allLabels.add(riverLabel);

			}
		});
		river.setFont(new Font("Arial", Font.PLAIN, 13));
		river.setBounds(700, 144, 60, 40);
		frame.getContentPane().add(river);
		
		JButton fort = new JButton("Fort");
		fort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
    			allLabels.add(lblMake.makeLabel("src/game/imgs/fort.png","mid",100, 100));

			}
		});
		fort.setFont(new Font("Arial", Font.PLAIN, 13));
		fort.setBounds(700, 186, 60, 40);
		frame.getContentPane().add(fort);

	}

	private static void fortOptions(){
		JButton trade = new JButton("Trade");
		JButton leave = new JButton("Leave");

		MyLabel lblLineOne = new MyLabel();
		lblLineOne.setText("You have come to a fort. You can either trade or leave.");
		lblLineOne.setBounds(110, 300, 550, 30);
		frame.add(lblLineOne);

		MyLabel lblLineTwo = new MyLabel();
		lblLineTwo.setText("The fort has: " + (int)(Math.random() * 10) + " supplies.");
		lblLineTwo.setBounds(110, 320, 550, 30);
		frame.add(lblLineTwo);
 

		trade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fortDecisionMade = true;
				frame.remove(trade);
				frame.remove(leave);
				frame.remove(lblLineOne);
				frame.remove(lblLineTwo);
				traveling = true;
			}
		});
		trade.setFont(new Font("Arial", Font.PLAIN, 13));
		trade.setBounds(150, 260, 60, 40);
		frame.getContentPane().add(trade);
		
		
		leave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fortDecisionMade = true;
				frame.remove(trade);
				frame.remove(leave);
				frame.remove(lblLineOne);
				frame.remove(lblLineTwo);
				traveling = true;
			}
		});
		leave.setFont(new Font("Arial", Font.PLAIN, 13));
		leave.setBounds(240, 260, 60, 40);
		frame.getContentPane().add(leave);
	}
	
	private static void riverOptions() {
		JButton floating = new JButton("Float");
		JButton ford = new JButton("Ford");

		MyLabel lblLineOne = new MyLabel();
		lblLineOne.setText("You have come to a river. You can either float across or ford the river.");
		lblLineOne.setBounds(110, 300, 550, 30);
		frame.add(lblLineOne);

		MyLabel lblLineTwo = new MyLabel();
		lblLineTwo.setText("The river's speed is: " + (int)(Math.random() * 10) + " mph.");
		lblLineTwo.setBounds(110, 320, 550, 30);
		frame.add(lblLineTwo);
 

		ford.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				riverDecisionMade = true;
				frame.remove(floating);
				frame.remove(ford);
				frame.remove(lblLineOne);
				frame.remove(lblLineTwo);
				traveling = true;
			}
		});
		ford.setFont(new Font("Arial", Font.PLAIN, 13));
		ford.setBounds(150, 260, 60, 40);
		frame.getContentPane().add(ford);
		
		
		floating.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				riverDecisionMade = true;
				frame.remove(floating);
				frame.remove(ford);
				frame.remove(lblLineOne);
				frame.remove(lblLineTwo);
				traveling = true;
			}
		});
		floating.setFont(new Font("Arial", Font.PLAIN, 13));
		floating.setBounds(240, 260, 60, 40);
		frame.getContentPane().add(floating);


		
	}

	public static void wagonScreen(){
		frame.getContentPane().removeAll();
		frame.repaint();

		JLabel title = new JLabel("General Store");
		title.setFont(new Font("Arial", Font.PLAIN, 16));
		title.setBounds(370, 25, 130, 20);
		frame.add(title);


		JPanel store = new JPanel();
		store.setBackground(new Color(93, 226, 231));
		store.setOpaque(true);
		store.setBounds(300,50,250,300);
		store.setLayout(null);
		frame.add(store);

		JPanel gre = new JPanel();
		gre.setBackground(new Color(255, 255, 255));
		gre.setOpaque(true);
		gre.setBounds(290,20,270,340);
		frame.add(gre);

		
		JTextArea blerb = new JTextArea("You have $500 to spend at the store. \nYou can buy food, oxen, clothes, and other supplies. \nYou can also see the status of your wagon and your party members.");
		blerb.setFont(new Font("Arial", Font.PLAIN, 13));
		blerb.setBounds(20, 50, 260, 200);
		blerb.setLineWrap(true); 
		blerb.setWrapStyleWord(true); 
		blerb.setEditable(false);
		frame.add(blerb);

		JLabel money = new JLabel("Money: $500");
		money.setFont(new Font("Arial", Font.PLAIN, 13));
		money.setBounds(20, 20, 100, 20);
		store.add(money);

		JLabel food = new JLabel("Food: 0lbs");
		food.setFont(new Font("Arial", Font.PLAIN, 13));
		food.setBounds(80, 50, 80, 30);
		store.add(food);

		JButton btnFoodIncrease = new JButton("+50 lbs");
		btnFoodIncrease.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(crew.money > 0){
				wagon.food = wagon.food + 50;
				crew.money = crew.money - 50;
				money.setText("Money: $" + crew.money);
				food.setText("Food: " + wagon.food + "lbs");
			} else {
				JOptionPane.showMessageDialog(null, "You don't have enough money");	
			}
			}
		});

		btnFoodIncrease.setFont(new Font("Arial", Font.PLAIN, 13));
		btnFoodIncrease.setBounds(6, 50, 80, 30);
		store.add(btnFoodIncrease);

		JButton btnFoodDecrease = new JButton("-50 lbs");
		btnFoodDecrease.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(wagon.food > 0){
				crew.money = crew.money + 50;
				money.setText("Money: $" + crew.money);
				wagon.food = wagon.food - 50;
				food.setText("Food: " + wagon.food + "lbs");
			}
			else {
				JOptionPane.showMessageDialog(null, "You can't have negative food");	
			}
			}
		});

		

		btnFoodDecrease.setFont(new Font("Arial", Font.PLAIN, 13));
		btnFoodDecrease.setBounds(150, 50, 80, 30);
		store.add(btnFoodDecrease);

		

		JCheckBox ox1 = new JCheckBox("+1 Oxen");
		ox1.addActionListener(e -> {

			if (ox1.isSelected()) {
				wagon.setOxNumber(1);
			} else { 
				wagon.setOxNumber(-1);
			}
		});
		ox1.setBounds(6, 200, 90, 20);
		store.add(ox1);

		JCheckBox ox2 = new JCheckBox("+1 Oxen");
		ox2.addActionListener(e -> {
            		
			if (ox2.isSelected()) {
				wagon.setOxNumber(1);
			} else { 
				wagon.setOxNumber(-1);
			}

		});
		ox2.setBounds(85, 200, 90, 20);
		store.add(ox2);

		JCheckBox ox3 = new JCheckBox("+1 Oxen");
		ox3.addActionListener(e -> {

			if (ox3.isSelected()) {
				wagon.setOxNumber(1);
			} else { 
				wagon.setOxNumber(-1);
			}
		});
		ox3.setBounds(165, 200, 90, 20);
		store.add(ox3);

		JButton btnNewButton = new JButton("Start Journey");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameScreen();
			}
		});

		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 13));
		btnNewButton.setBounds(140, 270, 110, 30);
		store.add(btnNewButton);


	}

	private static void storeScreen() {

		JPanel store = new JPanel();
		store.setBackground(new Color(93, 226, 231));
		store.setOpaque(true);
		store.setBounds(300,50,250,300);
		frame.add(store , 0);

		JPanel gre = new JPanel();
		gre.setBackground(new Color(255, 255, 255));
		gre.setOpaque(true);
		gre.setBounds(290,40,270,320);
		frame.add(gre , 1);


		String[] thing = null;
        int count = 0;
        int index = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("src/csv/items.csv"))) {
            String line;
            //loops for every line in the csv file with the items
            while ((line = br.readLine()) != null) {
            	Item itm = new Item(); // item object read in from the csv
            	
            	thing = line.split(COMMA_DELIMITER); // puts all the comma seperated values into an array per line
            	JCheckBox chckbxNewCheckBox = new JCheckBox(thing[0] +", "+ thing[1] + "lbs"); // makes the check box
            	
            	String weight = thing[1]; //weight of the item
            	
            	itmArray[index] = itm;
            	
            	
            	//This is the code that checks if the check box has been checked or not
            	chckbxNewCheckBox.addActionListener(e -> {
            		
            		
            		//if the check box is selected the wieght is updated
            	    if (chckbxNewCheckBox.isSelected()) {
            	    
            	    } else { 
            	    
            	    }
            	});
    			chckbxNewCheckBox.setBounds(6, 6+(count * 15), 250, 23);
    			checkBoxArray[index] = chckbxNewCheckBox;
    			store.add(checkBoxArray[index]);
                count++;
                index++;
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

		JButton btnNewButton = new JButton("Exit Store");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.remove(gre);
				frame.remove(store);
				frame.repaint();
			}
		});

		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 13));
		btnNewButton.setBounds(160, 250, 75, 40);
		store.add(btnNewButton);

		frame.repaint();
	}

	private static void winScreen(){
		JOptionPane.showMessageDialog(null, "You Have Won! \n You Have Made It To Oregon! \n Congratulations!");
	}

}
