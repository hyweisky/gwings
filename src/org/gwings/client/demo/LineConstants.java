package org.gwings.client.demo;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

public interface LineConstants {

    public static final Boolean[] BOOLEAN_VALUES = new Boolean[] {
            Boolean.TRUE, 
            Boolean.FALSE 
    };
    
    public static final String[] IMAGE_URL_VALUES = new String[] {
            "pics/table/star_on.gif",
            "pics/table/star_off.gif" 
    };
    
    public static final String[] STRING_VALUES = new String[] {
            "Lorem ipsum dolor sit amet, consectetuer adipiscing elit.",
            "Nam nec orci quis nunc volutpat viverra.",
            "Praesent in quam vitae nisi volutpat posuere.",
            "Nullam dignissim consequat eros.",
            "Aliquam placerat mauris eget nunc.",
            "Vestibulum suscipit elementum nisi." 
    };

    public static final DateTimeFormat DATA_FORMAT = DateTimeFormat.getFormat("dd/MM/yyyy");
    
    public static final Date[] DATE_VALUES = new Date[] {
            DATA_FORMAT.parse("29/01/1982"), 
            DATA_FORMAT.parse("03/04/1988"),
            DATA_FORMAT.parse("17/11/2004"), 
            DATA_FORMAT.parse("06/06/2006") 
    };

}
