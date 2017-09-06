package kamienica.feature.security;

public interface ISecurityService {

    void changePassword(final String mail, final String oldPassowrd, final String newPwassword);
}
