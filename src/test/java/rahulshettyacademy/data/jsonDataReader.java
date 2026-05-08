package rahulshettyacademy.data;

import java.util.List;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class jsonDataReader {
	
	
	//TypeReference means whats is your DTO file name or \poso file name
	public static<T> Object[][] jsonDataAsReader(String FilePath,TypeReference<List<T>>TypeRef)
	{
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<T> dataList = 	mapper.readValue(new File(FilePath),TypeRef);
			
			Object [][] result = new Object[dataList.size()][1];
			
			for (int i = 0; i < dataList.size(); i++) {
				result[i][0] = dataList.get(i);
            }
			return result;

		} catch (IOException e) {
			e.printStackTrace();
			return new Object [0][0];
		}
	}

}
