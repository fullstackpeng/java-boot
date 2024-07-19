package com.fullstackpeng.common.data.jpa.core.identifier;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;

import java.io.Serializable;
import java.util.EnumSet;

import static org.hibernate.generator.EventTypeSets.INSERT_ONLY;

public class DefaultIdentifierGenerator implements BeforeExecutionGenerator {
    private static final Sequence sequence = new Sequence(null);

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object owner, Object currentValue,
                                 EventType eventType) {
        if (currentValue != null) {
            return (Serializable) currentValue;
        }
        return sequence.nextId() + "";
    }

    @Override
    public EnumSet<EventType> getEventTypes() {
        return INSERT_ONLY;
    }
}
