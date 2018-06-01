package kamienica.controller.api.v1;

import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class AbstractApi {

    public static final String DUPLICATE_VALUE = "Istnieje już taka wartość w bazie danych";
    public static final String CONSTRAINT_VIOLATION = "Nie można usunąć elementu, dla którego istnieją powiązania w bazie";
    public static final String UNEXPECTED_ERROR = "Wystąpił nieoczekiwany błąd podczas zapisu.";
}
