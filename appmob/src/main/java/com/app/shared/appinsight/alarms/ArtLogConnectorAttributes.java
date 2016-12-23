package com.app.shared.appinsight.alarms;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Cache;

@Entity
@Table(name = "art_log_connector_attributes_m")
@Cache(alwaysRefresh = true)
public class ArtLogConnectorAttributes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UUIDGenerator")
	@Basic(optional = false)
	@Column(name = "attributeId")
	private String attributeId;

	@ManyToOne
	@JoinColumn(name = "connectorId", referencedColumnName = "connectorId")
	private ArtLogConnector artLogConnector;

	@Column(name = "attributeName")
	private String attributeName;

	@Column(name = "attributeValue")
	private String attributeValue;

	@Column(name = "attributeComment")
	private String attributeComment;

	@Column(name = "attributeDisplayName")
	private String attributeDisplayName;

	public ArtLogConnectorAttributes() {
		super();
	}

	public ArtLogConnectorAttributes(ArtLogConnector artlogconnector, String attributeName, String attributeValue, String attributeComment, String attributeDisplayName) {

		this.artLogConnector = artlogconnector;
		this.attributeName = attributeName;
		this.attributeValue = attributeValue;
		this.attributeComment = attributeComment;
		this.attributeDisplayName = attributeDisplayName;
	}

	public String getAttributeDisplayName() {
		return attributeDisplayName;
	}

	public void setAttributeDisplayName(final String attributeDisplayName) {
		this.attributeDisplayName = attributeDisplayName;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(final String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(final String attributeValue) {
		this.attributeValue = attributeValue;
	}

	public String getAttributeComment() {
		return attributeComment;
	}

	public void setAttributeComment(final String attributeComment) {
		this.attributeComment = attributeComment;
	}

	public String getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(final String attributeId) {
		this.attributeId = attributeId;
	}

	public ArtLogConnector getArtlogconnector() {
		return artLogConnector;
	}

	public void setArtlogconnector(final ArtLogConnector artlogconnector) {
		this.artLogConnector = artlogconnector;
	}

	@Override
	public String toString() {
		return "ArtLogConnectorAttributes [attributeId=" + attributeId + ", attributeName=" + attributeName + ", attributeValue=" + attributeValue + ", attributeComment="
				+ attributeComment + ", attributeDisplayName=" + attributeDisplayName + "]";
	}

	public String toJSON() {
		StringBuilder sb = new StringBuilder();
		sb.append("{").append("\"attributeId\":" + "\"" + getAttributeId() + "\"").append(",\"attributeName\":" + "\"" + getAttributeName() + "\"")
				.append(",\"attributeValue\":" + "\"" + getAttributeValue() + "\"").append(",\"attributeComment\":" + "\"" + getAttributeComment() + "\"")
				.append(",\"attributeDisplayName\":" + "\"" + getAttributeDisplayName() + "\"").append("}");
		return sb.toString();
	}
}
