package com.app.config.appSetup.model;
import com.athena.config.appsetUp.interfaces.DrivePropertiesInterface;

public final class DriveProperties implements DrivePropertiesInterface {
	private final String drive;
	private final String publicDrive;
	private final String privateDrive;

	public DriveProperties(String drive, String publicDrive, String privateDrive) {
		this.drive = drive;
		this.publicDrive = publicDrive;
		this.privateDrive = privateDrive;
	}

	@Override
	public String getDrive() {
		return drive;
	}

	@Override
	public String getPublicDrive() {
		return publicDrive;
	}

	@Override
	public String getPrivateDrive() {
		return privateDrive;
	}

	@Override
	public String toString() {
		return "DriveProperties [drive=" + drive + ", publicDrive=" + publicDrive + ", sharedDrive=" + privateDrive + "]";
	}
}
