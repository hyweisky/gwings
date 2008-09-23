/**
 * 
 */
package org.gwings.client.table.pagination;

import java.io.Serializable;

import org.gwings.client.table.pagination.action.DataProvider;
import org.gwings.client.table.pagination.i18n.PaginationBundle;
import org.gwings.client.table.pagination.i18n.PaginationMessages;


import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MouseListenerAdapter;
import com.google.gwt.user.client.ui.Widget;


/**
 * @author USER
 *
 */
public class PaginationBar<T extends Serializable> extends Composite {

    private PageResult<T> pageResult;

    private PaginationBundle bundle;
    private PaginationMessages messages;
    
    private ListBox pageList;
    private Image previous;
    private Image next;
    private Image first;
    private Image last;
    private Label size;
    private FlexTable layout;
    
    private DataProvider<T> provider; 
    private PaginationTable table;
    
    public PaginationBar(DataProvider<T> provider){
        setProvider(provider);
        init();
        setupLayout();
        setupListeners();
    }

    private void init() {
        bundle = GWT.create(PaginationBundle.class);
        messages = GWT.create(PaginationMessages.class);
        
        pageList = new ListBox();
        size = new Label("");

        previous = bundle.previous().createImage();
        next = bundle.next().createImage();
        first = bundle.first().createImage();
        last = bundle.last().createImage();
        
        layout = new FlexTable();
        PageResult<T> result = new PageResult<T>();
        result.setSize(20);
        setPageResult(result);
    }

    private void setupLayout() {
        layout.setWidget(0, 0, new Label(messages.page()));
        layout.setWidget(0, 1, pageList);
        layout.setWidget(0, 2, new Label(messages.of()));
        layout.setWidget(0, 3, size);
        layout.setWidget(0, 4, first);
        layout.setWidget(0, 5, new Label(messages.first()));
        layout.setWidget(0, 6, bundle.separator().createImage());
        layout.setWidget(0, 7, previous);
        layout.setWidget(0, 8, new Label(messages.previous()));
        layout.setWidget(0, 9, bundle.separator().createImage());
        layout.setWidget(0, 10, new Label(messages.next()));
        layout.setWidget(0, 11, next);
        layout.setWidget(0, 12, bundle.separator().createImage());
        layout.setWidget(0, 13, new Label(messages.last()));
        layout.setWidget(0, 14, last);
        
        HorizontalPanel hPanel = new HorizontalPanel();
        hPanel.add(layout);
        hPanel.setBorderWidth(1);
        initWidget(hPanel);
    }
    
    public void setPageResult(PageResult<T> result){
        this.pageResult = result;
        pageList.clear();
        size.setText(pageResult.getSize()+"");
        for(int i = 1 ; i <= pageResult.getSize(); i++){
            pageList.addItem(i+"");
        }
    }
    
    private void setupListeners() {
        first.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                PageConfig cfg = new PageConfig();
                cfg.setFirst(0);
                cfg.setCurrent(0);
                PageResult<T> data = provider.fetchData(cfg);
//                table.getTableModel().clearRows();
//                table.getTableModel().setLines(data.getResults());
                setPageResult(data);
            }
        });
        previous.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                PageConfig clone = pageResult.getPageConfig();
                clone.setCurrent(clone.getCurrent() -1);
                PageResult<T> data = provider.fetchData(clone);
//                table.getTableModel().clearRows();
//                table.getTableModel().setLines(data.getResults());
                setPageResult(data);
            }
        });
        next.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                Window.alert("next");
            }
        });
        last.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                Window.alert("last");
            }
        });
        
        first.addMouseListener(new MouseListenerAdapter() {
            public void onMouseEnter(Widget sender) {
                bundle.firstOver().applyTo(first);
            }
            public void onMouseLeave(Widget sender) {
                bundle.first().applyTo(first);
            }
        });
        next.addMouseListener(new MouseListenerAdapter() {
            public void onMouseEnter(Widget sender) {
                bundle.nextOver().applyTo(next);
            }
            public void onMouseLeave(Widget sender) {
                bundle.next().applyTo(next);
            }
        });
        last.addMouseListener(new MouseListenerAdapter() {
            public void onMouseEnter(Widget sender) {
                bundle.lastOver().applyTo(last);
            }
            public void onMouseLeave(Widget sender) {
                bundle.last().applyTo(last);
            }
        });
        previous.addMouseListener(new MouseListenerAdapter() {
            public void onMouseEnter(Widget sender) {
                bundle.previousOver().applyTo(previous);
            }
            public void onMouseLeave(Widget sender) {
                bundle.previous().applyTo(previous);
            }
        });
    }

    
    /**
     * @return the provider
     */
    public DataProvider<T> getProvider() {
        return provider;
    }

    
    /**
     * @param provider the provider to set
     */
    public void setProvider(DataProvider<T> provider) {
        this.provider = provider;
    }
}
