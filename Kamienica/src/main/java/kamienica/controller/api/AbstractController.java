package kamienica.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public abstract class AbstractController {

	protected static final String DUPLICATE_VALUE = "Istnieje już taka wartość w bazie danych";
	protected static final String CONSTRAINT_VIOLATION="Nie można usunąć elementu, dla którego istnieją powiązania w baize";
}
