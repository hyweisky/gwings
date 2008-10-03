package org.gwings.client.table.pagination.view;

import org.gwings.client.table.model.Plotable;
import org.gwings.client.table.pagination.i18n.PaginationBundle;
import org.gwings.client.table.pagination.i18n.PaginationMessages;
import org.gwings.client.table.pagination.model.DataProvider;
import org.gwings.client.table.pagination.model.Pager;
import org.gwings.client.table.pagination.observer.PagerEvent;
import org.gwings.client.table.pagination.observer.PagerListener;
import org.gwings.client.table.scroll.pagination.PaginatedScrollTable;
import org.gwings.client.ui.BusyWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MouseListenerAdapter;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


public class PaginationBar<T extends Plotable> extends Composite implements PagerListener<T> {
    
    private class ImageChangeListener extends MouseListenerAdapter{

        private AbstractImagePrototype over;
        private AbstractImagePrototype normal;
        private boolean ignoringEvents;
        
        public ImageChangeListener(AbstractImagePrototype normal, AbstractImagePrototype over){
            this.over = over;
            this.normal = normal;
        }
        
        @Override
        public void onMouseEnter(Widget sender) {
            if(!isIgnoringEvents()){
                Image img = (Image) sender;
                over.applyTo(img);
            }
        }
        
        @Override
        public void onMouseLeave(Widget sender) {
            if(!isIgnoringEvents()){
                Image img = (Image) sender;
                normal.applyTo(img);
            }
        }

        
        /**
         * @return the ignoreEvents
         */
        public boolean isIgnoringEvents() {
            return ignoringEvents;
        }

        
        /**
         * @param ignoreEvents the ignoreEvents to set
         */
        public void setIgnoringEvents(boolean ignoreEvents) {
            this.ignoringEvents = ignoreEvents;
        }
    };

    private PaginationMessages messages;
    private PaginationBundle images;
    
    private FlexTable layout;
    private ListBox currentPageSelector;
    private HTML availablePages;
    
    private Label lastLabel;
    private Label nextLabel;
    private Label previousLabel;
    private Label firstLabel;
    
    private Image lastImage;
    private Image nextImage;
    private Image previousImage;
    private Image firstImage;
    
    private ImageChangeListener nextListener;
    private ImageChangeListener previousListener;
    
    private boolean nextDisabled;
    private boolean previousDisabled;
    
    private PaginatedScrollTable<T> table;
    private Pager<T> pager;
    private BusyWidget busy;

    public PaginationBar(PaginatedScrollTable<T> table){
        setTable(table);
        init();
        setupLayout();
        setupListeners();
    }
    
    private void init() {
        layout = new FlexTable();
        
        messages = GWT.create(PaginationMessages.class);
        images = GWT.create(PaginationBundle.class);
        
        initImages();
        previousListener = new ImageChangeListener(images.previous(), images.previousOver());
        nextListener = new ImageChangeListener(images.next(), images.nextOver());
        
        setPreviousDisabled(true);
        setNextDisabled(false);
        
        firstLabel = new Label(messages.first());
        previousLabel = new Label(messages.previous());
        nextLabel = new Label(messages.next());
        lastLabel = new Label(messages.last());
        
        currentPageSelector = new ListBox();
        availablePages = new HTML();
        
        setPager(new Pager<T>());
        busy = new BusyWidget(getTable());
    }

    private void initImages() {
        firstImage = createImage();
        previousImage = createImage();
        nextImage = createImage();
        lastImage = createImage();
        
        images.first().applyTo(firstImage);
        images.previousDisabled().applyTo(previousImage);
        images.next().applyTo(nextImage);
        images.last().applyTo(lastImage);
    }

    private Image createImage() {
        return new Image();
    }

    private void setupLayout() {
        HorizontalPanel navigationPanel = new HorizontalPanel();
        navigationPanel.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
        navigationPanel.setSpacing(2);
        
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
        
        layout.setStylePrimaryName("org_gwings_PaginationBar");
        navigationPanel.setStylePrimaryName("org_gwings_PaginationBar");
        
        currentPageSelector.setStylePrimaryName("pageSelector");
        availablePages.setStylePrimaryName("pagesAvailableLable");
        
        initWidget(layout);
        
        layout.getRowFormatter().setVerticalAlign(0, VerticalPanel.ALIGN_MIDDLE);
    }

    private void setupListeners() {
        firstImage.addMouseListener(new ImageChangeListener(images.first(), images.firstOver()));
        previousImage.addMouseListener(previousListener);
        nextImage.addMouseListener(nextListener);
        lastImage.addMouseListener(new ImageChangeListener(images.last(), images.lastOver()));
        
        firstImage.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                try {
                    busy.setVisible(true);
                    pager.firstPage();
                }
                catch (Exception e) {
                    stopBusy();
                    Window.alert(e.getMessage());
                }
            }
        });
        previousImage.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                try {
                    if(!isPreviousDisabled()){
                        busy.setVisible(true);
                        pager.previousPage();
                    }
                }
                catch (Exception e) {
                    stopBusy();
                    Window.alert(e.getMessage());
                }
            }
        });
        nextImage.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                try {
                    if(!isNextDisabled()){
                        busy.setVisible(true);
                        pager.nextPage();
                    }
                }
                catch (Exception e) {
                    stopBusy();
                    Window.alert(e.getMessage());
                }
            }
        });
        lastImage.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                try {
                    busy.setVisible(true);
                    pager.lastPage();
                }
                catch (Exception e) {
                    stopBusy();
                    Window.alert(e.getMessage());
                }
            }
        });
        
        currentPageSelector.addChangeListener(new ChangeListener() {
            public void onChange(Widget sender) {
                int index = currentPageSelector.getSelectedIndex();
                String page = currentPageSelector.getItemText(index);
                try {
                    busy.setVisible(true);
                    pager.goToPage(new Integer(page));
                }
                catch (NumberFormatException e) {
                    stopBusy();
                    Window.alert(e.getMessage());
                }
                catch (Exception e) {
                    stopBusy();
                    Window.alert(e.getMessage());
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
    }

    private void mountRange() {
        currentPageSelector.clear();
        
        Integer totalPages = 0;
        try {
            totalPages = pager.getTotalPages();
            if(totalPages == 1){
                setNextDisabled(true);
                setPreviousDisabled(true);
            }
        }
        catch (Exception e) {
            Window.alert(e.getMessage());
        }
        
        for(int i = 0; i < totalPages; i++){
            currentPageSelector.addItem((i+1)+"");
        }
        
        availablePages.setHTML(totalPages+"");
    }

    public void firstPage(PagerEvent<T> evt) {
        currentPageSelector.setSelectedIndex(0);
     
        setPreviousDisabled(true);
        try {
            Integer totalPages = pager.getTotalPages();
            setNextDisabled(totalPages == 1);
        }
        catch (Exception e) {
            Window.alert(e.getMessage());
        }
    }

    public void lastPage(PagerEvent<T> evt) {
        currentPageSelector.setSelectedIndex(currentPageSelector.getItemCount()-1);
        
        setNextDisabled(true);
        try {
            Integer totalPages = pager.getTotalPages();
            setPreviousDisabled(totalPages == 1);
        }
        catch (Exception e) {
            Window.alert(e.getMessage());
        }
    }

    public void nextPage(PagerEvent<T> evt) {
        int index = currentPageSelector.getSelectedIndex();
        currentPageSelector.setSelectedIndex(++index);
        
        Integer totalPages = 0;
        try {
            totalPages = pager.getTotalPages();
            if(index+1 == totalPages){
                setNextDisabled(true);
            }
        }
        catch (Exception e) {
            Window.alert(e.getMessage());
        }
        setPreviousDisabled(false);
        
    }

    public void pageChanged(PagerEvent<T> evt) {
        Pager<T> pager = evt.getPager();
        
        Integer index = pager.currentPageIndex();
        currentPageSelector.setSelectedIndex(index-1);
        
        try {
            Integer totalPages = pager.getTotalPages();
            if(index > 1 && index < totalPages){
                setNextDisabled(false);
                setPreviousDisabled(false);
            }
            if(index == 1){
                setPreviousDisabled(true);
            }
            if(index == totalPages){
                setNextDisabled(true); 
            }
        }
        catch (Exception e) {
            Window.alert(e.getMessage());
        }
    }

    public void previousPage(PagerEvent<T> evt) {
        Integer index = currentPageSelector.getSelectedIndex();
        currentPageSelector.setSelectedIndex(--index);
        
        if(index == 0){
            setPreviousDisabled(true);
        }
        setNextDisabled(false);
    }
    
    /**
     * @return
     * @see org.gwings.client.table.pagination.model.Pager#getProvider()
     */
    public DataProvider<T> getProvider() {
        return pager.getProvider();
    }

    /**
     * @param provider
     * @see org.gwings.client.table.pagination.model.Pager#setProvider(org.gwings.client.table.pagination.model.DataProvider)
     */
    public void setProvider(DataProvider<T> provider) {
        pager.setProvider(provider);
        if(provider != null){
            mountRange();
            DeferredCommand.addCommand(new Command() {
                public void execute() {
                    try {
                        pager.firstPage();
                    }
                    catch (Exception e) {
                        Window.alert(e.getMessage());
                    }
                }
            });
        }
    }

    
    /**
     * @return the nextDisabled
     */
    private boolean isNextDisabled() {
        return nextDisabled;
    }

    
    /**
     * @param disabled the nextDisabled to set
     */
    private void setNextDisabled(boolean disabled) {
        this.nextDisabled = disabled;
        nextListener.setIgnoringEvents(disabled);
        if(disabled){
            images.nextDisabled().applyTo(nextImage);
        }
        else{
            images.next().applyTo(nextImage);
        }
    }

    
    /**
     * @return the previousDisabled
     */
    private boolean isPreviousDisabled() {
        return previousDisabled;
    }

    
    /**
     * @param disabled the previousDisabled to set
     */
    private void setPreviousDisabled(boolean disabled) {
        this.previousDisabled = disabled;
        previousListener.setIgnoringEvents(disabled);
        if(disabled){
            images.previousDisabled().applyTo(previousImage);
        }
        else{
            images.previous().applyTo(previousImage);
        }
    }

    /**
     * @param table the table to set
     */
    public void setTable(PaginatedScrollTable<T> table) {
        this.table = table;
    }

    /**
     * @return the table
     */
    public PaginatedScrollTable<T> getTable() {
        return table;
    }
    
    public void stopBusy(){
        busy.setVisible(false);
    }
}
