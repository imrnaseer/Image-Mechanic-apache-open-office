package uoj.csc.rad.gp1;

import com.sun.star.beans.XPropertySet;
import com.sun.star.comp.helper.Bootstrap;
import com.sun.star.frame.XDesktop;
import com.sun.star.lang.XComponent;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.lang.XMultiServiceFactory;
import com.sun.star.text.TextContentAnchorType;
import com.sun.star.text.XText;
import com.sun.star.text.XTextContent;
import com.sun.star.text.XTextDocument;
import com.sun.star.ucb.XFileIdentifierConverter;
import com.sun.star.ucb.XSimpleFileAccess;
import com.sun.star.uno.UnoRuntime;

import com.sun.star.uno.XComponentContext;

public class imgatch {    
    
    private XComponentContext m_xContext = null;
    private XMultiComponentFactory m_xMCF = null;
    private static String m_sGraphicFile;
    private static String m_sGraphicFileURL;

    imgatch(String s,int w,int h) throws java.lang.Exception 
    {
        m_sGraphicFile=s;
        java.io.File sourceFile = new java.io.File(m_sGraphicFile);
        m_sGraphicFileURL = convertToURL("", sourceFile.getAbsolutePath());
        if (!checkFile(m_sGraphicFileURL))
        {
            System.exit(1);
        }

        XTextDocument xTextDoc = createTextDocument();
        XMultiServiceFactory xMSFDoc = (XMultiServiceFactory) 
                UnoRuntime.queryInterface(XMultiServiceFactory.class, xTextDoc);
        
        Object oGraphic = xMSFDoc.createInstance(
                "com.sun.star.text.TextGraphicObject");
        
        XTextContent xTextContent = (XTextContent) UnoRuntime.queryInterface(
                XTextContent.class, oGraphic);
        
        XText xText = xTextDoc.getText();
        xText.insertTextContent(xText.getEnd(), xTextContent, true);
        
        XPropertySet xPropSet = (XPropertySet) UnoRuntime.queryInterface(
                XPropertySet.class, oGraphic);
        
        xPropSet.setPropertyValue("AnchorType", TextContentAnchorType.AT_PARAGRAPH);

        xPropSet.setPropertyValue("GraphicURL", m_sGraphicFileURL);

        xPropSet.setPropertyValue("HoriOrientPosition", 5500);

        xPropSet.setPropertyValue("VertOrientPosition", 4200);

        xPropSet.setPropertyValue("Width", w*25);

        xPropSet.setPropertyValue("Height", h*25);
    }
     
    private XMultiComponentFactory getMultiComponentFactory()
            throws java.lang.Exception {
        if (m_xContext == null && m_xMCF == null) {
            m_xContext = Bootstrap.bootstrap();
            m_xMCF = m_xContext.getServiceManager();
        }
        return m_xMCF;
    }
    
    private XComponent newDocComponent()
            throws java.lang.Exception {
        m_xMCF = this.getMultiComponentFactory();
        Object desktop = m_xMCF.createInstanceWithContext(
                "com.sun.star.frame.Desktop", m_xContext);
        
            XDesktop xDesktop = (XDesktop)UnoRuntime.queryInterface(XDesktop.class, desktop);
     XComponent document = xDesktop.getCurrentComponent();
        return document;
    }
    
    private XTextDocument createTextDocument() {
        XTextDocument aTextDocument = null;

        try {
            XComponent xComponent = newDocComponent();
            aTextDocument = (XTextDocument) UnoRuntime.queryInterface(
                    XTextDocument.class, xComponent);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return aTextDocument;
    }
      
    private boolean checkFile( String aURL ){
        boolean bExists = false;
        try {
            XSimpleFileAccess xSFA = (XSimpleFileAccess) UnoRuntime.queryInterface(
                    XSimpleFileAccess.class, 
                    this.getMultiComponentFactory().createInstanceWithContext(
                        "com.sun.star.ucb.SimpleFileAccess", m_xContext));
            bExists = xSFA.exists(aURL) && !xSFA.isFolder(aURL);
        } catch (com.sun.star.ucb.CommandAbortedException ex){
        } catch (com.sun.star.uno.Exception ex){
        } catch (java.lang.Exception ex){
        }
        return bExists;
    }
    
    private String convertFromURL(String sURLPath) throws Exception {
        String sSystemPath = null;
        try {
            m_xMCF = getMultiComponentFactory();
            XFileIdentifierConverter xFileConverter =
                    (XFileIdentifierConverter) UnoRuntime.queryInterface(
                        XFileIdentifierConverter.class,
                        m_xMCF.createInstanceWithContext(
                            "com.sun.star.ucb.FileContentProvider", m_xContext));
            sSystemPath = xFileConverter.getSystemPathFromFileURL(sURLPath);

        } catch (com.sun.star.uno.Exception e) {
            e.printStackTrace(System.err);
        } 
            return sSystemPath;
    }
    
    private String convertToURL(String sBase, String sSystemPath) throws Exception {
        String sURL = null;
        try {
            m_xMCF = getMultiComponentFactory();
            XFileIdentifierConverter xFileConverter =
                    (XFileIdentifierConverter) UnoRuntime.queryInterface(
                        XFileIdentifierConverter.class,
                        m_xMCF.createInstanceWithContext(
                            "com.sun.star.ucb.FileContentProvider", m_xContext));
            sURL = xFileConverter.getFileURLFromSystemPath(
                    sBase, sSystemPath );
        } catch (com.sun.star.uno.Exception e) {
        } 
            return sURL;
    }
}
