package refactoredGTA;

import java.util.ArrayList;

public class Update {
	private String typeName;
	private int typeNum;
	private ArrayList<String> updateFields;
	private ArrayList<String> updateData;
	public Update(String typeName, int typeNum, ArrayList<String> updateFields, ArrayList<String> updateData)
	{
		this.typeName = typeName;
		this.typeNum = typeNum;
		this.updateFields =updateFields;
		this.updateData = updateData;
		
	}
	public String getTypeName()
	{
		return typeName;
	}
	public int getTypeNum()
	{
		return typeNum;
	}
	public ArrayList<String> getUpdateFields()
	{
		return updateFields;
	}
	public ArrayList<String> getUpdateData()
	{
		return updateData;
	}
	
}
