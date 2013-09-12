package spring.tutorial.instantiate.bean;

import org.springframework.beans.factory.FactoryBean;

public class SampleBeanFactoryBean implements FactoryBean<SampleBean> {
	
	private int intVal;
	private float floatVal;

	@Override
	public SampleBean getObject() throws Exception {
		SampleBean s = new SampleBean(intVal);
		s.setFloatValue(floatVal);
		return s;
	}

	@Override
	public Class<?> getObjectType() {
		return SampleBean.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}
	
	public int getIntVal() {
		return intVal;
	}

	public void setIntVal(int intVal) {
		this.intVal = intVal;
	}
	
	public float getFloatVal() {
		return floatVal;
	}

	public void setFloatVal(float floatVal) {
		this.floatVal = floatVal;
	}

}
