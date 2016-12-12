package sda.code.intermediate.part1.answers.patterns;

import sda.code.intermediate.part1.answers.data.Service;

public class ServiceBuilder extends ProductBuilder<ServiceBuilder, Service> {
	private Integer time;

	public ServiceBuilder withTime(Integer time) {
		this.time = time;
		return this;
	}

	@Override
	protected void validate() {
		super.validate();
		if (time == null || time <= 0) {
			throw new InvalidBuilderState("Time cannot be zero or less");
		}
	}

	@Override
	public Service build() {
		prepare();
		validate();
		return new Service(name, price, time);
	}

}
