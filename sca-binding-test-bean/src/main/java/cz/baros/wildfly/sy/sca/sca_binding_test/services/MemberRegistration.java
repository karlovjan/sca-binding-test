package cz.baros.wildfly.sy.sca.sca_binding_test.services;

import cz.baros.wildfly.sy.sca.sca_binding_test.model.Member;
import cz.baros.wildfly.sy.sca.sca_binding_test.dao.MemberDAORepositoryImpl;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;


// The @Stateless annotation eliminates the need for manual transaction demarcation
@RequestScoped
@Transactional
public class MemberRegistration {

    @Inject
    private Logger log;

    @Inject
    private MemberDAORepositoryImpl em;

    @Inject
    private Event<Member> memberEventSrc;

    public void register(Member member) throws Exception {
        log.info("Registering " + member.getName());
        em.persist(member);
        memberEventSrc.fire(member);
    }
}