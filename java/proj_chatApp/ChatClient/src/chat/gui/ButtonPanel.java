package chat.gui;

import chat.User;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

/**
 * User list panel, displays connected users, Current username and Logout button
 *
 * @author Gary
 */
public class ButtonPanel extends JPanel {

    /**
     * Parent frame ClientGUI
     */
    public final ClientGUI parent;

    private RoundPanel userNameLabel;
    private RoundPanel logoutButton;
    private RoundPanel addroomButton;
    private JPanel listPanel;
    private JScrollPane scrollPane;
    private final Color BACKGROUND = new Color(244, 245, 246);
    private final Color BUTTON_COLOR = new Color(45, 54, 73);
    private final Color BUTTON_COLOR_ON_HOVER = new Color(130, 145, 172);
    private final Color USER_COLOR = new Color(244, 245, 246);
    private final Color USER_TEXT_COLOR = new Color(0,0,0);
    private final Color BUTTON_TEXT_COLOR = new Color(255, 255, 255);
    private final Dimension SIZE = new Dimension(150, 0);
    private final Dimension USER_NAME_LABEL_SIZE = new Dimension(100, 30);
    private final Dimension SCROLL_PANE_SIZE = new Dimension(0, 50);
    private final Dimension LOGOUT_BUTTON_ARC = new Dimension(0, 0);
    private final Dimension LOGOUT_BUTTON_SIZE = new Dimension(150, 25);
    private final Dimension ARC = new Dimension(20, 20);
    private final Font DEFAULT_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
    private final Insets SCROLL_PANE_INSETS = new Insets(8, 8, 8, 8);
    private final Insets BUTTON_INSETS = new Insets(0, 0, 0, 0);
    private final EmptyBorder EMPTY_BORDER = new EmptyBorder(SCROLL_PANE_INSETS);
    private final String LOGOUT_BUTTON_TEXT = "Logout";
    private final String ADDROOM_BUTTON_TEXT = "Create Room";

    /**
     * Init ButtonPanel
     * @param parent Parent frame ClientGUI
     */
    public ButtonPanel(JFrame parent) {
        this.parent = (ClientGUI) parent;

        init();
        addListeners();
    }

    /**
     * Init GUI components of this panel
     */
    private void init() {
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        setLayout(gridbag);
        setBackground(BACKGROUND);
        setPreferredSize(SIZE);

        //Component used to logout of chat
        logoutButton = new RoundPanel(LOGOUT_BUTTON_ARC, new GridBagLayout());
        logoutButton.setOpaque(false);
        logoutButton.setForeground(BUTTON_COLOR);
        logoutButton.setBackground(BUTTON_COLOR);
        JLabel jb2 = new JLabel(LOGOUT_BUTTON_TEXT);
        jb2.setFont(DEFAULT_FONT);
        jb2.setForeground(BUTTON_TEXT_COLOR);
        logoutButton.add(jb2);
        logoutButton.setBorder(EMPTY_BORDER);
        logoutButton.setPreferredSize(LOGOUT_BUTTON_SIZE);
        
        //Component used to add room
        
        addroomButton = new RoundPanel(LOGOUT_BUTTON_ARC, new GridBagLayout());
        addroomButton.setOpaque(false);
        addroomButton.setForeground(BUTTON_COLOR);
        addroomButton.setBackground(BUTTON_COLOR);
        JLabel jb3 = new JLabel(ADDROOM_BUTTON_TEXT);
        jb3.setFont(DEFAULT_FONT);
        jb3.setForeground(BUTTON_TEXT_COLOR);
        addroomButton.add(jb3);
        addroomButton.setBorder(EMPTY_BORDER);
        addroomButton.setPreferredSize(LOGOUT_BUTTON_SIZE);
       
        
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;
        c.insets = BUTTON_INSETS;
        add(addroomButton, c);
        
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;
        c.insets = BUTTON_INSETS;
        add(logoutButton, c);


    }

    private void addListeners() {
        //User notification of mouse events for the logout button
        logoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                logoutButton.setBackground(BUTTON_COLOR_ON_HOVER);
                logoutButton.setForeground(BUTTON_COLOR_ON_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logoutButton.setBackground(BUTTON_COLOR);
                logoutButton.setForeground(BUTTON_COLOR);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    parent.logout();
                } catch (Exception ex) {
                    ((MessagePanel) getParent()).getChatPanel().addServerMessage("LOGOUT FAILED");
                    System.out.println(getClass() + ".logoutPanel.mousePressed():ConnectionError");
                }
            }
        });
    }

  
}
