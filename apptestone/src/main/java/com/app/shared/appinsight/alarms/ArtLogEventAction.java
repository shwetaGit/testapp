package com.app.shared.appinsight.alarms;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Cache;

@Entity
@Table(name = "art_log_event_action_m")
@Cache(alwaysRefresh = true)
public class ArtLogEventAction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "eventActionId")
	private Integer eventActionId;

	@Column(name = "eventAction")
	private String eventAction;

	public ArtLogEventAction() {
		super();
	}

	public ArtLogEventAction(Integer eventActionId, String eventAction) {
		super();
		this.eventActionId = eventActionId;
		this.eventAction = eventAction;
	}

	public Integer getEventActionId() {
		return eventActionId;
	}

	public void setEventActionId(final Integer eventActionId) {
		this.eventActionId = eventActionId;
	}

	public String getEventAction() {
		return eventAction;
	}

	public void setEventAction(final String eventAction) {
		this.eventAction = eventAction;
	}

	@Override
	public String toString() {
		return "ArtLogEventAction [eventActionId=" + eventActionId + ", eventAction=" + eventAction + "]";
	}

	public String toJSON() {
		StringBuilder sb = new StringBuilder();
		sb.append("{").append("\"eventActionId\":" + "\"" + getEventActionId() + "\"").append(",\"eventAction\":" + "\"" + getEventAction() + "\"").append("}");
		return sb.toString();
	}
}
