package Modele;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

class TableHeader extends PdfPageEventHelper {
    PdfTemplate total;

    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {
        total = writer.getDirectContent().createTemplate(30, 16);
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
    	
        PdfPTable table = new PdfPTable(3);
        
        try {
        	
        	 if(writer.getPageNumber()!=1){
                 table.setWidths(new int[]{24, 24, 2});
                 table.setTotalWidth(527);
                 table.setLockedWidth(true);
                 table.getDefaultCell().setFixedHeight(20);
                 table.getDefaultCell().setBorder(0);
                 table.addCell("Trivia-Marketing © 2013");
                 table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                 table.addCell(String.format("Page %d /", writer.getPageNumber()));
                 PdfPCell cell = new PdfPCell(Image.getInstance(total));
                 cell.setBorder(0);
                 table.addCell(cell);
                 table.writeSelectedRows(0, -1, 34, 25, writer.getDirectContent());
        	 }

        }
        catch(DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }

    @Override
    public void onCloseDocument(PdfWriter writer, Document document) {
        ColumnText.showTextAligned(total, Element.ALIGN_LEFT,
                new Phrase(String.valueOf(writer.getPageNumber() - 1)),
                2, 2, 0);
    }
    
   
}
