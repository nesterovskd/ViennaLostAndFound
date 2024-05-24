package at.ac.univie.hci.viennalostandfound.user;

import java.io.Serializable;

import at.ac.univie.hci.viennalostandfound.R;

public class User implements Serializable {
    String id;
    private String name;
    private String emailAddress;
    private int profilePictureId;

    private String foundCount;
    private String lostCount;
    private String appreciationCount;

    private String phoneNumber;
    private String homeAddress;

    public User(String name, String emailAddress, int profilePictureId, String id) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.profilePictureId = profilePictureId;
        this.id= id;
    }

    public User(String name, String emailAddress) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.profilePictureId = R.drawable.ic_profile_foreground;
    }

    public String getId() {
        return id;
    }

    public static void setProfileInfoForRegisteredUser(User registeredUser) {
        registeredUser.setFoundCount("0");
        registeredUser.setLostCount("0");
        registeredUser.setAppreciationCount("0");
        registeredUser.setPhoneNumber("...");
        registeredUser.setHomeAddress("...");
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

    public String getFoundCount() {
        return foundCount;
    }

    public void setFoundCount(String foundCount) {
        this.foundCount = foundCount;
    }

    public String getLostCount() {
        return lostCount;
    }

    public void setLostCount(String lostCount) {
        this.lostCount = lostCount;
    }

    public String getAppreciationCount() {
        return appreciationCount;
    }

    public void setAppreciationCount(String appreciationCount) {
        this.appreciationCount = appreciationCount;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }
}
