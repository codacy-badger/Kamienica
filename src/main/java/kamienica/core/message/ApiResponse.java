package kamienica.core.message;

import java.util.List;

public class ApiResponse<E> {

    private List<E> objectList;

    public void setObjectList(List<E> objectList) {
        this.objectList = objectList;
    }

    public List<E> getObjectList() {
        return objectList;
    }
}
