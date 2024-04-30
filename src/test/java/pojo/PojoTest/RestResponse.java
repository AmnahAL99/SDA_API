package pojo.PojoTest;

import java.util.List;

public class RestResponse{
	private List<DataItem> data;
	private Meta meta;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setMeta(Meta meta){
		this.meta = meta;
	}

	public Meta getMeta(){
		return meta;
	}

	public RestResponse() {
	}

	public RestResponse(List<DataItem> data, Meta meta) {
		this.data = data;
		this.meta = meta;
	}

	@Override
 	public String toString(){
		return 
			"RestResponse{" + 
			"data = '" + data + '\'' + 
			",meta = '" + meta + '\'' + 
			"}";
		}
}