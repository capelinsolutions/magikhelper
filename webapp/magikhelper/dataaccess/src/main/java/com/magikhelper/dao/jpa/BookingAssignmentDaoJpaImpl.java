/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magikhelper.dao.jpa;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.magikhelper.dao.BookingAssignmentDao;
import com.magikhelper.entities.BookingAssignment;

@Repository(BookingAssignmentDao.name)
public class BookingAssignmentDaoJpaImpl extends GenericDaoJpaImpl<BookingAssignment, Integer> implements BookingAssignmentDao {

    private static final Log log = LogFactory.getLog(BookingAssignmentDaoJpaImpl.class);

    public BookingAssignmentDaoJpaImpl() {
        super(BookingAssignment.class);
    }

}
