package org.gwtproject.tutorial.server.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.BiConsumer;

import org.gwtproject.tutorial.server.constants.Constants;
import org.gwtproject.tutorial.server.constants.MessageConstants;
import org.gwtproject.tutorial.server.pojo.Context;
import org.gwtproject.tutorial.server.pojo.Enemy;
import org.gwtproject.tutorial.server.pojo.Field;
import org.gwtproject.tutorial.server.pojo.Hero;

public class ContextHelper extends Context {
	private Optional<Context> context;

	public ContextHelper() {
		this.context = super.getInstance();
	}

	public Optional<Context> toInstance() {
		return context;
	}

	public ContextHelper applyLineInfo(LineInfo lineInfo) {
		return lineInfo.putParsedLineIntoContext(this);
	}

	public ContextHelper setRange(String[] info) throws InstantiationException, IllegalAccessException {
		Field field = context.map(Context::getField).orElse(new Field());
		String rangeString = info[0];
		Integer range = new IntegerConverter(rangeString).convert();
		field.setRange(range);
		context.ifPresent(con -> con.setField(field));
		return this;
	}

	public <T extends org.gwtproject.tutorial.server.pojo.base.Character> ContextHelper setCharacterHP(
			String[] characterInfo) throws InstantiationException, IllegalAccessException {
		return setCharacterPoint(characterInfo, org.gwtproject.tutorial.server.pojo.base.Character::setHp);
	}

	public <T extends org.gwtproject.tutorial.server.pojo.base.Character> ContextHelper setCharacterAttackPoint(
			String[] characterInfo) throws InstantiationException, IllegalAccessException {
		return setCharacterPoint(characterInfo, org.gwtproject.tutorial.server.pojo.base.Character::setAttackPoint);
	}

	private <T extends org.gwtproject.tutorial.server.pojo.base.Character> ContextHelper setCharacterPoint(
			String[] characterInfo, BiConsumer<T, Integer> setter)
			throws InstantiationException, IllegalAccessException {
		List<Enemy> enemyList = context.map(Context::getEnemyList).orElse(new ArrayList<Enemy>());
		String characterType = characterInfo[0];
		Integer point = new IntegerConverter(characterInfo[1]).convert();
		if (new StringChecker(characterType).isNotEmpty()) {
			if (characterType.equalsIgnoreCase(Constants.HERO)) {
				Hero hero = context.map(Context::getHero).orElse(new Hero());
				setter.accept((T) hero, point);
				context.ifPresent(con -> con.setHero(hero));
			} else {
				Optional<Enemy> optEnemy = enemyList //
						.stream() //
						.filter(enemy -> characterType.equalsIgnoreCase(enemy.getSpecies())) //
						.findFirst();
				boolean optEnemyIsPresent = optEnemy.isPresent();
				Enemy enemy = optEnemy.orElse(new Enemy());
				enemy.setSpecies(characterType);
				setter.accept((T) enemy, point);
				if (!optEnemyIsPresent) {
					enemyList.add(enemy);
				}
			}
		}
		context.ifPresent(con -> con.setEnemyList(enemyList));
		return this;
	}

	public ContextHelper setEnemyPosition(String[] characterInfo)
			throws InstantiationException, IllegalAccessException {
		List<Enemy> enemyList = context.map(Context::getEnemyList).orElse(new ArrayList<Enemy>());
		Field field = context.map(Context::getField).orElse(new Field());
		SortedMap<Integer, Enemy> enemyMap = new PojoGetter<SortedMap>(field.getEnemyMap(), TreeMap.class).get();
		String characterType = characterInfo[0];
		Integer position = new IntegerConverter(characterInfo[1]).convert();
		if (new StringChecker(characterType).isNotEmpty()) {
			Enemy enemy = enemyList //
					.stream() //
					.filter((e) -> characterType.equalsIgnoreCase(e.getSpecies())) //
					.findFirst() //
					.orElseThrow(() -> {
						String formattedMessage = String.format(MessageConstants.MESSAGE_6, characterType);
						return new RuntimeException(formattedMessage);
					});
			enemyMap.put(position, enemy);
		}
		field.setEnemyMap(enemyMap);
		return this;
	}

	public ContextHelper setEnemy(String[] info) throws InstantiationException, IllegalAccessException {
		List<Enemy> enemyList = context.map(Context::getEnemyList).orElse(new ArrayList<Enemy>());
		String characterType = info[0];
		if (new StringChecker(characterType).isNotEmpty()) {
			Optional<Enemy> optEnemy = enemyList //
					.stream() //
					.filter((enemy) -> characterType.equalsIgnoreCase(enemy.getSpecies())) //
					.findFirst();
			boolean optEnemyIsPresent = optEnemy.isPresent();
			Enemy enemy = optEnemy.orElse(new Enemy());
			enemy.setSpecies(characterType);
			if (!optEnemyIsPresent) {
				enemyList.add(enemy);
			}
		}
		context.ifPresent(con -> con.setEnemyList(enemyList));
		return this;
	}

}
