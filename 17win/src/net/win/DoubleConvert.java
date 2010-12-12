package net.win;

import org.apache.commons.beanutils.Converter;

public class DoubleConvert implements Converter {
	public Object convert(Class clazz, Object obj) {
		if (obj instanceof Double) {
			return obj;
		}
		String p = (String) obj;
		if (p == null || p.trim().length() == 0) {
			return null;
		}else{
			return new Double(p);
		}
		 
	}
}
