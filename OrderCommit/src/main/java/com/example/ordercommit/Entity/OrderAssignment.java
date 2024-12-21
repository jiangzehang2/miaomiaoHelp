package com.example.ordercommit.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "order_assignments", schema = "test")
public class OrderAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "runner_id")
    private User runner;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "assigned_at")
    private Instant assignedAt;

    @Column(name = "completed_at")
    private Instant completedAt;

    @ColumnDefault("'assigned'")
    @Lob
    @Column(name = "status")
    private String status;

}