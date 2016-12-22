package com.app.bean;
/** CLASS IS USED TO SEND STATUS OF NOTIFICATION SENDING */
public class NotificationResponseBean {

	private boolean notificationStatus;

	public NotificationResponseBean() {
	}

	public NotificationResponseBean(boolean notificationStatus) {
		this.notificationStatus = notificationStatus;
	}

	public boolean getNotificationStatus() {
		return notificationStatus;
	}

	public void setNotificationStatus(boolean notificationStatus) {
		this.notificationStatus = notificationStatus;
	}
}
