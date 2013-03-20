/**
 * 
 */
package org.sunilbrown.spring;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sunilbrown.spring.util.HibernateUtil;

/**
 * @author sunil
 *
 */
public class EventManager {
	public static final Logger log = LoggerFactory.getLogger(EventManager.class);
	public static void main(String[] args) {
        EventManager mgr = new EventManager();

        if (args[0].equals("store")) {
            mgr.createAndStoreEvent("My Event", new Date());
        }
        else if (args[0].equals("list")) {
            List events = mgr.listEvents();
            for (int i = 0; i < events.size(); i++) {
                Event theEvent = (Event) events.get(i);
                log.info(
                        "Event: " + theEvent.getTitle() + 
                        " Time: " + theEvent.getDate() +
                        " Id: " + theEvent.getId()
                );
            }
        }
        else if (args[0].equals("addpersontoevent")) {
            Long eventId = mgr.createAndStoreEvent("My Event", new Date());
            Long personId = mgr.createAndStorePerson("Foo", "Bar");
            log.info("Adding person " + personId + " to event " + eventId);
            mgr.addPersonToEvent(personId, eventId);
            log.info("Added person " + personId + " to event " + eventId);
        }

        HibernateUtil.getSessionFactory().close();
    }

    private Long createAndStoreEvent(String title, Date theDate) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Event theEvent = new Event();
        theEvent.setTitle(title);
        theEvent.setDate(theDate);
        session.save(theEvent);
        Long id = theEvent.getId();
        session.getTransaction().commit();
        return id;
    }
    
    private Long createAndStorePerson(String firstName, String lastName) {
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
        Person thePerson = new Person();
        thePerson.setFirstname(firstName);
        thePerson.setLastname(lastName);
        session.save(thePerson);
        Long id = thePerson.getId();
        session.getTransaction().commit();
        return id;
    }
    
    private List listEvents() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List result = session.createQuery("from Event").list();
        session.getTransaction().commit();
        return result;
    }
    
    private void addPersonToEvent(Long personId, Long eventId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Person aPerson = (Person) session.load(Person.class, personId);
        Event anEvent = (Event) session.load(Event.class, eventId);
        aPerson.getEvents().add(anEvent);

        session.getTransaction().commit();
    }
}
