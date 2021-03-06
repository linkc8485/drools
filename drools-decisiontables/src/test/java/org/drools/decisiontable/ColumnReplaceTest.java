package org.drools.decisiontable;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.drools.compiler.compiler.DecisionTableFactory;
import org.drools.core.definitions.rule.impl.RuleImpl;
import org.junit.Test;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.DecisionTableConfiguration;
import org.kie.internal.builder.DecisionTableInputType;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.api.io.ResourceType;

public class ColumnReplaceTest {

	@Test
	public void testAutoFocusToLockOnActiveReplacement () throws FileNotFoundException {
		
        DecisionTableConfiguration dTableConfiguration = KnowledgeBuilderFactory.newDecisionTableConfiguration();
        dTableConfiguration.setInputType( DecisionTableInputType.CSV );

        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("columnReplaceTest.csv", getClass()), ResourceType.DTABLE, dTableConfiguration);
        if (kbuilder.hasErrors())
        {
            System.out.println(kbuilder.getErrors());
            fail("Knowledge builder cannot compile package!");
        }
        System.out.println(DecisionTableFactory.loadFromInputStream(new FileInputStream(new File("src/test/resources/org/drools/decisiontable/columnReplaceTest.csv")), dTableConfiguration));
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        
        assertTrue(((RuleImpl)kbase.getRule("org.drools.decisiontable", "lockOnActiveRule")).isLockOnActive());
        // lock-on-active was not set on autoFocusRule, so it should be by default false
        assertFalse(((RuleImpl)kbase.getRule("org.drools.decisiontable", "autoFocusRule")).isLockOnActive());
        
        assertFalse(((RuleImpl)kbase.getRule("org.drools.decisiontable", "lockOnActiveRule")).getAutoFocus());
        // auto-focus was set to be true, so it should be true
        assertTrue(((RuleImpl)kbase.getRule("org.drools.decisiontable", "autoFocusRule")).getAutoFocus());
	}
}
