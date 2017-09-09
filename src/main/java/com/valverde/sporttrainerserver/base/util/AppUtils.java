package com.valverde.sporttrainerserver.base.util;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;

public class AppUtils {

    public static boolean isNull(Object o) {
        return o == null;
    }

    public static boolean isNotNull(Object o) {
        return o != null;
    }

    public static Date getDate(XMLGregorianCalendar calendar) {
        return calendar.toGregorianCalendar().getTime();
    }
}
