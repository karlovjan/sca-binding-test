package cz.baros.wildfly.sy.sca.sca_binding_test.dao;

import javax.enterprise.context.ApplicationScoped;

import cz.baros.wildfly.sy.sca.sca_binding_test.model.Member;

@ApplicationScoped
public class MemberDAORepositoryImpl extends AbstractDAORepositoryImpl<Member> {

	private static final long serialVersionUID = 1L;

	public MemberDAORepositoryImpl() {
		super(Member.class);
		
	}

}
