package ch.globaz.avs4.personnes.application.configuration;

public enum Profiles {

	DEV("dev"),
	PRODUCTION("prod");

	private String profileValue;

	Profiles(String profileValue){
		this.profileValue = profileValue;
	}

	public String value() {
		return this.profileValue;
	}
}
