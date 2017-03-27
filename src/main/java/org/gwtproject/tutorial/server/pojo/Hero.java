package org.gwtproject.tutorial.server.pojo;

public class Hero extends org.gwtproject.tutorial.server.pojo.base.Character {

	public Hero() {
		super();
	}

	public Hero(Hero instance) {
		super(instance);
	}

	@Override
	public String toString() {
		return "Hero [getHp()=" + getHp() + ", getAttackPoint()=" + getAttackPoint() + "]";
	}

}
