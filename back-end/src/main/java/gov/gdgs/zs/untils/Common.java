package gov.gdgs.zs.untils;

import java.util.UUID;

public class Common {
	public String newUUID (){
		String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
		return uuid;
	}
}
