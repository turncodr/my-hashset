package turncodr.rocks;



class TestObject {
	
	int value;
	public TestObject(int i){
		value = i;
	}

	public int getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return "TestObject" + value;
	}

	@Override
	public boolean equals(Object o){
		if(o==null){
			return false;
		}

		if(this==o){
			return true;
		}

		if ((o instanceof TestObject) && (((TestObject) o).getValue() == this.value)) {
			return true;
		} else {
			return false;
		}
	}

	public int hashCode() {
		if(value < 1){
			return 0;
		}
		else if(value < 3){
			return 1;
		}
		else if(value < 5){
			return 2;
		}
		else if(value < 7){
			return 3;
		}
		else if(value < 9){
			return 4;
		}
		else if(value < 11){
			return 5;
		}
		
		return 6;
	
	}

}
