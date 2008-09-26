//package org.gwings.client.ui;
//
//import org.gwings.client.table.pagination.model.Page;
//import org.gwings.client.table.pagination.model.Pager;
//
//import com.google.gwt.user.client.Window;
//import com.google.gwt.user.client.ui.Button;
//import com.google.gwt.user.client.ui.ClickListener;
//import com.google.gwt.user.client.ui.FlexTable;
//import com.google.gwt.user.client.ui.Label;
//import com.google.gwt.user.client.ui.SimplePanel;
//import com.google.gwt.user.client.ui.Widget;
//
///**
// * @author Marcelo Emanoel
// * @since 14/03/2007
// */
//public class PagingBar extends SimplePanel {
//	
//	private Button forwardButton;
//	private Button backwardButton;
//	private Button fastForwardButton;
//	private Button fastBackwardButton;
//	private Label navigationLabel;
//	private FlexTable layout;
//
////	private Pager pager;
//	private TableModel model; 
//	
//	
//	public PagingBar(Pager pager, TableModel model){
//		initialize(pager, model);
//		setupUI();
//		setupStyles();
//		setupListeners();
//	}
//	
////	public PagingBar(Table parent){
////		this(new PagerWizard(), parent.getTableModel());
////	}
//
//	private void initialize(Pager thePager, TableModel theModel) {
//		this.model = theModel;
////		this.pager = thePager;
//		this.layout = new FlexTable();
//	
//		this.forwardButton = new Button();
//		this.fastForwardButton = new Button();
//		this.backwardButton = new Button();
//		this.fastBackwardButton = new Button();
//		this.navigationLabel = new Label();
//		
//		updateNavigation();
//	}
//
//	private void setupUI() {
//		add(layout);
//		
//		layout.setWidget(0, 0, navigationLabel);
//		layout.setWidget(0, 1, fastBackwardButton);
//		layout.setWidget(0, 2, backwardButton);
//		layout.setWidget(0, 3, forwardButton);
//		layout.setWidget(0, 4, fastForwardButton);
//		
//		layout.setCellPadding(0);
//		layout.setCellSpacing(0);
////		layout.setBorderWidth(1);
//	}
//
//	private void setupStyles() {
//		
//		layout.setStyleName("org-gwings-PagingBar");
//		fastBackwardButton.setStyleName("fastBackwardButton");
//		fastForwardButton.setStyleName("fastForwardButton");
//		backwardButton.setStyleName("backwardButton");
//		forwardButton.setStyleName("forwardButton");
//		navigationLabel.setStyleName("navigationLabel");
//		
//	}
//
//	private void setupListeners() {
//		fastBackwardButton.addClickListener(new ClickListener() {
//			public void onClick(Widget sender) {
//				goToFirstPage();
//			}
//		});
//		fastForwardButton.addClickListener(new ClickListener() {
//			public void onClick(Widget sender) {
//				goToLastPage();
//			}
//		});
//		forwardButton.addClickListener(new ClickListener() {
//			public void onClick(Widget sender) {
//				goToNextPage();
//			}
//		});
//		backwardButton.addClickListener(new ClickListener() {
//			public void onClick(Widget sender) {
//				goToPreviousPage();
//			}
//		});
//	}
//	
//	protected void updateNavigation() {
//		Integer index = pager.pageIndex();
//		Integer total = pager.getTotalRows();
//		
//		String navigationText = index+"/"+total;
//		navigationLabel.setText(navigationText);
//	}
//	
//	public Pager getPager() {
//		return pager;
//	}
//
//	public void setPager(Pager pager) {
//		this.pager = pager;
//		goToFirstPage();
//		
//	}
//
//	private void updatePage(Page page) {
//		model.setLines(page.getResults());
//		updateNavigation();
//	}
//
//	/**
//	 * 
//	 */
//	private void goToFirstPage() {
//		try {
//			updatePage(pager.firstPage());
//		} catch (IlegalPagerException e) {
//			Window.alert("This shouldn't happen... "+e.getMessage());
//		}
//	}
//
//	/**
//	 * 
//	 */
//	private void goToLastPage() {
//		try {
//			updatePage(pager.lastPage());
//		} catch (IlegalPagerException e) {
//			Window.alert("This shouldn't happen... "+e.getMessage());
//		}
//	}
//
//	/**
//	 * 
//	 */
//	private void goToNextPage() {
//		try {
//			updatePage(pager.nextPage());
//		} catch (IlegalPagerException e) {
//			Window.alert("This shouldn't happen... "+e.getMessage());
//		}
//	}
//
//	/**
//	 * 
//	 */
//	private void goToPreviousPage() {
//		try {
//			updatePage(pager.previousPage());
//		} catch (IlegalPagerException e) {
//			Window.alert("This shouldn't happen... "+e.getMessage());
//		}
//	}
//}