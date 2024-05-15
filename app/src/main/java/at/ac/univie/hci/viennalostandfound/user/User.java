package at.ac.univie.hci.viennalostandfound.user;

public class User {
    private final String name;
    private final int profilePictureId;

    public User(String name, int profilePictureId) {
        this.name = name;
        this.profilePictureId = profilePictureId;
    }

    public String getName() {
        return name;
    }

    public int getProfilePictureId() {
        return profilePictureId;
    }
}
