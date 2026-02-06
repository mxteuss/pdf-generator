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
            Font fontBold = new Font(Font.TIMES_ROMAN, 14, Font.BOLD);
            Font fontItalic = new Font(Font.TIMES_ROMAN, 12, Font.ITALIC);

            // ---------------------------------// CAPA ------------------------------------------------------------

            Paragraph instituicao = new Paragraph(pdfModel.getInstituicao().toUpperCase(), fontBold);
                instituicao.setAlignment(Paragraph.ALIGN_CENTER);
                instituicao.setSpacingBefore(50f);
                document.add(instituicao);

                Paragraph curso =  new Paragraph(pdfModel.getCurso().toUpperCase(), fontNormal);
                curso.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(curso);

                Paragraph nome = new Paragraph(pdfModel.getNome().toUpperCase(), fontBold);
                nome.setAlignment(Paragraph.ALIGN_CENTER);
                nome.setSpacingBefore(100f);
                document.add(nome);

                Paragraph titulo = new Paragraph(pdfModel.getTitulo().toUpperCase(), fontBold);
                titulo.setAlignment(Paragraph.ALIGN_CENTER);
                titulo.setSpacingBefore(180f);
                document.add(titulo);
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

// ---------------------------------// FOLHA DE ROSTO ------------------------------------------------------------
            document.newPage();
            document.add(nome);
            document.add(titulo);
            Paragraph paragraph =  new Paragraph(String.format("%s para obtenção do título de %s em %s apresentado à %s ",
                    pdfModel.getTipoTrabalho(),
                    pdfModel.getObjetivo(),
                    pdfModel.getCurso(),
                    pdfModel.getInstituicao()), fontNormal);
            paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
            paragraph.setSpacingBefore(50f);
            document.add(paragraph);

            Paragraph orientador = new Paragraph("Orientador:" + pdfModel.getOrientador(), fontNormal);
            orientador.setAlignment(Paragraph.ALIGN_RIGHT);
            orientador.setSpacingBefore(40f);

            document.add(orientador);
            canvas.setFontAndSize(BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED), 12);

            canvas.showTextAligned(PdfContentByte.ALIGN_CENTER,
                    pdfModel.getCidade().toUpperCase(),
                    Centro, PosicaoCidade,0);

            canvas.showTextAligned(PdfContentByte.ALIGN_CENTER,
                    pdfModel.getAno(),
                    Centro, PosicaoAno,0);


            // ---------------------------------// DEDICATÓRIA ------------------------------------------------------------

            document.newPage();
            Paragraph dedicatoria = new Paragraph(pdfModel.getDedicatoria(), fontNormal);
            dedicatoria.setAlignment(Paragraph.ALIGN_RIGHT);
            dedicatoria.setSpacingBefore(50f);
            document.add(dedicatoria);
            // ---------------------------------// AGRADECIMENTOS ------------------------------------------------------------

            document.newPage();
            Paragraph titulo3 = new Paragraph("AGRADECIMENTOS", fontBold);
            titulo3.setAlignment(Paragraph.ALIGN_CENTER);
            Paragraph agradecimentos = new  Paragraph(pdfModel.getAgradecimentos(), fontNormal);
            agradecimentos.setAlignment(Paragraph.ALIGN_CENTER);
            agradecimentos.setSpacingBefore(50f);
            document.add(titulo3);
            document.add(agradecimentos);

            // ---------------------------------// Epígrafe ------------------------------------------------------------

            document.newPage();
            Paragraph epigrafe = new Paragraph(pdfModel.getEpigrafe(), fontItalic);
            epigrafe.setAlignment(Paragraph.ALIGN_RIGHT);
            epigrafe.setSpacingBefore(140f);
            document.add(epigrafe);


            // ---------------------------------// RESUMO ------------------------------------------------------------

            document.newPage();
            Paragraph titulo4 = new Paragraph("RESUMO", fontBold);
            titulo4.setAlignment(Paragraph.ALIGN_CENTER);
            Paragraph resumo = new  Paragraph(pdfModel.getResumo(), fontNormal);
            resumo.setAlignment(Paragraph.ALIGN_CENTER);
            resumo.setSpacingBefore(50f);
            Paragraph palavrasChave = new Paragraph("Palavras-chave:" + pdfModel.getPalavrasChave().replace(",", "."), fontNormal);
            palavrasChave.setAlignment(Paragraph.ALIGN_CENTER);
            palavrasChave.setSpacingBefore(10f);

            document.add(titulo4);
            document.add(resumo);
            document.add(palavrasChave);


            // ---------------------------------// ABSTRACT ------------------------------------------------------------

            document.newPage();

            Paragraph titulo5 = new Paragraph("ABSTRACT", fontBold);
            titulo5.setAlignment(Paragraph.ALIGN_CENTER);
            Paragraph resumoEn = new  Paragraph(pdfModel.getResumoEn(), fontNormal);
            resumo.setAlignment(Paragraph.ALIGN_CENTER);
            resumo.setSpacingBefore(50f);
            Paragraph keywords = new Paragraph("Keywords:" + pdfModel.getKeywords().replace(",", "."), fontNormal);
            palavrasChave.setAlignment(Paragraph.ALIGN_CENTER);
            palavrasChave.setSpacingBefore(10f);

            document.add(titulo5);
            document.add(resumoEn);
            document.add(keywords);

            document.close();
            return outputStream.toByteArray();

        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
