package kr.co.seoulit.insa.commsvc.systemmgmt.to;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DetailCodeTO {
	
	String detailCodeNumber,codeNumber, detailCodeName, detailCodeNameusing;

}
