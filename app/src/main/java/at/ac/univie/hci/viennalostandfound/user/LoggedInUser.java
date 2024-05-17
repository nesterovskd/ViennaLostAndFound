package at.ac.univie.hci.viennalostandfound.user;

public class LoggedInUser {
    private static User loggedInUser;
    private boolean loggedInState = false;
    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        LoggedInUser.loggedInUser = loggedInUser;
    }

    public void setLoggedInState(boolean state){this.loggedInState=state;}
    public boolean getLoggedInState() {return loggedInState;}
}
