package com.alexdefreitas.session.model.entity;

import com.alexdefreitas.agenda.model.entity.AgendaEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "session")
public class SessionEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "AGENDA_ID")
    private AgendaEntity agenda;

    @Column(name = "OPENING_DATE")
    @CreationTimestamp
    private LocalDateTime openingDate;

    @Column(name = "CLOSING_DATE")
    private LocalDateTime closingDate;

    @Column(name = "CLOSED")
    private boolean closed;
}
