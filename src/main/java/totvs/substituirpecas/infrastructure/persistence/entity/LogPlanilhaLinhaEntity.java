package totvs.substituirpecas.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogPlanilhaLinhaEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer pedido;

    private String referenciaOriginal;

    private String corOriginal;

    private String tamanhoOriginal;

    private Integer quantidadePendente;

    private String referenciaDestino;

    private String corDestino;

    private String tamanhoDestino;

    private BigDecimal preco;

    private Integer quantidadeAlterada;

    private String status;

    private LocalDateTime horaAlteracao;

}
