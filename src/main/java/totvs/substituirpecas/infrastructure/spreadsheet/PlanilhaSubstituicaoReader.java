package totvs.substituirpecas.infrastructure.spreadsheet;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import totvs.substituirpecas.application.dto.PlanilhaLinha;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class PlanilhaSubstituicaoReader {

    public List<PlanilhaLinha> execute(InputStream file) throws IOException {

        Workbook workbook = WorkbookFactory.create(file);

        List<PlanilhaLinha> planilhaLinha = new ArrayList<>();

        Sheet sheet = workbook.getSheetAt(0);
        for (Row  row : sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }

            PlanilhaLinha object = new PlanilhaLinha();

            Cell pedido = row.getCell(0);
            Cell refOriginal =  row.getCell(1);
            Cell tamanhoOrignal = row.getCell(2);
            Cell corOriginal = row.getCell(3);
            Cell refDestino = row.getCell(4);

            if (pedido != null) {
                object.setPedido((int) pedido.getNumericCellValue());
            }
            if (refOriginal != null) {
                object.setReferenciaOriginal(getCellValueAsString(refOriginal));
            }
            if (refDestino != null) {
                object.setReferenciaDestino(getCellValueAsString(refDestino));
            }
            if (tamanhoOrignal != null) {
                object.setTamanhoOriginal(getCellValueAsString(tamanhoOrignal));
            }
            if (corOriginal != null) {
                object.setCorOriginal(getCellValueAsString(corOriginal));
            }
            planilhaLinha.add(object);
        }

        return planilhaLinha;
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }

        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();

            case NUMERIC -> {
                if (DateUtil.isCellDateFormatted(cell)) {
                    yield cell.getLocalDateTimeCellValue().toString();
                }

                double value = cell.getNumericCellValue();

                if (value == Math.floor(value)) {
                    yield String.valueOf((long) value);
                }

                yield String.valueOf(value);
            }

            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());

            case FORMULA -> cell.getCellFormula();

            case BLANK -> "";

            default -> "";
        };
    }

}
