package cwk4;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/**
 * Provide a GUI interface for the game
 *
 * @author A.A.Marczyk
 * @version 20/10/23
 */
public class GameGUI {
    private WIN gp = new SpaceWars("Horatio");
    private JFrame myFrame = new JFrame("Game GUI");
    private JPanel centerPanel = new JPanel();


    private JButton activateFleetBtn = new JButton("Activate Fleet");
    private JButton retreatFleetBtn = new JButton("Retreat Fleet");
    private JLabel fleetStrengthLabel = new JLabel("Your Fleet Strength: 0");


    private JTextArea listing = new JTextArea();
    private JLabel codeLabel = new JLabel();
    private JButton fightBtn = new JButton("Fight");
    private JPanel eastPanel = new JPanel();
    private JButton newGameBtn = new JButton("New Game");
    private JButton loadGameBtn = new JButton("Load Game");
    private JButton exitBtn = new JButton("Exit");
    private JLabel creditsLabel = new JLabel("Credits: 0");

    public GameGUI() {
        makeFrame();
        makeMenuBar(myFrame);
        showButtons();
    }

    /**
     * Create the main frame's menu bar.
     */
    private void makeMenuBar(JFrame frame) {
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);


        // create the Save Game button
        JButton saveGameButton = new JButton("Save Game");
        menubar.add(saveGameButton);

        // Add an ActionListener to the Save Game button
        saveGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gp.saveGame("olenka.txt");
                JOptionPane.showMessageDialog(myFrame, "Game saved to olenka.txt");
            }
        });
    }

    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame() {
        myFrame.setLayout(new BorderLayout());
        myFrame.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setLayout(new GridLayout(3, 1));
        centerPanel.add(newGameBtn);
        centerPanel.add(loadGameBtn);
        centerPanel.add(exitBtn);
        creditsLabel.setVisible(false);

        // Create a JPanel for the westPanel
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));

        // Add creditsLabel and fleetStrengthLabel to the westPanel
        westPanel.add(creditsLabel);
        westPanel.add(fleetStrengthLabel);

        // Add the westPanel to the BorderLayout
        myFrame.add(westPanel, BorderLayout.WEST);

        // Make the fleetStrengthLabel invisible initially
        fleetStrengthLabel.setVisible(false);

        myFrame.add(listing, BorderLayout.NORTH);
        listing.setVisible(false);
        myFrame.add(eastPanel, BorderLayout.EAST);
        eastPanel.setLayout(new GridLayout(4, 1));
        eastPanel.add(fightBtn);
        fightBtn.addActionListener(new FightBtnHandler());
        eastPanel.setVisible(false);

        myFrame.setPreferredSize(new Dimension(800, 600)); // Change the numbers to the desired width and height
        myFrame.pack();
        myFrame.setLocationRelativeTo(null); // Center the window on the screen
        myFrame.setVisible(true);
    }

    private String fighting(int code) {
        switch (code) {
            case 0:
                return "Fight won";
            case 1:
                return "Fight lost as no suitable force available";
            case 2:
                return "Fight lost on battle strength, force destroyed";
            case 3:
                return "fight is lost and admiral completely defeated ";
        }
        return " no such fight ";
    }
    private void checkGameOverAndReturnToMainMenu() {
        String fleetStrengthText = fleetStrengthLabel.getText();
        int fleetStrength = Integer.parseInt(fleetStrengthText.split(": ")[1]);

        String creditsText = creditsLabel.getText();
        int credits = Integer.parseInt(creditsText.split(": ")[1]);

        if (fleetStrength == 0 && credits <= 0) {
            JOptionPane.showMessageDialog(myFrame, "Game Over");
            myFrame.dispose(); // Close the window
            System.exit(0); // Stop the program
        }
    }



    private void updateFleetStrengthLabel() {
        String fleetInfo = gp.getASFleet();
        String[] lines = fleetInfo.split("\n");
        int totalBattleStrength = 0;

        for (String line : lines) {
            if (line.contains("Battle strength")) {
                int battleStrength = Integer.parseInt(line.split(":")[1].trim());
                totalBattleStrength += battleStrength;
            }
        }

        fleetStrengthLabel.setText("Your Fleet Strength: " + totalBattleStrength);
        fleetStrengthLabel.setVisible(true);

    }



    private class FightBtnHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int result = -1;
            String inputValue = JOptionPane.showInputDialog("Fight number ?: ");
            int num = Integer.parseInt(inputValue);
            result = gp.doBattle(num);
            JOptionPane.showMessageDialog(myFrame, fighting(result));
        }
    }
    private String getBattleInfo(String[] lines, int startIndex) {
        StringBuilder battleInfo = new StringBuilder("<html>");
        for (int i = startIndex; i < startIndex + 5 && i < lines.length; i++) {
            battleInfo.append(lines[i].trim());
            if (i < startIndex + 4) {
                battleInfo.append("<br>");
            }
        }
        battleInfo.append("</html>");
        return battleInfo.toString();
    }
    private String activation(int code)
    {
        switch (code)
        {
            case 0: JOptionPane.showMessageDialog(myFrame, "force is activated"); return "force is activated" ;
            case 1: JOptionPane.showMessageDialog(myFrame, "force is not in the UFFDock"); return "force is not in the UFFDock";
            case 2: JOptionPane.showMessageDialog(myFrame, "not enough money"); return "not enough money";
            case 3:return "no such force";
            default: return "Error";
        }
    }
    private void showButtons() {
        newGameBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                activateFleetBtn.setVisible(true);
                retreatFleetBtn.setVisible(true);
                String admiralName = JOptionPane.showInputDialog("Enter admiral name: ");
                gp = new SpaceWars(admiralName);

                JOptionPane.showMessageDialog(myFrame, "New game started!");
                centerPanel.setVisible(false);
                loadGameBtn.setVisible(false);
                exitBtn.setVisible(false);
                creditsLabel.setText(String.format("Credits: %s", gp.getWarchest()));

                creditsLabel.setVisible(true);  // make the credits label visible
                fleetStrengthLabel.setVisible(true);
                // Create buttons for each battle number
                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

                JScrollPane scrollPane = new JScrollPane(buttonPanel);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                scrollPane.setPreferredSize(new Dimension(600, 300));

                String allBattles = gp.getAllBattles();
                String[] lines = allBattles.split("\n");


                for (int i = 0; i < lines.length; i++) {
                    String line = lines[i].trim();
                    if (line.startsWith("Battle number")) {
                        String battleNumber = line.split(":")[1].trim();
                        int battleNum = Integer.parseInt(battleNumber);

                        String battleInfo = getBattleInfo(lines, i);
                        JButton btn = new JButton(battleInfo);
                        btn.setPreferredSize(new Dimension(btn.getPreferredSize().width, 100));
                        btn.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                int result = -1;
                                result = gp.doBattle(battleNum);
                                JOptionPane.showMessageDialog(myFrame, fighting(result));
                                creditsLabel.setText(String.format("Credits: %s", gp.getWarchest()));
                                updateFleetStrengthLabel();
                                checkGameOverAndReturnToMainMenu();

                            }
                        });
                        buttonPanel.add(btn);

                        // Increment the loop counter to account for the 4 additional lines of battle information.
                        i += 4;
                    }
                }
                JPanel bottomCenterPanel = new JPanel();
                bottomCenterPanel.setLayout(new BoxLayout(bottomCenterPanel, BoxLayout.X_AXIS));

                bottomCenterPanel.add(Box.createHorizontalGlue());
                bottomCenterPanel.add(activateFleetBtn);
                bottomCenterPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Add some spacing between the buttons
                bottomCenterPanel.add(retreatFleetBtn);
                bottomCenterPanel.add(Box.createHorizontalGlue());
                myFrame.add(bottomCenterPanel, BorderLayout.SOUTH);
                activateFleetBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String allForces = gp.getAllForces();
                        String[] lines = allForces.split("\n");

                        // Create a new JFrame to display the available ships
                        JFrame activateFleetFrame = new JFrame("Activate Fleet");
                        activateFleetFrame.setLayout(new BorderLayout());

                        JPanel shipsPanel = new JPanel();
                        shipsPanel.setLayout(new BoxLayout(shipsPanel, BoxLayout.Y_AXIS));

                        JScrollPane scrollPane = new JScrollPane(shipsPanel);
                        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                        scrollPane.setPreferredSize(new Dimension(600, 300));

                        for (int i = 0; i < lines.length; i++) {
                            String line = lines[i].trim();
                            if (line.startsWith("Fleet reference")) {
                                StringBuilder shipInfo = new StringBuilder("<html>");
                                boolean forceIsActive = false;
                                for (int j = i; j < i + 9 && j < lines.length; j++) {
                                    shipInfo.append(lines[j].trim());
                                    if (j < i + 8) {
                                        shipInfo.append("<br>");
                                    }
                                    if (lines[j].contains("Force state: Active")) {
                                        forceIsActive = true;
                                    }
                                }
                                shipInfo.append("</html>");

                                JButton shipButton = new JButton(shipInfo.toString());
                                shipButton.setPreferredSize(new Dimension(shipButton.getPreferredSize().width, 150));

                                shipButton.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        // Implement the activation logic for the ship here
                                        int res = gp.activateForce(line.split(":")[1].trim());
                                        if (res==0){
                                            shipButton.setVisible(false);
                                        }
                                        System.out.println(activation(res));
                                        creditsLabel.setText(String.format("Credits: %s", gp.getWarchest()));
                                        updateFleetStrengthLabel();
                                        checkGameOverAndReturnToMainMenu();

                                    }
                                });
                                if (forceIsActive) {
                                    shipButton.setVisible(false);
                                }
                                shipsPanel.add(shipButton);
                                i += 8; // Increment the loop counter to account for the additional lines of ship information.
                            }

                        }

                        activateFleetFrame.add(scrollPane, BorderLayout.CENTER);
                        activateFleetFrame.pack();
                        activateFleetFrame.setVisible(true);
                    }
                });
                retreatFleetBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String allForces = gp.getASFleet();
                        String[] lines = allForces.split("\n");

                        // Create a new JFrame to display the available ships
                        JFrame activateFleetFrame = new JFrame("Active Fleet");
                        activateFleetFrame.setLayout(new BorderLayout());

                        JPanel shipsPanel = new JPanel();
                        shipsPanel.setLayout(new BoxLayout(shipsPanel, BoxLayout.Y_AXIS));

                        JScrollPane scrollPane = new JScrollPane(shipsPanel);
                        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                        scrollPane.setPreferredSize(new Dimension(600, 300));

                        for (int i = 0; i < lines.length; i++) {
                            String line = lines[i].trim();
                            if (line.startsWith("Fleet reference")) {
                                StringBuilder shipInfo = new StringBuilder("<html>");
                                for (int j = i; j < i + 9 && j < lines.length; j++) {
                                    shipInfo.append(lines[j].trim());
                                    if (j < i + 8) {
                                        shipInfo.append("<br>");
                                    }
                                }
                                shipInfo.append("</html>");
                                JButton shipButton = new JButton(shipInfo.toString());
                                shipButton.setPreferredSize(new Dimension(shipButton.getPreferredSize().width, 150));
                                shipButton.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        // Implement the activation logic for the ship here
                                        System.out.println(gp.getASFleet());
                                        gp.recallForce(line.split(":")[1].trim());
                                        shipButton.setVisible(false);

                                        creditsLabel.setText(String.format("Credits: %s", gp.getWarchest()));
                                        updateFleetStrengthLabel();
                                        checkGameOverAndReturnToMainMenu();
                                    }
                                });

                                shipsPanel.add(shipButton);
                                i += 8; // Increment the loop counter to account for the additional lines of ship information.
                            }
                        }

                        activateFleetFrame.add(scrollPane, BorderLayout.CENTER);
                        activateFleetFrame.pack();
                        activateFleetFrame.setVisible(true);
                    }
                });

                centerPanel.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.weightx = 1;
                gbc.weighty = 1;
                gbc.fill = GridBagConstraints.NONE;
                centerPanel.add(scrollPane, gbc);
                newGameBtn.setVisible(false);
                centerPanel.revalidate();
                centerPanel.repaint();
                centerPanel.setVisible(true);
            }
        });

        loadGameBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(myFrame, "Restore from file");
                gp = gp.restoreGame("battles.txt");
                System.out.println(gp.toString());
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        new GameGUI();
    }
}

