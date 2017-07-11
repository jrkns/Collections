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
public class UserListPanel extends JPanel {

    /**
     * Parent frame ClientGUI
     */
    public final ClientGUI parent;

    private RoundPanel userNameLabel;
    private JPanel listPanel;
    private JScrollPane scrollPane;
    private final Color BACKGROUND = new Color(244, 245, 246);
    private final Color USER_COLOR = new Color(45, 54, 73);
    private final Color USER_TEXT_COLOR = new Color(255, 255, 255);
    private final Dimension SIZE = new Dimension(150, 0);
    private final Dimension USER_NAME_LABEL_SIZE = new Dimension(100, 50);
    private final Dimension SCROLL_PANE_SIZE = new Dimension(0, 50);
    private final Dimension LOGOUT_BUTTON_ARC = new Dimension(0, 0);
    private final Dimension ARC = new Dimension(20, 20);
    private final Font DEFAULT_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
    private final Font USERNAME_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 20);
    private final Insets SCROLL_PANE_INSETS = new Insets(8, 8, 8, 8);
    private final Insets BUTTON_INSETS = new Insets(0, 0, 0, 0);
    private final EmptyBorder EMPTY_BORDER = new EmptyBorder(SCROLL_PANE_INSETS);

    /**
     * Init UserListPanel
     * @param parent Parent frame ClientGUI
     */
    public UserListPanel(JFrame parent) {
        this.parent = (ClientGUI) parent;

        init();
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

        //Component that displays this logged in user
        userNameLabel = new RoundPanel(LOGOUT_BUTTON_ARC, new GridBagLayout());
        userNameLabel.setOpaque(false);
        userNameLabel.setForeground(USER_COLOR);
        userNameLabel.setBackground(USER_COLOR);
        JLabel jb = new JLabel("");
        jb.setFont(USERNAME_FONT);
        jb.setForeground(USER_TEXT_COLOR);
        userNameLabel.add(jb);
        userNameLabel.setBorder(EMPTY_BORDER);
        userNameLabel.setPreferredSize(USER_NAME_LABEL_SIZE);

        //List panel is used to contain all connected users
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(BACKGROUND);
        
        //Allow scrolling for the list panel
        scrollPane = new JScrollPane(
                listPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBackground(BACKGROUND);
        scrollPane.setBorder(EMPTY_BORDER);
        scrollPane.setPreferredSize(SCROLL_PANE_SIZE);

        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        c.insets = BUTTON_INSETS;
        add(userNameLabel, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = GridBagConstraints.RELATIVE;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        add(scrollPane, c);

    }


    /**
     * Method is used to find any user that aren't connected anymore but the
     * list of users is still displaying they are connected. In which case
     * remove them.
     */
    private boolean isInArray(String user, Component[] array) {
        for (int i = 0; i < array.length; i++) {
            String oldUser = ((UserListItem) array[i]).getUsername();

            if (user.equals(oldUser)) {
                //Found match!
                return true;
            }
        }

        //Wasn't found
        return false;
    }

    /**
     * Method used to find users that are connected but aren't in the local 
     * list of users, in which case add a new user label.
     */
    private boolean isInList(String user, List<User> list) {
        for (int i = 0; i < list.size(); i++) {
            String newUser = list.get(i).getUsername();

            if (user.equals(newUser)) {
                //Found match!
                return true;
            }
        }

        //Wasn't found
        return false;
    }

    /**
     * Use this to update the username component.
     *
     * @param userName Username to replace previous.
     */
    public void updateUserName(String userName) {
        ((JLabel) userNameLabel.getComponent(0)).setText(userName);
    }

    /**
     * Add and remove users from the list of connected users to match the
     * servers connected users.
     * @param newList New list of connected users.
     */
    public void updateListPanel(List<User> newList) {
        Component[] oldList = listPanel.getComponents();

        //Remove users who have logged out
        for (Component oldLabel : oldList) {
            String user = ((UserListItem) oldLabel).getUsername();
            if (isInList(user, newList)) {
                //User is contained in newU do nothing
            } else {
                //Remove JLabel
                listPanel.remove(oldLabel);
                ((MessagePanel) getParent()).getChatPanel().addServerMessage(((UserListItem) oldLabel).getUsername() + " left.");
            }
        }

        //Add users who have recently joined
        for (User newUser : newList) {
            String user = newUser.getUsername();
            if (isInArray(user, oldList)) {
                //User is contained in newU do nothing
            } else if (!user.equals(parent.getUserName())) {
                //Add new user
                addUserItem(user);
            }
        }

        scrollPane.invalidate();
        scrollPane.validate();
        scrollPane.repaint();
    }

    /**
     * Get the list of local connected users.
     * @return Panel containing all connected user items.
     */
    protected JPanel getListPanel() {
        return listPanel;
    }

    /**
     * Add a GUI component that shows that a user is connected to the server.
     */
    private void addUserItem(String user) {
        UserListItem item = new UserListItem(ARC, user);

        listPanel.add(item);
        ((MessagePanel) getParent()).getChatPanel().addServerMessage(user + " joined.");
    }

    /**
     * Get the selected users of the user list panel.
     * @return All selected users.
     */
    protected ArrayList<String> getSelectedUsers() {
        ArrayList<String> arr = new ArrayList<>();

        for (Component item : listPanel.getComponents()) {
            if (((UserListItem) item).isSelected()) {
                arr.add(((UserListItem) item).getUsername());
            }
        }

        return arr;
    }
}
