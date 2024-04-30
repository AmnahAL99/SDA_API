package pojo.PojoTest;

public class Meta{
	private Pagination pagination;

	public void setPagination(Pagination pagination){
		this.pagination = pagination;
	}

	public Pagination getPagination(){
		return pagination;
	}


	public Meta() {
	}

	public Meta(Pagination pagination) {
		this.pagination = pagination;
	}

	@Override
 	public String toString(){
		return 
			"Meta{" + 
			"pagination = '" + pagination + '\'' + 
			"}";
		}
}
