package spring.tutorial.instantiate.bean;

public class SampleBean {

	private int intValue;
	private float floatValue;
	private ReferencedBean referencedBean;
	
	public SampleBean(){
		
	}
	
	// "intVal" is used by application-config.xml to instantiate the constructor, not "intValue"!
	public SampleBean(int intVal){
		this.intValue = intVal;
	}
	
	public SampleBean(ReferencedBean refBean){
		this.referencedBean = refBean;
	}
	
	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public float getFloatValue() {
		return floatValue;
	}

	public void setFloatValue(float floatValue) {
		this.floatValue = floatValue;
	}
	
	public String toString() {
		if(referencedBean ==null){
			return "This is a sample Bean having an integer:"+intValue+" and a float number:"+floatValue;
		}else{
			return referencedBean.toString();
		}
		
	}
}
