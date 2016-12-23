package com.app.shared.appinsight.alarms;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Cache;

@Entity
@Table(name = "art_log_architecture_layer_m")
@Cache(alwaysRefresh = true)
public class ArtLogArchitectureLayer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "layerId")
	private Integer layerId;

	@Column(name = "layerName")
	private String layerName;

	public ArtLogArchitectureLayer() {
		super();
	}

	public ArtLogArchitectureLayer(Integer layerId, String layerName) {
		super();
		this.layerId = layerId;
		this.layerName = layerName;
	}

	public Integer getLayerId() {
		return layerId;
	}

	public void setLayerId(final Integer layerId) {
		this.layerId = layerId;
	}

	public String getLayerName() {
		return layerName;
	}

	public void setLayerName(final String layerName) {
		this.layerName = layerName;
	}

	@Override
	public String toString() {
		return "ArtLogArchitectureLayer [layerId=" + layerId + ", layerName=" + layerName + "]";
	}

	public String toJSON() {
		StringBuilder sb = new StringBuilder();
		sb.append("{").append("\"layerId\":" + "\"" + getLayerId() + "\"").append(",\"layerName\":" + "\"" + getLayerName() + "\"").append("}");
		return sb.toString();
	}
}
