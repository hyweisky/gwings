package org.gwings.server.demo.pagination;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.gwings.client.demo.LinePlotable;
import org.gwings.client.table.pagination.model.PageConfig;

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
        this(new Random().nextInt(937));
    }
    
    private void generateRandomData(Integer size) {
        for(int i = 0; i < size; i++){
            
            int booleanIndex = new Random().nextInt(BOOLEAN_VALUES.length);
            int imageIndex = new Random().nextInt(IMAGE_URL_VALUES.length);
            int stringIndex = new Random().nextInt(STRING_VALUES.length);
            int dateIndex = new Random().nextInt(DATE_VALUES.length);
            
            Boolean marked = BOOLEAN_VALUES[booleanIndex];
            String imgUrl = IMAGE_URL_VALUES[imageIndex];
            String msg = STRING_VALUES[stringIndex];
            Date date = DATE_VALUES[dateIndex];
            
            data.add(new LinePlotable(marked, imgUrl, msg, date));
        }
    }

    public List<LinePlotable> list(PageConfig config) throws Exception {
        Integer start = config.getStart();
        Integer finish = (config.getFinish() >= countTotal() ? countTotal()-1 : config.getFinish());
        config.setTotalAvailable(countTotal());

        List<LinePlotable> result = new ArrayList<LinePlotable>(data.subList(start, finish));
        return result;
    }

    public Integer countTotal() throws Exception {
        return size;
    }

}
