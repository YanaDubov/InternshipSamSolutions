package com.yana.internship.repository;

import com.yana.internship.entity.TestEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

    private final SessionFactory sessionFactory;

    public HibernateTestRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public TestEntity update(TestEntity bean) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(TestEntity.class.toString(), bean);
        return bean;
    }

    @Override
    public TestEntity create(TestEntity bean) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(TestEntity.class.toString(),bean);
        return bean;
    }

    @Override
    public TestEntity getById(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        TestEntity bean = currentSession.get(TestEntity.class, id);
        return bean;
    }

    @Override
    public List<TestEntity> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<TestEntity> cq = cb.createQuery(TestEntity.class);
        Root<TestEntity> root = cq.from(TestEntity.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public int deleteById(int id) {
        Session session = sessionFactory.getCurrentSession();
        TestEntity testEntity = session.byId(TestEntity.class).load(id);
        session.delete(testEntity);
        return id;
    }
}
