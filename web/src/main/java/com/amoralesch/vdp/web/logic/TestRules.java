package com.amoralesch.vdp.web.logic;

import com.amoralesch.vdp.web.logic.fluent.FieldBuilder;
import com.amoralesch.vdp.web.logic.fluent.RuleBuilder;

import static com.amoralesch.vdp.web.logic.fluent.FieldBuilder.newField;
import static com.amoralesch.vdp.web.logic.fluent.RuleBuilder.newRule;

public class TestRules extends Rules {
    public TestRules() {
        super();

        addRule(newRule("GET", "/hola/mundo")
            .build());

        addRule(newRule("POST", "/post/test")
            .forwardTo("/api/pco")
            .withRequestField(newField("id")
                .withDescription("ID")
                .withMaxLen(5)
                .mapTo("requestId")
                .build())
            .withRequestField(newField("user.firstName")
                .withDescription("user's first name")
                .isRequired()
                .withMaxLen(15)
                .mapTo("userFirstName")
                .build())
            .withRequestField(newField("user.lastName")
                .withDescription("user's last name")
                .withMaxLen(15)
                .build())
            .withResponseField(newField("external")
                .mapTo("connected?")
                .mustEqual("yes")
                .build())
            .withResponseField(newField("response.working")
                .withDescription("working")
                .isRequired()
                .mapTo("response.value")
                .build())
            .build());
    }
}