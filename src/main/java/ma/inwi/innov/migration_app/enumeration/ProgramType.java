package ma.inwi.innov.migration_app.enumeration;

import lombok.Getter;


@Getter
public enum ProgramType {
    CHALLENGE("challenge"),
    EVENT("event");
	
    ProgramType(String name) {
		this.name = name;
	}

	private final String name;
}
