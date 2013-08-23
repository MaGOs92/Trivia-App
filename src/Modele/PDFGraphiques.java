package Modele;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.MeterPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.DefaultValueDataset;

import java.util.ArrayList;
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
	  private static Font smallBold = new Font(Font.HELVETICA, 12,
		      Font.NORMAL);
	  
	  public ArrayList<Colonne> colonneSelectionnees;
	  
	  public ArrayList<Colonne> colonneM;
	  
	  public ArrayList<Colonne> colonneP;
	  
	  
	 
	public ArrayList<Colonne> getColonneM() {
		return colonneM;
	}

	public void setColonneM(ArrayList<Colonne> colonneM) {
		this.colonneM = colonneM;
	}

	public ArrayList<Colonne> getColonneP() {
		return colonneP;
	}

	public void setColonneP(ArrayList<Colonne> colonneP) {
		this.colonneP = colonneP;
	}

	public ArrayList<Colonne> getColonneSelectionnees() {
		return colonneSelectionnees;
	}

	public void setColonneSelectionnees(ArrayList<Colonne> colonneSelectionnees) {
		this.colonneSelectionnees = colonneSelectionnees;
	}

	public PDFGraphiques(DataAuditModele model, ArrayList<Colonne> colonneM, ArrayList<Colonne> colonneP){
		this.setColonneSelectionnees(new ArrayList<Colonne>());
		this.setColonneM(colonneM);
		this.setColonneP(colonneP);
		for (int i = 0; i < model.getNbColonnesTotales(); i++){
        	if (model.getTabColonne()[i].isSelectionnee()){
        		this.getColonneSelectionnees().add(model.getTabColonne()[i]);
        	}
		} 	
	}
	  
    public static JFreeChart generatePieChart(Colonne colonne) {
    	
        DefaultPieDataset dataSet = new DefaultPieDataset();
    	
        	dataSet.setValue("Incorrect entries", colonne.getNbCasesIncorrectes());
        	dataSet.setValue("Empty entries", colonne.getNbCasesVides());
        	dataSet.setValue("Filled entries", colonne.getNbCasesRemplies());
 
        JFreeChart chart = ChartFactory.createPieChart("Data quality repartition", dataSet, true, true, false);
        
        chart.setBackgroundPaint(Color.white); 
        chart.setBorderVisible(false);
        
        return chart;
    }
    
    public static JFreeChart createBarChartMarketability(ArrayList<Colonne> colonnes){
    	
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	
    	float position = 0;
    	
    	for (int i = 0; i < colonnes.size(); i++) {
			dataset.addValue(colonnes.get(i).getPourcentagesCasesRemplies(), "Marketability", colonnes.get(i).getNomColonne());
			position += colonnes.get(i).getPourcentagesCasesRemplies();
		}
    	
    	position = position / colonnes.size();
    	
    	JFreeChart barChart = ChartFactory.createBarChart("Marketability score", "", "", 
				dataset, PlotOrientation.VERTICAL, true, true, false);
    	
    	
    	ValueMarker marker = new ValueMarker(position);  // position is the value on the axis
    	marker.setPaint(Color.black);
    	marker.setLabel("Average : " + Math.round(position)); // see JavaDoc for labels, colors, strokes

    	XYPlot plot = (XYPlot) barChart.getPlot();
    	plot.addDomainMarker(marker);
    	
    	barChart.removeLegend();
    	
    	return barChart;
    }
    
    public static JFreeChart createBarChartPromotability(ArrayList<Colonne> colonnes){
    	
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	
    	for (int i = 0; i < colonnes.size(); i++) {
			dataset.addValue(colonnes.get(i).getPourcentagesCasesRemplies(), "Promotability", colonnes.get(i).getNomColonne());
		}
    	
    	JFreeChart barChart = ChartFactory.createBarChart("Promotability score", "", "", 
				dataset, PlotOrientation.VERTICAL, true, true, false);
    	
    	barChart.removeLegend();
    	
    	return barChart;
    }
    
    
    public static JFreeChart createMeterChart(Colonne colonne) {
    	
    	DefaultValueDataset dataset = new DefaultValueDataset(Math.round(colonne.getPourcentagesCasesRemplies()));

        MeterPlot plot = new MeterPlot(dataset);
        
        Color rougeTrivia = new Color(180,5,22);
        Color grisTrivia = new Color(192,192,192);
        
        
        //plot.addInterval(new MeterInterval("High", new Range(75.0, 100.0)));

        plot.setUnits("%");

        plot.setDialBackgroundPaint(grisTrivia);
        
        plot.setValuePaint(Color.black);
        
        //plot.setDrawBorder(true);
        
        plot.setNeedlePaint(rougeTrivia);

        JFreeChart chart = new JFreeChart("Data quality score", JFreeChart.DEFAULT_TITLE_FONT, plot, false);
        
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
    	
    		Image image = com.lowagie.text.Image.getInstance("Img\\bandeau_reporting.png");
    		image.setAbsolutePosition(0, 720);
    		image.scaleToFit(700, 142);
    		document.add(image);
    		
    	    Paragraph titre = new Paragraph();
    	    // We add one empty line
    	    addEmptyLine(titre, 8);
    	    // Lets write a big header
    	    titre.add(new Paragraph("DataDiscovery : " + model.getNomTable(), titreFont));

    	    addEmptyLine(titre, 3);
    	    
    	    titre.setAlignment(Element.ALIGN_CENTER);
    	    
    	    document.add(titre);
    	    
    	    Paragraph generated = new Paragraph();

    	    generated.add(new Paragraph("Report generated by: " + System.getProperty("user.name") + ", " + new Date(),
    	        smallBold));
    	    
    	    addEmptyLine(generated, 3);
    	    
    	    document.add(generated);
    	    
    	    Paragraph summary = new Paragraph();
    	    
    	    summary.add(new Paragraph("Quick summary :", catFont));
    	    
    	    addEmptyLine(summary, 1);
    	    summary.add(new Paragraph("Audited file : " + model.getNomTable() +
    	    		"\n Customer : " + model.getNomClient() +
    	    		"\n Total number of fields : " + model.getNbColonnesTotales() +
    	    		"\n Total number of entries : " + model.getNbLignesTotales() +
    	    		"\n File's fill rate : " + Math.round(pourcentageRemplissageFichier) + " %" +
    	    		"\n Selected fields's fill rate : " + Math.round(pourcentageRemplissageSelection) + " %" +
    	    		"\n Number of selected fields : " + model.getNbLignesSelectionnee() +
    	    		"\n Selected fields : " + valeursSelectionnees,smallBold));
    	    
    	    document.add(summary);
    	    	    
    	    document.newPage();
    	  }
    
    public static void writeChartToPDF(PDFGraphiques PDF, String fileName, DataAuditModele model) {
        PdfWriter writer = null;
     
        Document document = new Document();
     
        try {
        	
            writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
            
            document.open();
            
            addMetaData(document, model);
            
            addTitlePage(document, model);
            
            PDF.addClassTable(document, model, writer);
            
            PDF.addMarkPromPage(document, writer);
            
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
    	    PdfPTable table = new PdfPTable(3);

    	    // t.setBorderColor(BaseColor.GRAY);
    	    // t.setPadding(4);
    	    // t.setSpacing(4);
    	    // t.setBorderWidth(1);

    	    PdfPCell c1 = new PdfPCell(new Phrase("Value"));
    	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    	    table.addCell(c1);

    	    c1 = new PdfPCell(new Phrase("Count"));
    	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    	    table.addCell(c1);
    	    
    	    c1 = new PdfPCell(new Phrase("Percentage"));
    	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    	    table.addCell(c1);
    	    
    	    table.setHeaderRows(1);
    	    
    	    for (int i = 0; i < vf.length; i++){
    	    	table.addCell(vf[i]);
    	    }

    	    subCatPart.add(table);

    	  }
    
    public static void createClassTable(Paragraph subCatPart, String[] vf)
  	      throws BadElementException {
  	    PdfPTable table = new PdfPTable(3);

  	    // t.setBorderColor(BaseColor.GRAY);
  	    // t.setPadding(4);
  	    // t.setSpacing(4);
  	    // t.setBorderWidth(1);

  	    PdfPCell c1 = new PdfPCell(new Phrase("Class"));
  	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
  	    table.addCell(c1);

  	    c1 = new PdfPCell(new Phrase("Count of selected field"));
  	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
  	    table.addCell(c1);
  	    
  	    c1 = new PdfPCell(new Phrase("Average correct percentage"));
  	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
  	    table.addCell(c1);
  	    
  	    table.setHeaderRows(1);
  	    
  	    for (int i = 0; i < vf.length; i++){
  	    	table.addCell(vf[i]);
  	    }

  	    subCatPart.add(table);

  	  }
    
    public void addMarkPromPage(Document document, PdfWriter writer) throws DocumentException{
    	
    	Paragraph titre = new Paragraph();
	    addEmptyLine(titre, 3);	    
	    titre.add(new Paragraph("Marketability and promotability scores", titreFont));
	    addEmptyLine(titre, 3);	    
	    titre.setAlignment(Element.ALIGN_CENTER);   
	    document.add(titre);
    	
        // Bar graphiqueMarketability
        
        JFreeChart marketability = createBarChartMarketability(this.getColonneM());
        
        PdfContentByte contentByte = writer.getDirectContent();
        
        PdfTemplate tBarre1 = contentByte.createTemplate(330, 230);

        Graphics2D g2dbarre = tBarre1.createGraphics(330, 230, new DefaultFontMapper());
        Rectangle2D r2dbarre = new Rectangle2D.Double(0, 0, 330, 230);
 
        marketability.draw(g2dbarre, r2dbarre);;
        
        g2dbarre.dispose();
        
        // Bar graphiquePromotability
        
        JFreeChart promotability = createBarChartPromotability(this.getColonneP());
        
        PdfTemplate tBarre2 = contentByte.createTemplate(330, 230);

        Graphics2D g2dbarre2 = tBarre2.createGraphics(330, 230, new DefaultFontMapper());
        Rectangle2D r2dbarre2 = new Rectangle2D.Double(0, 0, 330, 230);
 
        promotability.draw(g2dbarre2, r2dbarre2);;
        
        g2dbarre2.dispose();
        
        contentByte.addTemplate(tBarre1, 120, 450);
        contentByte.addTemplate(tBarre2, 120, 100);
        
        document.newPage();
    }
    
    public static void addContent(Document document, Colonne colonne, PdfWriter writer, int i) throws DocumentException {
    	
        Anchor anchor = new Anchor(colonne.getNomColonne(), catFont);
        anchor.setName(colonne.getNomColonne());
        Chapter catPart = new Chapter(new Paragraph(anchor), i);
        
        Paragraph subPara = new Paragraph();
        
	    addEmptyLine(subPara, 2);
	    
	    String para = "Field name : " + colonne.getNomColonne() +
	    	"\n Storage : " + colonne.getTypeDeDonnee();
	    if (colonne.isStringValues()){
	    	para +=	"\n Mapping value : " + colonne.getMappingString().getNom();
	    }
	    else{
	    	para +=	"\n Mapping value : " + colonne.getMappingINT().getNom();
	    }
	    para +=	"\n Class : " + colonne.getClasse().getNom() +
        		"\n Number of filled entries : " + colonne.getNbCasesRemplies() + 
        		"\n Number of empty entries : " + colonne.getNbCasesVides() + 
        		"\n Number of incorrect entries : " + colonne.getNbCasesIncorrectes();
        
	    subPara.add(new Paragraph(para));
	    
	    addEmptyLine(subPara, 1);
	    
	    if (colonne.getValeursFrequentes().length != 0){
	    	subPara.add(new Paragraph("The three most frequent entries : "));
		    createTable(subPara, colonne.getValeursFrequentes());		    
		    addEmptyLine(subPara, 1);
	    }
	    
	    addEmptyLine(subPara, 1);
	    
	    if (colonne.isStringValues()){
	    	if (colonne.getMappingString().getValeursIncorrectes().size() != 0){
	    		subPara.add(new Paragraph("Incorrect values from the mapping filter : "));
			    createTable(subPara, colonne.getMappingString().transformerListeTab());		    
			    addEmptyLine(subPara, 1);
	    	}
	    }
	    else{
	    	if (colonne.getMappingINT().getValeursIncorrectes().size() != 0){
	    		subPara.add(new Paragraph("Incorrect values from the mapping filter : "));
			    createTable(subPara, colonne.getMappingINT().transformerListeTab());		    
			    addEmptyLine(subPara, 1);
	    	}
	    }
	    
	    addEmptyLine(subPara, 1);
	    
	    if (colonne.getValeursListeSelectionnees().size() != 0){
	    	if (colonne.isKeepOrRemove()){
	    		subPara.add(new Paragraph("Kept values : "));
	    	}
	    	else{
		    	subPara.add(new Paragraph("Removed values : "));
	    	}
		    createTable(subPara, colonne.transformerListeTab());		    
		    addEmptyLine(subPara, 1);
	    }
        
        document.add(catPart);
        document.add(subPara);
        writer.getPageNumber();
        document.newPage();
        
        
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
         
        contentByte.addTemplate(tPie, 120, 450);
        contentByte.addTemplate(tMeter, 135, 100);
        document.newPage();

      }
    
    public void addClassTable(Document document, DataAuditModele model, PdfWriter writer) throws DocumentException{
    	
    	Paragraph titre = new Paragraph();
	    addEmptyLine(titre, 3);	    
	    titre.add(new Paragraph("Class table", titreFont));
	    addEmptyLine(titre, 3);	    
	    titre.setAlignment(Element.ALIGN_CENTER);   
	    document.add(titre);
	    
	    String [] tabClass = new String[18];
	    
	    tabClass[0] = "Identifier";
	    tabClass[3] = "Indicator";
	    tabClass[6] = "Quantity";
	    tabClass[9] = "Date";
	    tabClass[12] = "Text";
	    tabClass[15] = "Code";
	    
	    int nbClasse = 1;
	    for (int i = 1; i < 18; i += 3){
	    	int nb = 0;
	    	float pourcentage = 0;
	    	for (int j = 0; j < this.getColonneSelectionnees().size(); j++){
		    	if (this.getColonneSelectionnees().get(j).getClasse().getId() == nbClasse){
		    		nb ++;
		    		pourcentage += this.getColonneSelectionnees().get(j).getPourcentagesCasesRemplies();
		    	}	    		
	    	}
	    	tabClass[i] = "" + nb;
	    	pourcentage = pourcentage / nb;	    	
	    	tabClass[i+1] = Math.round(pourcentage) + "%";
	    	
	    	nbClasse ++;
	    }
	    
	    Paragraph tab = new Paragraph();
	    createClassTable(tab, tabClass);
	    document.add(tab);	    
        writer.getPageNumber();
        document.newPage();
    }

	
}

