package com.vtcmer.app.model.entities;

public class User {
	
	public User(){}
	
	/**
	 * Instantiates a new user.
	 *
	 * @param id the id
	 * @param name the name
	 * @param age the age
	 * @param salary the salary
	 */
	public User(final long id, final String name, final int age, final double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    private long id;

    private String name;

    private int age;

    private double salary;

    public long getId() {
        return this.id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(final double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(final Object other) {
        boolean result = false;
        if (other instanceof User) {
            final User that = (User) other;
            result = (this.getId() == that.getId());
        }
        return result;
    }


}
