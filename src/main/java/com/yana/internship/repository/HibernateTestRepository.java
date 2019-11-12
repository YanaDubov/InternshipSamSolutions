package com.yana.internship.repository;

import com.yana.internship.entity.Lang;
import com.yana.internship.entity.LocalEntity;
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
        TestEntity thisBean = getById(bean.getId(), Lang.en);
        Session currentSession = sessionFactory.getCurrentSession();
        thisBean.setName(bean.getName());
        for (int i=0;i<bean.getLocals().size();i++) {
            thisBean.getLocals().get(i).setLocalName( bean.getLocals().get(i).getLocalName());
           // currentSession.saveOrUpdate(thisBean.getLocals().get(i));
        }
        currentSession.saveOrUpdate(TestEntity.class.toString(), thisBean);
        return thisBean;
    }

    @Override
    public TestEntity create(TestEntity bean) {
        Session currentSession = sessionFactory.getCurrentSession();
        bean.getLocals().forEach(localEntity -> {
            localEntity.setTestEntity(bean);
        });
        currentSession.save(TestEntity.class.toString(),bean);

        return bean;
    }

    @Override
    public TestEntity getById(int id, Lang lang) {
        Session currentSession = sessionFactory.getCurrentSession();
        TestEntity bean = currentSession.get(TestEntity.class, id);
        bean.getLocals().forEach(localEntity -> {
            if(localEntity.getLang().equals(lang)){
                bean.setName(localEntity.getLocalName());
            }
        });
        return bean;
    }

    @Override
    public List<TestEntity> getAll(Lang lang) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<TestEntity> cq = cb.createQuery(TestEntity.class);
        Root<TestEntity> root = cq.from(TestEntity.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        List<TestEntity> list = query.getResultList();
        list.forEach(testEntity -> {
            testEntity.getLocals().forEach(localEntity -> {
                if(localEntity.getLang().equals(lang)){
                    testEntity.setName(localEntity.getLocalName());
                }
            });
        });
        return list;
    }

    @Override
    public int deleteById(int id) {
        Session session = sessionFactory.getCurrentSession();
        TestEntity testEntity = session.byId(TestEntity.class).load(id);
        session.delete(testEntity);
        return id;
    }
}
