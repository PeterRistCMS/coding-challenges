package com.mhp.coding.challenges.dependency.inquiry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class InquiryService{

    private static final Logger LOG = LoggerFactory.getLogger(InquiryService.class);

    private final ApplicationEventPublisher applicationEventPublisher;

    public InquiryService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void create(final Inquiry inquiry) {
        LOG.info("User sent inquiry: {}", inquiry);

        InquiryEvent inquiryEvent = new InquiryEvent(this, inquiry);
        applicationEventPublisher.publishEvent(inquiryEvent);
    }

}
