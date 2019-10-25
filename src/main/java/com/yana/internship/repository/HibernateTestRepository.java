package com.yana.internship.repository;

import com.yana.internship.bean.TestBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class HibernateTestRepository implements TestRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public TestBean update(TestBean bean) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(TestBean.class.toString(), bean);
        return bean;
    }

    @Override
    public TestBean create(TestBean bean) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(TestBean.class.toString(),bean);
        return bean;
    }

    @Override
    public TestBean getById(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        TestBean bean = currentSession.get(TestBean.class, id);
        return bean;
    }

    @Override
    public List<TestBean> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery< TestBean > cq = cb.createQuery(TestBean.class);
        Root< TestBean > root = cq.from(TestBean.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public TestBean deleteById(int id) {
        Session session = sessionFactory.getCurrentSession();
        TestBean testBean = session.byId(TestBean.class).load(id);
        session.delete(testBean);
        return testBean;
    }
}
