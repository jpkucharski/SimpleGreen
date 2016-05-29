package ui;
import com.vaadin.Application;
import com.vaadin.ui.Window;

public class ViewInterface extends Application {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() {
		setMainWindow(new AutoCrudViews());
	}

	class AutoCrudViews extends Window {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public AutoCrudViews() {
			setContent(new View1());
		}
	}
}