package com.test.scriptgen.generator;


/**
 * This enumaration lists all phases in which a database can be updated.
 */
public enum Phase {
	ONLINE_CHANGES("online_changes", 1),
	ONLINE_INDICES("online_indices", 2),
	ONLINE_MIGRATION("online_migration", 3),
	OFFLINE_MIGRATION("offline_migration", 4),
	OFFLINE_INDICES("offline_indices", 5),
	OFFLINE_CHANGES("offline_changes", 6),
	ONLINE_AFTER("online_after", 7);

	/** Array of all phases that use scripts (either liquibase changelogs, shell scripts, or something else). */
	public static final Phase[] ALL_SCRIPT_PHASES = new Phase[] {
			ONLINE_CHANGES,
			ONLINE_INDICES,
			OFFLINE_INDICES,
			OFFLINE_CHANGES,
			ONLINE_AFTER
	};

	/** Returns the order of the last phase. */
	public static final Integer ORDER_OF_LAST_PHASE = 7;

	private final  String name;
	private final  Integer order;

	Phase( String name,  Integer order) {
		this.name = name;
		this.order = order;
	}

	public static String determineNameByOrder(Integer order) {
		for (Phase phase : Phase.values()) {
			if (phase.getOrder().equals(order)) {
				return phase.getName();
			}
		}

		return null;
	}

	
	public String getName() {
		return name;
	}

	
	public Integer getOrder() {
		return order;
	}
}
