package com.app.server.businessservice.appbasicsetup.usermanagement;
import com.app.bean.DocumentBean;

import com.app.bean.DocumentTemplate;

import com.app.shared.appbasicsetup.usermanagement.ArtDocumentTemplate;

import com.app.server.repository.appbasicsetup.usermanagement.ArtDocumentTemplateRepository;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.athena.config.appsetUp.interfaces.AppConfigurationInterface;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

/**
 * DOMAIN SERVICE IS USED TO CREATE THE DOCUMENT TEMPLATE
 * 
 * createDocument - METHOD USED TO CREATE THE DOCUMENT FROM USER'S UPLOADED
 * DOCUMENT HTML TEMPLATE AND UPLOAD IT TO USER-WORKSPACE WITH RESPECTED
 * DOCUMENT TYPE
 * 
 * DOC_TYPES 1 - PDF 2 - DOC
 * 
 * */
@Component
public class DocumentTemplateDomainService {

	@Autowired
	private ArtDocumentTemplateRepository artDocumentTemplateRepository;

	@Autowired
	private AppConfigurationInterface appConfigurationInterface;

	/**
	 * CREATING DOCUMENT BY REPLACING DYNAMIC VALUES IN DOCUMENT TEMPLATE AND
	 * SAVING IT FOR FURTHER USE
	 */
	public DocumentBean createDocument(final DocumentTemplate documentTemplate) throws Exception {
		final ArtDocumentTemplate artDocumentTemplate = artDocumentTemplateRepository.findById(documentTemplate.getTemplateId());
		final String documentContaints = prepareDocumentByTemplate(documentTemplate, artDocumentTemplate);
		final String documentPath = saveDocument(documentContaints, artDocumentTemplate);
		final DocumentBean documentBean = new DocumentBean(artDocumentTemplate.getDocName(), documentPath);
		return documentBean;
	}

	/**
	 * PROCESSING THE DOCUMENT BODY AND REPLACE THE TEMPLATE KEYS BY ITS ACTUAL
	 * VALUES USING VELOCITY-TEMPLATE
	 */
	private String prepareDocumentByTemplate(final DocumentTemplate documentTemplate, final ArtDocumentTemplate artDocumentTemplate) throws Exception {
		final VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.init();
		final VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("object", documentTemplate.getReferences());
		final StringWriter stringWriter = new StringWriter();
		velocityEngine.evaluate(velocityContext, stringWriter, "", artDocumentTemplate.getDocTemplate());
		return stringWriter.toString();
	}

	/** METHOD USE TO CREATE THE PDF DOCUMENT AND SAVE IT TO USER'S WORKSPACE */
	private String saveDocument(final String documentContaints, final ArtDocumentTemplate artDocumentTemplate) throws DocumentException, IOException {
		String documentBasePath = appConfigurationInterface.getPathConfig().getWebAppPath() + "/documents";
		String documentExtension = "";
		File documentFolderPathFile = null;
		String documentPath = "";
		if (artDocumentTemplate.getDocType() == ArtDocumentTemplate.PDF_DOCUMENT || artDocumentTemplate.getDocType() == ArtDocumentTemplate.WORD_DOC_DOCUMENT) {
			documentBasePath = documentBasePath.concat("/pdfdocs");
			if (artDocumentTemplate.getDocType() == ArtDocumentTemplate.PDF_DOCUMENT) {
				documentExtension = ".pdf";
			} else {
				documentExtension = ".docx";
			}
			documentFolderPathFile = new File(documentBasePath);
			documentFolderPathFile.mkdirs();
			documentPath = documentFolderPathFile.getAbsolutePath() + "/" + artDocumentTemplate.getDocName() + documentExtension;
			this.writePDFDocument(documentPath, documentContaints);
		} else if (artDocumentTemplate.getDocType() == ArtDocumentTemplate.TEXT_DOCUMENT) {
			documentBasePath = documentBasePath.concat("/textdocs");
			documentExtension = ".txt";
			documentFolderPathFile = new File(documentBasePath);
			documentFolderPathFile.mkdirs();
			documentPath = documentFolderPathFile.getAbsolutePath() + "/" + artDocumentTemplate.getDocName() + documentExtension;
			this.writeTextDocument(documentPath, documentContaints);
		}
		return documentPath;
	}

	/** WRITING FORMATTED PDF DOCUMENT */
	private void writePDFDocument(final String documentPath, final String documentContaints) throws DocumentException, IOException {
		final OutputStream docOutputStream = new FileOutputStream(documentPath);
		final Document document = new Document();
		final PdfWriter pdfWriter = PdfWriter.getInstance(document, docOutputStream);
		document.open();
		final InputStream documentInputStream = new ByteArrayInputStream(documentContaints.getBytes());
		XMLWorkerHelper.getInstance().parseXHtml(pdfWriter, document, documentInputStream);
		document.close();
		docOutputStream.close();
	}

	/**
	 * WRITING PLAIN TEXT DOCUMENT
	 * 
	 * @throws IOException
	 */
	private void writeTextDocument(final String documentPath, final String documentContaints) throws IOException {
		final FileWriter fileWriter = new FileWriter(documentPath);
		final BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		final PrintWriter printWriter = new PrintWriter(bufferedWriter);
		printWriter.write(documentContaints);
		printWriter.close();
		bufferedWriter.close();
	}
}
