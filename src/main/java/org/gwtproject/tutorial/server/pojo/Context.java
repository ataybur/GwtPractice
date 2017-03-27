package org.gwtproject.tutorial.server.pojo;

import java.util.List;
import java.util.Optional;

public class Context {
	private static Context context = new Context();

	private Hero hero;
	private Field field;
	private List<Enemy> enemyList;
	private List<String> console;

	protected Context() {
	}

	public static Optional<Context> getInstance() {
		return Optional.ofNullable(context);
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public List<Enemy> getEnemyList() {
		return enemyList;
	}

	public void setEnemyList(List<Enemy> enemyList) {
		this.enemyList = enemyList;
	}

	public List<String> getConsole() {
		return console;
	}

	public void setConsole(List<String> console) {
		this.console = console;
	}

	@Override
	public String toString() {
		return "Context [hero=" + hero + ", field=" + field + ", enemyList=" + enemyList + "]";
	}

}
