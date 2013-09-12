package spring.tutorial.instantiate.bean;

public class SampleBeanFactory {

	public static SampleBean createSampleBean(){
		return new SampleBean();
	}
	
	// the parameter name must be the same as SampleBean's constructor arguments, here is intVal. Attention, it's not the class field "intValue".
	public static SampleBean createSampleBean(int intVal){
		return new SampleBean(intVal);
	}
	
	public static SampleBean createSampleBean(ReferencedBean refBean){
		return new SampleBean(refBean);
	}
}
