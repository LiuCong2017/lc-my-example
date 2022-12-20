import demo.DataBaseFactory;
import demo.KeyValueIDataFactory;
import demo.factory.IDataBase;
import demo.factory.IKeyValueData;

public class Main {
	public static void main(String[] args) {
		IDataBase dataBase = new DataBaseFactory().getDataBase();
		IKeyValueData keyValueData = new KeyValueIDataFactory().getKeyValueData();

		dataBase.insert();
		keyValueData.add();
	}
}
