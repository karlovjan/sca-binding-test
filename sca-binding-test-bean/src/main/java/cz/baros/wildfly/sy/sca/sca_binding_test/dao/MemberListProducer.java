package cz.baros.wildfly.sy.sca.sca_binding_test.dao;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

import cz.baros.wildfly.sy.sca.sca_binding_test.model.Member;

@RequestScoped
public class MemberListProducer {

    @Inject
    private MemberDAORepositoryImpl memberRepository;

    private List<Member> members;

    // @Named provides access the return value via the EL variable name "members" in the UI (e.g.,
    // Facelets or JSP view)
    @Produces
    @Named
    public List<Member> getMembers() {
        return members;
    }

    public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Member member) {
        retrieveAllMembers();
    }

    @PostConstruct
    public void retrieveAllMembers() {
        members = memberRepository.findAll();
    }
}