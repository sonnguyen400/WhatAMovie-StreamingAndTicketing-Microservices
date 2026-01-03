package com.sonnguyen.snbuilding.infrastructure.persistence.entity;

import com.sonnguyen.common.data.persistence.entity.AuditingEntity;
import com.sonnguyen.snbuilding.infrastructure.support.enums.RoomStatus;
import com.sonnguyen.snbuilding.infrastructure.support.enums.ScreenType;
import com.sonnguyen.snbuilding.infrastructure.support.enums.SoundSystem;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "room_entity")
public class RoomEntity extends AuditingEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "theater_id")
    private UUID theaterId;

    @Column(name = "code")
    private String code;

    @Column(name = "screen_type")
    @Enumerated(EnumType.STRING)
    private ScreenType screenType;

    @Column(name = "sound_system")
    @Enumerated(EnumType.STRING)
    private SoundSystem soundSystem;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "seat_layout_template")
    private String seatLayoutTemplate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RoomStatus status;

}
