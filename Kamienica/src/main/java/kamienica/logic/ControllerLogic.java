package kamienica.logic;

import java.util.Map;

public interface ControllerLogic {

	public void register(Map<String, Object> map, String url);

	public void list(Map<String, Object> map, String url);

	public void edit(Map<String, Object> map, String url);

	public void save(Map<String, Object> map, String url);

}
