package Beans;


public class Token {
	
	Object tokenValue;
	short tokenYYVal;
	int colNum;
	int lineNum;
	
	public Token(short yyval,Object val,int colNum,int lineNum){
		this.setColNum(colNum);
		this.setLineNum(lineNum);
		this.setTokenValue(val);
		this.setTokenYYVal(yyval);
	}
	
	public int getColNum() {
		return colNum;
	}
	public void setColNum(int colNum) {
		this.colNum = colNum;
	}
	public int getLineNum() {
		return lineNum;
	}
	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}
	public short getTokenYYVal() {
		return tokenYYVal;
	}
	public void setTokenYYVal(short tokenYYVal) {
		this.tokenYYVal = tokenYYVal;
	}
	public Object getTokenValue() {
		return tokenValue;
	}
	public void setTokenValue(Object val) {
		this.tokenValue = val;
	}
	

}
