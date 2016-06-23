package kamienica.controller.json;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface RestController<T extends Object> {

	ResponseEntity<List<T>> getList();

	ResponseEntity<T> getById( Long id);
}
