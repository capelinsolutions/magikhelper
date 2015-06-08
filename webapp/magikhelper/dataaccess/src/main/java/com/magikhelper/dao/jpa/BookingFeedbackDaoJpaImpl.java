/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magikhelper.dao.jpa;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.magikhelper.dao.BookingFeedbackDao;
import com.magikhelper.entities.BookingFeedback;

@Repository(BookingFeedbackDao.name)
public class BookingFeedbackDaoJpaImpl extends GenericDaoJpaImpl<BookingFeedback, Integer> implements BookingFeedbackDao {

    private static final Log log = LogFactory.getLog(BookingFeedbackDaoJpaImpl.class);

    public BookingFeedbackDaoJpaImpl() {
        super(BookingFeedback.class);
    }

}
