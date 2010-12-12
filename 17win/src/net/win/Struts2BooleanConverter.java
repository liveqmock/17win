package net.win;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

@SuppressWarnings("unchecked")
public class Struts2BooleanConverter extends StrutsTypeConverter {
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		Boolean bool = null;
		String value = null;
		if (values != null && values.length > 0) {
			value = values[0];
			return new Boolean(value);
		}
		return bool;
	}

	@Override
	public String convertToString(Map context, Object obj) {
		return obj.toString();
	}
}
