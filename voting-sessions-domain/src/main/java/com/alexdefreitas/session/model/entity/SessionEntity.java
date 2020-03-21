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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_AGENDA")
    private AgendaEntity agenda;

    @Column(name = "OPENING_DATE")
    @CreationTimestamp
    private LocalDateTime openingDate;
}
