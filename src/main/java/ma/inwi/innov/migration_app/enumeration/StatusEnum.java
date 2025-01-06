package ma.inwi.innov.migration_app.enumeration;

import lombok.Getter;


@Getter
public enum StatusEnum {
    STARTED("Started"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed");
    StatusEnum(String name) {
		this.name = name;
	}

	private final String name;
}
