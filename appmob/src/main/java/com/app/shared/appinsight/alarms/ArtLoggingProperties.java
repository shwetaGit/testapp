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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;

@Entity
@Table(name = "art_log_config_attributes_m")
@Cache(alwaysRefresh = true)
@JsonIdentityInfo(generator = PropertyGenerator.class, property = "attributeId")
public class ArtLoggingProperties implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UUIDGenerator")
	@Basic(optional = false)
	@Column(name = "attributeId")
	private String attributeId;

	@Column(name = "attributeName")
	private String attributeName;

	@Column(name = "attributeValue")
	private String attributeValue;

	@Column(name = "attributeComment")
	private String attributeComment;

	@Column(name = "attributeDisplayName")
	private String attributeDisplayName;

	@ManyToOne
	@JoinColumn(name = "logConfigId", referencedColumnName = "logConfigId")
	private ArtLogConfig artLogConfig;

	public ArtLoggingProperties() {
		super();
	}

	public ArtLoggingProperties(String configId, String attributeName, String attributeValue, String attributeComment, String attributeDisplayName, ArtLogConfig artLogConfig) {
		this.attributeId = configId;
		this.attributeName = attributeName;
		this.attributeValue = attributeValue;
		this.attributeComment = attributeComment;
		this.attributeDisplayName = attributeDisplayName;
		this.artLogConfig = artLogConfig;
	}

	public ArtLoggingProperties(String attributeName, String attributeValue, String attributeComment, String attributeDisplayName, ArtLogConfig artLogConfig) {
		this.attributeName = attributeName;
		this.attributeValue = attributeValue;
		this.attributeComment = attributeComment;
		this.attributeDisplayName = attributeDisplayName;
		this.artLogConfig = artLogConfig;
	}

	public String getAttributeComment() {
		return attributeComment;
	}

	public void setAttributeComment(final String attributeComment) {
		this.attributeComment = attributeComment;
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

	public String getConfigId() {
		return attributeId;
	}

	public void setConfigId(final String configId) {
		this.attributeId = configId;
	}

	public ArtLogConfig getArtLogConfig() {
		return artLogConfig;
	}

	public void setArtLogConfig(final ArtLogConfig awsLogConfig) {
		this.artLogConfig = awsLogConfig;
	}

	public void setAttributeValue(final String attributeValue) {
		this.attributeValue = attributeValue;
	}

	public String getAttributeDisplayName() {
		return attributeDisplayName;
	}

	public void setAttributeDisplayName(final String attributeDisplayName) {
		this.attributeDisplayName = attributeDisplayName;
	}

	public String getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(final String attributeId) {
		this.attributeId = attributeId;
	}

	@Override
	public String toString() {
		return "ArtLoggingProperties [attributeId=" + attributeId + ", attributeName=" + attributeName + ", attributeValue=" + attributeValue + ", attributeComment="
				+ attributeComment + ", attributeDisplayName=" + attributeDisplayName + "]";
	}

	public String toJSON() {

		StringBuilder sb = new StringBuilder();
		sb.append("{").append("\"attributeId\":" + "\"" + getAttributeId() + "\"").append(",\"attributeName\":" + "\"" + getAttributeName() + "\"")
				.append(",\"attributeValue\":" + "\"" + getAttributeValue() + "\"").append(",\"attributeComment\":" + "\"" + getAttributeComment() + "\"")
				.append(",\"attributeDisplayName\":" + "\"" + getAttributeDisplayName() + "\"").append("}");
		return sb.toString();
	}

	public String toXMLString() {
		String qtype = "";
		String qsize = "";
		String refreshTime = "";
		StringBuilder buf = new StringBuilder();

		if (this.attributeName.equalsIgnoreCase("alarm.queue")) {
			buf.append("<!--").append(this.attributeComment).append("-->\n");
			qtype = this.attributeValue;
			buf.append("<eventQueueType value=\"" + qtype + "\"/>\n");
		} else if (this.attributeName.equalsIgnoreCase("alarm.volume")) {
			qsize = this.attributeValue;

			buf.append(" <eventQueueSize value=\"" + qsize + "\"/>\n");
		} else if (this.attributeName.equalsIgnoreCase("refresh.frequency")) {
			refreshTime = this.attributeValue;
			buf.append("<!--").append(this.attributeComment).append("-->\n");
			buf.append("<refresh timeUnit=\"minutes\">" + refreshTime + "</refresh>");
		}

		return buf.toString();
	}

}
