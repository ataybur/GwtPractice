package org.gwtproject.tutorial.server.utils;

import org.gwtproject.tutorial.server.constants.MessageConstants;
import org.gwtproject.tutorial.server.pojo.Context;
import org.gwtproject.tutorial.server.pojo.Enemy;
import org.gwtproject.tutorial.server.pojo.Hero;

public class PlayingHero extends PlayingCharacter<Hero> {
	public PlayingHero(Hero instance) {
		super(instance);
	}

	public boolean isAlive(PlayingCharacter<Enemy> currentEnemy) throws InstantiationException, IllegalAccessException {
		Double heroHpDouble = super.getCharacterRemainingHp(currentEnemy);
		boolean result;
		if (heroHpDouble > 0d) {
			getInstance().setHp(heroHpDouble.intValue());
			result = true;
			new ConsolePrinter(Context.getInstance()) //
					.printLog(MessageConstants.MESSAGE_3, currentEnemy.getInstance().getSpecies(),
							heroHpDouble.intValue());
		} else {
			Double enemyHpDouble = currentEnemy.getCharacterRemainingHp(getInstance());
			new ConsolePrinter(Context.getInstance()) //
					.printLog(MessageConstants.MESSAGE_4, currentEnemy.getInstance().getSpecies(),
							enemyHpDouble.intValue());
			result = false;
		}
		return result;
	}
}
