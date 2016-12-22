package com.app.shared.appbasicsetup.usermanagement;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.athena.shared.pluggable.entity.AuditPropertiesEntity;

@Entity
@Table(name = "art_document_template")
public class ArtDocumentTemplate extends AuditPropertiesEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final Integer PDF_DOCUMENT = 1;

	public static final Integer WORD_DOC_DOCUMENT = 2;

	public static final Integer TEXT_DOCUMENT = 3;

	@Id
	@GeneratedValue(generator = "UUIDGenerator")
	@Size(min = 3, max = 64)
	@Column(name = "doc_temp_Id")
	private String docTempId;

	@Column(name = "doc_name")
	private String docName;

	@Column(name = "doc_type")
	private Integer docType;

	@Column(name = "doc_template")
	private String docTemplate;

	public ArtDocumentTemplate() {
		super();
	}

	public String getDocTempId() {
		return docTempId;
	}

	public void setDocTempId(final String docTempId) {
		this.docTempId = docTempId;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(final String docName) {
		this.docName = docName;
	}

	public Integer getDocType() {
		return docType;
	}

	public void setDocType(final Integer docType) {
		this.docType = docType;
	}

	public String getDocTemplate() {
		return docTemplate;
	}

	public void setDocTemplate(final String docTemplate) {
		this.docTemplate = docTemplate;
	}

	public Object getPrimaryKey() {
		return this.docTempId;
	}
}
