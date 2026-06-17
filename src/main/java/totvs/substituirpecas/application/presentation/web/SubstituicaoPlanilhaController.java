package totvs.substituirpecas.application.presentation.web;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import totvs.substituirpecas.application.port.in.GerarPlanilhaUseCase;
import totvs.substituirpecas.application.port.in.PlanilhaSubstituicaoUseCase;
import totvs.substituirpecas.application.presentation.web.mapper.PlanilhaUploadMapper;

@Controller
@RequestMapping("/importar")
public class SubstituicaoPlanilhaController {

    private final PlanilhaUploadMapper mapper;
    private final PlanilhaSubstituicaoUseCase useCase;

    public SubstituicaoPlanilhaController(PlanilhaUploadMapper mapper,
                                          PlanilhaSubstituicaoUseCase useCase) {
        this.mapper = mapper;
        this.useCase = useCase;
    }

    @PostMapping("/substituir")
    public ResponseEntity<?> importarPlanilha(@RequestParam("arquivo")MultipartFile file){
            useCase.executar(mapper.toCommand(file));
            return ResponseEntity.status(HttpStatus.OK).body("Ok");
    }
//
//   @GetMapping("/resultados/excel")
//   public ResponseEntity<byte[]> exportarPlanilha(){
//
//   }

}
