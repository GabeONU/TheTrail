package game;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
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
	private static boolean riverDecisionMade;
	private static int fortIndex = -1;
	private static Wagon wag = new Wagon();
	
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
				gameScreen();
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 19));
		btnNewButton.setBounds(173, 101, 436, 199);
		frame.getContentPane().add(btnNewButton);
		

		
		
	}

	public static void gameScreen() {
    	frame.getContentPane().removeAll();
		frame.repaint();
		/* 
		allLabels = lblMake.makeLabels("src/csv/images.csv");
		for(int i = 0; i < allLabels.size(); i++) {
			frame.add(allLabels.get(i));
		}
		*/
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
    		frame.getContentPane().add(wag);
    		
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

			MyLabel days = new MyLabel();
			days.setBounds(100,360,150,20);
			backgorund.add(days);
 	       	 	      
		
		frame.repaint();
	
		Timer timer = new Timer(41, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

			

            if(traveling == true) {
            	
				
 
        		miliSecondCound = miliSecondCound + 1;
        		if(miliSecondCound >= 24) {
        			
        			secondCount++;
        			miliSecondCound = 0;
					days.setText("" + secondCount);	
        			allLabels.add(lblMake.randLabel("src/csv/images.csv"));
				}

        		//add a refrence to the river pos
        		if(riverLabel != null) {

        			if(riverLabel.getX() >= -20 && riverDecisionMade == false) { 
        				
        				traveling = false; 
        				riverOptions();
        				
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
    			fortIndex = allLabels.size() -1;
			}
		});
		fort.setFont(new Font("Arial", Font.PLAIN, 13));
		fort.setBounds(700, 186, 60, 40);
		frame.getContentPane().add(fort);

		JLabel speedNum = new JLabel();
		speedNum.setBounds(700,270, 60,40);
		frame.getContentPane().add(speedNum);

		JButton speedIncrese = new JButton("Increse");
		speedIncrese.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
    		wag.changePace(1);
			
			}
		});
		speedIncrese.setFont(new Font("Arial", Font.PLAIN, 13));
		speedIncrese.setBounds(700, 228, 60, 40);
		frame.getContentPane().add(speedIncrese);
	}
	
	private static void riverOptions() {
		JButton ford = new JButton("Ford");
		ford.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				riverDecisionMade = true;
			}
		});
		ford.setFont(new Font("Arial", Font.PLAIN, 13));
		ford.setBounds(150, 260, 60, 40);
		frame.getContentPane().add(ford);
		
		JButton floating = new JButton("Float");
		floating.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				riverDecisionMade = true;
			}
		});
		floating.setFont(new Font("Arial", Font.PLAIN, 13));
		floating.setBounds(240, 260, 60, 40);
		frame.getContentPane().add(floating);
		
	}

	private static void storeScreen() {

		System.out.println("Store Screen");

		JPanel store = new JPanel();
		store.setBackground(Color.BLUE);
		store.setOpaque(true);
		store.setBounds(300,50,250,300);
		frame.add(store , 5);

		JPanel gre = new JPanel();
		gre.setBackground(Color.GREEN);
		gre.setOpaque(true);
		gre.setBounds(290,40,270,320);
		frame.add(gre , 6);

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

		

}
