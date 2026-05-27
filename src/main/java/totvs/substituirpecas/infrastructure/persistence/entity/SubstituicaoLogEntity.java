package totvs.substituirpecas.infrastructure.persistence.entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubstituicaoLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_pedido", nullable = false)
    private Integer idPedido;

    @Column(name = "qtd_substituida")
    private Integer qtdSubstituida;

    @Column(name = "qtd_cancelada")
    private Integer qtdCancelada;

    @Column(name = "qtd_incluida")
    private Integer qtdIncluida;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private OffsetDateTime criadoEm;

    @PrePersist
    void prePersist() {
        if (criadoEm == null) criadoEm = OffsetDateTime.now().minusHours(3L);
    }

}
