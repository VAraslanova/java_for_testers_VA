package ru.stqa.mantis.model;

public record UserData(String username, String realname, String email, String password) {

    public UserData() {
        this("", "", "", "");
    }

    public UserData withUsername(String username) {
        return new UserData(username, this.realname, this.email, this.password);
    }

    public UserData withRealname(String realname) {
        return new UserData(this.username, realname, this.email, this.password);
    }
    public UserData withEmail(String email) {
        return new UserData(this.username, this.realname, email, this.password);
    }

    public UserData withPassword(String password) {
        return new UserData(this.username, this.realname, this.email, password);
    }
}
