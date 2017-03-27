package org.gwtproject.tutorial.server.utils;

public class PlayingCharacter<T extends org.gwtproject.tutorial.server.pojo.base.Character>
		extends org.gwtproject.tutorial.server.pojo.base.Character {
	private T instance;

	public PlayingCharacter(T instance) {
		super(instance);
		this.instance = instance;
	}

	public <E extends org.gwtproject.tutorial.server.pojo.base.Character> Double getCharacterRemainingHp(E rival) {
		Integer instanceHp = instance.getHp();
		Double instanceHpDouble = instanceHp.doubleValue();
		Integer instanceAttackPoint = instance.getAttackPoint();
		Integer rivalHp = rival.getHp();
		Integer rivalAttackPoint = rival.getAttackPoint();
		Integer addition = rivalHp % instanceAttackPoint;
		if (addition != 0) {
			addition = instanceAttackPoint - addition;
		}
		Double rivalHpWithAddition = Double.valueOf(rivalHp + addition);
		Double multiplier = rivalHpWithAddition / instanceAttackPoint.doubleValue();
		instanceHpDouble -= rivalAttackPoint.doubleValue() * multiplier;
		return instanceHpDouble;
	}

	public T getInstance() {
		return instance;
	}
}
