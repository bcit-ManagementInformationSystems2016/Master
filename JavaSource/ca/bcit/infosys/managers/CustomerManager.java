/**
 * 
 */
package ca.bcit.infosys.managers;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ca.bcit.infosys.models.Customer;

/**
 * Handles CRUD actions for Customer
 * @author StuArt & cbow
 *
 */

@Dependent
@Stateless
public class CustomerManager {
     @PersistenceContext(unitName="BluehostTesty") EntityManager em;

        /**
         * Find Customer record from database.
         * 
         * @param id
         *            primary key for record.
         * @return the Category record with key = id, null if not found.
         */
        public Customer find(int id) {
            return em.find(Customer.class, id);
            }

        /**
         * Persist Customer record into database. id must be unique.
         * 
         * @param cust
         *            the record to be persisted.
         */
        public void persist(Customer cust) {
            em.persist(cust);
        }

        /**
         * merge Customer record fields into existing database record.
         * 
         * @param cust
         *            the record to be merged.
         */
        public void merge(Customer cust) {
            em.merge(cust);
        }

        /**
         * Remove cust from database.
         * 
         * @param cust
         *            record to be removed from database
         */
        public void remove(Customer cust) {
            //attach category
            cust = find(cust.getCustomerID());
            em.remove(cust);
        }

        /**
         * Return Customer table as array of customers.
         * 
         * @return Customer[] of all records in Customer table
         */
        public Customer[] getAll() {
            TypedQuery<Customer> query = em.createQuery("select c from Customer c", Customer.class); 
            java.util.List<Customer> categories = query.getResultList();
            Customer[] catarray = new Customer[categories.size()];
            for (int i=0; i < catarray.length; i++) {
                catarray[i] = categories.get(i);
            }
            return catarray;
        }
    

}