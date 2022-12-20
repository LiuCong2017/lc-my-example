package demo;

import demo.factory.IDataBase;
import demo.factory.IKeyValueData;
import demo.factory.idbimpl.MongoDB;
import demo.factory.idbimpl.MySql;
import demo.factory.idbimpl.Oracle;

public class DataBaseFactory implements IDataFactory{
    private boolean isMysql;
    private boolean isMongoDB;
    @Override
    public IKeyValueData getKeyValueData() {
        return null;
    }

    @Override
    public IDataBase getDataBase() {
        if (isMysql) {
            return new MySql();
        }
        if (isMongoDB) {
            return new MongoDB();
        }

        return new Oracle();
    }
}
