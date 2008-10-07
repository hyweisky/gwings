package org.gwings.server.demo.pagination;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.gwings.client.demo.LineConstants;
import org.gwings.client.demo.LinePlotable;
import org.gwings.client.table.pagination.model.PageConfig;

import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.Image;


public class LineProviderFacade implements LineConstants{
    private static LineProviderFacade instance = null;

    private Integer size;
    private List<LinePlotable> data;
    
    public static LineProviderFacade getInstance(){
        if(instance == null) {
            instance = new LineProviderFacade();
        }
        return instance;
    }
    
    
    private LineProviderFacade(Integer size){
        this.size = size;
        data = new ArrayList<LinePlotable>();
        generateRandomData(size);
    }
    
    private LineProviderFacade(){
        this(Random.nextInt(937));
    }
    
    private void generateRandomData(Integer size) {
        for(int i = 0; i < size; i++){
            
            int booleanIndex = Random.nextInt(BOOLEAN_VALUES.length);
            int imageIndex = Random.nextInt(IMAGE_VALUES.length);
            int stringIndex = Random.nextInt(STRING_VALUES.length);
            int dateIndex = Random.nextInt(DATE_VALUES.length);
            
            Boolean marked = BOOLEAN_VALUES[booleanIndex];
            Image img = new Image(IMAGE_VALUES[imageIndex].getUrl());
            String msg = STRING_VALUES[stringIndex];
            Date date = DATE_VALUES[dateIndex];
            
            data.add(new LinePlotable(marked, img, msg, date));
        }
    }

    public List<LinePlotable> list(PageConfig config) throws Exception {
        Integer start = config.getStart();
        Integer finish = config.getFinish();

        return new ArrayList<LinePlotable>(data.subList(start, finish));
    }

    public Integer countTotal() throws Exception {
        return size;
    }

}
