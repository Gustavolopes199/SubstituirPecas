package totvs.substituirpecas.application.presentation.web.mapper;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import totvs.substituirpecas.infrastructure.spreadsheet.PlanilhaSubstituicaoCommand;

import java.io.IOException;

@Component
public class PlanilhaUploadMapper {

    public PlanilhaSubstituicaoCommand toCommand(MultipartFile file) {
        try {
            return new PlanilhaSubstituicaoCommand(file.getOriginalFilename(), file.getInputStream());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
