package at.ac.univie.hci.viennalostandfound.user;

public class LoggedInUser {
    private static User loggedInUser;

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        LoggedInUser.loggedInUser = loggedInUser;
    }
}
