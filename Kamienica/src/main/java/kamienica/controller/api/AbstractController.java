package kamienica.controller.api;

import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class AbstractController {

	protected static final String DUPLICATE_VALUE = "Istnieje już taka wartość w bazie danych";
	protected static final String CONSTRAINT_VIOLATION="Nie można usunąć elementu, dla którego istnieją powiązania w baize";
	protected static final String UNEXPECTED_ERROR="Wystąpił nieoczekiwany błąd podczas zapisu.";
}
