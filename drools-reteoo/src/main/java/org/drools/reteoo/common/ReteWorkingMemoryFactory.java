package org.drools.reteoo.common;

import org.drools.core.SessionConfiguration;
import org.drools.core.common.InternalAgenda;
import org.drools.core.common.InternalFactHandle;
import org.drools.core.common.InternalWorkingMemory;
import org.drools.core.common.WorkingMemoryFactory;
import org.drools.core.event.AgendaEventSupport;
import org.drools.core.event.RuleEventListenerSupport;
import org.drools.core.event.RuleRuntimeEventSupport;
import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.spi.FactHandleFactory;
import org.kie.api.runtime.Environment;

import java.io.Serializable;

public class ReteWorkingMemoryFactory implements WorkingMemoryFactory, Serializable {

    private static final WorkingMemoryFactory INSTANCE = new ReteWorkingMemoryFactory();

    public static WorkingMemoryFactory getInstance() {
        return INSTANCE;
    }

    public InternalWorkingMemory createWorkingMemory(long id, InternalKnowledgeBase kBase, SessionConfiguration config, Environment environment) {
        return new ReteWorkingMemory(id, kBase, false, config, environment);
    }

    public InternalWorkingMemory createWorkingMemory(long id, InternalKnowledgeBase kBase, FactHandleFactory handleFactory, long propagationContext, SessionConfiguration config, InternalAgenda agenda, Environment environment) {
        return new ReteWorkingMemory(id, kBase, handleFactory, propagationContext, config, agenda, environment);
    }

    public InternalWorkingMemory createWorkingMemory(long id, InternalKnowledgeBase kBase, FactHandleFactory handleFactory, InternalFactHandle initialFactHandle, long propagationContext, SessionConfiguration config, Environment environment, RuleRuntimeEventSupport workingMemoryEventSupport, AgendaEventSupport agendaEventSupport, RuleEventListenerSupport ruleEventListenerSupport, InternalAgenda agenda) {
        return new ReteWorkingMemory(id, kBase, handleFactory, initialFactHandle, propagationContext, config, environment, workingMemoryEventSupport, agendaEventSupport, ruleEventListenerSupport, agenda);
    }
}
