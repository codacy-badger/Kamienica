package kamienica.core.message;

import java.util.List;

/**
 * Created by macfol on 11/27/16.
 */
public class ApiResponse<E> {

    private List<E> objectList;

    public void setObjectList(List<E> objectList) {
        this.objectList = objectList;
    }
}
