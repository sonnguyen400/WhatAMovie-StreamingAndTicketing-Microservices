package com.sonnguyen.snbuilding.infrastructure.persistence.entity;

import com.sonnguyen.common.data.persistence.entity.AuditingEntity;
import com.sonnguyen.snbuilding.infrastructure.support.enums.SeatStatus;
import com.sonnguyen.snbuilding.infrastructure.support.enums.SeatType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "seat_entity")
public class SeatEntity extends AuditingEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "room_id")
    private UUID roomId;

    @Column(name = "row_label")
    private String rowLabel;

    @Column(name = "seat_number")
    private String seatNumber;

    @Transient
    private SeatType type;

    @Transient
    private SeatStatus status;

    @Column(name = "x_position")
    private Integer xPosition;

    @Column(name = "y_position")
    private Integer yPosition;

}
