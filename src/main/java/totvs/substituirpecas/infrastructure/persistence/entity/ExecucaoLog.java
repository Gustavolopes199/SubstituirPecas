package totvs.substituirpecas.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "execucao_log")
public class ExecucaoLog {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String horaInicio;

    private String horaFim;

    private Integer quantidadeLinhas;

    private Integer quantidadeItensAlterados;

    private Integer quantidadeCancelados;

    private Integer valorGerado;

    private String status;

}
