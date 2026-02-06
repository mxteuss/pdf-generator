package mxteuss.java.service;

import lombok.Data;
import mxteuss.java.model.PdfModel;
import org.openpdf.text.*;
import org.openpdf.text.pdf.BaseFont;
import org.openpdf.text.pdf.PdfContentByte;
import org.openpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Data
@Service
public class PdfService {

    public byte[] gerarPdfABNT(PdfModel pdfModel)
    {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            document.setMargins(
                    72f,
                    56.7f,
                    85.05f,
                    56.7f
            );
            document.open();

            Font fontNormal = new Font(Font.TIMES_ROMAN, 12, Font.NORMAL);

                Paragraph tittle = new Paragraph(pdfModel.getInstituicao().toUpperCase(), fontNormal);
                tittle.setAlignment(Paragraph.ALIGN_CENTER);
                tittle.setSpacingBefore(50f);
                document.add(tittle);

                Paragraph course =  new Paragraph(pdfModel.getCurso().toUpperCase(), fontNormal);
                course.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(course);

                Paragraph name = new Paragraph(pdfModel.getNome().toUpperCase(), fontNormal);
                name.setAlignment(Paragraph.ALIGN_CENTER);
                name.setSpacingBefore(100f);
                document.add(name);

                Paragraph title = new Paragraph(pdfModel.getTitulo().toUpperCase(), fontNormal);
                title.setAlignment(Paragraph.ALIGN_CENTER);
                title.setSpacingBefore(180f);
                document.add(title);
                System.out.println(pdfModel.getCidade());

                PdfContentByte canvas = writer.getDirectContent();


                canvas.setFontAndSize(BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED), 12);
                float PosicaoCidade = 100f;
                float PosicaoAno = 85f;

                float Centro = PageSize.A4.getWidth() / 2;

            canvas.showTextAligned(PdfContentByte.ALIGN_CENTER,
                    pdfModel.getCidade().toUpperCase(),
                    Centro, PosicaoCidade,0);

            canvas.showTextAligned(PdfContentByte.ALIGN_CENTER,
                    pdfModel.getAno(),
                    Centro, PosicaoAno,0);
            document.close();

            return outputStream.toByteArray();

        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
