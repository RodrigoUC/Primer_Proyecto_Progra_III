package pos.presentation.historico;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import pos.logic.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class Controller {
    private View view;
    private Model model;

    public Controller(View view, Model model) {
        model.init();
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void search(String id) throws Exception {
        try{
            model.setFilter(buscarCliente(id));
            model.setListFacturasFilter(buscarFacturas());  //busca facturas con nombre/id
            model.setListLineasListado(listadoHistorico()); //setea la primera tabla
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public List<Factura>buscarFacturas() throws Exception {
        List<Factura> facturas;
        Factura factura = new Factura();
        try {
            factura.setCliente(model.getFilter());
            facturas = Service.instance().search(factura);
            return facturas;

        } catch (Exception e) {
            throw e;
        }
    }

    Cliente buscarCliente(String id) throws Exception {
        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNombre(id);
        try {
            cliente = Service.instance().read(cliente);
            //listadoHistorico();
            return cliente;
        } catch (Exception e) {
            throw e;
        }
    }


    //-----------------LISTADO DE LINEAS DE FACTURAS DEL CLIENTE (primera tabla)----------
    List<Linea> listadoHistorico() throws Exception {
        try {
            List<Linea> listado = new ArrayList<Linea>();
            //model.setListFacturasFilter(buscarFacturas()); //facturas que coincidan con id, ya se setea en este search
            for (int i = 0; i < model.getListFacturasFilter().size(); i++) {
                listado.add(new Linea(model.getListFacturasFilter().get(i)));   //lineas con las facturas que coinciden con id
            }
            return listado;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error listado historico", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }


    //--------------LISTA DE LINEAS DE ABAJO SEGUN EL CODIGO DE FACTURA (segunda tabla)--------
    void listaLineasNormales(int row) throws Exception {       //
        try{
            List<Linea> sbf = Service.instance().searchbyFactura(model.getListFacturasFilter().get(row).getCodigo());
            model.setListLineasNormales(sbf);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error listado historico", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void print()throws Exception{
        try {
            String dest = "historico.pdf";
            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdf = new PdfDocument(writer);

            Document document = new Document(pdf);
            document.setMargins(20, 20, 20, 20);

            Table header = new Table(1);
            header.setWidth(400);
            header.setHorizontalAlignment(HorizontalAlignment.CENTER);
            header.addCell(getCell(new Paragraph("Listado de Facturas").setFont(font).setBold().setFontSize(20f), TextAlignment.CENTER, false));
            document.add(header);

            document.add(new Paragraph(""));
            document.add(new Paragraph(""));

            Color bkg = ColorConstants.RED;
            Color frg = ColorConstants.WHITE;
            Table body = new Table(4);
            body.setWidth(400);
            body.setHorizontalAlignment(HorizontalAlignment.CENTER);
            body.addCell(getCell(new Paragraph("Numero").setBackgroundColor(bkg).setFontColor(frg), TextAlignment.CENTER, true));
            body.addCell(getCell(new Paragraph("Cliente").setBackgroundColor(bkg).setFontColor(frg), TextAlignment.CENTER, true));
            body.addCell(getCell(new Paragraph("Cajero").setBackgroundColor(bkg).setFontColor(frg), TextAlignment.CENTER, true));
            body.addCell(getCell(new Paragraph("Fecha").setBackgroundColor(bkg).setFontColor(frg), TextAlignment.CENTER, true));


            for (Factura e : model.getListFacturasFilter()) {
                body.addCell(getCell(new Paragraph(String.valueOf(e.getCodigo())), TextAlignment.CENTER, true));
                body.addCell(getCell(new Paragraph(e.getCliente().getNombre()), TextAlignment.CENTER, true));
                body.addCell(getCell(new Paragraph(e.getCajero().getNombre()), TextAlignment.CENTER, true));
                body.addCell(getCell(new Paragraph(e.getFecha().toString()), TextAlignment.CENTER, true));
            }
            document.add(body);
            document.close();
        } catch (Exception ex){
            throw ex;
        }
    }

    private Cell getCell(Paragraph paragraph, TextAlignment alignment, boolean hasBorder) {
        Cell cell = new Cell().add(paragraph);
        cell.setPadding(0);
        cell.setTextAlignment(alignment);
        if(!hasBorder) cell.setBorder(Border.NO_BORDER);
        return cell;
    }

}