package org.gwings.client.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.gwings.client.table.pagination.model.DataProvider;
import org.gwings.client.table.pagination.model.Page;
import org.gwings.client.table.pagination.model.PageConfig;

import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.Image;

public class LineProvider implements DataProvider<LinePlotable>, LineConstants{

    private static final long serialVersionUID = -6506240230479402719L;
    private Integer size;
    private List<LinePlotable> data;
    
    public LineProvider(Integer size){
        this.size = size;
        data = new ArrayList<LinePlotable>();
        generateRandomData(size);
    }
    
    public LineProvider(){
        this(Random.nextInt(937));
    }
    
    private void generateRandomData(Integer size) {
        for(int i = 0; i < size; i++){
            
            int booleanIndex = Random.nextInt(BOOLEAN_VALUES.length);
            int imageIndex = Random.nextInt(IMAGE_VALUES.length);
            int stringIndex = Random.nextInt(STRING_VALUES.length);
            int dateIndex = Random.nextInt(DATE_VALUES.length);
            
            Boolean marked = BOOLEAN_VALUES[booleanIndex];
            Image img = IMAGE_VALUES[imageIndex];
            String msg = STRING_VALUES[stringIndex];
            Date date = DATE_VALUES[dateIndex];
            
            data.add(new LinePlotable(marked, img, msg, date));
        }
    }

    public Page<LinePlotable> fetchData(PageConfig config) throws Exception {
        Integer start = config.getStart();
        Integer finish = config.getFinish();
        
        List<LinePlotable> items = new ArrayList<LinePlotable>();
        for(int i = start; i < finish && i < data.size(); i++){
            items.add(data.get(i));
        }
        
        Page<LinePlotable> page = new Page<LinePlotable>();
        page.setItems(items);
        page.setConfig(config);
        
        return page;
    }

    public Integer fetchSize() throws Exception {
        return size;
    }

}