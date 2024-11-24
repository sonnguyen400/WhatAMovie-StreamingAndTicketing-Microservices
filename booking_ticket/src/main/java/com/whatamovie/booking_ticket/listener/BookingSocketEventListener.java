package com.whatamovie.booking_ticket.listener;

import com.whatamovie.booking_ticket.model.TokenAuthenticationSocketPrincipal;
import com.whatamovie.booking_ticket.service.SeatOrderedService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingSocketEventListener {
    SimpMessagingTemplate messagingTemplate;
    SeatOrderedService seatOrderedService;
    @EventListener
    public void handleSocketDisconnect(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        TokenAuthenticationSocketPrincipal user = (TokenAuthenticationSocketPrincipal) headerAccessor.getUser();
        if(user != null) {
            messagingTemplate.convertAndSend("/booking/public", seatOrderedService.cancelSeatOrderBySession(headerAccessor.getSessionId()));
        }
    }
}
