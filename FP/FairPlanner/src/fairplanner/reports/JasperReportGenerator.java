package fairplanner.reports;

import fairplanner.alpha.ConnectDB;
import java.io.InputStream;

import java.sql.Connection;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author admin
 */
public class JasperReportGenerator {

    InputStream designFileStream;

    public JasperReportGenerator() {
    }

    public void generateReport(String fileName) {
        try {
            designFileStream = getClass().getResourceAsStream(fileName);
            JasperDesign jasperDesign = JRXmlLoader.load(designFileStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            Connection con = ConnectDB.connect();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, con);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            System.out.println("Exception in generationg report, generateReport() of JasperReportGenerator: " + e);
        }

    }
}
