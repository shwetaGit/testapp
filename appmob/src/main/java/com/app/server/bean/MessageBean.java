package com.app.server.bean;
public class MessageBean extends BeanAdapter {
	private long id;
	private String name;
	private boolean hasError;
	private String message;

	public MessageBean() {
		super();
	}

	public MessageBean(final long id, String name, final boolean hasError, final String message) {
		super();
		this.id = id;
		this.name = name;
		this.hasError = hasError;
		this.message = message;
	}

	@Override
	public String toString() {
		return "MessageBean [id=" + id + ", name=" + name + ", hasError=" + hasError + ", message=" + message + "]";
	}

	public MessageBean(final String name, final boolean hasError, final String message) {
		this.name = name;
		this.hasError = hasError;
		this.message = message;
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public boolean isHasError() {
		return hasError;
	}

	public void setHasError(final boolean hasError) {
		this.hasError = hasError;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public String toJson() {
		StringBuilder jsonData = new StringBuilder();
		jsonData.append("{").append("\"id\":\"" + id + "\",").append("\"name\":\"" + name + "\",").append("\"hasError\":\"" + hasError + "\",")
				.append("\"message\":\"" + message + "\"").append("}");

		return jsonData.toString();
	}
}
