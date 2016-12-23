package com.app.shared.appbasicsetup.aaa;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;

import com.athena.shared.pluggable.entity.ArtEntity;

@Entity
@Table(name = "art_user_last_status")
public class ArtUserLastStatus extends ArtEntity implements com.athena.shared.pluggable.entity.Entity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@Basic(optional = false)
	@GeneratedValue(generator = "UUIDGenerator")
	private String id;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "menu_id")
	private String menuId;

	@Column(name = "json")
	private String jsonData;

	public ArtUserLastStatus() {
		super();
	}

	public ArtUserLastStatus(final String userId, final String menuId, final String jsonData) {
		super();
		this.userId = userId;
		this.menuId = menuId;
		this.jsonData = jsonData;
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(final String menuId) {
		this.menuId = menuId;
	}

	@Override
	public Object getPrimaryKey() {
		return id;
	}

	@Override
	public JSONObject toJSON() {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("id", id);
			jsonObject.put("userId", userId);
			jsonObject.put("menuId", menuId);
			jsonObject.put("jsonData", jsonData);
		} catch (JSONException e) {
		}
		return jsonObject;
	}

}
