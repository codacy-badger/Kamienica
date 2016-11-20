package kamienica.core.util;

import java.util.List;

public class ApiResponse2<T> {

	List<T> objectList;
	T object;
	List<?> nestedElements;
	int currentPage;
	int offset;
	
	public ApiResponse2() {
		super();
	}
	
	public List<?> getNestedElements() {
		return nestedElements;
	}

	public void setNestedElements(List<?> nestedElements) {
		this.nestedElements = nestedElements;
	}

	

	

	public List<T> getObjectList() {
		return objectList;
	}

	public void setObjectList(List<T> objectList) {
		this.objectList = objectList;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

}
