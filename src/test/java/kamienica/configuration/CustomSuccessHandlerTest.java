package kamienica.configuration;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

import static org.mockito.Mockito.*;

public class CustomSuccessHandlerTest {

    private static final CustomSuccessHandler CUT = new CustomSuccessHandler();
    private static final String URL = "localhost:0000/views/owner/home.html";

    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);

    private final Collection<? extends GrantedAuthority> authorities = Lists.newArrayList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    private final Authentication authentication = new AnonymousAuthenticationToken("asd", "asd", authorities);

    @Test
    public void handle() throws IOException {
        when(request.getContextPath()).thenReturn("localhost:0000");
        when(response.encodeRedirectURL(URL)).thenReturn(URL);
        CUT.handle(request, response, authentication);
        verify(response, times(1)).sendRedirect(URL);
    }
}