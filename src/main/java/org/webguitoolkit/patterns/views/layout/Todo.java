package org.webguitoolkit.patterns.views.layout;

import java.io.Serializable;

public class Todo implements Serializable {
	private String description;
	private int effort;
	private boolean done = false;

	public Todo(String desc) {
		setDescription(desc);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getEffort() {
		return effort;
	}

	public void setEffort(int effort) {
		this.effort = effort;
	}

	/**
	 * @param done the done to set
	 */
	public void setDone(boolean done) {
		this.done = done;
	}

	/**
	 * @return the done
	 */
	public boolean isDone() {
		return done;
	}

}
