package com.mhp.coding.challenges.dependency.inquiry;

import com.mhp.coding.challenges.dependency.notifications.EmailHandler;
import com.mhp.coding.challenges.dependency.notifications.PushNotificationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class InquiryService{

    private static final Logger LOG = LoggerFactory.getLogger(InquiryService.class);

    private final ApplicationEventPublisher applicationEventPublisher;

    public InquiryService(ApplicationEventPublisher applicationEventPublisher, EmailHandler emailHandler, PushNotificationHandler pushNotificationHandler) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.emailHandler = emailHandler;
        this.pushNotificationHandler = pushNotificationHandler;
    }

    private final EmailHandler emailHandler;
    private final PushNotificationHandler pushNotificationHandler;

    public void create(final Inquiry inquiry) {
        LOG.info("User sent inquiry: {}", inquiry);

        // Solution 1
        InquiryEvent inquiryEvent = new InquiryEvent(this, inquiry);
        applicationEventPublisher.publishEvent(inquiryEvent);

        // Solution 2
        emailHandler.sendEmail(inquiry);
        pushNotificationHandler.sendNotification(inquiry);
    }

}
