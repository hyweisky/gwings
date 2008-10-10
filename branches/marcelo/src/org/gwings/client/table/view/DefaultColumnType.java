package org.gwings.client.table.view;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License. Copyright
 * 2007 Marcelo Emanoel B. Diniz <marceloemanoel AT gmail.com>
 * 
 * @author Marcelo Emanoel
 * @since 04/02/2007
 */
@SuppressWarnings("unchecked")
public class DefaultColumnType implements ColumnRenderer {

    public Widget renderType(Object value) {
        if(value == null){
            return new HTML();
        }
        String type = value.getClass().getName();
        if (type != null) {
            if (type.equals("java.lang.Boolean")) {
                Boolean b = (Boolean) value;
                CheckBox checkBox = new CheckBox();
                checkBox.setChecked(b.booleanValue());
                return checkBox;
            }
            if (type.equals("java.util.Date")) {
                Date dt = (Date) value;
                DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM/yyyy");
                return new HTML(fmt.format(dt));
            }
            if (type.equals("com.google.gwt.user.client.ui.Image")) {
                Image image = (Image) value;
                return image;
            }
            if (type.equals("java.util.List")
                    || type.equals("java.util.Collection")) {
                Collection c = (Collection) value;
                ListBox list = new ListBox();
                for (Iterator i = c.iterator(); i.hasNext();) {
                    list.addItem(i.next().toString());
                }
                return list;
            }
        }

        return new HTML(value.toString());
    }

}