package totvs.substituirpecas.infrastructure.spreadsheet;

import org.springframework.stereotype.Component;
import totvs.substituirpecas.application.dto.transport.PlanilhaResultado;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class PlanilhaResultadoMaker {

    public byte[] resultToExcel(List<PlanilhaResultado> resultados){
        try{
         ByteArrayOutputStream out = new ByteArrayOutputStream();
         OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);


         writer.write('\uFEFF');
         writer.write("pedido;nomeCliente;cnpjCliente;referenciaOriginal;tamanhoOriginal;corOriginal;referenciaDestino;tamanhoDestino;corDestino\n");



            for (PlanilhaResultado resultado : resultados) {
                writer.write(String.join(";",
                        str(resultado.pedido()),
                        str(resultado.nomeCLiente()),
                        str(resultado.cnpjCliente()),
                        str(resultado.referenciaOriginal()),
                        str(resultado.tamanhoOriginal()),
                        str(resultado.corOriginal()),
                        str(resultado.referenciaDestino()),
                        str(resultado.tamanhoDestino()),
                        str(resultado.corDestino())));

                writer.write("\n");
            }

            writer.flush();

        return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static String str(Object o) {
        return o == null ? "" : o.toString();
    }
    private static String esc(String s) {
        if (s == null) return "";
        if (s.contains(";") || s.contains("\"") || s.contains("\n")) {
            return "\"" + s.replace("\"", "\"\"") + "\"";
        }
        return s;
    }

}
