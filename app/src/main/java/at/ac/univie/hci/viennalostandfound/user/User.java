package at.ac.univie.hci.viennalostandfound.user;

import java.io.Serializable;

import at.ac.univie.hci.viennalostandfound.R;

public class User implements Serializable {
    private String name;
    private String emailAddress;
    private int profilePictureId;

    public User(String name, String emailAddress, int profilePictureId) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.profilePictureId = profilePictureId;
    }

    public User(String name, String emailAddress) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.profilePictureId = R.drawable.ic_profile_foreground;
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public int getProfilePictureId() {
        return profilePictureId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setProfilePictureId(int profilePictureId) {
        this.profilePictureId = profilePictureId;
    }
}
