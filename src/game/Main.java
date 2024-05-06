package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

    private static Store store;
    private static Person player;

    public static void main(String[] args) {
        store = new Store();
        store.itemsStore.add(new Item("Item 1", 10, day, day));
        store.itemsStore.add(new Item("Item 2", 20, day, day));
        player = new Person("Player", 50);

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

    public Main() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 800, 450);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

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

        allLabels = lblMake.makeLabels("src/csv/images.csv");
        for (int i = 0; i < allLabels.size(); i++) {
            frame.add(allLabels.get(i));
        }

        JButton storeButton = new JButton("Enter Store");
        storeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openStore();
            }
        });
        storeButton.setFont(new Font("Arial", Font.PLAIN, 13));
        storeButton.setBounds(700, 10, 100, 30);
        frame.getContentPane().add(storeButton);

        JButton btnNewButton = new JButton("Travel");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                traveling = !traveling;
            }
        });
        btnNewButton.setFont(new Font("Arial", Font.PLAIN, 13));
        btnNewButton.setBounds(700, 60, 60, 40);
        frame.getContentPane().add(btnNewButton);

        JButton btnDebug = new JButton("Debug");
        btnDebug.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                debugButtons();
            }
        });
        btnDebug.setFont(new Font("Arial", Font.PLAIN, 13));
        btnDebug.setBounds(700, 102, 60, 40);
        frame.getContentPane().add(btnDebug);

        JLabel wagonLabel = new JLabel("Wagon");
        wagonLabel = lblMake.makeLabel("src/game/imgs/Wagon.png", "wag", 64, 64);
        wagonLabel.setBounds(360, 150, 64, 64);
        frame.getContentPane().add(wagonLabel);

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setSize(new Dimension(frame.getWidth(), frame.getHeight()));

        JPanel middlePanel = new JPanel();
        middlePanel.setOpaque(false);
        middlePanel.setSize(new Dimension(frame.getWidth(), frame.getHeight()));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setSize(new Dimension(frame.getWidth(), frame.getHeight()));

        JPanel background = new JPanel();
        background.setOpaque(false);
        background.setSize(new Dimension(frame.getWidth(), frame.getHeight()));

        frame.add(background);
        frame.add(topPanel);
        frame.add(middlePanel);
        frame.add(bottomPanel);

        frame.repaint();

        Timer timer = new Timer(41, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (traveling) {
                    miliSecondCound++;
                    if (miliSecondCound >= 24) {
                        secondCount++;
                        miliSecondCound = 0;
                        allLabels.add(lblMake.randLabel("src/csv/images.csv"));
                    }

                    if (riverLabel != null) {
                        if (riverLabel.getX() >= -20 && !riverDecisionMade) {
                            traveling = false;
                            riverOptions();
                        }
                    }

                    for (int i = 0; i < allLabels.size(); i++) {
                        if (allLabels.get(i).getText().equals("for")) {
                            topPanel.add(allLabels.get(i));
                        }
                        if (allLabels.get(i).getText().equals("mid")) {
                            middlePanel.add(allLabels.get(i));
                        }
                        if (allLabels.get(i).getText().equals("bac")) {
                            bottomPanel.add(allLabels.get(i));
                        }

                        if (allLabels.get(i).getX() > 900) {
                            allLabels.remove(i);
                        }
                    }

                    for (int i = 0; i < allLabels.size(); i++) {
                        if (allLabels.get(i).getText().equals("for")) {
                            allLabels.get(i).setBounds(allLabels.get(i).getX() + 5, allLabels.get(i).getY(), allLabels.get(i).getWidth(), allLabels.get(i).getHeight());
                        }
                        if (allLabels.get(i).getText().equals("mid")) {
                            allLabels.get(i).setBounds(allLabels.get(i).getX() + 3, allLabels.get(i).getY(), allLabels.get(i).getWidth(), allLabels.get(i).getHeight());
                        }
                        if (allLabels.get(i).getText().equals("bac")) {
                            allLabels.get(i).setBounds(allLabels.get(i).getX() + 1, allLabels.get(i).getY(), allLabels.get(i).getWidth(), allLabels.get(i).getHeight());
                        }

                        frame.repaint();
                    }
                }
            }
        });

        timer.start(); // Start the timer
    }

    private static void openStore() {
        StorePopup storePopup = new StorePopup(store, player);
        storePopup.setVisible(true);
    }

    private static void debugButtons() {
        JButton river = new JButton("River");
        river.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                riverLabel = lblMake.makeLabel("src/game/imgs/river.png", "for", 800, 450);
                allLabels.add(riverLabel);
            }
        });
        river.setFont(new Font("Arial", Font.PLAIN, 13));
        river.setBounds(700, 144, 60, 40);
        frame.getContentPane().add(river);

        JButton fort = new JButton("Fort");
        fort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                allLabels.add(lblMake.makeLabel("src/game/imgs/fort.png", "mid", 100, 100));
                fortIndex = allLabels.size() - 1;
            }
        });
        fort.setFont(new Font("Arial", Font.PLAIN, 13));
        fort.setBounds(700, 186, 60, 40);
        frame.getContentPane().add(fort);

        JLabel speedNum = new JLabel();
        speedNum.setBounds(700, 270, 60, 40);
        frame.getContentPane().add(speedNum);

        JButton speedIncrease = new JButton("Increase");
        speedIncrease.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wag.changePace(1);
            }
        });
        speedIncrease.setFont(new Font("Arial", Font.PLAIN, 13));
        speedIncrease.setBounds(700, 228, 60, 40);
        frame.getContentPane().add(speedIncrease);
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
}

