package kamienica.feature.security;

public interface ISecurityService {

    public void changePassword(final String mail, final String oldPassowrd, final String newPwassword);
}
