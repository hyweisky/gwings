package org.gwings.client.table.pagination.view;

import org.gwings.client.table.pagination.i18n.PaginationBundle;
import org.gwings.client.table.pagination.i18n.PaginationMessages;
import org.gwings.client.table.pagination.model.Pager;
import org.gwings.client.table.pagination.observer.PagerEvent;
import org.gwings.client.table.pagination.observer.PagerListener;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MouseListenerAdapter;
import com.google.gwt.user.client.ui.Widget;


public class PaginationBar<T> extends Composite implements PagerListener<T> {
    
    private PaginationMessages messages;
    private PaginationBundle images;
    
    private FlexTable layout;
    private ListBox currentPageSelector;
    private Label availablePages;
    
    private Label lastLabel;
    private Label nextLabel;
    private Label previousLabel;
    private Label firstLabel;
    
    private Image lastImage;
    private Image nextImage;
    private Image previousImage;
    private Image firstImage;
    
    private Pager<T> pager;

    public PaginationBar(){
        init();
        setupLayout();
        setupListeners();
    }
    
    private void init() {
        layout = new FlexTable();
        
        messages = GWT.create(PaginationMessages.class);
        images = GWT.create(PaginationBundle.class);
        
        firstImage = images.first().createImage();
        previousImage = images.previous().createImage();
        nextImage = images.next().createImage();
        lastImage = images.last().createImage();

        firstLabel = new Label(messages.first());
        previousLabel = new Label(messages.previous());
        nextLabel = new Label(messages.next());
        lastLabel = new Label(messages.last());
        
        currentPageSelector = new ListBox();
        availablePages = new Label();

        setPager(new Pager<T>());
    }

    private void setupLayout() {
        HorizontalPanel navigationPanel = new HorizontalPanel();
        navigationPanel.add(firstImage);
        navigationPanel.add(firstLabel);
        navigationPanel.add(images.separator().createImage());
        navigationPanel.add(previousImage);
        navigationPanel.add(previousLabel);
        navigationPanel.add(images.separator().createImage());
        navigationPanel.add(nextLabel);
        navigationPanel.add(nextImage);
        navigationPanel.add(images.separator().createImage());
        navigationPanel.add(lastLabel);
        navigationPanel.add(lastImage);

        layout.setWidget(0, 0, new Label(messages.page()));
        layout.setWidget(0, 1, currentPageSelector);
        layout.setWidget(0, 2, new Label(messages.of()));
        layout.setWidget(0, 3, availablePages);
        layout.setWidget(0, 4, new Label(""));
        layout.setWidget(0, 5, navigationPanel);
        
        initWidget(layout);
    }

    private void setupListeners() {
        firstImage.addMouseListener(new ImageChangeListener(images.first(), images.firstOver()));
        previousImage.addMouseListener(new ImageChangeListener(images.previous(), images.previousOver()));
        nextImage.addMouseListener(new ImageChangeListener(images.next(), images.nextOver()));
        lastImage.addMouseListener(new ImageChangeListener(images.last(), images.lastOver()));
        
        firstImage.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                try {
                    pager.firstPage();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        previousImage.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                try {
                    pager.previousPage();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        nextImage.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                try {
                    pager.nextPage();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        lastImage.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                try {
                    pager.lastPage();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * @return the pager
     */
    public Pager<T> getPager() {
        return pager;
    }
    
    /**
     * @param pager the pager to set
     */
    public void setPager(Pager<T> pager) {
        if(this.pager != null){
            this.pager.removePagerListener(this);
        }
        
        this.pager = pager;
        this.pager.addPagerListener(this);
        
        mountRange();
    }

    private void mountRange() {
        currentPageSelector.clear();
        
        Integer totalPages = 0;
        try {
            totalPages = pager.getTotalPages();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        for(int i = 0; i < totalPages; i++){
            currentPageSelector.addItem((i+1)+"");
        }

        currentPageSelector.setSelectedIndex(pager.currentPageIndex());
        availablePages.setText(totalPages+"");
        
    }

    public void firstPage(PagerEvent<T> evt) {
        Integer index = evt.getPager().currentPageIndex();
        currentPageSelector.setSelectedIndex(index);
    }

    public void lastPage(PagerEvent<T> evt) {
        Integer index = evt.getPager().currentPageIndex();
        currentPageSelector.setSelectedIndex(index);
    }

    public void nextPage(PagerEvent<T> evt) {
        Integer index = evt.getPager().currentPageIndex();
        currentPageSelector.setSelectedIndex(index);
    }

    public void pageChanged(PagerEvent<T> evt) {
        Integer index = evt.getPager().currentPageIndex();
        currentPageSelector.setSelectedIndex(index);
    }

    public void previousPage(PagerEvent<T> evt) {
        Integer index = evt.getPager().currentPageIndex();
        currentPageSelector.setSelectedIndex(index);        
    }
    
    private class ImageChangeListener extends MouseListenerAdapter{

        private AbstractImagePrototype over;
        private AbstractImagePrototype normal;
        
        public ImageChangeListener(AbstractImagePrototype normal, AbstractImagePrototype over){
            this.over = over;
            this.normal = normal;
        }
        
        @Override
        public void onMouseEnter(Widget sender) {
            Image img = (Image) sender;
            over.applyTo(img);
        }
        
        @Override
        public void onMouseLeave(Widget sender) {
            Image img = (Image) sender;
            normal.applyTo(img);
        }
    };
}
