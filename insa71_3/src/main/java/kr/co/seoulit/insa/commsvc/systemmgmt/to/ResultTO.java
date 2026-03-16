package kr.co.seoulit.insa.commsvc.systemmgmt.to;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ResultTO {
	
	private String errorCode;
	private String errorMsg;
	
	@JsonIgnore
	private Map<String, Object> map = new HashMap<>();

	@JsonAnyGetter
	public Map<String, Object> getMap() {
		return map;
	}

	public void setAttribute(String key, Object value) {
		map.put(key, value);
	}
}
