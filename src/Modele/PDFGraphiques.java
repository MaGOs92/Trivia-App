package Modele;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.MeterInterval;
import org.jfree.chart.plot.MeterPlot;
import org.jfree.data.Range;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.DefaultValueDataset;

import java.util.Date;

import com.lowagie.text.Anchor;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Chapter;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;

import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;



public class PDFGraphiques {
	
	  private static Font titreFont = new Font(Font.TIMES_ROMAN, 22,
		      Font.BOLD);	  
	  private static Font catFont = new Font(Font.TIMES_ROMAN, 18,
		      Font.BOLD);  
	  private static Font smallBold = new Font(Font.TIMES_ROMAN, 12,
		      Font.BOLD);
	
    public static JFreeChart generatePieChart(Colonne colonne) {
    	
        DefaultPieDataset dataSet = new DefaultPieDataset();
        	
        	dataSet.setValue("Filled entries", colonne.getNbCasesRemplies());
        	dataSet.setValue("Empty entries", colonne.getNbCasesVides());
        	dataSet.setValue("Incorrect entries", colonne.getNbCasesIncorrectes());
 
        JFreeChart chart = ChartFactory.createPieChart("Data quality repartition", dataSet, true, true, false);
        
        chart.setBackgroundPaint(Color.white); 
        chart.setBorderVisible(false);
        
        return chart;
    }
    
    public static JFreeChart createMeterChart(Colonne colonne) {
    	
    	DefaultValueDataset dataset = new DefaultValueDataset(colonne.getPourcentagesCasesRemplies());

        MeterPlot plot = new MeterPlot(dataset);
        
        if (colonne.getMapping().getId() == 1)
        {
        	plot.addInterval(new MeterInterval("High", new Range(95.0, 100.0)));

        }
        else
        {
        	plot.addInterval(new MeterInterval("High", new Range(75.0, 100.0)));
        }

        plot.setUnits("%");

        JFreeChart chart = new JFreeChart("Data quality overview", JFreeChart.DEFAULT_TITLE_FONT, plot, false);
        chart.setBackgroundPaint(Color.white); 
        return chart;
    }
    
    public static void addMetaData(Document document, DataAuditModele model){
        document.addTitle("DataAudit" + model.getNomTable());
        document.addSubject("Data audit reporting");
        document.addAuthor("Trivia Marketing");
        document.addCreator("Trivia Marketing");
    }
    
    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
          paragraph.add(new Paragraph(" "));
        }
    }
    
    public static void addTitlePage(Document document, DataAuditModele model)
    	      throws DocumentException, MalformedURLException, IOException {
    	
    		String valeursSelectionnees = "";
    		
    		float nbValeurRempliesFichier = 0;
    		
    		float nbValeurRempliesSelection = 0;
    		
    		float nbValeurTotalesFichier = model.getNbColonnesTotales() * model.getNbLignesTotales();
    		
    		float nbValeurTotalesSelection = model.getNbLignesSelectionnee() * model.getNbLignesTotales();
    		
    		
            for (int i = 0; i < model.getNbColonnesTotales(); i++){
            	nbValeurRempliesFichier += model.getTabColonne()[i].getNbCasesRemplies();
            }
            
            float pourcentageRemplissageFichier = (nbValeurRempliesFichier / nbValeurTotalesFichier) * 100;
            System.out.println(nbValeurRempliesFichier);
            System.out.println(nbValeurTotalesFichier);
            System.out.println(pourcentageRemplissageFichier);
    		
            for (int i = 0; i < model.getNbColonnesTotales(); i++){
            	if (model.getTabColonne()[i].isSelectionnee()){
            		if (i == model.getNbColonnesTotales() - 1)
            			valeursSelectionnees += model.getTabColonne()[i].getNomColonne();
            		else
            			valeursSelectionnees += model.getTabColonne()[i].getNomColonne() + " / ";
            		
            		nbValeurRempliesSelection += model.getTabColonne()[i].getNbCasesRemplies();
            	}
            }
            
            float pourcentageRemplissageSelection = (nbValeurRempliesSelection / nbValeurTotalesSelection) * 100;
            System.out.println(nbValeurRempliesSelection);
            System.out.println(nbValeurTotalesSelection);
            System.out.println(pourcentageRemplissageSelection);
    	
    		Image image = com.lowagie.text.Image.getInstance("Img\\trivia.jpg");
    		document.add(image);
    		
    	    Paragraph titre = new Paragraph();
    	    // We add one empty line
    	    addEmptyLine(titre, 3);
    	    // Lets write a big header
    	    titre.add(new Paragraph("Data Audit " + model.getNomTable(), titreFont));

    	    addEmptyLine(titre, 3);
    	    
    	    titre.setAlignment(Element.ALIGN_CENTER);
    	    
    	    document.add(titre);
    	    
    	    Paragraph preface = new Paragraph();
    	    
    	    preface.add(new Paragraph("Quick summary :", catFont));
    	    
    	    addEmptyLine(preface, 1);
    	    preface.add(new Paragraph("Audited file : " + model.getNomTable() +
    	    		"\n Customer : " + model.getNomClient() +
    	    		"\n Total number of fields : " + model.getNbColonnesTotales() +
    	    		"\n Total number of entries : " + model.getNbLignesTotales() +
    	    		"\n File's fill rate : " + pourcentageRemplissageFichier + " %" +
    	    		"\n Selected values's fill rate : " + pourcentageRemplissageSelection + " %" +
    	    		"\n Number of selected values : " + model.getNbLignesSelectionnee() +
    	    		"\n Selected values : " + valeursSelectionnees,smallBold));
    	    
    	    document.add(preface);
    	    
    	    Paragraph generated = new Paragraph();
    	    
    	    addEmptyLine(generated, 14);

    	    generated.add(new Paragraph("Report generated by: " + System.getProperty("user.name") + ", " + new Date(),
    	        smallBold));
    	    
    	    titre.setAlignment(Element.ALIGN_LEFT);
    	    
    	    document.add(generated);
    	    
    	    
    	    document.newPage();
    	  }
    
    public static void writeChartToPDF(String fileName, DataAuditModele model) {
        PdfWriter writer = null;
     
        Document document = new Document();
     
        try {
            writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            
            addMetaData(document, model);
            
            addTitlePage(document, model);
            int j = 0;
            for (int i = 0; i < model.getNbColonnesTotales(); i++){
            	if (model.getTabColonne()[i].isSelectionnee()){
            		j++;
            		addContent(document, model.getTabColonne()[i], writer, j);
            	}
            }
     
        } catch (Exception e) {
            e.printStackTrace();
        }
        document.close();
    }
    
    public static void createTable(Paragraph subCatPart, String[] vf)
    	      throws BadElementException {
    	    PdfPTable table = new PdfPTable(2);

    	    // t.setBorderColor(BaseColor.GRAY);
    	    // t.setPadding(4);
    	    // t.setSpacing(4);
    	    // t.setBorderWidth(1);

    	    PdfPCell c1 = new PdfPCell(new Phrase("Value"));
    	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    	    table.addCell(c1);

    	    c1 = new PdfPCell(new Phrase("Number of entries"));
    	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    	    table.addCell(c1);
    	    
    	    table.setHeaderRows(1);
    	    
    	    for (int i = 0; i < vf.length; i++){
    	    	table.addCell(vf[i]);
    	    }

    	    subCatPart.add(table);

    	  }
    
    public static void addContent(Document document, Colonne colonne, PdfWriter writer, int i) throws DocumentException {
    	
        Anchor anchor = new Anchor(colonne.getNomColonne(), catFont);
        anchor.setName(colonne.getNomColonne());
        Chapter catPart = new Chapter(new Paragraph(anchor), i);
        
        Paragraph subPara = new Paragraph();
        
	    addEmptyLine(subPara, 2);
        
	    subPara.add(new Paragraph("Field name : " + colonne.getNomColonne() +
	    		"\n Storage : " + colonne.getTypeDeDonnee() +
        		"\n Mapping value : " + colonne.getMapping().getNom() +
        		"\n Class : " + colonne.getClasse() +
        		"\n Number of filled entries : " + colonne.getNbCasesRemplies() + 
        		"\n Number of empty entries : " + colonne.getNbCasesVides() + 
        		"\n Number of incorrect entries : " + colonne.getNbCasesIncorrectes() +
        		"\n 3 most frequent entries : "));
	    
	    addEmptyLine(subPara, 1);
	    
	    createTable(subPara, colonne.getValeursFrequentes());
        
        document.add(catPart);
        document.add(subPara);
          
        // Pie
        
        JFreeChart pie = generatePieChart(colonne);
        
        
        PdfContentByte contentByte = writer.getDirectContent();
        
        PdfTemplate tPie = contentByte.createTemplate(330, 230);

        Graphics2D g2dpie = tPie.createGraphics(330, 230, new DefaultFontMapper());
        Rectangle2D r2dpie = new Rectangle2D.Double(0, 0, 330, 230);
 
        pie.draw(g2dpie, r2dpie);;
        
        g2dpie.dispose();
        
        // Meter

        JFreeChart meter = createMeterChart(colonne);
        
        PdfTemplate tMeter = contentByte.createTemplate(300, 200);
        
        Graphics2D g2dmeter = tMeter.createGraphics(300, 200, new DefaultFontMapper());
        Rectangle2D r2dmeter = new Rectangle2D.Double(0, 0, 300, 200);
        
        meter.draw(g2dmeter, r2dmeter);
        
        g2dmeter.dispose();
         
        contentByte.addTemplate(tPie, 120, 290);
        contentByte.addTemplate(tMeter, 135, 50);
        

        document.newPage();

      }

	
}

