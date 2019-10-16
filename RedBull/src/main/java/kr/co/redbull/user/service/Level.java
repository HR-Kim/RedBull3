package kr.co.redbull.user.service;

public enum Level {
	
	MANAGER(90, null) ,GOLD(30, MANAGER), SILVER(20, GOLD), BASIC(10, SILVER);
	
	// 멤버변수
	private final int value;
	private final Level nextLevel;
	
	
	// 생성자
	Level(int value, Level next) {
		
		this.value = value;
		nextLevel = next;
	}
	
	public Level getNextLevel() {
		
		return nextLevel;
	}
	
	// DB Insert 시 사용 Data
	public int intValue() {
		
		return value;
	}
	
	// DB에서 가져올 때(Get)
	public static Level valueOf(int value) {
		
		switch(value) {
		
			case 10: return BASIC;
			
			case 20: return SILVER;
			
			case 30: return GOLD;
			
			case 90: return MANAGER;
			
			default: throw new AssertionError("Unknown value: " + value);
		
		}//--switch
	}

}//--enum
