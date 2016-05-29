package logs;

import javax.persistence.EntityManager;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import entity.Communicatorlogs;

public class LogsSavingClass {

	 private String sender1;
	 private String messageType1;
	 private String receiver1;
	
	public static final String PERSISTENCE_UNIT = "simpleGreen";
	
	public LogsSavingClass(String sender, String messagType, String receiver) {
		this.sender1 = sender;
		this.messageType1 = messagType;
		this.receiver1 = receiver;
	}

	public void sqlLogsService() {
			logsSavingWithEntityManagerr();
	}

	public void logsSavingWithEntityManagerr() {
		EntityManager entityManager = JPAContainerFactory
				.createEntityManagerForPersistenceUnit(PERSISTENCE_UNIT);
		entityManager.getTransaction().begin();
		Communicatorlogs entityObject = new Communicatorlogs();
		entityObject.setSender(sender1);
		entityObject.setMmessagetype(messageType1);
		entityObject.setReceiver(receiver1);
		entityManager.persist(entityObject);
		entityManager.getTransaction().commit();
		entityManager.close();

	}
}