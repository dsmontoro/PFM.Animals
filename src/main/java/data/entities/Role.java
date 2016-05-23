package data.entities;

public enum Role {
    ADMIN, ASSOCIATION, ADOPTER, AUTHENTICATED;

    public String roleName() {
        return "ROLE_" + this.toString();
    }

}
