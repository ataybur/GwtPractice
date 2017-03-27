package org.gwtproject.tutorial.server.pojo;

public class Enemy extends org.gwtproject.tutorial.server.pojo.base.Character {

	public Enemy() {
		super();
	}

	public Enemy(Enemy instance) {
		super(instance);
		if (instance != null) {
			this.species = instance.getSpecies();
		}
	}

	private String species;

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	@Override
	public String toString() {
		return "Enemy [species=" + species + ", getHp()=" + getHp() + ", getAttackPoint()=" + getAttackPoint() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((species == null) ? 0 : species.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enemy other = (Enemy) obj;
		if (species == null) {
			if (other.species != null)
				return false;
		} else if (!species.equals(other.species))
			return false;
		return true;
	}

}
