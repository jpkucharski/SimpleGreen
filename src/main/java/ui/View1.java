package ui;

import java.sql.Date;
import java.util.List;

import sender.EMail;
import sender.SMS;
import sender.TWEETER;
import logs.LogsSavingClass;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;














import org.hibernate.validator.xml.GetterType;

import entity.Communicatorlogs;

public class View1 extends CustomComponent {

	private static final long serialVersionUID = 1L;
	private AbsoluteLayout mainLayout;
	private TextField messageTopicTextField;
	private TextField receiverTextField;
	private TextField senderTextField;
	private NativeSelect messageTypeSelect;
	private RichTextArea messageEditingField;
	private String sender;
	private String messageType;
	private String receiver;
	private String messageTopic;
	private String messageText;
	private String[] reciversTable;
	private int i, x, y, z;
	private ArrayList<String> messageTypesList;
	
	
	
	private String emailAdresFormat = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	private String phoneNamberFormat = "\\d{9}";
	private String tweeterAdresFormat = "\\#\\w+";
	
	//"^\\#([\\d{3})$";

	public static final String PERSISTENCE_UNIT = "simpleGreen";
	private static final String String = null;
	private JPAContainer<Communicatorlogs> container;

	public View1() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}

	private AbsoluteLayout buildMainLayout() {
		creatingLayout();
		return mainLayout;
	}
	
	public void creatingLayout() {
		mainLayoutCreation();
		logsTableCreation();
		senderTextFieldCreation();
		messageTypesChooserCreation();
		receiverTextFieldCreation();
		messageTopicTextField();
		sendButtonCreation();
		clearButtonCreation();
		messageTextFieldCreation();
		logoCreation();
		arrow1Creation();
		arrow2Creation();
		arrow3Creation();
	}
		
	public void mainLayoutCreation(){
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		setWidth("100.0%");
		setHeight("100.0%");
	}
	 public void senderTextFieldCreation(){
		senderTextField = new TextField("Sender");
		senderTextField.setImmediate(false);
		senderTextField.setWidth("130px");	
		mainLayout.addComponent(senderTextField,"top:60.0px;left:120.0px;");
	 }
	 
	 
	 
	 
	 
	 public Class<? extends Package> classList() throws ClassNotFoundException{
		 
		 //Class c = Class.forName("MyClass"); // if you want to specify a class
		 Package pac1= Package.getPackage("types");
		 //pac1.getClass();
		 return pac1.getClass();
		 
//		 Class c1 = this.getClass();          // if you want to use the current class
//
//		 System.out.println("Package: "+c.getPackage()+"\nClass: "+c.getSimpleName()+"\nFull Identifier: "+c.getName());
		 
		 
	 }
	
	 
	 
	 
	










	String[] typy = {"opcja1", "opcja2", "opcja3"};
	
	public void typyLista() throws UnsupportedOperationException, ClassNotFoundException{
		for (int i=0; i<typy.length; i++){
			messageTypeSelect.addItem(classList());
			
		}
	}
	public void putingTypesToMessageChoose(){
		for ( int i =0; i<messageTypesList.size(); i++){
		messageTypeSelect.addItem(messageTypesList.get(i));
		}
	}
	
	public void messageTypesChooserCreation() {
		messageTypeSelect = new NativeSelect("Message type");
		messageTypeSelect.setWidth("-1px");
		messageTypeSelect.setImmediate(false);
		putingTypesToMessageChoose();
		messageTypeSelect.setNullSelectionAllowed(false);
		mainLayout.addComponent(messageTypeSelect, "top:130.0px;left:150.0px;");
	}
	 
	public void receiverTextFieldCreation() {
		receiverTextField = new TextField(
				"Receiver (To send Multimessage separate the receivers with '; ') ");
		receiverTextField.setImmediate(false);
		receiverTextField.setWidth("350px");
		mainLayout.addComponent(receiverTextField, "top:200.0px;left:120.0px;");
	}
	public void messageTopicTextField(){
		messageTopicTextField = new TextField("Message topic");
		messageTopicTextField.setImmediate(false);
		messageTopicTextField.setWidth("500px");
		mainLayout.addComponent(messageTopicTextField,"top:280.0px;left:120.0px;");
	}
	public void sendButtonCreation(){
		Button sendButton = new Button("Send", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (senderTextField.getValue().equals("")) {
					getWindow().showNotification("!SENDER FIELD IS EMPTY!");
				}
				else if (receiverTextField.getValue().equals("")) {
					getWindow().showNotification("!RECEIVER FIELD IS EMPTY!");
				}
				else if (messageTypeSelect.getValue() == (null)) {
					getWindow().showNotification("!SELECT MESSAGE TYPE!");
				}
				else {
					redirectionOfMessageType();
				}
			}
		});
		sendButton.setImmediate(false);
		mainLayout.addComponent(sendButton, "top:520.0px;left:160.0px;");
	}

	public void redirectionOfMessageType(){
		if (messageTypeSelect.getValue().equals("sms")) {
			senderPhoneNamberFormatValidation();
		}
		else if (messageTypeSelect.getValue().equals("e-mail")) {
			senderEmailAdresFormatValidation();
			
		}
		else if (messageTypeSelect.getValue().equals("tweet")) {
			senderTweetAdresFormatValidation();
			
		}
		else if (messageTypeSelect.getValue() == (null)) {
			getWindow().showNotification("!SELECT MESSAGE TYPE!");
		}
	}
	
	public void senderEmailAdresFormatValidation() {
		if (!senderTextField.getValue().toString().matches(emailAdresFormat)) {
			getWindow().showNotification("!SENDER E-mail INCORECT FORMAT!",
					Window.Notification.TYPE_HUMANIZED_MESSAGE);
		}
		else {
			receiversTableCreation();
			reciversEmailFormatVeryfication();
		}
	}
	
	public void senderTweetAdresFormatValidation(){
		if (!senderTextField.getValue().toString().matches(tweeterAdresFormat)) {
			getWindow().showNotification("!SENDER #TWEET INCORECT FORMAT!",
					Window.Notification.TYPE_HUMANIZED_MESSAGE);
		}
		else {
			receiversTableCreation();
			reciversTweetFormatVeryfication();
		}
	}
	
	
	
	
	
	
	public void reciversTweetFormatVeryfication() {
		for (i = 0; i < reciversTable.length; i++) {
			if (!reciversTable[i].matches(tweeterAdresFormat)) {
				getWindow().showNotification(
						"!RECEIVER #TWEET INCORECT FORMAT: " + reciversTable[i]
								+ " !");
				return;
			}
		}
		tweetSending();
		logsSaving2();
	}
	public void tweetSending() {
		try {
			for (i = 0; i < reciversTable.length; i++) {
				setingSettingMessageParameters();
				TWEETER sendObject = new TWEETER(sender, messageType,
						reciversTable[i], messageTopic, messageText);
				sendObject.tweetService();
			}
			getWindow().showNotification("Tweet was sent.");
		}
		catch (Exception e) {
			getWindow().showNotification("!TWEET SENDING FAILURE!",
					"<br/>Check your internet connection.",
					Window.Notification.TYPE_ERROR_MESSAGE);
		}
	}
	
	public void senderPhoneNamberFormatValidation() {
		if (!senderTextField.getValue().toString().matches(phoneNamberFormat)) {
			getWindow().showNotification(
					"!SENDER PHONE NAMBER INCORECT FORMAT! (only 9 digits)");
		}
		else {
			receiversTableCreation();
			receiversPhoneNamberFormatVeryfication();

		}
	}
	
	public void receiversPhoneNamberFormatVeryfication() {
		for (i = 0; i < reciversTable.length; i++) {
			if (!reciversTable[i].matches(phoneNamberFormat)) {
				getWindow().showNotification(
						"!RECEIVER PHONE NAMBER INCORECT FORMAT: "
								+ reciversTable[i] + " !");
				return;
			}
		}
		smsSending();
		logsSaving2();
	}

	public void clearButtonCreation() {
		Button clearButton = new Button("Clear", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				textFieldsCleaning();
			}
		});
		clearButton.setImmediate(false);
		mainLayout.addComponent(clearButton, "top:520px; left: 250px;");
	}

	public void messageTextFieldCreation() {
		messageEditingField = new RichTextArea();
		messageEditingField.setImmediate(false);
		messageEditingField.setWidth("500px");
		mainLayout.addComponent(messageEditingField,
				"top:305.0px;left:120.0px;");
	}
	public void logoCreation(){
		Embedded programLogo = new Embedded();
		programLogo.setImmediate(false);
		programLogo.setSource(new ThemeResource("../Simple.png"));
		programLogo.setType(1);
		mainLayout.addComponent(programLogo);
	}
	public void arrow1Creation(){
		Embedded arrow1 = new Embedded();
		arrow1.setImmediate(false);
		arrow1.setSource(new ThemeResource("../Arrow.png"));
		arrow1.setType(1);
		mainLayout.addComponent(arrow1, "top:85.0px;left:150.0px;");
		
	}
	public void arrow2Creation(){
		Embedded arrow2 = new Embedded();
		arrow2.setImmediate(false);
		arrow2.setSource(new ThemeResource("../Arrow.png"));
		arrow2.setType(1);
		mainLayout.addComponent(arrow2, "top:155.0px;left:150.0px;");
		
	}
	public void arrow3Creation(){
		Embedded arrow3 = new Embedded();
		arrow3.setImmediate(false);
		arrow3.setSource(new ThemeResource("../Arrow.png"));
		arrow3.setType(1);
		mainLayout.addComponent(arrow3, "top:230.0px;left:150.0px;");
		
	}

	public void logsTableCreation() {
		container = JPAContainerFactory.make(Communicatorlogs.class,
				PERSISTENCE_UNIT);
		Table logsTable = new Table("Messages logs");
		logsTable.addContainerProperty("date", Date.class, null);
		logsTable.addContainerProperty("messagetype", String.class, null);
		logsTable.addContainerProperty("sender", String.class, null);
		logsTable.addContainerProperty("receiver", String.class, null);
		logsTable.setContainerDataSource(container);
		logsTable.setVisibleColumns(new Object[] { "date", "messagetype",
				"sender", "receiver" });
		logsTable.setImmediate(false);
		logsTable.setWidth("600px");
		logsTable.sort(new Object[] { "date" }, new boolean[] { false });
		mainLayout.addComponent(logsTable, "top:40.0px;left:800.0px;");
	}

	public void setingSettingMessageParameters() {
		sender = senderTextField.getValue().toString();
		messageType = messageTypeSelect.getValue().toString();
		receiver = reciversTable[i];
		messageTopic = messageTopicTextField.getValue().toString();
		messageText = messageEditingField.getValue().toString();
	}

	public void textFieldsCleaning() {
		senderTextField.setValue("");
		messageTypeSelect.setValue(null);
		receiverTextField.setValue("");
		messageTopicTextField.setValue("");
		messageEditingField.setValue("");
	}
	public void receiversTableCreation() {
		reciversTable = receiverTextField.getValue().toString().split("; ");
	}

	public void reciversEmailFormatVeryfication() {
		for (i = 0; i < reciversTable.length; i++) {
			if (!reciversTable[i].matches(emailAdresFormat)) {
				getWindow().showNotification(
						"!RECEIVER E-mail INCORECT FORMAT: " + reciversTable[i]
								+ " !");
				return;
			}
		}
		emailsSending();
		logsSaving2();
	}

	public void emailsSending() {
		try {
			for (i = 0; i < reciversTable.length; i++) {
				setingSettingMessageParameters();
				EMail sendObject = new EMail(sender, messageType,
						reciversTable[i], messageTopic, messageText);
				sendObject.eMailService();
			}
			getWindow().showNotification("e-mail was sent.");
		}
		catch (Exception e) {
			getWindow().showNotification("!E-MAIL SENDING FAILURE!",
					"<br/>Check your internet connection.",
					Window.Notification.TYPE_ERROR_MESSAGE);
		}
	}
	
	public void smsSending() {
		try {

			for (i = 0; i < reciversTable.length; i++) {
				setingSettingMessageParameters();
				SMS sendObject = new SMS(sender, messageType, reciversTable[i],
						messageTopic, messageText);
				sendObject.smsService();
			}
			getWindow().showNotification("sms was sent.");
		}
		catch (Exception e) {
			getWindow().showNotification("!SMS SENDING FAILURE!",
					"<br/>Check your internet connection.",
					Window.Notification.TYPE_ERROR_MESSAGE);
		}
	}
	
	public void logsSaving2() {
		try {

			for (i = 0; i < reciversTable.length; i++) {
				setingSettingMessageParameters();
				LogsSavingClass logSav = new LogsSavingClass(sender,
						messageType, receiver);
				logSav.sqlLogsService();
			}
			container.refresh();
			textFieldsCleaning();

		}
		catch (Exception e) {
			getWindow().showNotification("!LOGS DATABASE CONNECTION FAILURE!",
					"<br/>Check your internet connection.",
					Window.Notification.TYPE_ERROR_MESSAGE);
		}
	}

	public int metoda1(){
		z=x+y;
		return z;
		}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}
	
	public void messageTypesListCreation(){
		messageTypesList.add("Sms");
		messageTypesList.add("Mms");
		messageTypesList.add("Email");
		messageTypesList.add("Tweeter");
	}
	
	
	
	
	
	
	
	
	
	
}